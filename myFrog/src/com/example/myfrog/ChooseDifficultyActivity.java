package com.example.myfrog;

import com.example.myfrog.R;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ChooseDifficultyActivity extends GameBase {
	protected Button simple;
	protected Button middle;
	protected Button hard;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.choose_difficulty_activity);
//		SoundPlayer.init(ViewChangeHard.this);
//		SoundPlayer.playMusic(R.raw.clap, true);
		
		
		simple = (Button) findViewById(R.id.btnsimple);
		middle = (Button) findViewById(R.id.btnmiddle);
		hard = (Button)findViewById(R.id.btnhard);
		
		
		simple.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectGame("simple");
			}
		});
		middle.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectGame("middle");
			}
		});
		hard.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectGame("hard");
			}
		});
	}
	
	protected void selectGame(String hard) {
		Intent intentGo = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("SelectHard", hard);
		intentGo.putExtras(bundle);

		intentGo.setClass(ChooseDifficultyActivity.this, GameTap.class);
		startActivity(intentGo);
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.welcome, menu);
//		return true;
//	}

}
