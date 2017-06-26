package com.huebeiro.stockontrol;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.huebeiro.database.DatabaseHelper;

public class DBTestActivity extends AppCompatActivity {

    Button btnInsert, btnSelect, btnDelete, btnSelectType;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSelectType = (Button) findViewById(R.id.btnSelectType);
        helper = new DatabaseHelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = helper.getWritableDatabase();
                String query = "INSERT INTO Product(name, description, type) VALUES (\"Coé\", \"Rapaziadaaaaaaaaa\", 1)";
                helper.executeQuery(database, query);
                Toast.makeText(DBTestActivity.this, "Inserted...", Toast.LENGTH_SHORT).show();
                database.close();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = helper.getReadableDatabase();
                String query = "Select count(id) as total from Product";
                Cursor cursor = helper.selectQuery(database, query);
                if(cursor.moveToNext()){
                    int total = cursor.getInt(0);
                    Toast.makeText(DBTestActivity.this, "Total: " + total, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DBTestActivity.this, "Couldn't show total.", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                database.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = helper.getWritableDatabase();
                String query = "DELETE FROM ProductType WHERE id = 1";
                helper.executeQuery(database, query);
                Toast.makeText(DBTestActivity.this, "DELETED...", Toast.LENGTH_SHORT).show();
                database.close();
            }
        });

        btnSelectType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = helper.getReadableDatabase();
                String query = "Select count(id) as total from ProductType";
                Cursor cursor = helper.selectQuery(database, query);
                if(cursor.moveToNext()){
                    int total = cursor.getInt(0);
                    Toast.makeText(DBTestActivity.this, "Total: " + total, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DBTestActivity.this, "Couldn't show total.", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                database.close();
            }
        });
    }
}
