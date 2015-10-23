/**
 * 
 */
package com.example.app.block;

import org.core.store.app.StoreBaseCallBack;

import com.example.app.utils.MemoryUtils;
import com.vnp.core.common.UnZipExecute;
import com.vnp.core.common.VnpMemoryUtils;
import com.vnp.core.datastore.DataStore;
import com.vnp.core.service.RequestInfo;
import com.vnp.core.service.RestClient;
import com.vnp.core.service.RestClient.IDownloadUploadFileCallBack;

import android.content.Context;

/**
 * @author teemo
 * 
 */
public class DownloadBlockListCallBack extends StoreBaseCallBack {
	private String name, linkzip;

	/**
	 * @param context
	 */
	public DownloadBlockListCallBack(Context context) {
		super(context);
		DataStore.getInstance().init(mContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.core.store.app.StoreBaseCallBack#parseResponse()
	 */
	@Override
	public Object parseResponse() {
		return null;
	}

	@Override
	public Object execute() {
		if (isDownloaded()) {
			return null;
		}
		RestClient client = new RestClient(null);
		final RequestInfo requestInfo = new RequestInfo();
		final MemoryUtils memoryUtils = new MemoryUtils(mContext);
		requestInfo.setFileNameSave(name + ".zip");
		requestInfo.setUrl(linkzip);
		requestInfo.setFileFolderSaveFile(memoryUtils
				.getPathCacheExternalMemory());

		IDownloadUploadFileCallBack downloadUploadFileCallBack = new IDownloadUploadFileCallBack() {

			@Override
			public void sucess() {

				//
				UnZipExecute zipExecute = new UnZipExecute();
				zipExecute.execute(memoryUtils.getPathCacheExternalMemory()
						+ "/" + name + ".zip", memoryUtils.getPathContent(),
						true);
				DataStore.getInstance().save("DOWNLOAD" + name, true);
			}

			@Override
			public void start() {

			}

			@Override
			public void onProcess(long arg0, long arg1) {

			}

			@Override
			public void error(int arg0) {

			}
		};
		client.exeDownloadFile(requestInfo, downloadUploadFileCallBack);
		return null;
	}

	private boolean isDownloaded() {
		return DataStore.getInstance().get("DOWNLOAD" + name, false);
	}

	public void init(String name, String linkzip) {
		this.name = name;
		this.linkzip = linkzip;
	}
}
