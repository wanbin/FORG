package com.example.myforg;

import android.os.Bundle;
import android.text.TextUtils.StringSplitter;
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
		
		text_currentScore	= (TextView)findViewById(R.id.textView4); 
		text_maxScore	= (TextView)findViewById(R.id.textView3); 
		
		Bundle bundle	= this.getIntent().getExtras();
		String currentScore	= bundle.getString("currentScore");
		String maxScore	= bundle.getString("maxScore");
		
		text_maxScore.setText(maxScore);
		text_currentScore.setText(currentScore);
	}
}
