package com.silvermatch.someutilities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

import com.silvermatch.someutilities.gui.GuiSomeUtilities;
import com.silvermatch.someutilities.tileentity.TileEntitySomeUtilities;

import cpw.mods.fml.common.network.ByteBufUtils;

public class MessageHandleTextUpdate extends MessageXYZ<MessageHandleTextUpdate>{
    private int id;
    private String text;

    public MessageHandleTextUpdate(){}

    public MessageHandleTextUpdate(TileEntitySomeUtilities te, int id, String text){
        super(te);
        this.id = id;
        this.text = text;
    }

    @Override
    public void fromBytes(ByteBuf buf){
        super.fromBytes(buf);
        id = buf.readInt();
        text = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf){
        super.toBytes(buf);
        buf.writeInt(id);
        ByteBufUtils.writeUTF8String(buf, text);
    }

    @Override
    public void handleClientSide(MessageHandleTextUpdate message, EntityPlayer player){
        handleServerSide(message, player);
        GuiScreen gui = Minecraft.getMinecraft().currentScreen;//<-- Warning, this will crash when tested on server, will be discussed next episode.
        if(gui instanceof GuiSomeUtilities) {
            ((GuiSomeUtilities)gui).onTextfieldUpdate(message.id);
        }
    }

    @Override
    public void handleServerSide(MessageHandleTextUpdate message, EntityPlayer player){
        TileEntity te = message.getTileEntity(player.worldObj);
        if(te instanceof TileEntitySomeUtilities) {
            ((TileEntitySomeUtilities)te).onGuiTextfieldUpdate(message.id, message.text);
        }
    }

}
