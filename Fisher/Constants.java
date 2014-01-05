package Fisher;

import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.Tile;

import java.text.NumberFormat;

public class Constants {

    //Method
    public static boolean BANKING = false;
    public static boolean DROPPING = false;
    public static boolean STILES = false;

    public static boolean LOAD1 = false;
    public static boolean LOAD2 = false;
    public static boolean LOAD3 = false;
    public static boolean LOAD4 = false;
    public static boolean LOAD5 = false;
    public static boolean LOAD6 = false;

    public static String status = "";

    //Paint
    public static long startTime, runTime = 0;
    public static int xpStart, lvlStart;
    public static int fishCaught;
    public static NumberFormat n = NumberFormat.getInstance();
    public static String getCurrentStatus;

    //Barbarian Fishing
    public static final Tile[] FlY_PATH = { new Tile(3107, 3433, 0), new Tile(3104, 3435, 0), new Tile(3101, 3437, 0),
            new Tile(3098, 3440, 0), new Tile(3095, 3442, 0), new Tile(3092, 3444, 0),
            new Tile(3091, 3447, 0), new Tile(3092, 3450, 0), new Tile(3093, 3453, 0),
            new Tile(3094, 3456, 0), new Tile(3091, 3457, 0), new Tile(3088, 3459, 0),
            new Tile(3087, 3462, 0), new Tile(3090, 3464, 0), new Tile(3093, 3464, 0),
            new Tile(3096, 3465, 0), new Tile(3098, 3468, 0), new Tile(3098, 3471, 0),
            new Tile(3098, 3474, 0), new Tile(3098, 3477, 0), new Tile(3099, 3480, 0),
            new Tile(3099, 3483, 0), new Tile(3096, 3484, 0), new Tile(3093, 3484, 0),
            new Tile(3091, 3487, 0), new Tile(3090, 3490, 0), new Tile(3094, 3491, 0)};
    public static final Tile[] FLY_FISH_PATH = new Tile[] { new Tile(3094, 3493, 0), new Tile(3089, 3492, 0), new Tile(3086, 3489, 0),
            new Tile(3090, 3485, 0), new Tile(3095, 3485, 0), new Tile(3099, 3482, 0),
            new Tile(3099, 3476, 0), new Tile(3100, 3471, 0), new Tile(3096, 3467, 0),
            new Tile(3096, 3462, 0), new Tile(3096, 3457, 0), new Tile(3094, 3452, 0),
            new Tile(3090, 3449, 0), new Tile(3090, 3445, 0), new Tile(3092, 3439, 0),
            new Tile(3096, 3437, 0), new Tile(3101, 3435, 0), new Tile(3106, 3433, 0) };
    public static final Area FLY_FISH_AREA = new Area(new Tile(3114, 3436, 0), new Tile(3101, 3421, 0));
    public static final String BARBARIAN_METHOD_BAIT = "Bait";
    public static final String BARBARIAN_METHOD_LURE = "Lure";
    public static final int BARBARIAN_NPC = 328;
    public static final int BARBARIAN_ANIMATION = 623;
    public static final int TROUT = 335;
    public static final int SALMON = 331;
    public static final int PIKE = 349;


    //Karamja Fishing
    public static final Tile[] STILES_PATH = { new Tile(2920, 3174, 0), new Tile(2915, 3174, 0),
            new Tile(2910, 3172, 0), new Tile(2905, 3172, 0), new Tile(2901, 3169, 0),
            new Tile(2896, 3168, 0), new Tile(2892, 3165, 0), new Tile(2888, 3162, 0),
            new Tile(2885, 3158, 0), new Tile(2881, 3154, 0), new Tile(2877, 3151, 0),
            new Tile(2872, 3149, 0), new Tile(2867, 3148, 0), new Tile(2862, 3147, 0),
            new Tile(2857, 3145, 0), new Tile(2852, 3142, 0)};
    public static final Tile[] STILES_FISH_PATH = new Tile[] { new Tile(2851, 3142, 0), new Tile(2855, 3144, 0), new Tile(2860, 3145, 0),
            new Tile(2864, 3148, 0), new Tile(2869, 3151, 0), new Tile(2871, 3156, 0),
            new Tile(2876, 3158, 0), new Tile(2880, 3162, 0), new Tile(2884, 3165, 0),
            new Tile(2889, 3167, 0), new Tile(2894, 3168, 0), new Tile(2898, 3170, 0),
            new Tile(2903, 3171, 0), new Tile(2909, 3171, 0), new Tile(2914, 3171, 0),
            new Tile(2919, 3171, 0), new Tile(2924, 3172, 0), new Tile(2924, 3177, 0), new Tile(2925, 3178, 0) };
    public static final Area STILES_AREA = new Area(new Tile(2848, 3141, 0), new Tile(2855, 3147, 0));
    public static final Area KARAMJA_FISH_AREA = new Area( new Tile(2919, 3185, 0), new Tile(2929, 3173, 0));
    public static final String KARAMJA_METHOD_CAGE = "Cage";
    public static final String KARAMJA_METHOD_HARPOON = "Harpoon";
    public static final int KARAMJA_NPC = 324;
    public static final int KARAMJA_CAGE_ANIMATION = 619;
    public static final int KARAMJA_HARPOON_ANIMATION = 618;
    public static final int TUNA = 359;

    //Crayfish Fishing
    public static final Area CRAYFISH_AREA = new Area(new Tile(2895, 3476, 0), new Tile(2920, 3433, 0) );
    public static final String BURTHORPE_METHOD_CAGE = "Cage";
    public static final int BURTHORPE_ANIMATION = 10009;
    public static final int BURTHORPE_NPC = 14907;
    public static final int CRAYFISH = 13435;

    //Anchovies Fishing
    public static final Area ANCHOVIES_AREA = new Area(new Tile(3094, 3221, 0), new Tile(3078, 3238, 0));
    public static final String DRAYNOR_METHOD_NET = "Net";
    public static final int DRAYNOR_ANIMATION = 621;
    public static final int DRAYNOR_NPC = 327;
    public static final int ANCHOVIES = 321;
    public static final int SHRIMP = 317;
}
