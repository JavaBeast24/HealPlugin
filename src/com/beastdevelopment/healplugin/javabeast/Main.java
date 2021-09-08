package com.beastdevelopment.healplugin.javabeast;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    static int maxHealth = 20;
    static int heal = 20;
    static int food = 20;

    static String prefix = "§7[§2HealPlugin§7] ";

    static String need_player = "§4This command can only be used by a player.";
    static String healed_player = "§2You got healed.";
    static String healed_player_by = "§2You got healed by §6<sender>.";
    static String healed_sender = "§2You have healed §6<player>.";
    static String player_not_online = "§4This player is not online.";
    static String health_wrong_usage = "§4usage: /heal [<player>].";

    @Override
    public void onEnable() {
        // region <config>
        FileConfiguration config = getConfig();

        if(config.contains("max_health")) {
            maxHealth = config.getInt("max_health");
        }else
            config.set("max_health", maxHealth);

        if(config.contains("heal")) {
            heal = config.getInt("heal");
        }else
            config.set("heal", heal);

        if(config.contains("food")) {
            food = config.getInt("food");
        }else
            config.set("food", food);

        if(config.contains("need_player")) {
            need_player = config.getString("need_player");
        }else
            config.set("need_player", need_player);

        if(config.contains("healed_player")) {
            healed_player = config.getString("healed_player");
        }else
            config.set("healed_player", healed_player);

        if(config.contains("healed_player_by")) {
            healed_player_by = config.getString("healed_player_by");
        }else
            config.set("healed_player_by", healed_player_by);

        if(config.contains("healed_sender")) {
            healed_sender = config.getString("healed_sender");
        }else
            config.set("healed_sender", healed_sender);

        if(config.contains("player_not_online")) {
            player_not_online = config.getString("player_not_online");
        }else
            config.set("player_not_online", player_not_online);

        if(config.contains("health_wrong_usage")) {
            health_wrong_usage = config.getString("health_wrong_usage");
        }else
            config.set("health_wrong_usage", health_wrong_usage);

        saveConfig();

        // endregion
        // region <commands>
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("heal").setTabCompleter(new HealCommand());
        // endregion
    }

}
