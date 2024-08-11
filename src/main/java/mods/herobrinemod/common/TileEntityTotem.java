package mods.herobrinemod.common;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class TileEntityTotem extends TileEntity {

    private int texture = 0;

    public TileEntityTotem() {}

    @Override
    public void updateEntity() {
        if (this.worldObj.isRemote) {
            return;
        }
        final int x = this.xCoord;
        final int y = this.yCoord;
        final int z = this.zCoord;
        if (this.worldObj.getBlock(x, y + 2, z) == Blocks.fire
            && this.worldObj.getBlock(x, y + 1, z) == Blocks.netherrack
            && this.worldObj.getBlock(x, y - 1, z) == Blocks.gold_block
            && this.worldObj.getBlock(x, y - 2, z) == Blocks.gold_block) {
            if (HerobrineMod.canSpawn) {
                return;
            }
            if (!this.worldObj.isRemote) {
                MinecraftServer.getServer()
                    .getConfigurationManager()
                    .sendChatMsg(new ChatComponentText("You don't know what you did...."));
            }

            if (!HerobrineMod.canSpawn || this.texture == 0) {
                this.texture = 1;// Switch texture
                HerobrineMod.canSpawn = true;
                this.worldObj.setBlockMetadataWithNotify(x, y, z, 1, 3);
            }
            final EntityLightningBolt bolt = new EntityLightningBolt(this.worldObj, x, y + 2, z);

            this.worldObj.spawnEntityInWorld(bolt);
            if (!this.worldObj.isRemote) {

                this.worldObj.playSoundAtEntity(bolt, "ambient.weather.thunder", 10000F, 0.8F + 50 * 0.2F);
                this.worldObj.playSoundAtEntity(bolt, "random.explode", 2F, 0.5F + 100 * 0.2F);
            }
        } else if (HerobrineMod.canSpawn) {
            this.unset(this.worldObj, x, y, z);
        }
    }

    public void unset(World world, int i, int j, int k) {
        this.texture = 0;// chance texture
        HerobrineMod.canSpawn = false;// Can not spawn anymore
        world.setBlockMetadataWithNotify(i, j, k, 0, 3);
    }

}
