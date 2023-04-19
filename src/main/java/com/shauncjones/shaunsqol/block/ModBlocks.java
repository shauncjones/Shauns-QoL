package com.shauncjones.shaunsqol.block;

import com.shauncjones.shaunsqol.ShaunsQoL;
import com.shauncjones.shaunsqol.block.custom.CrusherBlock;
import com.shauncjones.shaunsqol.block.custom.InfusingStationBlock;
import com.shauncjones.shaunsqol.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ShaunsQoL.MOD_ID);

    //Tin Blocks
    public static final RegistryObject<Block> BLOCK_TIN = registerBlock("block_tin", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops()), ShaunsQoL.SHAUNSQOL_TAB);
    public static final RegistryObject<Block> ORE_TIN = registerBlock("ore_tin", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7)), ShaunsQoL.SHAUNSQOL_TAB);
    public static final RegistryObject<Block> DEEPSLATE_ORE_TIN = registerBlock("deepslate_ore_tin", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7)), ShaunsQoL.SHAUNSQOL_TAB);

    //Infusing Station
    public static final RegistryObject<Block> INFUSING_STATION = registerBlock("infusing_station",() -> new InfusingStationBlock(BlockBehaviour.Properties.of(Material.METAL).strength(6f).noOcclusion()), ShaunsQoL.SHAUNSQOL_TAB);

    //Crusher
    public static final RegistryObject<Block> MACHINE_CRUSHER = registerBlock("machine_crusher",() -> new CrusherBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2f).lightLevel(state -> state.getValue(CrusherBlock.LIT)? 15 : 0)), ShaunsQoL.SHAUNSQOL_TAB);

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
