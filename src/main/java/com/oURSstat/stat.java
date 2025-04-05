package com.oURSstat;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//명령어 관련
public class stat implements CommandExecutor, Listener {

    private final PlayerDataManager dataManager;

    public stat(PlayerDataManager dataManager){
        this.dataManager = dataManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();

        //처음 접속한 유저일 경우에만 초기값 저장
        if (!dataManager.hasPlayerData(uuid)) {
            dataManager.savePlayerStat(uuid, 0);
            dataManager.savePlayerSTR(uuid, 0);
            dataManager.savePlayerWIS(uuid, 0);
            dataManager.savePlayerSEN(uuid, 0);
            dataManager.savePlayerVIT(uuid, 0);
            dataManager.savePlayerEND(uuid, 0);
            dataManager.savePlayerMND(uuid, 0);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings){
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "이 명령어는 플레이어만 사용할 수 있습니다.");
            return true;
        }
        Player p = (Player) commandSender;

        //힘 : STR / 지혜 : WIS / 오감 : SEN / 체력 : VIT / 지구력 : END / 정신력 : MND
        int stat = dataManager.getPlayerStat(p.getUniqueId());
        int STR = dataManager.getPlayerSTR(p.getUniqueId());
        int WIS = dataManager.getPlayerWIS(p.getUniqueId());
        int SEN = dataManager.getPlayerSEN(p.getUniqueId());
        int VIT = dataManager.getPlayerVIT(p.getUniqueId());
        int END = dataManager.getPlayerEND(p.getUniqueId());
        int MND = dataManager.getPlayerMND(p.getUniqueId());

        if(strings.length == 0){
            statmenu.openStatmenu(p, dataManager);
            return true;
        }else if(!p.isOp() && strings.length >= 1){
            p.sendMessage("명령어를 올바르게 입력해주세요.");
            p.sendMessage("올바른 명령어 : /스텟");
        }

