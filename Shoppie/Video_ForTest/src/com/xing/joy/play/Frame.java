package com.xing.joy.play;

import java.util.HashMap;

public class Frame {
	private String name;
	private String frame;
	private String offset;
	private String rotated;
	private String sourceColorRect;
	private String sourceSize;

	public Frame(String key, HashMap<String, Object> hashMap) {
		name = key;
		frame = MPlist.getString(hashMap, "frame");
		offset = MPlist.getString(hashMap, "offset");
		rotated = MPlist.getString(hashMap, "rotated");
		sourceColorRect = MPlist.getString(hashMap, "sourceColorRect");
		sourceSize = MPlist.getString(hashMap, "sourceSize");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getRotated() {
		return rotated;
	}

	public void setRotated(String rotated) {
		this.rotated = rotated;
	}

	public String getSourceColorRect() {
		return sourceColorRect;
	}

	public void setSourceColorRect(String sourceColorRect) {
		this.sourceColorRect = sourceColorRect;
	}

	public String getSourceSize() {
		return sourceSize;
	}

	public void setSourceSize(String sourceSize) {
		this.sourceSize = sourceSize;
	}

	public int startFrameX() {
		try {
			return Integer.parseInt(frame.substring(2, frame.indexOf(",")));
		} catch (Exception e) {
			return 0;
		}
	}

	public int startFrameY() {
		try {
			return Integer.parseInt(frame.substring(frame.indexOf(",") + 1,
					frame.indexOf("},")));
		} catch (Exception e) {
			return 0;
		}
	}

	public int startWidthFrame() {
		try {
			String temp = frame.substring(frame.indexOf("},{") + 3);
			return Integer.parseInt(temp.substring(0, temp.indexOf(",")));
		} catch (Exception e) {
			return 0;
		}
	}

	public int startHeightFrame() {
		try {
			String temp = frame.substring(frame.indexOf("},{") + 3);
			return Integer.parseInt(temp.substring(temp.indexOf(",") + 1,
					temp.indexOf("}")));
		} catch (Exception e) {
			return 0;
		}
	}

	public boolean isRontate() {
		if (rotated == null || "".equals(rotated)) {
			return false;
		}
		return !("false".equalsIgnoreCase(rotated));
	}

}