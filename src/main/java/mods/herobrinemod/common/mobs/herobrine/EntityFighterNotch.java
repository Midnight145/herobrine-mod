package mods.herobrinemod.common.mobs.herobrine;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityFighterNotch extends EntityHerobrineBase {

    public int wait;
    int tick = 400;

    public EntityFighterNotch(World world, double x, double y, double z) {
        super(world);
        this.wait = 0;
        this.setPosition(x, y + this.yOffset, z);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.worldObj.isRemote) {
            return;
        }
        if (this.tick > 400) {
            this.tick = 0;

            EntityFighter fighter = (EntityFighter) this.findPlayerToAttack();
            if (fighter != null) {
                super.setAttackTarget(fighter);
            } else {
                this.wait++;
                if (this.wait >= 400) {
                    this.setDead();
                    this.part();
                }
            }
        } else {
            this.tick++;
        }
    }

    @Override
    protected Entity findPlayerToAttack() {
        final int range = 100;
        final List<EntityFighter> list_ = this.worldObj.getEntitiesWithinAABB(
            EntityFighter.class,
            AxisAlignedBB.getBoundingBox(
                this.posX - range,
                this.posY - range,
                this.posZ - range,
                this.posX + range,
                this.posY + range,
                this.posZ + range));

        if (!list_.isEmpty()) {
            return list_.get(this.worldObj.rand.nextInt(list_.size()));
        }
        this.wait++;

        if (this.wait >= 400) {
            this.setDead();
            this.part();
        }

        return null;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(1.0f);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(10.0f);
    }

}
