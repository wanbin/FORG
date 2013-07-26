package com.example.myfrog;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.view.DriveView;
public class GameDrive extends Activity {
	
	DriveView driveView ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
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
