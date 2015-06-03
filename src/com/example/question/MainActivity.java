package com.example.question;

import com.example.question.R;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText accountEdit;
	private EditText passwordEdit;
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private CheckBox rememberPass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActivityCollector.addActivity(this);
		
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		accountEdit = (EditText) findViewById(R.id.num1);
		passwordEdit = (EditText) findViewById(R.id.num2);
		rememberPass = (CheckBox) findViewById(R.id.cB01);
		boolean isRemember = pref.getBoolean("remember_password", false);
		if(isRemember)
		{
			String account = pref.getString("account","");
			String password = pref.getString("password","");
			accountEdit.setText(account); 
			passwordEdit.setText(password);
			rememberPass.setChecked(true);
		}
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
				editor = pref.edit();
				if(rememberPass.isChecked()){
					editor.putBoolean("remember_password", true);
					editor.putString("account",account);
					editor.putString("password",password);
				}else{
					editor.clear();
				}
				editor.commit();
			Intent intent01=new Intent(this,SecondActivity.class);
			this.startActivity(intent01);
		}else{
			Toast.makeText(MainActivity.this, "密码错误!",Toast.LENGTH_SHORT).show();
		}
		
		
		
		
	}
}
