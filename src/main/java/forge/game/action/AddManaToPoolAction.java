package forge.game.action;

import forge.game.Game;

public class AddManaToPoolAction extends GameAction {

	private String mana;
	
	public AddManaToPoolAction(final String mana) {
		this.mana = mana;
	}
	
	@Override
	public void execute(final Game game) {
		System.out.println("\tAdding " + mana + " to mana pool.");
	}

	@Override
	public void undo(final Game game) {
		System.out.println("\tUndoing Mana Add: Removing " + mana + " from mana pool.");
	}
	
}
