package mods.HerobrineMod.common.mobs.herobrine;

import mods.HerobrineMod.common.HerobrineMod;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;

public class EntityBuilder extends EntityHerobrineBase {

	@Override
	public boolean getCanSpawnHere() { return true; }

	public EntityBuilder(World par1World) {
		super(par1World);
		this.lifespawn = 700 + (int) (Math.random() * 500 + 1);
		this.startAction = 100 + (int) (Math.random() * 300 + 1);

		if ((int) (Math.random() + 0.5) == 0) {
			this.Trap = true;
			this.inHand = Items.diamond_pickaxe;
		}

		if ((int) (Math.random() * 10 + 1) == 5) {
			this.placeGlass = true;
		}

		if ((int) (Math.random() * 30 + 1) == 5) {
			this.CutLeaves = true;
		}

		if ((int) (Math.random() * 5 + 1) == 2) {
			this.placeASign = true;
		}

		if ((int) (Math.random() * 20 + 1) == 2) {
			this.Bloody = true;
		}

		this.spawnedby = true;
	}

	////////////////////////////////////////////////////////////////////////////////////
	public EntityBuilder(World world, double d, double d1, double d2) {

		this(world);
		if (!HerobrineMod.canSpawn) {
			this.setDead();
		}

		this.lifespawn = 700 + (int) (Math.random() * 500 + 1);
		this.startAction = 100 + (int) (Math.random() * 300 + 1);

		this.setPosition(d, d1 + this.yOffset, d2);

		if ((int) (Math.random() + 0.5) == 0) {
			this.inHand = Items.diamond_pickaxe;
			this.Trap = true;
		}

		if ((int) (Math.random() * 10 + 1) == 5) {
			this.placeGlass = true;
		}

		if ((int) (Math.random() * 5 + 1) == 2) {
			this.placeASign = true;
		}
		if ((int) (Math.random() * 20 + 1) == 2) {
			this.Bloody = true;
		}

	}

	////////////////////////////////////////////////////////////////////////////////////
	protected void part() {
		for (int i = 0; i < 20; i++) {
			final double d = this.rand.nextGaussian() * 0.02D;
			final double d1 = this.rand.nextGaussian() * 0.02D;
			final double d2 = this.rand.nextGaussian() * 0.02D;
			final double d3 = 10D;
			this.worldObj.spawnParticle("largesmoke",
					this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
					this.posY + this.rand.nextFloat() * this.height - d1 * d3,
					this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3, d, d1, d2);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void setDead() {
		this.part();
		this.isDead = true;
	}

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void onLivingUpdate() {

		super.onLivingUpdate();

		if (this.worldObj.isRemote) { return; }

		final int i = (int) this.posX;
		final int j = (int) this.posY;
		final int k = (int) this.posZ;
		final EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 30D);

		if (entityplayer != null && this.canEntityBeSeen(entityplayer) && !this.Cought && !HerobrineMod.debug
				&& !this.spawnedby) {
			this.Cought = true;
			this.worldObj.playSoundEffect(i + 0.5D, j + 0.5D, k + 0.5D, "mob.ghast.scream", 35F, 0.8F);
		}

		if (this.livingTicker > 200 && this.placeASign) {
			this.sign();
		}

		if (this.Trap && this.livingTicker > 400) {
			final int rnd = (int) (Math.random() * 70 + 1);
			if (rnd == 10 && !this.BuildingLoaded && HerobrineMod.placeTnt == 1) {
				this.worldObj.setBlock(i, j, k, Blocks.tnt, 0, 3);

				this.worldObj.setBlock(i + 1, j, k, Blocks.tnt, 0, 3);
				this.worldObj.setBlock(i + 2, j, k, Blocks.tnt, 0, 3);
				this.worldObj.setBlock(i + 1, j + 1, k, Blocks.fire, 0, 3);
				this.setDead();
			}
		}
		else {
			final int rnd = (int) (Math.random() * 50 + 1);
			if (rnd == 10 && !this.BuildingLoaded && HerobrineMod.placeFire == 1 && this.livingTicker > 400) {
				this.worldObj.setBlock(i, j, k, Blocks.fire, 0, 3);

				this.worldObj.setBlock(i + 1, j, k, Blocks.fire, 0, 3);
				this.worldObj.setBlock(i + 2, j, k, Blocks.fire, 0, 3);
				this.worldObj.setBlock(i + 3, j, k, Blocks.fire, 0, 3);
				this.worldObj.setBlock(i + 4, j, k, Blocks.fire, 0, 3);
				this.worldObj.setBlock(i + 5, j, k, Blocks.fire, 0, 3);

				this.setDead();
			}
		}
		if (this.startAction <= this.livingTicker) {

			final boolean builded = this.Build();
			if (builded) {
				this.setDead();
			}
		}

		if (this.lifespawn <= this.livingTicker) {
			this.setDead();
		}

		if (this.Cought) {
			this.endTicker++;
			if (this.endTicker > 100) {
				this.setDead();
			}
		}

		final int rnd = (int) (Math.random() * 50 + 1);
		if (rnd < 7 && this.CutLeaves) {

			final HerobrineStalker x = new HerobrineStalker();
			x.cutLeaves(this.worldObj, i, j, k);
		}

		if (rnd < 10 && HerobrineMod.placeGlass == 1 && this.placeGlass) {

			this.worldObj.setBlock(i, j - 1, k, Blocks.glass, 0, 3);
		}
		if ((rnd < 5 && this.Bloody && !this.placeGlass) && (this.worldObj.getBlock(i, j - 1, k) != Blocks.glass)) {
			this.worldObj.setBlockMetadataWithNotify(i, j, k, Block.getIdFromBlock(Blocks.redstone_wire), 0);
		}
		this.livingTicker++;
	}
	////////////////////////////////////////////////////////////////////////////////////

	public void sign() {

		int direction = (int) (Math.random() * 9 + 1);
		direction -= 1;

		final Block id = this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ);
		if (id == null && id != Blocks.water) {

			this.worldObj.setBlock((int) this.posX, (int) this.posY, (int) this.posZ, Blocks.standing_sign, direction,
					3);
			final TileEntitySign tileentitysign = (TileEntitySign) this.worldObj.getTileEntity((int) this.posX,
					(int) this.posY, (int) this.posZ);
			int txt = (int) (Math.random() * phrases.length + 1);
			txt -= 1;
			tileentitysign.signText[0] = phrases[txt];
		}

		this.placeASign = false;

	}

