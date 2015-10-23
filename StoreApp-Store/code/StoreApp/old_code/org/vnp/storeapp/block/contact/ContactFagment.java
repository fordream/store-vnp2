package org.vnp.storeapp.block.contact;

import org.vnp.storeapp.R;
import org.vnp.storeapp.base.BaseFragment;
import org.vnp.storeapp.callback.StatusPackageCallBack;
import org.vnp.storeapp.model.ItemStatusPackage;
import org.vnp.storeapp.service.StoreAppService.BlockType;
import org.vnp.storeapp.utils.StoreAppUtils;

import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.vnp.core.callback.ExeCallBack;
import com.vnp.core.common.ImageLoader;
import com.vnp.core.common.VNPPartternChecked;

public class ContactFagment extends BaseFragment implements OnClickListener {

	public ContactFagment() {
		super();
		setType(BlockType.BLOCK_CONTACT);
		setCanRunExecute(false);
	}

	@Override
	public int getResourceLayout() {
		return R.layout.contactfragment;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onInitCreateView(View v) {
		super.onInitCreateView(v);
		StoreAppUtils.resizeW960(v.findViewById(R.id.icon_company_bor), 480, 480);
		StoreAppUtils.resizeW960(v.findViewById(R.id.imageView_about), 370, 370);
		StoreAppUtils.setTextSize(v.findViewById(R.id.textView1), 30);

		// show submit button
		StatusPackageCallBack callBack = new StatusPackageCallBack(getActivity());
		ItemStatusPackage status = (ItemStatusPackage) callBack.parseResponse();

		if (status.getStatusCode() == ItemStatusPackage.STATUS_PROFESSIONAL) {
			v.findViewById(R.id.contact_for_me).setVisibility(View.VISIBLE);
		} else {
			v.findViewById(R.id.contact_for_me).setVisibility(View.GONE);
		}

		v.findViewById(R.id.button1).setOnClickListener(this);

		Contact contact = (Contact) new ContactCallBack(getActivity()).parseResponse();
		if (contact != null) {
			setImageUrl((ImageView) v.findViewById(R.id.imageView_about), contact.img);

			setText(v, R.id.textView1, contact.text);
		}
	}

	@Override
	public String getTitleBar() {
		return null;
	}

	@Override
	public void onClick(View v) {
		String name = getStrText(R.id.editText2);
		String email = getStrText(R.id.editText1);
		String message = getStrText(R.id.editText3);
		String telephone = getStrText(R.id.editText4);

		if (!VNPPartternChecked.isEmail(email)) {
			makeText(R.string.dont_email);
			return;
		}

		if (name.trim().equals("")) {
			makeText(R.string.inpput_your_name);
			return;
		}

		if (telephone.trim().equals("")) {
			makeText(R.string.inpput_your_telephone);
			return;
		}

		if (message.trim().equals("")) {
			makeText(R.string.inpput_your_message);
			return;
		}

		final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getString(R.string.loadding));

		ExeCallBack exeCallBack = new ExeCallBack();
		exeCallBack.executeAsynCallBack(new ContactForMeCallBack(getActivity(), name, email, message, telephone) {
			@Override
			public void onCallBack(Object arg0) {
				dialog.dismiss();
				if (restClient.getResponseCode() == 200) {
					makeText("suscess");
				} else {
					makeText(restClient.getErrorMessage());
				}

				clearText(R.id.editText1);
				clearText(R.id.editText2);
				clearText(R.id.editText3);
				clearText(R.id.editText4);
			}
		});
	}

}