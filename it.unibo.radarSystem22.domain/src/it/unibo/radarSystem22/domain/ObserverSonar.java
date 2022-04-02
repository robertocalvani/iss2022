package it.unibo.radarSystem22.domain;

import java.util.Observable;

import it.unibo.radarSystem22.domain.interfaces.IObserver;

@SuppressWarnings("deprecation")
public class ObserverSonar implements IObserver{
	private String distanza;
	private int id;
	
	@Override
	public void update(String value) {
		System.out.println("Observer" + id + "distanza: " + value);
		this.distanza = value;		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}

}
