package mods.HerobrineMod.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;




import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class HerobrineBuilder {

	public HerobrineBuilder()
	{

	}


	private static void placeBuildingHero(Block[][][] id, int[][][] it, World world,int x, int y, int z, int depth)
	{


		y += depth;
		// place solid blocks first
		for (int i = 0; i < id.length; i++)
		{
			for (int j = 0; j < id[0].length; j++)
			{
				for (int k = 0; k < id[0][0].length; k++)
				{
					if (!isNonSolid(id[i][j][k]))
					{

						//if(id[i][j][k]==200)
						//	id[i][j][k] = HerobrineMod.HerobrineTrigger.blockID;

						   if(tunnel==1)
						   {
							   if ((id[i][j][k] != Block.getBlockById(2)) && (id[i][j][k] != Blocks.grass)  && (id[i][j][k] != Blocks.dirt) && (id[i][j][k] != Block.getBlockById(3)) && (id[i][j][k] !=Block.getBlockById(4) ) && (id[i][j][k] !=Block.getBlockById(7) )&& (id[i][j][k] !=Block.getBlockById(24)))
								    world.setBlock(x + i + 1, y + j, z + k + 1, id[i][j][k], it[i][j][k],3);
						   }else{

					            world.setBlock(x + i + 1, y + j, z + k + 1, id[i][j][k], it[i][j][k],3);
					            if(id[i][j][k]==Blocks.obsidian)
					            {
					             	posx=x+i+1;
					            	posy=y+j;
					            	posz=z+k+1;
					            }

					            if(id[i][j][k]==Blocks.nether_brick)
					            {
					             	posx2=x+i+1;
					            	posy2=y+j;
					            	posz2=z+k+1;
					            }
						   }
					}

				}
			}
		}

		// place all other blocks
		for (int i = 0; i < id.length; i++)
		{
			for (int j = 0; j < id[0].length; j++)
			{
				for (int k = 0; k < id[0][0].length; k++)
				{



					//if(id[i][j][k]==200)
					//	id[i][j][k] = HerobrineMod.HerobrineTrigger.blockID;
					if(tunnel==1)
					   {
						 if ((id[i][j][k] != Block.getBlockById(2)) && (id[i][j][k] != Blocks.grass)  && (id[i][j][k] != Blocks.dirt) && (id[i][j][k] != Block.getBlockById(3)) && (id[i][j][k] !=Block.getBlockById(4)) && (id[i][j][k] !=Block.getBlockById(7) )&& (id[i][j][k] !=Block.getBlockById(24) ))
							   world.setBlock(x + i + 1, y + j, z + k + 1, id[i][j][k], it[i][j][k],3);
					   }else{

				            world.setBlock(x + i + 1, y + j, z + k + 1, id[i][j][k], it[i][j][k],3);
				            if(id[i][j][k]==Blocks.obsidian)
				            {
				            	posx=x+i+1;
				            	posy=y+j;
				            	posz=z+k+1;
				            }
					   }


					if ((id[i][j][k] == Block.getBlockById(27)) || (id[i][j][k] == Block.getBlockById(28)) || (id[i][j][k] ==Block.getBlockById(66)))
					{

					}
					if (j < id[0].length - 1 && (id[i][j][k] == Block.getBlockById(64) || id[i][j][k] == Block.getBlockById(71)))
					{
						world.setBlock(x + i + 1, y + j + 1, z + k + 1, id[i][j + 1][k], it[i][j + 1][k],3); // Door-Bug
					}

				}
			}
		}

	}



	public void load(String file, int type)
	{

    	try {



    		InputStream loca = null;

    		if(type==0 )loca = HerobrineMod.class.getResourceAsStream("buildings/traps/"+file);
    		 if(type==1) loca = HerobrineMod.class.getResourceAsStream("buildings/constructions/"+file);



    		BufferedReader in = new BufferedReader(new InputStreamReader(loca));

    		//BufferedReader in = new BufferedReader(new FileReader(loca.toString()));

    		String zeile = null;
    		String[] temp;
    		int le1,le2, le3;
    		tunnel =  Integer.parseInt(in.readLine());
    		depth = Integer.parseInt(in.readLine());
    		le1 = Integer.parseInt(in.readLine());
    		le2 = Integer.parseInt(in.readLine());
    		le3 = Integer.parseInt(in.readLine());
    		Block ai[][][] = new Block[le1][le2][le3];
    	    int ai1[][][] =new int[le1][le2][le3];


    		for(int le11=0;le11<le1; le11++)
    		{
    			for(int le22=0; le22<le2; le22++)
    			{
    				for(int le33=0;le33<le3; le33++)
    				{
    					zeile=in.readLine();

    					ai[le11][le22][le33]=Block.getBlockById(Integer.parseInt(zeile));//BlockID
    					zeile=in.readLine();
    					ai1[le11][le22][le33]=Integer.parseInt(zeile);//Metadata

    				}

    			}
    		}

            setBlockID(ai);
            setBlockMeta(ai1);
    		in.close();
    	} catch (IOException e) {
    		System.out.println("Herobrine didn't find the building/Trap...");
    		e.printStackTrace();
    	}
	}



	   public static boolean tryToPlace(World world, int i, int j, int k)
	   {
		   if(areaIsEmpty(world,i, j, k))
		   {
			   placeBuildingHero(getBlockID(), getBlockMeta(), world,i, j, k, depth);
			   return true;
		   }
		   return false;
	   }
	   private static boolean areaIsEmpty(World world, int i, int j, int k)
	    {

		int counter=0;
	    Block[][][] ai = blockID;
	    if(tunnel==0)
	      {
	        for(int l = 0; l < ai.length; l++)
	        {
	            for(int i1 = 0; i1 < ai[0].length; i1++)
	            {
	                for(int j1 = 0; j1 < ai[0][0].length; j1++)
	                {

	                    if(world.getBlock(i + l + 1, j + i1, k + j1 + 1) != Blocks.glass_pane && world.getBlock(i + l + 1, j + i1, k + j1 + 1) != Blocks.air && world.getBlock(i + l + 1, j + i1, k + j1 + 1) != Block.getBlockById(232) && world.getBlock(i + l + 1, j + i1, k + j1 + 1) != Blocks.tallgrass)
	                    {
	                        counter ++;
	                        if(((ai[0][0].length*ai[0].length)/2)<counter)
	                        	return false;
	                    }
	                }

	            }

	            for(int l2 = 0; l2 < ai.length; l2++)
		        {
		            for(int i2 = 0; i2 < ai[0][0].length; i2++)
		            {

		                    if(world.getBlock(i + l2 + 1, j-1, k+i2) == Blocks.air)
		                    {
		                        return false;
		                    }


		            }

		        }
	        }
		   }else{

			   for(int l = 0; l < ai.length; l++)
		        {
		            for(int i1 = 0; i1 < ai[0].length; i1++)
		            {
		                for(int j1 = 0; j1 < ai[0][0].length; j1++)
		                {
		                    if(world.getBlock(i + l + 1, (j+ depth)+ i1, k + j1 + 1) == Blocks.sand  || world.getBlock(i + l + 1, (j+ depth)+ i1, k + j1 + 1) == Blocks.air ||world.getBlock(i + l + 1, (j+depth) + i1, k + j1 + 1) == Block.getBlockById(232))
		                    {
		                            counter ++;
		                            if(5< counter)
		                        	   return false;
		                    }
		                }

		            }
		   }

		   }
	        return true;
	    }




	   public static Block[][][] getBlockID()
	    {
	        return blockID;
	    }

	    public static void setBlockID(Block ai[][][])
	    {
	        blockID = ai;
	    }

	    public static int[][][] getBlockMeta()
	    {
	        return blockMeta;
	    }

	    public static void setBlockMeta(int ai[][][])
	    {
	        blockMeta = ai;
	    }

	    public static void setBlockID2(Block ai[][][])
	    {
	        blockID2 = ai;
	    }



	    public static void setBlockMeta2(int ai[][][])
	    {
	        blockMeta2 = ai;
	    }

	public static boolean isNonSolid(Block i)
	{
		int id = Block.getIdFromBlock(i);
		return (id==70) || (id==72) ||(id==46) || (id == 6) || (id == 26) || (id == 27) || (id == 28) || (id == 30) || (id == 31) || (id == 32) || (id == 37) || (id == 38) || (id == 39) || (id == 40) || (id == 50) || (id == 51) || (id == 55) || (id == 59) || (id == 63) || (id == 64) || (id == 65) || (id == 66) || (id == 68) || (id == 69) ||  (id == 71)  || (id == 75) || (id == 76) || (id == 77) || (id == 78) || (id == 81) || (id == 83) || (id == 90) || (id == 93) || (id == 94) || (id == 96);
	}

	private static int xWidth,yHeight, zLength;

	  private static int depth = 0;
	  private static int tunnel=0;
	  public  static int posx,posy,posz;
	  private static int posx2,posy2,posz2;
	  private static int prex,prey,prez;
	  private static Block blockID[][][];
	  private static int blockMeta[][][];
	  private static Block blockID2[][][];
	  private static int blockMeta2[][][];
}

