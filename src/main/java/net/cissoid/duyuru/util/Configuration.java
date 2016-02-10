package net.cissoid.duyuru.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Configuration {

    File configFile;
    FileConfiguration config;

    public Configuration(String file, JavaPlugin plugin) {
        configFile = new File(plugin.getDataFolder(), file);
        config = new YamlConfiguration();

        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();

            try {
                copy(plugin.getResource(file), configFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void load() {
        try {
            config.load(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
