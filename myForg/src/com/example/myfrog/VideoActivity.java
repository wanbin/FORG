package com.example.myfrog;

//import java.io.File;

import com.example.myforg.R;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.VideoView;

public class VideoActivity extends GameBase {
	VideoView startVideo;
	//MediaController mController;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.video_activity);
		startVideo = (VideoView) findViewById(R.id.video);
		
		//mController = new MediaController(this);
		//File video = new File("C:/intro.mp4");
//		if (video.exists()) {
			startVideo.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.intro);
		//startVideo.setVideoURI(uri);
			//startVideo.setMediaController(mController);
			//mController.setMediaPlayer(startVideo);
			startVideo.requestFocus();
			startVideo.start();
			//finish();
			startVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
	        {           
	            public void onCompletion(MediaPlayer mp) 
	            {
	            		Log("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
	            		TEST=false;
	            		Intent goMain = new Intent();
	        			goMain.setClass(VideoActivity.this, WelcomeActivity.class);
	        			startActivity(goMain);
	        			finish();
	            }   
	            
	        }); 
			//finish();   
		//}
	}

	    
	    

}
