package Fisher.Jobs;

import Fisher.Node;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Npc;

public class TurnTo extends Node {

    private Npc myPool = ctx.npcs.getNil();

    public TurnTo(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate(){
        for (Npc pool : ctx.npcs.select().id(Fisher.AIOFisher.fishspotnpc).nearest().first()) {
            myPool = pool;
        }
        return ctx.backpack.select().count() != 28
                && !myPool.isOnScreen()
                && ctx.players.local().getAnimation() != Fisher.AIOFisher.animation
                && Fisher.AIOFisher.area.contains(ctx.players.local());
    }

    @Override
    public void execute(){
        ctx.camera.turnTo(myPool.getLocation());

    }

    public String getStatus() {
        return "Turning Camera...";
    }
}