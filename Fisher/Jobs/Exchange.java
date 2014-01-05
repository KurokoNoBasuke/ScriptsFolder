package Fisher.Jobs;

import Fisher.Node;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Npc;

public class Exchange extends Node {

    private Npc stiles = ctx.npcs.getNil();

    public Exchange(MethodContext ctx) {
        super(ctx);
    }

    public boolean validate(){
        for (Npc pool : ctx.npcs.select().id(11267).nearest().first()) {
            stiles = pool;
        }
        return ctx.backpack.select().count() == 28
                && ctx.players.local().getLocation().distanceTo(stiles.getLocation()) < 8
                && Fisher.Constants.STILES;
    }

    public void execute(){
        stiles.interact("Exchange");
        sleep(1500);

        if(ctx.widgets.get(1184, 2).isVisible()){
            ctx.widgets.get(1184, 11).click();
            sleep(500);
        }

        if(ctx.widgets.get(1184, 4).isVisible()){
            ctx.widgets.get(1184, 4).click();
            sleep(500);
        }

        final Timer timeout = new Timer(5000);
        while (timeout.isRunning() && ctx.players.local().isInMotion()) {
            sleep(1000);
        }
    }

    public String getStatus() {
        return "Exchanging Stiles...";
    }
}
