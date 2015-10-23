package vnp.mr.mintmedical;

import vnp.mr.mintmedical.base.MbaseAdialog;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.item.S11;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnp.core.common.ImageLoaderUtils;
import com.vnp.core.common.VNPResize;

public class S11Detailt extends MbaseAdialog implements OnClickListener {
	S11 item;

	public S11Detailt(Context context, OnClickListener clickListener) {
		super(context, clickListener);

	}

	public S11Detailt(S11Activity context, S11 item) {
		super(context, null);
		this.item = item;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onResume();
	}

	protected void onResume() {
		HeaderView headerView = (HeaderView) findViewById(R.id.headerView1);
		headerView.updateText(R.string.s11header);
		headerView.showButton(true, false);
		headerView.updateTextButtonRight(R.string.cancel);
		headerView.setOnClickListenerButtonBack(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		headerView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		VNPResize resize = VNPResize.getInstance();
		resize.resizeSacle(findViewById(R.id.s11_detail_content),
				MintUtils.WIDTH_ITEM,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.imageView1), MintUtils.WIDTH_ITEM,
				MintUtils.WIDTH_ITEM);

		resize.setTextsize(findViewById(R.id.s11itemview_text1),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.s11itemview_text2),
				MintUtils.TEXTSIZE_ITEM1);
		resize.setTextsize(findViewById(R.id.s11itemview_text3),
				MintUtils.TEXTSIZE_ITEM1);

		((TextView) findViewById(R.id.s11itemview_text1)).setText(item.sender);
		((TextView) findViewById(R.id.s11itemview_text2)).setText(MintUtils
				.cutDate(item.info_datetime));
		((TextView) findViewById(R.id.s11itemview_text3)).setText(item.info);

		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		ImageLoaderUtils.getInstance(getContext()).DisplayImage(
				item.getString("image"), imageView);

	}

	public int getLayout() {
		return R.layout.s11detail;
	}

	@Override
	public void onClick(View v) {
	}
}