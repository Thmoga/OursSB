package com.oURSstat;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataManager {

    private final JavaPlugin plugin;
    private File playerDataFile;
    private FileConfiguration playerDataConfig;

    public PlayerDataManager(JavaPlugin plugin){
        this.plugin = plugin;
        setup();
    }

    private void setup(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        playerDataFile = new File(plugin.getDataFolder(), "playerdata.yml");

        if(!playerDataFile.exists()){
            try{
                playerDataFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    public boolean hasPlayerData(UUID uuid) {
        // 스텟 데이터 유무 파악
        return playerDataConfig.contains(uuid.toString() + ".STR");
    }


    public void savePlayerStat(UUID uuid, int stat){
        //힘 : STR / 지혜 : WIS / 오감 : SEN / 체력 : VIT / 지구력 : END / 정신력 : MND
        String name = Bukkit.getOfflinePlayer(uuid).getName();
        playerDataConfig.set(uuid.toString() + ".name", name);
        playerDataConfig.set(uuid.toString() + ".stat", stat);
        saveFile();
    }
    public void savePlayerSTR(UUID uuid, int STR){
        //힘 : STR / 지혜 : WIS / 오감 : SEN / 체력 : VIT / 지구력 : END / 정신력 : MND
        playerDataConfig.set(uuid.toString() + ".STR(힘)", STR);
        saveFile();
    }
    public void savePlayerWIS(UUID uuid, int WIS){
        //힘 : STR / 지혜 : WIS / 오감 : SEN / 체력 : VIT / 지구력 : END / 정신력 : MND
        playerDataConfig.set(uuid.toString() + ".WIS(지혜)", WIS);
        saveFile();
    }
    public void savePlayerSEN(UUID uuid,int SEN){
        //힘 : STR / 지혜 : WIS / 오감 : SEN / 체력 : VIT / 지구력 : END / 정신력 : MND
        playerDataConfig.set(uuid.toString() + ".SEN(오감)", SEN);
        saveFile();
    }
    public void savePlayerVIT(UUID uuid,int VIT){
        //힘 : STR / 지혜 : WIS / 오감 : SEN / 체력 : VIT / 지구력 : END / 정신력 : MND
        playerDataConfig.set(uuid.toString() + ".VIT(체력)", VIT);
        saveFile();
    }
    public void savePlayerEND(UUID uuid,int END){
        //힘 : STR / 지혜 : WIS / 오감 : SEN / 체력 : VIT / 지구력 : END / 정신력 : MND
        playerDataConfig.set(uuid.toString() + ".END(지구력)", END);
        saveFile();
    }
    public void savePlayerMND(UUID uuid,int MND){
        //힘 : STR / 지혜 : WIS / 오감 : SEN / 체력 : VIT / 지구력 : END / 정신력 : MND
        playerDataConfig.set(uuid.toString() + ".MND(정신력)", MND);
        saveFile();
    }
    public int getPlayerStat(UUID uuid){
        return playerDataConfig.getInt(uuid.toString() + ".stat", 0);
    }
    public int getPlayerSTR(UUID uuid){
        return playerDataConfig.getInt(uuid.toString() + ".STR(힘)", 0);
    }
    public int getPlayerWIS(UUID uuid){
        return playerDataConfig.getInt(uuid.toString() + ".WIS(지혜)", 0);
    }
    public int getPlayerSEN(UUID uuid){
        return playerDataConfig.getInt(uuid.toString() + ".SEN(오감)", 0);
    }
    public int getPlayerVIT(UUID uuid){
        return playerDataConfig.getInt(uuid.toString() + ".VIT(체력)", 0);
    }
    public int getPlayerEND(UUID uuid){
        return playerDataConfig.getInt(uuid.toString() + ".END(지구력)", 0);
    }
    public int getPlayerMND(UUID uuid){
        return playerDataConfig.getInt(uuid.toString() + "..MND(정신력)", 0);
    }
    public String getPlayername(UUID uuid){
        return playerDataConfig.getString(uuid.toString() + ".name", "알 수 없음");
    }
    private void saveFile() {
        try{
            playerDataConfig.save(playerDataFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
