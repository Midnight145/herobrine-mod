package mods.herobrinemod.common.mobs;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

import mods.herobrinemod.common.Config;

public class EntityEvilMobBase extends EntityMob implements IMob {

    public EntityEvilMobBase(World world, float width, float height) {
        super(world);
        if (!Config.spawnEvilMobs) {
            this.setDead();
        }
        if (Config.mini_mode) {
            this.setSize(width / 2, height / 2);
        } else {
            this.setSize(width, height);
        }
    }
}
