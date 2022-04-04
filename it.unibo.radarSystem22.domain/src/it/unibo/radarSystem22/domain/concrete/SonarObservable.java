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
	private  BufferedReader reader ;
	private Process p ;
	private DistanceMeasured d;
	private boolean stopped = true;
	
	public SonarObservable() {
		DistanceMeasured d = new DistanceMeasured();
	}
	
	@Override
	public void activate() {
		ColorsOut.out("SonarConcrete | activate ");
 		if( p == null ) { 
 	 		try {
 				p       = Runtime.getRuntime().exec("sudo ./SonarAlone");
 		        reader  = new BufferedReader( new InputStreamReader(p.getInputStream()));
 		        ColorsOut.out("SonarConcrete | sonarSetUp done");
 	       	}catch( Exception e) {
 	       		ColorsOut.outerr("SonarConcrete | sonarSetUp ERROR " + e.getMessage() );
 	    	}
 		}
 		super.activate();
	}
	
	@Override
	public void deactivate() {
		ColorsOut.out("SonarObservable | deactivate", ColorsOut.BgYellow );
		if( p != null ) {
			p.destroy();  //Block the runtime process
			p=null;
		}
		super.deactivate();	
	}
	
	@Override
	public IDistance getDistance() {
		return d;
	}
	
	@Override
	public boolean isActive() {
		return ! stopped;
	}
	
	@Override
	public void register(IObserver obs) {
		d.addObserver(obs);
		
	}
	
	@Override
	public void unregister(IObserver obs) {
		d.deleteObserver(obs);
	}
	
	@Override
	public void sonarProduce() {
		IDistance dis;
		try {
			String data = reader.readLine();
			if( data == null ) return;
			int v = Integer.parseInt(data);
			ColorsOut.out("SonarConcrete | v=" + v );
			int lastSonarVal = d.getVal();
			if( lastSonarVal != v && v < DomainSystemConfig.sonarDistanceMax) {	
				//Eliminiamo dati del tipo 3430 //TODO: filtri in sottosistemi a stream
				dis = new Distance(v);
				d.setVal(dis);
				//updateDistance( v );	 			
			}
       }catch( Exception e) {
       		ColorsOut.outerr("SonarConcrete |  " + e.getMessage() );
       }		
	}

	@Override
	protected void sonarSetUp() {
		// TODO Auto-generated method stub
		
	}
	
}