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
	
	private Article currentArticle  = new Article();
	private ArrayList<Article> articleList = new ArrayList<Article>();
	
	private int articlesTillNow  = 0;
	
	private static final int ARTICLE_LIMIT = 20;
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
			Log.d(TAG, "Article title: " + content.toString());
			currentArticle.setTitle(content.toString());

		}
		else if (localName.equalsIgnoreCase("author"))
		{
			Log.d(TAG, "Article Author: " + content.toString());
			currentArticle.setAuthor(content.toString());

		}
		else if ((localName.equalsIgnoreCase("description")) || (localName.equalsIgnoreCase("content")))
		{
			Log.d(TAG, "Article Decription: " + content.toString());
			currentArticle.setDescription(content.toString());
		}
		else if ((localName.equalsIgnoreCase("pubDate")) || (localName.equalsIgnoreCase("published")))
		{
			Log.d(TAG, "Article published date: " + content.toString());
			currentArticle.setPubDate(content.toString());
		}
		else if (localName.equalsIgnoreCase("encoded"))
		{
			Log.d(TAG, "Article content: " + content.toString());
			currentArticle.setEncodedContent(content.toString());
		}
		else if ((localName.equalsIgnoreCase("item")) || (localName.equalsIgnoreCase("entry")))
		{
			articleList.add(currentArticle);

			currentArticle = new Article();

			// Lets check if we've hit our limit on number of articles
			articlesTillNow++;
			if (articlesTillNow >= ARTICLE_LIMIT)
			{
				throw new SAXException();
			}
		}
		else if ((localName.equalsIgnoreCase("link")) || (localName.equalsIgnoreCase("id")))
		{
			try {
				Log.d(TAG, "Article link url: " + content.toString());
				currentArticle.setUrl(new URL(content.toString()));
			} catch (MalformedURLException e) {
				Log.e("RSA Error", e.getMessage());
			}

		}




		// Check if looking for article, and if article is complete
		if (localName.equalsIgnoreCase("item")) {

			
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		content.append(new String(ch, start, length));
	}
	
	public ArrayList<Article> getArticles(String feedURL) {
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
		
		return articleList;
		
	}

}
