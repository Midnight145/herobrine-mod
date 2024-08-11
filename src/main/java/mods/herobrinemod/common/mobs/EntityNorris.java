package mods.herobrinemod.common.mobs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import mods.herobrinemod.common.mobs.herobrine.EntityStalker;

public class EntityNorris extends EntityStalker {

    public EntityNorris(World world, double x, double y, double z) {
        super(world, x, y, z);
        this.isHerobrine = false;
        this.attackChance = 5;
    }

    @Override
    public void Attack() {
        final EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 3000D);
        this.stalker.roundhouseKick(player);
    }
}
