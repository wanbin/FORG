package com.example.myforg;

//import java.io.File;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.provider.MediaStore.Video;
import android.widget.MediaController;
import android.widget.VideoView;

public class kaishidonghua extends ViewBase {
	VideoView kaishidonghua;
	MediaController mController;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.kaishidonghua);
		// 界面上的VideoView组件
		kaishidonghua = (VideoView) findViewById(R.id.video);
		mController = new MediaController(this);
		//File video = new File("C:/intro.mp4");
//		if (video.exists()) {
			kaishidonghua.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.intro);
		//kaishidonghua.setVideoURI(uri);
			kaishidonghua.setMediaController(mController);
			mController.setMediaPlayer(kaishidonghua);
			kaishidonghua.requestFocus();
			kaishidonghua.start();
		//}
	}

}
