package com.shauncjones.shaunsqol.item.custom;

import com.shauncjones.shaunsqol.ShaunsQoL;
import com.shauncjones.shaunsqol.inventory.BackpackInventory;
import com.shauncjones.shaunsqol.screen.menu.item.BackpackMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BackpackItem extends Item {
    public static final int SIZE = 27;
    private static final String NBT_COLOR = "BackpackColor";
    public BackpackItem(Properties pProperties) {
        super(pProperties);
    }

    public static int getBackpackColor(ItemStack stack){
        return stack.getOrCreateTag().getInt(NBT_COLOR);
    }

    public static void setBackpackColor(ItemStack stack, int color){
        stack.getOrCreateTag().putInt(NBT_COLOR, color);
    }

    public static int getItemColor(ItemStack stack, int tintIndex){
        if(tintIndex == 0){
            return getBackpackColor(stack);
        }
        return 0xFFFFFF;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.shaunsqol.backpack"));
        }else{
            pTooltipComponents.add(Component.translatable("tooltip.shaunsqol.shift").withStyle(ChatFormatting.BLUE));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public static SimpleContainer getInventory(ItemStack stack) {
        return new BackpackInventory(stack, SIZE);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, @NotNull InteractionHand hand) {
        if (!world.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            NetworkHooks.openScreen((ServerPlayer) player, new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return stack.getHoverName();
                }

                @Override
                public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
                    return new BackpackMenu(syncId, inv, stack);
                }
            }, buf -> buf.writeBoolean(hand == InteractionHand.MAIN_HAND));
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if(pCategory == ShaunsQoL.SHAUNSQOL_TAB){
            for(DyeColor color : DyeColor.values()){
                ItemStack stack = new ItemStack(this);
                setBackpackColor(stack, color.getFireworkColor());
                pItems.add(stack);
            }
        }
    }
}
