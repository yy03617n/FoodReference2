package com.example.foodreference2;

import java.net.URI;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class view_foodcatagories extends ListActivity {
	Context context = this;
	private boolean isinsubstate = false;
	Uri uri = Uri.parse("");
	String[] perams = {"*"};
	private String[] return_needed = {"category_id", "category_name"};
	Cursor ITEMS = FoodProvider.query(uri, perams, null, return_needed, "category");
	// REPLACE ABOVE NULL WITH DATABASE QUEREY FOR CATAGORY NAMES;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(context, R.layout.activity_result));
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			 if (!isinsubstate){
				ITEMS = FoodProvider.query(uri, iD, null, returned_column_Name, column_Name) ;
				//ABOVE NEEDS QUEREY TO SET ARRAY WITH ITEMS
				setListAdapter(new ArrayAdapter<String>(view_foodcatagories.this, R.layout.activity_result));
				isinsubstate = true;
				} else if (isinsubstate){
					Intent i = new Intent(context, detailed_view.class);
					i.putExtra("FOOD_NAME", cursor.moveToPosition(position));
					startActivity(i);
				}
				

			}

			
		});
	}

	// if current state == subcatagories than load catagories
	// else super on back pressd

	@Override
	public void onBackPressed(){
		if (isinsubstate){
			//ITEMS = ;
			//ABOVE DATABASE CALL FOR CATAGORIES
			setListAdapter(new ArrayAdapter<String>(context, R.layout.activity_view_list,ITEMS));
			isinsubstate = false;
		}else{
			super.onBackPressed();
		}
		
	}
}