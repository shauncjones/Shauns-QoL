package com.shauncjones.shaunsqol.item;

import com.shauncjones.shaunsqol.ShaunsQoL;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ShaunsQoL.MOD_ID);
    
    public static final RegistryObject<Item> rawTin = ITEMS.register("raw_tin", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<Item> ingotTin = ITEMS.register("ingot_tin", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
