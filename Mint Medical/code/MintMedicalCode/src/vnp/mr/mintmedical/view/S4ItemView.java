package vnp.mr.mintmedical.view;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.S4Activity;
import vnp.mr.mintmedical.S4ManagerActivity;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.item.S4ItemForShow;
import vnp.mr.mintmedical.s4.S4ItemDataConfig;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.vnp.core.common.LogUtils;
import com.vnp.core.common.VNPResize;
import com.vnp.core.view.CustomLinearLayoutView;

public class S4ItemView extends CustomLinearLayoutView {

	public S4ItemView(Context context) {
		super(context);
		init(R.layout.s4itemview);
	}

	public void showHeadder(boolean b) {
		findViewById(R.id.s4ditemview_header).setVisibility(
				b ? View.VISIBLE : View.GONE);
	}

	@Override
	public void init(int res) {
		super.init(res);
		AvatarView avatarView = (AvatarView) findViewById(R.id.s4ditemviewavatarView1);
		avatarView.setType(AvatarView.S4D);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		VNPResize resize = VNPResize.getInstance();
		/*
		 * Header
		 */

		resize.resizeSacle(findViewById(R.id.s4firstitemview),
				MintUtils.WIDTH_ITEM, LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.s4first1itemview),
				LayoutParams.MATCH_PARENT, 180);
		resize.resizeSacle(findViewById(R.id.s4_button1itemview), 530 / 2,
				90 / 2);

		findViewById(R.id.s4_button1itemview).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						v.getContext().startActivity(
								new Intent(v.getContext(),
										S4ManagerActivity.class));
					}
				});
		resize.resizeSacle(findViewById(R.id.s4_text2itemview), 580 / 2,
				190*3 / 2);
		resize.setTextsize(findViewById(R.id.s4_text1itemview), 17);

		resize.resizeSacle(findViewById(R.id.s4ditemview_header),
				MintUtils.WIDTH_ITEM, 42);
		resize.resizeSacle(findViewById(R.id.s4ditem_1), MintUtils.WIDTH_ITEM,
				LayoutParams.WRAP_CONTENT);
		
		
		
		resize.resizeSacle(findViewById(R.id.s4itemview_content_main),
				MintUtils.WIDTH_ITEM, 130);

		resize.setTextsize(findViewById(R.id.s4ditemview_textheader),
				MintUtils.TEXTSIZE_S4_HEADER);

		resize.setTextsize(findViewById(R.id.s4ditemview_textview1),
				MintUtils.TEXTSIZE_ITEM1);
		
		resize.setTextsize(findViewById(R.id.TextView01),
				MintUtils.TEXTSIZE_ITEM1);

		resize.setTextsize(findViewById(R.id.s4ditemview_textview2),
				MintUtils.TEXTSIZE_ITEM2);

		resize.setTextsize(findViewById(R.id.s4ditemview_textview2_2),
				MintUtils.TEXTSIZE_ITEM2);
		resize.setTextsize(findViewById(R.id.TextView02),
				MintUtils.TEXTSIZE_ITEM2);

		resize.setTextsize(findViewById(R.id.s4ditemview_textview3),

		MintUtils.TEXTSIZE_ITEM2);
		resize.setTextsize(findViewById(R.id.s4ditemview_textview4),
				MintUtils.TEXTSIZE_ITEM2);

		resize.resizeSacle(findViewById(R.id.s4ditemview_img2),
				MintUtils.IMGITEM_WIDTH2, MintUtils.IMGITEM_WIDTH2);
		resize.setTextsize(findViewById(R.id.s4ditemview_textview1_1),
				MintUtils.TEXTSIZE_ITEM1);
		

		resize.resizeSacle(findViewById(R.id.s4itemview_content_main_1),
				MintUtils.WIDTH_ITEM, 50);
		resize.resizeSacle(findViewById(R.id.s4ditemview_img2_1),
				MintUtils.IMGITEM_WIDTH2, MintUtils.IMGITEM_WIDTH2);
		
		resize.setTextsize(findViewById(R.id.s4ditemview_textview1_2),
				MintUtils.TEXTSIZE_ITEM1);
	}

	@Override
	public void setGender() {
		findViewById(R.id.s4itemview_content_main_1).setVisibility(View.GONE);
		findViewById(R.id.s4itemview_content_main).setVisibility(View.VISIBLE);
		if (getData() instanceof S4ItemDataConfig) {
			S4ItemDataConfig config = (S4ItemDataConfig) getData();
			findViewById(R.id.s4ditem_1).setVisibility(View.GONE);
			findViewById(R.id.s4firstitemview).setVisibility(View.VISIBLE);

			if (config.type == S4ItemDataConfig.TYPEUPCOMMING) {
				findViewById(R.id.s4first1itemview).setVisibility(View.VISIBLE);
				findViewById(R.id.s4_text2itemview).setVisibility(View.GONE);
			} else {
				if (config.size == 0) {
					findViewById(R.id.s4first1itemview).setVisibility(View.GONE);
					findViewById(R.id.s4_text2itemview).setVisibility(View.VISIBLE);
				} else {
					findViewById(R.id.s4first1itemview).setVisibility(View.GONE);
					findViewById(R.id.s4_text2itemview).setVisibility(View.GONE);
					findViewById(R.id.s4ditem_1).setVisibility(View.VISIBLE);
					findViewById(R.id.s4firstitemview).setVisibility(View.GONE);
					findViewById(R.id.s4itemview_content_main).setVisibility(View.GONE);
					findViewById(R.id.s4itemview_content_main_1).setVisibility(View.VISIBLE);
				}
			}
		} else {
			findViewById(R.id.s4ditem_1).setVisibility(View.VISIBLE);
			findViewById(R.id.s4firstitemview).setVisibility(View.GONE);

			S4ItemForShow item = (S4ItemForShow) getData();
			String message = item.count + " upcoming appointments";
			if (!item.isUpcoming) {
				message = "Appointment History";
			}

			setText(R.id.s4ditemview_textheader, message);
			setText(R.id.s4ditemview_textview1, item.time);
			setText(R.id.s4ditemview_textview1_1, item.start_time);
			setText(R.id.s4ditemview_textview2,
					String.format("%s ,%s", item.doctor_fullname, item.level));

			setText(R.id.s4ditemview_textview2_2, item.professional);
			setText(R.id.s4ditemview_textview3, item.office_address);
			AvatarView avatarView = (AvatarView) findViewById(R.id.s4ditemviewavatarView1);
			avatarView.loadAvartar(item.avatar);

			if (!item.isUpcoming) {
				// setText(R.id.s4ditemview_textview4, item.status);
			} else
				setText(R.id.s4ditemview_textview4, "");
		}
	}

	private void setText(int s4ditemviewTextheader, String day) {
		((TextView) findViewById(s4ditemviewTextheader)).setText(day);
	}

	@Override
	public void showHeader(boolean arg0) {

	}
}