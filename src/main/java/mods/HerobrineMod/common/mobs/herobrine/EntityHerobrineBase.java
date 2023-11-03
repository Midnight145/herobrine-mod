package mods.HerobrineMod.common.mobs.herobrine;

import mods.HerobrineMod.common.HerobrineMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityHerobrineBase extends EntityAnimal {
	public EntityHerobrineBase(World par1World) {
		super(par1World);
		this.setSize(1.4F, 0.9F);
	}
	
	/**
	 * Finds the closest player within 16 blocks to attack, or null if this Entity
	 * isn't interested in attacking
	 * (Animals, Spiders at day, peaceful PigZombies).
	 */
	
	@Override
	protected Entity findPlayerToAttack() { return this.worldObj.getClosestPlayerToEntity(this, 16D); }
	
	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by
	 * each mob to define their attack.
	 */
	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		
		if (this.rand.nextInt(100) == 0) {
			this.entityToAttack = null;
		}
		else {
			
			super.attackEntity(par1Entity, par2);
		}
	}
	
	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	@Override
	public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.ARTHROPOD; }
	
	@Override
	public boolean isPotionApplicable(PotionEffect par1PotionEffect) {
		return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
	}
	
	@Override
	public boolean getCanSpawnHere() { return HerobrineMod.canSpawn; }
	
	@Override
	public EntityAgeable createChild(EntityAgeable paramEntityAgeable) { return null; }
}
