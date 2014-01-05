package Fisher.Jobs;

import Fisher.AIOFisher;
import Fisher.Node;
import org.powerbot.script.methods.MethodContext;

public class ToFish extends Node {

    public ToFish(MethodContext ctx) {
        super(ctx);
    }

    public boolean validate(){
        return ctx.backpack.count() != 28 &&
                !AIOFisher.area.contains(ctx.players.local()) &&
                Fisher.Constants.BANKING;
    }


    public void execute(){
        if(!ctx.players.local().isInMotion()){
            ctx.movement.newTilePath(Fisher.Constants.FLY_FISH_PATH).traverse();
        }

    }

    public String getStatus() {
        return "To Fish Spot Barbarian...";
    }
}
