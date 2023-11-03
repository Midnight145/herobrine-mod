package mods.HerobrineMod.common.mobs.herobrine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityMob, World, MathHelper, Item

public class EntityFighter extends EntityHerobrineBase {

	public EntityFighter(World world, double x, double y, double z) { super(world); }

	public EntityFighter(World world) { super(world); }

	public int func_82193_c(Entity par1Entity) { return 8; }

	public EntityFighter(World worldObj, float f, float g, float h, boolean q) {
		super(worldObj);

		this.setPosition(f, g + (double) this.yOffset, h);

		this.ingame = 0;
		this.wait = 0;
		this.start = false;
		worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "ambient.cave.cave", 35F, 0.8F);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.worldObj.isRemote) { return; }

		if ((int) (Math.random() * 90 + 1) == 1) {
			final EntityPlayer stalk2 = this.worldObj.getClosestPlayerToEntity(this, 30D);
			if (stalk2 != null) {
				this.setPosition(stalk2.posX, stalk2.posY, stalk2.posZ + 3);
			}
		}
		this.wait++;

		if (this.wait < 45) {

		}

		if (!this.start) {
			final EntityPlayer stalk2 = this.worldObj.getClosestPlayerToEntity(this, 30D);

			if (stalk2 == null) { return; }

			this.faceEntity(stalk2, 30F, 30F);
		}

		if (this.wait == 45) {
			this.start = true;
			final EntityPlayer stalk = this.worldObj.getClosestPlayerToEntity(this, 30D);

			if (stalk == null) { return; }

			if ((int) (Math.random() * 8 + 1) == 1) {
				this.worldObj.spawnEntityInWorld(new EntityFighter2(this.worldObj, (float) stalk.posX + 0.5F,
						(float) stalk.posY + 0.0F, (float) stalk.posZ + 0.5F));

				MinecraftServer.getServer().getConfigurationManager()
						.sendChatMsg(new ChatComponentText("Herobrine: Hi brother!"));

				MinecraftServer.getServer().getConfigurationManager()
						.sendChatMsg(new ChatComponentText("Notch: I don't want you in my game!"));

			}
		}

		if (this.start) {
			this.ingame++;
		}

		if (this.ingame == 800) {
			this.setDead();
		}
	}

	@Override
	public void setDead() {

		final EntityPlayer stalk2 = this.worldObj.getClosestPlayerToEntity(this, 30D);

		if (stalk2 == null) { return; }

		super.setDead();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.UNDEAD; }

	@Override
	protected Entity findPlayerToAttack() {
		if (!this.start) { return null; }

		final EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 16D);

		if (entityplayer == null) { return null; }

		if (entityplayer != null && this.canEntityBeSeen(entityplayer)) { return entityplayer; }
		return null;
	}

	@Override
	protected boolean isMovementCeased() {
		if (!this.start) { return true; }

		return false;
	}

	protected void dropFewItems() {
		final Item i = this.getDropItemId();
		this.dropItem(i, 1);
	}

	protected Item getDropItemId() {
		final int i = (int) (Math.random() * 4 + 1);

		if (i == 1) { return Items.diamond_sword; }

		if (i == 2) { return Items.golden_apple; }

		if (i == 3) { return Items.cake; }

		if (i == 4) { return Items.cookie; }

		return null;
	}

	private int ingame;
	private boolean start;
	public int wait;

}
