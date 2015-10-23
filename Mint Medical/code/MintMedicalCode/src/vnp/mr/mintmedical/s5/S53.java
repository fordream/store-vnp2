package vnp.mr.mintmedical.s5;

import org.json.JSONArray;
import org.json.JSONObject;

import vnp.mr.mintmedical.R;
import vnp.mr.mintmedical.base.MBaseFragment;
import vnp.mr.mintmedical.base.MintUtils;
import vnp.mr.mintmedical.callback.S53Callback;
import vnp.mr.mintmedical.view.MintProgessDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;

import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.callback.ExeCallBackOption;
import com.vnp.core.common.CommonAndroid;

public class S53 extends MBaseFragment implements OnClickListener {
	@Override
	public int getLayout() {
		return R.layout.s53;
	}

	@Override
	public void onHiddenKeyBoard() {
		super.onHiddenKeyBoard();
		CommonAndroid.hiddenKeyBoard(getActivity());
	}

	@Override
	public void onResume() {
		super.onResume();
		resize.resizeSacle(findViewById(R.id.s5_content), MintUtils.WIDTH_ITEM,
				LayoutParams.WRAP_CONTENT);
		resize.resizeSacle(findViewById(R.id.textView1), MintUtils.WIDTH_ITEM,
				60);
		resize.resizeSacle(findViewById(R.id.s4cet1), MintUtils.WIDTH_ITEM, 100);
		resize.resizeSacle(findViewById(R.id.id1), MintUtils.WIDTH_ITEM, 100);

		resize.resizeSacle(findViewById(R.id.s4cbtn), 580 / 2, 45);
		resize.setTextsize(findViewById(R.id.s4cbtn), MintUtils.TEXTSIZE_BUTTON);

		resize.setTextsize(findViewById(R.id.textView1),
				MintUtils.TEXTSIZE_S4_HEADER);
		resize.setTextsize(findViewById(R.id.s4cet1),
				MintUtils.TEXTSIZE_ITEM1 - 1);

		findViewById(R.id.s4cbtn).setOnClickListener(this);
	}

	@Override
	public int getHeaderRes() {
		return R.string.s53header;
	}

	@Override
	public void onClick(View v) {
		onHiddenKeyBoard();
		String text = ((EditText) findViewById(R.id.s4cet1)).getText()
				.toString();

		if (text.trim().equals("")) {
			MintUtils.showDialog(getActivity(),
					"Are you sure don't want to put any more detail?",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sendRequest();
						}
					}, null);
			return;
		} else {
			sendRequest();
		}

	}

	void sendRequest() {
		S53Callback s53Callback = new S53Callback(getActivity()) {
			@Override
			public void loadSucess(String response) {
				super.loadSucess(response);
				try {
					String status = new JSONArray(response).getJSONObject(0)
							.getString("dataValue");

					if (status.equals("1")) {

						if (getOnClickListener() != null)
							getOnClickListener().onClick(null, 0);
					} else {
						MintUtils
								.toastMakeText(getActivity(), "Can not send");
					}
				} catch (Exception exception) {
					MintUtils.toastMakeText(getActivity(), "Can not send");
				}
			}

			@Override
			public void loadError(int responseCode, String errorMessage) {
				super.loadError(responseCode, errorMessage);
				MintUtils.toastMakeText(getActivity(), "Can not send");
			}
		};
		s53Callback.addParam("reason", ((EditText) findViewById(R.id.s4cet1))
				.getText().toString());
		s53Callback.addParam("sympid", ((S52) getParent()).item.id);
		s53Callback.addParam("symptoms", ((S52) getParent()).getSymptoms());

		ExeCallBack exeCallBack = new ExeCallBack();
		exeCallBack.setExeCallBackOption(new ExeCallBackOption(getActivity(),
				true, R.string.loadding,new MintProgessDialog(getActivity())));
		exeCallBack.executeAsynCallBack(s53Callback);
	}
}