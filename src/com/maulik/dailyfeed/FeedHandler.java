package com.maulik.dailyfeed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class FeedHandler extends DefaultHandler{
	
	private Post currentPost  = new Post();
	private ArrayList<Post> postList = new ArrayList<Post>();
	
	private int postsTillNow  = 0;
	
	private static final int POST_LIMIT = 20;
	private static final String TAG = "FeedHandler";
	
	StringBuffer content = new StringBuffer();
	

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		
		content = new StringBuffer();
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		if (localName.equalsIgnoreCase("title"))
		{
			Log.d(TAG, "Post title: " + content.toString());
			currentPost.setTitle(content.toString());

		}
		else if ((localName.equalsIgnoreCase("pubDate")) || (localName.equalsIgnoreCase("published")))
		{
			Log.d(TAG, "Post published date: " + content.toString());
			currentPost.setPubDate(content.toString());
		}
		else if ((localName.equalsIgnoreCase("item")) || (localName.equalsIgnoreCase("entry")))
		{
			postList.add(currentPost);

			currentPost = new Post();

			// Lets check if we've hit our limit on number of posts
			postsTillNow++;
			if (postsTillNow >= POST_LIMIT)
			{
				throw new SAXException();
			}
		}
		else if ((localName.equalsIgnoreCase("link")) || (localName.equalsIgnoreCase("id")))
		{
			try {
				Log.d(TAG, "Post link url: " + content.toString());
				currentPost.setUrl(new URL(content.toString()));
			} catch (MalformedURLException e) {
				Log.e("RSA Error", e.getMessage());
			}

		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		content.append(new String(ch, start, length));
	}
	
	public ArrayList<Post> getPosts(String feedURL) {
		URL url;
		
		try {
			SAXParserFactory saxFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxFactory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			
			url = new URL(feedURL);
			
			xmlReader.setContentHandler(this);
			xmlReader.parse(new InputSource(url.openStream()));
			
		} catch (ParserConfigurationException e) {
			Log.e("Exception:",e.toString());
		} catch (SAXException e) {
			Log.e("Exception:",e.toString());
		} catch (IOException e) {
			Log.e("Exception:",e.toString());
		}
		
		return postList;
		
	}

}
