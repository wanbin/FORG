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
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
	//按钮指针
	private View currentButton;

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
		breakHelmet.addFrame(new ColorDrawable(0), 0);
	    breakHelmet.setOneShot(true);
		
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
			newcount = 1;
			column = 1;
			row = 1;
		} else if (gamePro == 1) {
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
		gamePro = newcount;
		initFrog(newcount, column, row);
		
	}



	@SuppressLint("NewApi")
	protected void initFrog(int count, int column, int row) {
		frogcount = count;
		Log.d("frogCount init", String.valueOf(frogcount));
		tableLayout.removeAllViews();
		for (int n = 0; n < row; n++) {
			TableRow tableRow = new TableRow(this);
			tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
			tableLayout.addView(tableRow);
			for (int m = 0; m < column; m++) {
				Button btn = new Button(this);
				Random random = new Random();
				int clickcount = Math.abs(random.nextInt()) % 2 + 1;
				FrameLayout frameLayout = new FrameLayout(this);
				frameLayout.setTag(clickcount);
				
				btn.setBackgroundResource(R.anim.yellow);
				btn.setMaxHeight(50);
				btn.setClickable(false);
				frameLayout.addView(btn);
				if (clickcount == 2) {
					Button helmetBtn = new Button(this);
					helmetBtn.setClickable(false);
					helmetBtn.setId(n);
					helmetBtn.setBackground(breakHelmet);
					helmetBtn.setHeight(20);
					helmetBtn.setWidth(20);
					frameLayout.addView(helmetBtn);
				}
				frameLayout.setBackgroundColor(Color.BLUE);
				frameLayout.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View view) {
						// TODO Auto-generated method stub
						int index = (Integer) view.getTag()-1;
						Log.d("index", String.valueOf(index));
						View btn = ((ViewGroup)view).getChildAt(index);
						btn.setVisibility(View.GONE);
						Log.d("FrogCount before",String.valueOf(frogcount));
						if (index <= 0) {
							frogcount = frogcount-1;
							source++;
							updateText();
							view.setClickable(false);
							Log.d("FrogCount after", String.valueOf(frogcount));
							if (frogcount <= 0) {
								controlGame();
							}
						}
						view.setTag(index);
						
					}
				});
				tableRow.addView(frameLayout, 50, 100);
			}
		}
		// girdlayout.setScaleY(0.5f);
		// girdlayout.setScaleX(0.5f);
	}

	protected int checkBtnCount() {

		return 1;
	}
	
	private void checkIfAnimationDone(AnimationDrawable anim){
        final AnimationDrawable a = anim;
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checkIfAnimationDone(a);
                } else{
                	currentButton.setVisibility(View.GONE);
                }
            }
        }, timeBetweenChecks);
        
    };

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
