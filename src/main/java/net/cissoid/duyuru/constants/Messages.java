package net.cissoid.duyuru.constants;

import net.cissoid.duyuru.util.Configuration;
import net.cissoid.duyuru.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.UnsupportedEncodingException;

public enum Messages {

    PLUGIN_PREFIX("&6Duyuru &8> "),
    PLUGIN_ENABLE("&aEklenti aktif."),
    PLUGIN_DISABLE("&cEklenti pasif."),
    FAILURE_SYNTAX("&cBu komudu kullanmak için yazı girmeniz lazım."),
    FAILURE_PERMISSION("&cBu komut için gerekli yetkiniz yok.");

    public String message;

    Messages(String message) {
        this.message = Utils.color(message);
    }

    public String toString() {
        return message;
    }

    public void log() {
        send(Bukkit.getConsoleSender());
    }

    public void send(CommandSender sender) {
        sender.sendMessage(PLUGIN_PREFIX + message);
    }

    public static void updateMessageByConfig(Configuration configuration) {
        configuration.load();

        FileConfiguration config = configuration.getConfig();

        for(Messages message : Messages.values()) {
            String paths = message.name().toLowerCase();
            String path = "";

            for (String string : paths.split("_")) {
                path += String.valueOf(string.charAt(0)).toUpperCase() + string.substring(1, string.length()) + ".";
            }

            path = path.substring(0, path.length() - 1).replaceAll("ı", "i");

            if(config.contains(path)) {
                String unicode = config.getString(path);
                try {
                    message.message = Utils.color(new String(unicode.getBytes("ISO-8859-1"),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
