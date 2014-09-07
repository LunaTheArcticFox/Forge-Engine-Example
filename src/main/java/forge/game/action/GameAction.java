package forge.game.action;

import forge.game.Game;

/**
 * A {@link forge.game.action.GameAction} is a single action that does one thing and one thing only in the game.
 * They are used by {@link forge.game.event.GameEvent}s to create complex events and they interact directly with the 
 * game state. A {@link forge.game.event.GameEvent} does not touch the game state directly.
 */
public abstract class GameAction {
	
	public abstract void execute(final Game game);
	public abstract void undo(final Game game);
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
	
}
