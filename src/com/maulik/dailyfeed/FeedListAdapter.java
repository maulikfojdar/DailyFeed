package com.maulik.dailyfeed;

//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
//import android.widget.ImageView;
import android.widget.TextView;

public class FeedListAdapter extends ArrayAdapter<JSONObject> {


	public FeedListAdapter(Activity activity, ArrayList<JSONObject> imageAndTexts) {
		super(activity, 0, imageAndTexts);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Activity activity = (Activity) getContext();
		LayoutInflater inflater = activity.getLayoutInflater();

		// Inflate the views from XML
		View rowView = inflater.inflate(R.layout.feed_layout, null);
		JSONObject jsonImageText = getItem(position);

		//////////////////////////////////////////////////////////////////////////////////////////////////////
		//The next section we update at runtime the text - as provided by the JSON from our REST call
		////////////////////////////////////////////////////////////////////////////////////////////////////
		TextView textView1 = (TextView) rowView.findViewById(R.id.feed_text);
		TextView textView2 = (TextView) rowView.findViewById(R.id.feed_subtext);
		
		//ImageView imageView = (ImageView) rowView.findViewById(R.id.feed_image);


        try {
        	
        	
        	Spanned text = (Spanned)jsonImageText.get("text");
        	textView1.setText(text);
        	Spanned subtext = (Spanned)jsonImageText.get("subtext");
        	textView2.setText(subtext);
        	
        	

        }
        catch (JSONException e) {
        	textView1.setText("JSON Exception");
        }

		return rowView;

	} 

}