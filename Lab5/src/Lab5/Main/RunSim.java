
package Lab5.Main;

import Lab5.generic.EventQueue;
import Lab5.generic.Event;
import Lab5.generic.State;
import Lab5.event.StartEvent;

/**
 * Runs a simulation
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class RunSim implements K {

	protected State s;
	protected EventQueue eq;
	protected Event e;

	public RunSim() {
	}

	/**
	 * runs the simulation and returns the end state
	 * 
	 * @return - State
	 */
	public State run(int args) {
		eq = new EventQueue();

		// TODO: Change boolean from true to false to stop prints
		e = new StartEvent(SEED, args, M, L, LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME, LOW_PAYMENT_TIME,
				HIGH_PAYMENT_TIME, true, END_TIME, STOP_TIME, eq);

		s = e.getEventState();
		eq.addEvent(e);

		while (s.getRunSim()) {
			if (!eq.isEmpty()) {

				eq.getFirstEvent().effect();
				eq.removeFirstEvent();
			}
		}

		return s;
	}

	public static void main(String[] args) {
		RunSim rs = new RunSim();
		rs.run(13);
	}

}
