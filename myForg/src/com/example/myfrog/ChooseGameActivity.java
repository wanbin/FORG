package com.example.myfrog;

import com.example.myforg.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseGameActivity extends GameBase {
	private Button btnGameTap;
	private Button btnGameDiaplay;
	private Button btnGameColor;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_game_activity);
		btnGameTap = (Button) findViewById(R.id.btnGameTap);
		btnGameDiaplay = (Button) findViewById(R.id.btnGameDiaplay);
		btnGameColor = (Button) findViewById(R.id.btnGameColor);
		
		
		btnGameTap.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent goMain = new Intent();
				goMain.setClass(ChooseGameActivity.this,ChooseDifficultyActivity.class);
				startActivity(goMain);
			}
		});
		btnGameDiaplay.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent goMain = new Intent();
				goMain.setClass(ChooseGameActivity.this,GamePop.class);
				startActivity(goMain);
			}
		});
		btnGameColor.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent goMain = new Intent();
				goMain.setClass(ChooseGameActivity.this,GamePainter.class);
				startActivity(goMain);
			}
		});
	}
	
}
