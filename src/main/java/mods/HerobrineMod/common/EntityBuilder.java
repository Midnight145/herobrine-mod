package mods.HerobrineMod.common;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;




public class EntityBuilder extends EntityBaseHMod{

	
	 public boolean getCanSpawnHere()
	    {
	    	return true;
	    }

	public EntityBuilder(World par1World) {
		super(par1World);
		this.lifespawn=700+((int) (Math.random() * 500 + 1));
		this.startAction=  100 +(int) (Math.random() * 300 + 1);
	
		
		if(((int)(Math.random()  + 0.5))==0)
		{
			this.Trap=true;
			this.inHand= Items.diamond_pickaxe;
		}
			
		if(((int)(Math.random() * 10+ 1))==5)
			this.placeGlass=true;
		
		if(((int)(Math.random() * 30+ 1))==5)
			this.CutLeaves=true;
		
		if(((int)(Math.random() * 5 +1))==2)
			this.placeASign=true;
		
		if(((int)(Math.random() * 20 +1))==2)
			this.Bloody=true;
		
		spawnedby = true;
	}


	
////////////////////////////////////////////////////////////////////////////////////
	public EntityBuilder(World world, double d, double d1, double d2) {

		
		this(world);
		if (!HerobrineMod.canSpawn)
			setDead();
		
		this.lifespawn=700+((int) (Math.random() * 500 + 1));
		this.startAction=  100 +(int) (Math.random() * 300 + 1);



		setPosition(d, d1 + (double) yOffset, d2);
		

		if(((int)(Math.random()  + 0.5))==0)
		{
			this.inHand= Items.diamond_pickaxe;
			this.Trap=true;
		}
			
		if(((int)(Math.random() * 10+ 1))==5)
			this.placeGlass=true;
		
		if(((int)(Math.random() * 5 +1))==2)
			this.placeASign=true;
		if(((int)(Math.random() * 20 +1))==2)
			this.Bloody=true;

 	}
	
////////////////////////////////////////////////////////////////////////////////////
	protected void part() {
		for (int i = 0; i < 20; i++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			double d3 = 10D;
			worldObj.spawnParticle("largesmoke",
					(posX + (double) (rand.nextFloat() * width * 2.0F))
	- (double) width - d * d3,
	(posY + (double) (rand.nextFloat() * height)) - d1 * d3,
	(posZ + (double) (rand.nextFloat() * width * 2.0F))
-	 (double) width - d2 * d3, d, d1, d2);
		}
	}
////////////////////////////////////////////////////////////////////////////////////
	public void setDead()
	{
		part();
		this.isDead=true;
	}

////////////////////////////////////////////////////////////////////////////////////
	public void onLivingUpdate() {
	
		
		super.onLivingUpdate();
		
		if (worldObj.isRemote)
			return;
		
		int i = (int) this.posX;
		int j = (int) this.posY;
		int k = (int) this.posZ;
		EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 30D);
		
		 if(entityplayer != null && canEntityBeSeen(entityplayer) && !this.Cought && !HerobrineMod.debug && !spawnedby)
		 {
			 this.Cought=true;
			 worldObj.playSoundEffect((double)i +
				  0.5D, (double)j + 0.5D, (double)k + 0.5D, "mob.ghast.scream", 35F,
					  0.8F);
		}
			
				  
		if(this.livingTicker > 200 && this.placeASign)
			this.sign();
		
		if(this.Trap && this.livingTicker > 400)
		{
			int rnd = ((int) (Math.random() * 70 + 1));
			if (rnd == 10 && !this.BuildingLoaded && HerobrineMod.placeTnt == 1) {
				worldObj.setBlock(i, j, k, Blocks.tnt,0,3);
	
				worldObj.setBlock(i + 1, j, k, Blocks.tnt,0,3);
				worldObj.setBlock(i + 2, j, k, Blocks.tnt,0,3);
				worldObj.setBlock(i + 1, j + 1, k,
						Blocks.fire,0,3);
				this.setDead();
			}
		}else{
			int rnd = ((int) (Math.random() * 50 + 1));
			if (rnd == 10 && !this.BuildingLoaded && HerobrineMod.placeFire == 1 && this.livingTicker > 400) {
				worldObj.setBlock(i, j, k, Blocks.fire,0,3);
			
				worldObj.setBlock(i + 1, j, k, Blocks.fire,0,3);
				worldObj.setBlock(i + 2, j, k, Blocks.fire,0,3);
				worldObj.setBlock(i + 3, j, k, Blocks.fire,0,3);
				worldObj.setBlock(i + 4, j, k, Blocks.fire,0,3);
				worldObj.setBlock(i + 5, j, k, Blocks.fire,0,3);
			
				this.setDead();
			}
		}
		if(this.startAction <= this.livingTicker)
		{
	
			boolean builded=this.Build();
			if(builded)
				this.setDead();
		}
		
