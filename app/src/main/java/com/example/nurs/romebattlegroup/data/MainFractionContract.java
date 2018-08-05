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
    }
    public static class FracOtryadEntry implements BaseColumns{
        public static final String TABLE_NAME = "fraction_otryad";
        public static final String COLUMN_FRAC_NAME = "frac_name";
        public static final String COLUMN_OTRYADI = "otryadi";
//        public static final String COLUMN_KOMANDOVANIE = "komandovanie";
//        public static final String COLUMN_PEHOTA_BLIZ_BOI = "pehota_bliz_boi";
//        public static final String COLUMN_STRELKI_PEHOTINCI = "strelki_pehotinci";
//        public static final String COLUMN_PEHOTA_S_KOPIYAMI = "pehota_s_kopiyami";
//        public static final String COLUMN_CAV_BLIZ_BOI = "cav_bliz_boi";
//        public static final String COLUMN_CAV_STRELKI = "cav_strelki";
//        public static final String COLUMN_CAV_UDAR = "cav_udar";
//        public static final String COLUMN_SLON = "slon";
//        public static final String COLUMN_STAC_DALNO_MACHINE = "stac_dalno_machine";
//        public static final String COLUMN_DALNO_MACHINE = "dalno_machine";
//        public static final String COLUMN_OSOB_BOI_EDENIC = "osob_boi_edenic";
//        public static final String COLUMN_PHLOTOVOD = "phlotovod";
//        public static final String COLUMN_SHIP_BLIZ_BOI = "ship_bliz_boi";
//        public static final String COLUMN_SHIP_STRELKI = "ship_strelk";
//        public static final String COLUMN_SHIP_DALNO_MACHINE = "ship_dalno_machine";
    }
}
