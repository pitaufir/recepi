import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class searchRecipe extends Thread{
	private String searchItems;
	private JTextArea areaResultDisplay;
	
	searchRecipe(String searchItems, JTextArea areaResultDisplay){
		this.searchItems = searchItems;
		this.areaResultDisplay = areaResultDisplay;
	}

	public void run() {
		
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("key", "0776f76a543952c8327722b585d7869d"));
			params.add(new BasicNameValuePair("q", searchItems));
			String strUrl = "https://www.food2fork.com/api/search";
						
			makeHttpRequest(strUrl, "GET", params);
			
		}
	
	private void makeHttpRequest(String url, String string, List<NameValuePair> params) {
		// TODO Auto-generated method stub
		
		InputStream is = null;
		String json = "";
		JSONObject jObj = null;
		String line = null;
		
		
		try {
			//request method is GET
			DefaultHttpClient httpClient = new DefaultHttpClient();
			String paramString = URLEncodedUtils.format(params, "utf-8");
			url += "?" + paramString;
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse;
			httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + httpResponse.getStatusLine().getStatusCode());
			is = httpEntity.getContent();
			
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuffer result = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				result.append(line);
				
			}
			
			is.close();
			json = result.toString();
			jObj = new JSONObject(json);
			
			String display = null;
			for(int i = 0; i < 30; i++) {
				if(i == 0)
				display = jObj.getJSONArray("recipes").getJSONObject(i).getString("title");
				else
				display += jObj.getJSONArray("recipes").getJSONObject(i).getString("title") + "\n";
			}
			
			areaResultDisplay.setText(display);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
