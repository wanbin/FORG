package com.example.myfrog;

import java.util.Timer;
import java.util.TimerTask;

import com.example.myfrog.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class ChronometerActivity extends Activity {

	private Chronometer chronometer = null;

	private TextView Ev1;

	/** Called when the activity is first created. */
	@SuppressLint("HandlerLeak")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chronometer_activity);
		Ev1 = (TextView) findViewById(R.id.textView1);
		Timer timer = new Timer();
		timer.schedule(timetask, 1000, 10);
	}

	Handler handler = new Handler() {
		int i=0;
		public void handleMessage(Message msg) {
			i++;
			Ev1.setText( "Soruce:"+i);
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

	public void onStart(View view) {
		chronometer.start();
	}

	public void onStop(View view) {
		chronometer.stop();
	}

	public void onReset(View view) {
		chronometer.setBase(SystemClock.elapsedRealtime());
	}

}
