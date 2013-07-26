package com.example.myfrog;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myforg.R;
import com.example.util.ResReader;


public class WelcomeActivity extends GameBase {
	
	private ImageView imageView;
	private ImageView points;
	private AnimationDrawable animDrawable;
	private AnimationDrawable animDrawableStar;
	private ImageView imageTest;
	private ImageView star;
	private ImageView imageSetting;
	private ImageView imageBarShadow;
	private ImageView imageBarBg;
	private ImageView imageBarContent;
	private ImageView imageBarholder;

	private ResReader resReader;
	private ResReader frogReader;
	private ResReader barReader;

	private Bitmap btnBg;
	private int frogIndex = 1;
	private boolean onthis = false;
	private Timer timer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		imageView = (ImageView)findViewById(R.id.frog_welcome);


		points = (ImageView) findViewById(R.id.imageView1);
		star = (ImageView) findViewById(R.id.imageStar);
		imageSetting = (ImageView) findViewById(R.id.imageSetting);

		resReader = new ResReader(this, "achiev_not_done");
		btnBg = resReader.getImg("achiev_not_done.png");
		imageSetting.setImageBitmap(btnBg);

		initProBar();

		// imageView.setBackgroundResource(R.anim.index_anmi);
		// animDrawable = (AnimationDrawable)imageView.getBackground();
		star.setBackgroundResource(R.anim.star); 
		animDrawableStar = (AnimationDrawable)star.getBackground();
		
		
		frogReader = new ResReader(this, "frog_idle_small");

		//SoundPlayer.init(this);
		//SoundPlayer.pushSound(R.raw.rocket);
		AnimationSet as=new AnimationSet(true);  
        TranslateAnimation al=new TranslateAnimation( 0,0,0,20,0,0,0,10);  
        al.setDuration(500);
        as.addAnimation(al);
        al.setRepeatMode(Animation.REVERSE);
        al.setRepeatCount(-1);
        points.startAnimation(as);  

		if (GetAnimationStat()==0) {
			Intent goMain = new Intent();
			goMain.setClass(WelcomeActivity.this, VideoActivity.class);
			startActivity(goMain);
			finish();
			SetAnimationStat(1);
			return;
		}

//        initJPUSH();
        

		imageSetting.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
						onthis = false;
						stopTimer();
						Intent intentGo = new Intent();
						intentGo.setClass(WelcomeActivity.this,
								SettingActivity.class);
						startActivity(intentGo);
			}
		});

		showHLoading();
		timer = new Timer();
		timer.schedule(timetask, 0, 40);
		onthis = true;

	}

	private void setProBar(float barlong) {
		imageBarContent.setScaleX(barlong);
	}
	
	private void initProBar() {
		imageBarShadow = (ImageView) findViewById(R.id.imageShadow);
		imageBarBg = (ImageView) findViewById(R.id.imageBarBackground);
		imageBarContent = (ImageView) findViewById(R.id.imageBarContent);
		imageBarholder = (ImageView) findViewById(R.id.imageBarholder);

		barReader = new ResReader(this, "exp_bar");
		imageBarShadow.setImageBitmap(barReader
				.getImg("exp_bar_holder_back.png"));
		imageBarBg.setImageBitmap(barReader.getImg("exp_bar_holder.png"));
		imageBarContent.setImageBitmap(barReader.getImg("exp_bar.png"));

	}
	

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			updateFrog();
			super.handleMessage(msg);
		}
	};
	
	private void stopTimer() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		if (timetask != null) {
			timetask.cancel();
			timetask = null;
		}
	}

	TimerTask timetask = new TimerTask() {
		public void run() {
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}
	};

	private void updateFrog() {
		Bitmap bit = frogReader.getImg("frog_idle_small0"
				+ String.format("%03d", frogIndex) + ".png");
		if (frogIndex >= 200) {
			frogIndex = 1;
		} else {
			frogIndex++;
		}
		setProBar((float) frogIndex / 200);
		imageView.setImageBitmap(bit);

	}
	protected void initJPUSH()
	{
//		 JPushInterface.setDebugMode(true);
//         JPushInterface.init(this);
//         JPushInterface.setAliasAndTags(getApplicationContext(), "wanbin", null);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		// animDrawable.start();
		animDrawableStar.start();
	}
	
	
	
	public void stopAnimate(View view)
	{
		
		Intent goMain = new Intent();
		goMain.setClass(WelcomeActivity.this, ChooseGameActivity.class);
		startActivity(goMain);
	}
	
	@Override
	public void onDestroy()
	{

		// SoundPlayer.setMusicSt(false);
    	super.onDestroy();
	}

	private int GetAnimationStat() {
		Context ctx = WelcomeActivity.this;
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		return  sp.getInt("AnimationSwith", 0);
	}
	
	
	private void SetAnimationStat(int TotalTime){
		Context ctx = WelcomeActivity.this;
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		Editor meditor = sp.edit();
		meditor.putInt("AnimationSwith",TotalTime );
		meditor.commit();
		return ;
	}
}
