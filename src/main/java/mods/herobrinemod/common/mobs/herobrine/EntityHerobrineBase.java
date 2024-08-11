package mods.herobrinemod.common.mobs.herobrine;

import mods.herobrinemod.common.HerobrineMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityHerobrineBase extends EntityMob {
	public EntityHerobrineBase(World world) {
		super(world);
        if (!HerobrineMod.canSpawn) {
            this.setDead();
        }
        this.setSize(.6F, 1.8F);
	}

	@Override
	protected Entity findPlayerToAttack() { return this.worldObj.getClosestPlayerToEntity(this, 16D); }

	@Override
	protected void attackEntity(Entity entity, float par2) {
		if (this.rand.nextInt(100) == 0) {
			this.entityToAttack = null;
		}
		else {
			super.attackEntity(entity, par2);
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.ARTHROPOD; }

	@Override
	public boolean isPotionApplicable(PotionEffect effect) {
		return effect.getPotionID() != Potion.poison.id && super.isPotionApplicable(effect);
	}

	@Override
	public boolean getCanSpawnHere() { return HerobrineMod.canSpawn; }

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

    @Override
    public void setDead() {
        this.part();
        this.isDead = true;
    }

}
