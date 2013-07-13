package com.example.view;

import com.example.myforg.R;
import com.example.util.ResReader;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DriveView extends SurfaceView implements SurfaceHolder.Callback{

	Context mContext;
	Paint   mPaint;
	
	private boolean running = true;
	
	private Bitmap background_1,background_2;
	private Bitmap drivingFrogBitmap;
	
	private Bitmap giraffe[] = new Bitmap[13];
	
	private ResReader resReader;
	
	private static int _x,_x2,_x3,background_width;
	
	SurfaceHolder Holder;
	Thread drivingThread;
	
	
	public DriveView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		
		resReader = new ResReader(context);
		
//		backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		
		background_1 = resReader.getImg("drive","skate-bg1.png");
		background_2 = resReader.getImg("drive", "skate-bg2.png");
		
		
		for(int i=0;i<giraffe.length;i++){
			giraffe[i]=BitmapFactory.decodeResource(getResources(), R.drawable.cg_jump_000+i);
		}
		
		Holder = this.getHolder();
		Holder.addCallback(this);
		
		background_width = background_1.getWidth();
		
		_x = 0;
		_x2 = background_width;
		_x3 = 0;
	}
	
	
	
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// 启动自定义线程进行绘图
		drivingThread = new Thread(new DriveThread());
		drivingThread.start();
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	
	
	class DriveThread implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Canvas canvas = null;
			int rotate = 0 ;
			int position = 0;
			mPaint = new Paint();
			int runCount = 0;
			while (running) {
				try {
					canvas = Holder.lockCanvas();
					canvas.drawColor(Color.WHITE);
					//绘制背景
					_x -= 10;
					_x2 -= 10;
					Log.d("Frog",String.valueOf(_x));
					if (_x <= -background_width) {
						_x = background_width;
					}
					if (_x2 <= -background_width) {
						_x2 = background_2.getWidth();
					}
					canvas.drawBitmap(background_1, _x, 0, mPaint);
					canvas.drawBitmap(background_2, _x2,0, mPaint);
					
					runCount++;
					if (runCount >= giraffe.length) {
						runCount = 0;
					}
					
					
//					_x3 += 10;
					
					if (_x3 > 800) {
						_x3 = 0;
					}
					Matrix m = new Matrix();
					m.postScale(-1, 1);
					m.postTranslate(100, 350);
					canvas.drawBitmap(giraffe[runCount], m, mPaint);
//					mPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
//					mPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OVER));
					
//					m.postRotate(
//							(rotate+=48)%360,
//							drivingFrogBitmap.getWidth()/2,
//							drivingFrogBitmap.getHeight()/2
//							);
					
//					
//					canvas.scale(0.5f, 0.5f);
//					
//					m.postTranslate((position+=10)%300, (position+=10)%300);
//					
//					canvas.drawBitmap(drivingFrogBitmap, m, mPaint);
					
					Thread.sleep(30);
				} catch (Exception e) {
					// TODO: handle exception
				} finally{
					Holder.unlockCanvasAndPost(canvas);
				}
			}
		}
		
		private void name() {
			
		}
		
	}

}
