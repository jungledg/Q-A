package com.example.question;

import android.widget.LinearLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ImageButton1 extends LinearLayout
{
  private ImageView mImage;
  private TextView mText;
 
  public ImageButton1(Context context, AttributeSet attrs)
  {
    super(context,attrs);
 
    mImage = new ImageView(context,attrs);
    mImage.setPadding(0,0,0,0);
    mText = new TextView(context,attrs);
    mText.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
    mText.setPadding(0,0,0,0);
 
    setClickable(true);
    setFocusable(true);
    setBackgroundResource(android.R.drawable.btn_default);
    setOrientation(LinearLayout.HORIZONTAL);
    addView(mImage);
    addView(mText);
  } 
}