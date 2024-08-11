package mods.herobrinemod.common.mobs.herobrine;

import mods.herobrinemod.common.Config;
import mods.herobrinemod.common.HerobrineMod;
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
	protected int lifespan = 0;
    protected int freezeChance = 20;
    protected boolean isHerobrine = true;
    protected int attackChance = 5;
    protected HerobrineStalker stalker;

    @Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
	}

	public EntityStalker(World world, double x, double y, double z) {
		super(world);
		if (!HerobrineMod.canSpawn) {
			this.setDead();
		}
        this.setPosition(x, y + this.yOffset, z);
        stalker = new HerobrineStalker(this);

		if (world.rand.nextInt(30 + 1) == 10) {
            if (world.rand.nextInt(1 + 1) == 0) {
				this.inHand = Items.diamond_sword;
			}
			else {
				this.inHand = Items.golden_sword;
			}
		}
	}



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

			if (this.worldObj.rand.nextInt(70 + 1) == 9) {
				toTeleport.setPosition(this.posX + 1, this.posY, this.posZ);
				this.freezePlayer = true;
			}
			else {
				this.setPosition(toTeleport.posX + 1, toTeleport.posY, toTeleport.posZ);
			}

			super.onLivingUpdate();
			this.hasAppeared = true;
			final int s = this.worldObj.rand.nextInt(20 + 1);

            if (isHerobrine) {
                if (s == 5) {
                    this.stalker.spawnFireRingOnSelf(this.worldObj.rand.nextInt(8));
                }

                if (this.worldObj.rand.nextInt(30 + 1) == 15) {
                    this.stalker.spreadVirus();
                }
            }

			if (s == 10) {
				this.worldObj.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "mob.ghast.scream", 35F, 0.8F);// scream
			}

			this.worldObj.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "ambient.cave.cave", 35F, 0.8F);// scream
			this.lifespan = 30 + this.worldObj.rand.nextInt(20 + 1);

			// Freeze the player?
			if ((int) (Math.random() * freezeChance + 1) == 9) {
				this.freezePlayer = true;
				this.worldObj.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "mob.ghast.scream", 35F, 0.8F);

			}
			final int f = this.worldObj.rand.nextInt(25 + 1);

			if (f == 15 && Config.dropTorch)
			{

                this.stalker.dropTorches();
			}
		}
		else {
			this.livingTicker++;

			if (15 < this.livingTicker) {
				this.ceased = true;
			}

		}

		if (this.freezePlayer) {
            if (stalk != null) {
                this.stalker.freeze(stalk);
            }
		}

		if (this.lifespan < this.livingTicker) {

			if (stalk != null && !this.worldObj.isRemote && this.worldObj.rand.nextInt(15 + 1) == 15) {
				this.worldObj.spawnEntityInWorld(new EntityFighter(this.worldObj, (float) stalk.posX + 0.5F,
						(float) stalk.posY + 0.0F, (float) stalk.posZ + 0.5F));
				this.setDead();
			}

			this.Attack();
			this.setDead();
		}
	}


	public void Attack() {
		final int rnd = this.worldObj.rand.nextInt(30) + 1;
        final EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 3000D);
		switch (rnd) {
			case 1:
				if (!this.worldObj.isRemote) {
					this.stalker.spawnZombies();
				}

				break;

			case 2:
				if (!this.worldObj.isRemote) {
					this.stalker.spawnChickens();
				}

				break;

			case 3:
				this.stalker.roundhouseKick(player);
				break;

			case 4:
				this.stalker.spawnLightning(player);
				break;

			case 5:
				this.stalker.dropItems(player);
				break;

			case 6, 8:
				this.stalker.setOnFire(player);
				break;

			case 7:
				this.stalker.throwPlayer(player);
				break;

            case 9:
				if (!this.worldObj.isRemote) {
					this.stalker.placeWater();
				}

				break;

			case 10:
				if (!this.worldObj.isRemote) {
					this.stalker.placeLava();
				}
				break;

			case 12:
				if (!this.worldObj.isRemote) {
					this.stalker.breakGlass();
				}

				break;

			case 13:
				if (!this.worldObj.isRemote) {
					this.stalker.spawnVillager();
				}
				break;
			case 14:
				if (!this.worldObj.isRemote) {
					this.stalker.spawnFireRingOnPlayer(player, rand.nextInt(12) + 5
                    );
				}
				break;

			case 15:
				if (!this.worldObj.isRemote) {
					this.stalker.RingOfEnemies(player, rand.nextInt(2) + 5, 0);
				}
				break;
			case 16:
				if (!this.worldObj.isRemote) {
					this.stalker.RingOfEnemies(player, rand.nextInt(2) + 4, 1);
				}
				break;
			case 17:
				if (!this.worldObj.isRemote) {
					this.stalker.RingOfEnemies(player, rand.nextInt(2) + 4, 2);
				}
				break;

			case 18:
				if (!this.worldObj.isRemote) {
					this.stalker.spreadVirus();
				}
				break;
			case 19:
				if (!this.worldObj.isRemote) {
					this.stalker.potion(player);
				}
				break;

		}
		if (this.worldObj.rand.nextInt(250 + 1) == 10) {
			this.stalker.spawnBoss();
		}

	}


	@SuppressWarnings("unused")
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {

		if (this.worldObj.rand.nextInt(attackChance + 1) == 0) {
            final EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 3000D);
			this.stalker.throwPlayer(player);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}


	@Override
	protected boolean isMovementCeased() { return this.ceased; }


	@Override
	protected String getHurtSound() { return ""; }


	@Override
	public ItemStack getHeldItem() {
		if (this.inHand != null) { return new ItemStack(this.inHand); }

		return null;
	}

}
