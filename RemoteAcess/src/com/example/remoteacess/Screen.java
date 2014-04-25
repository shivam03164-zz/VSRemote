package com.example.remoteacess;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.example.network.Background;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Screen extends Activity {
    ImageView image;
    boolean flag = true;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.livescreen);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	    image=(ImageView)findViewById(R.id.imageView1);

			AsyncWs task = new AsyncWs();
			task.execute();
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class AsyncWs extends AsyncTask<Void, Void, Void>
	{
		private  Bitmap  yourSelectedImage;
		 
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
					Socket socket = new Socket("192.168.1.3", 4420);
					OutputStream outToServer = socket.getOutputStream();
	                DataOutputStream out = new DataOutputStream(outToServer);
	                InputStream inputStream = socket.getInputStream();
	                while(flag)
	                {
	                	int size = (inputStream.read() & 0xFF) << 24 | (inputStream.read() & 0xFF) << 16 | (inputStream.read() & 0xFF) << 8 | (inputStream.read() & 0xFF);
	                	byte[] data = new byte[size];
	                	int total = 0;
	                	int flag = 0;
	                	while (total != size)
	                	{
	                		total += inputStream.read(data, total, size - total);
	                	}
	
	                	yourSelectedImage = BitmapFactory.decodeStream(new ByteArrayInputStream(data));
	                	publishProgress(null);
	                	
	                }
	                socket.close();
	                
				} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;
		
		}
		

		
		@Override
		protected void onProgressUpdate(Void... params)
		{
			@SuppressWarnings("deprecation")
			BitmapDrawable drawable = new BitmapDrawable( yourSelectedImage);
              image.setImageDrawable(drawable);
		}
	}
	
	@Override
	public void onBackPressed()
	{
		flag = false;
		finish();
	}

	
}
