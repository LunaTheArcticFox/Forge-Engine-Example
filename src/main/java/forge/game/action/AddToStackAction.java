package forge.game.action;

import forge.game.Game;
import forge.game.event.GameEvent;

public class AddToStackAction extends GameAction {
	
	private GameEvent event;
	
	public AddToStackAction(final GameEvent event) {
		this.event = event;
	}
	
	@Override
	public void execute(final Game game) {
		game.gameStack.addEvent(event);
		System.out.println("\tAdded " + event + " to top of stack.");
	}

	@Override
	public void undo(final Game game) {
		game.gameStack.undoLastEvent();
		System.out.println("\tUndoing stack add: Removing " + event + " from top of stack.");
	}
}
