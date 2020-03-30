package order.gas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GAS_ORDER";
    private static final String TABLE_NAME = "customers";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String ADDRESS = "adđress";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final int VERSION = 1;
    private Context context;
    public  DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    private String SQL_TABLE_CUSTOMERS = "CREATE TABLE " + TABLE_NAME + " (" +
            NAME + " TEXT, " +
            PHONE + " TEXT, " +
            ADDRESS + " TEXT, " +
            PASSWORD + " TEXT, " +
            USERNAME + " TEXT) ";


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_TABLE_CUSTOMERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addCustomer (Customer customer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,customer.getName());
        values.put(PHONE,customer.getPhone());
        values.put(ADDRESS,customer.getAddress());
        values.put(PASSWORD,customer.getPassword());
        values.put(USERNAME,customer.getUsername());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<Customer> getAllCustomers() {
        String[] columns = {
                NAME,
                PHONE,
                ADDRESS,
                PASSWORD,
                USERNAME
        };
        String sortOrder = NAME + " ASC";
        List<Customer> customerList = new ArrayList<Customer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                customer.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
                customer.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                customer.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
                customer.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
                // Adding user record to list
                customerList.add(customer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return customerList;
    }

    public boolean checkCustomer(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = PASSWORD + " = ?" + " AND " + USERNAME + " = ?";
        String[] columns = {
                NAME,
                PHONE,
                ADDRESS,
                PASSWORD,
                USERNAME
        };
        String[] selectionArgs = {password,username};

        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;

    }

    public boolean checkCustomer_phone (String phone) {
        String[] columns = {PHONE};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = PHONE + " = ? ";
        String[] selectionArgs = {phone};

        Cursor cursor = db.query(TABLE_NAME,
                                columns,                    //columns to return
                                selection,                  //columns for the WHERE clause
                                selectionArgs,              //The values for the WHERE clause
                                null,                       //group the rows
                                null,                      //filter by row groups
                                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkCustomer_username (String username) {
        String[] columns = {USERNAME};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = USERNAME + " = ? ";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_NAME,
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public String findPassword (String phone) {
        String messageError = "This phone number does not exist";
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = PHONE + " = ? ";
        String[] selectionArgs = {phone};

        Cursor cursor = db.query(TABLE_NAME,
                null,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);
        if (cursor.getCount()<1)
        {
            cursor.close();
            return messageError;
        }
        else {
            cursor.moveToFirst();
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            cursor.close();
            return password;
        }
    }
    public String returnName (String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = USERNAME + " = ? ";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_NAME,
                null,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);


            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            cursor.close();
            return name;


    }
    public String returnPhone (String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = USERNAME + " = ? ";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_NAME,
                null,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);

            cursor.moveToFirst();
            String phone = cursor.getString(cursor.getColumnIndex(PHONE));
            cursor.close();
            return phone;

    }

    public String returnAddress (String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = USERNAME + " = ? ";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_NAME,
                null,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);

            cursor.moveToFirst();
            String address= cursor.getString(cursor.getColumnIndex(ADDRESS));
            cursor.close();
            return address;

    }
}
