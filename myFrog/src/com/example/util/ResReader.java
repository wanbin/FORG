package com.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


public class ResReader{
	
	private Bitmap resBitmap;
	private List<String> lineList = new ArrayList<String>();
	private Context context;
	private int txtId;
	
	public ResReader(Context c,String packName) {
		context = c;
		txtId = context.getResources().getIdentifier("com.example.myforg:drawable/"+packName+"_txt",null,null);
		int imgId = context.getResources().getIdentifier("com.example.myforg:drawable/"+packName,null,null);
		resBitmap = this.ReadBitmap(imgId);
		this.getListFromRes();
	}
	
	/**
	 * 获取图片
	 * @param imgName
	 * @return
	 */
	public Bitmap getImg(String imgName) {

		String[] arr = getImageString(imgName);
		Bitmap imgNew = null;
		try {

			imgNew = Bitmap.createBitmap(resBitmap, changeStringToInt(arr[1]),
					changeStringToInt(arr[2]), changeStringToInt(arr[3]),
					changeStringToInt(arr[4]));
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
		}
		return imgNew;
	}
	

	private int changeStringToInt(String str) {
		return Integer.parseInt(str.replaceAll(" ","") );
	}
	
	/**
	 * 读取bitmap大图
	 * @param resID
	 * @return
	 */
	private Bitmap ReadBitmap(int resID)
	{
		Log.d("resReader","read bitmap");
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		
		InputStream inputStream = context.getResources().openRawResource(resID);
		return BitmapFactory.decodeStream(inputStream, null, options);
	}
	
	/**
	 * 读出图片所在行
	 * @param imgName
	 * @return
	 */
	private String[] getImageString(String imgName)
	{
		if (lineList == null)
		{
			getListFromRes();
		}
		
		for (String tempLine:lineList) {
			String[] arr = tempLine.split(",");
			if (arr[0].equals(imgName)) {
				return arr;
			}
		}
		return null;
	}

	/**
	 * 将文本放入List以便以后使用
	 * @return
	 */
	private List<String> getListFromRes() {
		String line;
		try {
			Log.d("resReader", "Read txt");
			InputStream inputStream = context.getResources().openRawResource(txtId);
			InputStreamReader inputStreamReader = null;
			try {
				inputStreamReader = new InputStreamReader(inputStream, "gbk");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			while ((line = bufferedReader.readLine()) != null) {
				lineList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
