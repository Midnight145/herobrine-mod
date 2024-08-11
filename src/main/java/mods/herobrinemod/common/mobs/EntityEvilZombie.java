package mods.herobrinemod.common.mobs;

import mods.herobrinemod.common.Config;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityEvilZombie extends EntityZombie {

    public EntityEvilZombie(World world) {
        super(world);
        if (!Config.spawnEvilMobs) {
            this.setDead();
        }
        this.isImmuneToFire = true;
    }

	public EntityEvilZombie(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y + this.yOffset, z);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.15D);
	}

    @Override
    public int getTotalArmorValue() { return 2; }
}
