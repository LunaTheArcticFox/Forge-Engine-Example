package forge.game.action;

import forge.game.Game;
import forge.game.event.GameEvent;

public class ResolveStackAction extends GameAction {
	
	private GameEvent event;

	@Override
	public void execute(final Game game) {
		event = game.gameStack.removeLastEvent();
		System.out.println("\tResolving top of stack: " + event);
	}

	@Override
	public void undo(final Game game) {
		game.gameStack.addEvent(event);
		System.out.println("\tUndoing stack resolution: Adding to top of stack: " + event);
	}
	
}
