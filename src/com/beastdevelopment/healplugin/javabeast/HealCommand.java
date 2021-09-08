package com.beastdevelopment.healplugin.javabeast;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HealCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(args.length == 0) {
                player.setMaxHealth(Main.maxHealth);
                player.setHealth(Main.heal);
                player.setFoodLevel(Main.food);

                player.sendMessage(Main.prefix + Main.healed_player);
            }else if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if(target == null) {
                    player.sendMessage(Main.prefix+Main.player_not_online);
                    return true;
                }

                target.setMaxHealth(Main.maxHealth);
                target.setHealth(Main.heal);
                target.setFoodLevel(Main.food);

                target.sendMessage(Main.prefix+Main.healed_player_by.
                        replace("<player>", target.getName()).
                        replace("<sender>", player.getName())
                );

                player.sendMessage(Main.prefix+Main.healed_sender.
                        replace("<player>", target.getName()).
                        replace("<sender>", player.getName())
                );
            }else
                player.sendMessage(Main.prefix+Main.health_wrong_usage);

        }else
            sender.sendMessage(Main.prefix+ Main.need_player);

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> list = new ArrayList<>();

        if(strings.length == 1) {
            for(Player p:Bukkit.getOnlinePlayers()) {
                list.add(p.getName());
            }
        }

        return list;
    }
}
