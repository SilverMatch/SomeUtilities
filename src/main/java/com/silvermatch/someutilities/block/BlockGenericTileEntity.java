package com.silvermatch.someutilities.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BlockGenericTileEntity extends BlockContainer{
    public BlockGenericTileEntity(Material material){
        super(material);
    }

    public BlockGenericTileEntity(){
        this(Material.rock);
    }

}
