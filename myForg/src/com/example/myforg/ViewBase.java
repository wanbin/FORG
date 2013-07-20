package com.example.myforg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

public class ViewBase extends Activity {
	int disWidth;
	int disHeight;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		String className = getTraceInfo();
		Log.v("tag", "CREATE View " + className + "Success!");
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		disWidth = (int) dm.widthPixels;
		disHeight = (int) dm.heightPixels;

	}

	public static String getTraceInfo() {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		sb.append("class: ").append(stacks[1].getClassName());
		return sb.toString();
	}

	protected void Log(String string) {
		Log.v("tag", string);
	}

	protected void fadeIn(TextView textView) {
		AnimationSet as = new AnimationSet(true);
		AlphaAnimation fadein = new AlphaAnimation(0.0f, 1.0f);
		fadein.setDuration(500);
		AlphaAnimation fadeout = new AlphaAnimation(1.0f, 0.0f);
		fadeout.setDuration(300);
		fadeout.setStartOffset(500);
		as.addAnimation(fadein);
		as.addAnimation(fadeout);
		textView.setAnimation(as);
		//��������ʱЧ��
		as.setFillAfter(true);
		as.startNow();
	}
	
	
	protected void fadeOut(TextView textView) {
		AlphaAnimation fade = new AlphaAnimation(1.0f, 0.0f);
		fade.setDuration(1000);
		fade.setRepeatMode(Animation.REVERSE);
		textView.setAnimation(fade);
		fade.startNow();
	}

	/**
	 * ��PNG��Դ�ļ���ȡ����Ӧ��ͼƬ��Դ
	 * @param packName ��ͼ����
	 * @param imgPackName �ڲ�ͼ������
	 * @return
	 */
	public Bitmap getImg(String packName, String imgPackName) {
		int idpack = getResources().getIdentifier("com.example.myforg:drawable/"+packName,null,null);
		int idimg = getResources().getIdentifier("com.example.myforg:drawable/"+packName+"_txt",null,null);
		Bitmap frogBitmap = ReadBitmap(this, idpack);
		
		String[] arr = getStringFromRes(idimg, imgPackName);
		if (arr == null) {
			return null;
		}
		Bitmap imgNew = Bitmap.createBitmap(frogBitmap,
				changeStringToInt(arr[1]), changeStringToInt(arr[2]),
				changeStringToInt(arr[3]), changeStringToInt(arr[4]));
		return imgNew;
	}

	public int changeStringToInt(String str) {
		return Integer.parseInt(str.replaceAll(" ","") );
	}
	
	protected Bitmap ReadBitmap(Context context,int resID)
	{
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
//		options.inPurgeable = true;
//		options.inInputShareable = true;
		
		//��ȡͼƬ��Դ
		InputStream inputStream = context.getResources().openRawResource(resID);
		return BitmapFactory.decodeStream(inputStream, null, options);
	}

	/**
	 * ����Ӧ����Դ�����ļ���ȡ������Ҫ��һ��ͼƬ��¼
	 * @param id
	 * @param imgName
	 * @return
	 */
	public String[] getStringFromRes(int id, String imgName) {
		InputStream inputStream = getResources().openRawResource(id);
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(inputStream, "gbk");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				String[] arr = line.split(",");
				if (arr[0].equals(imgName)) {
					return arr;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
