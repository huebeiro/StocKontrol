package com.huebeiro.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.huebeiro.model.Expense;
import com.huebeiro.model.ExpenseItem;
import com.huebeiro.model.Product;
import com.huebeiro.model.ProductType;
import com.huebeiro.model.Purchase;
import com.huebeiro.model.PurchaseItem;

import java.util.ArrayList;

/**
 * Author: adilson
 * Date: 20/06/17
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE = "StocKontrol.db";
    private static final String[] DATABASE_SCRIPT = {
            "CREATE TABLE " + ProductType.TABLE_NAME + " ( " +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    name TEXT " +
                    ");",
            "INSERT INTO " + ProductType.TABLE_NAME + "(name) VALUES " +
                    "(\"Office Supply\")," +
                    "(\"Cleaning Supply\")," +
                    "(\"Edibles\")," +
                    "(\"Hygiene\")" +
                    ";",
            "CREATE TABLE " + Product.TABLE_NAME + " ( " +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    name TEXT, " +
                    "    description TEXT, " +
                    "    type INTEGER, " +
                    "    FOREIGN KEY(type) REFERENCES " + ProductType.TABLE_NAME + "(id) ON DELETE CASCADE " +
                    ");",
            "INSERT INTO Product(name, description, type) VALUES " +
                    "(\"Pencil\", \"2B Yellow Pencil\", 1)," +
                    "(\"Rag\", \"White Rag\", 2)," +
                    "(\"Dorinhos\", \"Corn nacho snack\", 3)," +
                    "(\"Soap\", \"Roses soap bar\", 4)",
            "CREATE TABLE " + Purchase.TABLE_NAME + " ( " +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    note TEXT, " +
                    "    date TEXT " +
                    ");",
            "CREATE TABLE " + PurchaseItem.TABLE_NAME + " ( " +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    purchase INTEGER, " +
                    "    product INTEGER, " +
                    "    quantity INTEGER, " +
                    "    price REAL, " +
                    "    FOREIGN KEY(purchase) REFERENCES " + Purchase.TABLE_NAME + "(id) ON DELETE CASCADE, " +
                    "    FOREIGN KEY(product) REFERENCES " + Product.TABLE_NAME + "(id) ON DELETE CASCADE " +
                    ");",
            "CREATE TABLE " + Expense.TABLE_NAME + " ( " +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    note TEXT, " +
                    "    date TEXT " +
                    ");",
            "CREATE TABLE " + ExpenseItem.TABLE_NAME + " ( " +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    purchase INTEGER, " +
                    "    product INTEGER, " +
                    "    quantity INTEGER, " +
                    "    price REAL, " +
                    "    FOREIGN KEY(purchase) REFERENCES TIPO(id) ON DELETE CASCADE, " +
                    "    FOREIGN KEY(product) REFERENCES TIPO(id) ON DELETE CASCADE " +
                    ");"
    };

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    public Cursor selectQuery(SQLiteDatabase database, String sqlQuery){
        return database.rawQuery(sqlQuery, null);
    }

    public void executetQuery(SQLiteDatabase database, String sqlQuery){
        database.execSQL(sqlQuery);
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(Product.TABLE_NAME,new String[]{
                "id",
                "name",
                "description",
                "type"
        }, null ,null, null, null, "id");
        while (cursor.moveToNext()){
            Product product = new Product();
            product.setId(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setDescription(cursor.getString(2));
            product.setType(cursor.getInt(3));
            products.add(product);
        }
        cursor.close();
        database.close();
        return products;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String script : DATABASE_SCRIPT){
            db.execSQL(script);
        }
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE " + ExpenseItem.TABLE_NAME);
            db.execSQL("DROP TABLE " + Expense.TABLE_NAME);
            db.execSQL("DROP TABLE " + PurchaseItem.TABLE_NAME);
            db.execSQL("DROP TABLE " + Purchase.TABLE_NAME);
            db.execSQL("DROP TABLE " + Product.TABLE_NAME);
            db.execSQL("DROP TABLE " + ProductType.TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
