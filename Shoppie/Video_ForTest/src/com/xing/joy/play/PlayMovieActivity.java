package com.xing.joy.play;

import jp.naver.KDTCUE.test.R;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.xing.joy.play.abs.AbsAnimationListener;
import com.xing.joy.play.util.CommonResize;
import com.xing.joy.play.util.IMedialayer;
import com.xing.joy.play.util.MediaUtils;

public class PlayMovieActivity extends BaseGameActivity implements
		OnClickListener {
	private Content content;
	private SurfaceHolder surfaceHolder;
	private MediaUtils mediaUtils;
	// item
	private ImageView imgContent;
	private IMedialayer iMedialayer = new IMedialayer() {
		private Thread thread;

		@Override
		public void onPrepared(MediaPlayer mp) {
			super.onPrepared(mp);
			findViewById(R.id.btnPlay).setVisibility(View.GONE);
			thread = new Thread(runnable);
			thread.start();
		}

		@Override
		public boolean onError(MediaPlayer mp, int what, int extra) {
			super.onError(mp, what, extra);
			findViewById(R.id.btnPlay).setVisibility(View.VISIBLE);
			return false;
		}

		@Override
		public void onCompletion(MediaPlayer mp) {
			super.onCompletion(mp);
			findViewById(R.id.btnPlay).setVisibility(View.VISIBLE);
			thread = null;
			imgContent.setImageBitmap(null);
		}

		@Override
		public void onPositionChangle(final int position, int duration) {
		}

		private Runnable runnable = new Runnable() {
			@Override
			public void run() {
				int TIME = 100;
				int mIndex = -2;
				int position = 0;

				while (!isFinishing() && thread != null) {
					LRC lrc = content.getLrc();
					int index = lrc.check(position);
					if (mIndex != index) {
						final Bitmap bitmap = content.getFrameBitmap(index);
						imgContent.post(new Runnable() {
							@Override
							public void run() {
								imgContent.setImageBitmap(bitmap);
							}
						});
						mIndex = index;
					}

					position += TIME;

					try {
						Thread.sleep(TIME);
					} catch (InterruptedException e) {
					}
				}
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_movie);
		findViewById(R.id.btnMenu1).setOnClickListener(this);
		findViewById(R.id.btnPlay).setOnClickListener(this);
		findViewById(R.id.btnMenu2).setOnClickListener(this);
		findViewById(R.id.btnMenu3).setOnClickListener(this);
		findViewById(R.id.btnBack).setOnClickListener(this);
		imgContent = (ImageView) findViewById(R.id.imageView1);

		final CheckBox checkBox = (CheckBox) findViewById(R.id.cbText);
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				imgContent.setVisibility(checkBox.isChecked() ? View.VISIBLE
						: View.GONE);
			}
		});

		checkBox.setChecked(true);

		content = new Content(this);

		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.SurfaceView);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		mediaUtils = new MediaUtils(this);
		mediaUtils.setPath(content.getPathVideo());
		mediaUtils.setiPlay(iMedialayer);
	}

	private Camera mCamera;

	// I
	@Override
	public EngineOptions onCreateEngineOptions() {
		mCamera = new Camera(0, 0, 480, 320);

		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
						480, 320), mCamera);
		engineOptions.getAudioOptions().setNeedsSound(true);
		engineOptions.getRenderOptions().setARGB8888(true);
		engineOptions.getRenderOptions().setDithering(true);
		return engineOptions;
	}

	// II
	@Override
	public void onCreateResources(
			OnCreateResourcesCallback onCreateResourcesCallback)
			throws Exception {

	}

	// III
	@Override
	public void onCreateScene(OnCreateSceneCallback onCreateSceneCallback)
			throws Exception {

	}

	// IV
	@Override
	public void onPopulateScene(Scene scene,
			OnPopulateSceneCallback onPopulateSceneCallback) throws Exception {

	}

	@Override
	protected void onResume() {
		super.onResume();

		int width = 60;
		CommonResize.resizeLandW480H320(findViewById(R.id.btnMenu1), width,
				width);
		CommonResize.resizeLandW480H320(findViewById(R.id.btnMenu2), width,
				width);
		CommonResize.resizeLandW480H320(findViewById(R.id.btnMenu3), width,
				width);
		CommonResize
				.resizeLandW480H320(findViewById(R.id.cbText), width, width);
		CommonResize.resizeLandW480H320(findViewById(R.id.btnBack), width,
				width);
		CommonResize.resizeLandW480H320(findViewById(R.id.btnPlay), 80, 80);
		CommonResize
				.resizeLandW480H320(findViewById(R.id.play_movie), 480, 320);

		CommonResize.resizeLandW480H320(imgContent, 440, 30);

		CommonResize.sendViewToPositionLandW480H320(imgContent, 20, 220);
	}

	@Override
	protected void onPause() {
		mediaUtils.destroy();
		super.onPause();
		finish();
	}

	@Override
	public void onClick(final View v) {
		AnimationSet animationSet = createAnimationSet(v);
		int id = v.getId();
		if (id == R.id.btnMenu1) {
			v.startAnimation(animationSet);
		} else if (id == R.id.btnMenu2) {
			v.startAnimation(animationSet);
		} else if (id == R.id.btnMenu3) {
			v.startAnimation(animationSet);
		} else if (id == R.id.btnPlay) {
			animationSet.setAnimationListener(new AbsAnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					v.setVisibility(View.GONE);
					content = new Content(PlayMovieActivity.this);
					mediaUtils.initializationMediaPlayer("", "", surfaceHolder);
				}
			});
			v.startAnimation(animationSet);
		} else if (id == R.id.btnBack) {
			animationSet.setAnimationListener(new AbsAnimationListener() {
				@Override
				public void onAnimationEnd(Animation animation) {
					finish();
				}
			});

			v.startAnimation(animationSet);
		}
	}

	private AnimationSet createAnimationSet(View v) {
		AnimationSet animSet = new AnimationSet(false);
		ScaleAnimation zoom = new ScaleAnimation(1, 2, 1, 2);
		AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.0f);
		TranslateAnimation ta = new TranslateAnimation(0, -v.getWidth() / 2, 0,
				-v.getHeight() / 2);

		animSet.addAnimation(zoom);
		animSet.addAnimation(alphaAnimation);
		animSet.addAnimation(ta);
		animSet.setDuration(500);
		return animSet;
	}

}