package io.github.harry9137.teatime.item;

import io.github.harry9137.teatime.TeaTime;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class ItemTeaSeed extends ItemSeeds {
    public ItemTeaSeed(Block crops) {
        super(crops, Blocks.FARMLAND);
        this.setUnlocalizedName("itemTeaSeed");
        this.setRegistryName(TeaTime.MODID, "itemTeaSeed");
    }
}
