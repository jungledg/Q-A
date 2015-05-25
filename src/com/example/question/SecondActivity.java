package com.example.question;

import java.util.ArrayList;
import java.util.List;

import com.example.question.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.TabActivity;
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
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class SecondActivity extends TabActivity {
	
	private TabHost tabHost=null;
	private Spinner spinner01=null,spinner02=null;
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
	private List<Theme> themeList = new ArrayList<Theme>();					//“首页”的适配器
	private List<Line> lineList = new ArrayList<Line>();					//“排队”的适配器
	private List<Square> squareList = new ArrayList<Square>();				//“广场”的适配器 
	ButtonMine buttonmine=null;												//我的 最上的按钮
	private int count =0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		ActivityCollector.addActivity(this);
		initTheme();														//初始化“我的”
		initLine();															//初始化"排队"
		initSquare();														//初始化"广场"
		
		ThemeAdapter adapater_theme = new ThemeAdapter(SecondActivity.this, R.layout.theme, themeList);
		LineAdapater adapter_queue = new LineAdapater(SecondActivity.this, R.layout.paidui, lineList);
		SquareAdapter adapter_square = new SquareAdapter(SecondActivity.this,R.layout.square,squareList);
		
		
		tabHost=getTabHost();
		TabSpec tab1=tabHost.newTabSpec("tab1").setIndicator("首页").setContent(R.id.shouye);
		tabHost.addTab(tab1);
		TabSpec tab2=tabHost.newTabSpec("tab2").setIndicator("排队").setContent(R.id.paidui);
		tabHost.addTab(tab2);
		TabSpec tab3=tabHost.newTabSpec("tab3").setIndicator("广场").setContent(R.id.guangchang);
		tabHost.addTab(tab3);
		TabSpec tab4=tabHost.newTabSpec("tab4").setIndicator("我的").setContent(R.id.mine);
		tabHost.addTab(tab4);
		
		int num = 0;
		String InfoName = "jungle";
		String InfoMajor = "软件工程";
		Boolean InfoSex = false;
		buttonmine=new ButtonMine(R.drawable.mine_icon_portrait, InfoName, InfoSex, InfoMajor);
		View headview=getLayoutInflater().inflate(R.layout.headview, null); 				//"排队"中的head
		//-------------------“排队”中两个下拉控件-------------------------
		spinner01 = (Spinner)headview.findViewById(R.id.spinner01);
		spinner02=(Spinner) headview.findViewById(R.id.spinner02);
		this.spinner01.setOnItemSelectedListener(new MySpinnerListener());
		//--------------------“我的”中三个按钮-----------------------------
		Button bt1_mine=(Button) findViewById(R.id.bt1_mine);						//排队号码			
		Button bt2_mine=(Button) findViewById(R.id.bt2_mine);						//已排课程
		Button bt3_mine=(Button) findViewById(R.id.bt3_mine);						//信誉值
		Button bt_mine=(Button) findViewById(R.id.bt_mine);
		bt_mine.setBackgroundResource(R.drawable.background17);
		bt_mine.setText(buttonmine.getName()+"\n"+buttonmine.getMajor());
		bt1_mine.setText(num+"\n"+"排队号码");
		bt2_mine.setText(num+"\n"+"已排的课程");
		bt3_mine.setText(num+"\n"+"信誉值");
		bt1_mine.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.baidu.com"));
				startActivity(intent);
			}
		});
		
		ListView listview_data = (ListView) findViewById(R.id.listView1);
		ListView listview_queue = (ListView) findViewById(R.id.queue_listView2);
		ListView listview_square = (ListView) findViewById(R.id.listview3);
		listview_queue.addHeaderView(headview);                                           //加入headview
		
		listview_data.setAdapter(adapater_theme);
		listview_queue.setAdapter(adapter_queue);
		listview_square.setAdapter(adapter_square);
		
		
		listview_data.setOnItemClickListener(new OnItemClickListener() {

			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Theme theme =themeList.get(position);
					Intent intent2 = new Intent(SecondActivity.this,ThirdActivity.class);
					int count1 = 0,count2 = 0;                 //count1记录当前position，count2记录同样名称的个数
					
					Line line=null;							//实现在“排队中”搜索
					line = lineList.get(count1);
					while(true)		
					{
						Log.d("SecondActivity","hehe1");
						if(line.getLesson()==theme.getname())
						{
							intent2.putExtra("name", line.getLesson());
							intent2.putExtra("teacher", line.getTeacher());
							intent2.putExtra("place", line.getPlace());
							intent2.putExtra("month", line.getMonth());
							intent2.putExtra("day", line.getDay());
							intent2.putExtra("img", line.getImageId());
							System.out.println(line.getTeacher());
							count2++;
						}
						count1++;
						
						
						if(count == count1)
							break;
						line = lineList.get(count1);
					}
						
					intent2.putExtra("count", count2);
					startActivity(intent2);
			}
			
		});
		
		listview_queue.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
				Toast.makeText(getApplicationContext(),"yes", Toast.LENGTH_SHORT).show();
				
			}
			
		});
		
	}
	public void Control_Bt(View v)
	{
		Intent intent3 = new Intent(SecondActivity.this,MyInfoActivity.class);
		intent3.putExtra("InFoName",buttonmine.getName());
		intent3.putExtra( "InFoMajor",buttonmine.getMajor());
		intent3.putExtra("InFoSex", buttonmine.getSex());
		intent3.putExtra("InFoImage", buttonmine.getMimage());
		startActivity(intent3);
	}
   
	
	private void initTheme() {
		Theme lisan = new Theme("离散数学");
		themeList.add(lisan);
		Theme gailvlun = new Theme("概率论");
		themeList.add(gailvlun);
		Theme cjiajia = new Theme("C++");
		themeList.add(cjiajia);
		Theme cyuyan = new Theme("C语言");
		themeList.add(cyuyan);
		Theme gaoshu = new Theme( "高等数学");
		themeList.add(gaoshu);
		Theme  shudian= new Theme("数字电路");
		themeList.add(shudian);
		Theme shujujiegou = new Theme("数据结构");
		themeList.add(shujujiegou);
		Theme wuli = new Theme("大学物理");
		themeList.add(wuli);
		Theme desgin = new Theme("设计模式");
		themeList.add(desgin);
		Theme suanfa = new Theme("算法分析");
		themeList.add(suanfa);
		Theme jizu = new Theme("计算机组成原理");
		themeList.add(jizu);
	
	}

	private void initLine(){
		Line num1 = new Line(R.drawable.ic_thumbnail_placeholder, "计算机组成原理","袁春风", "2102", "7月", "2日");
		System.out.println(num1.getTeacher()+"hehe");
		lineList.add(num1);
		Line num2 = new Line(R.drawable.ic_thumbnail_placeholder, "离散数学","周勇", "6101", "6月", "11日");
		lineList.add(num2);
		Line num3 = new Line(R.drawable.ic_thumbnail_placeholder, "大学物理","张三", "6101", "5月", "11日");
		lineList.add(num3);
		Line num4 = new Line(R.drawable.ic_thumbnail_placeholder, "高等数学","赵四", "6101", "5月", "15日");
		lineList.add(num4);
		Line num5 = new Line(R.drawable.ic_thumbnail_placeholder, "数字电路","王五", "6101", "6月", "1日");
		lineList.add(num5);
		Line num6 = new Line(R.drawable.ic_thumbnail_placeholder, "概率论","小六", "6101", "7月", "5日");
		lineList.add(num6);
		count = 6;
		
	}
	private void initSquare(){
		Square num1 = new Square(R.drawable.background10, "【认真谈课】教你如何上课不走神");
		squareList.add(num1);
		Square num2 = new Square(R.drawable.coffe02, "【攻略】一波学霸来袭~");
		squareList.add(num2);
	}
	
	private class MySpinnerListener implements OnItemSelectedListener{
		
		
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
