package com.example.myforg;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewGameChangeColor extends ViewBase {
	protected Button startGame;
	protected View viewcan;
	protected GridLayout girdlayout;
	private int frogcount;
	private int gamePro;
	private Timer timer;
	private boolean start;
	private int timelimit;
	private int source;
	private TextView remaintime;
	private TextView textScoure;
	private TextView addTime;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_color);
		startGame = (Button) findViewById(R.id.btnStart);
		remaintime = (TextView) findViewById(R.id.remaintime);
		textScoure = (TextView) findViewById(R.id.textSource);
		addTime = (TextView) findViewById(R.id.addtime);

		girdlayout = (GridLayout) findViewById(R.id.gridlayout1);
		startGame.setOnClickListener(new Button.OnClickListener() {// 创建监听
					@Override
					public void onClick(View v) {
						startGame();
					}
				});
	}

	protected void startGame() {
		timelimit = 1000;
		gamePro = -1;
		source = 0;
		controlGame();
		updateTime();
	}

	/**
	 * 这个方法控制游戏进程
	 */
	protected void controlGame() {
		int newcount = -1;
		int column = 2;
		int row = 2;
		if (gamePro == -1) {
			newcount = 4;
			column = 2;
			row = 2;
		} else if (gamePro == 4) {
			newcount = 6;
			column = 3;
			row = 2;
		} else if (gamePro == 6) {
			newcount = 9;
			column = 3;
			row = 3;
		} else if (gamePro == 9) {
			newcount = 12;
			column = 4;
			row = 3;
		} else if (gamePro == 12) {
			newcount = 16;
			column = 4;
			row = 4;
		} else if (gamePro == 16) {
			newcount = 20;
			column = 5;
			row = 4;
		} else if (gamePro == 20) {
			newcount = 4;
			column = 2;
			row = 2;
		}
		if (gamePro != -1) {
			timelimit = timelimit + 100;
			addTime.setText("+1Second");
			fadeIn(addTime);
		}

		if (start == false && gamePro == -1) {
			start = true;
			if (timer == null) {
				timer = new Timer();
				timer.schedule(timetask, 0, 10);
			}
			startGame.setClickable(false);
			girdlayout.setVisibility(View.VISIBLE);
		}

		initFrog(newcount, column, row);
		gamePro = newcount;
	}

	protected void initFrog(int count, int column, int row) {
		frogcount = count;
		int temclickcount=0;
		girdlayout.removeAllViews();
		girdlayout.setColumnCount(column);
		girdlayout.setRowCount(row);
		for (int n = 0; n < count; n++) {
			Button btn = new Button(this);
			Random random = new Random();
			int clickcount = Math.abs(random.nextInt()) % 2+1;
			btn.setText("btn" + clickcount);
			btn.setTag(clickcount);
			//计算本次游戏的需要点击次数
			temclickcount+=clickcount;
			btn.setOnClickListener(new Button.OnClickListener() {// 创建监听
				@Override
				public void onClick(View v) {
					int temtag=(Integer)v.getTag();
					if(temtag<=1)
					{
						v.setVisibility(View.INVISIBLE);
					}
					else{
						temtag-=1;
						Button tem=(Button)v;
						tem.setText("btn"+temtag);
						v.setTag(temtag);
					}
					
					frogcount--;
					source++;
					updateText();
					if (frogcount <= 0) {
						controlGame();
					}
				}
			});
			girdlayout.addView(btn);
		}
		frogcount=temclickcount;
	}

	protected int checkBtnCount() {

		return 1;
	}

	// 加十毫秒
	private void addTenMMS() {
		if (start == true) {
			timelimit -= 1;
		}
		if (timelimit <= 0) {
			timelimit = 0;
			start = false;
			startGame.setClickable(true);
			girdlayout.setVisibility(View.INVISIBLE);
			
		}
		updateTime();
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			addTenMMS();
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

	private void updateTime() {
		remaintime.setText("Remain:"
				+ Double.toString((double) timelimit / 100) + "s");
	}

	private void updateText() {
		textScoure.setText("Source:" + source);
	}

}
