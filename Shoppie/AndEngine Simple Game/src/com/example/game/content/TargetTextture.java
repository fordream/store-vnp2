package com.example.game.content;

import java.util.Random;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.modifier.MoveXModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import android.content.Context;

public class TargetTextture extends AbsSprite {

	public TargetTextture() {
		super("Target.png");
	}

	// @Override
	// public void onLoadResources(Context context, Engine mEngine) {
	// BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
	// BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(512,
	// 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	//
	// mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory
	// .createFromAsset(mBitmapTextureAtlas, context, "Target.png",
	// 128, 0);
	// mEngine.getTextureManager().loadTexture(mBitmapTextureAtlas);
	// }

	@Override
	public void onLoadScene(int x, int y, float scale, Scene mMainScene,
			int pIndex) {

	}

	public Sprite ramdom(Scene mMainScene, Camera mCamera) {
		Random rand = new Random();
		int x = (int) mCamera.getWidth() + getWidth();
		int minY = getHeight();
		int maxY = (int) (mCamera.getHeight() - getHeight());
		int rangeY = maxY - minY;
		int y = rand.nextInt(rangeY) + minY;
		Sprite target = new Sprite(x, y, deepCopy());
		mMainScene.attachChild(target);

		int minDuration = 2;
		int maxDuration = 4;
		int rangeDuration = maxDuration - minDuration;
		int actualDuration = rand.nextInt(rangeDuration) + minDuration;

		MoveXModifier mod = new MoveXModifier(actualDuration, target.getX(),
				-target.getWidth());
		target.registerEntityModifier(mod.deepCopy());
		return target;
	}
}