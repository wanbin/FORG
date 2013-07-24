package com.example.myforg;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils.StringSplitter;
import android.util.Log;
import android.widget.TextView;

/**游戏结束后，显示本局等分和 历史最好记录
 * @author liuchunlong
 * @version 2013-07-17 00:23
 */
public class ShowScore extends ViewBase {
	private TextView text_currentScore;
	private TextView text_maxScore;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_score);
		Log.v("tag", "CREATE View " + ShowScore.class + "Success!");
		text_currentScore	= (TextView)findViewById(R.id.textView4); 
		text_maxScore	= (TextView)findViewById(R.id.textView3); 
		//设置intent传递的值
		Intent intent	= getIntent();
		String currentScore	= intent.getStringExtra("currentScore");
		String maxScore	= intent.getStringExtra("maxScore");
		
		Log.e("tag", "currentScore============= " + currentScore);
		Log.e("tag", "text_maxScore============ " + maxScore);
		if(null!=currentScore&&null!=maxScore){
			text_maxScore.setText(maxScore);
			text_currentScore.setText("111");
		}
		text_currentScore.setText("000");
		
	}
	
	private void setText(){
		Intent intent	= this.getIntent();
		String currentScore	= intent.getStringExtra("currentScore");
		String maxScore	= intent.getStringExtra("maxScore");
		if(null!=currentScore&&null!=maxScore){
			text_maxScore.setText(maxScore);
			text_currentScore.setText("111");
		}
	}
}
