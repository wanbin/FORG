//wanbin 困难选择界面
package com.example.myforg;

import com.example.util.SoundPlayer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ViewChangeHard extends ViewBase {
	protected Button simple;
	protected Button middle;
	protected Button hard;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_hard);
		
		//在这里load音效
//		SoundPlayer.init(ViewChangeHard.this);
//		SoundPlayer.playMusic(R.raw.clap, true);
		
		
		simple = (Button) findViewById(R.id.btnsimple);
		middle = (Button) findViewById(R.id.btnmiddle);
		hard = (Button)findViewById(R.id.btnhard);
		
		
		simple.setOnClickListener(new Button.OnClickListener() {// 创建监听
			@Override
			public void onClick(View v) {
				selectGame("simple");
			}
		});
		middle.setOnClickListener(new Button.OnClickListener() {// 创建监听
			@Override
			public void onClick(View v) {
				selectGame("middle");
			}
		});
		hard.setOnClickListener(new Button.OnClickListener() {// 创建监听
			@Override
			public void onClick(View v) {
				selectGame("hard");
			}
		});
	}
	
	protected void selectGame(String hard) {
		Intent intentGo = new Intent()
		;
		Bundle bundle = new Bundle();
		/*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/
		bundle.putString("SelectHard", hard);
//		/*把bundle对象assign给Intent*/
		intentGo.putExtras(bundle);

		intentGo.setClass(ViewChangeHard.this, ViewGameTap.class);
		startActivity(intentGo);
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.welcome, menu);
//		return true;
//	}

}
