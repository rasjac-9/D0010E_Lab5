
package Lab5.generic;

import java.util.Observable;

/**
 * Generic state
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
@SuppressWarnings("deprecation")
public class State extends Observable{
	protected boolean runSim = true;
	protected double currentTime = 0d;
	protected EventQueue eq;
	protected View eyeOfModor;
	
	protected int lostCustomer; // Amount of customers turned away from store
	

	/**
	 * Constructor
	 */
	public State() {
	}

	/**
	 * Changes runSim to false
	 */
	public void stopSim() {
		runSim = false;
	}
	
	/**
	 * returns runSim
	 * 
	 * @return - boolean runSim
	 */
	public boolean getRunSim() {
		return runSim;
	}

	/**
	 * Changes the current time to the arg
	 * 
	 * @param t - arg that becomes new time
	 */
	public void setCurrentTime(double t) {
		this.currentTime = t;
	}
	
	/**
	 * returns the current time
	 * 
	 * @return - double currentTime
	 */
	public double getCurrentTime() {
		return currentTime;
	}
	
	public int getLostCustomer() {
		return lostCustomer;
	}
}
