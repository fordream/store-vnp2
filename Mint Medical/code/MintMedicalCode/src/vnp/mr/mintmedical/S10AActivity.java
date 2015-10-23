package vnp.mr.mintmedical;

import vnp.mr.mintmedical.base.MBaseActivity;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.config.DBConfig;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

public class S10AActivity extends MBaseActivity {
	@Override
	public int getLayout() {
		return R.layout.s10bactivity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HeaderView headerView = getView(R.id.aboutactivity_headerview);
		headerView.updateText(R.string.whatweoffer);
		headerView.showButton(true, false);
		headerView.setOnClickListenerButtonBack(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

//		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//				R.drawable.s10a_1);
//		((ImageView) findViewById(R.id.aboutactivity_img))
//				.setImageBitmap(bitmap);
	}

	@Override
	protected void onResume() {
		super.onResume();
		resize.resizeSacle(findViewById(R.id.aboutactivity_img),
				LayoutParams.MATCH_PARENT, 143);
		resize.resizeSacle(findViewById(R.id.s10maon_content), MintUtils.WIDTH_ITEM,
				LayoutParams.WRAP_CONTENT);

//		resize.resizeSacle(findViewById(R.id.s10a_textview1),
//				LayoutParams.MATCH_PARENT, 40);
//		resize.resizeSacle(findViewById(R.id.s10a_textview2),
//				LayoutParams.MATCH_PARENT, 80);
		resize.setTextsize(findViewById(R.id.s10a_textview1),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.s10a_textview2),
				MintUtils.TEXTSIZE_ITEM2);

		String whatweofffer = "Family and General Medicine"
							+"\n\nWomen's health"
							+"\n\nHealth Screening"
							+"\n\nAesthetics"
							+"\n\nMental Capacity Act"
							+"\n\nDispute Resolution Advice"
							+"\n\nTravel medications and vaccination service";
		((TextView) findViewById(R.id.s10a_textview1)).setText(whatweofffer);
		((TextView) findViewById(R.id.s10a_textview2)).setText("");
		
	}
}
