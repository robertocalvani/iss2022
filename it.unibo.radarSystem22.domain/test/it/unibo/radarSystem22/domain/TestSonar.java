package it.unibo.radarSystem22.domain;

import static org.junit.Assert.*;

import org.junit.*;

import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.mock.SonarMock;

public class TestSonar {

	@Before
	public void up() {
		System.out.println("up");
	}
	
	@After 
	public void down() {
		System.out.println("down");		
	}	
	
	@Test
	public void testSonarMock() {
		
		System.out.println("TestSonarMock");
		
		ISonar sonar = new SonarMock();
		
		assertTrue( !sonar.isActive() );
		sonar.activate();
		assertTrue( sonar.isActive() );
		sonar.deactivate();
		assertTrue( !sonar.isActive() );
	}

}
