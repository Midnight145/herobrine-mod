package mods.HerobrineMod.common.mobs;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityZombie2 extends EntityZombie {

	public EntityZombie2(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y + this.yOffset, z);
		
	}

	public EntityZombie2(World world) {
		super(world);

		this.isImmuneToFire = true;

	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.15000011920929D);
	}

	protected int func_40119_ar() { return 2; }

	public EnumCreatureAttribute func_40124_t() { return EnumCreatureAttribute.UNDEAD; }
}
