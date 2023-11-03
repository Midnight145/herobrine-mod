package mods.HerobrineMod.common.mobs.herobrine;

import mods.HerobrineMod.common.HerobrineMod;
import mods.HerobrineMod.common.mobs.EntityChicken2;
import mods.HerobrineMod.common.mobs.EntityCow2;
import mods.HerobrineMod.common.mobs.EntityNorris;
import mods.HerobrineMod.common.mobs.EntityPig2;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityDummySpawner extends EntityAnimal {

	public EntityDummySpawner(World par1World) {
		super(par1World);

		System.out.println("Herobrine Spawn");
		System.out.println("canSpawn: " + HerobrineMod.canSpawn);
		System.out.println(this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL);

		if (!HerobrineMod.debug
				&& (!HerobrineMod.canSpawn || this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)) {
			System.out.println("Killing...");
			this.setDead();
		}
	}

	public EntityDummySpawner(World worldClient, double x, double y, double z) {
		this(worldClient);
		this.setPosition(x, y, z);
	}

	@Override
	public void onLivingUpdate() {
		System.out.println("In onLivingUpdate");
		if (this.worldObj.isRemote) { return; }
		System.out.println("Attempting spawn...");

		if (HerobrineMod.extremeRare == 1 && (int) (Math.random() * 80 + 1) != 10) {
			this.setDead();
			return;
		}

		if ((int) (Math.random() * 130 + 1) == 10) {
			this.worldObj.spawnEntityInWorld(
					new EntityHerobrine(this.worldObj, (float) this.posX, (float) this.posY, (float) this.posZ + 3));
			System.out.println("Spawned EntityHerobrine");

		}

		if ((int) (Math.random() * 130 + 1) == 10) {
			this.worldObj.spawnEntityInWorld(
					new EntityNorris(this.worldObj, (float) this.posX, (float) this.posY, (float) this.posZ + 3));
			System.out.println("Spawned EntityNorris");

		}

		if ((int) (Math.random() * 2 + 1) == 1) {

			this.worldObj.spawnEntityInWorld(new EntityStalker(this.worldObj, (float) this.posX + 0.5F,
					(float) this.posY + 0.0F, (float) this.posZ + 0.5F));
			System.out.println("Spawned EntityStalker");

		}
		else {

			this.worldObj.spawnEntityInWorld(new EntityBuilder(this.worldObj, (float) this.posX + 0.5F,
					(float) this.posY + 0.0F, (float) this.posZ + 0.5F));
			System.out.println("Spawned EntityBuilder");
		}

		if ((int) (Math.random() * 100 + 1) == 5) {
			this.worldObj.spawnEntityInWorld(new EntityPig2(this.worldObj, (float) this.posX + 0.5F,
					(float) this.posY + 0.0F, (float) this.posZ + 0.5F));
			System.out.println("Spawned EntityPig2");
		}

		if ((int) (Math.random() * 100 + 1) == 6) {
			this.worldObj.spawnEntityInWorld(new EntityCow2(this.worldObj, (float) this.posX + 0.5F,
					(float) this.posY + 0.0F, (float) this.posZ + 0.5F));
			System.out.println("Spawned EntityCow2");
		}
		if ((int) (Math.random() * 100 + 1) == 3) {
			this.worldObj.spawnEntityInWorld(new EntityChicken2(this.worldObj, (float) this.posX + 0.5F,
					(float) this.posY + 0.0F, (float) this.posZ + 0.5F));
			System.out.println("Spawned EntityChicken2");
		}
		this.isDead = true;
		this.setDead();
		super.onLivingUpdate();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
	}

	@Override
	public boolean getCanSpawnHere() { return HerobrineMod.canSpawn; }

	public EntityDummySpawner spawnBabyAnimal(EntityAgeable par1EntityAgeable) { return null; }

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack) { return false; }

	@Override
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable) { return null; }

}
