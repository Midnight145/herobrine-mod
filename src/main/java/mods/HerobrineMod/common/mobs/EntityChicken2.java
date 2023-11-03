package mods.HerobrineMod.common.mobs;

import mods.HerobrineMod.common.HerobrineMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityChicken2 extends EntityMob implements IMob {

	public EntityChicken2(World p_i1738_1_) {
		super(p_i1738_1_);
		if (HerobrineMod.mini_mode == 1) {
			this.setSize(0.15F, 0.35F);
		}
		else {
			this.setSize(0.3F, 0.7F);
		}

	}
	
	public EntityChicken2(World world, double x, double d, double z) { // TODO Auto-generated constructor stub }
		this(world);
		this.setPosition(x, d, z);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		Entity entity = this.findPlayerToAttack();
		if (entity == null) { return; }
		EntityPlayer player = (EntityPlayer) entity;
		if (player.getHealth() < 5.0) {
			this.setDead();
		}
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}
	
}
