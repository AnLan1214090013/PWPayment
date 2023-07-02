package cn.pixelwar.pwpayment.Rank.LuckyPermUsage;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.data.DataMutateResult;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.group.GroupManager;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class LuckyPermUsage {

    private LuckPerms api = LuckPermsProvider.get();

    public String getLPRank(Player player){
        User user = api.getUserManager().getUser(player.getUniqueId());
//        GroupManager groupManager = api.getGroupManager();
//        Group group;
//        group.get
        if (user != null)
        return user.getPrimaryGroup();
        return "player";
    }

    public void setLPRank(Player player, String rank){
        //先清除玩家的所有group
        User user = api.getUserManager().getUser(player.getUniqueId());

        GroupManager groupManager = api.getGroupManager();
//        //玩家当前所在组的weight
//        Group topGroup = groupManager.getGroup(user.getPrimaryGroup());
//        int topWeight = topGroup.getWeight().getAsInt();
        //玩需要设置组的weight
        Group aimGroup = groupManager.getGroup(rank);
        int aimWeight = aimGroup.getWeight().getAsInt();

        //找出所有比目标组所在的好的组
        for (Group g : groupManager.getLoadedGroups()) {
            //遍历组的weight
            int gWeight = g.getWeight().getAsInt();
            if (gWeight>aimWeight){

                InheritanceNode gNode = InheritanceNode.builder(g.getName()).value(true).build();
                DataMutateResult result = user.data().remove(gNode);
                Bukkit.broadcastMessage("设置目标: "+ rank +" g: "+g.getName()+  "gWeight: "+gWeight+" aimgroup: "+ aimGroup.getName() +" aimWeight" +aimWeight+" 删除了"+g.getName()+" 是否成功: "+result.wasSuccessful());
            }
        }
        InheritanceNode node = InheritanceNode.builder(rank).value(true).build();
        DataMutateResult result = user.data().add(node);
        Bukkit.getLogger().info("[PWPayment] LPRank设置状况: "+result.name()+" 是否成功: "+result.wasSuccessful());
        if (result.wasSuccessful()){
            Bukkit.getLogger().info("[PWPayment] 已经成功设置玩家"+player.getDisplayName()+"的LPRank为"+rank);
        }
    }

}
