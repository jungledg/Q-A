package com.example.question;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SquareAdapter extends ArrayAdapter<Square>{

	public SquareAdapter(Context context, int resource,
			List<Square> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}
	public View getView(int position,View convertView,ViewGroup parent){
		Square gc=getItem(position);
		
		View view;
		ViewHolder viewHolder;
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(R.layout.square, null);
		
			viewHolder = new ViewHolder();
			viewHolder.textview=(TextView) view.findViewById(R.id.square_data);
			viewHolder.imgview=(ImageView) view.findViewById(R.id.square_image);
		
			view.setTag(viewHolder);
		}else{
			view =	convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		
		viewHolder.imgview.setImageResource(gc.getImgId());
		viewHolder.textview.setText(gc.getTextview());
		return view;
		
	}
	class ViewHolder{
		ImageView imgview;
		TextView textview;
	}
}
