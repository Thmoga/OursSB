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

                    int END = dataManager.getPlayerEND(player.getUniqueId());
                    int VIT = dataManager.getPlayerVIT(player.getUniqueId());
                    int STR = dataManager.getPlayerSTR(player.getUniqueId());
                    int MND = dataManager.getPlayerMND(player.getUniqueId());
                    int SEN = dataManager.getPlayerSEN(player.getUniqueId());
                    int WIS = dataManager.getPlayerWIS(player.getUniqueId());

                    double END_at = END * 0.02;
                    double END_toat = END;
                    double STR_D = STR * 0.5;

                    //힘 스텟
                    AttributeInstance attackAttr = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                    if (attackAttr != null) attackAttr.setBaseValue(STR_D);

                    //지구력 스텟
                    AttributeInstance END_Armor_at = player.getAttribute(Attribute.GENERIC_ARMOR);
                    AttributeInstance END_Armor_toat = player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS);
                    if(END_Armor_at != null) END_Armor_at.setBaseValue(END_at);
                    if(END_Armor_toat != null) END_Armor_toat.setBaseValue(END_toat);

                    //체력 스텟
                    AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                    if (maxHealth != null) {
                        maxHealth.setBaseValue(20.0 + VIT);
                    }
                }
            }
        }.runTaskTimer(this, 0L, 5L); // 0틱 후 시작, 5틱(0.25초)마다 반복
    }

    @Override
    public void onDisable() {
        getLogger().info("서버가 종료됨에 따라 스텟 플러그인이 종료합니다.");
    }
}