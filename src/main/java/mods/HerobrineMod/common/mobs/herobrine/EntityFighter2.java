package mods.HerobrineMod.common.mobs.herobrine;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityMob, World, MathHelper, Item

public class EntityFighter2 extends EntityHerobrineBase {

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0f);
	}

	public EntityFighter2(World world) {
		super(world);

		this.wait = 0;
	}

	public int func_82193_c(Entity par1Entity) { return 10; }

	public EntityFighter2(World worldObj, double x, double y, double z) {
		super(worldObj);
		this.setPosition(x, y + this.yOffset, z);

		this.wait = 0;
		this.st = false;

	}

	int tick = 400;

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.worldObj.isRemote) { return; }

		if (!this.st) {

			this.st = true;
		}

		final int range = 20;

		if (this.tick > 400) {
			this.tick = 0;
			final List list_ = this.worldObj.getEntitiesWithinAABB(EntityFighter.class,
					AxisAlignedBB.getBoundingBox(this.posX - range, this.posY - range, this.posZ - range,
							this.posX + range, this.posY + range, this.posZ + range));

			if (!list_.isEmpty()) {
				super.setAttackTarget((EntityLiving) list_.get(this.worldObj.rand.nextInt(list_.size())));
			}
			else {
				this.wait++;

				if (400 < this.wait) {
					this.setDead();
					this.part();
				}
			}
		}
		else {
			this.tick++;
		}
	}

	@Override
	protected Entity findPlayerToAttack() {
		final int range = 100;
		final List list_ = this.worldObj.getEntitiesWithinAABB(EntityFighter.class,
				AxisAlignedBB.getBoundingBox(this.posX - range, this.posY - range, this.posZ - range, this.posX + range,
						this.posY + range, this.posZ + range));

		if (!list_.isEmpty()) { return (EntityLiving) list_.get(this.worldObj.rand.nextInt(list_.size())); }
		this.wait++;

		if (400 < this.wait) {
			this.setDead();
			this.part();
		}

		return null;
	}

	@Override
	protected boolean isMovementCeased() { return false; }

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

	public int wait;
	private boolean st;

}
