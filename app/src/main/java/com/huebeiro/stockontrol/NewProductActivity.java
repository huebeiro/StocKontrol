package com.huebeiro.stockontrol;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.huebeiro.database.DatabaseHelper;
import com.huebeiro.model.ProductType;

import java.util.ArrayList;

public class NewProductActivity extends AppCompatActivity {

    EditText editName;
    EditText editDescription;
    Spinner spinType;
    Button btnRegister;
    Button btnCancel;
    ArrayList<ProductType> types;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        editName = (EditText) findViewById(R.id.editName);
        editDescription = (EditText) findViewById(R.id.editDescription);
        spinType = (Spinner) findViewById(R.id.spinType);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = types.get(spinType.getSelectedItemPosition()).getId();
                String query =
                        "INSERT INTO Product(name, description, type) VALUES " +
                        "(\"" + editName.getText() +"\", \"" + editDescription.getText() + "\", " + type + ");";
                SQLiteDatabase database = helper.getWritableDatabase();
                helper.executeQuery(database, query);
                database.close();
                finish();
            }
        });
        helper = new DatabaseHelper(this);
        types = helper.getProductTypes();
        ArrayAdapter<ProductType> adp = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, types);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinType.setAdapter(adp);
    }

}
