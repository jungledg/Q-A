package com.example.question.service;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService{

	public MyIntentService() {
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void onDestory(){
		super.onDestroy();
		
	}
	
}
