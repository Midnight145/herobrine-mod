package mods.herobrinemod.common.mobs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityEvilVillager extends EntityEvilMobBase {
	private int lifespan;

	public EntityEvilVillager(World world) {
		super(world, 0.6F, 1.8F);
		this.lifespan = 700 + world.rand.nextInt(500 + 1);
	}

	public EntityEvilVillager(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y + this.yOffset, z);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.lifespan <= 0) {
			this.setDead();
		}
		this.lifespan--;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(.5D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
	}
}
