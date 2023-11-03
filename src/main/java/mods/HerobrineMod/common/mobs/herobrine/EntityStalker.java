package mods.HerobrineMod.common.mobs.herobrine;

import mods.HerobrineMod.common.HerobrineMod;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityStalker extends EntityHerobrineBase {

	public Item inHand;
	protected boolean hasAppeared = false;
	protected boolean freezePlayer = false;
	protected boolean ceased = false;
	protected int livingTicker = 0;
	protected int lifespawn = 0;
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
	}

	public EntityStalker(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub

	}

	////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////
	public EntityStalker(World world, double d, double d1, double d2) {

		this(world);

		if (!HerobrineMod.canSpawn) // !worldObj.isRemote)
		{
			this.setDead();
		}
		System.out.println("Stalker");

		this.setPosition(d, d1 + this.yOffset, d2);

		if ((int) (Math.random() * 30 + 1) == 10) {
			if ((int) Math.random() + 1 == 0) {
				this.inHand = Items.diamond_sword;
			}
			else {
				this.inHand = Items.golden_sword;
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	protected void part() {
		for (int i = 0; i < 20; i++) {
			final double d = this.rand.nextGaussian() * 0.02D;
			final double d1 = this.rand.nextGaussian() * 0.02D;
			final double d2 = this.rand.nextGaussian() * 0.02D;
			final double d3 = 10D;
			this.worldObj.spawnParticle("largesmoke",
					this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
					this.posY + this.rand.nextFloat() * this.height - d1 * d3,
					this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3, d, d1, d2);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDead() {
		this.part();
		this.isDead = true;
	}

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void onLivingUpdate() {

		final EntityPlayer stalk = this.worldObj.getClosestPlayerToEntity(this, 300D);

		if (stalk != null) {
			this.faceEntity(stalk, 30F, 30F);    // face the player
		}

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

			if (s == 5) {
				final HerobrineStalker d = new HerobrineStalker();
				d.RingOfFireH((int) (Math.random() * 5 + 3), this.worldObj, this);
			}

			if ((int) (Math.random() * 30 + 1) == 15) {
				final HerobrineStalker d = new HerobrineStalker();
				d.Virus(this, this.worldObj);
			}

			if (s == 10) {
				this.worldObj.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "mob.ghast.scream", 35F, 0.8F);// scream
			}
			
			this.worldObj.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "ambient.cave.cave", 35F, 0.8F);// scream
			this.lifespawn = 30 + (int) (Math.random() * 20 + 1);

			// Freeze the player?
			if ((int) (Math.random() * 20 + 1) == 9) {

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

			}
			final int f = (int) (Math.random() * 25 + 1);

			if (f == 15 && HerobrineMod.dropTorch == 1)// Dropp all Torches?
			{
				final HerobrineStalker st = new HerobrineStalker();
				st.dropTorches(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
			}
		}
		else {
			this.livingTicker++;

			if (15 < this.livingTicker) {
				this.ceased = true;
			}

		}

		if (this.freezePlayer) {
			if (this.worldObj.isRemote) {

				final EntityPlayerSP player = (EntityPlayerSP) stalk;

				final HerobrineStalker x = new HerobrineStalker();
				if (stalk != null) {
					x.freeze(player, this);
				}
			}
			else {
				if (stalk != null) {
					stalk.motionX = 0;
					stalk.motionY = 0;
					stalk.motionZ = 0;
				}
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
	public void Attack() {
		final int rnd = (int) (Math.random() * 30 + 1);
		final HerobrineStalker st = new HerobrineStalker();

		switch (rnd) {
			case 1:
				if (!this.worldObj.isRemote) {
					st.Zombie(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ, this);
				}

				break;

			case 2:
				if (!this.worldObj.isRemote) {
					st.Chicken(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ, this);
				}

				break;

			case 3:
				st.blastGlowe(this, this.worldObj);
				break;

			case 4:
				st.Lightning(this.worldObj, this);
				break;

			case 5:
				st.dropItems(this.worldObj, this);
				break;

			case 6:
				st.onFire(this.worldObj, this);
				break;

			case 7:
				st.throwplayer(this.worldObj, this);
				break;

			case 8:
				st.onFire(this.worldObj, this);
				break;

			case 9:
				if (!this.worldObj.isRemote) {
					st.placeWater(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
				}

				break;

			case 10:
				if (!this.worldObj.isRemote) {
					st.placeLava(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
				}
				break;

			case 12:
				if (!this.worldObj.isRemote) {
					st.breakglass(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
				}

				break;

			case 13:
				if (!this.worldObj.isRemote) {
					st.HVillager(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ, this);
				}
				break;
			case 14:
				if (!this.worldObj.isRemote) {
					st.RingOfFire((int) (Math.random() * 12 + 5), this.worldObj, this);
				}
				break;

			case 15:
				if (!this.worldObj.isRemote) {
					st.RingOfEnemies((int) (Math.random() * 2 + 5), this.worldObj, this, 0);
				}
				break;
			case 16:
				if (!this.worldObj.isRemote) {
					st.RingOfEnemies((int) (Math.random() * 2 + 4), this.worldObj, this, 1);
				}
				break;
			case 17:
				if (!this.worldObj.isRemote) {
					st.RingOfEnemies((int) (Math.random() * 2 + 4), this.worldObj, this, 2);
				}
				break;

			case 18:
				if (!this.worldObj.isRemote) {
					st.Virus(this, this.worldObj);
				}
				break;
			case 19:
				if (!this.worldObj.isRemote) {
					st.potion(this.worldObj, this);
				}
				break;

		}
		if ((int) (Math.random() * 250 + 1) == 10) {
			st.Boss(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ, this);
		}

	}

	////////////////////////////////////////////////////////////////////////////////////
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {

		if ((int) (Math.random() * 5 + 1) == 2) {
			final HerobrineStalker thrower = new HerobrineStalker();
			thrower.throwplayer2(this.worldObj, this);
		}
		else {
			// setDead();
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected boolean isMovementCeased() { return this.ceased; }

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected String getHurtSound() { return ""; }

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ItemStack getHeldItem() {
		if (this.inHand != null) { return new ItemStack(this.inHand); }

		return null;
	}

}
