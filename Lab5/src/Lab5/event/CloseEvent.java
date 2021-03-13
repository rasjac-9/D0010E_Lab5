
package Lab5.event;

import Lab5.generic.Event;
import Lab5.generic.State;
import Lab5.specifid.SuperMarket;

/**
 * Creates a closing event
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class CloseEvent extends Event {

	/**
	 * Constructor that creates a closing event
	 * 
	 * @param sm        - SuperMarket
	 * @param closeTime - Event time
	 */
	public CloseEvent(State sm, double closeTime) {
		s = sm;
		time = closeTime;
		name = "Stänger";
	}

	/**
	 * Closes the store when called
	 */
	public void effect() {
		((SuperMarket) s).viewUpdate(this);
		((SuperMarket) s).closeStore();
	}
}
