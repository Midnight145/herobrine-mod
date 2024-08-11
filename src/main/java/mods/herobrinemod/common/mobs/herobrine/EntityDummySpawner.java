package mods.herobrinemod.common.mobs.herobrine;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import mods.herobrinemod.common.Config;
import mods.herobrinemod.common.HerobrineMod;
import mods.herobrinemod.common.mobs.EntityEvilChicken;
import mods.herobrinemod.common.mobs.EntityEvilCow;
import mods.herobrinemod.common.mobs.EntityEvilPig;
import mods.herobrinemod.common.mobs.EntityNorris;

public class EntityDummySpawner extends EntityAnimal {

    public EntityDummySpawner(World world) {
        super(world);
        if (!Config.debug && (!HerobrineMod.canSpawn || this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)) {
            this.setDead();
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.worldObj.isRemote) {
            return;
        }
        int val;
        boolean didSomething = true;
        if (Config.extremeRare && this.worldObj.rand.nextInt(80 + 1) != 10) {
            this.setDead();
            return;
        }
        val = this.worldObj.rand.nextInt(100 + 1);
        switch (val) {
            case 1:
                this.worldObj.spawnEntityInWorld(
                    new EntityHerobrine(this.worldObj, (float) this.posX, (float) this.posY, (float) this.posZ + 3));
                break;
            case 2:
                this.worldObj.spawnEntityInWorld(
                    new EntityNorris(this.worldObj, (float) this.posX, (float) this.posY, (float) this.posZ + 3));
                break;
            case 3:
                this.worldObj.spawnEntityInWorld(
                    new EntityEvilChicken(
                        this.worldObj,
                        (float) this.posX + 0.5F,
                        (float) this.posY + 0.0F,
                        (float) this.posZ + 0.5F));
            case 4:
                this.worldObj.spawnEntityInWorld(
                    new EntityEvilPig(
                        this.worldObj,
                        (float) this.posX + 0.5F,
                        (float) this.posY + 0.0F,
                        (float) this.posZ + 0.5F));
                break;
            case 5:
                this.worldObj.spawnEntityInWorld(
                    new EntityEvilCow(
                        this.worldObj,
                        (float) this.posX + 0.5F,
                        (float) this.posY + 0.0F,
                        (float) this.posZ + 0.5F));
                break;
            default:
                didSomething = false;
        }

        if (this.worldObj.rand.nextBoolean()) {
            this.worldObj.spawnEntityInWorld(
                new EntityStalker(
                    this.worldObj,
                    (float) this.posX + 0.5F,
                    (float) this.posY + 0.0F,
                    (float) this.posZ + 0.5F));
        } else {
            this.worldObj.spawnEntityInWorld(
                new EntityBuilder(
                    this.worldObj,
                    (float) this.posX + 0.5F,
                    (float) this.posY + 0.0F,
                    (float) this.posZ + 0.5F));
        }

        if (didSomething) {
            this.isDead = true;
            this.setDead();

        }

    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(0.0D);
    }

    @Override
    public boolean getCanSpawnHere() {
        return HerobrineMod.canSpawn;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ignored) {
        return null;
    }

}
