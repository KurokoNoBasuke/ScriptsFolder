package Fisher.Utils;

import Fisher.Node;
import org.powerbot.script.methods.MethodContext;

public class Widgets extends Node {

    public Widgets(MethodContext ctx) {
        super(ctx);
    }

    public boolean validate(){
        return ctx.game.isLoggedIn();
    }

    public void execute(){
        if(ctx.widgets.get(669, 6).isVisible()){
            ctx.widgets.get(669, 1).click() ;
        }

        if(ctx.widgets.get(1223, 3).isVisible()){
            System.out.println("True");
            ctx.widgets.get(1223, 12).hover();
        }
    }

    public String getStatus() {
        return "Closing Widgets...";
    }
}