	////////////////////////////////////////////////////////////////////////////////////
	public boolean Build() {
		if (this.Trap) {
			if (!this.BuildingLoaded) {
				final int building = (int) (Math.random() * HerobrineMod.Traps + 1);
				final String file = new StringBuilder().append(Integer.toString(building)).append(".dat").toString();// new
																														// Integer(building)
				this.build.load(file, 0);
				if (HerobrineBuilder.tryToPlace(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ)) {
					return true;
				}

				this.BuildingLoaded = true;

			}
			else {
				if (HerobrineBuilder.tryToPlace(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ)) {
					return true;
				}

			}
		}
		else {
			if (!this.BuildingLoaded) {
				final int building = (int) (Math.random() * HerobrineMod.buildings + 1);
				final String file = new StringBuilder().append(Integer.toString(building)).append(".dat").toString();// new
																														// Integer(building)
				this.build.load(file, 1);
				if (HerobrineBuilder.tryToPlace(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ)) {
					return true;
				}

				this.BuildingLoaded = true;

			}
			else {
				if (HerobrineBuilder.tryToPlace(this.worldObj, (int) this.posX, (int) this.posY, (int) this.posZ)) {
					return true;
				}

			}

		}

		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ItemStack getHeldItem() {
		if (this.inHand != null) { return new ItemStack(this.inHand); }

		return null;
	}
	////////////////////////////////////////////////////////////////////////////////////

	public Item inHand = Items.golden_pickaxe;
	public int lifespawn = 0;
	public int livingTicker = 0;
	public int startAction = 0;
	public int endTicker = 0;
	public boolean Cought = false;
	public boolean CutLeaves = false;
	public boolean placeGlass = false;
	public boolean Trap = false;
	public boolean BuildingLoaded = false;
	public boolean placeASign = false;
	public boolean Bloody = false;
	private boolean spawnedby = false;
	private final HerobrineBuilder build = new HerobrineBuilder();

	public static String[] phrases = { "pmc sucks.", "E?", "u mad bro?", "Am I a ghost?", "Arrow in the knee!", "E",
			"Get lost!", "Chuck", "GameChap", "Berty", "EEEEEEEEEEE!", "I like you", "May I kill you?",
			"Look behind you!", "1+1", "Gold apple", "I rock!", "Watch your back!", "I was here", "Herobrine",
			"I'll get you!", "Find me", "Hi!", "Notch", "Buy Minecraft!", "Get me?", "Where are you?", "Chuck Norris!",
			"Watch out!", "I'm your shadow", "It's me Herobrine!", "Im a griefer", "Where is notch?",
			"Guess who was here", "Keep out", "Hell", "Im not dead!", "I'm alive!", "True", "1.1.0", "ASL?", "---" };

}
