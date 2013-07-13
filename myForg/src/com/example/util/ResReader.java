package com.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;


public class ResReader extends View{
	
	public ResReader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 从PNG资源文件中取出相应的图片资源
	 * @param idpack
	 * @param idimg
	 * @return
	 */
	public Bitmap getImg(String packName, String imgName) {
		int idpack = getResources().getIdentifier("com.example.myforg:drawable/"+packName,null,null);
		int idimg = getResources().getIdentifier("com.example.myforg:drawable/"+packName+"_txt",null,null);
		Bitmap frogBitmap = ReadBitmap(this, idpack);
		
		String[] arr = getStringFromRes(idimg, imgName);
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
	
	protected Bitmap ReadBitmap(ResReader resReader,int resID)
	{
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
//		options.inPurgeable = true;
//		options.inInputShareable = true;
		
		//获取图片资源
		InputStream inputStream = resReader.getResources().openRawResource(resID);
		return BitmapFactory.decodeStream(inputStream, null, options);
	}

	/**
	 * 从相应的资源配置文件中取出来需要的一条图片记录
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
