package com.example.game.content;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.content.Context;

public class AbsSprite {
	private String fileName;

	protected Sprite player;
	protected TextureRegion mPlayerTextureRegion;

	protected BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(
			512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

	public AbsSprite(String fileName) {
		super();
		this.fileName = fileName;
	}

	public float getX() {
		return player.getX();
	}

	public float getY() {
		return player.getY();
	}

	public int getWidth() {
		return mPlayerTextureRegion.getWidth();
	}

	public int getHeight() {
		return mPlayerTextureRegion.getHeight();
	}

	public TextureRegion deepCopy() {
		return mPlayerTextureRegion.deepCopy();
	}

	public void setVisible(boolean pVisible) {
		player.setVisible(pVisible);
	}

	public void onLoadResources(Context context, Engine mEngine) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mBitmapTextureAtlas, context, fileName, 0, 0);
		mEngine.getTextureManager().loadTexture(mBitmapTextureAtlas);
	}

	public void onLoadScene(int x, int y, float scale, Scene mMainScene,
			int pIndex) {
		int PlayerX = x - mPlayerTextureRegion.getWidth() / 2;
		int PlayerY = y - mPlayerTextureRegion.getHeight() / 2;
		player = new Sprite(PlayerX, PlayerY, mPlayerTextureRegion);
		player.setScale(scale);
		mMainScene.attachChild(player, pIndex);
	}
}