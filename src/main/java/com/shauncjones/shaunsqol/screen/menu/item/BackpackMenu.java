package com.shauncjones.shaunsqol.screen.menu.item;

import com.shauncjones.shaunsqol.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class BackpackMenu extends AbstractContainerMenu {

    private final Level level;
    private final ContainerData data;

    public BackpackMenu(int id, Inventory inv) {
        this(id, inv, new SimpleContainerData(2));
    }

    public BackpackMenu(int id, Inventory inv, ContainerData data) {
        super(ModMenuTypes.CRUSHER_MENU.get(), id);
        this.level = inv.player.level;
        this.data = data;
    }



    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }
}
