package vnp.mr.mintmedical.base;

import vnp.mr.mintmedical.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.viewpagerindicator.db.DBUserLogin;
import com.vnp.core.base.BaseResizeConfige;
import com.vnp.core.common.VNPResize;
import com.vnp.core.datastore.DataStore;

public abstract class MBaseActivity extends FragmentActivity {
	protected VNPResize resize = VNPResize.getInstance();
	public DBUserLogin dbUserLogin;
	private BaseResizeConfige baseResizeConfige;

	public void changeFragemtn(int r_id_content_frame, Fragment rFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(r_id_content_frame, rFragment);
		ft.commit();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbUserLogin = new DBUserLogin(this);
		resize.init(this, 320, 480, null, null);

		DataStore.getInstance().init(this);
		baseResizeConfige = new BaseResizeConfige(this);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(getLayout());
	}

	@Override
	protected void onResume() {
		super.onResume();
		baseResizeConfige.onResumeOrOnAttachedToWindow();
	}

	public void onCreateAddResize(View v, int width, int height, int textsize) {
		baseResizeConfige.onCreateAdd(v, width, height, textsize);
	}

	public <T extends View> T getView(int res) {
		@SuppressWarnings("unchecked")
		T view = (T) findViewById(res);
		return view;
	}

	public abstract int getLayout();

	public void setupListView(View view) {
		if (view != null && view instanceof ListView) {
			ListView listView = (ListView) view;
			listView.setDivider(null);
			listView.setSelector(R.drawable.listview_selected);
		}
	}
}