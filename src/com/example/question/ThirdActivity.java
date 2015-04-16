package com.example.question;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebView.FindListener;
import android.widget.Button;

public class ThirdActivity extends Activity {
	
		private String shopname;
	public static void actionstart(Context context,String name){
		Intent intent = new Intent(context,ThirdActivity.class);
		intent.putExtra("name", name);
		context.startActivity(intent);
		
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		ActivityCollector.addActivity(this);
		Button bt = (Button) findViewById(R.id.third_bt1);
		Intent i = getIntent();
		shopname = i.getStringExtra("name");
		bt.setText(shopname);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}

}
