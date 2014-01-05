package Fisher;

import Fisher.Jobs.Dropping;
import Fisher.Utils.AntiPattern;
import Fisher.Utils.Widgets;
import org.powerbot.event.MessageListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import Fisher.Jobs.*;
import org.powerbot.event.MessageEvent;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.wrappers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.DefaultComboBoxModel;

@Manifest(authors = {"GinoGino"}, name = "AIO F2P FISHER V0.1", description = "Fish in a variety of locations, and a variety of fish", version = 2.2)
public class AIOFisher extends PollingScript implements MessageListener {

    private final ArrayList<Node> nodes = new ArrayList<>();
    //GUI
    public static String name = "";
    public static Area area, area2;
    public static Tile[] myPath, mypathBack;
    public static Tile endTileFront, endTileBack;
    public static int fishOne;
    public static int fishTwo;
    public static int fishspotnpc;
    public static int animation;
    private final AIOFisher.GUI gui = new GUI();

    public AIOFisher() {
            gui.setVisible(true);
                while (gui.isActive()) sleep(350);
                Collections.addAll(nodes,
                            new Fish(ctx),
                            new Dropping(ctx),
                            new ToBank(ctx),
                            new ToStiles(ctx),
                            new Bank(ctx),
                            new Exchange(ctx),
                            new ToFishKaramja(ctx),
                            new ToFish(ctx),
                            new TurnTo(ctx),
                            new DroppingTuna(ctx),
                            new AntiPattern(ctx),
                            new Widgets(ctx)
                    );
    }


    public void setup() {
        Constants.xpStart = ctx.skills.getExperience(Skills.FISHING);
        Constants.lvlStart = ctx.skills.getLevel(Skills.FISHING);
        Constants.startTime = System.currentTimeMillis();

        if(Constants.LOAD1 = true){
            name = Constants.BURTHORPE_METHOD_CAGE;
            fishOne = Constants.CRAYFISH;
            fishspotnpc = Constants.BURTHORPE_NPC;
            animation = Constants.BURTHORPE_ANIMATION;
            area = Constants.CRAYFISH_AREA;
        }

        if(Constants.LOAD2 = true){
            name = Constants.DRAYNOR_METHOD_NET;
            fishOne = Constants.ANCHOVIES;
            fishTwo = Constants.SHRIMP;
            fishspotnpc = Constants.DRAYNOR_NPC;
            area = Constants.ANCHOVIES_AREA;
            animation = Constants.DRAYNOR_ANIMATION;
        }

        if(Constants.LOAD3 = true){
            name = Constants.BARBARIAN_METHOD_BAIT;
            fishOne = Constants.PIKE;
            fishspotnpc = Constants.BARBARIAN_NPC;
            animation = Constants.BARBARIAN_ANIMATION;
            myPath = Constants.FlY_PATH;
            area = Constants.FLY_FISH_AREA;
            endTileFront = new Tile(3093, 3491, 0);
            mypathBack = Constants.FLY_FISH_PATH;
            endTileBack = new Tile(3106, 3433, 0);
        }

        if(Constants.LOAD4 = true){
            name = Constants.BARBARIAN_METHOD_LURE;
            fishOne = Constants.TROUT;
            fishTwo = Constants.SALMON;
            fishspotnpc = Constants.BARBARIAN_NPC;
            animation = Constants.BARBARIAN_ANIMATION;
            myPath = Constants.FlY_PATH;
            area = Constants.FLY_FISH_AREA;
            endTileFront = new Tile(3093, 3491, 0);
            mypathBack = Constants.FLY_FISH_PATH;
            endTileBack = new Tile(3106, 3433, 0);
        }

        if(Constants.LOAD5 = true){
            name = Constants.KARAMJA_METHOD_CAGE;
            fishspotnpc = Constants.KARAMJA_NPC;
            animation = Constants.KARAMJA_CAGE_ANIMATION;
            myPath = Constants.STILES_PATH;
            area = Constants.KARAMJA_FISH_AREA;
            endTileFront = new Tile(2852, 3142, 0);
            mypathBack = Constants.STILES_FISH_PATH;
            endTileBack = new Tile(2924, 3172, 0);
            area2 = Constants.STILES_AREA;
        }

        if(Constants.LOAD6 = true){
            name = Constants.KARAMJA_METHOD_HARPOON;
            fishspotnpc = Constants.KARAMJA_NPC;
            animation = Constants.KARAMJA_HARPOON_ANIMATION;
            fishOne = Constants.TUNA;
            myPath = Constants.STILES_PATH;
            area = Constants.KARAMJA_FISH_AREA;
            endTileFront = new Tile(2852, 3142, 0);
            mypathBack = Constants.STILES_FISH_PATH;
            endTileBack = new Tile(2924, 3172, 0);
            area2 = Constants.STILES_AREA;
        }

        if(ctx.camera.getPitch() != 50){
            ctx.camera.setPitch(50);
        }


    }

    public int poll() {
         for (Node node : nodes) {
            if (node.validate()) {
                int value = (ctx.settings.get(3148) >>> 18) & 0xFFF;
                System.out.println(value);
                node.execute();
                return org.powerbot.script.util.Random.nextInt(250, 375);
            }
        }
        return 250;
    }

       public void start() {

        if (ctx.game.isLoggedIn()) {
            setup();
        }
        super.start();
    }

    public void messaged(MessageEvent msg) {
        String message = msg.getMessage();
        if (message.startsWith("You catch")) {
            Constants.fishCaught++;
        }
    }

    public class GUI extends JFrame {
        public GUI() {
            initComponents();
        }


        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - dg;j sdagklj
            panel1 = new JPanel();
            comboBox1 = new JComboBox();
            comboBox2 = new JComboBox();
            button1 = new JButton();

