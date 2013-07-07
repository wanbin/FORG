package com.example.util;

import com.example.myforg.*;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.util.Log;

/**
 * 声音控制类
 * @author Wenson
 * @version 2013-07-06
 */
public class SoundPlayer{

	private static MediaPlayer music;
	private static SoundPool soundPool;
	
	private static boolean musicSt = true; //音乐开关
	private static boolean soundSt = true; //音效开关
	private static Context context;
	
	private static Map<Integer,Integer> soundMap; //音效资源id与加载过后的音源id的映射关系表
	
	@SuppressLint("UseSparseArrays")
	public static void init(Context pContext) {
		context = pContext;
		
		//初始化音效的东西 SoundPool
		soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC,100);
		soundMap  = new HashMap<Integer, Integer>();
		
	}
	
	/**
	 * 播放音效
	 * @param resId 音效资源id
	 */
	public static void playSound(int resId)
	{
		if(soundSt)
		{
			if(!soundMap.containsKey(resId))
				soundMap.put(resId, soundPool.load(context, resId, 1));
			Integer soundId = soundMap.get(resId);
			if(soundId != null)
				soundPool.play(soundId, 1, 1, 1, 0, 1);
		}
	}
	
	/**
	 * 播放音乐
	 * @param resId 资源ID
	 * @param loop  是否循环
	 */
	public static void playMusic(int resId, boolean loop) {
		if (musicSt) {
			music = MediaPlayer.create(context, resId);
			music.start();
			music.setLooping(loop);

			//监听播放完成事件
//			music.setOnCompletionListener(new OnCompletionListener() {
//				@Override
//				public void onCompletion(MediaPlayer mp) {
//					mp.seekTo(0);
//					Log.v("Loop", "Completed");
//					music.seekTo(3000);
//					music.start();
//				}
//			});
		}
	}
	
	/**
	 * 暂停音乐
	 */
	public static void pauseMusic()
	{
		if(music.isPlaying())
			music.pause();
	}
	
	
	/**
	 * 获得音乐开关状态
	 * @return boolean
	 */
	public static boolean getMusicSt() {
		return musicSt;
	}
	
	/**
	 * 设置音乐开关状态
	 * @param musicSt
	 */
	public static void setMusicSt(boolean musicSt) {
		SoundPlayer.musicSt = musicSt;
		if(musicSt)
			music.start();
		else
			music.stop();
	}

	/**
	 * 获得音效开关状态
	 * @return
	 */
	public static boolean getSoundSt() {
		return soundSt;
	}

	/**
	 * 设置音效开关
	 * @param soundSt
	 */
	public static void setSoundSt(boolean soundSt) {
		SoundPlayer.soundSt = soundSt;
	}
	
	/**
	 * 发出鼓掌的声音
	 */
	public static void clap()
	{
		playSound(R.raw.clap);
	}
}