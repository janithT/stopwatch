package com.m.stopwatch;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.m.stopwatch.R;

public class Time60 extends Activity{
	ArrayList<String> list =new ArrayList<String>();
    ArrayAdapter<String> adapter;
    
    
	
	Button Bstart, Breset, lap;
	TextView time;
	long starttime = 0L; 
	long timeInMilliseconds = 0L; 
	long timeSwapBuff = 0L; 
	long updatedtime = 0L; 
	int t = 1; 
	int secs = 0; 
	int mins = 0; 
	int milliseconds = 0; 
	Handler handler = new Handler(); 
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time60);
		Bstart = (Button) findViewById(R.id.start);
		Breset = (Button) findViewById(R.id.reset);
		lap = (Button) findViewById(R.id.lap);
		time = (TextView) findViewById(R.id.timer);
		
		ListView myList = (ListView)findViewById(R.id.list);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        myList.setAdapter(adapter);
      //  final Chronometer myChronometer = (Chronometer)findViewById(R.id.chronometer);
       // final ImageButton buttonLap = (ImageButton)findViewById(R.id.lap);

		
		Bstart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  
				// TODO Auto-generated method stub
				if (t == 1) { 
					Bstart.setText("Pause"); 
					starttime = SystemClock.uptimeMillis(); 
					handler.postDelayed(updateTimer, 0); 
					t = 0; 
					} else { 
					Bstart.setText("Start"); 
					time.setTextColor(Color.BLUE); 
					timeSwapBuff += timeInMilliseconds; 
					handler.removeCallbacks(updateTimer); 
					t = 1; 
					}} 
		});
		
		Breset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				starttime = 0L; 
				timeInMilliseconds = 0L; 
				timeSwapBuff = 0L; 
				updatedtime = 0L; 
				t = 1; 
				secs = 0; 
				mins = 0; 
				milliseconds = 0; 
				Bstart.setText("Start"); 
				handler.removeCallbacks(updateTimer); 
				time.setText("00:00:00"); 
			}
		});
		
		lap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String s = "" + ("" + mins + ":" + String.format("%02d", secs) + ":" 
						+ String.format("%03d", milliseconds) );
				
				 
				list.add(s);
				
	            
	            adapter.notifyDataSetChanged();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public Runnable updateTimer = new Runnable() { 
		public void run() { 
		timeInMilliseconds = SystemClock.uptimeMillis() - starttime; 
		updatedtime = timeSwapBuff + timeInMilliseconds; 
		secs = (int) (updatedtime / 1000); 
		mins = secs / 100; 
		secs = secs % 100; 
		milliseconds = (int) (updatedtime % 1000); 
		time.setText("" + mins + ":" + String.format("%02d", secs) + ":" 
		+ String.format("%03d", milliseconds)); 
		time.setTextColor(Color.RED); 
		handler.postDelayed(this, 0); 
		//runOnUiThread(updateTimer);
		}}; 


}
