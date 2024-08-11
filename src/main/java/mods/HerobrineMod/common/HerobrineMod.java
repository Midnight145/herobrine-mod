package mods.HerobrineMod.common;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;



import mods.HerobrineMod.UpdateChecker.UpdateChecker;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid="herobrinemod", name="HerobrineMod", version="3.7")

public class HerobrineMod {
	
	
	
    	public static String version = "3.7";
    	public static boolean starting = false;
    	
    	//Warning!!
    	public static boolean debug =false;
    
	    public static int buildings = 22;
	    public static int Trap = 12;

    
    	public static int BlockID;
    	public static int debugID;
    	public static int[] discIDS = new int[3];
    	public static int Traps;
		public static boolean canSpawn=false;
	    public static int attac=1;
	    public static int[] spawn = new int[3];
	    public static int extremeRare;
	    public static int chuckNorris;
	    public static int heroSwine;
	    public static int cowBrine;
	    public static int notch;
	    public static int placeFire;
	    public static int placeTnt;
	    public static int placeLava;
	    public static int polterbrine = 1;
	    public static int placeBuildings;
	    public static int placeTraps;
	    public static int placeGlass;
	    public static int placeWater;
	    public static int setOnFire;
	    public static int dropTorch;
	    public static int sleepA;
	    public static int prank;
	    public static int dropper;
	    public static int kick;
	    public static int teleport=1;
	    public static int reminder;
	    public static int ad;
	    public static int mini_mode;
	    public static int no_totem;

	    public static int animal_circle;
	    
	    public static Block totem;
	    
	     public static Item itsHerobrine;
	     public static Item seeherobrine;

	     // public static final Item seeHerobrine;


