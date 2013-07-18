package com.example.myforg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.util.ResReader;
import com.example.view.JumpView;

public class ActivityJump extends ViewBase {
	private ImageView btnJumpOne;
	private ImageView btnJumpTwo;
	private ImageView btnFrog;
	private AnimationDrawable animDrawable;
	JumpView jumpView;
	private int objectLong = 8;
	private int mapNow = 0;
	private SurfaceHolder holder;
	private Paint paint;
	private ResReader resReader;
	private int a;
	private boolean running = true;
	private Bitmap back;
	Thread jumpThread;
	Paint mPaint;
	private Bitmap zhaoze01;
	private Bitmap zhaoze02;
	private Bitmap zhaoze03;
	private Bitmap zhaoze04;
	private Bitmap zhaoze05;
	private Bitmap zhaoze06;
	private int jumpStep;
	private int[] objectplace = { 1, 1, 2, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1,
			1, 0, 3, 1, 2, 0, 1, 4, 4, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1,
			0, 3, 1, 5, 1, 2, 5, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1,
			1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jump);
		btnJumpOne = (ImageView) findViewById(R.id.imageOne);
		btnJumpTwo = (ImageView) findViewById(R.id.imageTwo);

		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface);
		surfaceView.setZOrderOnTop(true);
		surfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		resReader = new ResReader(this, "zhaoze_objects");

		zhaoze01 = resReader.getImg("objects0001.png");
		zhaoze02 = resReader.getImg("objects0002.png");
		zhaoze03 = resReader.getImg("objects0003.png");
		zhaoze04 = resReader.getImg("objects0004.png");
		zhaoze05 = resReader.getImg("objects0005.png");
		zhaoze06 = resReader.getImg("objects0006.png");

		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(3);
		holder = surfaceView.getHolder();

		jumpStep = 0;

		holder.addCallback(new SurfaceHolder.Callback() {
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
			}

			public void surfaceCreated(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				jumpThread = new Thread(new JumpThread());
				jumpThread.start();
			}

			public void surfaceDestroyed(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
			}
		});




		// linearLayout.setWidth();
		btnFrog = (ImageView) findViewById(R.id.imageFrog);
		btnJumpOne.setImageBitmap(getImg("jumpbutton", "jumpbutton10.png"));
		btnJumpTwo.setImageBitmap(getImg("jumpbutton", "jumpbutton20.png"));

		btnFrog.setBackgroundResource(R.anim.jump);
		animDrawable = (AnimationDrawable) btnFrog.getBackground();

		btnJumpOne.setOnClickListener(new Button.OnClickListener() {// 创建监听
					@Override
					public void onClick(View v) {
						jumpOne(1);
					}
				});

		btnJumpTwo.setOnClickListener(new Button.OnClickListener() {// 创建监听
					@Override
					public void onClick(View v) {
						jumpOne(2);
					}
				});
		// initMap(mapNow, objectLong);
		// jumpView = new JumpView(this);
		// FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(100,
		// 100);
		// addContentView(jumpView, params);
	}

	protected int[] initObject(int startPlace) {
		int[] place = new int[objectLong];
		for (int i = startPlace; i < startPlace + objectLong; i++) {
			place[i - startPlace] = objectplace[i];
		}
		return place;
	}

	protected void initMap(int start, int count) {
		int[] map = initObject(start);
		for (int i = 0; i < map.length; i++) {

		}
	}

	protected void moveObject(int oneTwo) {

	}

	protected void jumpOne(int count) {
		animDrawable.stop();
		animDrawable.start();
		jumpStep = count;
		jumpThread = new Thread(new JumpThread());
		jumpThread.start();
		setBtn(false);
	}

	private void setBtn(boolean canuse) {
		btnJumpOne.setClickable(canuse);
		btnJumpTwo.setClickable(canuse);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}


	class BackgroudThread implements Runnable {
		@Override
		public void run() {
			try {
				// TODO Auto-generated method stub
				while (running && a < 1000) {
					Canvas canvas = holder.lockCanvas();
					canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
					canvas.drawBitmap(zhaoze01, a, 0, null);
					holder.unlockCanvasAndPost(canvas);
					holder.lockCanvas(new Rect(0, 0, 0, 0));
					holder.unlockCanvasAndPost(canvas);
					a++;
					Thread.sleep(100);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				// holder.unlockCanvasAndPost(canvas);
			}
		}
	}


	class JumpThread implements Runnable {
		@Override
		public void run() {
			int move = 0;
			int[] map = initObject(mapNow);
			boolean init = mapNow == 0 ? true : false;
			try {
				// TODO Auto-generated method stub
				while (move > -132 * jumpStep || init) {
					Canvas canvas = holder.lockCanvas();
					canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
					Bitmap temmap = zhaoze01;
					for (int tem = 0; tem < map.length; tem++) {
						boolean skip = false;
						switch (map[tem]) {
						case 0:
							skip=true;
							break;
						case 1:
							temmap = zhaoze01;
							break;
						case 2:
							temmap = zhaoze02;
							break;
						case 3:
							temmap = zhaoze03;
							break;
						case 4:
							temmap = zhaoze04;
							break;
						}
						if (!skip) {
							canvas.drawBitmap(temmap, tem * 132 + move, 0, null);
						}

					}
					holder.unlockCanvasAndPost(canvas);
					holder.lockCanvas(new Rect(0, 0, 0, 0));
					holder.unlockCanvasAndPost(canvas);
					move = move - 16 * jumpStep;
					init = false;
					Thread.sleep(5);
				}
				mapNow += jumpStep;
				setBtn(true);
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				// holder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
