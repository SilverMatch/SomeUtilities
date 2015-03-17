package com.silvermatch.someutilities.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGeneric extends Block{

    public BlockGeneric(Material material){
        super(material);
    }

    public BlockGeneric(){
        this(Material.rock);
    }

}