        // The instance of your mod that Forge uses.
        @Instance("herobrinemod")
        public static HerobrineMod instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="mods.HerobrineMod.client.ClientProxy", serverSide="mods.HerobrineMod.common.CommonProxy")
        public static CommonProxy proxy;
        
        
     
        public static BiomeGenBase[] biomesWithout(BiomeGenBase... biomesWithout){
            ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();
            
            for(BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()){
                for(int i = 0; i < biomesWithout.length; i++){
                    if(biome != null && biome != biomesWithout[i]){
                        biomes.add(biome);
                    }
                }
            }
            return biomes.toArray(new BiomeGenBase[biomes.size()]);
        }

        
     
        @EventHandler
    	public void preInit(FMLPreInitializationEvent event)
    	{
   
 
        
        	System.out.println("Herobrine Mod preInit....");
        	proxy.preInit();
        	
        	if(prank==0)
        	{
        		this.totem = new BlockHerobrine(Material.rock).setHardness(3F).setResistance(20F).setCreativeTab(CreativeTabs.tabDecorations).setBlockName("totem").setBlockTextureName("herobrinemod:blockskin1");
        		GameRegistry.registerBlock(this.totem, this.totem.getLocalizedName().substring(5));

        		GameRegistry.addRecipe(new ItemStack(totem, 1), new Object[]
        				{ "###", "#X#", "###", Character.valueOf('X'), Blocks.soul_sand, Character.valueOf('#'), Items.bone });
        	}

    	

    		itsHerobrine = new ItemHDisc("seeherobrine", "gothtigger92 - Have You Seen the Herobrine").setUnlocalizedName("seeherobrine").setTextureName("herobrinemod:record_seeherobrine");
    		seeherobrine = (new ItemHDisc("itsherobrine", "Bla Bla Gaming - It's Herobrine")).setUnlocalizedName("itsherobrine").setTextureName("herobrinemod:record_ctune");
    		GameRegistry.registerItem(itsHerobrine, itsHerobrine.getUnlocalizedName().substring(5));
    		GameRegistry.registerItem(seeherobrine, seeherobrine.getUnlocalizedName().substring(5));
    		
    	
       		Configuration configuration = new Configuration(event.getSuggestedConfigurationFile());
       		
    		try
    		{
    			configuration.load();
    			Property prop;
    			


    			prop = configuration.get(Configuration.CATEGORY_GENERAL, "spawn_enemy_circles", "1");
    			animal_circle= Integer.valueOf(prop.getString()).intValue();   
    			prop = configuration.get(Configuration.CATEGORY_GENERAL, "spawn", "30");
    	        spawn[0]= Integer.valueOf(prop.getString()).intValue();
    	    	prop = configuration.get(Configuration.CATEGORY_GENERAL, "max_spawn", "7");
    	        spawn[1]= Integer.valueOf(prop.getString()).intValue();   
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "min_spawn", "2");
    	        spawn[2]= Integer.valueOf(prop.getString()).intValue();
    	        prop.comment = "Not higher than max_spawn, it will crash your game!";
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "prank", "0");
    	        prank = Integer.valueOf(prop.getString()).intValue();
    	        prop.comment = "Wanna troll your friends? There you go.";
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "pigbrine", "1");
    	        heroSwine = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "cowbrine", "1");
    	        cowBrine = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "place_fire", "1");
    	        placeFire = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "place_lava", "1");
    	        placeLava = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "place_buildings", "1");
    	        placeBuildings = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "place_traps", "1");
    	        placeTraps = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "place_glass", "1");
    	        placeGlass = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "place_tnt", "1");
    	        placeTnt = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "place_water", "1");
    	        placeWater = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "set_player_on_fire", "1");
    	        setOnFire = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "drop_torches", "1");
    	        dropTorch = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "drop_player_items_on_floor", "1");
    	        dropper = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "Roundhousekick", "1");
    	        kick = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "extremraremode", "0");
    	        extremeRare = Integer.valueOf(prop.getString()).intValue();
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "update_reminder", "1");
    	        reminder = Integer.valueOf(prop.getString()).intValue();         
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "show_add", "1");
    	        ad = Integer.valueOf(prop.getString()).intValue();	        
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "mini_mode", "0");
    	        mini_mode = Integer.valueOf(prop.getString()).intValue();   	        
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "no_totem_mode", "0");
    	        no_totem = Integer.valueOf(prop.getString()).intValue();   			
    	        prop = configuration.get(Configuration.CATEGORY_GENERAL, "debug_mode", "0");
    	        int deb = Integer.valueOf(prop.getString()).intValue();
    	     
    	      
    	        if(deb==1)
    	        	debug = true;
    	        
    		} catch (Exception e)
    		{
    			FMLLog.severe(e.getMessage());

    		} finally
    		{
    			configuration.save();
    		}



            
     	    EntityRegistry.addSpawn(EntityChicken2.class, 1, 1, 2, EnumCreatureType.creature,BiomeGenBase.desert,
             	    BiomeGenBase.desertHills,
             	    BiomeGenBase.extremeHills,
             	    BiomeGenBase.extremeHillsEdge,
             	    BiomeGenBase.forest,
             	    BiomeGenBase.forestHills,
             	    BiomeGenBase.frozenOcean,
             	    BiomeGenBase.frozenRiver,
             	    BiomeGenBase.hell,
             	    BiomeGenBase.iceMountains,
             	    BiomeGenBase.icePlains,
             	    BiomeGenBase.jungle,
             	    BiomeGenBase.jungleHills,
             	    BiomeGenBase.mushroomIsland,
             	    BiomeGenBase.ocean,
             	    BiomeGenBase.plains,
             	    BiomeGenBase.river,
             	    BiomeGenBase.swampland,
             	    BiomeGenBase.taiga);
     	    
    	    EntityRegistry.addSpawn(EntityCow2.class, 2, 1, 4, EnumCreatureType.creature,
             	    BiomeGenBase.desert,
             	    BiomeGenBase.desertHills,
             	    BiomeGenBase.extremeHills,
             	    BiomeGenBase.extremeHillsEdge,
             	    BiomeGenBase.forest,
             	    BiomeGenBase.forestHills,
             	    BiomeGenBase.frozenOcean,
             	    BiomeGenBase.frozenRiver,
             	    BiomeGenBase.hell,
             	    BiomeGenBase.iceMountains,
             	    BiomeGenBase.icePlains,
             	    BiomeGenBase.jungle,
             	    BiomeGenBase.jungleHills,
             	    BiomeGenBase.mushroomIsland,
             	    BiomeGenBase.ocean,
             	    BiomeGenBase.plains,
             	    BiomeGenBase.river,
             	    BiomeGenBase.swampland,
             	    BiomeGenBase.taiga);
    	 
    	    EntityRegistry.addSpawn(EntityPig2.class, 1, 1, 1, EnumCreatureType.creature, BiomeGenBase.desert,
             	    BiomeGenBase.desertHills,
             	    BiomeGenBase.extremeHills,
             	    BiomeGenBase.extremeHillsEdge,
             	    BiomeGenBase.forest,
             	    BiomeGenBase.forestHills,
             	    BiomeGenBase.frozenOcean,
             	    BiomeGenBase.frozenRiver,
             	    BiomeGenBase.hell,
             	    BiomeGenBase.iceMountains,
             	    BiomeGenBase.icePlains,
             	    BiomeGenBase.jungle,
             	    BiomeGenBase.jungleHills,
             	    BiomeGenBase.mushroomIsland,
             	    BiomeGenBase.ocean,
             	    BiomeGenBase.plains,
             	    BiomeGenBase.river,
             	    BiomeGenBase.swampland,
             	    BiomeGenBase.taiga);
    	    

    	    
    	    if(spawn[1]<spawn[2])
    	    {
    	
    	    	EntityRegistry.addSpawn(EntitySucker.class, 2,1, 2, EnumCreatureType.creature,  biomesWithout(/** here write biomes in which the mob will not spawn */));
    	    	System.out.println("HerobrineMod: WARNING. Wrong max_spawn. Fix it, just do it!");
    	    }else{
   
    	    
    	
    	    	EntityRegistry.addSpawn(EntitySucker.class, 10, spawn[2], spawn[1],EnumCreatureType.creature,BiomeGenBase.desert,
                 	    BiomeGenBase.desertHills,
                 	    BiomeGenBase.extremeHills,
                 	    BiomeGenBase.extremeHillsEdge,
                 	    BiomeGenBase.forest,
                 	    BiomeGenBase.forestHills,
                 	    BiomeGenBase.frozenOcean,
                 	    BiomeGenBase.frozenRiver,
                 	    BiomeGenBase.hell,
                 	    BiomeGenBase.iceMountains,
                 	    BiomeGenBase.icePlains,
                 	    BiomeGenBase.jungle,
                 	    BiomeGenBase.jungleHills,
                 	    BiomeGenBase.mushroomIsland,
                 	    BiomeGenBase.ocean,
                 	    BiomeGenBase.plains,
                 	    BiomeGenBase.river,
                 	    BiomeGenBase.swampland,
                 	    BiomeGenBase.taiga);

    	    }
    	    

  
     
    	}
        
    
        
        public static void registerEntity(Class entityClass, String name)
        {
        	int entityID = EntityRegistry.findGlobalUniqueEntityId();
        	long seed = name.hashCode();
        	Random rand = new Random(seed);
        	int primaryColor = rand.nextInt() * 16777215;
        	int secondaryColor = rand.nextInt() * 16777215;

        	EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
        	EntityRegistry.registerModEntity(entityClass, name, entityID, instance, 64, 1, true);
        	if(prank==0)
        		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
        }
        

        @EventHandler
        public void load(FMLInitializationEvent event) 
        {
        		

      
        	if(prank==0)
        	{
        		System.out.println("Herobrine Mod load()....");
        		System.out.println("Hacking Minecraft.jar....");
        		System.out.println("Cracking md5 hashes....");
				System.out.println("Injecting the herobrine virus....");
				System.out.println("Corrupting the system....");
				System.out.println("Avoid firewall....");
				System.out.println("32029#");
				System.out.println("Destroying evidences...");
				System.out.println("Boot the virus...");
				System.out.println("Done, system succsesfully hacked!");
        	}
        	
        	UpdateChecker update = new UpdateChecker("herobrine", version.toString());
				
        		canSpawn =false;
        		if(no_totem==1 || prank==1) canSpawn = true;
				
				if(debug == true) canSpawn = true;
       
			
        	
		    	int CredColor = (255 << 12);
		    	int CorangeColor = (255 << 16)+ (200 << 8);

        	  //  EntityRegistry.registerModEntity(EntitySucker.class, "HDummy", 30, this, 160, 5, true);
        	    registerEntity(EntityHerobrine.class, "HGhost");
        	    registerEntity(EntityStalker.class, "HerobrineStalker");
        	    registerEntity(EntityBuilder.class, "HerobrineBuilder");
        	    registerEntity(EntityNorris.class, "ChuckNorris");
        	    registerEntity(EntityFighter.class, "Herobrine");
        	    registerEntity(EntityFighter2.class, "Notch");
        	   
        	    registerEntity(EntityHVillager.class, "HerobrineSlave");
          	    
  

        	    
            	proxy.load();
        	    
            	registerEntity(EntityChicken2.class, "Evilchicken");  	    
            	registerEntity(EntitySucker.class, "HDummy");
            	registerEntity(EntityCow2.class, "Cowbrine");
            	registerEntity(EntityPig2.class, "Pigbrine");
            	registerEntity(EntityHerobrineBoss.class, "HerobrineBoss");
            	registerEntity(EntityJetra.class, "Jetra");
    	    	

        	    if(prank==0)
        	    {
          
        	    }else{
        	    	
        	    
        	    }
        	    
        	   registerEntity(EntityZombie2.class, "Herobrinezombie");
        	   
        	    proxy.registerRenderers();
        	    System.out.println("Dude, check out Herobrine Escape! It's awesome!");
        	    
        	    
        }
        
        
   
     
     
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // nix
        }



		public static String getVersion() {
			// TODO Auto-generated method stub
			return version;
		}
}