package com.shauncjones.shaunsqol.block.entity;

import com.shauncjones.shaunsqol.ShaunsQoL;
import com.shauncjones.shaunsqol.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ShaunsQoL.MOD_ID);

    public static final RegistryObject<BlockEntityType<CrusherBlockEntity>> MACHINE_CRUSHER = BLOCK_ENTITIES.register("machine_crusher", () -> BlockEntityType.Builder.of(CrusherBlockEntity::new, ModBlocks.MACHINE_CRUSHER.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
