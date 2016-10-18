package io.github.harry9137.teatime.block;

import io.github.harry9137.teatime.TeaTime;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockTea extends BlockCropBase {
    public BlockTea() {
        super(Blocks.FARMLAND);
        this.setRegistryName(TeaTime.MODID, "tileTeaCrop");
    }
}
