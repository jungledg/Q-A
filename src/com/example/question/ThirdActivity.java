package com.example.question;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ThirdActivity extends Activity {
	
	
	String place,lesson,teacher,month,day;
	int imgId,count;
	
	private List<Line> lineList = new ArrayList<Line>();
	
	public static void actionstart(Context context,String name){
		Intent intent = new Intent(context,ThirdActivity.class);
		intent.putExtra("name", name);
		context.startActivity(intent);
		
		}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paidui);
		
		
		Intent i = getIntent();
		
		imgId=i.getExtras().getInt("img");
		lesson=i.getStringExtra("name");
		teacher=i.getStringExtra("teacher");
		place=i.getStringExtra("place");
		month=i.getStringExtra("month");
		day=i.getStringExtra("day");
		System.out.println(teacher);
		count=i.getExtras().getInt("count");
		initLine();
		
		LineAdapater adapter_queue = new LineAdapater(ThirdActivity.this, R.layout.paidui, lineList);
		ListView listview_queue = (ListView) findViewById(R.id.queue_listView2);
		listview_queue.setAdapter(adapter_queue);
		
		listview_queue.setOnItemClickListener(new OnItemClickListener() {
			
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent4=new Intent(ThirdActivity.this,ForthActivity.class);
				intent4.putExtra("lesson", lesson);
				intent4.putExtra("teacher", teacher);
				intent4.putExtra("place", place);
				intent4.putExtra("month", month);
				intent4.putExtra("day", day);
				startActivity(intent4);
			}
		});
		
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}
	
	private void initLine(){
		Line num1=new Line(imgId, lesson, teacher, place, month, day);
		lineList.add(num1);
	}
	

}
