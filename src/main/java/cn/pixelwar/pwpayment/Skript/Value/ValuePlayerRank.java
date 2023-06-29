package cn.pixelwar.pwpayment.Skript.Value;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwpayment.PWPayment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValuePlayerRank extends SimpleExpression<String> {

    static {
        Skript.registerExpression( ValuePlayerRank.class, String.class, ExpressionType.COMBINED, "[the] pwrank of %player%");
    }
    private Expression<Player> player;
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "pwrank of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        Player p = player.getSingle(event);
        String rank = PWPayment.connectionPool.getPlayerRank(p.getUniqueId(), p.getDisplayName());
        return new String[]{rank};
    }
}
