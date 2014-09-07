package forge.game.action;

import forge.game.Game;
import forge.game.player.Player;

public class PassPriorityAction extends GameAction {

	private Player oldPlayer;
	
	@Override
	public void execute(final Game game) {
		oldPlayer = game.activePlayer;
		game.activePlayer = game.playerAfter(game.activePlayer);
		System.out.println("\tPlayer '" + oldPlayer + "' passes priority to '" + game.activePlayer + "'");
	}

	@Override
	public void undo(final Game game) {
		game.activePlayer = oldPlayer;
		System.out.println("\tUndoing Priority Pass: Player '" + oldPlayer + "' now has priority.");
	}
	
}
