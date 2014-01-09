import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.util.Timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Manifest(authors = {"GinoGino"}, name = "Basic/Advanced Gnome", description = "", version = 0.1)
public class GnomeAgilityCourse extends PollingScript {

    //Advanced Object IDs
    private final int LOG = 69526;
    private final int NET = 69383;
    private final int FIRST_BRANCH = 69508;
    private final int SECOND_BRANCH = 69506;
    private final int SIGNPOST = 69514;
    private final int POLE = 43529;
    private final int BARRIER = 69381;

    //Advanced Object Areas
    private final Area AREA_LOG = new Area(new Tile(2478, 3435, 0), new Tile(2471, 3437, 0));
    private final Area AREA_NET = new Area(new Tile(2478, 3430, 0), new Tile(2470, 3424, 0));
    private final Area AREA_BRANCH1 = new Area(new Tile(2477, 3421, 1), new Tile(2470, 3425, 1));
    private final Area AREA_BRANCH2 = new Area(new Tile(2478, 3418, 2), new Tile(2471, 3422, 2));
    private final Area AREA_SIGNPOST = new Area(new Tile(2477, 3417, 3), new Tile(2471, 3421, 3));
    private final Area AREA_POLE = new Area(new Tile(2483, 3418, 3), new Tile(2488, 3421, 3));
    private final Area AREA_BARRIER = new Area(new Tile(2482, 3431, 3), new Tile(2488, 3436, 3));
    private final Area AREA_UNDER_PIPE = new Area(new Tile(2489, 3434, 0), new Tile(2481, 3440, 0));


    //Basic Object IDs
    private final int LOWER_ROPE = 2312;
    private final int TREE_BRANCH = 69507;
    private final int LOWER_NET = 69378;
    private final int LOWER_PIPE = 69378;

    //Basic Object Areas
    private final Area AREA_BRANCH = new Area(new Tile(2482, 3417, 2), new Tile(2489, 3422, 2));
    private final Area AREA_BASE = new Area(new Tile(2490, 3415, 0), new Tile(2481, 3421, 0));
    private final Area AREA_PIPE = new Area(new Tile(2489, 3428, 0), new Tile(2482, 3424, 0));

    private JobContainer container = null;


    public GnomeAgilityCourse() {
          getExecQueue(State.START).add(new Runnable() {
            @Override
            public void run() {
             if (container == null) {
                    container = new JobContainer(new Job[]{
                            new WalkAcrossLog(getContext()),
                            new ClimbOverNet(getContext()),
                            new ClimbOverBranch1(getContext()),
                            new ClimbOverBranch2(getContext()),
                            new RunAcrossSign(getContext()),
                            new SwingToPole(getContext()),
                            new ClimbBarrier(getContext()),
                            new ClickingLog(getContext()),
                            new WalkAcrossRope(getContext()),
                            new ClimbTreeBranchLower(getContext()),
                            new ClimbOverLowerNet(getContext()),
                            new ClimbIntoPipe(getContext()),

                    });

                }
            }
        });
    }

    @Override
    public int poll() {
       try{
        final Job job = container.get();
        if (job != null) {
            job.execute();
            return job.delay();
        }

    } catch (Exception e){

       }
        return 250;
    }

    public void start() {
        super.start();
    }

    public abstract class Job extends MethodProvider {
        public Job(MethodContext ctx) {
            super(ctx);
        }

        /* override this to extend the sleep time */
        public int delay() {
            return 250;
        }

        /* returns the priority of the job. higher priority = executed first */
        public int priority() {
            return 0;
        }

        public abstract boolean activate();

        public abstract void execute();
    }

    public class JobContainer implements Comparator<Job> {
        private List<Job> jobList = new ArrayList<>();

        public JobContainer(Job[] jobs) {
            submit(jobs);
        }

        public void submit(final Job... jobs) {
            for (Job j : jobs) {
                if (!jobList.contains(j)) {
                    jobList.add(j);
                }
            }
            Collections.sort(jobList, this);
        }

        @Override
        public int compare(Job o1, Job o2) {
            return o2.priority() - o1.priority();
        }

        public Job get() {
            for (Job j : jobList) {
                if (j.activate()) {
                    return j;
                }
            }
            return null;
        }
    }

    public class WalkAcrossLog extends Job{

        public WalkAcrossLog(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return AREA_LOG.contains(ctx.players.local()) && !ctx.players.local().isInMotion();
        }

