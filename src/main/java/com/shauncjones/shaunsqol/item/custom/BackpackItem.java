package com.shauncjones.shaunsqol.item.custom;

import com.shauncjones.shaunsqol.block.entity.CrusherBlockEntity;
import com.shauncjones.shaunsqol.item.ModItems;
import com.shauncjones.shaunsqol.item.entity.BackpackItemEntity;
import com.shauncjones.shaunsqol.screen.menu.item.BackpackMenu;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BackpackItem extends Item {

    public BackpackItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return super.hasCustomEntity(stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide() && pUsedHand == InteractionHand.MAIN_HAND){
            NetworkHooks.openScreen(((ServerPlayer) pPlayer), new SimpleMenuProvider((containerId, playerInventory, player) -> new BackpackMenu(containerId, playerInventory), Component.translatable("menu.title.examplemod.mymenu")));
        }

        return super.use(pLevel,pPlayer,pUsedHand);
    }

}
