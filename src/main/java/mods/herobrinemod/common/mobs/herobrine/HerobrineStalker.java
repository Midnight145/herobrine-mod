package mods.herobrinemod.common.mobs.herobrine;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import mods.herobrinemod.common.Config;
import mods.herobrinemod.common.mobs.EntityEvilChicken;
import mods.herobrinemod.common.mobs.EntityEvilPig;
import mods.herobrinemod.common.mobs.EntityEvilVillager;
import mods.herobrinemod.common.mobs.EntityEvilZombie;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class HerobrineStalker {

    EntityStalker stalker;

	public HerobrineStalker(EntityStalker stalker) {
        this.stalker = stalker;
    }

	public void freeze(EntityPlayer player) {
		if (player == null) { return; }

		player.motionY = 0;
		player.motionX = 0;
		player.motionZ = 0;
        forceFaceEntity(player);
        if (player.worldObj.isRemote) {
            ((EntityPlayerSP) player).timeInPortal = 0.5F;
            ((EntityPlayerSP) player).prevTimeInPortal = 0.5F;
        }
	}

	private static float updateRotation(float current, float change) {
		float var4 = MathHelper.wrapAngleTo180_float(change - current);
        return (float) Math.min(50, Math.max(var4, -50.0)) + current;
	}

	private void forceFaceEntity(Entity player) {
		final double dx = this.stalker.posX - player.posX;
		final double dz = this.stalker.posZ - player.posZ;
		double dy;

        dy = this.stalker.posY + this.stalker.getEyeHeight() - (player.posY + player.getEyeHeight());

		final double d3 = MathHelper.sqrt_double(dx * dx + dz * dz);
		final float horiz_change = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - 90.0F;
		final float vert_change = (float) -(Math.atan2(dy, d3) * 180.0D / Math.PI);
		player.rotationPitch = updateRotation(player.rotationPitch, vert_change);
		player.rotationYaw = updateRotation(player.rotationYaw, horiz_change);
	}

	public void spreadVirus() {
		final double x = this.stalker.posX;
		final double y = this.stalker.posY;
		final double z = this.stalker.posZ;
        World world = this.stalker.worldObj;
		final List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class,
				AxisAlignedBB.getBoundingBox(x - 150, y - 150, z - 150, x + 150, y + 150, z + 150));

        for (EntityLivingBase entity : entities) {
            EntityLivingBase enemy;
            if (Config.mappings.containsKey(entity.getClass())) {
                try {
                    enemy = (EntityLivingBase) Config.mappings.get(entity.getClass()).newInstance(world);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    continue;
                }
                enemy.copyLocationAndAnglesFrom(entity);
                entity.setDead();
                world.spawnEntityInWorld(enemy);
            }
        }
	}

	public void roundhouseKick(EntityPlayer player) {
		if (Config.kick) {
            if (player == null) {
                return;
            }
			player.motionZ += 0.5f;
			player.motionX += 0.8;
			player.motionY += 0.5;

            //noinspection SpellCheckingInspection
            sendMessage(player, "kcik-esuohdnuoR");
		}
	}

	public void spawnLightning(EntityPlayer player) {
        if (player == null) { return; }
        World world = player.worldObj;
        sendRandomPhrase(player);
		world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX, player.posY + 2, player.posZ));
	}

    private static void sendRandomPhrase(EntityPlayer player) {
        int message = player.getEntityWorld().rand.nextInt(phrases.length);
        sendMessage(player, phrases[message]);
    }
    private static void sendHello(EntityPlayer player) {
        sendMessage(player, "Hi " + player.getDisplayName());
    }

    private static void sendMessage(EntityPlayer player, String message) {
        if (player.getEntityWorld().isRemote) { return; }
        player.addChatComponentMessage(new ChatComponentText(message));
    }
    private static void sendGlobalPhrase() {
        int message = MinecraftServer.getServer().getEntityWorld().rand.nextInt(phrases.length);
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(phrases[message]));
    }

	public void dropTorches() {
        int x = (int) this.stalker.posX;
        int y = (int) this.stalker.posY;
        int z = (int) this.stalker.posZ;
        World world = this.stalker.worldObj;
		z -= 2;

		for (int dx = -100; dx < 100; dx++) {
			for (int dy = -10; dy < 10; dy++) {
				for (int dz = -100; dz < 100; dz++) {
					if (world.getBlock(x + dx + 1, y + dy, z + dz + 1) == Blocks.torch) {
						Blocks.torch.dropBlockAsItem(world, x + dx + 1, y + dy, z + dz + 1, 0, 0);
					}
				}
			}
		}
	}



	public void breakGlass() {
        int x = (int) this.stalker.posX;
        int y = (int) this.stalker.posY;
        int z = (int) this.stalker.posZ - 2;
        World world = this.stalker.worldObj;

		for (int dx = -20; dx < 20; dx++) {
			for (int dy = -10; dy < 10; dy++) {
				for (int dz = -20; dz < 20; dz++) {
					if (world.getBlock(x + dx + 1, y + dy, z + dz + 1) == Blocks.glass
							&& world.rand.nextInt(4 + 1) == 3) {
						world.setBlock(x + dx + 1, y + dy, z + dz + 1, Blocks.air, 0, 3);
					}
				}
			}
		}
	}

	public void setOnFire(EntityPlayer player) {
        if (player == null) { return; }
        World world = player.worldObj;
        if (Config.setOnFire && world.rand.nextInt(7 + 1) == 2) {

            player.setFire(10);
			if (!world.isRemote) {
				sendRandomPhrase(player);
				sendHello(player);
			}
			player.inventory.addItemStackToInventory(new ItemStack(Items.bucket, 1));
		}
	}

	public void throwPlayer(EntityPlayer player) {
        if (player == null) { return; }
        player.motionY += 0.998888888F;
        sendRandomPhrase(player);
	}

	public void spawnBoss() {
        int x = (int) this.stalker.posX;
        int y = (int) this.stalker.posY;
        int z = (int) this.stalker.posZ;
        World world = this.stalker.worldObj;
		world.spawnEntityInWorld(new EntityHerobrineBoss(world, x + 0.5F, y + 1.0F, z + 0.5F));
	}

	public void spawnZombies() {
        int x = (int) this.stalker.posX;
        int y = (int) this.stalker.posY;
        int z = (int) this.stalker.posZ;
        World world = this.stalker.worldObj;
		world.spawnEntityInWorld(new EntityEvilZombie(world, x + 0.5F, y + 1.0F, z + 0.5F));
		world.spawnEntityInWorld(new EntityEvilZombie(world, x - 0.5F, y - 1.0F, z - 0.5F));
        sendGlobalPhrase();
	}

	public void spawnVillager() {
        int x = (int) this.stalker.posX;
        int y = (int) this.stalker.posY;
        int z = (int) this.stalker.posZ;
        World world = this.stalker.worldObj;
		world.spawnEntityInWorld(new EntityEvilVillager(world, x + 0.5F, y + 1.0F, z + 0.5F));
        sendGlobalPhrase();
	}

	public void spawnChickens() {
        int x = (int) this.stalker.posX;
        int y = (int) this.stalker.posY;
        int z = (int) this.stalker.posZ;
        World world = this.stalker.worldObj;

        for (int i = 0; i < 10; i++) {
            world.spawnEntityInWorld(new EntityEvilChicken(world, x + 0.5F * i, y + 1.0F, z + 0.5F * i));
        }

        if (world.rand.nextInt(7 + 1) == 2) {
            world.spawnEntityInWorld(new EntityEvilPig(world, x + 0.5F, y + 1.0F, z + 0.5F));
        }
        sendGlobalPhrase();
    }

	public void potion(EntityPlayer player) {
        if (player == null) { return; }
        World world = player.worldObj;

        int rnd = world.rand.nextInt(7);

		switch (rnd) {
			case 0:
				player.addPotionEffect(new PotionEffect(Potion.poison.id, 7 * 20, 0));
				break;
			case 1:
				player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 7 * 20, 0));
				break;
			case 2:
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 7 * 20, 0));
				break;
			case 3:
				player.addPotionEffect(new PotionEffect(Potion.blindness.id, 7 * 20, 0));
				break;
			case 4:
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 7 * 20, 0));
				break;
			case 5:
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 7 * 20, 0));
				break;
			case 6:
				player.addPotionEffect(new PotionEffect(Potion.harm.id, 7 * 20, 0));
				break;
		}
	}

	public void dropItems(EntityPlayer player) {
		if (!Config.dropper || player == null) { return; }

		sendRandomPhrase(player);
		player.inventory.dropAllItems();
	}

	public void spawnFireRingOnPlayer(EntityPlayer player, int radius) {
		if (!Config.placeFire || player == null) { return; }
        spawnFireRing(player, radius);
		sendRandomPhrase(player);
		sendHello(player);
	}

    public void spawnFireRingOnSelf(int radius) {
        spawnFireRing(this.stalker, radius);
    }

	private static void spawnFireRing(EntityLivingBase entity, int radius) {
        World world = entity.worldObj;
		if (!Config.placeFire) { return; }

		final double posX = entity.posX;
		final double posY = entity.posY;
		final double posZ = entity.posZ;
		for (int e = 0; e <= 360; e++) {

			final double x = posX + radius * Math.cos(e);
			final double z = posZ + radius * Math.sin(e);
			if (world.getBlock((int) x, (int) posY, (int) z) == Blocks.air
					&& world.getBlock((int) x, (int) posY - 1, (int) z) != Blocks.air) {
				world.setBlock((int) x, (int) posY, (int) z, Blocks.fire, 0, 3);
			}
		}
	}

	public void RingOfEnemies(EntityPlayer player, int radius, int enemy) {
		if (!Config.animal_circle || player == null) { return; }
        World world = player.worldObj;
		final double posX = player.posX;
		final double posY = player.posY;
		final double posZ = player.posZ;

		int cooldown = 0;
		for (int e = 0; e <= 360; e++) {
			cooldown++;
			if (30 < cooldown) {
				final double xAngle = posX + radius * Math.cos(e);
				final double zAngle = posZ + radius * Math.sin(e);
                EntityLivingBase entity = null;
				if (enemy == 0) {
					entity = new EntityEvilChicken(world, xAngle, posY + 1, zAngle);
				}
				else if (enemy == 1) {
					entity = new EntityEvilPig(world, xAngle, posY + 1, zAngle);
				}
				else if (enemy == 2) {
					entity = new EntityEvilVillager(world, xAngle, posY + 1, zAngle);
				}
                world.spawnEntityInWorld(entity);
                cooldown = 0;
            }
		}
	}

	public void placeWater() {
        World world = this.stalker.worldObj;
        int x = (int) this.stalker.posX;
        int y = (int) this.stalker.posY;
        int z = (int) this.stalker.posZ;
		if (Config.placeWater && world.rand.nextInt(10 + 1) == 2) {
			world.setBlock(x, y, z, Blocks.water, 0, 3);
			world.setBlock(x + 1, y, z, Blocks.water, 0, 3);
			world.setBlock(x + 2, y, z, Blocks.water, 0, 3);
			world.setBlock(x, y + 1, z, Blocks.water, 0, 3);
			world.setBlock(x + 1, y + 1, z, Blocks.water, 0, 3);
			world.setBlock(x + 2, y + 1, z, Blocks.water, 0, 3);
		}
	}

	public void placeLava() {
        World world = this.stalker.worldObj;
        int x = (int) this.stalker.posX;
        int y = (int) this.stalker.posY;
        int z = (int) this.stalker.posZ;
		if (Config.placeLava && world.rand.nextInt(10 + 1) == 2) {
			world.setBlock(x, y, z, Blocks.lava, 0, 3);
			world.setBlock(x + 1, y, z, Blocks.lava, 0, 3);
			world.setBlock(x + 2, y, z, Blocks.lava, 0, 3);
		}
	}

	@SuppressWarnings("SpellCheckingInspection")
    public static String[] phrases = { "140%!", "E", "EEEEEEEEEEEEEE!", "Hi GameChap!", "Hi Berty!",
			"?!!!erehw si hctoN", "nevaeh ot yawriats", "!lleh ot emoclew", "!kcik esuohdnouR", "!enirboreH ma I",
			"Hi!", "?tey deracs", "ten.tfarcenimtenalp no erog tsoP", "!deirram si hctoN", "!kcab ruoy hctaw", "ypeerC",
			"!olleh yaS", "?hctoN ees uoy diD", "!yalp annaw tsuj I", "!emag a yalp s'teL", "?em ekil uoy oD",
			"?mA I erehW", "?I mA ohW", "!evarg ym dniF", "!dneirf ruoy mI", "pihsrosneC", "!uoy ees ot eciN",
			"?uoy era ohW", "?edih uoy od yhW", "!uoy teg lliw I", "!sdnomaid tnaw I", "?em ees uoy naC",
			"!nileppeZ deL ot netsiL" };
}
