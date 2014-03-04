package com.maulik.dailyfeed;

//import java.net.URL;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;
//import android.os.AsyncTask;

public class RSSBuilder {
	
	public static ArrayList<JSONObject> getRSSFeeds(String feedURL){
		
		String feed = feedURL;
		
		FeedHandler handler = new FeedHandler();
		ArrayList<Post> posts = handler.getPosts(feed);
		return feedData(posts);
		}

	private static ArrayList<JSONObject> feedData(ArrayList<Post> posts) {
		
		ArrayList<JSONObject> items = new ArrayList<JSONObject>();
		for(Post post : posts)
		{
			JSONObject currentPost = new JSONObject();
			
			try {
				buildPost(post, currentPost);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			items.add(currentPost);
			
		}
		
		return items;
		
	}

	private static void buildPost(Post post, JSONObject currentArticle) throws JSONException {
		String title = post.getTitle();
		String date = post.getPubDate();
		URL url = post.getUrl();
		
		
	StringBuffer s = new StringBuffer();
	s.append(title);
	currentArticle.put("text",Html.fromHtml(s.toString()));
	StringBuffer s1 = new StringBuffer();
	s1.append(date);		
	
	
	currentArticle.put("subtext",Html.fromHtml(s1.toString()));
	currentArticle.put("url",url);
	}
	
}
