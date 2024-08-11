package mods.HerobrineMod.common;


import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityStalker extends EntityBaseHMod
{
	
	protected void applyEntityAttributes()
	{
	super.applyEntityAttributes();

	this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
	 this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
	}
	

    public EntityStalker(World par1World)
    {
        super(par1World);
        // TODO Auto-generated constructor stub
 
    }


////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////
    public EntityStalker(World world, double d, double d1, double d2, boolean chuck)
    {
    	
        this(world);
        this.Norris = chuck;

        if (!HerobrineMod.canSpawn) // !worldObj.isRemote)
        {
            setDead();
        }
        System.out.println("Stalker");

        setPosition(d, d1 + (double) yOffset, d2);

       
        if ((int)(Math.random() * 30 + 1) == 10)
        {
        	if((int)Math.random()+1==0)
        	{
        		this.inHand = Items.diamond_sword;
        	}else{
        		this.inHand = Items.golden_sword;
        	}
        }
    }
////////////////////////////////////////////////////////////////////////////////////
    protected void part()
    {
        for (int i = 0; i < 20; i++)
        {
            double d = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            double d3 = 10D;
            worldObj.spawnParticle("largesmoke",
                    (posX + (double)(rand.nextFloat() * width * 2.0F))
                    - (double) width - d * d3,
                    (posY + (double)(rand.nextFloat() * height)) - d1 * d3,
                    (posZ + (double)(rand.nextFloat() * width * 2.0F))
                    - (double) width - d2 * d3, d, d1, d2);
        }
    }
////////////////////////////////////////////////////////////////////////////////////
    public void setDead()
    {
        part();
        this.isDead = true;
    }

////////////////////////////////////////////////////////////////////////////////////
    public void onLivingUpdate()
    {
    	
    	 EntityPlayer stalk = (EntityPlayer) worldObj.getClosestPlayerToEntity(this, 300D);

          if (stalk != null)
          {
              faceEntity(stalk, 30F, 30F);    // face the player
          }
          
        this.ceased = true;

        if (this.hasAppeared)
        {
            super.onLivingUpdate();
        }

        int i = (int) this.posX;
        int j = (int) this.posY;
        int k = (int) this.posZ;

        if (!this.hasAppeared)
        {
            EntityPlayer toTeleport =  worldObj.getClosestPlayerToEntity(this, 3000D);
            
            if (toTeleport == null)
            {
                return;
            }

            if ((HerobrineMod.teleport == 1) && ((int)(Math.random() * 70 + 1) == 9))
            {
                toTeleport.setPosition(this.posX + 1, this.posY, this.posZ);
                this.freezePlayer = true;
            }
            else
            {
                this.setPosition(toTeleport.posX + 1, toTeleport.posY, toTeleport.posZ);
            }

            super.onLivingUpdate();
            this.hasAppeared = true;
            int s = (int)(Math.random() * 20 + 1);

            if(s == 5)
            {
            	HerobrineStalker d = new HerobrineStalker();
            	d.RingOfFireH((int)(Math.random() * 5 + 3), worldObj, this);
            }
            
            if((int)(Math.random() * 30 + 1) ==15)
            {
            	HerobrineStalker d = new HerobrineStalker();
            	d.Virus(this, worldObj);
            }
            
            if (s == 10)
                worldObj.playSoundEffect((double) i + 0.5D,
                        (double) j + 0.5D, (double) k + 0.5D,
                        "mob.ghast.scream", 35F, 0.8F);// scream

            worldObj.playSoundEffect((double) i + 0.5D,
                    (double) j + 0.5D, (double) k + 0.5D,
                    "ambient.cave.cave", 35F, 0.8F);// scream
            this.lifespawn = 30 + ((int)(Math.random() * 20 + 1));

            //Freeze the player?
            if((int)(Math.random() * 20 + 1) == 9)
            {
         
                HerobrineStalker x = new HerobrineStalker();
                
                if (worldObj.isRemote) {
          
                    EntityPlayerSP player = (EntityPlayerSP) toTeleport;
                    x.freeze(player, this);
                }else{
                	toTeleport.motionX=0;
                	toTeleport.motionY=0;
                	toTeleport.motionZ=0;
                	
                }
          
                this.freezePlayer = true;
                worldObj.playSoundEffect((double) i + 0.5D,
                        (double) j + 0.5D, (double) k + 0.5D,
                        "mob.ghast.scream", 35F, 0.8F);
               

            
            }
            int f = (int)(Math.random() * 25 + 1);
            
            if (f == 15 && HerobrineMod.dropTorch == 1)// Dropp all Torches?
            {
                HerobrineStalker st = new HerobrineStalker();
                st.dropTorches(worldObj, (int) this.posX, (int) this.posY,
                        (int) this.posZ);
            }
        }
        else
        {
            this.livingTicker++;

            if (15 < this.livingTicker)
            {
                this.ceased = true;
            }

            
        }

      

        if (this.freezePlayer)
        {
        	if(worldObj.isRemote)
        	{
        	
        	
        		EntityPlayerSP player = (EntityPlayerSP) stalk;
      
     
        		HerobrineStalker x = new HerobrineStalker();
				if(stalk!=null)
        		   x.freeze(player, this);
        	}else{
				if(stalk!=null)
				{
					stalk.motionX=0;
					stalk.motionY=0;
					stalk.motionZ=0;
				}
        	}
        }

        if (this.lifespawn < this.livingTicker)
        {
    
            if (!worldObj.isRemote)
                if ((int)(Math.random() * 15 + 1) == 10 && HerobrineMod.attac == 1)
                {
                    worldObj.spawnEntityInWorld(new EntityFighter(worldObj,
                            (float) stalk.posX + 0.5F, (float) stalk.posY + 0.0F,
                            (float) stalk.posZ + 0.5F, false));
                    this.setDead();
                }

            this.Attack();
            this.setDead();
        }
    }
////////////////////////////////////////////////////////////////////////////////////
    public void Attack()
    {
        int rnd = (int)(Math.random() * 30 + 1);
        HerobrineStalker st = new HerobrineStalker();

        if (this.Norris)
        {
			
			st.blastGlowe(this, worldObj);
            return;
        }


        switch (rnd)
        {
            case 1:
                if (!worldObj.isRemote)
                    st.Zombie(worldObj, (int) this.posX, (int) this.posY,
                            (int) this.posZ, this);

                break;

            case 2:
                if (!worldObj.isRemote)
                    st.Chicken(worldObj, (int) this.posX, (int) this.posY,
                            (int) this.posZ, this);

                break;

            case 3:
                st.blastGlowe(this, worldObj);
                break;

            case 4:
                st.Lightning(worldObj, this);
                break;

            case 5:
                st.dropItems(worldObj, this);
                break;

            case 6:
                st.onFire(worldObj, this);
                break;

            case 7:
                st.throwplayer(worldObj, this);
                break;

            case 8:
                st.onFire(worldObj, this);
                break;

            case 9:
                if (!worldObj.isRemote)
                    st.placeWater(worldObj, (int) this.posX, (int) this.posY,
                            (int) this.posZ);

                break;

            case 10:
                if (!worldObj.isRemote)
                    st.placeLava(worldObj, (int) this.posX, (int) this.posY,
                            (int) this.posZ);
                break;

            case 12:
                if (!worldObj.isRemote)
                    st.breakglass(worldObj, (int) this.posX, (int) this.posY,
                            (int) this.posZ);
          
                break;
                
            case 13:
                if (!worldObj.isRemote)
                    st.HVillager(worldObj, (int) this.posX, (int) this.posY,
                            (int) this.posZ, this);
                break;
            case 14:
            	if(!worldObj.isRemote)
            		st.RingOfFire((int)(Math.random() * 12 + 5), worldObj, this);
            	break;
            	
            case 15:
            	if(!worldObj.isRemote)
            		st.RingOfEnemies((int)(Math.random() * 2 + 5), worldObj, this,0);
            	break;
            case 16:
            	if(!worldObj.isRemote)
            		st.RingOfEnemies((int)(Math.random() * 2 + 4), worldObj, this,1);
            	break;
            case 17:
            	if(!worldObj.isRemote)
            		st.RingOfEnemies((int)(Math.random() * 2 + 4), worldObj, this,2);
            	break;
            	
            case 18:
            	if(!worldObj.isRemote)
            		st.Virus(this, worldObj);
            	break;  
            case 19:
            	if(!worldObj.isRemote)
            		st.potion(worldObj, this);
            	break;
               
        }
    	if((int)(Math.random() * 250 + 1)==10)
    		 st.Boss(worldObj, (int) this.posX, (int) this.posY,
    				 (int) this.posZ, this);

    }
////////////////////////////////////////////////////////////////////////////////////
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
       

        if (((int)(Math.random() * 5 + 1)) == 2)
        {
            HerobrineStalker thrower = new HerobrineStalker();
            thrower.throwplayer2(worldObj,this);
        }else{
        //	 setDead();
        }

        return super.attackEntityFrom(par1DamageSource, par2);
    }
////////////////////////////////////////////////////////////////////////////////////
    protected boolean isMovementCeased()
    {
        return ceased;
    }
////////////////////////////////////////////////////////////////////////////////////
    protected String getHurtSound()
    {
        return "";
    }

////////////////////////////////////////////////////////////////////////////////////
    public ItemStack getHeldItem()
    {
        if (inHand != null)
        {
            return new ItemStack(inHand);
        }

        return null;
    }
////////////////////////////////////////////////////////////////////////////////////

    public Item inHand;
    boolean Norris = false;
    boolean hasAppeared = false;
    boolean freezePlayer = false;
    boolean ceased = false;
    int livingTicker = 0;
    int lifespawn = 0;

}
