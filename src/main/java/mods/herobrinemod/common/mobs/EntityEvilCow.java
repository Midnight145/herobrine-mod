
package mods.herobrinemod.common.mobs;

import mods.herobrinemod.common.Config;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityEvilCow extends EntityEvilMobBase {
	public EntityEvilCow(World world) {
		super(world, .9f, 1.3f);
	}

	public EntityEvilCow(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y + this.yOffset, z);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
	}

	@Override
	public void setDead() {
		super.setDead();
		if (!this.worldObj.isRemote) { return; }

		if (Config.spawnEvilMobs && Config.placeLava) {

			this.worldObj.setBlock((int) this.posX, (int) this.posY, (int) this.posZ, Blocks.lava, 0, 0);
			this.worldObj.setBlock((int) this.posX, (int) this.posY + 1, (int) this.posZ, Blocks.lava, 0, 0);
			this.worldObj.markBlockForUpdate((int) this.posX, (int) this.posY, (int) this.posZ);
			this.worldObj.markBlockForUpdate((int) this.posX, (int) this.posY + 1, (int) this.posZ);
		}
	}

}
