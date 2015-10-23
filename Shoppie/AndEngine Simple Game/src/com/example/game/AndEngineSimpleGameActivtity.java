/**
 * 
 */
package com.example.game;

import java.util.Iterator;
import java.util.LinkedList;

import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.CameraScene;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

import com.example.game.content.Fail;
import com.example.game.content.FontTexture;
import com.example.game.content.MusicTexture;
import com.example.game.content.Pause;
import com.example.game.content.Player;
import com.example.game.content.ProjectileTexture;
import com.example.game.content.SoundTexture;
import com.example.game.content.TargetTextture;
import com.example.game.content.Win;

public class AndEngineSimpleGameActivtity extends MBaseGameActivity {
	private Player player = new Player();
	private FontTexture fontTexture = new FontTexture();
	private Fail fail = new Fail();
	private Win win = new Win();
	private TargetTextture targetTextture = new TargetTextture();
	private ProjectileTexture projectileTexture = new ProjectileTexture();

	private SoundTexture soundTexture = new SoundTexture();
	private MusicTexture musicTexture = new MusicTexture();

	// ----------------------------------------------------
	// (1)
	// Load from resoure
	// ----------------------------------------------------
	@Override
	public void onLoadResources() {
		player.onLoadResources(this, mEngine);
		fontTexture.onLoadResources(this, mEngine);
		fail.onLoadResources(this, mEngine);
		win.onLoadResources(this, mEngine);
		projectileTexture.onLoadResources(this, mEngine);

		soundTexture.onLoadResources(this, mEngine);
		musicTexture.onLoadResources(this, mEngine);

		targetTextture.onLoadResources(this, mEngine);
	}

	// ----------------------------------------------------
	// (2)
	// Create main screen
	// ----------------------------------------------------
	@Override
	public void onLoadScene(final Scene mMainScene) {
		mMainScene
				.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));

		int cameraWidth = (int) mCamera.getWidth();
		int cameraHeight = (int) mCamera.getHeight();
		
		player.onLoadScene(0, (int) (cameraHeight / 2), 2, mMainScene, 0);
		fontTexture.onLoadScene(100, 100, 1, mMainScene, 1);
		musicTexture.play();

		TargetsToBeAdded = new LinkedList<Sprite>();

		fail.onLoadScene((int) (cameraWidth / 2), (int) (cameraHeight / 2), 1,
				mMainScene, 0);
		fail.setVisible(false);

		win.onLoadScene((int) (cameraWidth / 2), (int) (cameraHeight / 2), 1,
				mMainScene, 0);
		win.setVisible(false);
	}

	// ----------------------------------------------------
	// (run) touch screen
	// ----------------------------------------------------
	@Override
	public boolean onSceneTouchEvent(Scene arg0, TouchEvent arg1) {
		if (arg1.getAction() == TouchEvent.ACTION_DOWN) {
			int offX = (int) (arg1.getX() - player.getX());
			int offY = (int) (arg1.getY() - player.getY());
			if (offX > 0) {
				// ----------------------------------------------------
				// create protected
				// ----------------------------------------------------
				Sprite projectile = projectileTexture.createProjectile(offX,
						offY, mMainScene, mCamera, player);
				projectilesToBeAdded.add(projectile);
			}

			soundTexture.play();

			return true;
		}
		return false;
	}

	// ----------------------------------------------------
	// (run) sau 1f lai add them 1 dong chi quai vat
	// ------------------------------------------------
	@Override
	public void onTimePassedTimerHandler(TimerHandler pTimerHandler) {
		Sprite target = targetTextture.ramdom(mMainScene, mCamera);
		TargetsToBeAdded.add(target);
	}

	private LinkedList<Sprite> TargetsToBeAdded;
	private LinkedList<Sprite> projectileLL = new LinkedList<Sprite>();
	private LinkedList<Sprite> targetLL = new LinkedList<Sprite>();
	private LinkedList<Sprite> projectilesToBeAdded = new LinkedList<Sprite>();

	// ----------------------------------------------------
	// (run) check xem quai van co an dan khong
	// ----------------------------------------------------
	@Override
	public void onUpdateHandler(float pSecondsElapsed) {
		Iterator<Sprite> targets = targetLL.iterator();
		Sprite _target;
		boolean hit = false;

		while (targets.hasNext()) {
			_target = targets.next();
			// ----------------------------------------------------
			// quai vat pha dupc thanh
			// ----------------------------------------------------
			if (_target.getX() <= -_target.getWidth()) {
				removeSprite(_target, targets);
				if (mEngine.isRunning()) {
					fail.setVisible(true);
					mEngine.stop();
				}
				break;
			}

			Iterator<Sprite> projectiles = projectileLL.iterator();

			while (projectiles.hasNext()) {
				// ----------------------------------------------------
				// Dan ban vuot man hinh
				// ----------------------------------------------------
				Sprite _p = projectiles.next();
				if (_p.getX() >= mCamera.getWidth()
						|| _p.getY() >= mCamera.getHeight() + _p.getHeight()
						|| _p.getY() <= -_p.getHeight()) {

					removeSprite(_p, projectiles);
					continue;
				}

				// ----------------------------------------------------
				// Quai vat trung dan
				// ----------------------------------------------------
				if (_target.collidesWith(_p)) {
					removeSprite(_p, projectiles);
					hit = true;
					break;
				}
			}

			// ----------------------------------------------------
			// remove quai vat va tang diem cho nguoi choi
			// ----------------------------------------------------
			if (hit) {
				removeSprite(_target, targets);
				hit = false;
				setScore("" + (Integer.parseInt(getScore()) + 1));
				fontTexture.setText(getScore());
			}
		}

		if (Integer.parseInt(getScore()) >= 5) {
			if (mEngine.isRunning()) {
				win.setVisible(true);
				mEngine.stop();
			}
		}

		projectileLL.addAll(projectilesToBeAdded);
		projectilesToBeAdded.clear();

		targetLL.addAll(TargetsToBeAdded);
		TargetsToBeAdded.clear();
	}

	// ----------------------------------------------------
	// (run) touch screen
	// ----------------------------------------------------
	private void removeSprite(final Sprite _sprite, Iterator<Sprite> it) {
		mMainScene.detachChild(_sprite);
		it.remove();
	}
}