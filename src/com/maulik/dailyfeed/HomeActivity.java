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

	String[] stringArray = new String[] {"TechCrunch","The Verge","Samashing Magazine","Mashable","ReadWrite"};
	
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
		Intent intent = new Intent(getApplicationContext(), RssActivity.class);
		intent.putExtra("feedName", feedName);
		startActivity(intent);
		
	}



}
