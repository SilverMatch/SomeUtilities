package com.silvermatch.someutilities.client;

import com.silvermatch.someutilities.network.MessageExplode;
import com.silvermatch.someutilities.network.NetworkHandler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputHandler{
    private Keybindings getPressedKey(){
        for(Keybindings key : Keybindings.values()) {
            if(key.isPressed()) return key;
        }
        return null;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event){
        Keybindings key = getPressedKey();
        if(key != null) {
            switch(key){
			default:
				break;
            }
        }
    }
}
