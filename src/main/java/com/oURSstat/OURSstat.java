package com.oURSstat;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class OURSstat extends JavaPlugin {

    private PlayerDataManager PlayerDataManager;

    @Override
    public void onEnable() {
        getLogger().info("스텟 플러그인이 실행되었습니다.");

        PlayerDataManager = new PlayerDataManager(this);

        getCommand("메뉴").setExecutor(new menu());
        getCommand("스텟").setExecutor(new stat(PlayerDataManager));

        //사용 아이템 등록
        getServer().getPluginManager().registerEvents(new statmenu(PlayerDataManager), this);



        PlayerDataManager dataManager = new PlayerDataManager(this);

        // 반복 작업 시작 (1초마다 실행)
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    int stat = dataManager.getPlayerStat(player.getUniqueId());
                    AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                    if (maxHealth != null) {
                        maxHealth.setBaseValue(20.0 + stat);
                    }
                }
            }
        }.runTaskTimer(this, 0L, 20L); // 0틱 후 시작, 20틱(1초)마다 반복
    }

    @Override
    public void onDisable() {
        getLogger().info("서버가 종료됨에 따라 스텟 플러그인이 종료합니다.");
    }
}