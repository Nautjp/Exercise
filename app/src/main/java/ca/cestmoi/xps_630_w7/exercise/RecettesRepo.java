package ca.cestmoi.xps_630_w7.exercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class RecettesRepo {
    private DBHelper dbHelper;

    public RecettesRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Recettes recettes) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Recettes.KEY_title, recettes.title);
        values.put(Recettes.KEY_time,recettes.time);
        values.put(Recettes.KEY_description, recettes.description);
        values.put(Recettes.KEY_moreinfo, recettes.moreinfo);

        // Inserting Row
        long recettes_Id = db.insert(Recettes.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) recettes_Id;
    }

    public void delete(int recettes_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Recettes.TABLE, Recettes.KEY_ID + "= ?", new String[] { String.valueOf(recettes_Id) });
        db.close(); // Closing database connection
    }

    public void update(Recettes recettes) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Recettes.KEY_title, recettes.title);
        values.put(Recettes.KEY_time,recettes.time);
        values.put(Recettes.KEY_description, recettes.description);
        values.put(Recettes.KEY_moreinfo, recettes.moreinfo);


        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Recettes.TABLE, values, Recettes.KEY_ID + "= ?", new String[] { String.valueOf(recettes.recettes_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getRecettesList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Recettes.KEY_ID + "," +
                Recettes.KEY_title + "," +
                Recettes.KEY_time + "," +
                Recettes.KEY_description + "," +
                Recettes.KEY_moreinfo +
                " FROM " + Recettes.TABLE;

        //Recettes recettes = new Recettes();
        ArrayList<HashMap<String, String>> recettesList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> recettes = new HashMap<String, String>();
                recettes.put("id", cursor.getString(cursor.getColumnIndex(Recettes.KEY_ID)));
                recettes.put("name", cursor.getString(cursor.getColumnIndex(Recettes.KEY_title)));
                recettesList.add(recettes);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return recettesList;

    }

    public Recettes getRecettesById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Recettes.KEY_ID + "," +
                Recettes.KEY_title + "," +
                Recettes.KEY_description + "," +
                Recettes.KEY_time + "," +
                Recettes.KEY_moreinfo +
                " FROM " + Recettes.TABLE
                + " WHERE " +
                Recettes.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Recettes recettes = new Recettes();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                recettes.recettes_ID =cursor.getInt(cursor.getColumnIndex(Recettes.KEY_ID));
                recettes.title =cursor.getString(cursor.getColumnIndex(Recettes.KEY_title));
                recettes.description  =cursor.getString(cursor.getColumnIndex(Recettes.KEY_description));
                recettes.time =cursor.getInt(cursor.getColumnIndex(Recettes.KEY_time));
                recettes.moreinfo =cursor.getString(cursor.getColumnIndex(Recettes.KEY_moreinfo));


            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return recettes;
    }

}
