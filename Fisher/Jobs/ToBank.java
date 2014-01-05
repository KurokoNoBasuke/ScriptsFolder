package Fisher.Jobs;

import Fisher.Node;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToBank extends Node {

    public ToBank(MethodContext ctx){
        super(ctx);
    }

    public boolean validate(){
        return ctx.backpack.select().count() == 28 && !ctx.bank.isOnScreen() && Fisher.Constants.BANKING;
    }

    public void execute(){
        try{
            Tile next = findNextTile();
            if (next != null) {
                if (next.distanceTo(Fisher.AIOFisher.endTileFront) < ctx.players.local().getLocation()
                        .distanceTo(Fisher.AIOFisher.endTileFront)) {
                    if (ctx.game.getClientState() != ctx.game.INDEX_MAP_LOADING) {
                        if (walk(next)) {
                            sleep(500);
                            while (next.distanceTo(ctx.players.local().getLocation()) > 9
                                    && ctx.movement.getDestination().distanceTo(
                                    ctx.players.local().getLocation()) > 9
                                    && ctx.players.local().isInMotion()) {
                                sleep(100);
                            }
                            sleep(500);
                        }
                    }
                }
            }
        } catch (Exception e){

        }
    }

    private Tile findNextTile() {
        List<Double> distances = new ArrayList<>();
        for (int i = 0; i < Fisher.AIOFisher.myPath.length; i++) {
            distances.add(Fisher.AIOFisher.myPath[i]
                    .distanceTo(ctx.players.local()));
        }
        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) == Collections.min(distances)) {
                return i + 1 > Fisher.AIOFisher.myPath.length ? Fisher.AIOFisher.myPath[i]
                        : Fisher.AIOFisher.myPath[i + 1].distanceTo(ctx.players.local()) > 9 ? Fisher.AIOFisher.myPath[i + 1]
                        : i + 2 > Fisher.AIOFisher.myPath.length ? Fisher.AIOFisher.myPath[i + 1]
                        : Fisher.AIOFisher.myPath[i + 2];
            }
        }
        return null;
    }

    public boolean walk(Tile t) {
        if (t.getMatrix(ctx).isOnMap()) {
            if (ctx.movement.stepTowards(t)) {
                return true;
            }
        }
        return false;
    }

    public String getStatus() {
        return "To Bank...";
    }

}
