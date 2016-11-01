package droid.zaeem.notifierx.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Droid on 8/1/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "notifierxdatabase";

    // Contacts table name
    private static final String TABLE_INBOX = "inbox";
    private static final String TABLE_IMPORTANT = "important";
    private static final String TABLE_STARRED = "starred";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_BODY = "body";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INBOX_TABLE = "CREATE TABLE " + TABLE_INBOX + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_BODY + " TEXT" + ")";

        String CREATE_IMPORTANT_TABLE = "CREATE TABLE " + TABLE_IMPORTANT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_BODY + " TEXT" + ")";

        String CREATE_STARRED_TABLE = "CREATE TABLE " + TABLE_STARRED + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_BODY + " TEXT" + ")";

        db.execSQL(CREATE_INBOX_TABLE);
        db.execSQL(CREATE_IMPORTANT_TABLE);
        db.execSQL(CREATE_STARRED_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INBOX);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMPORTANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STARRED);


        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addMessage(Message message,String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,message.getTitle());
        values.put(KEY_BODY,message.getBody());
        // Inserting Row
        db.insert(table, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Message getMessage(int id,String table) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table, new String[] { KEY_ID,
                        KEY_TITLE, KEY_BODY}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Message message = new Message(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return message;
    }

    // Getting All Contacts
    public List<Message> getAllMessages(String table) {
        List<Message> messageList = new ArrayList<Message>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + table;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Message message= new Message();
                message.setId(Integer.parseInt(cursor.getString(0)));
                message.setTitle(cursor.getString(1));
                message.setBody(cursor.getString(2));
                messageList.add(message);
            } while (cursor.moveToNext());
        }

        // return contact list
        return messageList;
    }

    // Updating single contact
    public int updateMessage(Message message,String table) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, message.getTitle());
        values.put(KEY_BODY, message.getBody());

        // updating row
        return db.update(table, values, KEY_ID + " = ?",
                new String[] { String.valueOf(message.getId()) });
    }

    // Deleting single contact
    public void deleteMessage(Message message,String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, KEY_ID + " = ?",
                new String[] { String.valueOf(message.getId()) });
        db.close();
    }


    // Getting contacts Count
    public int getMessagesCount(String table) {
        String countQuery = "SELECT  * FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}