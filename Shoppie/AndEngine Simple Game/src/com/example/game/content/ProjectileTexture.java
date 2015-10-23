package com.example.game.content;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import android.content.Context;

public class ProjectileTexture extends AbsSprite {

	public ProjectileTexture() {
		super("Projectile.png");
	}

//	@Override
//	public void onLoadResources(Context context, Engine mEngine) {
//		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
//		mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory
//				.createFromAsset(this.mBitmapTextureAtlas, context,
//						"Projectile.png", 64, 0);
//		mEngine.getTextureManager().loadTexture(mBitmapTextureAtlas);
//	}

	@Override
	public void onLoadScene(int x, int y, float scale, Scene mMainScene,
			int pIndex) {

	}

	public void shootProjectile(float pX, float pY, Scene mMainScene) {
		player = new Sprite(pX, pY, mPlayerTextureRegion.deepCopy());
		mMainScene.attachChild(player, 1);
	}

	public Sprite createProjectile(int offX, int offY, Scene mMainScene,
			Camera mCamera, Player player) {
		final Sprite projectile;

		projectile = new Sprite(player.getX(), player.getY(), deepCopy());
		mMainScene.attachChild(projectile, 1);

		int realX = (int) (mCamera.getWidth() + projectile.getWidth() / 2.0f);
		float ratio = (float) offY / (float) offX;
		int realY = (int) ((realX * ratio) + projectile.getY());

		int offRealX = (int) (realX - projectile.getX());
		int offRealY = (int) (realY - projectile.getY());
		float length = (float) Math.sqrt((offRealX * offRealX)
				+ (offRealY * offRealY));
		float velocity = 480.0f / 1.0f; // 480 pixels / 1 sec
		float realMoveDuration = length / velocity;

		MoveModifier mod = new MoveModifier(realMoveDuration,
				projectile.getX(), realX, projectile.getY(), realY);
		projectile.registerEntityModifier(mod.deepCopy());
		return projectile;
	}
}
