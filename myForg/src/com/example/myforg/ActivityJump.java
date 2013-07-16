package com.example.myforg;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActivityJump extends ViewBase {
	private ImageView btnJumpOne;
	private ImageView btnJumpTwo;
	private ImageView btnFrog;
	private AnimationDrawable animDrawable;
	private LinearLayout linearLayout;

	private int objectLong = 40;
	private int mapNow = 0;
	private int[] objectplace = { 1, 1, 2, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1,
			1, 0, 3, 1, 2, 0, 1, 4, 4, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1,
			0, 3, 1, 5, 1, 2, 5, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1,
			1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jump);
		btnJumpOne = (ImageView) findViewById(R.id.imageOne);
		btnJumpTwo = (ImageView) findViewById(R.id.imageTwo);
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		// linearLayout.setWidth();
		btnFrog = (ImageView) findViewById(R.id.imageFrog);
		btnJumpOne.setImageBitmap(getImg("jumpbutton", "jumpbutton10.png"));
		btnJumpTwo.setImageBitmap(getImg("jumpbutton", "jumpbutton20.png"));

		btnFrog.setBackgroundResource(R.anim.jump);
		animDrawable = (AnimationDrawable) btnFrog.getBackground();

		btnJumpOne.setOnClickListener(new Button.OnClickListener() {// 创建监听
					@Override
					public void onClick(View v) {
						jumpOne();
					}
				});

		btnJumpTwo.setOnClickListener(new Button.OnClickListener() {// 创建监听
					@Override
					public void onClick(View v) {
						jumpTwo();
					}
				});
		initMap(mapNow, objectLong);

	}

	protected int[] initObject(int startPlace) {
		int[] place = new int[objectLong];
		for (int i = startPlace; i < startPlace + objectLong; i++) {
			place[i - startPlace] = objectplace[i];
		}
		return place;
	}

	protected void initMap(int start, int count) {
		// int[] map = initObject(start);
		// gridlayoutMap.removeAllViews();
		if (linearLayout.getChildCount() == 0) {
			for (int i = 0; i < objectLong; i++) {
				ImageView imgMap = new ImageView(this);
				// imgMap.setTag("btnfrog" + i);
				Bitmap animDrawable = getImg("zhaoze_objects", "objects000"
						+ objectplace[i] + ".png");
				imgMap.setImageBitmap(animDrawable);
				linearLayout.addView(imgMap, 132, 58);
			}
		}
			
		// } else {
		// for (int i = 0; i < objectLong; i++) {
		// String tag = "btnfrog" + i;
		// ImageView imgMap = (ImageView) tableRow
		// .findViewWithTag(tag);
		// Bitmap animDrawable = getImg("zhaoze_objects", "objects000"
		// + map[i] + ".png");
		// imgMap.setImageBitmap(animDrawable);
		// }
		// }
	}

	protected void moveObject(int oneTwo) {

	}

	protected void jumpOne() {
		animDrawable.stop();
		animDrawable.start();

		AnimationSet as = new AnimationSet(true);
		TranslateAnimation al = new TranslateAnimation(0, -(mapNow * 132), 0,
				-(mapNow * 132) - 132, 0, 0, 0,
				0);
		al.setDuration(280);
		TranslateAnimation al2 = new TranslateAnimation(0, -132, 0, 0, 0, 0, 0,
				0);
		al2.setDuration(0);
		al2.setStartOffset(280);
		as.addAnimation(al);
		// as.addAnimation(al2);
		as.setFillAfter(true);
		mapNow += 1;

		al.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				btnJumpOne.setClickable(false);
				btnJumpTwo.setClickable(false);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// initMap(mapNow, 1);
				btnJumpTwo.setClickable(true);
				btnJumpOne.setClickable(true);
			}
		});

		linearLayout.startAnimation(as);
		Log("Jump One");
	}

	protected void jumpTwo() {
		animDrawable.stop();
		animDrawable.start();
		mapNow += 2;
		AnimationSet as = new AnimationSet(true);
		TranslateAnimation al = new TranslateAnimation(0, -(mapNow * 132), 0,
				-(mapNow * 132) - 2 * 132, 0, 0, 0, 0);
		al.setDuration(280);
		TranslateAnimation al2 = new TranslateAnimation(0, -132 * 2, 0, 0, 0,
				0, 0, 0);
		al2.setDuration(0);
		al2.setStartOffset(280);
		as.addAnimation(al);
		// as.addAnimation(al2);
		as.setFillAfter(true);
		al.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				btnJumpOne.setClickable(false);
				btnJumpTwo.setClickable(false);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// initMap(mapNow, 2);
				btnJumpTwo.setClickable(true);
				btnJumpOne.setClickable(true);
			}
		});
		linearLayout.startAnimation(as);
		Log("Jump Two");
	}
}
