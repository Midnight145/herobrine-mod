package mods.HerobrineMod.common.mobs.herobrine;

import java.util.List;

import mods.HerobrineMod.common.HerobrineMod;
import mods.HerobrineMod.common.mobs.EntityChicken2;
import mods.HerobrineMod.common.mobs.EntityCow2;
import mods.HerobrineMod.common.mobs.EntityHVillager;
import mods.HerobrineMod.common.mobs.EntityPig2;
import mods.HerobrineMod.common.mobs.EntityZombie2;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
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

	public HerobrineStalker() {}

	public void freeze(EntityPlayerSP player, EntityLiving EntityLiving) {
		if (player == null) { return; }

		player.motionY = 0;
		player.motionX = 0;
		player.motionZ = 0;
		this.faceEntity(EntityLiving, 50F, 50F, player);

		// READDDDDDDDDDDDDDDDDDDDDDD!!!!!!!!!!!!!!!!!!!

		player.timeInPortal = 0.5F;
		player.prevTimeInPortal = 0.5F;

	}

	private float updateRotation(float par1, float par2, float par3) {
		float var4 = MathHelper.wrapAngleTo180_float(par2 - par1);

		if (var4 > par3) {
			var4 = par3;
		}

		if (var4 < -par3) {
			var4 = -par3;
		}

		return par1 + var4;
	}

	private void faceEntity(Entity par1Entity, float par2, float par3, EntityPlayerSP player) {
		final double d0 = par1Entity.posX - player.posX;
		final double d1 = par1Entity.posZ - player.posZ;
		double d2;

		if (par1Entity instanceof EntityLivingBase) {
			final EntityLivingBase entitylivingbase = (EntityLivingBase) par1Entity;
			d2 = entitylivingbase.posY + entitylivingbase.getEyeHeight() - (player.posY + player.getEyeHeight());
		}
		else {
			d2 = (par1Entity.boundingBox.minY + par1Entity.boundingBox.maxY) / 2.0D
					- (player.posY + player.getEyeHeight());
		}

		final double d3 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
		final float f2 = (float) (Math.atan2(d1, d0) * 180.0D / Math.PI) - 90.0F;
		final float f3 = (float) -(Math.atan2(d2, d3) * 180.0D / Math.PI);
		player.rotationPitch = this.updateRotation(player.rotationPitch, f3, par3);
		player.rotationYaw = this.updateRotation(player.rotationYaw, f2, par2);

	}

	public void Virus(EntityLiving EntityLiving, World world) {
		final double x = EntityLiving.posX;
		final double y = EntityLiving.posY;
		final double z = EntityLiving.posZ;
		final List entities = world.getEntitiesWithinAABB(Entity.class,
				AxisAlignedBB.getBoundingBox(x - 150, y - 150, z - 150, x + 150, y + 150, z + 150));

		for (int i = 0; i < entities.size(); i++) {
			final Entity entity = (Entity) entities.get(i);

			if (entity instanceof EntityPig) {

				final EntityPig2 sp = new EntityPig2(world, entity.posX, entity.posY, entity.posZ);
				((EntityPig) entity).setDead();
				world.spawnEntityInWorld(sp);

			}

			if (entity instanceof EntityChicken) {

				final EntityChicken2 sp = new EntityChicken2(world, entity.posX, entity.posY, entity.posZ);
				((EntityChicken) entity).setDead();
				world.spawnEntityInWorld(sp);

			}

			if (entity instanceof EntityCow) {

				final EntityCow2 sp = new EntityCow2(world, entity.posX, entity.posY, entity.posZ);
				((EntityCow) entity).setDead();
				world.spawnEntityInWorld(sp);

			}

			if (entity instanceof EntityVillager) {

				final EntityHVillager sp = new EntityHVillager(world, entity.posX, entity.posY, entity.posZ);
				((EntityVillager) entity).setDead();
				world.spawnEntityInWorld(sp);

			}
		}
	}

	public void blastGlowe(EntityLiving EntityLiving, World world) {

		if (HerobrineMod.kick == 1) {
			final EntityPlayer player = world.getClosestPlayerToEntity(EntityLiving, 3000D);
			player.motionZ += 0.5f;
			player.motionX += 0.8;
			player.motionY += 0.5;

			if (!world.isRemote) {
				MinecraftServer.getServer().getConfigurationManager()
						.sendChatMsg(new ChatComponentText("kcik-esuohdnuoR"));
			}
		}
	}

	public void Lightning(World world, EntityLiving EntityLiving) {
		final EntityPlayer player = world.getClosestPlayerToEntity(EntityLiving, 300D);
		int msg = (int) (Math.random() * phrases.length + 1);
		msg -= 1;
		if (player == null) { return; }

		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(phrases[msg]));
		world.spawnEntityInWorld(new EntityLightningBolt(world, player.posX, player.posY + 2, player.posZ));
	}

	public void dropTorches(World world, int i, int j, int k) {
		k -= 2;

		for (int l = -100; l < 100; l++) {
			for (int i1 = -10; i1 < 10; i1++) {
				for (int j1 = -100; j1 < 100; j1++) {
					if (world.getBlock(i + l + 1, j + i1, k + j1 + 1) == Blocks.torch) {
						Blocks.torch.dropBlockAsItem(world, i + l + 1, j + i1, k + j1 + 1, 0, 0);
					}
				}
			}
		}
	}

	public void cutLeaves(World world, int i, int j, int k) {
		k -= 2;

		for (int l = -20; l < 20; l++) {
			for (int i1 = -10; i1 < 10; i1++) {
				for (int j1 = -20; j1 < 20; j1++) {
					if (world.getBlock(i + l + 1, j + i1, k + j1 + 1) == Blocks.leaves) {
						world.setBlock(i + l + 1, j + i1, k + j1 + 1, Blocks.air, 0, 3);
					}
				}
			}
		}
	}

	public void breakglass(World world, int i, int j, int k) {
		k -= 2;

		for (int l = -20; l < 20; l++) {
			for (int i1 = -10; i1 < 10; i1++) {
				for (int j1 = -20; j1 < 20; j1++) {
					if (world.getBlock(i + l + 1, j + i1, k + j1 + 1) == Blocks.glass
							&& (int) (Math.random() * 4 + 1) == 3) {

						world.setBlock(i + l + 1, j + i1, k + j1 + 1, Blocks.air, 0, 3);
					}
				}
			}
		}
	}

	public void onFire(World worldObj, EntityLiving EntityLiving) {
		if (HerobrineMod.setOnFire == 1 && (int) (Math.random() * 7 + 1) == 2) {
			final EntityPlayer entityplayer2 = worldObj.getClosestPlayerToEntity(EntityLiving, 3000D);
			if (entityplayer2 == null) { return; }

			// entityplayer2.setOnFireFromLava();
			entityplayer2.setFire(10);
			int msg = (int) (Math.random() * phrases.length + 1);
			msg -= 1;
			if (!worldObj.isRemote) {
				MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(phrases[msg]));
				MinecraftServer.getServer().getConfigurationManager()
						.sendChatMsg(new ChatComponentText("Hi " + entityplayer2.getDisplayName()));
			}
			entityplayer2.inventory.addItemStackToInventory(new ItemStack(Items.bucket, 1));
		}
	}

	public void throwplayer2(World worldObj, EntityLiving EntityLiving) {
		final EntityPlayer entityplayer2 = worldObj.getClosestPlayerToEntity(EntityLiving, 3000D);

		if (entityplayer2 == null) { return; }

		entityplayer2.motionY += 0.998888888F;
		int msg = (int) (Math.random() * phrases.length + 1);
		msg -= 1;
		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(phrases[msg]));
	}

	public void Boss(World worldObj, int i, int j, int k, EntityLiving EntityLiving) {

		worldObj.spawnEntityInWorld(new EntityHerobrineBoss(worldObj, i + 0.5F, j + 1.0F, k + 0.5F));
	}

	public void Zombie(World worldObj, int i, int j, int k, EntityLiving EntityLiving) {
		worldObj.spawnEntityInWorld(new EntityZombie2(worldObj, i + 0.5F, j + 1.0F, k + 0.5F));
		worldObj.spawnEntityInWorld(new EntityZombie2(worldObj, i - 0.5F, j - 1.0F, k - 0.5F));
		int msg = (int) (Math.random() * phrases.length + 1);
		msg -= 1;
		
		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(phrases[msg]));
	}

	public void HVillager(World worldObj, int i, int j, int k, EntityLiving EntityLiving) {
		worldObj.spawnEntityInWorld(new EntityHVillager(worldObj, i + 0.5F, j + 1.0F, k + 0.5F));
		int msg = (int) (Math.random() * phrases.length + 1);
		msg -= 1;

		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(phrases[msg]));
	}

	public void Chicken(World worldObj, int i, int j, int k, EntityLiving EntityLiving) {
		int msg = (int) (Math.random() * phrases.length + 1);
		msg -= 1;

		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(phrases[msg]));

		for (int x = 0; x < 10; x++) {
			worldObj.spawnEntityInWorld(new EntityChicken2(worldObj, i + 0.5F * x, j + 1.0F, k + 0.5F * x));
		}

		if ((int) (Math.random() * 7 + 1) == 2) {
			worldObj.spawnEntityInWorld(new EntityPig2(worldObj, i + 0.5F, j + 1.0F, k + 0.5F));
		}
	}

	public void potion(World worldObj, EntityLiving EntityLiving) {
		final EntityPlayer player2 = worldObj.getClosestPlayerToEntity(EntityLiving, 3000D);

		final int rnd = (int) (Math.random() * 7);

		switch (rnd) {
			case 0:
				player2.addPotionEffect(new PotionEffect(Potion.poison.id, 7 * 20, 0));
				break;
			case 1:
				player2.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 7 * 20, 0));
				break;
			case 2:
				player2.addPotionEffect(new PotionEffect(Potion.confusion.id, 7 * 20, 0));
				break;
			case 3:
				player2.addPotionEffect(new PotionEffect(Potion.blindness.id, 7 * 20, 0));
				break;
			case 4:
				player2.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 7 * 20, 0));
				break;
			case 5:
				player2.addPotionEffect(new PotionEffect(Potion.nightVision.id, 7 * 20, 0));
				break;
			case 6:
				player2.addPotionEffect(new PotionEffect(Potion.harm.id, 7 * 20, 0));
				break;
		}
	}

	public void dropItems(World worldObj, EntityLiving EntityLiving) {
		if (HerobrineMod.dropper == 0) { return; }

		final EntityPlayer entityplayer2 = worldObj.getClosestPlayerToEntity(EntityLiving, 3000D);

		int msg = (int) (Math.random() * phrases.length + 1);
		msg -= 1;
		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(phrases[msg]));

		entityplayer2.inventory.dropAllItems();
	}

	public void RingOfFire(int radius, World world, EntityLiving Entity) {

		if (HerobrineMod.placeFire == 0) { return; }
		final EntityPlayer entityplayer2 = world.getClosestPlayerToEntity(Entity, 3000D);

		final double posX = entityplayer2.posX;
		final double posY = entityplayer2.posY;
		final double posZ = entityplayer2.posZ;
		for (int e = 0; e <= 360; e++) {

			final double x = posX + radius * Math.cos(e);
			final double z = posZ + radius * Math.sin(e);
			if (world.getBlock((int) x, (int) posY, (int) z) == Blocks.air) {
				if (world.getBlock((int) x, (int) posY - 1, (int) z) != Blocks.air) {
					world.setBlock((int) x, (int) posY, (int) z, Blocks.fire, 0, 3);
				}
			}
			
		}

		int msg = (int) (Math.random() * phrases.length + 1);
		msg -= 1;

		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(phrases[msg]));
		MinecraftServer.getServer().getConfigurationManager()
				.sendChatMsg(new ChatComponentText("Hi " + entityplayer2.getDisplayName()));

	}

	public void RingOfFireH(int radius, World world, EntityLiving Entity) {

		if (HerobrineMod.placeFire == 0) { return; }

		final double posX = Entity.posX;
		final double posY = Entity.posY;
		final double posZ = Entity.posZ;
		for (int e = 0; e <= 360; e++) {

			final double x = posX + radius * Math.cos(e);
			final double z = posZ + radius * Math.sin(e);
			if (world.getBlock((int) x, (int) posY, (int) z) == Blocks.air) {
				if (world.getBlock((int) x, (int) posY - 1, (int) z) != Blocks.air) {
					world.setBlock((int) x, (int) posY, (int) z, Blocks.fire, 0, 3);
				}
			}
			
		}

	}

	public void RingOfEnemies(int radius, World world, EntityLiving Entity, int enemy) {

		if (HerobrineMod.animal_circle == 0) { return; }

		final EntityPlayer entityplayer2 = world.getClosestPlayerToEntity(Entity, 3000D);

		final double posX = entityplayer2.posX;
		final double posY = entityplayer2.posY;
		final double posZ = entityplayer2.posZ;
		int cooldown = 0;
		for (int e = 0; e <= 360; e++) {

			cooldown++;
			if (30 < cooldown) {
				final double x = posX + radius * Math.cos(e);
				final double z = posZ + radius * Math.sin(e);
				if (enemy == 0) {
					final EntityChicken2 sp = new EntityChicken2(world, x, posY + 1, z);
					world.spawnEntityInWorld(sp);
				}
				if (enemy == 1) {
					final EntityPig2 sp = new EntityPig2(world, x, posY + 1, z);
					world.spawnEntityInWorld(sp);
				}
				if (enemy == 2) {
					final EntityHVillager sp = new EntityHVillager(world, x, posY + 1, z);
					world.spawnEntityInWorld(sp);
				}

				cooldown = 0;
			}

		}

	}

	public void placeWater(World worldObj, int i, int j, int k) {
		if (HerobrineMod.placeWater == 1 && (int) (Math.random() * 10 + 1) == 2) {

			worldObj.setBlock(i, j, k, Blocks.water, 0, 3);
			worldObj.setBlock(i + 1, j, k, Blocks.water, 0, 3);
			worldObj.setBlock(i + 2, j, k, Blocks.water, 0, 3);
			worldObj.setBlock(i, j + 1, k, Blocks.water, 0, 3);
			worldObj.setBlock(i + 1, j + 1, k, Blocks.water, 0, 3);
			worldObj.setBlock(i + 2, j + 1, k, Blocks.water, 0, 3);

		}
	}

	public void placeLava(World worldObj, int i, int j, int k) {
		if (HerobrineMod.placeLava == 1 && (int) (Math.random() * 10 + 1) == 2) {

			worldObj.setBlock(i, j, k, Blocks.lava, 0, 3);
			worldObj.setBlock(i + 1, j, k, Blocks.lava, 0, 3);
			worldObj.setBlock(i + 2, j, k, Blocks.lava, 0, 3);
		}
	}

	public static String[] christmas = { "Damn! Build me a christmastree!", "Im satan santa!", "Hi!",
			"I got a present for you!", "HoHoHo!", "Merry Christmas!", "Where is my christmastree??!",
			"I am a bad santa!", "Hi Bertie! I got a present for you!", "Hi GameChap!", "come closer",
			"Don't be scared", "Already got an arrow in your knee?" };

	public static String[] phrases = { "140%!", "E", "EEEEEEEEEEEEEE!", "Hi GameChap!", "Hi Berty!",
			"?!!!erehw si hctoN", "nevaeh ot yawriats", "!lleh ot emoclew", "!kcik esuohdnouR", "!enirboreH ma I",
			"Hi!", "?tey deracs", "ten.tfarcenimtenalp no erog tsoP", "!deirram si hctoN", "!kcab ruoy hctaw", "ypeerC",
			"!olleh yaS", "?hctoN ees uoy diD", "!yalp annaw tsuj I", "!emag a yalp s'teL", "?em ekil uoy oD",
			"?mA I erehW", "?I mA ohW", "!evarg ym dniF", "!dneirf ruoy mI", "pihsrosneC", "!uoy ees ot eciN",
			"?uoy era ohW", "?edih uoy od yhW", "!uoy teg lliw I", "!sdnomaid tnaw I", "?em ees uoy naC",
			"!nileppeZ deL ot netsiL" };

	public void throwplayer(World worldObj, EntityLiving me) {
		// TODO Auto-generated method stub
		final EntityPlayer entityplayer2 = worldObj.getClosestPlayerToEntity(me, 3000D);
		if (entityplayer2 == null) { return; }
		entityplayer2.motionY += 0.998888888F;
	}
}
