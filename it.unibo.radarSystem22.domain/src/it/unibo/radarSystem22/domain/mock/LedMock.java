package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedMock implements ILed{

	private boolean state;
	
	public LedMock(boolean state) {
		this.state = state;
	}
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		state = true;
		
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		state = false;
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return state;
	}
} 