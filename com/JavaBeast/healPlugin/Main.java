package com.JavaBeast.healPlugin;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements CommandExecutor, TabCompleter {

    private static ConsoleCommandSender cmd;

    @Override
    public void onEnable() {
        cmd = Bukkit.getConsoleSender();

        getCommand("heal").setExecutor(this);
        getCommand("heal").setTabCompleter(this);

        cmd.sendMessage("§7[§bHeal§7] §aStarted!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equals("heal")){
            if(args.length == 0){
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    player.setHealth(player.getMaxHealth());
                    player.setFoodLevel(20);
                    player.sendMessage("§7[§bHeal§7] §aYou have been healed!");
                }else{
                    sender.sendMessage("§7[§bHeal§7] §4You have to be a player to use this command.");
                }
                return true;
            }else if (args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null){
                    sender.sendMessage("§7[§bHeal§7] §4Player not found!");
                    return true;
                }

                target.setHealth(target.getMaxHealth());
                target.setFoodLevel(20);
                target.sendMessage("§7[§bHeal§7] §aYou have been healed!");
                sender.sendMessage("§7[§bHeal§7] §aTarget §6"+target.getName()+"§a has been healed!");
                return true;
            }else{
                sender.sendMessage("§7[§bHeal§7] §cUsage : /heal [<player>]");
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();

        if(args.length == 1){
            for(Player player : Bukkit.getOnlinePlayers()){
                list.add(player.getName());
            }
        }

        return list;
    }
}
