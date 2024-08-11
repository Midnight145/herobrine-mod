package mods.HerobrineMod.client;

import mods.HerobrineMod.UpdateChecker.Notifier;
import mods.HerobrineMod.UpdateChecker.UpdateChecker;
import mods.HerobrineMod.common.HerobrineMod;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;



public class ClientTickHandler implements Notifier
{
        private boolean checkUpdate = true;

        @SubscribeEvent
        public void tickStart(ClientTickEvent event)
        {
              //  ClientProxy.onTickInGame(Minecraft.getMinecraft());

            	if(HerobrineMod.prank==1 || HerobrineMod.reminder == 0) return;

        		if(checkUpdate && Minecraft.getMinecraft().thePlayer != null){
        			UpdateChecker.checkVersion("herobrine",HerobrineMod.getVersion(), this);
        			checkUpdate = false;
        		}

        }

        @Override
        public void notifyOnUpdate()
        {
                MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText("§b New update available for The Herobrine Mod!"));
        }

}


