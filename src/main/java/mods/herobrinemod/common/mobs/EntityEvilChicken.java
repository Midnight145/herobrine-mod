package mods.herobrinemod.common.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityEvilChicken extends EntityEvilMobBase {

    public EntityEvilChicken(World world) {
        super(world, .3f, .7f);
    }

    public EntityEvilChicken(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x, y, z);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Entity entity = this.findPlayerToAttack();
        if (entity == null) {
            return;
        }
        EntityPlayer player = (EntityPlayer) entity;
        if (player.getHealth() < 5.0) {
            this.setDead();
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(.5D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(.800000011920929D);
    }

}
