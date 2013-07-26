package com.example.myfrog;

import com.example.myforg.R;

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
	
	
	public void changeColor(String colorname)
	{
		
	}
	
	public void call()
	{
		
	}
	
	
	public ImageView playIndex()
	{
		ImageView imageView = new ImageView(null);
		imageView.setBackgroundResource(R.anim.index_anmi);
		AnimationDrawable animDrawable = (AnimationDrawable) imageView
				.getBackground();
		animDrawable.start();
		return imageView;
	}
	
	
	public ImageView playlodding() {
		ImageView imageView = new ImageView(null);
		imageView.setBackgroundResource(R.anim.index_loading);
		AnimationDrawable animDrawable = (AnimationDrawable) imageView
				.getBackground();
		animDrawable.start();
		return imageView;
	}
	

}
