package Fisher.Jobs;

import Fisher.Node;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Npc;

public class Fish extends Node {

    private Npc myPool = ctx.npcs.getNil();

    public Fish(MethodContext ctx) {
        super(ctx);
    }

    public boolean validate(){
        for (Npc pool : ctx.npcs.select().id(Fisher.AIOFisher.fishspotnpc).nearest().first()) {
            myPool = pool;
        }
        return ctx.backpack.select().count() != 28 &&
                myPool.isOnScreen() && Fisher.AIOFisher.area.contains(ctx.players.local()) &&
                ctx.players.local().getAnimation() != Fisher.AIOFisher.animation &&
                !ctx.players.local().isInMotion();
    }

    public void execute(){
        myPool.interact(Fisher.AIOFisher.name);
        sleep(8000);
        final Timer timeout = new Timer(5000);
        while (timeout.isRunning() && ctx.players.local().getAnimation() == Fisher.AIOFisher.animation) {
            sleep(1000);
        }

    }

    public String getStatus() {
        return "Fishing...";
    }
}
