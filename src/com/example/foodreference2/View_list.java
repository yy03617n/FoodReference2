package com.example.foodreference2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class View_list extends Activity {
	Context context = this;
	private boolean isinsubstate = false;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_view_list, menu);
		return true;
	}

}
