package com.example.game.content;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import android.content.Context;

public class Win extends AbsSprite {

	public Win() {
		super("win.png");
	}

//	@Override
//	public void onLoadResources(Context context, Engine mEngine) {
//		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
//		mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory
//				.createFromAsset(mBitmapTextureAtlas, context, "win.png", 0, 0);
//		mEngine.getTextureManager().loadTexture(mBitmapTextureAtlas);
//	}
//
//	@Override
//	public void onLoadScene(int x, int y, float scale, Scene mMainScene,
//			int pIndex) {
//		int PlayerX = x - mPlayerTextureRegion.getWidth() / 2;
//		int PlayerY = y - mPlayerTextureRegion.getHeight() / 2;
//		player = new Sprite(PlayerX, PlayerY, mPlayerTextureRegion);
//		// player.setScale(scale);
//		mMainScene.attachChild(player);
//	}

}