        if(p.isOp()) {
            if (strings[0].equals("도움말")) {
                p.sendMessage("[ 스텟 관련 명령어 ]");
                p.sendMessage("1. /스텟 도움말");
                p.sendMessage("   : 스텟 관련 명령어를 확인합니다.");
                p.sendMessage("2. /스텟 증가 [종류] [변수] [플레이어]");
                p.sendMessage("   : 플레이어에게 [종류]의 스텟을 [변수]만큼 줍니다.");
                p.sendMessage("   [스텟 종류] 스텟, 힘, 지혜, 오감, 체력, 지구력, 정신력");
                p.sendMessage("3. /스텟 전체초기화 [플레이어]");
                p.sendMessage("   : 플레이어의 모든 스텟을 초기화 합니다.");
                p.sendMessage("3. /스텟 초기화 [플레이어]");
                p.sendMessage("   : 플레이어의 모든 스텟을 잔여스텟으로 치환합니다.");
                p.sendMessage("4. /스텟 스크롤 [변수]");
                p.sendMessage("   : 우클릭시 [변수]만큼 스텟을 얻는 아이템을 만들어 냅니다.");
            } else if (strings[0].equals("증가")) {
                if (strings.length < 2) {
                    p.sendMessage("스텟 [종류]를 입력해주세요.");
                    p.sendMessage("올바른 명령어 : /스텟 증가 [종류] [변수] [플레이어]");
                    return true;
                }
                if (strings.length < 3) {
                    p.sendMessage("[변수] 값을 입력해주세요.");
                    p.sendMessage("올바른 명령어 : /스텟 증가 [종류] [변수] [플레이어]");
                    return true;
                }
                if (strings.length < 4) {
                    p.sendMessage("[플레이어] 이름을 입력해주세요.");
                    p.sendMessage("올바른 명령어 : /스텟 증가 [종류] [변수] [플레이어]");
                    return true;
                }

                int index = Integer.parseInt(strings[2]);
                Player sp = Bukkit.getPlayer(strings[3]);

                if (strings[1].equals("스텟")) {
                    if (index > 0) {
                        if (sp == null) {
                            p.sendMessage("플레이어의 이름이 틀렸거나 현제 서버에 존재하지 않습니다.");
                            return true;
                        }
                        dataManager.savePlayerStat(sp.getUniqueId(), stat + index);
                        p.sendMessage("[ " + sp.getName() + " ] 님의 잔여 스텟을 " + index + " 만큼 증가 시켰습니다.");
                        return true;
                    } else {
                        p.sendMessage("0 이상의 정수 값만을 입력하세요.");
                        return true;
                    }
                } else if (strings[1].equals("힘")) {
                    if (index > 0) {
                        if (sp == null) {
                            p.sendMessage("플레이어의 이름이 틀렸거나 현제 서버에 존재하지 않습니다.");
                            return true;
                        }
                        dataManager.savePlayerSTR(sp.getUniqueId(), STR + index);
                        p.sendMessage("[ " + sp.getName() + " ] 님의 힘 스텟을 " + index + " 만큼 증가 시켰습니다.");
                        return true;
                    } else {
                        p.sendMessage("0 이상의 정수 값만을 입력하세요.");
                        return true;
                    }
                } else if (strings[1].equals("지혜")) {
                    if (index > 0) {
                        if (sp == null) {
                            p.sendMessage("플레이어의 이름이 틀렸거나 현제 서버에 존재하지 않습니다.");
                            return true;
                        }
                        dataManager.savePlayerWIS(sp.getUniqueId(), WIS + index);
                        p.sendMessage("[ " + sp.getName() + " ] 님의 지혜 스텟을 " + index + " 만큼 증가 시켰습니다.");
                        return true;
                    } else {
                        p.sendMessage("0 이상의 정수 값만을 입력하세요.");
                        return true;
                    }
                } else if (strings[1].equals("오감")) {
                    if (index > 0) {
                        if (sp == null) {
                            p.sendMessage("플레이어의 이름이 틀렸거나 현제 서버에 존재하지 않습니다.");
                            return true;
                        }
                        dataManager.savePlayerSEN(sp.getUniqueId(), SEN + index);
                        p.sendMessage("[ " + sp.getName() + " ] 님의 오감 스텟을 " + index + " 만큼 증가 시켰습니다.");
                        return true;
                    } else {
                        p.sendMessage("0 이상의 정수 값만을 입력하세요.");
                        return true;
                    }
                } else if (strings[1].equals("체력")) {
                    if (index > 0) {
                        if (sp == null) {
                            p.sendMessage("플레이어의 이름이 틀렸거나 현제 서버에 존재하지 않습니다.");
                            return true;
                        }
                        dataManager.savePlayerVIT(sp.getUniqueId(), VIT + index);
                        p.sendMessage("[ " + sp.getName() + " ] 님의 체력 스텟을 " + index + " 만큼 증가 시켰습니다.");
                        return true;
                    } else {
                        p.sendMessage("0 이상의 정수 값만을 입력하세요.");
                        return true;
                    }
                } else if (strings[1].equals("지구력")) {
                    if (index > 0) {
                        if (sp == null) {
                            p.sendMessage("플레이어의 이름이 틀렸거나 현제 서버에 존재하지 않습니다.");
                            return true;
                        }
                        dataManager.savePlayerEND(sp.getUniqueId(), END + index);
                        p.sendMessage("[ " + sp.getName() + " ] 님의 지구력 스텟을 " + index + " 만큼 증가 시켰습니다.");
                        return true;
                    } else {
                        p.sendMessage("0 이상의 정수 값만을 입력하세요.");
                        return true;
                    }
                } else if (strings[1].equals("정신력")) {
                    if (index > 0) {
                        if (sp == null) {
                            p.sendMessage("플레이어의 이름이 틀렸거나 현제 서버에 존재하지 않습니다.");
                            return true;
                        }
                        dataManager.savePlayerMND(sp.getUniqueId(), MND + index);
                        p.sendMessage("[ " + sp.getName() + " ] 님의 정신력 스텟을 " + index + " 만큼 증가 시켰습니다.");
                        return true;
                    } else {
                        p.sendMessage("0 이상의 정수 값만을 입력하세요.");
                        return true;
                    }
                }
            } else if (strings[0].equals("전체초기화")) {
                if (strings.length < 2) {
                    p.sendMessage("[플레이어] 이름을 입력해주세요.");
                    p.sendMessage("올바른 명령어 : /스텟 전체초기화 [플레이어]");
                    return true;
                }
                Player sp = Bukkit.getPlayer(strings[1]);
                if (sp == null) {
                    p.sendMessage("플레이어의 이름이 틀렸거나 현제 서버에 존재하지 않습니다.");
                    return true;
                }
                dataManager.savePlayerStat(sp.getUniqueId(), 0);
                dataManager.savePlayerSTR(sp.getUniqueId(), 0);
                dataManager.savePlayerWIS(sp.getUniqueId(), 0);
                dataManager.savePlayerSEN(sp.getUniqueId(), 0);
                dataManager.savePlayerVIT(sp.getUniqueId(), 0);
                dataManager.savePlayerEND(sp.getUniqueId(), 0);
                dataManager.savePlayerMND(sp.getUniqueId(), 0);
                p.sendMessage("[ " + sp.getName() + " ] 님의 모든 스텟을 0으로 초기화 시켰습니다.");
                return true;
            } else if (strings[0].equals("초기화")) {
                if (strings.length < 2) {
                    p.sendMessage("[플레이어] 이름을 입력해주세요.");
                    p.sendMessage("올바른 명령어 : /스텟 초기화 [플레이어]");
                    return true;
                }
                Player sp = Bukkit.getPlayer(strings[1]);
                if (sp == null) {
                    p.sendMessage("플레이어의 이름이 틀렸거나 현제 서버에 존재하지 않습니다.");
                    return true;
                }
                int pn = STR + WIS + SEN + VIT + END + MND + stat;
                dataManager.savePlayerStat(sp.getUniqueId(), pn);
                dataManager.savePlayerSTR(sp.getUniqueId(), 0);
                dataManager.savePlayerWIS(sp.getUniqueId(), 0);
                dataManager.savePlayerSEN(sp.getUniqueId(), 0);
                dataManager.savePlayerVIT(sp.getUniqueId(), 0);
                dataManager.savePlayerEND(sp.getUniqueId(), 0);
                dataManager.savePlayerMND(sp.getUniqueId(), 0);
                p.sendMessage("[ " + sp.getName() + " ] 님의 스텟을 초기화 시켰습니다.");
                return true;
            } else if (strings[0].equals("스크롤")) {
                if (strings.length < 2) {
                    p.sendMessage("[변수]를 입력해주세요.");
                    p.sendMessage("올바른 명령어 : /스텟 스크롤 [변수]");
                    return true;
                }

                int index = Integer.parseInt(strings[1]);
                if (index > 0) {
                    ItemStack scroll = new ItemStack(Material.PAPER);
                    ItemMeta meta = scroll.getItemMeta();
                    if (meta != null) {
                        meta.setDisplayName("&f[ 추가 스텟 포인트 ]");
                        List<String> lore = new ArrayList<>();
                        lore.add("&f추가 스텟 : " + index);
                        meta.setLore(lore);
                        meta.setCustomModelData(1000);
                        scroll.setItemMeta(meta);
                    }
                    p.getInventory().addItem(scroll);
                    p.sendMessage("스텟 포인트 " + index + " 만큼 증가시키는 스크롤을 만들었습니다.");
                    return true;
                } else {
                    p.sendMessage("0 이상의 정수 값만을 입력하세요.");
                    return true;
                }
            } else {
                p.sendMessage("명령어를 올바르게 입력해주세요.");
                p.sendMessage("올바른 명령어 : /스텟");
            }
        }

        AttributeInstance maxh = p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if(maxh != null){
            maxh.setBaseValue(20.0 + stat);
        }

        return true;
    }
}
