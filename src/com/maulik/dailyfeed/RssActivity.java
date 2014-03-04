package com.maulik.dailyfeed;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
//import android.widget.ArrayAdapter;

public class RssActivity extends Activity implements OnItemClickListener {

	ArrayList<JSONObject> feeds,mFeeds;
	private FeedListAdapter adapter;
	String feedURL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss);
		
		Intent intent  = getIntent();
		feedURL = intent.getStringExtra("feedURL");
		feeds = new ArrayList<JSONObject>();
		RetreiveFeedTask extendedtask = new RetreiveFeedTask();
		extendedtask.execute();
		
		/*new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {

				JSONObject jsonPosts = feeds.get(position);
	    		try {
					URL blogUrl = new URL(jsonPosts.get("url").toString());
					
					Intent intent = new Intent(this, WebViewActivity.class);
		    		intent.setData(Uri.parse(blogUrl));
		    		startActivity(intent);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.d("Error Error","Error");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});*/
		
		
		
		
		
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rss, menu);
		return true;
	}
	
	private void handleFeeds() {
		
		ListView list2 = (ListView) findViewById(R.id.articleList);
		
		//ArrayAdapter<JSONObject> adapter = new ArrayAdapter<JSONObject>(this,android.R.layout.simple_list_item_1,feeds);
	    //list2.setAdapter(adapter);
		list2.setAdapter(adapter);
		list2.setOnItemClickListener(this);
		
		
		
	}
	
	class RetreiveFeedTask extends AsyncTask<Object, Void, ArrayList<JSONObject>> {

		   

	    protected ArrayList<JSONObject> doInBackground(Object... arg0) {
	        
	    	
	    	try {
	    		feeds = RSSBuilder.getRSSFeeds(feedURL);
	    		adapter = new FeedListAdapter(RssActivity.this,feeds);
	    		return feeds;
	        } catch (Exception e) {
	            
	            return null;
	        }
	        
	        
	    }

	    protected void onPostExecute(ArrayList<JSONObject> feed) {
	        mFeeds = feed;
	        handleFeeds();
	    }

		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		JSONObject posts = feeds.get(position);
		try {
			String blogUrl = posts.get("url").toString();
			
			Intent intent = new Intent(this, WebViewActivity.class);
    		intent.setData(Uri.parse(blogUrl));
    		startActivity(intent);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("Error Error","Error");
		}
		
	}

}




