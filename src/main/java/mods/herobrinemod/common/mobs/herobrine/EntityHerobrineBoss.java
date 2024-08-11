package mods.herobrinemod.common.mobs.herobrine;

import net.minecraft.entity.boss.EntityWither;
import net.minecraft.world.World;

public class EntityHerobrineBoss extends EntityWither {

    public EntityHerobrineBoss(World world, float x, float y, float z) {
        super(world);
        this.setPosition(x, y, z);
    }
}
