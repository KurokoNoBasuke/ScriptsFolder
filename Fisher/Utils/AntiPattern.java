package Fisher.Utils;
import Fisher.Node;
import org.powerbot.script.methods.MethodContext;

public class AntiPattern extends Node {

    public AntiPattern(MethodContext ctx) {
        super(ctx);
    }

    public boolean validate(){
        return ctx.game.isLoggedIn();
    }

    public void execute() {
       //Need to think of something good here!
    }

    public String getStatus() {
        return "AntiPattern...";
    }
}
