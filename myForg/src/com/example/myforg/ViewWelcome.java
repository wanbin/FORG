package com.example.myforg;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
//import cn.jpush.android.api.JPushInterface;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ViewWelcome extends ViewBase {
	
	private ImageView imageView;
	private ImageView points;
	private AnimationDrawable animDrawable;
	private AnimationDrawable animDrawableStar;
	private ImageView imageTest;
	private ImageView star;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		imageView = (ImageView)findViewById(R.id.frog_welcome);
		points = (ImageView)findViewById(R.id.imageView1);
		star = (ImageView)findViewById(R.id.imageStar);
		
		// SoundPlayer.init(this);
		imageView.setBackgroundResource(R.anim.index_anmi); 
		animDrawable = (AnimationDrawable)imageView.getBackground();
		//加载音效
		star.setBackgroundResource(R.anim.star); 
		animDrawableStar = (AnimationDrawable)star.getBackground();
		
		
		//加载音效
		// SoundPlayer.pushSound(R.raw.claps3);
		
		AnimationSet as=new AnimationSet(true);  
        TranslateAnimation al=new TranslateAnimation( 0,0,0,20,0,0,0,10);  
        al.setDuration(500);
        as.addAnimation(al);
        al.setRepeatMode(Animation.REVERSE);
        al.setRepeatCount(-1);
        points.startAnimation(as);  
       
//        initJPUSH();
        
        
//        //这个函数是调取资源图的入口
//		Bitmap animDrawable = getImg("achiev_info", "rate-button2.png");
//		imageTest.setImageBitmap(animDrawable);
//        
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
		animDrawable.start();
		animDrawableStar.start();
	}
	
	
//	@SuppressWarnings("deprecation")
//	public void AnimateFrame()
//	{
//		
//		Bitmap frogBitmap = ReadBitmap(this, R.drawable.frog_smile_frame);
//		for (int i = 0; i < 4; i++) {
//			Bitmap frameBitmap = Bitmap.createBitmap(frogBitmap, 0, 100*i, 100, 100);
//			animDrawable.addFrame(new BitmapDrawable(getResources(),frameBitmap), 100);
//		}
//		
//		animDrawable.setOneShot(false);
//		int sdk = android.os.Build.VERSION.SDK_INT;
//		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//		    imageView.setBackgroundDrawable(animDrawable);
//		} else {
//		    imageView.setBackground(animDrawable);
//		}
//		
//		animDrawable.start();
//	}
	
	/**
	 * 停止动画
	 */
	public void stopAnimate(View view)
	{
		
		Intent goMain = new Intent();
		goMain.setClass(ViewWelcome.this, ViewSelectGame.class);
		startActivity(goMain);
	}
	
	public void gotoMain()
	{
		Intent intentGo = new Intent();
		intentGo.setClass(ViewWelcome.this, ViewSelectGame.class);
		startActivity(intentGo);
//		finish();
	}
	
//	public Bitmap ReadBitmap(Context context,int resID)
//	{
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inPreferredConfig = Bitmap.Config.RGB_565;
//		options.inPurgeable = true;
//		options.inInputShareable = true;
//		
//		//获取图片资源
//		InputStream inputStream = context.getResources().openRawResource(resID);
//		return BitmapFactory.decodeStream(inputStream, null, options);
//	}
	
	@Override
	public void onDestroy()
	{
		// SoundPlayer.setMusicSt(false);
		super.onDestroy();
	}
	

}
