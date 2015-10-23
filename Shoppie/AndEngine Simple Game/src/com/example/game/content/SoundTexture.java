/**
 * 
 */
package com.example.game.content;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

import android.content.Context;

/**
 * @author truongvv
 * 
 */
public class SoundTexture extends AbsSprite {
	public SoundTexture() {
		super("");
	}

	private Sound shootingSound;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.game.content.AbsSprite#onLoadResources(android.content.Context
	 * , org.anddev.andengine.engine.Engine)
	 */
	@Override
	public void onLoadResources(Context context, Engine mEngine) {
		SoundFactory.setAssetBasePath("mfx/");
		try {
			shootingSound = SoundFactory.createSoundFromAsset(
					mEngine.getSoundManager(), context, "pew_pew_lei.wav");
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
		if (shootingSound != null)
			shootingSound.play();
	}
}
