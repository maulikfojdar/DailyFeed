package com.maulik.dailyfeed;

import java.net.URL;

public class Post {
	
	private long postId;
	private long feedId;
	private String title;
	private String imgLink;
	private String pubDate;
	private URL url;
	private String author;
	private String encodedContent;

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getFeedId() {
		return feedId;
	}

	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
	

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setEncodedContent(String encodedContent) {
		this.encodedContent = encodedContent;
	}

	public String getEncodedContent() {
		return encodedContent;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getImgLink() {
		return imgLink;
	}

}
