package mods.HerobrineMod.common;


import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySucker extends EntityAnimal{

	public EntitySucker(World par1World) {
		super(par1World);
		
		System.out.println("Herobrine Spawn");
		
		if(!HerobrineMod.debug)
		if (!HerobrineMod.canSpawn ||  worldObj.difficultySetting == worldObj.difficultySetting.PEACEFUL)
			this.setDead();
		
	
		
		//this.setDead();

		// TODO Auto-generated constructor stub
	}
////////////////////////////////////////////////////////////////////////////////////
	public EntitySucker(World worldClient, double x, double y, double z) {


		this(worldClient);
	
		
		if(!HerobrineMod.debug)
		if (!HerobrineMod.canSpawn ||  worldObj.difficultySetting == worldObj.difficultySetting.PEACEFUL)
			this.setDead();
		
		this.setPosition(x,y,z);
		
		
		
		//this.setDead();
		
		// TODO Auto-generated constructor stub
	}
////////////////////////////////////////////////////////////////////////////////////
	public void onLivingUpdate() {

		if(!HerobrineMod.canSpawn)
		{
			System.out.println("Can't Spawn!");
			this.setDead();
			return;
		}
		
		if(worldObj.isRemote)
		{
			//this.setdeath() ?
			//return;
		}
		
		if(HerobrineMod.extremeRare==1)
		{
			if ((int) (Math.random() * 80 + 1) != 10)
			{
				this.setDead();
				return;
			}
		}
		
		if ((int) (Math.random() * 130 + 1) == 10) {
			worldObj.spawnEntityInWorld(new EntityHerobrine(worldObj,
					(float) this.posX, (float) this.posY,
					(float) this.posZ + 3));
			
		
	 	}

		if ((int) (Math.random() * 130 + 1) == 10) {
			worldObj.spawnEntityInWorld(new EntityNorris(worldObj,
					(float) this.posX, (float) this.posY,
					(float) this.posZ + 3,true));
			
		
	 	}
	
		//!!!!!!!!!!!
		    if((int)(Math.random()  * 2 +1 )==1)
			{
				
				worldObj.spawnEntityInWorld(new EntityStalker(worldObj,
						(float) this.posX + 0.5F, (float) this.posY + 0.0F,
						(float) this.posZ + 0.5F, false));
				
			}else{
		
			
				worldObj.spawnEntityInWorld(new EntityBuilder(worldObj,
						(float) this.posX + 0.5F, (float) this.posY + 0.0F,
						(float) this.posZ + 0.5F));
				
			}
		
		
		if ((int) (Math.random() * 100 + 1) == 5) {
			worldObj.spawnEntityInWorld(new EntityPig2(worldObj,(float) this.posX + 0.5F, (float) this.posY + 0.0F,
					(float) this.posZ + 0.5F));
		}

		if ((int) (Math.random() * 100 + 1) == 6) {
			worldObj.spawnEntityInWorld(new EntityCow2(worldObj,(float) this.posX + 0.5F, (float) this.posY + 0.0F,
					(float) this.posZ + 0.5F));
		}
		if ((int) (Math.random() * 100 + 1) == 3) {
			worldObj.spawnEntityInWorld(new EntityChicken2(worldObj,(float) this.posX + 0.5F, (float) this.posY + 0.0F,
					(float) this.posZ + 0.5F));
		}
		this.isDead = true;
		this.setDead();
	}
	

////////////////////////////////////////////////////////////////////////////////////

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
    }

    public boolean getCanSpawnHere()
    {
    	return true;
    }

    
	  public EntitySucker spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	    {
	        return null;
	    }

	    /**
	     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	     * the animal type)
	     */
	    public boolean isBreedingItem(ItemStack par1ItemStack)
	    {
	        return false;
	    }

	    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	    {
	        return null;
	    }
	
}
