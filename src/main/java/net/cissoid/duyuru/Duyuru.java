package net.cissoid.duyuru;

import net.cissoid.duyuru.constants.Messages;
import net.cissoid.duyuru.util.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class Duyuru extends JavaPlugin {

    private static Configuration configuration;

    @Override
    public void onEnable() {
        Duyuru.configuration = new Configuration("config.yml", this);

        this.setCommandExecutors();

        try {
            Class.forName(Messages.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Messages.updateMessageByConfig(configuration);
        Messages.PLUGIN_ENABLE.log();
    }

    @Override
    public void onDisable() {
        Messages.PLUGIN_DISABLE.log();
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    private void setCommandExecutors() {
        getCommand("duyuru").setExecutor(new net.cissoid.duyuru.commands.Duyuru());
    }

}
