package com.shauncjones.shaunsqol.client;

import com.shauncjones.shaunsqol.item.ModItems;
import com.shauncjones.shaunsqol.item.custom.BackpackItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class ColorHandlers {

    public static void registerItemColors(RegisterColorHandlersEvent.Item event){
        event.getItemColors().register(BackpackItem::getItemColor, ModItems.BACKPACK.get());
    }

}
