package com.oURSstat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class menu implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player p = (Player) commandSender;

        Inventory menu = Bukkit.createInventory(null, 54, ChatColor.WHITE + "\uEBBB\uEBBB\uEBBB\uEBBB\uEBBB\uEBBB\uEBBB\uEAAA");

        if(strings.length == 0){
            p.openInventory(menu);
        }else{
            p.sendMessage(ChatColor.WHITE + "올바른 명령어는 /메뉴 입니다.");
        }
        return true;
    }
}
