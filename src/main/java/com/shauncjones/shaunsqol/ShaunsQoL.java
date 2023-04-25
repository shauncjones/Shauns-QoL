package com.shauncjones.shaunsqol;

import com.shauncjones.shaunsqol.block.ModBlocks;
import com.shauncjones.shaunsqol.block.entity.ModBlockEntities;
import com.shauncjones.shaunsqol.client.ColorHandlers;
import com.shauncjones.shaunsqol.item.ModItems;
import com.shauncjones.shaunsqol.recipe.ModRecipes;
import com.shauncjones.shaunsqol.screen.screen.block.CrusherScreen;
import com.shauncjones.shaunsqol.screen.ModMenuTypes;
import com.shauncjones.shaunsqol.screen.screen.item.BackpackScreen;
import com.shauncjones.shaunsqol.world.feature.ModConfiguredFeatures;
import com.shauncjones.shaunsqol.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

//TODO: BUG HAS ALL BACKPACKS SHOWING IN ALL CREATIVE TABS

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ShaunsQoL.MOD_ID)
public class ShaunsQoL {
    public static final String MOD_ID = "shaunsqol";
    public static final Logger LOGGER = LogManager.getLogger();

    //Create custom creative mode tab with the Tin Ingot as the icon.
    public static final CreativeModeTab SHAUNSQOL_TAB = new CreativeModeTab("shaunsqoltab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.BLOCK_TIN.get());
        }
    };

    private IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    @Nonnull
    public static ResourceLocation getId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public ShaunsQoL() {
        LOGGER.info("Shaun's QoL: Loading Shaun's QoL Version 0.0.1-1.19.2");

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(ColorHandlers::registerItemColors);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        LOGGER.info("Shaun's QoL: Loaded " + ModItems.ITEMS.getEntries().size() + " items & " + ModBlocks.BLOCKS.getEntries().size() + " blocks");

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.CRUSHER_MENU.get(), CrusherScreen::new);
            MenuScreens.register(ModMenuTypes.BACKPACK_MENU.get(), BackpackScreen::new);
        }
    }
}
