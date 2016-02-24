package ca.cestmoi.xps_630_w7.exercise;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_ALIMENTS = "CREATE TABLE " + Aliments.TABLE  + "("
                + Aliments.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Aliments.KEY_name + " TEXT, "
                + Aliments.KEY_qte + " INTEGER, "
                + Aliments.KEY_type + " TEXT, "
                + Aliments.KEY_marque + " TEXT, "
                + Aliments.KEY_property + " TEXT)";

        db.execSQL(CREATE_TABLE_ALIMENTS);
        String CREATE_TABLE_RECETTES = "CREATE TABLE " + Recettes.TABLE  + "("
                + Recettes.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Recettes.KEY_name + " TEXT, "
                + Recettes.KEY_qte + " INTEGER, "
                + Recettes.KEY_type + " TEXT, "
                + Recettes.KEY_marque + " TEXT, "
                + Recettes.KEY_property + " TEXT)";

        db.execSQL(CREATE_TABLE_RECETTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Aliments.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Recettes.TABLE);

        // Create tables again
        onCreate(db);

    }

}
