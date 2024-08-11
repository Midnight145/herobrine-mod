package mods.HerobrineMod.client;


import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class SoundLoader 
{
	@SideOnly(Side.CLIENT)
	public void onSoundsLoaded(SoundLoadEvent event)
	{
		SoundManager manager = event.manager;
		
	//	manager.soundPoolStreaming.addSound("herobrinemod:seeherobrine.ogg");
		//manager.soundPoolStreaming.addSound("herobrinemod:itsherobrine.ogg");

	}
}