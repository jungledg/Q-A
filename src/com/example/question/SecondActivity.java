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
			{"ȫ��"},
			{"����������","����������"},
			{"��Դ1","��Դ2"},
			{"�Զ���1","�Զ���2"},
			{"����1","����2"},
			{"����1","����2"},
			{"����1","����2"},
			{"����1","����2"},
			{"��ѧ","����"},
			{"����1","����2"},
			{"����","���"},
			{"Ӣ��","����"},
			{"����","����"},
			{"����1","����2"},
			{"�������ѧ","�������"},
			{"����1","����2"},
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
		TabSpec tab1=tabHost.newTabSpec("tab1").setIndicator("��ҳ").setContent(R.id.tab1);
		tabHost.addTab(tab1);
		TabSpec tab2=tabHost.newTabSpec("tab2").setIndicator("�Ŷ�").setContent(R.id.tab2);
		tabHost.addTab(tab2);
		TabSpec tab3=tabHost.newTabSpec("tab3").setIndicator("�㳡").setContent(R.id.tab3);
		tabHost.addTab(tab3);
		TabSpec tab4=tabHost.newTabSpec("tab4").setIndicator("�ҵ�").setContent(R.id.tab4);
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
		Theme lisan = new Theme(R.drawable.lisan, "��ɢ��ѧ");
		themeList.add(lisan);
		Theme gailvlun = new Theme(R.drawable.gailvlun, "������");
		themeList.add(gailvlun);
		Theme cjiajia = new Theme(R.drawable.cjiajia, "C++");
		themeList.add(cjiajia);
		Theme cyuyan = new Theme(R.drawable.cyuyan, "C����");
		themeList.add(cyuyan);
		Theme gaoshu = new Theme(R.drawable.gaoshu, "�ߵ���ѧ");
		themeList.add(gaoshu);
		Theme  shudian= new Theme(R.drawable.shudian, "���ֵ�·");
		themeList.add(shudian);
		Theme shujujiegou = new Theme(R.drawable.shujujiegou, "���ݽṹ");
		themeList.add(shujujiegou);
		Theme wuli = new Theme(R.drawable.wuli, "��ѧ����");
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
	    	dialog1.setTitle("��ʾ");
	    	dialog1.setMessage("ȷ��Ҫ�뿪��(��_��)��");
	    	dialog1.setCancelable(true);
	    	dialog1.setPositiveButton("�ǵ�", new DialogInterface.OnClickListener(){
	    		public void onClick(DialogInterface dialog,int which){
	    			ActivityCollector.finishAll();
	    			
	    		}
	    	});
	    	dialog1.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					
					
				}
			});
	    	dialog1.show();
	    	
	    };

}
