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
import com.huebeiro.model.Product;
import com.huebeiro.model.Purchase;
import com.huebeiro.util.Util;

import java.util.ArrayList;

public class PurchaseActivity extends AppCompatActivity {

    Spinner spinProduct;
    EditText editPrice;
    EditText editQuantity;
    EditText editNote;
    Button btnPurchase;
    Button btnCancel;

    ArrayList<Product> products;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        spinProduct = (Spinner) findViewById(R.id.spinProduct);
        editPrice = (EditText) findViewById(R.id.editPrice);
        editQuantity = (EditText) findViewById(R.id.editQuantity);
        editNote = (EditText) findViewById(R.id.editNote);
        btnPurchase = (Button) findViewById(R.id.btnPurchase);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        helper = new DatabaseHelper(this);
        products = helper.getProducts();

        ArrayAdapter<Product> adp = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, products);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinProduct.setAdapter(adp);

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPurchase();
            }


        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void registerPurchase() {
        final String note = editNote.getText().toString();
        int product;
        int quantity;
        double price;

        try{
            product = products.get(spinProduct.getSelectedItemPosition()).getId();
        } catch (Exception ex){
            Util.alert("Select a product.", this, null);
            return;
        }
        try{
            quantity = Integer.valueOf(editQuantity.getText().toString());
        } catch (Exception ex){
            Util.alert("Insert a valid quantity.", this, null);
            return;
        }
        try{
            price = Double.valueOf(editPrice.getText().toString().replace(',','.'));
        } catch (Exception ex){
            Util.alert("Insert a valid price.", this, null);
            return;
        }
        String message = "Would you like to buy " + quantity + " unities of the product " +
                products.get(spinProduct.getSelectedItemPosition()).getName() +
                " expending " + (quantity * price) + " in total?";

        final int fProduct = product;
        final int fQuantity = quantity;
        final double fPrice = price;

        Util.ask(message, "Confirmation", this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query = "INSERT INTO " + Purchase.TABLE_NAME + "(note, product, quantity, price) VALUES " +
                        "(\"" + note + "\", " + fProduct + ", " + fQuantity + ", " + fPrice + ");";

                SQLiteDatabase database = helper.getWritableDatabase();
                helper.executeQuery(database, query);
                finish();
            }
        });
    }
}
