/**
 * 
 */
package vnp.mr.mintmedical.fragment;

import java.util.List;

import vnp.mr.mintmedical.fragment.S4H.S4HItemShow;
import android.content.Context;
import android.widget.SectionIndexer;

import com.vnp.core.common.BaseAdapter;
import com.woozzu.android.widget.StringMatcher;

/**
 * @author teemo
 * 
 */
public class S4HAdater extends BaseAdapter implements SectionIndexer {
	private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public S4HAdater(Context context, List<Object> lData,
			CommonGenderView genderView) {
		super(context, lData, genderView);
	}

	@Override
	public int getPositionForSection(int section) {
		// If there is no item for current section, previous section will be
		// selected
		for (int i = section; i >= 0; i--) {
			for (int j = 0; j < getCount(); j++) {

				Object _item = getItem(j);
				String item = ((S4HItemShow) _item).item.name.toUpperCase();
				if (i == 0) {
					// For numeric section
					for (int k = 0; k <= 9; k++) {
						if (StringMatcher.match(String.valueOf(item.charAt(0)),
								String.valueOf(k)))
							return j;
					}
				} else {
					if (StringMatcher.match(String.valueOf(item.charAt(0)),
							String.valueOf(mSections.charAt(i))))
						return j;
				}
			}
		}
		return 0;
	}

	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}

	@Override
	public Object[] getSections() {
		String[] sections = new String[mSections.length()];
		for (int i = 0; i < mSections.length(); i++)
			sections[i] = String.valueOf(mSections.charAt(i));
		return sections;
	}
}