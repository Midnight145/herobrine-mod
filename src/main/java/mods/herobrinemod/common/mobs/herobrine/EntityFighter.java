package mods.herobrinemod.common.mobs.herobrine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.Random;

public class EntityFighter extends EntityHerobrineBase {

    private int ingame = 0;
    private boolean start = false;
    public int wait = 0;

	public EntityFighter(World world, float x, float y, float z) {
		super(world);

		this.setPosition(x, y + (double) this.yOffset, z);
		world.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "ambient.cave.cave", 35F, 0.8F);
	}

	@Override
	public void onLivingUpdate() {
        Random rand = MinecraftServer.getServer().getEntityWorld().rand;
		super.onLivingUpdate();

		if (this.worldObj.isRemote) { return; }

		if (rand.nextInt(90 + 1) == 1) {
			final EntityPlayer toStalk = this.worldObj.getClosestPlayerToEntity(this, 30D);
			if (toStalk != null) {
				this.setPosition(toStalk.posX, toStalk.posY, toStalk.posZ + 3);
			}
		}
		this.wait++;
		if (!this.start) {
			final EntityPlayer toStalk = this.worldObj.getClosestPlayerToEntity(this, 30D);

			if (toStalk == null) { return; }

			this.faceEntity(toStalk, 30F, 30F);
		}

		if (this.wait == 45) {
			this.start = true;
			final EntityPlayer toStalk = this.worldObj.getClosestPlayerToEntity(this, 30D);

			if (toStalk == null) { return; }

			if (rand.nextInt(8 + 1) == 1) {
				this.worldObj.spawnEntityInWorld(new EntityFighterNotch(this.worldObj, (float) toStalk.posX + 0.5F,
						(float) toStalk.posY + 0.0F, (float) toStalk.posZ + 0.5F));

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
	protected Entity findPlayerToAttack() {
		if (!this.start) { return null; }

		final EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 16D);

        return this.canEntityBeSeen(entityplayer) ? entityplayer : null;
	}

	@Override
	protected boolean isMovementCeased() {
        return !this.start;
    }

	protected void dropFewItems() {
		final Item item = this.getDropItemId();
		this.dropItem(item, 1);
	}

	protected Item getDropItemId() {
		final int i = this.rand.nextInt(4 + 1);

		if (i == 1) { return Items.diamond_sword; }
		else if (i == 2) { return Items.golden_apple; }
		else if (i == 3) { return Items.cake; }
		else if (i == 4) { return Items.cookie; }

		return null;
	}

    @Override
    public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.UNDEAD; }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0f);
    }

}
