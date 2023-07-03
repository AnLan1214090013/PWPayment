package cn.pixelwar.pwpayment.Rank.RankUsage;

import cn.pixelwar.pwpayment.Utils.ChatColorCast;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Glowing {

    public void setGlowing(Player player){
        //先检查是否已经glow
        for (PotionEffect potionEffect : player.getActivePotionEffects()){
            PotionEffectType potionEffectType = potionEffect.getType();
            if (potionEffectType.equals(PotionEffectType.GLOWING)){
                player.removePotionEffect(PotionEffectType.GLOWING);
                player.sendMessage(ChatColorCast.format("&d▸ &f你的发光特效已经关闭，再次使用&b&l/glow&f打开"));
                player.playSound(player.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 2f);
                return;
            }
        }
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.GLOWING, 999999, 1, false, false, false);
        player.addPotionEffect(potionEffect);
        player.sendMessage(ChatColorCast.format("&d▸ &f你的发光特效已经打开，再次使用&b&l/glow&f关闭"));
        player.playSound(player.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 2f);
    }

}
