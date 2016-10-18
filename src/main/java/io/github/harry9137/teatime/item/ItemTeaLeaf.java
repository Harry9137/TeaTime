package io.github.harry9137.teatime.item;

import io.github.harry9137.teatime.TeaTime;
import net.minecraft.item.Item;

public class ItemTeaLeaf extends Item {
    public ItemTeaLeaf(){
        super();
        this.setUnlocalizedName("itemTeaLeaf");
        this.setRegistryName(TeaTime.MODID, "itemTeaLeaf");
    }
}
