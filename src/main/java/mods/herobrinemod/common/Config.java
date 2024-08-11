package mods.herobrinemod.common;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;

import cpw.mods.fml.common.FMLLog;
import mods.herobrinemod.common.mobs.EntityEvilChicken;
import mods.herobrinemod.common.mobs.EntityEvilCow;
import mods.herobrinemod.common.mobs.EntityEvilPig;
import mods.herobrinemod.common.mobs.EntityEvilVillager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class Config {

    public static HashMap<Class<?>, Constructor<?>> mappings = new HashMap<>();

	public static boolean kick;
	public static boolean setOnFire;
	public static boolean dropTorch;
	public static boolean dropper;

    public static boolean spawnEvilMobs;

	public static boolean animal_circle;
	public static int[] spawn = new int[3];

	public static boolean placeFire;
	public static boolean placeLava;
	public static boolean placeWater;
	public static boolean placeBuildings;
	public static boolean placeTraps;
	public static boolean placeGlass;
	public static boolean placeTnt;

	public static boolean no_totem;
	public static boolean mini_mode;
	public static boolean prank;
	public static boolean extremeRare;

	public static boolean debug;

    public static void load(File file) {
		final Configuration configuration = new Configuration(file);

		try {
			configuration.load();

			spawn[0] = configuration.getInt("spawn", Configuration.CATEGORY_GENERAL, 30, 1, 60, "");
			spawn[1] = configuration.getInt("max_spawn", Configuration.CATEGORY_GENERAL, 7, 2, 20, "");
			spawn[2] = configuration.getInt("min_spawn", Configuration.CATEGORY_GENERAL, 2, 1, 5, "");

			animal_circle = configuration.getBoolean("animal_circle", Configuration.CATEGORY_GENERAL, true, "");
			prank = configuration.getBoolean("prank", Configuration.CATEGORY_GENERAL, true, "");
            spawnEvilMobs = configuration.getBoolean("spawnEvilMobs", Configuration.CATEGORY_GENERAL, true, "");
            placeFire = configuration.getBoolean("place_fire", Configuration.CATEGORY_GENERAL, true, "");
			placeLava = configuration.getBoolean("place_lava", Configuration.CATEGORY_GENERAL, true, "");
			placeBuildings = configuration.getBoolean("place_buildings", Configuration.CATEGORY_GENERAL, true, "");
			placeTraps = configuration.getBoolean("place_traps", Configuration.CATEGORY_GENERAL, true, "");
			placeGlass = configuration.getBoolean("place_traps", Configuration.CATEGORY_GENERAL, true, "");
			placeTnt = configuration.getBoolean("place_tnt", Configuration.CATEGORY_GENERAL, true, "");
			placeWater = configuration.getBoolean("place_water", Configuration.CATEGORY_GENERAL, true, "");
			setOnFire = configuration.getBoolean("set_player_on_fire", Configuration.CATEGORY_GENERAL, true, "");
			dropTorch = configuration.getBoolean("drop_torches", Configuration.CATEGORY_GENERAL, true, "");
			dropper = configuration.getBoolean("drop_player_items_on_floor", Configuration.CATEGORY_GENERAL, true, "");
			kick = configuration.getBoolean("Roundhousekick", Configuration.CATEGORY_GENERAL, true, "");
			extremeRare = configuration.getBoolean("extremraremode", Configuration.CATEGORY_GENERAL, true, "");
			mini_mode = configuration.getBoolean("mini_mode", Configuration.CATEGORY_GENERAL, true, "");
			no_totem = configuration.getBoolean("no_totem_mode", Configuration.CATEGORY_GENERAL, true, "");
			debug = configuration.getBoolean("debug", Configuration.CATEGORY_GENERAL, true, "");
			if (Config.spawn[1] < Config.spawn[2]) {
				int tmp = Config.spawn[2];
				Config.spawn[2] = Config.spawn[1];
				Config.spawn[1] = tmp;
			}
		}
		catch (final Exception e) {
			FMLLog.severe(e.getMessage());
		}
		finally {
			configuration.save();
		}


        try {
            mappings.put(EntityPig.class, EntityEvilPig.class.getConstructor(World.class));
            mappings.put(EntityCow.class, EntityEvilCow.class.getConstructor(World.class));
            mappings.put(EntityChicken.class, EntityEvilChicken.class.getConstructor(World.class));
            mappings.put(EntityVillager.class, EntityEvilVillager.class.getConstructor(World.class));
        } catch (NoSuchMethodException ignored) {}
	}
}
