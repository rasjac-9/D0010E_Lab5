
package Lab5.event;

import Lab5.generic.Event;
import Lab5.generic.State;
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
	 * Constructor that creates the stop event
	 * 
	 * @param sm       - SuperMarket
	 * @param stopTime - Event time
	 */
	public StopEvent(State sm, double stopTime) {
		
		// TODO Auto-generated constructor stub
		s = sm;
		time = stopTime;
		name = "Stop";
	}
	
	/**
	 * Changes the run boolean to false
	 */
	public void effect() {
		((SuperMarket)s).stopUpdate(this);
		s.stopSim();
	} 
}
