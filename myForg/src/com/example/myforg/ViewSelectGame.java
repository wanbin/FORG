package com.example.myforg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewSelectGame extends ViewBase {
	private Button btnGameTap;
	private Button btnGameDiaplay;
	private Button btnGameColor;
	private Button btnGameRun;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_game);
		
		btnGameTap = (Button) findViewById(R.id.btnGameTap);
		btnGameDiaplay = (Button) findViewById(R.id.btnGameDiaplay);
		btnGameColor = (Button) findViewById(R.id.btnGameColor);
		btnGameRun = (Button) findViewById(R.id.BtnRun);
		
		
		btnGameTap.setOnClickListener(new Button.OnClickListener() {// 创建监听
			@Override
			public void onClick(View v) {
				Intent goMain = new Intent();
				goMain.setClass(ViewSelectGame.this,ViewChangeHard.class);
				startActivity(goMain);
			}
		});
		btnGameDiaplay.setOnClickListener(new Button.OnClickListener() {// 创建监听
			@Override
			public void onClick(View v) {
				Intent goMain = new Intent();
				goMain.setClass(ViewSelectGame.this,ViewGameDisplay.class);
				startActivity(goMain);
			}
		});
		btnGameColor.setOnClickListener(new Button.OnClickListener() {// 创建监听
			@Override
			public void onClick(View v) {
				Intent goMain = new Intent();
				goMain.setClass(ViewSelectGame.this,ViewGameChangeColor.class);
				startActivity(goMain);
			}
		});
		btnGameRun.setOnClickListener(new Button.OnClickListener() {// 创建监听
			@Override
			public void onClick(View v) {
				Intent goMain = new Intent();
						goMain.setClass(ViewSelectGame.this,
								DriveActivity.class);
				startActivity(goMain);
			}
		});
	}
	
}
