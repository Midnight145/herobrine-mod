
package mods.HerobrineMod.common.mobs;

import mods.HerobrineMod.common.HerobrineMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCow2 extends EntityCow {
	
	private int ticksAlive;
	
	public EntityCow2(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y + this.yOffset, z);
	}
	
	public EntityCow2(World world) {
		super(world);
		if (HerobrineMod.mini_mode == 1) {
			this.setSize(0.45F, 0.65F);
		}
		else {
			this.setSize(0.9F, 1.3F);
		}
		
		if (HerobrineMod.cowBrine == 0) {
			this.setDead();
		}
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 15D);
		if (player != null) {
			this.faceEntity(player, 30f, 30f);
		}
		if (this.ticksAlive % 40 == 0) {
			final Entity entity = this.findPlayerToAttack();
			if (entity == null) { return; }
			super.setAttackTarget((EntityLivingBase) entity);
		}
		this.ticksAlive++;
	}
	
	@Override
	public void setDead() {
		this.isDead = true;
		if (!this.worldObj.isRemote) { return; }
		
		if (HerobrineMod.cowBrine == 1 && HerobrineMod.placeLava == 1) {
			
			this.worldObj.setBlock((int) this.posX, (int) this.posY, (int) this.posZ, Blocks.lava, 0, 0);
			this.worldObj.setBlock((int) this.posX, (int) this.posY + 1, (int) this.posZ, Blocks.lava, 0, 0);
			
		}
	}
	
	@Override
	protected Entity findPlayerToAttack() {
		return this.worldObj.getClosestPlayerToEntity(this, 16D);
		
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
	public boolean isBreedingItem(ItemStack par1ItemStack) { return false; }
	
	@Override
	public EntityCow2 createChild(EntityAgeable par1EntityAgeable) { return null; }
	
}
