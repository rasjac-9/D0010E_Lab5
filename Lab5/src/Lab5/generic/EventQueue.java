
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
	public void addEvent(Event e) {

		if (eventList.size() > 1) {
			boolean beenPlaced = false;
			for (int i = 0; i < eventList.size(); i++) {
				if (eventList.get(i).getTime() > e.getTime()) {
					beenPlaced = true;
					eventList.add(i, e);
					break;
				}
			}
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
	 * removes the first event in the queue and sorts the queue after
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
