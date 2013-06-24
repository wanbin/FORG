package com.example.myforg;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Animation {
	
	/*上一祯播放的时间*/
	private long mLastPlayTime = 0;
	
	private int mPlayID = 0;
	
	private int mFrameCount = 0;
	
	private Bitmap[] mFrameBitmap = null;
	
	private Boolean mIsLoop = false;
	
	private Boolean mIsEnd = false;
	
	/*播放动画间隙时间*/
	private static final int ANIM_TIME = 100;
	
	/**
	 * 构造函数
	 * @param context
	 * @param frameBitmapID
	 * @param isLoop
	 */
	public Animation(Context context,int [] frameBitmapID, Boolean isLoop)
	{
		mFrameCount = frameBitmapID.length;
		mFrameBitmap = new Bitmap[mFrameCount];
		
		for (int i = 0; i < frameBitmapID.length; i++) {
			mFrameBitmap[i] = ReadBitmap(context, frameBitmapID[i]);
		}
		
		mIsLoop = isLoop;
	}
	
	/**
	 * 构造函数
	 * @param context
	 * @param frameBitmap
	 * @param isLoop
	 */
	public Animation(Context context, Bitmap[] frameBitmap,Boolean isLoop)
	{
		mFrameCount = frameBitmap.length;
		mFrameBitmap = frameBitmap;
		mIsLoop = isLoop;
	}
	
	
	/**
	 * 绘制动画中一帧
	 * @param canvas
	 * @param paint
	 * @param x
	 * @param y
	 * @param frameID
	 */
	public void DrawFrame(Canvas canvas,Paint paint,int x,int y,int frameID)
	{
		canvas.drawBitmap(mFrameBitmap[frameID], x, y, paint);
	}
	
	
	/**
	 * 绘制播放动画
	 * @param canvas
	 * @param paint
	 * @param x
	 * @param y
	 * @param frameID
	 */
	public void DrawAnimate(Canvas canvas,Paint paint,int x, int y,int frameID)
	{
		//如果没有播放结束则继续播放
		if (!mIsEnd) {
			DrawAnimate(canvas,paint,x,y,frameID);
			long time = System.currentTimeMillis();
			if (time-mLastPlayTime > ANIM_TIME) {
				mPlayID ++;
				mLastPlayTime = time;
				if (mPlayID > mFrameCount) {
					//标志着播放结束
					mIsEnd = true;
					if (mIsLoop) {
						mIsEnd = false;
						mPlayID= 0;
					}
				}
			}
		}
		
	}
	
	public Bitmap ReadBitmap(Context context,int resID)
	{
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inPurgeable = true;
		options.inInputShareable = true;
		
		//获取图片资源
		InputStream inputStream = context.getResources().openRawResource(resID);
		return BitmapFactory.decodeStream(inputStream, null, options);
	}
}
