package cn.pixelwar.pwpayment.Rank.Listeners;

import cn.pixelwar.pwpayment.PWPayment;
import cn.pixelwar.pwpayment.Rank.LuckyPermUsage.LuckyPermUsage;
import cn.pixelwar.pwpayment.SQL.ConnectionPool;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
//        //检查并设置玩家的rank
//        String PWRank = PWPayment.connectionPool.getPlayerRank(player.getUniqueId(), player.getDisplayName());
//        if(PWRank.equals("none")) return;
//        LuckyPermUsage luckyPermUsage = new LuckyPermUsage();
//        Bukkit.getLogger().info("[PWPayment] 检测到玩家"+player.getDisplayName()+"的PWRank为"+PWRank);
//        luckyPermUsage.setLPRank(player, PWRank);

    }


}
