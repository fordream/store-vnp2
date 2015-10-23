/**
 * 
 */
package com.example.game.content;

import java.io.IOException;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

import android.content.Context;

/**
 * @author truongvv
 * 
 */
public class MusicTexture extends AbsSprite {
	public MusicTexture() {
		super("");
	}

	private Music backgroundMusic;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.game.content.AbsSprite#onLoadResources(android.content.Context
	 * , org.anddev.andengine.engine.Engine)
	 */
	@Override
	public void onLoadResources(Context context, Engine mEngine) {
		MusicFactory.setAssetBasePath("mfx/");

		try {
			backgroundMusic = MusicFactory.createMusicFromAsset(
					mEngine.getMusicManager(), context, "background_music.wav");
			backgroundMusic.setLooping(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.game.content.AbsSprite#onLoadScene(int, int, float,
	 * org.anddev.andengine.entity.scene.Scene, int)
	 */
	@Override
	public void onLoadScene(int x, int y, float scale, Scene mMainScene,
			int pIndex) {

	}

	public void play() {
		backgroundMusic.play();
	}

	public void resumeMusic() {
		if (!backgroundMusic.isPlaying())
			backgroundMusic.resume();
	}

	public void pauseMusic() {
		if (backgroundMusic.isPlaying())
			backgroundMusic.pause();
	}

}
