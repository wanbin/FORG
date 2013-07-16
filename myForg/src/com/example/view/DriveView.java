package com.example.view;

import cn.jpush.android.c.i;

import com.example.myforg.R;
import com.example.util.ResReader;
import com.example.util.SoundPlayer;

import android.R.array;
import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DriveView extends SurfaceView implements SurfaceHolder.Callback{

	Context mContext;
	Paint   mPaint;
	
	private boolean running = true;
	
	private Bitmap background_1,background_2;   //�ƶ��ı���
	private Bitmap streetBitmap;                //��·
	
	private Bitmap streetLine;
	private Bitmap upButton,upButtonClick,downButton,downButtonClick;
	private Bitmap speedUp;
	
	private Rect upRect,downRect;
	private Bitmap giraffe[] = new Bitmap[13];
	
	private ResReader resReader;
	
	private int speed;
	
	private static int 
	_x1,                     //����1��X����
	_x2,                     //����2��X����
	_x3,                     //��·�ߵ�X����
	background_width_1,      //����1�Ŀ��
	background_width_2;      //����2�Ŀ��
	
	private int[] _y = new int[]{150,250,350};
	private int current = 0;
	
	SurfaceHolder Holder;
	Thread drivingThread;
	
	
	public DriveView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		
		resReader = new ResReader(context,"drive");
		
//		backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		
		background_1 = resReader.getImg("skate-bg1.png");
		background_2 = resReader.getImg("skate-bg2.png");
		streetBitmap = resReader.getImg("skate-middle.png");
		streetLine   = resReader.getImg("line.png");
		speedUp      = resReader.getImg("street0002.png");
		
		upButton     = resReader.getImg("upbutton.png");
		upButtonClick= resReader.getImg("upbutton1.png");
		
		downButton   = resReader.getImg("downbutton.png");
		downButtonClick = resReader.getImg("downbutton1.png");
		
		//�������
		upRect = new Rect(40, 510, 145, 575);
		downRect = new Rect(830, 510, 1000, 575);
		
		for(int i=0;i<giraffe.length;i++){
			giraffe[i]=BitmapFactory.decodeResource(getResources(), R.drawable.cg_jump_000+i);
		}
		
		Holder = this.getHolder();
		Holder.addCallback(this);
		
		background_width_1 = background_1.getWidth();
		background_width_2 = background_2.getWidth();
		
		_x1 = 0;
		_x2 = background_width_1;
		_x3 = 0;
		
		speed = 20; //�ƶ��ٶ�
	}
	
	
	
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// �����Զ����߳̽��л�ͼ
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
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int pointx=(int)event.getX();
		int pointy=(int)event.getY();
		if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE)
		{
			if (upRect.contains(pointx,pointy)) {
				if (current > 0) {
					current--;
				}
			}
			
			if (downRect.contains(pointx,pointy)) {
				if (current < 2) {
					current++;
				}
			}
		}
	    return super.onTouchEvent(event);
	}
	
	
	
	class DriveThread implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Canvas canvas = null;
			mPaint = new Paint();
			int runCount = 0;
			int speed_x  = 2000;
			int timeout  = 30;
			while (running) {
				try {
					canvas = Holder.lockCanvas();
					canvas.drawColor(Color.WHITE);
					//���Ʊ���
					_x1 -= speed;
					_x2 -= speed;
					_x3 -= speed;
					
					if (_x1 <= -background_width_1) {
						_x1 = background_width_2+_x2;
					}
					if (_x2 <= -background_2.getWidth()) {
						_x2 = background_width_1+_x1;
					}
					int street = 0;
					while(street <= 1280)
					{
						canvas.drawBitmap(streetBitmap, street,120, mPaint);
						street += streetBitmap.getWidth()-5;
					}
					
					if (_x3 == -80) {
						_x3 = 80;
					}
					//������·��
					canvas.drawBitmap(streetLine, _x3, 320, mPaint);
					canvas.drawBitmap(streetLine, _x3, 430, mPaint);
					//���Ʊ����羰
					canvas.drawBitmap(background_1, _x1, -135, mPaint);
					canvas.drawBitmap(background_2, _x2,-135, mPaint);
					
					runCount++;
					if (runCount >= giraffe.length) {
						runCount = 0;
					}
					
					//����С¹
					Matrix m = new Matrix();
					m.postScale(-1, 1);
					m.postTranslate(300, _y[current]);
					canvas.drawBitmap(giraffe[runCount], m, mPaint);
					
					//�������µİ�ť
					Matrix m2 = new Matrix();
					//�������ϰ�ť
					m2.postScale(0.5f, 0.5f);
					m2.postTranslate(40, 510);
					canvas.drawBitmap(upButton, m2, mPaint);
					m2.postTranslate(830, 0);
					canvas.drawBitmap(downButton, m2, mPaint);
					
					if (speed_x > 0) {
						//���Ƽ���·��
						int speed_y = _y[1]+100;
						canvas.drawBitmap(speedUp,speed_x, speed_y, mPaint);
					}
					speed_x -= speed;
					
					if (speed_x == 300) {
						if(_y[current]==_y[1])
						{
							speed = 80;
							_x3 = 0;
							SoundPlayer.playSound(R.raw.rocket);
						}
					}
					
					if (speed == 80) {
						timeout --;
					}
					
					if (timeout <= 0) {
						speed = 10;
					}
					
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
					Thread.sleep(50);
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
