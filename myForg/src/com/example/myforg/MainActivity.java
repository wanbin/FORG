package com.example.myforg;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("UseSparseArrays")
public class MainActivity extends Activity {
	private SoundPool sp;
	private int addTarget;
	private HashMap<Integer, Integer> spMap;
	private int source;
	private int tagretsource;
	private int timelimit;
	private int maxSource;
	private boolean hasPlaySound;
	private Timer timer;
	private Button btnFrog;
	private Button btnFrogback;
	private boolean start;
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

//	TextView totalSouce;
	TextView totalCount;
	TextView remainTime;
	TextView remainCount;
	TextView plus_time;
	TextView plus_target;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp = new SoundPool(2, AudioManager.STREAM_SYSTEM, 0);
		spMap = new HashMap<Integer, Integer>();

		spMap.put(1, sp.load(this, R.raw.clap, 1));
		totalCount = (TextView) findViewById(R.id.totalSouce);
//		totalCount = (TextView) findViewById(R.id.totalCount);
		remainTime = (TextView) findViewById(R.id.remainTime);
		remainCount = (TextView) findViewById(R.id.remainCount);
		plus_time = (TextView)findViewById(R.id.plusTime);
		plus_target = (TextView)findViewById(R.id.plusTarget);
		
		source=0;
		timelimit=500;
		tagretsource=20;
		addTarget=0;
		maxSource=this.getMaxSource();
		hasPlaySound=false;
		start=false;
		
		updateText();
		updateTime();
//
		btnFrog = (Button) findViewById(R.id.cryfrog);// 获取按钮资源
		btnFrogback = (Button) findViewById(R.id.reset);//获取按钮资源
		
		// Btn1添加监听
		btnFrog.setOnClickListener(new Button.OnClickListener() {// 创建监听
			@Override
			public void onClick(View v) {
				if(start==false)
				{
					start=true;
					if(timer==null)
					{
					timer = new Timer();
					timer.schedule(timetask, 0, 10);
					}
				}
				source++;
				tagretsource--;
				if (tagretsource == 0) {
					addTarget++;
					tagretsource = 20 + addTarget;
					timelimit += 500;
					
					//显示提示
					fadeIn(plus_time);
					fadeIn(plus_target);
					
					fadeOut(plus_target);
					fadeOut(plus_time);
				}
				if (source > maxSource) {
					maxSource=source;
					setMaxSource(source);
					if (hasPlaySound == false) {
						hasPlaySound = true;
						sp.play(spMap.get(spMap.get(1)), 50, 50, 1, 0, 1);
					}
				}
				updateText();
				
			}
		});
		
		
		btnFrogback.setOnClickListener(new Button.OnClickListener() {// 创建监听
			@Override
			public void onClick(View v) {
				source=0;
				timelimit=500;
				tagretsource=20;
				addTarget=0;
				updateText();
				updateTime();
				start=false;
				hasPlaySound = false;
				btnFrog.setClickable(true);
				btnFrogback.setClickable(false);	
			}
		});
//		
//		
//		
//		// BtnGo 添加监听，用于页面的跳转
//		BtnGo.setOnClickListener(new Button.OnClickListener() {// 创建监听
//			@Override
//			public void onClick(View v) {
//				String strTmp = String.valueOf(a++);
//				Ev1.setText(strTmp);
//				sp.play(spMap.get(spMap.get(1)), 50, 50, 1, 0, 1);
//				Intent intentGo = new Intent();
//				intentGo.setClass(MainActivity.this, ChronometerActivity.class);
//				startActivity(intentGo);
//				finish();
//			}
//
//		});

		btnFrogback.setClickable(false);
	}
	
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			addTenMMS();
			super.handleMessage(msg);
		}
	};
	

	TimerTask timetask = new TimerTask() {
		public void run() {
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}
	};
	
	//加十毫秒
	private void addTenMMS() {
		if(start)
		{
			timelimit -= 1;
		}
		if (timelimit <= 0) {
			timelimit=0;
//			timer.cancel();
			btnFrog.setClickable(false);
			btnFrogback.setClickable(true);
		}
		updateTime();
	}
	

	private void updateText() {
		totalCount.setText("Source:" + source);
//		totalCount.setText("Source:" + tagretsource);
		remainCount.setText("Target:" + tagretsource);
	}
	
	private void updateTime(){
		remainTime.setText("Remain:" + Double.toString((double)timelimit/100) + "s");
	}
	
	private int getMaxSource() {
//		return 5;
		// 获取SharedPreferences对象
		Context ctx = MainActivity.this;
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		return  sp.getInt("maxSource", 0);
	}
	
	
	private void setMaxSource(int source){
		Context ctx = MainActivity.this;
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		// 存入数据
		Editor editor = sp.edit();
		editor.putInt("maxSource",source );
		editor.commit();
		return ;
	}
	
	private void fadeIn(TextView textView) {
		AlphaAnimation fade = new AlphaAnimation(0.0f, 1.0f);
		fade.setDuration(1000);
		textView.setAnimation(fade);
		fade.startNow();
	}
	
	
	private void fadeOut(TextView textView) {
		AlphaAnimation fade = new AlphaAnimation(1.0f, 0.0f);
		fade.setDuration(1000);
		textView.setAnimation(fade);
		fade.startNow();
	}
	
	
}
