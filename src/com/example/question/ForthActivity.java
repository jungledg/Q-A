package com.example.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.question.LoadingButton;


public class ForthActivity extends 	ActionBarActivity {

	private static int flag = 0;
	String place,lesson,teacher,month,day;
	int lined_num=0;			//目前排队人数
	int isLined=0;			    //记录是否排队
	Button mStartBtn;
    LoadingButton mDefaultLButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forth);
		
		isLined=0;
        Intent i = getIntent();
		
		lesson=i.getStringExtra("lesson");
		teacher=i.getStringExtra("teacher");
		place=i.getStringExtra("place");
		month=i.getStringExtra("month");
		day=i.getStringExtra("day");
		flag=i.getIntExtra("flag", flag);
		Button third_bt1 =	(Button) findViewById(R.id.Forth_teacher);
		TextView third_time = (TextView) findViewById(R.id.Forth_time);
		TextView third_place = (TextView) findViewById(R.id.Forth_place);
		TextView third_num = (TextView) findViewById(R.id.Forth_num);
		
		
		/* 
		 *  2015-5-11
		 * 	第三个Activity跳转
		 * 	传入place,lesson,teacher,month,day
		 *  
		 *  */
		third_bt1.setText(teacher);
		third_place.setText(third_place.getText()+place);
		third_time.setText(third_time.getText()+month+"."+day);
		third_num.setText("已排人数:"+lined_num);
		
		
		mDefaultLButton = (LoadingButton)this.findViewById(R.id.lbtn_default);
        mStartBtn = (Button)this.findViewById(R.id.btn_start);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDefaultLButton.setTargetProgress(360);
				
			}
		});
        mDefaultLButton.setCallback(new LoadingButton.Callback() {
            @Override
            public void complete() {
                Toast.makeText(getApplicationContext(),"排队完成！"+place,Toast.LENGTH_SHORT).show();//在这里写回调方法
                lined_num=lined_num+1;
                
                isLined=1;
                
                TextView third_num_another = (TextView) findViewById(R.id.Forth_num);
                third_num_another.setText("已排人数:"+lined_num);
                
               
            }
        });
	}

		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forth, menu);
		
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

	public void onBackPressed(){
		if(isLined==1)
		{
			Intent intent_result =new Intent();
	        intent_result.putExtra("result_lesson",lesson);
	        intent_result.putExtra("result_teacher", teacher);
	        intent_result.putExtra("result_month", month);
	        intent_result.putExtra("result_day", day);
	        intent_result.putExtra("result_place", place);
	        setResult(RESULT_OK,intent_result);
		}
		
        finish();
	}
	
}
