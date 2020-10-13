package com.m.stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import android.R.integer;
import android.widget.BaseAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


import com.m.stopwatch.R;

public class MainActivity extends Activity {
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> listX = new ArrayList<String>();
	ArrayList<Integer> list2 = new ArrayList<Integer>();

	// ArrayList<String> reverse = list;

	ArrayAdapter<String> adapter;
	private String[] globalValuve = null;
	private String arrayst1;
	// list.reverse();

	// int ArrayRes = (int) list[];

	Button Bstart, Breset, lap, avg, data;
	TextView time, Text, acc, avgc;
	long starttime = 0L;
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedtime = 0L;
	int t = 1;
	int i = 1;
	int u = 0;
	int secs = 0;
	int mins = 0;
	int hour = 0;
	int milliseconds = 0;
	int tracker = 0;
	int tracker_c = 0;
	int tempH=0;
	int tempM = 0;
	int tempS =0;
	int tempMi = 0;
	//vari
	int Millisec = 0;
	int secondss = 0;
	int minitsss = 0;
	int hurss = 0;
	//up
	boolean b;
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testl);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		Bstart = (Button) findViewById(R.id.start);
		Breset = (Button) findViewById(R.id.reset);
		lap = (Button) findViewById(R.id.lap);
		avg = (Button) findViewById(R.id.avg);
		data = (Button) findViewById(R.id.data);
		time = (TextView) findViewById(R.id.timer);
		acc = (TextView) findViewById(R.id.acc);
		avgc = (TextView) findViewById(R.id.avgc);
		//Text = (TextView) findViewById(R.id.textView2);
		
		ListView myList = (ListView)findViewById(R.id.list);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        myList.setAdapter(adapter);
        Collections.reverse(list);
      //  final Chronometer myChronometer = (Chronometer)findViewById(R.id.chronometer);
       // final ImageButton buttonLap = (ImageButton)findViewById(R.id.lap);

		
		Bstart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  
				// TODO Auto-generated method stub
				if (t == 1 ) { 
					b= true;
					Bstart.setText("Pause"); 
					starttime = SystemClock.uptimeMillis(); 
					handler.postDelayed(updateTimer, 0); 
					t = 0; 
					} else { 
					Bstart.setText("Start"); 
					time.setTextColor(Color.RED); 
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
				b = false;
				secs = 0; 
				mins = 0; 
				hour =0;
				milliseconds = 0; 
				Bstart.setText("Start"); 
				handler.removeCallbacks(updateTimer); 
				time.setText("00:00:00:00"); 
				tracker =0;
				tracker_c =0;
				
				//clear
				adapter.clear();
				listX.clear();
				list.clear();
				u = 0;
				acc.setText("00:00:00:00");
				avgc.setText("00:00:00:00");
				
				tempH =0;
				tempM =0;
				tempMi =0;
				tempS= 0;
				
				 Millisec = 0;
				 secondss = 0;
				 minitsss = 0;
				 hurss = 0;
				
				/*tracker =0;
				tracker_c =0;*/
							
			}
		});
		
		lap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				int H = 0, M = 0, Se = 0 , MSe = 0 ;
				// remove t=0 to when get lap done pausing
				if(b == true && t == 0){
					String s =// (i++)+ " . " +
							( (++ u) + ". " + hour + ":" + mins + ":" + String.format("%02d", secs) + ":" 
									+ String.format("%03d", milliseconds) );
							String s2 = (hour + ":" + mins + ":" + String.format("%02d", secs) + ":" 
									+ String.format("%03d", milliseconds));
							
							Log.d("s2 :", s2);
							
											
							listX.addAll(Arrays.asList(s2.split(":")));
							
							if(tracker == 0) {
								
								list.add(s);
						        adapter.notifyDataSetChanged();
						        
						        tracker ++;
							}else {
								
								// get the gap
								try {
									int c1=0,c2=0,c3=0,c4 =0;
									int amountHour =0; 
									int amountMin = 0;
									int amountSec =0;
									int amountMil =0;
									
								for (int j=tracker_c*4;j<=(tracker_c*4)+7;j++) {
															
									Log.d("List X val :",listX.get(j));
									
									if(j%4 == 0){
										
										if(c1 ==0){
											amountHour = Integer.parseInt(listX.get(j));
											c1++;
										}
										else{
											 tempH = Integer.parseInt(listX.get(j));
											//H = tempH - amountHour;
										}
											
									}else if(j%4 == 1){
										
										if(c2 ==0){
											amountMin = Integer.parseInt(listX.get(j));
											c2 ++;
										}
										else {
											 tempM = Integer.parseInt(listX.get(j));
											//M = tempM - amountMin;
										}
										
									}else if(j%4 == 2){
										
										if(c3 ==0){
											amountSec = Integer.parseInt(listX.get(j));
											c3 ++;
										}
										else{
											 tempS = Integer.parseInt(listX.get(j));
											//Se = tempS - amountSec;
										}
																	
									}else if(j%4 == 3){
										
										if(c4 ==0){
											amountMil = Integer.parseInt(listX.get(j));
											c4 ++;
										}else{
											 tempMi =Integer.parseInt(listX.get(j));
											//MSe = tempMi - amountMil;
										}
										
										
										}else{
										
									}
									
								}
								
									tracker_c++;
									Log.d("hour", "amount "+tempH);
									Log.d("Min", "amount "+tempM);
									Log.d("Sec", "amount "+tempS);
									Log.d("MiSec", "amount "+tempMi);
									
									// add to the arry H/M/Se/Mi
									
									int arH[] = {amountHour*6000000,tempH*6000000};
									int arM[] = {amountMin*100000,tempM*100000};
									int arS[] = {amountSec*1000,tempS*1000};
									int arMi[] = {amountMil, tempMi};
									
									int totalTime = arH[0] + arM[0] + arS[0] + arMi[0];
									int tempTime =  arH[1] + arM[1] + arS[1] + arMi[1];
									
									int last = tempTime - totalTime ;
									Log.d("", "" + last);
									if(last == 0){
										
									}else{
										int Milli = last % 1000;
										int seco = last / 1000;
										int minits = seco/ 100;
										seco = seco %100;
										minits = minits % 60;
										int hurs = minits / 60;
										
										String pqrs =( (u) + ". "+ hurs + ":" + minits + ":" + String.format("%02d", seco) + ":" 
												+ String.format("%03d", Milli) );
										
										 list.add(pqrs);
										 adapter.notifyDataSetChanged();
										
									}
									
									 
									 
									
									//Log.d("ars", ":" + arS[0]+ ""+ arS[1]);
									// concatanate those valves here with : and make a string
									//list.add
									//notifichanges
								}catch (Exception e) {
									
									Log.d("eee",""+ e);
								}
									
							}
				}else{
					
				}
				 
					     	       
			}
		});
		
		avg.setOnClickListener(new OnClickListener() {
			 			
			@Override
			public void onClick(View arg0) {
				
				/*Log.d("hour", "amount "+H);
				Log.d("Min", "amount "+M);
				Log.d("Sec", "amount "+Se);
				Log.d("MiSec", "amount "+MSe);*/
				try{
								
				if(b == true ){// remove this when get avg in 1 list item
					if(list.size()>1){
						
					
					 String snew = (tempH + ":" + tempM + ":" + String.format("%02d", tempS) + ":" 
							+ String.format("%03d", tempMi));
					Log.d("snew", "bvjhbs" +snew);
							        
					 acc.setText(tempH + ":" + String.format("%02d", tempM) + ":" + String.format("%02d", tempS) + ":" 
							+ String.format("%03d", tempMi));
					
					int cal = tempH*6000000 + tempM*100000 + tempS*1000 + tempMi ;
					int avg = cal/list.size();
					
					 Millisec = avg % 1000;
					 secondss = avg / 1000;
					 minitsss = secondss/ 100;
					secondss = secondss %100;
					minitsss = minitsss % 60;
					 hurss = minitsss / 60;
					
				avgc.setText(hurss + ":" + String.format("%02d", minitsss) + ":" + String.format("%02d", secondss) + ":" 
							+ String.format("%03d", Millisec));
					}else{
						//acc.setText("00:00:00:00");
						//avgc.setText("00:00:00:00");
					}
				}
				
				
			}catch (Exception eff){
				acc.setText("00:00:00:00");
				avgc.setText("00:00:00:00");
		    }
			}
			
		});
		
		data.setOnClickListener(new OnClickListener() {
			 
			
			@Override
			public void onClick(View arg0) {
				//getAverageValue();
				
				
				
				if (b== true){
				Intent intent = new Intent(Intent.ACTION_SEND); 
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, "iestopwatch@gmail.com");  
                intent.putExtra(Intent.EXTRA_SUBJECT, "Stopwatch_data"); 
                           
                StringBuilder sb = new StringBuilder(); 
                for (String s : list) {
                    sb.append(s);
                    sb.append("\n");
                                                  
                }
                sb.append("\n");
                String snew1 = ("Cumulative Value: " + tempH + ":" + tempM + ":" + String.format("%02d", tempS) + ":" 
						+ String.format("%03d", tempMi));
                	sb.append(snew1);
                	sb.append("\n");
                	sb.append("\n");
                	
                String snew2 = ("Average: " + hurss + ":" + String.format("%02d", minitsss) + ":" + String.format("%02d", secondss) + ":" 
						+ String.format("%03d", Millisec));	
                	sb.append(snew2);
                	sb.append("\n");
                	
                	
                intent.putExtra(Intent.EXTRA_TEXT, sb.toString());
                startActivity(Intent.createChooser(intent, "Choose the app to send:"));
				}
				// TODO Auto-generated method stub
/*				adapter.clear();
				listX.clear();
				list.clear();
				u = 0;
				acc.setText("00:00:00:00");
				avgc.setText("00:00:00:00");
				
				tracker =0;
				tracker_c =0;
*/							
	           
			}

			
		});
			
					
			
	}

	public Runnable updateTimer = new Runnable() {
		public void run() {
			timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
			updatedtime = timeSwapBuff + timeInMilliseconds;
			secs = (int) (updatedtime / 600);

			mins = secs / 100;
			hour = mins / 60;
			secs = secs % 100;
			mins = mins % 60;
			hour = hour % 60;
			milliseconds = (int) (updatedtime % 1000);
			time.setText(String.format("%01d", hour) + ":"
					+ String.format("%02d", mins) + ":"
					+ String.format("%02d", secs) + ":"
					+ String.format("%03d", milliseconds));
			time.setTextColor(Color.GREEN);
			handler.postDelayed(this, 0);
			// runOnUiThread(updateTimer);
		}
	};

}