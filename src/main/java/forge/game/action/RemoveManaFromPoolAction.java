package forge.game.action;

import forge.game.Game;

public class RemoveManaFromPoolAction extends GameAction {

	private String mana;
	
	public RemoveManaFromPoolAction(final String mana) {
		this.mana = mana;
	}
	
	@Override
	public void execute(final Game game) {
		System.out.println("\tRemoving " + mana + " from mana pool.");
	}

	@Override
	public void undo(final Game game) {
		System.out.println("\tUndoing Mana Remove: Adding " + mana + " to mana pool.");
	}
	
}
