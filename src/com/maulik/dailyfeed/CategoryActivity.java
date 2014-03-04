package com.maulik.dailyfeed;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryActivity extends Activity implements OnItemClickListener {

	String[] stringArray = new String[] {"Technology","Design","News","Bussiness","Sports","Web"};
	String category;
	String[] categoryBlog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < stringArray.length; ++i) {
	      list.add(stringArray[i]);
	    }
	  
	    ListView list_category = (ListView) findViewById(R.id.categoryList);	
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
	    list_category.setAdapter(adapter);
	
	list_category.setOnItemClickListener(this);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

		String category = ((TextView) view).getText().toString();
		Intent intent;
		if(category == "Technology"){
			categoryBlog = new String[] {"The Verge", "Lifehacker"};
			intent = new Intent(getApplicationContext(), HomeActivity.class);
		}
		else if(category == "Design"){
			categoryBlog = new String[] {"Cool Hunting"};
			intent = new Intent(getApplicationContext(), HomeActivity.class);
		}
		else if(category == "News"){
			categoryBlog = new String[] {"Times of India","CNN News"};
			intent = new Intent(getApplicationContext(), HomeActivity.class);
		}
		else if(category == "Bussiness"){
			categoryBlog = new String[] {"Bussiness Insider"};
			intent = new Intent(getApplicationContext(), HomeActivity.class);
		}
		else if(category == "Sports"){
			categoryBlog = new String[] {"ESPN"};
			intent = new Intent(getApplicationContext(), HomeActivity.class);
		}
		else if(category == "Web"){
			categoryBlog = new String[] {"CSS-Tricks"};
			intent = new Intent(getApplicationContext(), HomeActivity.class);
		}
		else {
			categoryBlog = new String[] {"No blog to display"};
			intent = new Intent(getApplicationContext(), HomeActivity.class);
		}
		
		intent.putExtra("categoryBlogs", categoryBlog);
		startActivity(intent);
		
	}

}
