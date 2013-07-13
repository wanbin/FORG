package com.example.myforg;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.util.SoundPlayer;
import com.example.view.DriveView;

public class DriveActivity extends Activity {
	
	DriveView driveView ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SoundPlayer.playSound(R.raw.claps3);
		
		driveView = new DriveView(this);
		setContentView(driveView);
		
//		driveView = (DriveView) findViewById(R.id.drive);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}		
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d("Frog", "onKeyDown called !");
            driveView.setRunning(false);
        }
        return super.onKeyDown(keyCode, event);
    }
}
