package cn.pixelwar.pwpayment.Rank.RankUsage;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Glowing {

    public void setGlowing(Player player){
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.GLOWING, 999999, 1, false, false, false);
        player.addPotionEffect(potionEffect);
    }

}
