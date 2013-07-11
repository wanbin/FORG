package com.example.myforg;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GiraffeModel extends Button {

	private AnimationDrawable animDrawable;
	
	public GiraffeModel(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 变颜色
	 * @param colorname
	 */
	public void changeColor(String colorname)
	{
		
	}
	/**
	 * 叫声
	 */
	public void call()
	{
		
	}
	/**
	 * 首页动画
	 */
	public ImageView playIndex()
	{
		ImageView imageView = new ImageView(null);
		imageView.setBackgroundResource(R.anim.index_anmi);
		AnimationDrawable animDrawable = (AnimationDrawable) imageView
				.getBackground();
		animDrawable.start();
		return imageView;
	}
	/**
	 * 加载动画lodding
	 */
	public ImageView playlodding() {
		ImageView imageView = new ImageView(null);
		imageView.setBackgroundResource(R.anim.index_loading);
		AnimationDrawable animDrawable = (AnimationDrawable) imageView
				.getBackground();
		animDrawable.start();
		return imageView;
	}
	

}
