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
		ArrayList<Article> articles = handler.getArticles(feed);
		return feedData(articles);
		}

	private static ArrayList<JSONObject> feedData(ArrayList<Article> articles) {
		
		ArrayList<JSONObject> items = new ArrayList<JSONObject>();
		for(Article article : articles)
		{
			JSONObject currentArticle = new JSONObject();
			
			try {
				buildArticle(article, currentArticle);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			items.add(currentArticle);
			
		}
		
		return items;
		
	}

	private static void buildArticle(Article article, JSONObject currentArticle) throws JSONException {
		String title = article.getTitle();
		String date = article.getPubDate();
		String author = article.getAuthor();
		URL url = article.getUrl();
		
		
	StringBuffer s = new StringBuffer();
	s.append("<B>").append(title).append("</B>");
	currentArticle.put("text",Html.fromHtml(s.toString()));
	StringBuffer s1 = new StringBuffer();
	s1.append("<SMALL>").append("<I>").append(author).append(" - ");
	s1.append(date).append("</I>").append("</SMALL>");		
	
	
	currentArticle.put("subtext",Html.fromHtml(s1.toString()));
	currentArticle.put("url",url);
	}
	
}
