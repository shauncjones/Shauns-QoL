package com.shauncjones.shaunsqol.screen;

import com.shauncjones.shaunsqol.ShaunsQoL;
import com.shauncjones.shaunsqol.screen.menu.block.CrusherMenu;
import com.shauncjones.shaunsqol.screen.menu.item.BackpackMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ShaunsQoL.MOD_ID);

    public static final RegistryObject<MenuType<CrusherMenu>> CRUSHER_MENU = registerMenuType(CrusherMenu::new, "crusher_menu");
    public static final RegistryObject<MenuType<BackpackMenu>> BACKPACK_MENU = registerMenuType(BackpackMenu::fromNetwork, "backpack_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }

}
