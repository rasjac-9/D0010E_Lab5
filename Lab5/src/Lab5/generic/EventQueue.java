
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
	 * @param e - Event
	 */
	public void addEvent(Event e) {

		// if list is empty add directly else sees where to add
		if (eventList.size() > 1) {
			boolean beenPlaced = false;

			// Goes through the list
			for (int i = 0; i < eventList.size(); i++) {

				// if arg´s time smaller then current event list
				if (eventList.get(i).getTime() > e.getTime()) {
					beenPlaced = true;
					eventList.add(i, e);
					break;
				}
			}

			// if the loops fails to add event adds event
			if (!beenPlaced) {
				eventList.add(e);
			}

		} else {
			eventList.add(e);
		}
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
	 * Removes the first event in the queue and sorts the queue after
	 */
	public void removeFirstEvent() {
		if (!isEmpty()) {
			eventList.remove(0);
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
