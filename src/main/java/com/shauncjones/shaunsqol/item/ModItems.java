package com.shauncjones.shaunsqol.item;

import com.shauncjones.shaunsqol.ShaunsQoL;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    //Create a registry for mod items.
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ShaunsQoL.MOD_ID);

    public static final RegistryObject<Item> rawTin = ITEMS.register("raw_tin", () -> new Item(new Item.Properties().tab(ShaunsQoL.SHAUNSQOL_TAB)));
    public static final RegistryObject<Item> ingotTin = ITEMS.register("ingot_tin", () -> new Item(new Item.Properties().tab(ShaunsQoL.SHAUNSQOL_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
