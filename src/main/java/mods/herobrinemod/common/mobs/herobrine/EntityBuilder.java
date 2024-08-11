package mods.herobrinemod.common.mobs.herobrine;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;

import mods.herobrinemod.common.Config;
import mods.herobrinemod.common.HerobrineMod;

@SuppressWarnings("SpellCheckingInspection")
public class EntityBuilder extends EntityHerobrineBase {

    public Item inHand = Items.golden_pickaxe;
    public int lifespan;
    public int livingTicker = 0;
    public int startAction;
    public int endTicker = 0;
    public boolean hasBeenSeen = false;
    public boolean shouldCutLeaves = false;
    public boolean shouldPlaceGlass = false;
    public boolean shouldPlaceTrap = false;
    public boolean buildingLoaded = false;
    public boolean shouldPlaceSign = false;
    public boolean shouldPlaceRedstone = false;

    public static String[] phrases = { "pmc sucks.", "E?", "u mad bro?", "Am I a ghost?", "Arrow in the knee!", "E",
        "Get lost!", "Chuck", "GameChap", "Berty", "EEEEEEEEEEE!", "I like you", "May I kill you?", "Look behind you!",
        "1+1", "Gold apple", "I rock!", "Watch your back!", "I was here", "Herobrine", "I'll get you!", "Find me",
        "Hi!", "Notch", "Buy Minecraft!", "Get me?", "Where are you?", "Chuck Norris!", "Watch out!", "I'm your shadow",
        "It's me Herobrine!", "Im a griefer", "Where is notch?", "Guess who was here", "Keep out", "Hell",
        "Im not dead!", "I'm alive!", "True", "1.1.0", "ASL?", "---" };

    public EntityBuilder(World world, double x, double y, double z) {
        super(world);

        this.lifespan = 700 + world.rand.nextInt(500 + 1);
        this.startAction = 100 + world.rand.nextInt(300 + 1);

        this.setPosition(x, y + this.yOffset, z);

        if (world.rand.nextBoolean()) {
            this.inHand = Items.diamond_pickaxe;
            this.shouldPlaceTrap = true;
        }

        if (world.rand.nextInt(10 + 1) == 5) {
            this.shouldPlaceGlass = true;
        } else if (world.rand.nextInt(5 + 1) == 2) {
            this.shouldPlaceSign = true;
        } else if (world.rand.nextInt(20 + 1) == 2) {
            this.shouldPlaceRedstone = true;
        } else if (world.rand.nextInt(20 + 1) == 2) {
            this.shouldCutLeaves = true;
        }

    }

    @Override
    public void onLivingUpdate() {

        super.onLivingUpdate();
        if (this.worldObj.isRemote) {
            return;
        }

        final int x = (int) this.posX;
        final int y = (int) this.posY;
        final int z = (int) this.posZ;
        final EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 30D);

        if (entityplayer != null && this.canEntityBeSeen(entityplayer) && !this.hasBeenSeen && !Config.debug) {
            this.hasBeenSeen = true;
            this.worldObj.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "mob.ghast.scream", 35F, 0.8F);
        }

        if (this.livingTicker > 200 && this.shouldPlaceSign) {
            this.placeSign();
        }

        if (this.shouldPlaceTrap && this.livingTicker > 400) {
            final int rnd = this.worldObj.rand.nextInt(70 + 1);
            if (rnd == 10 && !this.buildingLoaded && Config.placeTnt) {
                this.worldObj.setBlock(x, y, z, Blocks.tnt, 0, 3);

                this.worldObj.setBlock(x + 1, y, z, Blocks.tnt, 0, 3);
                this.worldObj.setBlock(x + 2, y, z, Blocks.tnt, 0, 3);
                this.worldObj.setBlock(x + 1, y + 1, z, Blocks.fire, 0, 3);
                this.setDead();
            }
        } else {
            final int rnd = this.worldObj.rand.nextInt(50 + 1);
            if (rnd == 10 && !this.buildingLoaded && Config.placeFire && this.livingTicker > 400) {
                for (int i = 0; i < 5 + 1; i++) {
                    this.worldObj.setBlock(x + i, y, z, Blocks.fire, 0, 3);
                }
                this.setDead();
            }
        }
        if (this.startAction <= this.livingTicker) {

            final boolean built = this.build();
            if (built) {
                this.setDead();
            }
        }

        if (this.lifespan <= this.livingTicker) {
            this.setDead();
        }

        if (this.hasBeenSeen) {
            this.endTicker++;
            if (this.endTicker > 100) {
                this.setDead();
            }
        }

        final int rnd = this.worldObj.rand.nextInt(50 + 1);
        if (rnd < 7 && this.shouldCutLeaves) {
            this.cutLeaves();
        }

        if (rnd < 10 && Config.placeGlass && this.shouldPlaceGlass) {

            this.worldObj.setBlock(x, y - 1, z, Blocks.glass, 0, 3);
        }
        if (rnd < 5 && this.shouldPlaceRedstone
            && !this.shouldPlaceGlass
            && this.worldObj.getBlock(x, y - 1, z) != Blocks.glass) {
            this.worldObj.setBlockMetadataWithNotify(x, y, z, Block.getIdFromBlock(Blocks.redstone_wire), 0);
        }
        this.livingTicker++;
    }

    public void placeSign() {

        int direction = this.worldObj.rand.nextInt(9 + 1) - 1;

        final Block id = this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ);
        if (id == null) {
            this.worldObj
                .setBlock((int) this.posX, (int) this.posY, (int) this.posZ, Blocks.standing_sign, direction, 3);
            final TileEntitySign tileentitysign = (TileEntitySign) this.worldObj
                .getTileEntity((int) this.posX, (int) this.posY, (int) this.posZ);
            int txt = worldObj.rand.nextInt(phrases.length);
            tileentitysign.signText[0] = phrases[txt];
        }

        this.shouldPlaceSign = false;

    }

    public boolean build() {
        HerobrineBuilder builder = new HerobrineBuilder();
        if (Config.placeTraps && this.shouldPlaceTrap) {
            if (!this.buildingLoaded) {
                final int trap = worldObj.rand.nextInt(HerobrineMod.trapCount + 1);
                final String file = trap + ".dat";
                builder.load(file, 0);
                if (builder.tryToPlace(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ)) {
                    return true;
                }

                this.buildingLoaded = true;

            } else return builder.tryToPlace(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
        } else if (Config.placeBuildings && !this.buildingLoaded) {
            final int building = worldObj.rand.nextInt(HerobrineMod.buildingCount + 1);
            final String file = building + ".dat";
            // Integer(building)
            builder.load(file, 1);
            if (builder.tryToPlace(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ)) {
                return true;
            }

            this.buildingLoaded = true;

        } else return builder.tryToPlace(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
        return false;
    }

    @Override
    public boolean getCanSpawnHere() {
        return true;
    }

    @Override
    public ItemStack getHeldItem() {
        return this.inHand == null ? null : new ItemStack(this.inHand);
    }

    public void cutLeaves() {
        int x = (int) this.posX;
        int y = (int) this.posY;
        int z = (int) this.posZ - 2;
        World world = this.worldObj;

        for (int dx = -20; dx < 20; dx++) {
            for (int dy = -10; dy < 10; dy++) {
                for (int dz = -20; dz < 20; dz++) {
                    if (world.getBlock(x + dx + 1, y + dy, z + dz + 1) == Blocks.leaves) {
                        world.setBlock(x + dx + 1, y + dy, z + dz + 1, Blocks.air, 0, 3);
                    }
                }
            }
        }
    }
}
