package com.example.foodreference2;

import com.example.foodreference2.Reference;
import com.example.foodreference2.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

@SuppressWarnings("unused")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {    
		Intent intent = new Intent(this, view_foodcatagories.class);    
	startActivity(intent);}
	}