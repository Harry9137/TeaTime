package io.github.harry9137.teatime.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {
    public static void init(){
        GameRegistry.addShapelessRecipe(new ItemStack(ModCrops.TeaSeed), new Object[]{ModCrops.TeaLeaf});
    }
}
