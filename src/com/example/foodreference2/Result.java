package com.example.foodreference2;

import com.example.foodreference2.Reference;
import com.example.foodreference2.MainActivity;
import com.example.foodreference2.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class Result extends Activity {    
	@SuppressLint("NewApi")    
	@Override    
	public void onCreate(Bundle savedInstanceState) {    
		super.onCreate(savedInstanceState);    
		// Get the message from the intent    
		Intent intent = getIntent();    
		String message = intent.getStringExtra(Reference.EXTRA_MESSAGE);    
		// Create the text view    
		TextView textView = new TextView(this);    
		textView.setTextSize(40);    
		textView.setText(message);    
		// Set the text view as the activity layout    
	setContentView(textView);}
	
	@Override    
		public boolean onOptionsItemSelected(MenuItem item) {        
		switch (item.getItemId()) {        
		case android.R.id.home:NavUtils.navigateUpFromSameTask(this);            
			return true;        
			}        
		return super.onOptionsItemSelected(item);    
		}
}