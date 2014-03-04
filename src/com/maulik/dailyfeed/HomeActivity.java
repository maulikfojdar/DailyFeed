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
		
		Intent intent = getIntent();
		stringArray = intent.getStringArrayExtra("categoryBlogs");
		
		
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
		if(feedName.equalsIgnoreCase("techcrunch")){
			feedURL = "http://techcrunch.com/feed/";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("The Verge")){
			feedURL = "http://www.theverge.com/rss/index.xml";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("Cool Hunting")){
			feedURL = "http://www.coolhunting.com/index.xml";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("Times of India")){
			feedURL = "http://timesofindia.indiatimes.com/rssfeedsdefault.cms";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("Lifehacker")){
			feedURL = "http://feeds.gawker.com/lifehacker/full";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("CNN News")){
			feedURL = "http://rss.cnn.com/rss/cnn_topstories.rss";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("Smashing Magazine")){
			feedURL = "http://rss1.smashingmagazine.com/feed";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("Mashable")){
			feedURL = "http://feeds.mashable.com/Mashable";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("ReadWrite")){
			feedURL = "http://readwrite.com/main/feed/articles.xml";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("Bussiness Insider")){
			feedURL = "http://feeds2.feedburner.com/businessinsider";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("ESPN")){
			feedURL = "http://sports-ak.espn.go.com/espn/rss/news";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else if(feedName.equalsIgnoreCase("CSS-Tricks")){
			feedURL = "http://feeds.feedburner.com/CssTricks";
			intent = new Intent(getApplicationContext(), RssActivity.class);
		}
		else {
			intent = new Intent(getApplicationContext(), JsonActivity.class);
		}
		intent.putExtra("feedURL", feedURL);
		startActivity(intent);
		
	}



}
