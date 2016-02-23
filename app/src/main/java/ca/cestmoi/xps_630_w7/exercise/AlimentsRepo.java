package ca.cestmoi.xps_630_w7.exercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class AlimentsRepo {
    private DBHelper dbHelper;

    public AlimentsRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Aliments aliments) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Aliments.KEY_qte, aliments.qte);
        values.put(Aliments.KEY_marque,aliments.marque);
        values.put(Aliments.KEY_name, aliments.name);
        values.put(Aliments.KEY_type, aliments.type);
        values.put(Aliments.KEY_property, aliments.property);

        // Inserting Row
        long aliments_Id = db.insert(Aliments.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) aliments_Id;
    }

    public void delete(int aliments_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Aliments.TABLE, Aliments.KEY_ID + "= ?", new String[] { String.valueOf(aliments_Id) });
        db.close(); // Closing database connection
    }

    public void update(Aliments aliments) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Aliments.KEY_qte, aliments.qte);
        values.put(Aliments.KEY_marque,aliments.marque);
        values.put(Aliments.KEY_name, aliments.name);
        values.put(Aliments.KEY_type, aliments.type);
        values.put(Aliments.KEY_property, aliments.property);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Aliments.TABLE, values, Aliments.KEY_ID + "= ?", new String[] { String.valueOf(aliments.aliments_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getAlimentsList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Aliments.KEY_ID + "," +
                Aliments.KEY_name + "," +
                Aliments.KEY_marque + "," +
                Aliments.KEY_qte + "," +
                Aliments.KEY_type + "," +
                Aliments.KEY_property +
                " FROM " + Aliments.TABLE;

        //Aliments aliments = new Aliments();
        ArrayList<HashMap<String, String>> alimentsList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> aliments = new HashMap<String, String>();
                aliments.put("id", cursor.getString(cursor.getColumnIndex(Aliments.KEY_ID)));
                aliments.put("name", cursor.getString(cursor.getColumnIndex(Aliments.KEY_name)));
                alimentsList.add(aliments);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return alimentsList;

    }

    public Aliments getAlimentsById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Aliments.KEY_ID + "," +
                Aliments.KEY_name + "," +
                Aliments.KEY_marque + "," +
                Aliments.KEY_qte + "," +
                Aliments.KEY_type + "," +
                Aliments.KEY_property +
                " FROM " + Aliments.TABLE
                + " WHERE " +
                Aliments.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Aliments aliments = new Aliments();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                aliments.aliments_ID =cursor.getInt(cursor.getColumnIndex(Aliments.KEY_ID));
                aliments.name =cursor.getString(cursor.getColumnIndex(Aliments.KEY_name));
                aliments.marque  =cursor.getString(cursor.getColumnIndex(Aliments.KEY_marque));
                aliments.qte =cursor.getInt(cursor.getColumnIndex(Aliments.KEY_qte));
                aliments.type =cursor.getString(cursor.getColumnIndex(Aliments.KEY_type));
                aliments.property =cursor.getString(cursor.getColumnIndex(Aliments.KEY_property));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return aliments;
    }

}
