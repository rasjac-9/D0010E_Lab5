
package Lab5.event;

import Lab5.generic.Event;
import Lab5.generic.EventQueue;

import Lab5.specifid.SuperMarket;

/**
 * Creates a start event
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class StartEvent extends Event {

	private double closeTime, stopTime;

	/**
	 * Opens up the store and make sure new customers arrive
	 * 
	 * @param seed    - seed brings randomness
	 * @param reg     - cash register limit
	 * @param m       - max amount of customer in store
	 * 
	 * @param l       - arrival difference signifier
	 * @param pMin    - minimum time to pick items
	 * @param pMax    - maximum time to pick items
	 * @param kMin    - minimum time to pay for items
	 * @param kMax    - maximum time to pay for items
	 * 
	 * @param b       - boolean if print to console shall happen or not
	 * 
	 * @param endTime - when the store closes
	 * @param stopT   - when the simulations stops
	 * @param eventQ  - the event queue
	 */
	public StartEvent(int seed, int reg, int m, double l, double pMin, double pMax, double kMin, double kMax, boolean b,
			double endTime, double stopT, EventQueue eventQ) {

		s = new SuperMarket(seed, reg, m, l, pMin, pMax, kMin, kMax, b);
		eq = eventQ;

		closeTime = endTime;
		stopTime = stopT;
		name = "Start";
	}

	/**
	 * Creates arrival event, close event, stop event and adds to queue
	 */
	public void effect() {
		((SuperMarket) s).viewUpdate(this);
		eq.addEvent(new ArrivalEvent(eq, s, 0));
		eq.addEvent(new CloseEvent(s, closeTime));
		eq.addEvent(new StopEvent(s, stopTime));
	}
}
