package com.shauncjones.shaunsqol.item;

import com.shauncjones.shaunsqol.ShaunsQoL;
import com.shauncjones.shaunsqol.item.custom.BackpackItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    //Create a registry for mod items.
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ShaunsQoL.MOD_ID);

    //Tin Items
    public static RegistryObject<Item> RAWTIN = ITEMS.register("raw_tin", () -> new Item(new Item.Properties().tab(ShaunsQoL.SHAUNSQOL_TAB)));
    public static RegistryObject<Item> TININGOT = ITEMS.register("ingot_tin", () -> new Item(new Item.Properties().tab(ShaunsQoL.SHAUNSQOL_TAB)));

    public static RegistryObject<Item> BACKPACK = ITEMS.register("backpack", () -> new BackpackItem(new Item.Properties().tab(ShaunsQoL.SHAUNSQOL_TAB).stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
