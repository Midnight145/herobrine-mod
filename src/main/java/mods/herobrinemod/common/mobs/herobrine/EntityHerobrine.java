package mods.herobrinemod.common.mobs.herobrine;

import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.world.World;

public class EntityHerobrine extends EntityGhast {
	public EntityHerobrine(World world) {
		super(world);
		this.setSize(1.4F, 0.9F);
	}

	public EntityHerobrine(World world, float x, float y, float z) {
		this(world);
		this.setPosition(x, y, z);
	}
}
