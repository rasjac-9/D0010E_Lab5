
package Lab5.generic;

import java.util.ArrayList;

/**
 * Queues all event
 * 
 * @author Alex Bergdahl,
 * @author Kim Eriksson,
 * @author Peggy Khialie,
 * @author Rasmus Jacobsen
 *
 */
public class EventQueue {
	private ArrayList<Event> eventList = new ArrayList<>();

	/**
	 * Adds event to the queue, and sorts them
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {
		eventList.add(event);
		sortEvent();
	}

	/**
	 * Returns the first event in the queue
	 * 
	 * @return - Event
	 */
	public Event getFirstEvent() {
		return eventList.get(0);
	}

	/**
	 * Sorts the events depending on time
	 */
	public void sortEvent() {

		if (eventList.size() > 1) { // needs to hold more than 2 objects to sort

			/*
			 * e.g. arraylist [5,3,8,6] first loop: if (5 > 3) {swap place, [3,5,8,6]} if (5
			 * > 8) if (5 > 6) second loop: if (8 > 6) {swap place [3,5,6,8]}
			 */
			for (int i = 1; i < eventList.size(); i++) {
				for (int j = 0; j < eventList.size() - 1; j++) {

					if (eventList.get(j).getTime() > eventList.get(i).getTime()) {
						Event eventIndexI = eventList.get(i);
						Event eventIndexJ = eventList.get(j);

						eventList.set(j, eventIndexI);
						eventList.set(i, eventIndexJ);
					}
				}
			}
		}
	}

	/**
	 * removes the first event in the queue and sorts the queue after
	 */
	public void removeFirstEvent() {
		if (!isEmpty()) {
			eventList.remove(0);
			sortEvent();
		}
	}

	/**
	 * Checks if the queue is empty
	 * 
	 * @return - boolean
	 */
	public boolean isEmpty() {
		if (eventList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
