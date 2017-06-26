package com.huebeiro.stockontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.huebeiro.adapter.ProductList;
import com.huebeiro.database.DatabaseHelper;
import com.huebeiro.model.Product;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    private ArrayList<Product> products;
    private ProductList adapter;
    private DatabaseHelper helper;
    private ListView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, NewProductActivity.class);
                startActivity(intent);
            }
        });
        mainList = (ListView) findViewById(R.id.mainList);
        helper = new DatabaseHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateList();
    }

    private void updateList(){
        products = helper.getProducts();
        if(products.size() == 0){
            Product product = new Product();
            product.setId(0);
            product.setName("No products");
        }
        adapter = new ProductList(this, products){
            @Override
            public void onDelete(int id) {
                helper.deleteProduct(id);
                updateList();
            }
        };
        mainList.setAdapter(adapter);
    }
}
