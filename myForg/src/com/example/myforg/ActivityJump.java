package com.example.myforg;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityJump extends ViewBase {
	private ImageView btnJumpOne;
	private ImageView btnJumpTwo;
	private ImageView btnFrog;
	private AnimationDrawable animDrawable;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jump);
		btnJumpOne = (ImageView) findViewById(R.id.imageOne);
		btnJumpTwo = (ImageView) findViewById(R.id.imageTwo);

		btnFrog = (ImageView) findViewById(R.id.imageFrog);


		btnJumpOne.setImageBitmap(getImg("jumpbutton", "jumpbutton10.png"));
		btnJumpTwo.setImageBitmap(getImg("jumpbutton", "jumpbutton20.png"));

		for (int i = 0; i < 14; i++) {

		}

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
	}


	protected void jumpOne() {
		animDrawable.stop();
		animDrawable.start();
		Log("Jump One");
	}

	protected void jumpTwo() {
		animDrawable.stop();
		animDrawable.start();
		Log("Jump Two");
	}
}

