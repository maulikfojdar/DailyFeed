package com.maulik.dailyfeed;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends Activity implements OnItemClickListener{

	String[] stringArray = new String[] {"TechCrunch","The Verge","Times of India","Lifehacker","CNN News","Smashing Magazine","Mashable","ReadWrite","Treehouse"};
	String feedURL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < stringArray.length; ++i) {
	      list.add(stringArray[i]);
	    }
	  
	    ListView list1 = (ListView) findViewById(R.id.feedList);	
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
	    list1.setAdapter(adapter);
	
	list1.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.treehouse, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

		String feedName = ((TextView) view).getText().toString();
		Intent intent;
		if(feedName == "TechCrunch"){
			feedURL = "http://techcrunch.com/feed/";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName == "The Verge"){
			feedURL = "http://www.theverge.com/rss/index.xml";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName == "Times of India"){
			feedURL = "http://timesofindia.indiatimes.com/rssfeedsdefault.cms";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName == "Lifehacker"){
			feedURL = "http://feeds.gawker.com/lifehacker/full";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName == "CNN News"){
			feedURL = "http://rss.cnn.com/rss/cnn_topstories.rss";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName == "Smashing Magazine"){
			feedURL = "http://rss1.smashingmagazine.com/feed";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName == "Mashable"){
			feedURL = "http://feeds.mashable.com/Mashable";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName == "ReadWrite"){
			feedURL = "http://readwrite.com/main/feed/articles.xml";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else {
			intent = new Intent(getApplicationContext(), MainActivity.class);
		}
		intent.putExtra("feedURL", feedURL);
		startActivity(intent);
		
	}



}
