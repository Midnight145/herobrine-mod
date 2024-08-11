package mods.HerobrineMod.UpdateChecker;


import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
 
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
 
public class UpdateChecker implements Notifier
{
 
        private boolean checkUpdate = true;
        String mod, version;
 
        public UpdateChecker(String mod, String version)
        {
                this.mod = mod;
                this.version = version;
        }
 
        public static void checkVersion(final String mod, final String version, final Notifier notifier)
        {
                Thread check = new Thread(new Runnable() {
 
                        @Override
                        public void run()
                        {
                                try
                                {
                                        URL u = new URL("https://dl.dropboxusercontent.com/u/4695223/herobrine/versions.txt");
                                        InputStream in = u.openStream();
                                        Properties p = new Properties();
                                        p.load(in);
                                        String newVersion = p.getProperty(mod);
                                        if (version.compareTo(newVersion) != 0)
                                        {
                                                notifier.notifyOnUpdate();
                                        }
 
                                } catch (Exception e)
                                {
                                }
 
                        }
                });
                check.start();
        }
 
       
 
        @SubscribeEvent
        public void tickStart(ServerTickEvent event)
        {
                if (checkUpdate)
                {
                        checkVersion(mod, version, this);
                        checkUpdate = false;
                }
 
        }
 
        @Override
        public void notifyOnUpdate()
        {
                MinecraftServer.getServer().logInfo("New update available for " + mod + "!");
        }
 
       
}
