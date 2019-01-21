package com.example.nurs.romebattlegroup.data;

import android.provider.BaseColumns;

/**
 * Created by nurs on 02.04.18.
 */

public final class MainFractionContract {
    public static class FractionsEntry implements BaseColumns{
        public static final String TABLE_NAME = "fractions";
        public static final String COLUMN_FRACTION_NAME = "fraction_name";
        public static final String COLUMN_IMG_RES = "img_res";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + MainFractionContract.FractionsEntry.TABLE_NAME + " ("
                        + MainFractionContract.FractionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + MainFractionContract.FractionsEntry.COLUMN_FRACTION_NAME + " TEXT, "
                        + MainFractionContract.FractionsEntry.COLUMN_IMG_RES + " TEXT)";


        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FractionsEntry.TABLE_NAME;
    }
    public static class InfantyEntry implements BaseColumns{
        public static final String TABLE_NAME = "infanty";
        public static final String COLUMN_BATTLE_GROUP_NAME = "battle_group_name";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_BLIZ_BOI = "bliz_boi";
        public static final String COLUMN_URON_ORUZHIEM = "uron_oruzhiem";
        public static final String COLUMN_NATISK = "natisk";
        public static final String COLUMN_ZASHITA_BLIZ_BOI = "zashita_bliz_boi";
        public static final String COLUMN_BRONIA = "bronia";
        public static final String COLUMN_HP = "HP";
        public static final String COLUMN_MORAL = "Moral";
        public static final String COLUMN_FRACTION = "Fraction";
        public static final String COLUMN_KOLVO = "kolvo";
        public static final String COLUMN_TSENA_NAIMA = "tsena_naima";
        public static final String COLUMN_TSENA_SODERZHANIYA = "tsena_soderzhaniya";
        public static final String COLUMN_TYPE = "type_of_group";
        public static final String COLUMN_SHIP_HP = "ship_hp";
        public static final String COLUMN_SHIP_SPEED = "ship_speed";
        public static final String COLUMN_URON_SNARYADA = "uron_snaryada";
        public static final String COLUMN_DALNOST = "dalnost";
        public static final String COLUMN_VISTREL_V_MIN = "vistrel_v_min";
        public static final String COLUMN_BOEPRIPACY = "boepripasy";
        public static final String COLUMN_YOUTUBEVIDEO = "YouTubeVideo";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_SQUAD_IMAGE = "imageSquad";

    }
    public static class FracOtryadEntry implements BaseColumns{
        public static final String TABLE_NAME = "fraction_otryad";
        public static final String COLUMN_FRAC_NAME = "frac_name";
        public static final String COLUMN_OTRYADI = "otryadi";

    }
}
