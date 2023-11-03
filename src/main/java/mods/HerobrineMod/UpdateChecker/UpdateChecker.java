package mods.HerobrineMod.UpdateChecker;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraft.server.MinecraftServer;

public class UpdateChecker implements Notifier {
	
	private boolean checkUpdate = true;
	String mod, version;
	
	public UpdateChecker(String mod, String version) {
		this.mod = mod;
		this.version = version;
	}
	
	public static void checkVersion(final String mod, final String version, final Notifier notifier) {
		final Thread check = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					final URL u = new URL("https://dl.dropboxusercontent.com/u/4695223/herobrine/versions.txt");
					final InputStream in = u.openStream();
					final Properties p = new Properties();
					p.load(in);
					final String newVersion = p.getProperty(mod);
					if (version.compareTo(newVersion) != 0) {
						notifier.notifyOnUpdate();
					}
					
				}
				catch (final Exception e) {}
				
			}
		});
		check.start();
	}
	
	@SubscribeEvent
	public void tickStart(ServerTickEvent event) {
		if (this.checkUpdate) {
			checkVersion(this.mod, this.version, this);
			this.checkUpdate = false;
		}
		
	}
	
	@Override
	public void notifyOnUpdate() { MinecraftServer.getServer().logInfo("New update available for " + this.mod + "!"); }
	
}
