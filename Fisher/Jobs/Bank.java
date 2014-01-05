package Fisher.Jobs;

import Fisher.AIOFisher;
import Fisher.Node;
import org.powerbot.script.methods.MethodContext;

public class Bank extends Node {

    public Bank(MethodContext ctx) {
        super(ctx);
    }

    public boolean validate(){
        return ctx.backpack.select().count() > 1 &&
                ctx.bank.isOnScreen() && Fisher.Constants.BANKING;
    }

    public void execute(){
        if(!ctx.bank.isOpen()){
            ctx.bank.open();
        }

        if(ctx.bank.isOpen()){
            if(!ctx.backpack.select().id(AIOFisher.fishOne).isEmpty()){
                ctx.bank.deposit(AIOFisher.fishOne, 0);
            }
            if(!ctx.backpack.select().id(AIOFisher.fishTwo).isEmpty()){
                ctx.bank.deposit(AIOFisher.fishTwo, 0);
            }
        }

        if(ctx.bank.isOpen() && ctx.backpack.select().id(AIOFisher.fishOne, AIOFisher.fishTwo).isEmpty()){
            ctx.bank.close();
        }
    }

    public String getStatus() {
        return "Banking...";
    }
}

