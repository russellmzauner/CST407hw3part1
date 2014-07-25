package edu.oit.cst407.persistencetest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ToggleButton;


/** 
 * GenieActivity is an exercise in persistent data state
 * as specified in CST407 Homework 3 Problem 1
 * 
 * Assignment specifies three toggle buttons that retain
 * their state across launches of the application, and 
 * that if a button has been toggled that it is then 
 * disabled.  
 *
 * Since this is a very simple demonstration, SharedPreferences
 * methods were used instead of a database or writing out to 
 * larger storage.
 * 
 * @author Russell Zauner
 * @version 0.1 120724
 *
 */

public class GenieActivity extends Activity {
	
	/*
	 * The fields below outline three ToggleButton objects,
	 * three boolean values that represent persistent ToggleButton
	 * state, the name of the XML file SharedPreferences generates,
	 * keys to store/retrieve the state for each button, and a
	 * SharedPreference object to maintain values in.
	 */
	
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
		
		// 
		// Initialize ToggleButton objects by assigning them 
		// their layout objects.
		//
		tgButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
		tgButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
		tgButton3 = (ToggleButton) findViewById(R.id.toggleButton3);
		
		//
		// Assign the SharedPreferences data object for this application.  
		// I assume if the file doesn't already exist that Android ignores 
		// this and any other code related to it until time to create it,
		// and that if it does exist simply associates the object with the 
		// existing file.
		//
		sharedPreferences = getSharedPreferences(GENIE_PREFERENCES, Context.MODE_PRIVATE);
		
		// 
		// If there is a SharedPreferences file, then restore the objects' 
		// last stored states, and if actuated disable the button.
		//
		tgButton1.setChecked(sharedPreferences.getBoolean(STATE_BUTTON_1, tgButton1State));
		
		if (sharedPreferences.getBoolean(STATE_BUTTON_1, tgButton1State)){
			tgButton1.setEnabled(false);
		}
		tgButton2.setChecked(sharedPreferences.getBoolean(STATE_BUTTON_2, tgButton2State));
		if (sharedPreferences.getBoolean(STATE_BUTTON_2, tgButton2State)){
			tgButton2.setEnabled(false);
		}
		tgButton3.setChecked(sharedPreferences.getBoolean(STATE_BUTTON_3, tgButton3State));
		if (sharedPreferences.getBoolean(STATE_BUTTON_3, tgButton3State)){
			tgButton3.setEnabled(false);
		}
	}
	
			
	@Override
	protected void onStop() {
		super.onStop();
		
		//
		// Read the current on/off state of each ToggleButton
		//
		tgButton1State = tgButton1.isChecked();
		tgButton2State = tgButton2.isChecked();
		tgButton3State = tgButton3.isChecked();
		
		// 
		// Assign the SharedPreferences data object for this application.  If
		// it doesn't exist, Android will create it.
		//
		sharedPreferences = getSharedPreferences(GENIE_PREFERENCES, Context.MODE_PRIVATE);
		
		//
		// Invoke the Edit method of a SharedPreferences Editor Object.  I assume
		// this is what in C++ would be called a temporary object, a copy in memory used
		// to edit the data then overwrite the SharedPreferences file on persistent storage.
		//
		SharedPreferences.Editor editor = sharedPreferences.edit();
		
		// 
		// Store fields in the SharedPreferences temporary object.
		//		
		editor.putBoolean(STATE_BUTTON_1, tgButton1State);
		editor.putBoolean(STATE_BUTTON_2, tgButton2State);
		editor.putBoolean(STATE_BUTTON_3, tgButton3State);
		
		//
		// Overwrites the file in storage with the object in memory.
		//
		editor.commit();
	}
	
}
