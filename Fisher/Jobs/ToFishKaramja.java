package Fisher.Jobs;

import Fisher.Node;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToFishKaramja extends Node {

    public ToFishKaramja(MethodContext ctx) {
        super(ctx);
    }

    public boolean validate(){
        return ctx.backpack.count() != 28 &&
                !Fisher.AIOFisher.area.contains(ctx.players.local()) && Fisher.Constants.STILES;
    }

    public void execute(){
        try{
            Tile next = findNextTileBack();
            if (next != null) {
                if (next.distanceTo(Fisher.AIOFisher.endTileBack) < ctx.players.local().getLocation()
                        .distanceTo(Fisher.AIOFisher.endTileBack)) {
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
        }catch (Exception e){
        }
    }

    public Tile findNextTileBack() {
        List<Double> distances = new ArrayList<>();
        for (int i = 0; i < Fisher.AIOFisher.mypathBack.length; i++) {
            distances.add(Fisher.AIOFisher.mypathBack[i]
                    .distanceTo(ctx.players.local()));
        }
        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) == Collections.min(distances)) {
                return i + 1 > Fisher.AIOFisher.mypathBack.length ? Fisher.AIOFisher.mypathBack[i]
                        : Fisher.AIOFisher.mypathBack[i + 1].distanceTo(ctx.players.local()) > 9 ? Fisher.AIOFisher.mypathBack[i + 1]
                        : i + 2 > Fisher.AIOFisher.mypathBack.length ? Fisher.AIOFisher.mypathBack[i + 1]
                        : Fisher.AIOFisher.mypathBack[i + 2];
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
        return "To Fish Spot Karamja...";
    }
}
