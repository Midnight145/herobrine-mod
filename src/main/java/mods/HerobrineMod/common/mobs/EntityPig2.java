
package mods.HerobrineMod.common.mobs;

import mods.HerobrineMod.common.HerobrineMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityPig2 extends EntityPig {
	private int ticksAlive = 0;

	public EntityPig2(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y + this.yOffset, z);
	}

	public EntityPig2(World world) {
		super(world);
		if (HerobrineMod.mini_mode == 1) {
			this.setSize(0.45F, 0.45F);
		}
		else {
			this.setSize(0.9F, 0.9F);
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (this.ticksAlive % 40 == 0) {
			final Entity entity = this.findPlayerToAttack();
			if (entity == null) { return; }
			super.setAttackTarget((EntityLivingBase) entity);
		}
		this.ticksAlive++;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
	}
	
	@Override
	protected Entity findPlayerToAttack() { return this.worldObj.getClosestPlayerToEntity(this, 16D); }
	
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
	public void setDead() {
		this.isDead = true;

		if (this.worldObj.isRemote) { return; }

		if (HerobrineMod.placeTnt == 1) {

			final EntityTNTPrimed tnt = new EntityTNTPrimed(this.worldObj);
			this.worldObj.spawnEntityInWorld(tnt);
			tnt.setPosition(this.posX, this.posY, this.posZ);
		}
	}
	
	@Override
	public EntityPig createChild(EntityAgeable par1EntityAgeable) { return null; }

	@Override
	public boolean isBreedingItem(ItemStack stack) { return false; }
}
