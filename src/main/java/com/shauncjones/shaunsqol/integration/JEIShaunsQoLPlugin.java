package com.shauncjones.shaunsqol.integration;

import com.shauncjones.shaunsqol.ShaunsQoL;
import com.shauncjones.shaunsqol.recipe.CrusherRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIShaunsQoLPlugin implements IModPlugin {

    public static RecipeType<CrusherRecipe> CRUSHER_TYPE = new RecipeType<>(CrusherRecipeCategory.UID, CrusherRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ShaunsQoL.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrusherRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<CrusherRecipe> recipesCrushing = rm.getAllRecipesFor(CrusherRecipe.Type.INSTANCE);
        registration.addRecipes(CRUSHER_TYPE, recipesCrushing);
    }
}
