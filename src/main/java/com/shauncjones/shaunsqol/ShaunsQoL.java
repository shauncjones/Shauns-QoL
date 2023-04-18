package com.shauncjones.shaunsqol;

import com.mojang.logging.LogUtils;
import com.shauncjones.shaunsqol.block.ModBlocks;
import com.shauncjones.shaunsqol.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ShaunsQoL.MOD_ID)
public class ShaunsQoL {
    public static final String MOD_ID = "shaunsqol";
    private static final Logger LOGGER = LogUtils.getLogger();

    //Create custom creative mode tab with the Tin Ingot as the icon.
    public static final CreativeModeTab SHAUNSQOL_TAB = new CreativeModeTab("shaunsqoltab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ingotTin.get());
        }
    };

    public ShaunsQoL() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
