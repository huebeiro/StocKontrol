package com.huebeiro.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.huebeiro.model.Product;
import com.huebeiro.stockontrol.R;

import java.util.ArrayList;

public class ProductList extends CustomAdapter {

	private final ArrayList<Product> products;
	private final static int resourceID = R.layout.adapter_product;
	private final static int containerID = R.id.adapterProduct;
	private final static int colId = R.id.txtId;
	private final static int colName = R.id.txtName;
	private final static int colButton = R.id.imgDelete;

	public ProductList(Context context, ArrayList<Product> products) {
		super(context, resourceID, containerID);
		this.products = products;
		if(products != null){
			for(Product product : this.products)
				super.add(product.getName());
		}
	}

    public void onDelete(int id){

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
                rowView.findViewById(colButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDelete(product.getId());
                    }
                });
            } else {
                ((TextView) rowView.findViewById(colId)).setText("");
                ((TextView) rowView.findViewById(colName)).setText(product.getName());
                rowView.findViewById(colButton).setVisibility(View.INVISIBLE);
            }
		}
	    return rowView;
	}

}
