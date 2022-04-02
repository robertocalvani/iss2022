package it.unibo.radarSystem22.domain.concrete;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.DistanceMeasured;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IObserver;
import it.unibo.radarSystem22.domain.interfaces.ISonarObservable;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class SonarObservable extends SonarModel implements ISonarObservable{
	
	private  BufferedReader reader;
	private Process p;
	
	
	@Override
	public void activate() {
		ColorsOut.out("SonarObservable | activate ");		
		if( p == null ) { 
 	 		try {
 				p       = Runtime.getRuntime().exec("sudo ./SonarAlone");
 		        reader  = new BufferedReader( new InputStreamReader(p.getInputStream()));
 		        ColorsOut.out("SonarObservable | sonarSetUp done");
 	       	}catch( Exception e) {
 	       		ColorsOut.outerr("SonarObservable | sonarSetUp ERROR " + e.getMessage() );
 	    	}
 		}
 		super.activate();
	}

	@Override
	public void deactivate() {
		ColorsOut.out("SonarObservable | deactivate", ColorsOut.GREEN);
	    curVal = new DistanceMeasured();
		if( p != null ) {
			p.destroy();  //Block the runtime process
			p=null;
		}
		super.deactivate();		
	}

	@Override
	public IDistance getDistance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void register(IObserver obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregister(IObserver obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sonarSetUp() {
		curVal = new DistanceMeasured();
	}

	@Override
	protected void sonarProduce() {
		try {
			String data = reader.readLine();
			if( data == null ) return;
			int v = Integer.parseInt(data);
			ColorsOut.out("SonarObservable | v=" + v );
			int lastSonarVal = curVal.getVal();
			if( lastSonarVal != v && v < DomainSystemConfig.sonarDistanceMax) {	
				//Eliminiamo dati del tipo 3430 //TODO: filtri in sottosistemi a stream
  	 			updateDistance( v );	 			
			}
       }catch( Exception e) {
       		ColorsOut.outerr("SonarConcrete |  " + e.getMessage() );
       }				
	}

}
