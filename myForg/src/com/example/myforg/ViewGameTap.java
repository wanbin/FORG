package com.example.myforg;

import java.util.Timer;
import java.util.TimerTask;

import com.example.util.SoundPlayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("UseSparseArrays")
public class ViewGameTap extends ViewBase {
	private int addTarget;
	private int source;
	private int tagretsource;
	private int timelimit;
	private int maxSource;
	private boolean hasPlaySound;
	private Timer timer;
	private Button btnFrog;
	private Button btnFrogback;
	private boolean start;
	private int peraddscour;
	private Integer frogHeight;
	private Integer frogWidth;
	//判断游戏是否结束
	private boolean flag; 

	public int getFrogHeight() {
		return frogHeight;
	}


	public void setFrogHeight(int frogHeight) {
		this.frogHeight = frogHeight;
	}


	public int getFrogWidth() {
		return frogWidth;
	}


	public void setFrogWidth(int frogWidth) {
		this.frogWidth = frogWidth;
	}


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
		
		//在这里load音效
		SoundPlayer.init(ViewGameTap.this);
		
		Bundle bundle = this.getIntent().getExtras();
//        /*获取Bundle中的数据，注意类型和key*/
        String hard = bundle.getString("SelectHard");
		if ( hard.equals("hard") ) {
			peraddscour = 6;
		} else if (hard.equals("middle")) {
			peraddscour = 4;
		} else {
			peraddscour = 2;
		}

		totalCount = (TextView) findViewById(R.id.totalSouce);
//		totalCount = (TextView) findViewById(R.id.totalCount);
		remainTime = (TextView) findViewById(R.id.remainTime);
		remainCount = (TextView) findViewById(R.id.remainCount);
		plus_time = (TextView)findViewById(R.id.plusTime);
		plus_target = (TextView)findViewById(R.id.plusTarget);
		
		flag	= true;
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
		final AnimationSet aniSet	= new AnimationSet(true);
		final ScaleAnimation scaleAn	= new ScaleAnimation(1.02f, 1f, 1.02f, 1f);
		final ScaleAnimation scaleAni	= new ScaleAnimation(1.0f, 1.02f, 1.0f, 1.02f);
		
//		final ScaleAnimation scaleAni	= new ScaleAnimation(btnFrog.getContext(),);
		scaleAni.setDuration(10);
		scaleAn.setDuration(10);
		scaleAn.setStartOffset(10);
		aniSet.addAnimation(scaleAni);
		aniSet.addAnimation(scaleAn);
		btnFrog.setAnimation(aniSet);
		// Btn1添加监听
		btnFrog.setOnClickListener(new Button.OnClickListener() {// 创建监听
			@Override
			public void onClick(View v) {
//				aniSet.reset();
				v.startAnimation(aniSet);
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
					addTarget += peraddscour;
					tagretsource = 20 + addTarget;
					timelimit += 500;
					
					//显示提示
					fadeIn(plus_time);
					fadeIn(plus_target);
					
					plus_target.setText("+"+tagretsource);
					
					//播放鼓掌音效
					
					SoundPlayer.clap();
				}
				if (source > maxSource) {
					maxSource=source;
					setMaxSource(source);
					if (hasPlaySound == false) {
						hasPlaySound = true;
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
			if(flag){
				addTenMMS();
			}
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
			showScore(this.source,this.maxSource);
			flag	= false;
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
		Context ctx = ViewGameTap.this;
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		return  sp.getInt("maxSource", 0);
	}
	
	
	private void setMaxSource(int source){
		Context ctx = ViewGameTap.this;
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		// 存入数据
		Editor editor = sp.edit();
		editor.putInt("maxSource",source );
		editor.commit();
		return ;
	}
	
	private void showScore(int source ,int maxSource){
		Intent scoreIntent	= new Intent();
		scoreIntent.putExtra("currentScore", source);
		scoreIntent.putExtra("maxScore", maxSource);
		scoreIntent.setClass(ViewGameTap.this, ShowScore.class);
		startActivity(scoreIntent);
	}
}
