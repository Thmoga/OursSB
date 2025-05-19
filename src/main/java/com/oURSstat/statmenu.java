package com.oURSstat;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

//gui 및 아이템 관련
public class statmenu implements Listener {
    private final PlayerDataManager dataManager;

    public statmenu(PlayerDataManager dataManager) { this.dataManager = dataManager; }

    //스크롤 아이템 부분
    @EventHandler
    public void useScroll(PlayerInteractEvent e){
        if(e.getAction().toString().contains("RIGHT_CLICK")){
            Player p = e.getPlayer();
            ItemStack i = e.getItem();

            int s = dataManager.getPlayerStat(p.getUniqueId());

            if(i == null || i.getType() != Material.PAPER) return;
            if(!i.hasItemMeta()) return;

            ItemMeta m = i.getItemMeta();
            if(m == null || !m.hasCustomModelData()) return;

            if(m.getCustomModelData() != 1000) return;

            List<String> l = m.getLore();
            if(l == null || l.isEmpty()) return;

            String line = l.get(0).replaceAll("[^0-9]","");

            int sb = 0;
            try{
                sb = Integer.parseInt(line);
            }catch(NumberFormatException ex){
                p.sendMessage("아이템 정보가 잘못되었습니다.");
                return;
            }
            int current = dataManager.getPlayerStat(p.getUniqueId());
            dataManager.savePlayerStat(p.getUniqueId(), current + sb);
            p.sendMessage(p.getName() + " 님의 잔여 스텟이 " + sb + " 만큼 증가했습니다.");
            i.setAmount(i.getAmount() - 1);
            e.setCancelled(true);
        }
    }

