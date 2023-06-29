package cn.pixelwar.pwpayment.Skript.Effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwpayment.PWPayment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.UUID;

public class EffectAddRank extends Effect {
    private Expression<String> rankin;
    private Expression<Player> playerin;
    static {
        Skript.registerEffect(EffectAddRank.class, new String[] {
                "addrank %player% %string%",
        });
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.rankin = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "add rank for player: ";
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        String rank = rankin.getSingle(event);
        UUID uuid = player.getUniqueId();
        PWPayment.connectionPool.addPlayerRank(uuid, player.getDisplayName(), rank);
    }

}
