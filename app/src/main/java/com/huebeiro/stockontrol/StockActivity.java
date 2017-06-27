package com.huebeiro.stockontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.huebeiro.adapter.StockList;
import com.huebeiro.database.DatabaseHelper;
import com.huebeiro.model.Product;

import java.util.ArrayList;

public class StockActivity extends AppCompatActivity {

    private ArrayList<Product> products;
    private StockList adapter;
    private DatabaseHelper helper;
    private ListView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        helper = new DatabaseHelper(this);
        mainList = (ListView) findViewById(R.id.mainList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateList();
    }

    private void updateList(){
        products = helper.getProductsQuantity();
        adapter = new StockList(this, products);
        mainList.setAdapter(adapter);
    }
}
