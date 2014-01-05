package Fisher.Jobs;

import Fisher.Node;
import org.powerbot.script.methods.Hud;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Item;

import java.awt.*;

public class DroppingTuna extends Node {

    public DroppingTuna(MethodContext ctx) {
        super(ctx);
    }

    public boolean validate(){
        return ctx.backpack.select().count() == 28 &&
                !ctx.players.local().isInMotion() && Fisher.Constants.STILES && Fisher.Constants.LOAD6;
    }

    public void execute(){
        if(!ctx.hud.view(Hud.Window.BACKPACK)){
            ctx.hud.view(Hud.Window.BACKPACK);
        }

        if(ctx.combatBar.select().id(Fisher.Constants.TUNA).isEmpty()){
            for(Item item : ctx.backpack.select().id(Fisher.Constants.TUNA).first()){
                Point slotPoint = new Point(ctx.widgets.get(1430, 18).getCenterPoint().x,
                        ctx.widgets.get(1430, 18).getCenterPoint().y);
                ctx.mouse.move(item);
                ctx.mouse.drag(slotPoint, true);
                sleep(1500);
            }
        }

        for(Item item : ctx.backpack.select().id(Fisher.Constants.TUNA)){
            if(ctx.backpack.contains(item)){
                ctx.widgets.get(1430, 18).interact("Drop");
            }
        }
    }

    public String getStatus() {
        return "Dropping Fish...";
    }

}
