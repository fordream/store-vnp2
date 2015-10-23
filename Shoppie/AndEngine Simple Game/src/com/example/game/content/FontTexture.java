/**
 * 
 */
package com.example.game.content;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

/**
 * @author truongvv
 * 
 */
public class FontTexture extends AbsSprite {
	public FontTexture() {
		super("");
	}

	protected Font mFont;
	protected ChangeableText score;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.game.content.AbsSprite#onLoadResources(android.content.Context
	 * , org.anddev.andengine.engine.Engine)
	 */
	@Override
	public void onLoadResources(Context context, Engine mEngine) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mBitmapTextureAtlas = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mFont = new Font(mBitmapTextureAtlas, Typeface.create(Typeface.DEFAULT,
				Typeface.BOLD), 40, true, Color.BLACK);
		mEngine.getTextureManager().loadTexture(mBitmapTextureAtlas);
		mEngine.getFontManager().loadFont(mFont);
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
		score = new ChangeableText(0, 0, mFont, String.valueOf(0));
		score.setPosition(x - score.getWidth() - 5, 5);
		mMainScene.attachChild(score);
	}

	public void setText(String text) {
		score.setText(text);
	}

}
