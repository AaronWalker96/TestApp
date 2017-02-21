package uk.ac.tees.q5045715.lecturemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by q5045715 on 07/02/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "Lecturers.db";

    // Contacts table name
    private static final String TABLE_NAME = "lecturers";

    // Contacts Table Columns names
    private static final String COL_ID = "_id";     // Primary key column must be _id
    private static final String COL_NAME = "name";
    private static final String COL_PHONE = "phone";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Generate Create SQL Statement
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "
                + TABLE_NAME
                + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT,"
                + COL_PHONE + " TEXT" + ")";

        // Execute/run create SQL statement
        db.execSQL(CREATE_CONTACTS_TABLE);

        Log.d("Database", "Database Created.");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldNum, int newNum) {
        // Drop older table if exists and create fresh (deletes all data)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addLecturer(Lecturer lecturer){

        // Open database connection (for write)
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, lecturer.getName());
        values.put(COL_PHONE, lecturer.getPhoneNumber());
        // Id column not required (AutoIncrement)

        // Add record to database and get id of new record (must long integer).
        long id = db.insert(TABLE_NAME, null, values);

        db.close();  // Closing database connection

        return id; // Return id for new record

    }

    public List<Lecturer> getAll() {

        // Create empty list (in memory)
        List<Lecturer> list = new ArrayList<Lecturer>();
        // Connect to the database to read data
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate SQL SELECT statement
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        // Execute select statement
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {     // If data (records) available

            // Get position (index) of each of the column names.
            int idIdx = cursor.getColumnIndex(COL_ID);
            int nameIdx = cursor.getColumnIndex(COL_NAME);
            int phoneIdx = cursor.getColumnIndex(COL_PHONE);

            do {
                // Create lecturer object for current database record
                Lecturer lecturer = new Lecturer(
                        cursor.getInt(idIdx),
                        cursor.getString(nameIdx),
                        cursor.getString(phoneIdx)
                );

                // Add lecturer objext to list
                list.add(lecturer);

            } while (cursor.moveToNext());  // repeat until there are no more records
        }

        return list;
    }

    public void removeAll() {

        // Connect to the tables
        SQLiteDatabase db = this.getWritableDatabase();

        // Execute delete (drop) table SQL command
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // call create method to re-generate the table
        onCreate(db);
    }

    public void deleteLecturer(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                TABLE_NAME,                           // Param 1: Table name
                COL_ID + " = ?",                      // Param 2: where/filter clause
                new String[] { String.valueOf(id) }   // Param 3: Arguments (string) array
        );
        db.close();
    }

    public void updateLecturer(int ID, String name, String phone) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_PHONE, phone);

        db.update(
                TABLE_NAME,
                values,
                COL_ID + " = ?",
                new String[] { String.valueOf(ID) }
        );
        db.close();
    }

}