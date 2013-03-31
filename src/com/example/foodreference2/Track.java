package com.example.foodreference2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Track extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.track, menu);
		return true;
	}

}
