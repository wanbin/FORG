package com.example.myforg;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

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
}
