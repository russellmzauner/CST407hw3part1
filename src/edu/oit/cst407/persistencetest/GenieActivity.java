package edu.oit.cst407.persistencetest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ToggleButton;

public class GenieActivity extends Activity {
	
	ToggleButton tgButton1;
	ToggleButton tgButton2;
	ToggleButton tgButton3;
	
	boolean tgButton1State;
	boolean tgButton2State;
	boolean tgButton3State;
	
	public static final String GENIE_PREFERENCES = "GPrefs";
	public static final String STATE_BUTTON_1 = "keyButton1";
	public static final String STATE_BUTTON_2 = "keyButton2";
	public static final String STATE_BUTTON_3 = "keyButton3";
	
	SharedPreferences sharedPreferences;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_genie);
		
		tgButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
		tgButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
		tgButton3 = (ToggleButton) findViewById(R.id.toggleButton3);
		
		sharedPreferences = getSharedPreferences(GENIE_PREFERENCES, Context.MODE_PRIVATE);
		
		sharedPreferences.getBoolean(STATE_BUTTON_1, tgButton1State);
		sharedPreferences.getBoolean(STATE_BUTTON_2, tgButton2State);
		sharedPreferences.getBoolean(STATE_BUTTON_3, tgButton3State);
		
		tgButton1.setChecked(tgButton1State);
		tgButton2.setChecked(tgButton2State);
		tgButton3.setChecked(tgButton3State);
		
		Toast.makeText(GenieActivity.this,"ON CREATE", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		Toast.makeText(GenieActivity.this,"ON RESTART", Toast.LENGTH_SHORT).show();
	}	
		
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		Toast.makeText(GenieActivity.this,"ON START", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onResume(){
		super.onStart();
		
		Toast.makeText(GenieActivity.this,"ON RESUME", Toast.LENGTH_SHORT).show();
	}

	 
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		Toast.makeText(GenieActivity.this,"ON PAUSE", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		tgButton1State = tgButton1.isChecked();
		tgButton2State = tgButton2.isChecked();
		tgButton3State = tgButton3.isChecked();
		
		sharedPreferences = getSharedPreferences(GENIE_PREFERENCES, Context.MODE_PRIVATE);
		
		SharedPreferences.Editor editor = sharedPreferences.edit();
		
		editor.putBoolean(STATE_BUTTON_1, tgButton1State);
		editor.putBoolean(STATE_BUTTON_2, tgButton2State);
		editor.putBoolean(STATE_BUTTON_3, tgButton3State);
		
		editor.commit();
		
		Toast.makeText(GenieActivity.this,"ON STOP", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Toast.makeText(GenieActivity.this,"ON DESTROY", Toast.LENGTH_SHORT).show();
	}
	
}
