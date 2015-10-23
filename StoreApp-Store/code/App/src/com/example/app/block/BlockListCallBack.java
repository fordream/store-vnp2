package com.example.app.block;

import java.util.ArrayList;
import java.util.List;

import org.core.store.app.StoreBaseCallBack;
import org.core.store.app.StoreBaseItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.app.utils.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class BlockListCallBack extends StoreBaseCallBack {
	public BlockListCallBack(Context context) {
		super(context);
		url = Utils.BLOCKLIST;
	}

	@Override
	public Object execute() {
		Object object = super.execute();
		List<BlockList> blockLists = (List<BlockList>) parseResponse();
		for (BlockList blockList : blockLists) {
			DownloadBlockListCallBack downloadBlockList = new DownloadBlockListCallBack(
					mContext);
			downloadBlockList.init(blockList.name, blockList.linkzip);
			downloadBlockList.execute();
		}

		return object;
	}

	public void initFirst() {
		// TODO
	}

	@Override
	public Object parseResponse() {
		List<BlockList> blockLists = new ArrayList<BlockListCallBack.BlockList>();
		String response = getResponse();
		try {
			JSONObject jsonObject = new JSONObject(response);
			JSONArray array = jsonObject.getJSONArray("blocklist");
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				BlockList blockList = new BlockList();
				blockList.name = object.getString("name");
				blockList.linkzip = object.getString("linkzip");
				blockLists.add(blockList);
			}
		} catch (JSONException e) {
		}
		return blockLists;
	}

	class BlockList extends StoreBaseItem {
		String name, linkzip;

	}

}
