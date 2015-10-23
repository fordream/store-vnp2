package org.vnp.storeapp.blocklist;

import org.json.JSONException;
import org.json.JSONObject;
import org.vnp.storeapp.BlockType;
import org.vnp.storeapp.base.Item;

import android.util.Log;

public class Block extends Item {
	public Block(BlockType type) {
		super(type);
	}

	public String getBackgroundImg() {
		return backgroundImg;
	}
	private String backgroundImg;
	private String opacity;
	private String header;
	private String sub_header;
	private String img;
	private String sub_icon;
	private String backgoundColor;

	public String getOpacity() {
		return opacity;
	}

	public void setBackgoundColor(String backgoundColor) {
		this.backgoundColor = backgoundColor;
	}

	public String getBackgoundColor() {
		return backgoundColor;
	}

	public String getSub_icon() {
		return sub_icon;
	}

	public String getHeader() {
		return header;
	}

	public String getSub_header() {
		return sub_header;
	}

	public String getImg() {
		return img;
	}

	public Block(JSONObject object) {
		super(BlockType.BASE);
		try {
			String typeStr = object.get("type").toString();
			setType(BlockType.NONE);

			if ("OURCARS".equals(typeStr)) {
				setType(BlockType.OURCARS);
			} else if ("EVENTS".equals(typeStr)) {
				setType(BlockType.EVENTS);
			} else if ("LATESTNEWS".equals(typeStr)) {
				setType(BlockType.LATESTNEWS);
			} else if ("VISITUS".equals(typeStr)) {
				setType(BlockType.VISITUS);
			} else if ("GALLERY".equals(typeStr)) {
				setType(BlockType.GALLERY);
			} else if ("MAIN".equals(typeStr)) {
				setType(BlockType.MAIN);
			} else if ("ABOUT".equals(typeStr)) {
				setType(BlockType.ABOUT);
			} else if ("CONTACT".equals(typeStr)) {
				setType(BlockType.CONTACT);
			} else if ("DELETE".equals(typeStr)) {
				setType(BlockType.DELETE);
			} else if ("PORTFOLIO".equals(typeStr)) {
				setType(BlockType.PORTFOLIO);
			} else if ("PEN".equals(typeStr)) {
				setType(BlockType.PEN);
			}

			if (getType() != BlockType.NONE) {
				backgoundColor = object.getString("backgound-color");
				img = object.getString("image");
				sub_icon = object.getString("icon-logo");
				if (getType() == BlockType.MAIN) {
					header = object.getString("header-description");
					sub_header = object.getString("short-description");
				} else {
					header = object.getString("title-block");
					opacity = object.getString("opacity");
					backgroundImg= object.getString("backgound-img");
				}
			}

		} catch (JSONException e) {
			Log.e("ABC", "e", e);
		}
	}

}