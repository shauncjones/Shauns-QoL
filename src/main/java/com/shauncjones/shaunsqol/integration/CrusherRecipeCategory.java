package com.shauncjones.shaunsqol.integration;

import com.shauncjones.shaunsqol.ShaunsQoL;
import com.shauncjones.shaunsqol.block.ModBlocks;
import com.shauncjones.shaunsqol.recipe.CrusherRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class CrusherRecipeCategory implements IRecipeCategory<CrusherRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(ShaunsQoL.MOD_ID, "crushing");
    public final static ResourceLocation TEXTURE = new ResourceLocation(ShaunsQoL.MOD_ID, "textures/gui/machine_crusher_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public CrusherRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.MACHINE_CRUSHER.get()));
    }

    @Override
    public RecipeType<CrusherRecipe> getRecipeType() {
        return JEIShaunsQoLPlugin.CRUSHER_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Crusher");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrusherRecipe recipe, IFocusGroup focuses) {
            builder.addSlot(RecipeIngredientRole.INPUT, 86, 15).addIngredients(recipe.getIngredients().get(0));
            builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60).addItemStack(recipe.getResultItem());
    }
}
