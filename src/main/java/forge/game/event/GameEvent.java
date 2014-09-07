package forge.game.event;

import forge.game.Game;

/**
 * A {@link forge.game.event.GameEvent} is a series of actions to be performed sequentially, allowing for complex things
 * to happen in the game with fewer building blocks.
 */
public abstract class GameEvent {
	
	public abstract void execute(final Game game);
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
	
}
