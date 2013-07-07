package com.example.myforg;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class ViewBase extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		String className = getTraceInfo();
		Log.v("tag", "CREATE View " + className + "Success!");
	}

	public static String getTraceInfo() {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		sb.append("class: ").append(stacks[1].getClassName());
		return sb.toString();
	}

	protected void Log(String string) {
		Log.v("tag", string);
	}

	protected void fadeIn(TextView textView) {
		AnimationSet as = new AnimationSet(true);
		AlphaAnimation fadein = new AlphaAnimation(0.0f, 1.0f);
		fadein.setDuration(500);
		AlphaAnimation fadeout = new AlphaAnimation(1.0f, 0.0f);
		fadeout.setDuration(300);
		fadeout.setStartOffset(500);
		as.addAnimation(fadein);
		as.addAnimation(fadeout);
		textView.setAnimation(as);
		//保留结束时效果
		as.setFillAfter(true);
		as.startNow();
	}
	
	
	protected void fadeOut(TextView textView) {
		AlphaAnimation fade = new AlphaAnimation(1.0f, 0.0f);
		fade.setDuration(1000);
		fade.setRepeatMode(Animation.REVERSE);
		textView.setAnimation(fade);
		fade.startNow();
	}
}
