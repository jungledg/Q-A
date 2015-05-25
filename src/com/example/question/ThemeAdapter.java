package com.example.question;

import java.util.List;

import android.content.Context;
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
			//viewHolder.themeImage = (ImageView) view.findViewById(R.id.theme_image);
			viewHolder.themeName = (TextView) view.findViewById(R.id.theme_name);
			viewHolder.themeName.setTextColor(viewHolder.themeName.getResources().getColor(R.drawable.white));
			view.setTag(viewHolder);
		}
		else{
			view =	convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		switch(position)
		{
		case 0:
			viewHolder.themeName.setBackgroundResource(R.drawable.background14);
			break;
		case 1:
			viewHolder.themeName.setBackgroundResource(R.drawable.background1);
			break;
		case 2:
			viewHolder.themeName.setBackgroundResource(R.drawable.background2);
			break;
		case 3:
			viewHolder.themeName.setBackgroundResource(R.drawable.background3);
			break;
		case 4:
			viewHolder.themeName.setBackgroundResource(R.drawable.background4);
			break;
		case 5:
			viewHolder.themeName.setBackgroundResource(R.drawable.background5);
			break;
		case 6:
			viewHolder.themeName.setBackgroundResource(R.drawable.background6);
			break;
		case 7:
			viewHolder.themeName.setBackgroundResource(R.drawable.background7);
			break;
		case 8:
			viewHolder.themeName.setBackgroundResource(R.drawable.background8);
			break;
		case 9:
			viewHolder.themeName.setBackgroundResource(R.drawable.background9);
			break;
		case 10:
			viewHolder.themeName.setBackgroundResource(R.drawable.background10);
			break;
		case 11:
			viewHolder.themeName.setBackgroundResource(R.drawable.background11);
			break;
		case 12:
			viewHolder.themeName.setBackgroundResource(R.drawable.background12);
			break;
		case 13:
			viewHolder.themeName.setBackgroundResource(R.drawable.background13);
			break;
		default:
			break;
		}
		
		//viewHolder.themeImage.setImageResource(theme.getImageId());
		viewHolder.themeName.setText(theme.getname());
		
		return view;
	}
	
	class ViewHolder{
		ImageView themeImage;
		TextView themeName;
	}
	
}
