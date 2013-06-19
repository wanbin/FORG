package com.example.myforg;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

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
  
        Ev1 = (TextView)findViewById(R.id.myText);    
  
        Button Btn1 = (Button)findViewById(R.id.button1);//获取按钮资源    
        Btn1.setOnClickListener(new Button.OnClickListener(){//创建监听    
        	int a;
            @Override
			public void onClick(View v) {    
                String strTmp = String.valueOf(a++);    
                Ev1.setText(strTmp);    
            }    
  
        });
        }

}
