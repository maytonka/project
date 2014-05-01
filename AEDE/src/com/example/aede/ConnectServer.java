package com.example.aede;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ConnectServer extends AsyncTask<String, Integer, String> {
	private HttpPost httppost;
	private HttpClient httpclient;
	private List<NameValuePair> nameValuePairs;
	private ProgressDialog dialogConnect;
	private Context context;

	private OnResponseListener onResponseListener;

	ConnectServer(Context context, String URL,
			OnResponseListener onResponseListener) {
		this.context = context;
		this.onResponseListener = onResponseListener;

		// เธชเธฃเน�เธฒเธ�เธชเน�เธงเธ�เธ�เธฃเธฐเธ�เธญเธ�เธ—เธตเน�เธ�เธณเน€เธ�เน�เธ�เน�เธ�เธ�เธฒเธฃเน€เธ�เธทเน�เธญเธกเธ�เธฑเธ�
		// Server
		this.httpclient = new DefaultHttpClient();
		this.httppost = new HttpPost(URL);
		this.nameValuePairs = new ArrayList<NameValuePair>();

		// เธชเธฃเน�เธฒเธ� Dialog เธ•เธญเธ�เน€เธ�เธทเน�เธญเธกเธ•เน�เธญเธ�เธฑเธ�
		// Server
		// เธกเธตเธ�เธฒเธฃเธชเน�เธ� ConnectServer เน�เธซเน�เธ�เธฑเธ� Dialog
		// เน€เธ�เธทเน�เธญเน�เธ�เน�เน�เธ�เธ�เธฒเธฃเธขเธ�เน€เธฅเธดเธ�
		dialogConnect = new ProgressDialog(this.context);
		dialogConnect.setTitle(this.context.getString(R.string.app_name));
		dialogConnect.setMessage("Wait..");
	}

	// Function
	// เธชเธณเธซเธฃเธฑเธ�เน€เธ�เธดเน�เธกเธ•เธฑเธงเน�เธ�เธฃเน�เธ�เธ�เธฒเธฃเธชเน�เธ�เธ�เน�เธฒเน�เธ�เธ�
	// Post
	public void addValue(String key, String value) {
		nameValuePairs.add(new BasicNameValuePair(key, value));
	}

	// เธ�เน�เธญเธ�เธ—เธตเน�เธ�เธฐเธ—เธณ doInBackground
	// เธ�เธฐเธ—เธณเธ�เธฒเธ�เธ—เธตเน� Function เธ�เธตเน�เธ�เน�เธญเธ�
	protected void onPreExecute() {
		dialogConnect.show();
	}

	// เน€เธฃเธดเน�เธกเธ—เธณเธ�เธฒเธ�เน�เธ�เธ� Background
	protected String doInBackground(String... params) {
		InputStream is = null;
		String result = null;

		// เน€เธฃเธดเน�เธกเธ�เธฒเธฃเน€เธ�เธทเน�เธญเธกเธ•เน�เธ�เธฑเธ� Server
		try {
			// เธ—เธณเธ�เธฒเธฃเธชเน�เธ�เธ•เธฑเธงเน�เธ�เธฃเธ•เน�เธฒเธ�เน�
			// เน�เธ�เธฃเธนเธ�เน�เธ�เธ�เธ�เธญเธ� UTF-8
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
					HTTP.UTF_8));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

			// เธญเน�เธฒเธ�เธ�เธฅเธฅเธฑเธ�เธ�เน�เน�เธ�เธฃเธนเธ�เน�เธ�เธ�เธ�เธญเธ�
			// UTF-8
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			is.close();
			result = sb.toString();

			// เธ–เน�เธฒเธ�เธ“เธฐเน€เธ�เธทเน�เธญเธกเธ•เน�เธญเธ�เธฑเธ� Server
			// เธกเธตเธ�เธฑเธ�เธซเธฒ เธ�เธฐเน�เธชเธ”เธ� Log Error
		} catch (ClientProtocolException e) {
			Log.e("ConnectServer", e.toString());
		} catch (IOException e) {
			Log.e("ConnectServer", e.toString());
		}

		return result;
	}

	// เธ–เน�เธฒเธ—เธณเธ�เธฒเธ�เธ—เธตเน� doInBackground
	// เน€เธชเธฃเน�เธ�เน�เธฅเน�เธง เธ�เธฐเธกเธฒเธ—เธณเธ�เธฒเธ�เธ—เธตเน� Function
	// เธ�เธตเน�
	@SuppressWarnings("unchecked")
	protected void onPostExecute(String result) {

		// list เธ—เธตเน�เน�เธ�เน�เน€เธ�เน�เธ�เธ�เน�เธญเธกเธนเธฅ3333
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// เธ–เน�เธฒ result เน€เธ�เน�เธ� null เธ�เธทเธญ
		// เน�เธกเน�เธชเธฒเธกเธฒเธฃเธ–เน€เธ�เธทเน�เธญเธกเธ•เน�เธญเธ�เธฑเธ�
		// server เน�เธ”เน�
		// เธ–เน�เธฒเน€เธ�เธทเน�เธญเธกเธ•เน�เธ�เธฑเธ� server เน�เธ”เน�
		// เธ�เธฐเธ—เธณเธ�เธฒเธ�เธ•เน�เธญเน�เธ�เธ�เธตเน�
		if (result != null) {
			// เน€เธฃเธดเน�เธกเธ�เธฒเธฃเน�เธ�เธฅเธ� JSON
			// เน€เธ�เน�เธ�เธ�เน�เธญเธกเธนเธฅ
			try {

				JSONArray jsonArray = new JSONArray(result);

				// looping through All Contacts
				for (int i = 0; i < jsonArray.length(); i++) {
					// รกยปร…ยงยผร…ร…ร‘ยพยธรฌยทร•รจรคยดรฉร�ร’ร ยปรงยน JSON
					// Object
					JSONObject c = jsonArray.getJSONObject(i);
					HashMap<String, String> contact = new HashMap<String, String>();

					Iterator<String> keys = c.keys();
					while (keys.hasNext()) {
						String key = keys.next();
						contact.put(key, c.getString(key));
					}

					list.add(contact);
				}
				// เธ–เน�เธฒเธ�เธ“เธฐเน�เธ�เธฅเธ�เธ�เน�เธญเธกเธนเธฅ JSON
				// เธกเธตเธ�เธฑเธ�เธซเธฒเธ�เธฐเธกเธฒเธ—เธณเธ�เธฒเธ�เธชเน�เธงเธ�เธ�เธตเน�
			} catch (JSONException e) {

			}

			// เธ–เน�เธฒเน€เธ�เธทเน�เธญเธกเธ•เน�เธญเธ�เธฑเธ� server
			// เน�เธกเน�เน�เธ”เน�เธ�เธฐเธ—เธณเธ�เธฒเธ�เธ•เน�เธญเน�เธ�เธ�เธตเน�
		} else {

		}

		dialogConnect.dismiss();
		onResponseListener.onResponse(list);

	}

}