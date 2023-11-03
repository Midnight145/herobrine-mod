package mods.HerobrineMod.common.mobs.herobrine;

import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.world.World;

public class EntityHerobrine extends EntityGhast {
	public EntityHerobrine(World worldObj) {
		super(worldObj);
		this.setSize(1.4F, 0.9F);
	}

	public EntityHerobrine(World worldObj, float posX, float posY, float posZ) {
		this(worldObj);
		this.setPosition(posX, posY, posZ);
	}
}
