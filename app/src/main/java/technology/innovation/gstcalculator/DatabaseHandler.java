package technology.innovation.gstcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "gst_calculator";

    // Contacts table name
    private static final String TABLE_GST = "gst";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_BASE = "base";
    private static final String KEY_CGST = "cgst";
    private static final String KEY_SGST = "sgst";
    private static final String KEY_TGST= "tgst";
    private static final String KEY_TOTAL = "total";
    private static final String KEY_DATE= "date";
    private static final String KEY_GST_TYPE= "gstType";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_GST + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_AMOUNT+ " TEXT,"
                + KEY_BASE  + " TEXT,"
                + KEY_CGST + " TEXT,"
                + KEY_SGST + " TEXT,"
                + KEY_TGST + " TEXT,"
                + KEY_TOTAL + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_GST_TYPE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GST);

        // Create tables again
        onCreate(db);
    }


    // Delete table records
    public void deleteRecords() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_GST);

    }


    // Adding new Expenses
    public void addExpenses(GSTPojo gstPojo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, gstPojo.getAmount());
        values.put(KEY_BASE, gstPojo.getBase());
        values.put(KEY_CGST, gstPojo.getCgst());
        values.put(KEY_SGST, gstPojo.getSgst());
        values.put(KEY_TGST, gstPojo.getTgst());
        values.put(KEY_TOTAL, gstPojo.getTotal());
        values.put(KEY_DATE, gstPojo.getDate());
        values.put(KEY_GST_TYPE, gstPojo.getGstType());

        // Inserting Row
        db.insert(TABLE_GST, null, values);
        db.close(); // Closing database connection
    }


    // Getting All GST
    public List<GSTPojo> getAllGST() {
        List<GSTPojo> gstPojoList = new ArrayList<GSTPojo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GST+ " ORDER BY date DESC LIMIT 30";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GSTPojo gstPojo = new GSTPojo();
                gstPojo.setId(Integer.parseInt(cursor.getString(0)));
                gstPojo.setAmount(cursor.getString(1));
                gstPojo.setBase(cursor.getString(2));
                gstPojo.setCgst(cursor.getString(3));
                gstPojo.setSgst(cursor.getString(4));
                gstPojo.setTgst(cursor.getString(5));
                gstPojo.setTotal(cursor.getString(6));
                gstPojo.setDate(cursor.getString(7));
                gstPojo.setGstType(cursor.getString(8));


                gstPojoList.add(gstPojo);
            } while (cursor.moveToNext());
        }

        // return gst list
        return gstPojoList;
    }



}