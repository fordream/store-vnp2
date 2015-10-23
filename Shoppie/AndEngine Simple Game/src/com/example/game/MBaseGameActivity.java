/**
 * 
 */
package com.example.game;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.Display;

/**
 * @author truongvv
 * 
 */
public abstract class MBaseGameActivity extends BaseGameActivity implements
		IOnSceneTouchListener {

	public enum STATUSGAME {
		END, RUN, PAUSE, STOP, WIN, GAMEOVER, BEGIN
	}

	protected Camera mCamera;
	protected Scene mMainScene;
	private String score = "0";

	private STATUSGAME statusGame = STATUSGAME.BEGIN;

	public STATUSGAME getStatusGame() {
		return statusGame;
	}

	public void setStatusGame(STATUSGAME statusGame) {
		this.statusGame = statusGame;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.anddev.andengine.ui.IGameInterface#onLoadEngine()
	 */
	@Override
	public Engine onLoadEngine() {

		// getting the device's screen size
		final Display display = getWindowManager().getDefaultDisplay();
		int cameraWidth = display.getWidth();
		int cameraHeight = display.getHeight();

		cameraWidth = 480 * 2;
		cameraHeight = 320 * 2;
		mCamera = new Camera(0, 0, cameraWidth, cameraHeight);

		// Engine with varius options
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(cameraWidth, cameraHeight), mCamera)
				.setNeedsSound(true).setNeedsMusic(true));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.anddev.andengine.ui.IGameInterface#onLoadScene()
	 */
	@Override
	public Scene onLoadScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		mMainScene = new Scene();
		onLoadScene(mMainScene);

		// ----------------------------------------------------
		// (2.1) time runner
		// ----------------------------------------------------
		mMainScene.setOnSceneTouchListener(this);
		mMainScene.registerUpdateHandler(detect);
		mEngine.registerUpdateHandler(spriteTimerHandler);
		return mMainScene;
	}

	public abstract void onLoadScene(Scene mMainScene);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.anddev.andengine.ui.IGameInterface#onLoadResources()
	 */
	@Override
	public abstract void onLoadResources();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.anddev.andengine.ui.IGameInterface#onLoadComplete()
	 */
	@Override
	public void onLoadComplete() {

	}

	@Override
	public abstract boolean onSceneTouchEvent(Scene arg0, TouchEvent arg1);

	private IUpdateHandler detect = new IUpdateHandler() {
		@Override
		public void reset() {
		}

		@Override
		public void onUpdate(float pSecondsElapsed) {
			onUpdateHandler(pSecondsElapsed);
		}
	};

	private TimerHandler spriteTimerHandler = new TimerHandler(1f, true,
			new ITimerCallback() {
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					onTimePassedTimerHandler(pTimerHandler);
				}
			});

	// ----------------------------------------------------
	// (2.1) update khi co su thay doi giao dien
	// ----------------------------------------------------
	public abstract void onUpdateHandler(float pSecondsElapsed);

	// ----------------------------------------------------
	// (2.1) cu sau 1 khoang thoi gian lai lam 1 viec
	// ----------------------------------------------------
	public abstract void onTimePassedTimerHandler(TimerHandler pTimerHandler);

}
