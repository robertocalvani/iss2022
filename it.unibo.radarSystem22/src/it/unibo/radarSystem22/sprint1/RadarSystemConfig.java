package it.unibo.radarSystem22.sprint1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import it.unibo.radarSystem22.domain.utils.ColorsOut;


public class RadarSystemConfig {
 	public static boolean tracing         = false;	
	public static boolean testing         = false;			
	public static int DLIMIT              =  15;     	
	public static boolean  RadarGuiRemote = false;
	
//Aggiunte dello SPRINT2	
 	public static String hostAddr         = "localhost";		
	public static String raspAddr         = "localhost";		
	public static int serverPort          = 8023;
 	
	
	
	public static void setTheConfiguration(  ) throws JSONException {
		setTheConfiguration("../RadarSystemConfig.json");
	}
	
	public static void setTheConfiguration( String resourceName ) throws JSONException {
		//Nella distribuzione resourceName è in una dir che include la bin  
		FileInputStream fis = null;
		Reader r = null;
		try {
			ColorsOut.out("%%% setTheConfiguration from file:" + resourceName);
			if(  fis == null ) {
 				 fis = new FileInputStream(new File(resourceName));
 				 r = new InputStreamReader(fis);
			}
	        JSONTokener tokener = new JSONTokener(r);
	        JSONObject object   = new JSONObject(tokener);
	 		
   	        tracing          = object.getBoolean("tracing");
	        testing          = object.getBoolean("testing");
	        RadarGuiRemote   = object.getBoolean("RadarGuiRemote");
	        DLIMIT           = object.getInt("DLIMIT");	
//Aggiunte dello SPRINT2	
	        serverPort		= object.getInt("serverPort");
 	        hostAddr 		= object.getString("hostAddr");
	        raspAddr 		= object.getString("raspAddr");
	        
		} catch (FileNotFoundException e) {
 			ColorsOut.outerr("setTheConfiguration ERROR " + e.getMessage() );
		}

	}	
	 
}