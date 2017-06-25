package com.huebeiro.stockontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.huebeiro.adapter.MenuAdapter;
import com.huebeiro.adapter.MenuItem;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity
implements View.OnClickListener{

    private RecyclerView recyclerView;
    private ArrayList<MenuItem> items;
    private MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recyclerView = (RecyclerView) findViewById(R.id.gridMenu);
        items = new ArrayList<>();
        items.add(
                new MenuItem(R.drawable.ic_register, "Register")
        );
        items.add(
                new MenuItem(R.drawable.ic_buy, "Purchase")
        );
        items.add(
                new MenuItem(R.drawable.ic_consume, "Expend")
        );
        items.add(
                new MenuItem(R.drawable.ic_shelf, "Stock")
        );
        items.add(
                new MenuItem(R.drawable.ic_graph, "Dashboards")
        );

        adapter = new MenuAdapter(items, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }


    /**
     * Called when a recyclerView has been clicked.
     *
     * @param v The recyclerView that was clicked.
     */
    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        switch (position){
            case 0:
                Intent intent = new Intent(this, ProductActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "Item clicked: " + position, Toast.LENGTH_SHORT).show();
        }
    }
}
