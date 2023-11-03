package mods.HerobrineMod.common.mobs;

import mods.HerobrineMod.common.HerobrineMod;
import mods.HerobrineMod.common.mobs.herobrine.EntityFighter;
import mods.HerobrineMod.common.mobs.herobrine.EntityStalker;
import mods.HerobrineMod.common.mobs.herobrine.HerobrineStalker;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityNorris extends EntityStalker {

	public EntityNorris(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub

	}

	////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////
	public EntityNorris(World world, double d, double d1, double d2) {
		this(world);

		if (!HerobrineMod.canSpawn) // !worldObj.isRemote)
		{
			this.setDead();
		}

		this.setPosition(d, d1 + this.yOffset, d2);

		if ((int) (Math.random() * 30 + 1) == 10) {
			this.inHand = Items.diamond_sword;
		}

	}

	@Override
	public void onLivingUpdate() {
		this.ceased = true;

		if (this.hasAppeared) {
			super.onLivingUpdate();
		}

		final int i = (int) this.posX;
		final int j = (int) this.posY;
		final int k = (int) this.posZ;

		if (!this.hasAppeared) {
			final EntityPlayer toTeleport = this.worldObj.getClosestPlayerToEntity(this, 3000D);

			if (toTeleport == null) { return; }

			if (HerobrineMod.teleport == 1 && (int) (Math.random() * 70 + 1) == 9) {
				toTeleport.setPosition(this.posX + 1, this.posY, this.posZ);
				this.freezePlayer = true;
			}
			else {
				this.setPosition(toTeleport.posX + 1, toTeleport.posY, toTeleport.posZ);
			}

			super.onLivingUpdate();
			this.hasAppeared = true;
			final int s = (int) (Math.random() * 20 + 1);

			if (s == 10) {
				this.worldObj.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "mob.ghast.scream", 35F, 0.8F);// scream
			}
			
			this.worldObj.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "ambient.cave.cave", 35F, 0.8F);// scream
			this.lifespawn = 30 + (int) (Math.random() * 20 + 1);

			// Freeze the player?
			if ((int) (Math.random() * 11 + 1) == 9) {

				final HerobrineStalker x = new HerobrineStalker();

				if (this.worldObj.isRemote) {

					final EntityPlayerSP player = (EntityPlayerSP) toTeleport;
					x.freeze(player, this);
				}
				else {
					toTeleport.motionX = 0;
					toTeleport.motionY = 0;
					toTeleport.motionZ = 0;

				}

				this.freezePlayer = true;
				this.worldObj.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "mob.ghast.scream", 35F, 0.8F);
				final int f = (int) (Math.random() * 25 + 1);

				if (f == 15 && HerobrineMod.dropTorch == 1)// Dropp all Torches?
				{
					final HerobrineStalker st = new HerobrineStalker();
					st.dropTorches(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
				}
			}
		}
		else {
			this.livingTicker++;

			if (15 < this.livingTicker) {
				this.ceased = true;
			}

		}

		final EntityPlayer stalk = this.worldObj.getClosestPlayerToEntity(this, 300D);
		if (stalk != null) {
			this.faceEntity(stalk, 30F, 30F);    // face the player
		}

		if (this.freezePlayer) {
			if (this.worldObj.isRemote) {
				final EntityPlayerSP player = (EntityPlayerSP) stalk;

				final HerobrineStalker x = new HerobrineStalker();
				x.freeze(player, this);
			}
			else {
				stalk.motionX = 0;
				stalk.motionY = 0;
				stalk.motionZ = 0;
			}
		}

		if (this.lifespawn < this.livingTicker) {
			if (!this.worldObj.isRemote && (int) (Math.random() * 15 + 1) == 10 && HerobrineMod.attac == 1) {
				this.worldObj.spawnEntityInWorld(new EntityFighter(this.worldObj, (float) stalk.posX + 0.5F,
						(float) stalk.posY + 0.0F, (float) stalk.posZ + 0.5F, false));
				this.setDead();
			}

			this.Attack();
			this.setDead();
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void Attack() {
		final HerobrineStalker st = new HerobrineStalker();
		st.blastGlowe(this, this.worldObj);
	}

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		this.setDead();

		if ((int) (Math.random() * 10 + 1) == 5) {
			final HerobrineStalker thrower = new HerobrineStalker();
			thrower.throwplayer2(this.worldObj, this);
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}
}
