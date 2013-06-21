package com.example.myforg;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("UseSparseArrays")
public class MainActivity extends Activity {
	 private SoundPool sp; 
	 private HashMap<Integer,Integer> spMap; 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	TextView Ev1;
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		
		
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);
  
        sp=new SoundPool(2,AudioManager.STREAM_SYSTEM,0); 
        spMap = new HashMap<Integer,Integer>(); 
        
        spMap.put(1, sp.load(this, R.raw.clap, 1)); 
        Ev1 = (TextView)findViewById(R.id.myText);    
  
        Button Btn1 = (Button)findViewById(R.id.button1);//获取按钮资源    
        Btn1.setOnClickListener(new Button.OnClickListener(){//创建监听    
        	int a;
            @Override
			public void onClick(View v) {    
                String strTmp = String.valueOf(a++);    
                Ev1.setText(strTmp);    
//                sp.paly(spMap.get(1)); 
                sp.play(spMap.get(spMap.get(1)), 50, 50, 1, 1, 1);
            }    
  
        });
        }
	

}
