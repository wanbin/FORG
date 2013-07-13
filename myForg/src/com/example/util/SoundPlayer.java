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
 * ����������
 * @author Wenson
 * @version 2013-07-06
 */
public class SoundPlayer{

	private static MediaPlayer music;
	private static SoundPool soundPool;
	
	private static boolean musicSt = true; //���ֿ���
	private static boolean soundSt = true; //��Ч����
	private static Context context;
	
	private static Map<Integer,Integer> soundMap; //��Ч��Դid����ع������Դid��ӳ���ϵ��
	
	@SuppressLint("UseSparseArrays")
	public static void init(Context pContext) {
		context = pContext;
		//��ʼ����Ч�Ķ��� SoundPool
		soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC,100);
		soundMap  = new HashMap<Integer, Integer>();
	}
	
	/**
	 * ������Ч
	 * @param resId ��Ч��Դid
	 */
	public static void playSound(int resId)
	{
		if(soundSt)
		{
			Integer soundId = soundMap.get(resId);
			if(soundId != null)
				soundPool.play(soundId, 1, 1, 1, 0, 1);
		}
	}
	
	
	/**
	 * ��ǰ������Ч
	 * @param resId
	 */
	public static void pushSound(int resId)
	{
		if(!soundMap.containsKey(resId))
			soundMap.put(resId, soundPool.load(context, resId, 1));
	}
	
	/**
	 * ��������
	 * @param resId ��ԴID
	 * @param loop  �Ƿ�ѭ��
	 */
	public static void playMusic(int resId, boolean loop) {
		if (musicSt) {
			music = MediaPlayer.create(context, resId);
			music.start();
			music.setLooping(loop);
			//������������¼�
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
	 * ��ͣ����
	 */
	public static void pauseMusic()
	{
		if(music.isPlaying())
			music.pause();
	}
	
	
	/**
	 * ������ֿ���״̬
	 * @return boolean
	 */
	public static boolean getMusicSt() {
		return musicSt;
	}
	
	/**
	 * �������ֿ���״̬
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
	 * �����Ч����״̬
	 * @return
	 */
	public static boolean getSoundSt() {
		return soundSt;
	}

	/**
	 * ������Ч����
	 * @param soundSt
	 */
	public static void setSoundSt(boolean soundSt) {
		SoundPlayer.soundSt = soundSt;
	}
	
	/**
	 * �������Ƶ�����
	 */
	public static void clap()
	{
		playSound(R.raw.clap);
	}
}