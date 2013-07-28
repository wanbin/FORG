package com.example.myfrog;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfrog.R;
import com.example.util.ResReader;

public class SettingActivity extends GameBase {
	private ImageView btnSetSound;
	private ImageView btnSetMusic;
	private ImageView btnSetLang;
	private ImageView btnSetCrid;
	private ImageView btnSetReset;

	private TextView txtSound;
	private TextView txtMusic;
	private TextView txtLang;
	private TextView txtCrid;
	private TextView txtReset;

	private ResReader resReader;
	private ResReader settingReader;

	private ImageView btnReturn;
	private boolean soundStatus = true;
	private boolean musicStatus = true;
	private String lang = "cn";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity);
		btnSetSound = (ImageView) findViewById(R.id.imageSetSound);
		btnSetMusic = (ImageView) findViewById(R.id.imageSetMusic);
		btnSetLang = (ImageView) findViewById(R.id.imageSetLang);
		btnSetCrid = (ImageView) findViewById(R.id.imageSetCri);
		btnSetReset = (ImageView) findViewById(R.id.imageSetReset);
		btnReturn = (ImageView) findViewById(R.id.imageReturn);

		resReader = new ResReader(this, "achievements_in_game");
		Bitmap tem = resReader.getImg("achievements_in_game.png");
		btnSetSound.setImageBitmap(tem);
		btnSetMusic.setImageBitmap(tem);
		btnSetLang.setImageBitmap(tem);
		btnSetCrid.setImageBitmap(tem);
		btnSetReset.setImageBitmap(tem);
		
		settingReader = new ResReader(this, "achiev_not_done");
		btnReturn.setImageBitmap(settingReader.getImg("achiev_not_done.png"));

		txtSound = (TextView) findViewById(R.id.txtSound);
		txtMusic = (TextView) findViewById(R.id.txtMusic);
		txtLang = (TextView) findViewById(R.id.txtLang);
		txtCrid = (TextView) findViewById(R.id.txtCri);
		txtReset = (TextView) findViewById(R.id.txtReset);
		
		btnSetSound.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
						setSound();
			}
		});
		btnSetMusic.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
						setMusic();
			}
		});
		btnSetLang.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
						setLang();
			}
		});
		btnSetCrid.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		btnSetReset.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
					}
				});
		btnReturn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
						finish();
			}
		});
	}

	private void setSound() {
		if (soundStatus) {
			soundStatus = false;
			txtSound.setText(this.getString(R.string.txtSoundOff));
		} else {
			soundStatus = true;
			txtSound.setText(getString(R.string.txtSound));
		}
	}

	private void setMusic() {
		if (musicStatus) {
			musicStatus = false;
			txtMusic.setText(this.getString(R.string.txtMusicOff));
		} else {
			musicStatus = true;
			txtMusic.setText(this.getString(R.string.txtMusic));
		}
	}

	private void setLang() {
		if (lang.equals("cn")) {
			lang = "en";
			txtLang.setText(this.getString(R.string.txtLangEn));
		} else {
			lang = "cn";
			txtLang.setText(this.getString(R.string.txtLangCn));
		}
	}
}
