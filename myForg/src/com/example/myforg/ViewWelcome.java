package com.example.myforg;

import java.io.InputStream;

import com.example.util.SoundPlayer;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ViewWelcome extends Activity {
	
	private ImageView imageView;
	private ImageView points;
	private AnimationDrawable animDrawable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		imageView = (ImageView)findViewById(R.id.frog_welcome);
		points = (ImageView)findViewById(R.id.imageView1);
		//在这里load音效
		SoundPlayer.init(ViewWelcome.this);
		SoundPlayer.startMusic();
		
		animDrawable = new AnimationDrawable();
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		AnimationSet as=new AnimationSet(true);  
        TranslateAnimation al=new TranslateAnimation( 0,0,0,20,0,0,0,10);  
        al.setDuration(500);
        as.addAnimation(al);
        al.setRepeatMode(Animation.REVERSE);
        al.setRepeatCount(-1);
        points.startAnimation(as);  
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
		AnimateFrame();
	}
	
	
	@SuppressWarnings("deprecation")
	public void AnimateFrame()
	{
		
		Bitmap frogBitmap = ReadBitmap(this, R.drawable.frog_smile_frame);
		for (int i = 0; i < 4; i++) {
			
			Bitmap frameBitmap = Bitmap.createBitmap(frogBitmap, 0, 100*i, 100, 100);
			animDrawable.addFrame(new BitmapDrawable(getResources(),frameBitmap), 100);
		}
		
		animDrawable.setOneShot(false);
		int sdk = android.os.Build.VERSION.SDK_INT;
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
		    imageView.setBackgroundDrawable(animDrawable);
		} else {
		    imageView.setBackground(animDrawable);
		}
		
		animDrawable.start();
	}
	
	/**
	 * 停止动画
	 */
	public void stopAnimate(View view)
	{
		Intent goMain = new Intent();
		goMain.setClass(ViewWelcome.this,ViewChangeHard.class);
		startActivity(goMain);
	}
	
	public void gotoMain()
	{
		Intent intentGo = new Intent();
		intentGo.setClass(ViewWelcome.this, ViewChangeHard.class);
		startActivity(intentGo);
		finish();
	}
	
	public Bitmap ReadBitmap(Context context,int resID)
	{
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inPurgeable = true;
		options.inInputShareable = true;
		
		//获取图片资源
		InputStream inputStream = context.getResources().openRawResource(resID);
		return BitmapFactory.decodeStream(inputStream, null, options);
	}
	
	@Override
	public void onDestroy()
	{
		SoundPlayer.setMusicSt(false);
	}
	

}
