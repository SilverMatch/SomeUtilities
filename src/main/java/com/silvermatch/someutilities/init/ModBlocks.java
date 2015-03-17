package com.silvermatch.someutilities.init;

import net.minecraft.block.Block;

import com.silvermatch.someutilities.reference.Reference;
import com.silvermatch.someutilities.utility.Log;
import com.silvermatch.someutilities.utility.Names;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks{
    //public static final Block dutchFlag = new BlockDutchFlag();
    //public static final Block camoMine = new BlockCamoMine();

    public static void init(){
        //GameRegistry.registerBlock(dutchFlag, Names.Blocks.DUTCH_FLAG);
        //GameRegistry.registerBlock(camoMine, Names.Blocks.CAMO_MINE);

        Log.info("Modblocks initialized");
    }
}
