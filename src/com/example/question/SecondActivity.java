package com.example.question;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class SecondActivity extends TabActivity {
	
	private TabHost tabHost=null;
	private Spinner sipnner01=null,spinner02=null;
	private Context context;
	private String[][] major=new String[][]{
			{"全部"},
			{"飞行器制造","飞行器动力"},
			{"能源1","能源2"},
			{"自动化1","自动化2"},
			{"电子1","电子2"},
			{"机电1","机电2"},
			{"材料1","材料2"},
			{"飞行1","飞行2"},
			{"数学","物理"},
			{"经管1","经管2"},
			{"法律","社会"},
			{"英语","日语"},
			{"唱歌","跳舞"},
			{"航天1","航天2"},
			{"计算机科学","软件工程"},
			{"国教1","国教2"},
	};
	private ArrayAdapter<CharSequence> adapter_major=null;
	private List<Theme> themeList = new ArrayList<Theme>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		ActivityCollector.addActivity(this);
		initTheme();
		ThemeAdapter adapater_theme = new ThemeAdapter(SecondActivity.this, R.layout.theme, themeList);
		tabHost=getTabHost();
		TabSpec tab1=tabHost.newTabSpec("tab1").setIndicator("首页").setContent(R.id.tab1);
		tabHost.addTab(tab1);
		TabSpec tab2=tabHost.newTabSpec("tab2").setIndicator("排队").setContent(R.id.tab2);
		tabHost.addTab(tab2);
		TabSpec tab3=tabHost.newTabSpec("tab3").setIndicator("广场").setContent(R.id.tab3);
		tabHost.addTab(tab3);
		TabSpec tab4=tabHost.newTabSpec("tab4").setIndicator("我的").setContent(R.id.tab4);
		tabHost.addTab(tab4);
		context = this;
		
	
		sipnner01 = (Spinner)findViewById(R.id.Spinner01);
		spinner02=(Spinner) findViewById(R.id.Spinner02);
		this.sipnner01.setOnItemSelectedListener(new MySpinnerListener());
		Button bt1=(Button) findViewById(R.id.visit1);
		bt1.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.baidu.com"));
				startActivity(intent);
			}
		});
		
	   
		ListView listview_data = (ListView) findViewById(R.id.listView1);
		listview_data.setAdapter(adapater_theme);
		listview_data.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Theme theme =themeList.get(position);
					Intent intent2 = new Intent(SecondActivity.this,ThirdActivity.class);
					intent2.putExtra("name", theme.getname());
					startActivity(intent2);
			}
			
		});
	}

	private void initTheme() {
		Theme lisan = new Theme(R.drawable.lisan, "离散数学");
		themeList.add(lisan);
		Theme gailvlun = new Theme(R.drawable.gailvlun, "概率论");
		themeList.add(gailvlun);
		Theme cjiajia = new Theme(R.drawable.cjiajia, "C++");
		themeList.add(cjiajia);
		Theme cyuyan = new Theme(R.drawable.cyuyan, "C语言");
		themeList.add(cyuyan);
		Theme gaoshu = new Theme(R.drawable.gaoshu, "高等数学");
		themeList.add(gaoshu);
		Theme  shudian= new Theme(R.drawable.shudian, "数字电路");
		themeList.add(shudian);
		Theme shujujiegou = new Theme(R.drawable.shujujiegou, "数据结构");
		themeList.add(shujujiegou);
		Theme wuli = new Theme(R.drawable.wuli, "大学物理");
		themeList.add(wuli);
	}

	private class MySpinnerListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			adapter_major=new ArrayAdapter<CharSequence>(SecondActivity.this,android.R.layout.simple_spinner_item,major[arg2]);
			spinner02.setAdapter(adapter_major);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}
	 public void onBackPressed(){
	    	AlertDialog.Builder dialog1 = new AlertDialog.Builder(SecondActivity.this);
	    	dialog1.setTitle("提示");
	    	dialog1.setMessage("确定要离开吗(⊙_⊙)？");
	    	dialog1.setCancelable(true);
	    	dialog1.setPositiveButton("是的", new DialogInterface.OnClickListener(){
	    		public void onClick(DialogInterface dialog,int which){
	    			ActivityCollector.finishAll();
	    			
	    		}
	    	});
	    	dialog1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					
					
				}
			});
	    	dialog1.show();
	    	
	    };

}
