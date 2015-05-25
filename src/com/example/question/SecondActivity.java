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
	private List<Theme> themeList = new ArrayList<Theme>();					//����ҳ����������
	private List<Line> lineList = new ArrayList<Line>();					//���Ŷӡ���������
	private List<Square> squareList = new ArrayList<Square>();				//���㳡���������� 
	ButtonMine buttonmine=null;												//�ҵ� ���ϵİ�ť
	private int count =0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		ActivityCollector.addActivity(this);
		initTheme();														//��ʼ�����ҵġ�
		initLine();															//��ʼ��"�Ŷ�"
		initSquare();														//��ʼ��"�㳡"
		
		ThemeAdapter adapater_theme = new ThemeAdapter(SecondActivity.this, R.layout.theme, themeList);
		LineAdapater adapter_queue = new LineAdapater(SecondActivity.this, R.layout.paidui, lineList);
		SquareAdapter adapter_square = new SquareAdapter(SecondActivity.this,R.layout.square,squareList);
		
		
		tabHost=getTabHost();
		TabSpec tab1=tabHost.newTabSpec("tab1").setIndicator("��ҳ").setContent(R.id.shouye);
		tabHost.addTab(tab1);
		TabSpec tab2=tabHost.newTabSpec("tab2").setIndicator("�Ŷ�").setContent(R.id.paidui);
		tabHost.addTab(tab2);
		TabSpec tab3=tabHost.newTabSpec("tab3").setIndicator("�㳡").setContent(R.id.guangchang);
		tabHost.addTab(tab3);
		TabSpec tab4=tabHost.newTabSpec("tab4").setIndicator("�ҵ�").setContent(R.id.mine);
		tabHost.addTab(tab4);
		
		int num = 0;
		String InfoName = "jungle";
		String InfoMajor = "�������";
		Boolean InfoSex = false;
		buttonmine=new ButtonMine(R.drawable.mine_icon_portrait, InfoName, InfoSex, InfoMajor);
		View headview=getLayoutInflater().inflate(R.layout.headview, null); 				//"�Ŷ�"�е�head
		//-------------------���Ŷӡ������������ؼ�-------------------------
		spinner01 = (Spinner)headview.findViewById(R.id.spinner01);
		spinner02=(Spinner) headview.findViewById(R.id.spinner02);
		this.spinner01.setOnItemSelectedListener(new MySpinnerListener());
		//--------------------���ҵġ���������ť-----------------------------
		Button bt1_mine=(Button) findViewById(R.id.bt1_mine);						//�ŶӺ���			
		Button bt2_mine=(Button) findViewById(R.id.bt2_mine);						//���ſγ�
		Button bt3_mine=(Button) findViewById(R.id.bt3_mine);						//����ֵ
		Button bt_mine=(Button) findViewById(R.id.bt_mine);
		bt_mine.setBackgroundResource(R.drawable.background17);
		bt_mine.setText(buttonmine.getName()+"\n"+buttonmine.getMajor());
		bt1_mine.setText(num+"\n"+"�ŶӺ���");
		bt2_mine.setText(num+"\n"+"���ŵĿγ�");
		bt3_mine.setText(num+"\n"+"����ֵ");
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
		listview_queue.addHeaderView(headview);                                           //����headview
		
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
					int count1 = 0,count2 = 0;                 //count1��¼��ǰposition��count2��¼ͬ�����Ƶĸ���
					
					Line line=null;							//ʵ���ڡ��Ŷ��С�����
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
		Theme lisan = new Theme("��ɢ��ѧ");
		themeList.add(lisan);
		Theme gailvlun = new Theme("������");
		themeList.add(gailvlun);
		Theme cjiajia = new Theme("C++");
		themeList.add(cjiajia);
		Theme cyuyan = new Theme("C����");
		themeList.add(cyuyan);
		Theme gaoshu = new Theme( "�ߵ���ѧ");
		themeList.add(gaoshu);
		Theme  shudian= new Theme("���ֵ�·");
		themeList.add(shudian);
		Theme shujujiegou = new Theme("���ݽṹ");
		themeList.add(shujujiegou);
		Theme wuli = new Theme("��ѧ����");
		themeList.add(wuli);
		Theme desgin = new Theme("���ģʽ");
		themeList.add(desgin);
		Theme suanfa = new Theme("�㷨����");
		themeList.add(suanfa);
		Theme jizu = new Theme("��������ԭ��");
		themeList.add(jizu);
	
	}

	private void initLine(){
		Line num1 = new Line(R.drawable.ic_thumbnail_placeholder, "��������ԭ��","Ԭ����", "2102", "7��", "2��");
		System.out.println(num1.getTeacher()+"hehe");
		lineList.add(num1);
		Line num2 = new Line(R.drawable.ic_thumbnail_placeholder, "��ɢ��ѧ","����", "6101", "6��", "11��");
		lineList.add(num2);
		Line num3 = new Line(R.drawable.ic_thumbnail_placeholder, "��ѧ����","����", "6101", "5��", "11��");
		lineList.add(num3);
		Line num4 = new Line(R.drawable.ic_thumbnail_placeholder, "�ߵ���ѧ","����", "6101", "5��", "15��");
		lineList.add(num4);
		Line num5 = new Line(R.drawable.ic_thumbnail_placeholder, "���ֵ�·","����", "6101", "6��", "1��");
		lineList.add(num5);
		Line num6 = new Line(R.drawable.ic_thumbnail_placeholder, "������","С��", "6101", "7��", "5��");
		lineList.add(num6);
		count = 6;
		
	}
	private void initSquare(){
		Square num1 = new Square(R.drawable.background10, "������̸�Ρ���������Ͽβ�����");
		squareList.add(num1);
		Square num2 = new Square(R.drawable.coffe02, "�����ԡ�һ��ѧ����Ϯ~");
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
