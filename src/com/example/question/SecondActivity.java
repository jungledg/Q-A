package com.example.question;

import java.util.ArrayList;
import java.util.List;

import com.example.question.R;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.SmsManager;
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
	
	private static int flag = 0;
	private String invite_number;
	private TabHost tabHost=null;
	private Spinner spinner01=null,spinner02=null;
	private String[][] major=new String[][]{
			{"全部"},
			{"飞行器设计","飞行器动力"},
			{"能源1","能源2"},
			{"自动化1","自动化2"},
			{"电子信息1","电子信息2"},
			{"机电1","机电2"},
			{"材料1","材料2"},
			{"飞行1","飞行2"},
			{"数学","物理"},
			{"经管1","经管2"},
			{"法律","公关事务"},
			{"外国语1","外国语2"},
			{"艺术1","艺术2"},
			{"航宇1","航宇2"},
			{"计算机科学与技术","信息安全","软件工程","物联网工程"},
			{"国教1","国教2"},
	};
	private ArrayAdapter<CharSequence> adapter_major=null;
	private List<Theme> themeList = new ArrayList<Theme>();					//首页 中的List
	private List<Line> lineList = new ArrayList<Line>();					//队列中的item list
	private List<Square> squareList = new ArrayList<Square>();				//广场中的 list
	ButtonMine buttonmine=null;												//我的 中我的信息按钮
	private int count =0;
	
	//初始化 我的 Info
			int paidui_num=0;										//排队号码
			int lined_num=0;										//已排课程
			int credit_num=0;										//信誉值
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		ActivityCollector.addActivity(this);
		initTheme();														//初始化 首页
		initLine();															//初始化 排队
		initSquare();														//初始化 广场
		
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
		
		
		
		String InfoName = "jungle";
		String InfoMajor = "软件工程";	
		Boolean InfoSex = false;
		buttonmine=new ButtonMine(R.drawable.mine_icon_portrait, InfoName, InfoSex, InfoMajor);
		View headview=getLayoutInflater().inflate(R.layout.headview, null); 				//排队  里边加入headview
		//-------------------队列中的学院专业选择-------------------------
		spinner01 = (Spinner)headview.findViewById(R.id.spinner01);
		spinner02=(Spinner) headview.findViewById(R.id.spinner02);
		this.spinner01.setOnItemSelectedListener(new MySpinnerListener());
		//--------------------我的  里边的三个按钮----------------------------
		Button bt1_mine=(Button) findViewById(R.id.bt1_mine);						//			
		Button bt2_mine=(Button) findViewById(R.id.bt2_mine);						//
		Button bt3_mine=(Button) findViewById(R.id.bt3_mine);						//
		Button bt_mine=(Button) findViewById(R.id.bt_mine);
		bt_mine.setBackgroundResource(R.drawable.background17);
		bt_mine.setText(buttonmine.getName()+"\n"+buttonmine.getMajor());
		bt1_mine.setText(paidui_num+"\n"+"排队号码");
		bt2_mine.setText(lined_num+"\n"+"已排课程");
		bt3_mine.setText(credit_num+"\n"+"信誉值");
		bt1_mine.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.baidu.com"));
				startActivity(intent);
			}
		});
		bt2_mine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
																		//这里写"已排课程"的点击事件
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
					flag = 1;
					Intent intent2 = new Intent(SecondActivity.this,ThirdActivity.class);
					int count1 = 0,count2 = 0;                 //count1记录当前position，count2记录同样名称的个数
					
					Line line1=null;							//实现在 “排队”中 搜索  
					line1 = lineList.get(count1);
					while(true)		
					{
						Log.d("SecondActivity","hehe1");
						if(line1.getLesson()==theme.getname())
						{
							intent2.putExtra("name", line1.getLesson());
							intent2.putExtra("teacher", line1.getTeacher());
							intent2.putExtra("place", line1.getPlace());
							intent2.putExtra("month", line1.getMonth());
							intent2.putExtra("day", line1.getDay());
							intent2.putExtra("img", line1.getImageId());
							System.out.println(line1.getTeacher());
							count2++;
						}
						count1++;
						
						
						if(count == count1)
							break;
						line1 = lineList.get(count1);
					}
						
					intent2.putExtra("count", count2);
					intent2.putExtra("flag", flag);
					startActivityForResult(intent2, 1);
			}
			
		});
		
		listview_queue.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
				Toast.makeText(getApplicationContext(),"yes", Toast.LENGTH_SHORT).show();
				
				flag = 2;
				Intent intent3 = new Intent(SecondActivity.this,ThirdActivity.class);
				
				Line line2=null;							
				line2 = lineList.get(position);
	

						intent3.putExtra("name", line2.getLesson());
						intent3.putExtra("teacher", line2.getTeacher());
						intent3.putExtra("place", line2.getPlace());
						intent3.putExtra("month", line2.getMonth());
						intent3.putExtra("day", line2.getDay());
						intent3.putExtra("img", line2.getImageId());
					
				intent3.putExtra("flag", flag);
				startActivityForResult(intent3,1);
				
			}
			
		});
		
		
		
	}
	public void Control_Bt(View v)
	{
		Intent intent4 = new Intent(SecondActivity.this,MyInfoActivity.class);
		intent4.putExtra("InFoName",buttonmine.getName());
		intent4.putExtra( "InFoMajor",buttonmine.getMajor());
		intent4.putExtra("InFoSex", buttonmine.getSex());
		intent4.putExtra("InFoImage", buttonmine.getMimage());
		startActivity(intent4);
	}
   

	public void Invite_bt(View v){
	       startActivityForResult(new Intent(
	                Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI), 0);
	   }

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	switch(requestCode){
			case 1:															//------点击排队后返回的数据
				if(requestCode==1){
		    		if(resultCode == RESULT_OK){
						System.out.println("heheda01");
						String place,teacher,lesson,month,day;
						place=data.getStringExtra("result1_place");
						teacher=data.getStringExtra("result1_teacher");
						lesson=data.getStringExtra("result1_lesson");
						month=data.getStringExtra("result1_month");
						day=data.getStringExtra("result1_day");
						lined_num=lined_num+1;
						Button bt2_mine=(Button) findViewById(R.id.bt2_mine);	
						bt2_mine.setText(lined_num+"\n"+"已排课程");
					}
				}
				break;
			default:
				 super.onActivityResult(requestCode, resultCode, data);				//------好友邀请
			        if (resultCode == Activity.RESULT_OK) {
			            ContentResolver reContentResolverol = getContentResolver();
			             Uri contactData = data.getData();
			            Cursor cursor = managedQuery(contactData, null, null, null, null);
			             cursor.moveToFirst();
			             invite_number = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
			            Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
			                     null, 
			                     ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, 
			                     null, 
			                     null);
			             while (phone.moveToNext()) {
			            	 invite_number = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			            	 
			            	String content;
			                SmsManager smsManager=SmsManager.getDefault();//获取一个短信管理器
			         		content="欢迎使用答疑不用等~~这是一款免费服务于教学的APP,点击立即下载www.xxx.com(暂时还没下载地址o(╯□╰)o)";
			         		List<String> invite_texts=smsManager.divideMessage(content);
			         		for(String invite_text:invite_texts)
			         		{
			         			 Intent sendIntent = new Intent(Intent.ACTION_SENDTO);  
			         		    sendIntent.setData(Uri.parse("smsto:" + invite_number));  
			         		    sendIntent.putExtra("sms_body", invite_text);  
			         		   startActivity(sendIntent);  
			         		}
			             }
			             
			         }
			        break;
			    }
			}
	    	
	    	
	       
	
	
	
	
	
	
	//////////////-------------------
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
		Line num2 = new Line(R.drawable.ic_thumbnail_placeholder, "离散数学","周勇", "3303", "6月", "11日");
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
		Square num1 = new Square(R.drawable.background10, "一大波学霸来袭~");
		squareList.add(num1);
		Square num2 = new Square(R.drawable.background11, "【谈学习】轻松掌握知识的10个小技巧");
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
