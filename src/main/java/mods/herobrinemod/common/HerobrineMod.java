package mods.herobrinemod.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.herobrinemod.common.mobs.EntityEvilChicken;
import mods.herobrinemod.common.mobs.EntityEvilCow;
import mods.herobrinemod.common.mobs.EntityEvilPig;
import mods.herobrinemod.common.mobs.EntityEvilVillager;
import mods.herobrinemod.common.mobs.EntityEvilZombie;
import mods.herobrinemod.common.mobs.EntityNorris;
import mods.herobrinemod.common.mobs.herobrine.EntityBuilder;
import mods.herobrinemod.common.mobs.herobrine.EntityDummySpawner;
import mods.herobrinemod.common.mobs.herobrine.EntityFighter;
import mods.herobrinemod.common.mobs.herobrine.EntityFighterNotch;
import mods.herobrinemod.common.mobs.herobrine.EntityHerobrine;
import mods.herobrinemod.common.mobs.herobrine.EntityHerobrineBoss;
import mods.herobrinemod.common.mobs.herobrine.EntityStalker;

@SuppressWarnings({ "SpellCheckingInspection", "unused" })
@Mod(modid = "herobrinemod", name = "HerobrineMod", version = "3.7")

public class HerobrineMod {

    public static Logger logger = LogManager.getLogger("herobrinemod");

    public static String version = "4.0";

    public static int buildingCount = 22;

    public static int trapCount = 12;
    public static boolean canSpawn = false;

    public static Block totem;

    public static Item itsHerobrine;
    public static Item seeherobrine;

    // public static final Item seeHerobrine;

    // The instance of your mod that Forge uses.
    @Instance("herobrinemod")
    public static HerobrineMod instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(
        clientSide = "mods.herobrinemod.client.ClientProxy",
        serverSide = "mods.herobrinemod.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        Config.load(event.getSuggestedConfigurationFile());

        HerobrineMod.totem = new BlockHerobrine(Material.rock);
        GameRegistry.registerTileEntity(TileEntityTotem.class, "HerobrineTotem");
        itsHerobrine = new ItemHDisc("seeherobrine", "gothtigger92 - Have You Seen the Herobrine")
            .setUnlocalizedName("seeherobrine")
            .setTextureName("herobrinemod:record_seeherobrine");
        seeherobrine = new ItemHDisc("itsherobrine", "Bla Bla Gaming - It's Herobrine")
            .setUnlocalizedName("itsherobrine")
            .setTextureName("herobrinemod:record_ctune");
        GameRegistry.registerItem(
            itsHerobrine,
            itsHerobrine.getUnlocalizedName()
                .substring(5));
        GameRegistry.registerItem(
            seeherobrine,
            seeherobrine.getUnlocalizedName()
                .substring(5));

        EntityRegistry.addSpawn(
            EntityEvilChicken.class,
            1,
            1,
            2,
            EnumCreatureType.creature,
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

        EntityRegistry.addSpawn(
            EntityEvilCow.class,
            2,
            1,
            4,
            EnumCreatureType.creature,
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

        EntityRegistry.addSpawn(
            EntityEvilPig.class,
            1,
            1,
            1,
            EnumCreatureType.creature,
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
        EntityRegistry.addSpawn(
            EntityDummySpawner.class,
            10,
            Config.spawn[2],
            Config.spawn[1],
            EnumCreatureType.creature,
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
    }

    public static void registerEntity(Class<? extends Entity> entityClass, String name) {
        final int entityID = EntityRegistry.findGlobalUniqueEntityId();
        final long seed = name.hashCode();
        final Random rand = new Random(seed);
        final int primaryColor = rand.nextInt() * 16777215;
        final int secondaryColor = rand.nextInt() * 16777215;

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
        EntityRegistry.registerModEntity(entityClass, name, entityID, instance, 64, 1, true);
        if (Config.prank) {
            EntityList.entityEggs.put(entityID, new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
        }
    }

    @EventHandler
    public void load(FMLInitializationEvent event) {

        if (Config.prank) {
            System.out.println("Herobrine Mod load()....");
            System.out.println("Hacking Minecraft.jar....");
            System.out.println("Cracking md5 hashes....");
            System.out.println("Injecting the herobrine virus....");
            System.out.println("Corrupting the system....");
            System.out.println("Avoid firewall....");
            System.out.println("32029#");
            System.out.println("Destroying evidences...");
            System.out.println("Boot the virus...");
            System.out.println("Done, system successfully hacked!");
        }
        canSpawn = Config.no_totem || Config.prank;

        if (Config.debug) {
            canSpawn = true;
        }
        proxy.load();

        registerEntity(EntityHerobrine.class, "HGhost");
        registerEntity(EntityStalker.class, "HerobrineStalker");
        registerEntity(EntityBuilder.class, "HerobrineBuilder");
        registerEntity(EntityNorris.class, "ChuckNorris");
        registerEntity(EntityFighter.class, "Herobrine");
        registerEntity(EntityFighterNotch.class, "Notch");
        registerEntity(EntityDummySpawner.class, "HDummy");
        registerEntity(EntityHerobrineBoss.class, "HerobrineBoss");

        registerEntity(EntityEvilChicken.class, "EvilChicken");
        registerEntity(EntityEvilCow.class, "EvilCow");
        registerEntity(EntityEvilPig.class, "EvilPig");
        registerEntity(EntityEvilVillager.class, "EvilVillager");
        registerEntity(EntityEvilZombie.class, "EvilZombie");

        proxy.registerRenderers();
        System.out.println("Dude, check out Herobrine Escape! It's awesome!");

    }

    public static String getVersion() {
        // TODO Auto-generated method stub
        return version;
    }
}
