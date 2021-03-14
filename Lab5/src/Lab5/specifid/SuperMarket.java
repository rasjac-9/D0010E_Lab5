
package Lab5.specifid;

import Lab5.generic.View;
import Lab5.generic.State;
import Lab5.generic.Event;

import Lab5.rand.ExponentialRandomStream;
import Lab5.rand.UniformRandomStream;

/**
 * A Super Market store
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class SuperMarket extends State {

	// Variables to create new random numbers
	private UniformRandomStream ranStreamShop;
	private ExponentialRandomStream expStream;
	private UniformRandomStream ranStreamPick;

	/*
	 * ALL THE VARIABLES THAT THE STORE KEEPS TRACK OF
	 */
	protected int emptyReg; 		// num. of empty cash registers
	protected int totCustomer; 		// Total amount of customers that have tried to shop
	protected int customerPayed; 	// Amount of customers that have payed
	protected int inStore; 			// Current amount of customers in the store

	protected boolean openForBis; 	// Is store open or not
	protected double emptyRegTime; 	// Time cash registers been empty
	protected double inQueueTime; 	// Time some one have queued
	protected double lastEventTime; // The last time a event happens
	public FIFO cashQueue; 			// The queue to cash registers

	/*
	 * ALL VARIABLES THAT ARE GIVEN TO THE CONSTRUCTOR
	 */
	protected int SEED;
	protected int regLim;
	protected int customerLimit;

	protected double Lamda;
	protected double pMin;
	protected double pMax;
	protected double kMin;
	protected double kMax;

	/**
	 * Constructor that creates a super market
	 * 
	 * @param seed     - seed brings randomness
	 * @param regLimit - cash register limit
	 * @param CLimit   - max amount of customer in store
	 * 
	 * @param lambda   - arrival difference signifier
	 * @param pMin     - minimum time to pick items
	 * @param pMax     - maximum time to pick items
	 * @param kMin     - minimum time to pay for items
	 * @param kMax     - maximum time to pay for items
	 * @param output   - boolean if print to console shall happen or not
	 */
	@SuppressWarnings("deprecation")
	public SuperMarket(int seed, int regLimit, int CLimit, double lambda, double pMin, double pMax, double kMin,
			double kMax, boolean output) {

		// The given number of integer parameters
		this.SEED = seed;
		this.regLim = regLimit;
		this.customerLimit = CLimit;

		// The given number of double parameters
		this.Lamda = lambda;
		this.pMin = pMin;
		this.pMax = pMax;
		this.kMin = kMin;
		this.kMax = kMax;

		// To get random numbers for the customers
		ranStreamShop = new UniformRandomStream(kMin, kMax, SEED);
		ranStreamPick = new UniformRandomStream(pMin, pMax, SEED);
		expStream = new ExponentialRandomStream(Lamda, SEED);

		openForBis = true; // store is opened
		emptyReg = regLim; // sets amount of empty registers
		cashQueue = new FIFO(); // opens the register queue

		// Changes if we print to console or not
		if (output) {
			super.v = new SuperMarketConsole(this);
		} else {
			super.v = new View();
		}
		this.addObserver(v);
	}

	/*************************************************************************************************************************
	 * 
	 * THE FOLLOWING FUNKTIONS ARE USET TO MODIFY/GET THE DIFFRENT CUSTOMER VARIBELS
	 * 
	 *************************************************************************************************************************/

	/**
	 * Adds a customer to the store if customerLimit is not reached
	 * 
	 * @return - boolean if customerLimit is reached or not
	 */
	public boolean addCustomer() {
		if (inStore == customerLimit) {
			return false;
		} else {
			totCustomer += 1;
			inStore += 1;
			return true;
		}
	}

	/**
	 * Customer pays and leave store
	 */
	public void customerLeftStore() {
		if (inStore != 0) {
			inStore -= 1;
			customerPayed += 1;
			freeCashRegister();
		}
	}

	/**
	 * Returns the amount of people in the store currently.
	 * 
	 * @return - int customers in store
	 */
	public int getCustomers() {
		return inStore;
	}

	/**
	 * Returns the amount of people that have successfully paid.
	 * 
	 * @return - int customers that payed
	 */
	public int getShopped() {
		return customerPayed;
	}

	/**
	 * Adds to the amount turned away from the store
	 */
	public void addLostCustomer() {
		lostCustomer += 1;
	}

	/**
	 * returns the amount of missed customers if the store is full.
	 * 
	 * @return - int lost customers
	 */
	public int getLostCustomer() {
		return lostCustomer;
	}

	/**
	 * Returns the total amount of people that tried to shop.
	 * 
	 * @return - int total customers
	 */
	public int getTotalCustomers() {
		return totCustomer;
	}

	/*************************************************************************************************************************
	 * 
	 * THE FOLLOWING FUNKTIONS ARE USET TO MODIFY/GET THE DIFFRENT STORE VALUES
	 * 
	 *************************************************************************************************************************/

	/**
	 * Closes the store for new customers
	 */
	public void closeStore() {
		openForBis = false;
	}

	/**
	 * Returns if the store is open or not
	 * 
	 * @return - boolean openForBis
	 */
	public boolean getOpenForBis() {
		return openForBis;
	}

	/**
	 * Try to occupy a cash register if not possible throws exception
	 * 
	 * @throws ArithmeticException
	 */
	public void addToCashReg() throws ArithmeticException {
		if (emptyReg == 0) {
			throw new ArithmeticException();
		} else {
			emptyReg -= 1;
		}
	}

	/**
	 * Creates a free cash register
	 */
	public void freeCashRegister() {
		if (emptyReg != regLim) {
			emptyReg += 1;
		}
	}

	/**
	 * Returns the amount of registers that are not being used
	 * 
	 * @return - int amount of emptyReg
	 */
	public int getAvailableCashReg() {
		return emptyReg;
	}

	/**
	 * Returns the max amount of registers
	 * 
	 * @return - int max amount of registers
	 */
	public int getRegisterLimit() {
		return regLim;
	}

	/*************************************************************************************************************************
	 * 
	 * THE FOLLOWING FUNKTIONS ARE USET TO SET TIME TO DIFFRENT EVENTS ORE RELATED
	 * TO TIME IN A DIFFRENT WAY
	 * 
	 *************************************************************************************************************************/

	/**
	 * Returns a random time it takes fore one customer arrive to the store
	 * 
	 * @return - double time to arrive
	 */
	public double getArrivalTime() {
		return currentTime + expStream.next();
	}

	/**
	 * Returns a random time it takes fore one customer to pick his/her items
	 * 
	 * @return - double time to pick stuff
	 */
	public double getPickTime() {
		return currentTime + ranStreamPick.next();
	}

	/**
	 * Returns a random time it takes fore one customer to pay for his/her items
	 * 
	 * @return - double time to pay for stuff
	 */
	public double getPayTime() {
		return currentTime + ranStreamShop.next();
	}

	/**
	 * Update method that updates the time available cash registers has been empty.
	 * It updates the total queue time of all customers. Updates the current time
	 * and then notifies the observes. This method is called before every event
	 * happens.
	 * 
	 * @param event that happens
	 */
	@SuppressWarnings("deprecation")
	public void viewUpdate(Event event) {
		// Counts how much the cash reg. is not used
		if (emptyReg != 0) {
			// Makes a exception for when the store is closing
			if (!openForBis && event.getName() == "Ankomst") {
			} else {
				emptyRegTime += (event.getTime() - currentTime) * emptyReg;
			}
		}

		// Counts how much time customers spends in queue
		if (!cashQueue.isEmpty()) {
			inQueueTime += (event.getTime() - currentTime) * cashQueue.getSize();
		}

		// Updates current time
		currentTime = event.getTime();

		//
		if (!openForBis && event.getName() == "Ankomst") {
		} else {
			lastEventTime = event.getTime();
		}

		setChanged();
		notifyObservers(event);
	}

	/**
	 * specific viewUpdate for stopEvent
	 * 
	 * @param event - the stop event
	 */
	@SuppressWarnings("deprecation")
	public void stopUpdate(Event event) {
		setChanged();
		notifyObservers(event);
	}
}
