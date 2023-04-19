package com.shauncjones.shaunsqol.block.entity;

import com.shauncjones.shaunsqol.block.custom.CrusherBlock;
import com.shauncjones.shaunsqol.item.ModItems;
import com.shauncjones.shaunsqol.recipe.CrusherRecipe;
import com.shauncjones.shaunsqol.screen.CrusherMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrusherBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot){
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private int progress = 0;
    private int maxProgress = 78;
    private int litProgress = 0;
    private int maxLitDuration = 79;
    protected final ContainerData data;

    public CrusherBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MACHINE_CRUSHER.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch(pIndex){
                    case 0 -> CrusherBlockEntity.this.progress;
                    case 1 -> CrusherBlockEntity.this.maxProgress;
                    case 2 -> CrusherBlockEntity.this.litProgress;
                    case 3 -> CrusherBlockEntity.this.maxLitDuration;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch(pIndex){
                    case 0 -> CrusherBlockEntity.this.progress = pValue;
                    case 1 -> CrusherBlockEntity.this.maxProgress = pValue;
                    case 2 -> CrusherBlockEntity.this.litProgress = pValue;
                    case 3 -> CrusherBlockEntity.this.maxLitDuration = pValue;
                };
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    private boolean isLit() {
        return this.litProgress > 0;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Crusher");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CrusherMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side){
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap,side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemStackHandler.serializeNBT());
        pTag.putInt("crusher.progress", this.progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("crusher.progress");
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++){
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CrusherBlockEntity entity){
        if(level.isClientSide()){
            return;
        }

        if(hasRecipe(entity)){
            entity.progress++;
            setChanged(level,pos,state);
            if (!level.isClientSide()) {
                level.setBlock(pos,state.setValue(CrusherBlock.LIT, true), 3);
            }
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        }else{
            entity.resetProgress();
            setChanged(level,pos,state);
            if (!level.isClientSide()) {
                level.setBlock(pos,state.setValue(CrusherBlock.LIT, false), 3);
            }
        }

    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(CrusherBlockEntity pEntity){
        Level level = pEntity.level;
        SimpleContainer inventory = new SimpleContainer(pEntity.itemStackHandler.getSlots());
        for (int i = 0; i < pEntity.itemStackHandler.getSlots(); i++){
            inventory.setItem(i, pEntity.itemStackHandler.getStackInSlot(i));
        }

        Optional<CrusherRecipe> recipe = level.getRecipeManager().getRecipeFor(CrusherRecipe.Type.INSTANCE, inventory, level);

        if(hasRecipe(pEntity)){
            pEntity.itemStackHandler.extractItem(1, 1,false);
            pEntity.itemStackHandler.setStackInSlot(2,new ItemStack(recipe.get().getResultItem().getItem(),pEntity.itemStackHandler.getStackInSlot(2).getCount() + recipe.get().getResultItem().getCount()));
            pEntity.resetProgress();
        }
    }
    
    private static boolean hasRecipe(CrusherBlockEntity pEntity) {
        Level level = pEntity.level;
        SimpleContainer inventory = new SimpleContainer(pEntity.itemStackHandler.getSlots());
        for (int i = 0; i < pEntity.itemStackHandler.getSlots(); i++){
            inventory.setItem(i, pEntity.itemStackHandler.getStackInSlot(i));
        }

        Optional<CrusherRecipe> recipe = level.getRecipeManager().getRecipeFor(CrusherRecipe.Type.INSTANCE, inventory, level);

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(2).getItem() == itemStack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

}
