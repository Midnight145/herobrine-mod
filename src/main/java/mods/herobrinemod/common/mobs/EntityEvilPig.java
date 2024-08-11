
package mods.herobrinemod.common.mobs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

import mods.herobrinemod.common.Config;

public class EntityEvilPig extends EntityEvilMobBase {

    public EntityEvilPig(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x, y + this.yOffset, z);
    }

    public EntityEvilPig(World world) {
        super(world, .9f, .9f);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(0.9);
    }

    @Override
    public void setDead() {
        super.setDead();
        if (this.worldObj.isRemote) {
            return;
        }

        if (Config.placeTnt) {
            final EntityTNTPrimed tnt = new EntityTNTPrimed(this.worldObj);
            this.worldObj.spawnEntityInWorld(tnt);
            tnt.setPosition(this.posX, this.posY, this.posZ);
        }
    }
}