            setTitle("AIO FISHER");
            setAlwaysOnTop(true);
            setResizable(false);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            Container contentPane = getContentPane();
            contentPane.setLayout(null);


            comboBox1.setModel(new DefaultComboBoxModel(new String[] {
                    "Crayfish - Burthorpe", "Anchovies - Draynor", "Pike - Barbarian Village", "Trout/Salmon - Barbarian Village", "Lobster - Karamja", "Swordfish - Karamja"}));
            comboBox1.setBounds(5, 5, 130, comboBox1.getPreferredSize().height);


            comboBox2.setModel(new DefaultComboBoxModel(new String[] {
                    "Dropping - ActionBar", "Banking - Pike | Trout | Salmon", "Stiles - Karamja Fishing"}));
            comboBox1.setBounds(5, 5, 130, comboBox1.getPreferredSize().height);

            //======== panel1 ========
            {

                //---- button1 ----
                button1.setText("Start");

                button1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (comboBox1.getSelectedItem().equals("Crayfish - Burthorpe")) {
                            Constants.LOAD1 = true;
                            name = Constants.BURTHORPE_METHOD_CAGE;
                            fishOne = Constants.CRAYFISH;
                            fishspotnpc = Constants.BURTHORPE_NPC;
                            animation = Constants.BURTHORPE_ANIMATION;
                            area = Constants.CRAYFISH_AREA;
                        }
                        if (comboBox1.getSelectedItem().equals("Anchovies - Draynor")) {
                            Constants.LOAD2 = true;
                            name = Constants.DRAYNOR_METHOD_NET;
                            fishOne = Constants.ANCHOVIES;
                            fishTwo = Constants.SHRIMP;
                            fishspotnpc = Constants.DRAYNOR_NPC;
                            animation = Constants.DRAYNOR_ANIMATION;
                            area = Constants.ANCHOVIES_AREA;
                        }
                        if (comboBox1.getSelectedItem().equals("Pike - Barbarian Village")) {
                            Constants.LOAD3 = true;
                            name = Constants.BARBARIAN_METHOD_BAIT;
                            fishOne = Constants.PIKE;
                            fishspotnpc = Constants.BARBARIAN_NPC;
                            animation = Constants.BARBARIAN_ANIMATION;
                            myPath = Constants.FlY_PATH;
                            endTileFront = new Tile(3093, 3491, 0);
                            mypathBack = Constants.FLY_FISH_PATH;
                            endTileBack = new Tile(3106, 3433, 0);
                            area = Constants.FLY_FISH_AREA;
                        }
                        if (comboBox1.getSelectedItem().equals("Trout/Salmon - Barbarian Village")) {
                            Constants.LOAD4 = true;
                            name = Constants.BARBARIAN_METHOD_LURE;
                            fishOne = Constants.TROUT;
                            fishTwo = Constants.SALMON;
                            fishspotnpc = Constants.BARBARIAN_NPC;
                            animation = Constants.BARBARIAN_ANIMATION;
                            myPath = Constants.FlY_PATH;
                            endTileFront = new Tile(3093, 3491, 0);
                            mypathBack = Constants.FLY_FISH_PATH;
                            endTileBack = new Tile(3106, 3433, 0);
                            area = Constants.FLY_FISH_AREA;
                        }
                        if (comboBox1.getSelectedItem().equals("Lobster - Karamja")) {
                            Constants.LOAD5 = true;
                            name = Constants.KARAMJA_METHOD_CAGE;
                            fishspotnpc = Constants.KARAMJA_NPC;
                            animation = Constants.KARAMJA_CAGE_ANIMATION;
                            myPath = Constants.STILES_PATH;
                            area = Constants.KARAMJA_FISH_AREA;
                            endTileFront = new Tile(2852, 3142, 0);
                            mypathBack = Constants.STILES_FISH_PATH;
                            endTileBack = new Tile(2924, 3172, 0);
                            area2 = Constants.STILES_AREA;
                        }
                        if (comboBox1.getSelectedItem().equals("Swordfish - Karamja")) {
                            Constants.LOAD6 = true;
                            name = Constants.KARAMJA_METHOD_HARPOON;
                            fishspotnpc = Constants.KARAMJA_NPC;
                            animation = Constants.KARAMJA_HARPOON_ANIMATION;
                            fishOne = Constants.TUNA;
                            myPath = Constants.STILES_PATH;
                            area = Constants.KARAMJA_FISH_AREA;
                            endTileFront = new Tile(2852, 3142, 0);
                            mypathBack = Constants.STILES_FISH_PATH;
                            endTileBack = new Tile(2924, 3172, 0);
                            area2 = Constants.STILES_AREA;
                        }
                        if (comboBox2.getSelectedItem().equals("Banking - Pike | Trout | Salmon")) {

                            Constants.BANKING = true;
                        }
                        if (comboBox2.getSelectedItem().equals("Dropping - ActionBar")) {

                            Constants.DROPPING = true;
                        }
                        if (comboBox2.getSelectedItem().equals("Stiles - Karamja Fishing")) {

                            Constants.STILES = true;
                        }
                        setVisible(false);
                        gui.dispose();
                    }
                });

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup()
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap())
                                .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(button1)
                                        .addGap(24, 24, 65))
                );
                panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(button1)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }

            GroupLayout contentPaneLayout = new GroupLayout(contentPane);
            contentPane.setLayout(contentPaneLayout);
            contentPaneLayout.setHorizontalGroup(
                    contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 1, Short.MAX_VALUE))
            );
            contentPaneLayout.setVerticalGroup(
                    contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
            );
            pack();
            setLocationRelativeTo(getOwner());

        }

        private JPanel panel1;
        private JComboBox comboBox1;
        private JComboBox comboBox2;
        private JButton button1;

    }

}