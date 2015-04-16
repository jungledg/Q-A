package com.example.question;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText accountEdit;
	private EditText passwordEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActivityCollector.addActivity(this);
		accountEdit = (EditText) findViewById(R.id.num1);
		passwordEdit = (EditText) findViewById(R.id.num2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void Listen_Button(View source){
		
		String account = accountEdit.getText().toString();
		String password = passwordEdit.getText().toString();
		
		if(account.equals("123456")&&password.equals("123456"))
		{
			Intent intent01=new Intent(this,SecondActivity.class);
			this.startActivity(intent01);
		}else{
			Toast.makeText(MainActivity.this, "’À∫≈√‹¬Î¥ÌŒÛ£°",Toast.LENGTH_SHORT).show();
		}
		
		
		
		
	}
}
