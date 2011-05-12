package tests;

import org.junit.Before;
import org.junit.Test;

import sourcepac.ScheduleMain;

public class ScheduleMainTest {
	private ScheduleMain my_scheduler;
	
	@Before
	public void startUp() {
		my_scheduler = new ScheduleMain() ;
	}
	
	@Test
	public void loadFileTest() {
		
	}
	
}
