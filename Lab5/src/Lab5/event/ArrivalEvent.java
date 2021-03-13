
package Lab5.event;

import Lab5.generic.Event;
import Lab5.generic.State;
import Lab5.generic.EventQueue;
import Lab5.specifid.SuperMarket;

/**
 * Creates a arrival event
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class ArrivalEvent extends Event {

	/**
	 * Constructor
	 * 
	 * @param eventQ - EventQueue
	 * @param sm     - SuperMarket
	 * @param i      - Customer ID
	 */
	public ArrivalEvent(EventQueue eventQ, State sm, int i) {
		eq = eventQ;
		s = sm;
		ID = i;
		time = ((SuperMarket) sm).getArrivalTime();
		name = "Ankomst";
	}

	/**
	 * Checks if customer can enter store and i creates a pick event if thats true
	 * else calls lost customer function. In all cases calls new arrival event.
	 */
	public void effect() {
		((SuperMarket) s).viewUpdate(this);

		if (((SuperMarket) s).getOpenForBis() && ((SuperMarket) s).addCustomer()) {
			eq.addEvent(new PickEvent(eq, (SuperMarket) s, ID));
			eq.addEvent(new ArrivalEvent(eq, (SuperMarket) s, ID + 1));

		} else if (((SuperMarket) s).getOpenForBis() && !((SuperMarket) s).addCustomer()) {
			((SuperMarket) s).addLostCustomer();
			eq.addEvent(new ArrivalEvent(eq, (SuperMarket) s, ID + 1));
		}
	}
}
