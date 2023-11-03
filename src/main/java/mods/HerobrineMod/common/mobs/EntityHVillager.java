package mods.HerobrineMod.common.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class EntityHVillager extends EntityVillager {
	
	private int ticker = 0;
	private int lifespawn = 0;
	
	public EntityHVillager(World world, double x, double y, double z) {
		super(world);
		
		this.lifespawn = 700 + (int) (Math.random() * 500 + 1);
		this.setPosition(x, y + this.yOffset, z);
	}
	
	public EntityHVillager(World world) {
		super(world);
		this.lifespawn = 700 + (int) (Math.random() * 500 + 1);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.ticker++;
		if (this.lifespawn < this.ticker) {
			this.setDead();
		}
	}
	
	@Override
	protected Entity findPlayerToAttack() { return this.worldObj.getClosestPlayerToEntity(this, 30D); }
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(.5D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
	}
	
	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		
		if (this.rand.nextInt(100) == 0) {
			this.entityToAttack = null;
		}
		else {
			
			super.attackEntity(par1Entity, par2);
		}
	}
	
	@Override
	public EntityHVillager createChild(EntityAgeable par1EntityAgeable) { return null; }
	
}
