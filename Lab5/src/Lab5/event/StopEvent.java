
package Lab5.event;

import Lab5.generic.Event;
import Lab5.generic.State;
import Lab5.generic.EventQueue;
import Lab5.specifid.SuperMarket;

/**
 * Runs a simulation
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class StopEvent extends Event {

	/**
	 * Constructor
	 * 
	 * @param eventQ   - EventQueue
	 * @param sm       - SuperMarket
	 * @param stopTime - Event time
	 */
	public StopEvent(EventQueue eventQ, State sm, double stopTime) {
		
		// TODO Auto-generated constructor stub
		eq = eventQ;
		s = sm;
		time = stopTime;
		name = "Stop";
	}
	
	/**
	 * Changes the run boolean to false
	 */
	public void effect() {
//		System.out.println("******************************************************************************");
		
		((SuperMarket)s).stopUpdate(this);
		s.stopSim();
	} 
}
