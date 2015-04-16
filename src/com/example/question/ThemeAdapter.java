package com.example.question;

import java.util.List;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ThemeAdapter extends ArrayAdapter<Theme>{
	
	private int resourceId;
	public ThemeAdapter(Context context,int textViewResourceId,List<Theme> objects){
		super(context, textViewResourceId,objects);
		resourceId = textViewResourceId;
	}
	
	public View getView(int position,View convertView,ViewGroup parent){
		Theme theme = getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView == null)
		{
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.themeImage = (ImageView) view.findViewById(R.id.theme_image);
			viewHolder.themeName = (TextView) view.findViewById(R.id.theme_name);
			view.setTag(viewHolder);
		}
		else{
			view =	convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.themeImage.setImageResource(theme.getImageId());
		viewHolder.themeName.setText(theme.getname());
		return view;
	}
	
	class ViewHolder{
		ImageView themeImage;
		TextView themeName;
	}
	
}
