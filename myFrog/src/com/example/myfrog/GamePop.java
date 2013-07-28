package com.example.myfrog;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.callback.Callback;

import com.example.myfrog.R;
import com.example.util.ResReader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GamePop extends GameBase {
	protected Button startGame;
	protected View viewcan;
	private int frogcount;
	private int gamePro;
	private Timer timer;
	private boolean start;
	private int timelimit;
	private int source;
	private TextView remaintime;
	private TextView textScoure;
	private TextView addTime;
	private TableLayout tableLayout;
	private int randomPlayAnmi = 0;
	private int[] aninTime;
	//头盔破碎动画
	private AnimationDrawable breakHelmet;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_painter);
		startGame = (Button) findViewById(R.id.btnStart);
		remaintime = (TextView) findViewById(R.id.remaintime);
		textScoure = (TextView) findViewById(R.id.textSource);
		addTime = (TextView) findViewById(R.id.addtime);
		tableLayout = (TableLayout) findViewById(R.id.tableLayout);
		tableLayout.setGravity(Gravity.CENTER);
		aninTime = new int[25];
		
		//初始化帽子破碎的动画
		ResReader resReader = new ResReader(this, "helmet");
		breakHelmet = new AnimationDrawable();
		for(int i=1;i<16;i++){
			Bitmap helmet = resReader.getImg("helmet"+i);
			BitmapDrawable frame = new BitmapDrawable(this.getResources(),helmet);
			breakHelmet.addFrame(frame,10);
		}
		
		for (int i = 0; i < 25; i++) {
			Random random = new Random();
			aninTime[i] = Math.abs(random.nextInt()) % 100;
		}
		startGame.setOnClickListener(new Button.OnClickListener() {
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

	
	protected void controlGame() {
		int newcount = -1;
		int column = 2;
		int row = 2;
		if (gamePro == -1) {
			newcount = 2;
			column = 1;
			row = 1;
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
			newcount = 15;
			column = 5;
			row = 3;
		} else if (gamePro == 15) {
			newcount = 21;
			column = 7;
			row = 3;
		} else if (gamePro == 21) {
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
			tableLayout.setVisibility(View.VISIBLE);
		}

		initFrog(newcount, column, row);
		gamePro = newcount;
	}



	@SuppressLint("NewApi")
	protected void initFrog(int count, int column, int row) {
		frogcount = count;
		tableLayout.removeAllViews();
		int temclickcount = 0;
		for (int n = 0; n < row; n++) {
			TableRow tableRow = new TableRow(this);
			tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
			tableLayout.addView(tableRow);
			for (int m = 0; m < column; m++) {
				Button btn = new Button(this);
				Random random = new Random();
				int clickcount = Math.abs(random.nextInt()) % 2 + 1;
				FrameLayout frameLayout = new FrameLayout(this);
				btn.setBackgroundResource(R.anim.yellow);
				btn.setMaxHeight(50);
				temclickcount += clickcount;
				btn.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						v.setVisibility(View.INVISIBLE);
						frogcount--;
						source++;
						updateText();
						if (frogcount <= 0) {
							controlGame();
						}
					}
				});
				
				frameLayout.addView(btn);
				if (clickcount == 2) {
					Button helmetBtn = new Button(this);
					helmetBtn.setBackground(breakHelmet);
					helmetBtn.setMaxHeight(50);
					
					helmetBtn.setOnClickListener(new Button.OnClickListener() {

						@Override
						public void onClick(View view) {
							// TODO 监听帽子点击事件
							breakHelmet.start();
						}
					});
					frameLayout.addView(helmetBtn);
				}
				tableRow.addView(frameLayout, 200, 200);
			}
		}
		frogcount = temclickcount;
		// girdlayout.setScaleY(0.5f);
		// girdlayout.setScaleX(0.5f);
	}

	protected int checkBtnCount() {

		return 1;
	}

	private void addTenMMS() {
		if (start == true) {
			timelimit -= 1;
		}
		if (timelimit <= 0) {
			timelimit = 0;
			start = false;
			startGame.setClickable(true);
			tableLayout.setVisibility(View.INVISIBLE);
		}
		randomPlayAnmi++;
		if (randomPlayAnmi % 5 == 0) {
			for (int m = 0; m < aninTime.length; m++) {
				aninTime[m]--;
				if (aninTime[m] <= 0) {
//					randPlayAnmi(m);
					Random random = new Random();
					aninTime[m] = Math.abs(random.nextInt()) % 100 + 100;

				}
			}
		}
		updateTime();
	}


	private void randPlayAnmi(int palyindex) {
		int tem = 0;
		for (int i = 0; i < tableLayout.getChildCount(); i++) {
			TableRow v = (TableRow) tableLayout.getChildAt(i);
			for (int m = 0; m < v.getChildCount(); m++) {
				tem++;
				if (tem == palyindex) {
					View imageTem = (View) v.getChildAt(m);
					AnimationDrawable animDrawable = (AnimationDrawable) imageTem
							.getBackground();
					animDrawable.stop();
					animDrawable.start();
					return;
				}
			}
		}
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
