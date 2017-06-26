package com.huebeiro.stockontrol;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.huebeiro.database.DatabaseHelper;
import com.huebeiro.model.Expense;
import com.huebeiro.model.Product;
import com.huebeiro.util.Util;

import java.util.ArrayList;

public class ExpenseActivity extends AppCompatActivity {

    Spinner spinProduct;
    EditText editQuantity;
    EditText editNote;
    Button btnPurchase;
    Button btnCancel;

    ArrayList<Product> products;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        spinProduct = (Spinner) findViewById(R.id.spinProduct);
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
                registerExpense();
            }


        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void registerExpense() {
        final String note = editNote.getText().toString();
        int product;
        int quantity;
        int productQuanity;

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

        productQuanity = helper.getProductQuantity(product);

        if(productQuanity - quantity < 0){
            Util.alert("You have " + productQuanity + " units of this product in stock.", this, null);
            return;
        }

        String message = "Would you like to expend " + quantity + " units of the product " +
                products.get(spinProduct.getSelectedItemPosition()).getName() +
                " leaving you with " + (productQuanity - quantity) + " units?";

        final int fProduct = product;
        final int fQuantity = quantity;

        Util.ask(message, "Confirmation", this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query = "INSERT INTO " + Expense.TABLE_NAME + "(note, product, quantity) VALUES " +
                        "(\"" + note + "\", " + fProduct + ", " + fQuantity + ");";

                SQLiteDatabase database = helper.getWritableDatabase();
                helper.executeQuery(database, query);
                finish();
            }
        });
    }
}
