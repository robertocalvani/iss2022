package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.*;

public class SonarMock implements ISonar{
	private IDistance distance;
	private boolean active = false;
	
	public SonarMock() {
		
	}
	
	@Override
	public void activate() {
		this.active = true;
		
	}

	@Override
	public void deactivate() {
		this.active=false;
	}

	@Override
	public IDistance getDistance() {
		// TODO Auto-generated method stub
		return distance;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return this.active;
	}

}
