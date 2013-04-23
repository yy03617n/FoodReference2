package com.example.foodreference2;

import com.example.foodreference2.MainActivity;
import com.example.foodreference2.R;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;



public class Result extends Activity {   
	ListView listResults;
	Context context = this;
	String selection = "iD";
	String[] cat_projection = new String[] {"category", "iD"};
	String[] food_projection = new String[] {"food", "iD"};
	Uri cat_uri = Uri.parse("content://com.FoodReference2.FoodProvider/category");	
	Uri food_uri = Uri.parse("content://com.FoodReference2.FoodProvider/food");
	
	
	private boolean isinsubstate = false;
	
	String[] perams = {"*"};
	private String[] return_needed = {"category_id", "category_name"};
	Cursor cursor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		listResults = (ListView)findViewById(R.id.listResults);

       String[] arrayColumns = new String[]{""};
		
       int[] arrayViewID = new int[]{R.id.item_entry};
       
       
       
       cursor = getContentResolver().query(cat_uri, cat_projection, selection, null, null);
       
       SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_entry,cursor,arrayColumns,arrayViewID);

       listResults.setAdapter(adapter); 
       
       listResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				cursor.moveToPosition(position);
				if (!isinsubstate){
				String[] temp = {cursor.getString(cursor.getColumnIndex("iD"))};
				cursor = getContentResolver().query(food_uri, food_projection, selection, temp, null) ;
				//ABOVE NEEDS QUEREY TO SET ARRAY WITH ITEMS
				setListAdapter(new ArrayAdapter<String>(Result.this, R.layout.activity_result));
				isinsubstate = true;
				} else if (isinsubstate){
					Intent i = new Intent(context, detailed_view.class);
					i.putExtra("FOOD_NAME", cursor.getSt );
					startActivity(i);
				}
				

			}

			
		});
       
    		   
	}
	
	
	
	@Override    
		public boolean onOptionsItemSelected(MenuItem item) {        
		switch (item.getItemId()) {        
		case android.R.id.home:NavUtils.navigateUpFromSameTask(this);            
			return true;        
			}        
		return super.onOptionsItemSelected(item);    
		}
}
