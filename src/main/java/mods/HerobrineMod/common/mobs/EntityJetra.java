package mods.HerobrineMod.common.mobs;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EntityJetra extends EntityAnimal {
	
	public EntityJetra(World world, double x, double y, double z) {
		super(world);
		
		setPosition(x, y + (double) yOffset, z);
		
		this.heal(5.0f);
	}
	
	public EntityJetra(World world) {
		super(world);
		setSize(0.9F, 1.3F);
		
	}
	
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		speak_down++;
		if (speak_timer < speak_down) {
			speak_down = 0;
			speak_timer = (int) (Math.random() * 300 + 1);
			EntityPlayer entityplayer2 = worldObj.getClosestPlayerToEntity(this, 3000D);
			int msg = (int) (Math.random() * phrases.length + 1);
			msg -= 1;
			if (!worldObj.isRemote && entityplayer2 != null)
				MinecraftServer.getServer().getConfigurationManager()
						.sendChatMsg(new ChatComponentText("Jetra says: " + phrases[msg]));
			
		}
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String[] phrases = { "I feel lonely", "PLEASE!! DON'T HIT ME!", "Why am I here?",
			"Lol I'm in the herobrine mod.", "It hurts.", "I'm useless.", "My mum doesn't love me anymore :(",
			"I'm smelly -.-", "DafUQ BRO?", "Whuut?", "Blablablablabla", "I have no friends.",
			"My parents don't love me.", "Hi I'm 12.", "Muuuuh!", "Wanna be my friend?", "I'm sad :(", "I'm an emo.",
			"I have no gf.", "Everybody hates me :(" };
	private int speak_down = 0;
	private int speak_timer = 50;
}
