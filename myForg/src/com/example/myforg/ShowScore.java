package com.example.myforg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

/**��Ϸ��������ʾ���ֵȷֺ� ��ʷ��ü�¼
 * @author liuchunlong
 * @version 2013-07-17 00:23
 */
public class ShowScore extends ViewBase {
	private TextView text_currentScore;
	private TextView text_maxScore;
	private int defaultMax;
	private RatingBar ratBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_score);
		Log.v("tag", "CREATE View " + ShowScore.class + "Success!");
		text_currentScore	= (TextView)findViewById(R.id.textView4); 
		text_maxScore	= (TextView)findViewById(R.id.textView3); 
		ratBar			= (RatingBar)findViewById(R.id.ratingBar1); 
		//����intent���ݵ�ֵ
		setText();
//		Intent intent	= getIntent();
//		String currentScore	= intent.getStringExtra("currentScore");
//		String maxScore	= intent.getStringExtra("maxScore");
//		
//		Log.e("tag", "currentScore============= " + currentScore);
//		Log.e("tag", "text_maxScore============ " + maxScore);
//		if(null!=currentScore&&null!=maxScore){
//			text_maxScore.setText(maxScore);
//			text_currentScore.setText(currentScore);
//		}
		
		
		
	}
	
	/**
	 * ��ȡ��Ϸ������ ��תҳ��ʱ�����ֵ
	 */
	private void setText(){
		Intent intent	= this.getIntent();
		String currentScore	= intent.getStringExtra("currentScore");
		int current 	= intent.getIntExtra("current", 0);
		String maxScore	= intent.getStringExtra("maxScore");
		if(null!=currentScore&&null!=maxScore){
			text_maxScore.setText(maxScore);
			text_currentScore.setText(currentScore);
		}
		//����RatingBar
		ratBar.setRating(current/20);
	}
	
	public void setRatingBar(int defaultMax){
		
	}
}
