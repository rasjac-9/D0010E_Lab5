
package Lab5.specifid;

import java.util.ArrayList;

import Lab5.generic.Event;

import java.util.NoSuchElementException;

/**
 * The queue to the cash registers
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class FIFO {
	private ArrayList<Event> cashQueue = new ArrayList<Event>();
	public int queueCounter = 0;

	/**
	 * Adds a event to the event list
	 * 
	 * @param e - Event
	 */
	public void addToFIFO(Event e) {
		cashQueue.add(e);
		queueCounter++;
	}

	/**
	 * Removes the first event in the list throws exception if list is empty
	 * 
	 * @throws NoSuchElementException
	 */
	public void removeFirst() throws NoSuchElementException {
		if (getSize() > 0) {
			cashQueue.remove(0);
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns the total number of people that have bin in the queue
	 * 
	 * @return int number of people in queue
	 */
	public int totalQueue() {
		return queueCounter;
	}

	/**
	 * @return - true/false depending if the list is empty or not
	 */
	public boolean isEmpty() {
		if (getSize() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the first event in the list
	 * 
	 * @return Event - the first event
	 * @throws NoSuchElementException
	 */
	public Event getFirst() throws NoSuchElementException {
		if (cashQueue.size() > 0) {
			return cashQueue.get(0);
		} else {
			throw new NoSuchElementException();
		}

	}

	/**
	 * Returns the size of the array
	 * 
	 * @return - int size of queue
	 */
	public int getSize() {
		return cashQueue.size();
	}

	/**
	 * Returns a list of all the customerIDs in the array.
	 * 
	 * @return - list of customer IDs
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList getQue() {
		ArrayList<Integer> customerList = new ArrayList<>();
		for (int i = 0; i < cashQueue.size(); i++) {
			customerList.add(cashQueue.get(i).getID());
		}
		return customerList;
	}
}