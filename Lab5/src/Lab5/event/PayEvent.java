
package Lab5.event;

import Lab5.generic.Event;
import Lab5.generic.State;

import Lab5.specifid.SuperMarket;

/**
 * Creates a pay event
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class PayEvent extends Event {

	/**
	 * Constructor that creates a pay event
	 * 
	 * @param sm     - SuperMarket
	 * @param i      - Customer ID
	 */
	public PayEvent(State sm, int i) {
		s = sm;
		ID = i;
		time = ((SuperMarket) s).getPayTime();
		name = "Betalning";
	}

	/**
	 * Customer leaves store and if a customer is in register queue calls its effect
	 */
	public void effect() {
		((SuperMarket) s).viewUpdate(this);
		((SuperMarket) s).customerLeftStore();

		if (!((SuperMarket) s).cashQueue.isEmpty()) {
			((SuperMarket) s).cashQueue.getFirst().effect(time);
			((SuperMarket) s).cashQueue.removeFirst();
		}
	}
}
