package it.unibo.radarSystem22.domain.concrete;

import java.io.IOException;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.models.LedModel;

public class LedConcrete extends LedModel implements ILed{
private Runtime rt  = Runtime.getRuntime();
 	
	@Override
	protected void ledActivate(boolean val) {
		try {
			if( val ) {
				System.out.println("LedConcrete | START ON SCRIPT ");
				rt.exec( "python ledPythonOn.py" );
			}
			else {
				System.out.println("LedConcrete | START OFFs SCRIPT ");
				rt.exec( "python ledPythonOff.py" );
			}
		} catch (IOException e) {
			System.out.println("LedConcrete | ERROR " +  e.getMessage());
		}
	}
}
	