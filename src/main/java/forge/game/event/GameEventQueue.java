package forge.game.event;

import java.util.LinkedList;

public class GameEventQueue {

	private LinkedList<GameEvent> eventLinkedList = new LinkedList<>();
	
	private int currentIndex = -1; //Since the first event added will increment this to the index 0, it needs to be -1.

	/**
	 * WARNING: Removes the end of the queue -- the current index is the new last index
	 * @param event The event to add to the queue
	 */
	public void addEvent(final GameEvent event) {
		removeEnd();
		currentIndex++;
		eventLinkedList.add(event);
	}
	
	public boolean hasNextEvent() {
		return currentIndex < (eventLinkedList.size() - 1);
	}
	
	public GameEvent getNextEvent() {
		return eventLinkedList.get(++currentIndex);
	}
	
	public GameEvent getCurrentEvent() {
		return eventLinkedList.get(currentIndex);
	}
	
	public GameEvent undoLastEvent() {
		return eventLinkedList.get(currentIndex--);
	}
	
	public GameEvent removeLastEvent() {
		currentIndex--;
		return eventLinkedList.removeLast();
	}
	
	private void removeEnd() {
		System.out.println("Removing end of EventQueue...");
		if (hasNextEvent()) {
			while (eventLinkedList.size() > currentIndex + 1) {
				System.out.println("Removing: " + eventLinkedList.removeLast());
			}
		}
	}
	
	@Override
	public String toString() {
		String output = "[";
		for (GameEvent event : eventLinkedList) {
			if (eventLinkedList.indexOf(event) == currentIndex) {
				output += "CURRENT_INDEX: " + event + ", ";
			} else {
				output += event + ", ";
			}
		}
		output += "]";
		return output;
	}

}
