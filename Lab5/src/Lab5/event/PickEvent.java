
package Lab5.event;

import Lab5.generic.Event;
import Lab5.generic.State;
import Lab5.generic.EventQueue;

import Lab5.specifid.SuperMarket;

/**
 * Creates a pick event
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class PickEvent extends Event {

	/**
	 * Constructor creates a pick event
	 * 
	 * @param eventQ - EventQueue
	 * @param sm     - SuperMarket
	 * @param i      - Customer ID
	 */
	public PickEvent(EventQueue eventQ, State sm, int i) {
		eq = eventQ;
		s = sm;
		ID = i;
		time = ((SuperMarket) s).getPickTime();
		name = "Plock";
	}

	/**
	 * Try´s to add to the cash registers if success create pay event else add to
	 * register queue
	 */
	public void effect() {
		((SuperMarket) s).viewUpdate(this);

		try {
			((SuperMarket) s).addToCashReg();
			eq.addEvent(new PayEvent((SuperMarket) s, ID));
		} catch (ArithmeticException e) {
			((SuperMarket) s).cashQueue.addToFIFO(this);
		}
	}

	/**
	 * Special case when customer is in the register queue already
	 */
	public void effect(double t) {
		time = t;

		try {
			((SuperMarket) s).addToCashReg();
			eq.addEvent(new PayEvent((SuperMarket) s, ID));
		} catch (ArithmeticException e) {
			((SuperMarket) s).cashQueue.addToFIFO(this);
		}
	}
}