        @Override
        public void execute(){
           doCourse(LOG, AREA_NET, "Walk-across");

        }
    }

    public class ClimbOverNet extends Job {

        public ClimbOverNet(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return AREA_NET.contains(ctx.players.local()) && !ctx.players.local().isInMotion();
        }

        @Override
        public void execute(){
            doCourse(NET, AREA_BRANCH1, "Climb-over");

        }
    }

    public class ClimbOverBranch1 extends Job {

        public ClimbOverBranch1(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return  AREA_BRANCH1.contains(ctx.players.local()) && !ctx.players.local().isInMotion();
        }

        @Override
        public void execute(){
            doCourse(FIRST_BRANCH, AREA_BRANCH2, "Climb");

        }
    }

    public class ClimbOverBranch2 extends Job {

        public ClimbOverBranch2(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return  AREA_BRANCH2.contains(ctx.players.local()) && !ctx.players.local().isInMotion() &&  ctx.skills.getLevel(Skills.AGILITY) >= 85;
        }

        @Override
        public void execute(){
           doCourse(SECOND_BRANCH, AREA_SIGNPOST, "Climb-up");
        }
    }

    public class RunAcrossSign extends Job {

        public RunAcrossSign(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return  AREA_SIGNPOST.contains(ctx.players.local()) && !ctx.players.local().isInMotion();
        }

        @Override
        public void execute(){
            doCourse(SIGNPOST, AREA_POLE, "Run-across");
          }
    }


    public class SwingToPole extends Job {

        public SwingToPole(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return  AREA_POLE.contains(ctx.players.local()) && !ctx.players.local().isInMotion();
        }

        @Override
        public void execute(){
            doCourse(POLE, AREA_BARRIER, "Swing-to");
        }
    }

    public class ClimbBarrier extends Job {

        public ClimbBarrier(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return  AREA_BARRIER.contains(ctx.players.local()) && !ctx.players.local().isInMotion();
        }

        @Override
        public void execute(){
           doCourse(BARRIER, AREA_UNDER_PIPE, "Jump-over");
        }
    }

    public class ClickingLog extends Job {

        public ClickingLog(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return AREA_UNDER_PIPE.contains(ctx.players.local()) && !ctx.players.local().isInMotion();
        }

        @Override
        public void execute(){
           doCourse(LOG, AREA_NET, "Walk-across");

        }
    }

    //Basic Course
    public class WalkAcrossRope extends Job {

        public WalkAcrossRope(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return  AREA_BRANCH2.contains(ctx.players.local()) && ctx.skills.getRealLevel(Skills.AGILITY) < 85 && !ctx.players.local().isInMotion();
        }

        @Override
        public void execute(){
           doCourse(LOWER_ROPE, AREA_BRANCH, "Walk-on");
        }
    }

    public class ClimbTreeBranchLower extends Job {

        public ClimbTreeBranchLower(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return  AREA_BRANCH.contains(ctx.players.local()) && !ctx.players.local().isInMotion();
        }

        @Override
        public void execute(){
           doCourse(TREE_BRANCH, AREA_SIGNPOST, "Climb-down");
        }
    }

    public class ClimbOverLowerNet extends Job {

        public ClimbOverLowerNet(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return  AREA_BASE.contains(ctx.players.local()) && !ctx.players.local().isInMotion();
        }

        @Override
        public void execute(){
            doCourse(LOWER_NET, AREA_SIGNPOST, "Climb-over");
        }
    }

    public class ClimbIntoPipe extends Job {

        public ClimbIntoPipe(MethodContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate(){
            return  AREA_PIPE.contains(ctx.players.local()) && ctx.players.local().getAnimation() == -1;
        }

        @Override
        public void execute(){
            sleep(1300, 1500);
            doCourse(LOWER_PIPE, AREA_SIGNPOST, "Squeeze-through");
        }
    }


    private void doCourse(int object, Area area, String action){
        for(GameObject obj : ctx.objects.select().id(object).nearest().first()){
            if(obj != null){
                if(obj.isOnScreen()){
                    if(obj.interact(action)){
                        final Timer timeout = new Timer(5000);
                        while (timeout.isRunning() && !area.contains(ctx.players.local())) {
                            sleep(100);
                        }
                    }
                }else{
                    ctx.camera.turnTo(obj);
                }
            }
        }
    }
}

