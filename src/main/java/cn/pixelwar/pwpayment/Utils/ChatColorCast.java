package cn.pixelwar.pwpayment.Utils;

import org.bukkit.ChatColor;

public class ChatColorCast {
    public static String format (String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
