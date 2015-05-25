package com.example.question;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class ForthActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forth);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forth, menu);
		Intent i = getIntent();
		String place,lesson,teacher,month,day;
		lesson=i.getStringExtra("lesson");
		teacher=i.getStringExtra("teacher");
		place=i.getStringExtra("place");
		month=i.getStringExtra("month");
		day=i.getStringExtra("day");
		Button third_bt1 =	(Button) findViewById(R.id.Forth_teacher);
		TextView third_time = (TextView) findViewById(R.id.Forth_time);
		TextView third_place = (TextView) findViewById(R.id.Forth_place);
		TextView third_num = (TextView) findViewById(R.id.Forth_num);
		
		
		/* 
		 *  2015-5-11
		 * 	第四个Activity的跳转+界面
		 * 	传入选中课程的teacher.lesson.place.time等内容
		 *  
		 *  */
		third_bt1.setText(teacher);
		third_place.setText(third_place.getText()+place);
		third_time.setText(third_time.getText()+month+"."+day);
		third_num.setText(third_num.getText()+"0");
		return true;
	}

}
