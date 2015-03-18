package com.silvermatch.someutilities.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import com.silvermatch.someutilities.SomeUtilities;
import com.silvermatch.someutilities.keepxp.PlayerXP;
import com.silvermatch.someutilities.utility.Log;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class KeepXPEventHandler{
	

    /**
     * Using the LivingDropsEvent. Note that this is from  net.minecraftforge.event, so to make this method be called, this class needs to be registered at MinecraftForge.EVENT_BUS.register(new AdvancedModEventHandler()).
     * @param event
     */
    @SubscribeEvent
    public void removePlayerXPDrops(LivingDropsEvent event){
        if(event.entityLiving instanceof EntityPlayer) {
            event.lootingLevel = 0;
        }
    }
	
	
  /**
  * Using the LivingDeathEvent. Note that this is from  net.minecraftforge.event, so to make this method be called, this class needs to be registered at MinecraftForge.EVENT_BUS.register(new SomeUtilitiesEventHandler()).
  * @param event
  */
 @SubscribeEvent
 public void rememberPlayerXP(LivingDeathEvent event){
     if (!event.entityLiving.worldObj.isRemote)
     {
		 if(event.entityLiving instanceof EntityPlayer) {
	    	 Log.info("A player has died");
	    	 EntityPlayer deadPlayer = (EntityPlayer) event.entityLiving;
	    	 PlayerXP playerXP = new PlayerXP(deadPlayer.getUniqueID(), deadPlayer.experience, deadPlayer.experienceLevel, deadPlayer.experienceTotal);
	 		for(ModContainer mod : Loader.instance().getModList())
	 		{
	 			
	 			 Object c = mod.getMod();
	 			 if (c instanceof SomeUtilities)
	 			 {
	 				Log.info("Storing deadPlayer");
	 				 ((SomeUtilities) c).keepXP.addDeadPlayer(playerXP);
	 			 }
	 		}
		 }
    }
 }
 
 @SubscribeEvent
 public void restorePlayerXP(EntityJoinWorldEvent event){
     if (!event.world.isRemote)
     {
		 if(event.entity instanceof EntityPlayer) {
	    	 Log.info("A player has spawned");
	    	 EntityPlayer respawnedPlayer = (EntityPlayer) event.entity;
	 		for(ModContainer mod : Loader.instance().getModList())
	 		{
	 			
	 			 Object c = mod.getMod();
	 			 if (c instanceof SomeUtilities)
	 			 {
	 				Log.info("Getting Player from dead list");
	 				PlayerXP playerXP = ((SomeUtilities) c).keepXP.getDeadPlayer(respawnedPlayer.getUniqueID());
	 				if (playerXP != null)
	 				{
	 					Log.info("Dead Player found");
	 					respawnedPlayer.experience = playerXP.getExperience();
	 					respawnedPlayer.experienceLevel = playerXP.getExperienceLevel();
	 					respawnedPlayer.experienceTotal = playerXP.getExperienceTotal();
	 				}
	 				else
	 					Log.info("Dead Player not found");
	 			 }
	 		}
	    }
    }
 }
 
//ItemStack stack = new ItemStack(ModBlocks.dutchFlag);
 //event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, stack));


//    /**
//     * Using the PlayerTickEvent. Note that this is from cpw.mods.fml.common.gameevent, so to make this method be called, this class needs to be registered at FMLCommonHandler.instance().bus().register(new AdvancedModEventHandler()).
//     * @param event
//     */
//    @SubscribeEvent
//    public void onPlayerTick(PlayerTickEvent event){
//        if(event.side == Side.SERVER && event.phase == TickEvent.Phase.END) {
//            List<Entity> entities = event.player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(event.player.posX - 3, event.player.posY - 3, event.player.posZ - 3, event.player.posX + 3, event.player.posY + 3, event.player.posZ + 3));
//            for(Entity entity : entities) {
//                if(entity != event.player) {
//                    //entity.setVelocity(0, 1, 0); //This will crash when run on a dedicated server, because Entity#setVelocity is marked with @SideOnly(Side.CLIENT), and therefore stripped from a dedicated server instance.
//                    entity.motionX = 0;//The solution is to do this.
//                    entity.motionY = 1;
//                    entity.motionZ = 0;
//                }
//            }
//        }
//    }
}