    //메뉴 부분
    public static void openStatmenu(Player p, PlayerDataManager dataManager) {

        Inventory statmenugui = Bukkit.createInventory(null, 54, p.getName() + "의 스텟창");

        //gui 보일것
        ItemStack stattype_stat = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_stat = stattype_stat.getItemMeta();
        List<String> loretype_stat = new ArrayList<>();
        statmetatype_stat.setDisplayName("[ 잔여 스텟 ]");
        loretype_stat.add("현재 남아있는 스텟을 확인합니다.");
        int stat = dataManager.getPlayerStat(p.getUniqueId());
        loretype_stat.add("잔여 스텟 : " + stat);
        statmetatype_stat.setLore(loretype_stat);
        statmetatype_stat.setCustomModelData(1000);
        stattype_stat.setItemMeta(statmetatype_stat);
        statmenugui.setItem(40, stattype_stat);

        ItemStack stattype_statn = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_statn = stattype_statn.getItemMeta();
        statmetatype_statn.setDisplayName(" ");
        int statn = dataManager.getPlayerStat(p.getUniqueId());
        statmetatype_statn.setCustomModelData(2000 + statn);
        stattype_statn.setItemMeta(statmetatype_statn);
        statmenugui.setItem(49, stattype_statn);

        ItemStack stattype_STR = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_STR = stattype_STR.getItemMeta();
        List<String> loretype_STR = new ArrayList<>();
        statmetatype_STR.setDisplayName("[ 힘 스텟 ]");
        loretype_STR.add("현재 적용된 힘 스텟을 확인합니다.");
        int STR = dataManager.getPlayerSTR(p.getUniqueId());
        loretype_STR.add("힘 스텟 : " + STR);
        statmetatype_STR.setLore(loretype_STR);
        statmetatype_STR.setCustomModelData(1001);
        stattype_STR.setItemMeta(statmetatype_STR);
        statmenugui.setItem(1, stattype_STR);

        ItemStack stattype_STRn = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_STRn = stattype_STRn.getItemMeta();
        statmetatype_STRn.setDisplayName(" ");
        int STRn = dataManager.getPlayerSTR(p.getUniqueId());
        statmetatype_STRn.setCustomModelData(2000 + STRn);
        stattype_STRn.setItemMeta(statmetatype_STRn);
        statmenugui.setItem(10, stattype_STRn);

        ItemStack stattype_WIS = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_WIS = stattype_WIS.getItemMeta();
        List<String> loretype_WIS = new ArrayList<>();
        statmetatype_WIS.setDisplayName("[ 지혜 스텟 ]");
        loretype_WIS.add("현재 적용된 지혜 스텟을 확인합니다.");
        int WIS = dataManager.getPlayerWIS(p.getUniqueId());
        loretype_WIS.add("지혜 스텟 : " + WIS);
        statmetatype_WIS.setLore(loretype_WIS);
        statmetatype_WIS.setCustomModelData(1002);
        stattype_WIS.setItemMeta(statmetatype_WIS);
        statmenugui.setItem(3, stattype_WIS);

        ItemStack stattype_WISn = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_WISn = stattype_WISn.getItemMeta();
        statmetatype_WISn.setDisplayName(" ");
        int WISn = dataManager.getPlayerWIS(p.getUniqueId());
        statmetatype_WISn.setCustomModelData(2000 + WISn);
        stattype_WISn.setItemMeta(statmetatype_WISn);
        statmenugui.setItem(12, stattype_WISn);

        ItemStack stattype_SEN = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_SEN = stattype_SEN.getItemMeta();
        List<String> loretype_SEN = new ArrayList<>();
        statmetatype_SEN.setDisplayName("[ 오감 스텟 ]");
        loretype_SEN.add("현재 적용된 오감 스텟을 확인합니다.");
        int SEN = dataManager.getPlayerSEN(p.getUniqueId());
        loretype_SEN.add("오감 스텟 : " + SEN);
        statmetatype_SEN.setLore(loretype_SEN);
        statmetatype_SEN.setCustomModelData(1003);
        stattype_SEN.setItemMeta(statmetatype_SEN);
        statmenugui.setItem(5, stattype_SEN);

        ItemStack stattype_SENn = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_SENn = stattype_SENn.getItemMeta();
        statmetatype_SENn.setDisplayName(" ");
        int SENn = dataManager.getPlayerSEN(p.getUniqueId());
        statmetatype_SENn.setCustomModelData(2000 +SENn);
        stattype_SENn.setItemMeta(statmetatype_SENn);
        statmenugui.setItem(14, stattype_SENn);

        ItemStack stattype_VIT = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_VIT = stattype_VIT.getItemMeta();
        List<String> loretype_VIT = new ArrayList<>();
        statmetatype_VIT.setDisplayName("[ 체력 스텟 ]");
        loretype_VIT.add("현재 적용된 체력 스텟을 확인합니다.");
        int VIT = dataManager.getPlayerVIT(p.getUniqueId());
        loretype_VIT.add("체력 스텟 : " + VIT);
        statmetatype_VIT.setLore(loretype_VIT);
        statmetatype_VIT.setCustomModelData(1004);
        stattype_VIT.setItemMeta(statmetatype_VIT);
        statmenugui.setItem(7, stattype_VIT);

        ItemStack stattype_VITn = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_VITn = stattype_VITn.getItemMeta();
        statmetatype_VITn.setDisplayName(" ");
        int VITn = dataManager.getPlayerVIT(p.getUniqueId());
        statmetatype_VITn.setCustomModelData(2000 + VITn);
        stattype_VITn.setItemMeta(statmetatype_VITn);
        statmenugui.setItem(16, stattype_VITn);

        ItemStack stattype_END = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_END = stattype_END.getItemMeta();
        List<String> loretype_END = new ArrayList<>();
        statmetatype_END.setDisplayName("[ 지구력 스텟 ]");
        loretype_END.add("현재 적용된 지구력 스텟을 확인합니다.");
        int END = dataManager.getPlayerEND(p.getUniqueId());
        loretype_END.add("지구력 스텟 : " + END);
        statmetatype_END.setLore(loretype_END);
        statmetatype_END.setCustomModelData(1005);
        stattype_END.setItemMeta(statmetatype_END);
        statmenugui.setItem(19, stattype_END);

        ItemStack stattype_ENDn = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_ENDn = stattype_ENDn.getItemMeta();
        statmetatype_ENDn.setDisplayName(" ");
        int ENDn = dataManager.getPlayerEND(p.getUniqueId());
        statmetatype_ENDn.setCustomModelData(2000 + ENDn);
        stattype_ENDn.setItemMeta(statmetatype_ENDn);
        statmenugui.setItem(28, stattype_ENDn);

        ItemStack stattype_MND = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_MND = stattype_MND.getItemMeta();
        List<String> loretype_MND = new ArrayList<>();
        statmetatype_MND.setDisplayName("[ 정신력 스텟 ]");
        loretype_MND.add("현재 적용된 정신력 스텟을 확인합니다.");
        int MND = dataManager.getPlayerMND(p.getUniqueId());
        loretype_MND.add("정신력 스텟 : " + MND);
        statmetatype_MND.setLore(loretype_MND);
        statmetatype_MND.setCustomModelData(1006);
        stattype_MND.setItemMeta(statmetatype_MND);
        statmenugui.setItem(21, stattype_MND);

        ItemStack stattype_MNDn = new ItemStack(Material.GLASS_PANE);
        ItemMeta statmetatype_MNDn = stattype_MNDn.getItemMeta();
        statmetatype_MNDn.setDisplayName(" ");
        int MNDn = dataManager.getPlayerMND(p.getUniqueId());
        statmetatype_MNDn.setCustomModelData(2000 + MNDn);
        stattype_MNDn.setItemMeta(statmetatype_MNDn);
        statmenugui.setItem(30, stattype_MNDn);

        p.openInventory(statmenugui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(player.getName() + "의 스텟창")) {
            event.setCancelled(true); // 클릭 자체를 막음
        }
    }

}
