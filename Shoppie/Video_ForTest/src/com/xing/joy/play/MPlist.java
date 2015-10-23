package com.xing.joy.play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;

import android.util.Log;

import xmlwise.Plist;

public class MPlist {
	private String format;
	private String realTextureFileName;
	private String New_item;
	private String size;
	private String smartupdate;
	private String textureFileName;

	private List<Frame> lFrames = new ArrayList<Frame>();

	public MPlist(String input) {
		try {
			Map<String, Object> properties = Plist.load(input);

			Map<String, Object> objectFrame = (HashMap<String, Object>) properties
					.get("frames");
			Set<String> keys = objectFrame.keySet();
			for (String key : keys) {
				Frame frame = new Frame(key,
						(HashMap<String, Object>) objectFrame.get(key));
				lFrames.add(frame);
			}

			Collections.sort(lFrames, new Comparator<Frame>() {

				@Override
				public int compare(Frame o1, Frame o2) {
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});
			Log.e("MPlist", lFrames.size() + "");

			Map<String, Object> objectMetadata = (HashMap<String, Object>) properties
					.get("metadata");
			size = getString(objectMetadata, "size");
			realTextureFileName = getString(objectMetadata,
					"realTextureFileName");
			textureFileName = getString(objectMetadata, "textureFileName");
			format = getString(objectMetadata, "format");

			New_item = getString(objectMetadata, "New item");
			smartupdate = getString(objectMetadata, "smartupdate");

			Log.e("MPlist", size + "");
		} catch (Exception e) {
			Log.e("MPlist", "Error", e);
			e.printStackTrace();
		}
	}

	public static String getString(Map<String, Object> objectMetadata, String key) {
		try {
			return objectMetadata.get(key).toString();
		} catch (Exception exception) {
			return "";
		}
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRealTextureFileName() {
		return realTextureFileName;
	}

	public void setRealTextureFileName(String realTextureFileName) {
		this.realTextureFileName = realTextureFileName;
	}

	public String getNew_item() {
		return New_item;
	}

	public void setNew_item(String new_item) {
		New_item = new_item;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSmartupdate() {
		return smartupdate;
	}

	public void setSmartupdate(String smartupdate) {
		this.smartupdate = smartupdate;
	}

	public String getTextureFileName() {
		return textureFileName;
	}

	public void setTextureFileName(String textureFileName) {
		this.textureFileName = textureFileName;
	}

	public List<Frame> getlFrames() {
		return lFrames;
	}

	public void setlFrames(List<Frame> lFrames) {
		this.lFrames = lFrames;
	}

	public Frame getFrame(int index) {
		try {
			return lFrames.get(index);
		} catch (Exception e) {
			return null;
		}

	}

	public int getWidth() {
		try {
			String size = this.size;
			return Integer.parseInt(size.substring(1, size.indexOf(",")));
		} catch (Exception e) {

		}
		return 0;
	}

}