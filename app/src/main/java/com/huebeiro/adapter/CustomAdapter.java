package com.huebeiro.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CustomAdapter extends ArrayAdapter<String> {

	private int selectedIndex = -1;
	private int textViewResourceId;
	private int resourceId;
	private Context context;
	private final int selectedColor = Color.rgb(33, 149, 150); //Background color for select lines
	private final int evenColor = Color.argb(10, 255, 255, 255); //Background color for even lines
	
	
	public CustomAdapter(Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
		this.context = context;
		this.resourceId = resource;
		this.textViewResourceId = textViewResourceId;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, @NonNull ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(resourceId, parent, false);
	    if(position % 2 == 1){
	    	rowView.findViewById(textViewResourceId).setBackgroundColor(evenColor);
	    }
	    
	    if(position == selectedIndex){
	    	rowView.findViewById(textViewResourceId).setBackgroundColor(selectedColor);
	    }
	    return rowView;
	}
		
	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
}
