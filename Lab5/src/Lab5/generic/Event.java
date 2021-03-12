package Lab5.generic;

/**
 * Generic event
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class Event {
	protected int ID;
	protected State s;
	protected String name;
	protected double time;
	protected EventQueue eq;

	/**
	 * Events constructor
	 */
	public Event() {
	}

	/**
	 * What the event do
	 */
	public void effect() {
	}
	
	/**
	 * Special form of what event do
	 * 
	 * @param t - double time
	 */
	public void effect(double t) {
	}

	/**
	 * return events time
	 * 
	 * @return - double time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * return event id
	 * 
	 * @return - int id
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Returns event name
	 * 
	 * @return - string name
	 */
	public String getName() {
		return name;
	}
	
	public State getEventState() {
		return s;
	};
}
