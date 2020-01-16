package Data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Statya;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STATIY_TABLE = "CREATE TABLE " + Util.TABLE_NAME +"("
                +Util.KEY_ID + " INTEGER PRIMARY KEY, "
                +Util.KEY_ZAG + " TEXT, "
                +Util.KEY_TEKST+ " TEXT"+")";

        db.execSQL(CREATE_STATIY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);
    onCreate(db);
    }

    public void addStaiya(Statya statya){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_ZAG, statya.getZag());
        contentValues.put(Util.KEY_TEKST, statya.getTekst());
        db.insert(Util.TABLE_NAME, null, contentValues);

        db.close();
    }
    public Statya getStatya(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.KEY_ZAG, Util.KEY_TEKST},Util.KEY_ID+"=?", new String[] {String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
           cursor.moveToFirst();
        }
        Statya statya = new Statya(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return statya;
    }
    public List<Statya> getAllStatyas(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Statya> statyaList = new ArrayList<>();
        String selectAllStatyas="SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllStatyas,null);
        if(cursor.moveToFirst()){
            do{
                Statya statya = new Statya();
                statya.setId(Integer.parseInt(cursor.getString(0)));
                statya.setZag(cursor.getString(1));
                statya.setTekst(cursor.getString(2));
                statyaList.add(statya);
            }
            while (cursor.moveToNext());

        }
        return statyaList;
    }
}