		if(this.lifespawn <= this.livingTicker)
		{
			this.setDead();
		}
		
		if(this.Cought)
		{
			this.endTicker++;
			if(this.endTicker>100)
				this.setDead();
		}
		
		int rnd = (int) (Math.random() * 50 + 1);
		if (rnd <7 && this.CutLeaves) {

			HerobrineStalker x = new HerobrineStalker();
			x.cutLeaves(worldObj, i, j, k);
		}
		
		if(rnd <10 && HerobrineMod.placeGlass==1 && this.placeGlass)
		{

				worldObj.setBlock(i, j - 1, k,
						Blocks.glass,0, 3);
		}
		if(rnd <5 && this.Bloody && !this.placeGlass)
		{
			if(worldObj.getBlock(i,j-1,k)!= Blocks.glass)
				worldObj.setBlockMetadataWithNotify(i, j, k,
						Block.getIdFromBlock(Blocks.redstone_wire),0);
		}
		this.livingTicker++;
	}
////////////////////////////////////////////////////////////////////////////////////

	public void sign()
	{
		
			int direction = ((int) (Math.random() * 9 + 1));
			direction -= 1;

			Block id = worldObj.getBlock((int) this.posX,
					(int) this.posY, (int) this.posZ);
			if (id == null && id != Blocks.water) {
				
				
				worldObj.setBlock((int) this.posX,
						(int) this.posY, (int) this.posZ,
						Blocks.standing_sign, direction,3);
				TileEntitySign tileentitysign = (TileEntitySign) worldObj
						.getTileEntity((int) this.posX,
								(int) this.posY, (int) this.posZ);
				int txt = ((int) (Math.random() * phrases.length + 1));
				txt -= 1;
				tileentitysign.signText[0] = phrases[txt];
			}
			
			this.placeASign=false;
				
	}
////////////////////////////////////////////////////////////////////////////////////
	public boolean Build()
	{
		if(this.Trap) 
		{
			if(!this.BuildingLoaded)
			{
				int building = (int) (Math.random() * HerobrineMod.Traps + 1);
				String file = (new StringBuilder())
						.append(new Integer(building).toString())
						.append(".dat").toString();// new Integer(building)
				build.load(file,0);
				if (build.tryToPlace(worldObj, (int) this.posX,
						(int) this.posY, (int) this.posZ)) 
					return true;
			
				this.BuildingLoaded=true;
				
			}else{
				if (build.tryToPlace(worldObj, (int) this.posX,
						(int) this.posY, (int) this.posZ)) 
						return true;
			
			}
		}else{
			if(!this.BuildingLoaded)
			{
				int building = (int) (Math.random() * HerobrineMod.buildings + 1);
				String file = (new StringBuilder())
						.append(new Integer(building).toString())
						.append(".dat").toString();// new Integer(building)
				build.load(file,1);
				if (build.tryToPlace(worldObj, (int) this.posX,
						(int) this.posY, (int) this.posZ)) 
					return true;
			
				this.BuildingLoaded=true;
				
			}else{
				if (build.tryToPlace(worldObj, (int) this.posX,
						(int) this.posY, (int) this.posZ)) 
						return true;
			
			}
			
		}

		return false;
	}
////////////////////////////////////////////////////////////////////////////////////
	public ItemStack getHeldItem() {
		if (inHand != null)
			return new ItemStack(inHand);

		return null;
	}
////////////////////////////////////////////////////////////////////////////////////

	public Item inHand=Items.golden_pickaxe;
	public int lifespawn=0;
	public int livingTicker=0;
	public int startAction=0;
	public int endTicker=0;
	public boolean Cought=false;
	public boolean CutLeaves=false;
	public boolean placeGlass=false;
	public boolean Trap=false;
	public boolean BuildingLoaded=false;
	public boolean placeASign=false;
	public boolean Bloody=false;
	private boolean spawnedby = false;
	private HerobrineBuilder build = new HerobrineBuilder();

	public static String[] phrases = { "pmc sucks.", "E?", "u mad bro?", "Am I a ghost?",
		"Arrow in the knee!", "E", "Get lost!", "Chuck", "GameChap",
		"Berty", "EEEEEEEEEEE!", "I like you", "May I kill you?",
		"Look behind you!", "1+1", "Gold apple", "I rock!",
		"Watch your back!", "I was here", "Herobrine", "I'll get you!",
		"Find me", "Hi!", "Notch", "Buy Minecraft!", "Get me?",
		"Where are you?", "Chuck Norris!", "Watch out!", "I'm your shadow",
		"It's me Herobrine!", "Im a griefer", "Where is notch?",
		"Guess who was here", "Keep out", "Hell", "Im not dead!",
		"I'm alive!", "True", "1.1.0", "ASL?", "---"};

}
