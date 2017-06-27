package com.huebeiro.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huebeiro.model.Product;
import com.huebeiro.stockontrol.R;

import java.util.ArrayList;

public class StockList extends CustomAdapter {

	private final ArrayList<Product> products;
	private final static int resourceID = R.layout.adapter_stock;
	private final static int containerID = R.id.adapterProduct;
	private final static int colId = R.id.txtId;
	private final static int colName = R.id.txtName;
	private final static int colQuantity = R.id.txtQuantity;

	public StockList(Context context, ArrayList<Product> products) {
		super(context, resourceID, containerID);
		this.products = products;
		if(products != null){
			for(Product product : this.products)
				super.add(product.getName());
		}
	}

	@NonNull
	@Override
	public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
	    View rowView = super.getView(position, convertView, parent);
	    //Gets the view with default resources and inflated
	    if(products.size() > position) {
	    	final Product product = products.get(position);
	    	//Populate the view's items with the arraylist content
            if(product.getId() != 0) {
                ((TextView) rowView.findViewById(colId)).setText(String.valueOf(product.getId()));
                ((TextView) rowView.findViewById(colName)).setText(product.getName());
                ((TextView) rowView.findViewById(colQuantity)).setText(String.valueOf(product.getQuantity()));
				if(product.getQuantity() == 0){
                    ((TextView) rowView.findViewById(colQuantity)).setTextColor(Color.RED);
                }
            } else {
                ((TextView) rowView.findViewById(colId)).setText("");
                ((TextView) rowView.findViewById(colName)).setText(product.getName());
				((TextView) rowView.findViewById(colQuantity)).setText("");
            }
		}
	    return rowView;
	}

}
