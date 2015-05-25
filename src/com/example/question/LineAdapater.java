package com.example.question;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LineAdapater extends ArrayAdapter<Line>{

	public LineAdapater(Context context,int textViewResourceId,List<Line> objects){
		super(context, textViewResourceId,objects);
	}
	
	public View getView(int position,View convertView,ViewGroup parent){
		Line line = getItem(position);
		View view;
		ViewHolder viewHolder;
		
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(R.layout.queue, null);
		
			viewHolder = new ViewHolder();
			viewHolder.lineDay=(TextView) view.findViewById(R.id.Info_day);
			viewHolder.lineLesson=(TextView) view.findViewById(R.id.Info_lesson);
			viewHolder.lineImage=(ImageView) view.findViewById(R.id.Info_imageView1);
			viewHolder.lineMonth=(TextView) view.findViewById(R.id.Info_month);
			viewHolder.lineTeacher=(TextView) view.findViewById(R.id.Info_teacher);
			viewHolder.linePlace=(TextView) view.findViewById(R.id.Info_place);
			//viewHolder.lineTeacher.setTextColor(viewHolder.lineTeacher.getResources().getColor(R.drawable.yellow));
			view.setTag(viewHolder);
		}else{
			view =	convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		
		viewHolder.lineDay.setText(line.getDay());
		viewHolder.lineImage.setImageResource(line.getImageId());
		viewHolder.lineLesson.setText(line.getLesson());
		viewHolder.lineTeacher.setText(line.getTeacher());
		viewHolder.lineMonth.setText(line.getMonth());
		viewHolder.linePlace.setText(line.getPlace());
		
		
		return view;
		
	}
	
	class ViewHolder{
		ImageView lineImage;
		TextView lineLesson;
		TextView lineTeacher;
		TextView linePlace;
		TextView lineMonth;
		TextView lineDay;
	}
	
}
