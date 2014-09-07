package forge.game.action;

import forge.game.Game;
import forge.game.card.Card;

public class UntapCardAction extends GameAction {
	
	private Card card;
	
	public UntapCardAction(final Card card) {
		this.card = card;
	}
	
	@Override
	public void execute(final Game game) {
		card.tapped = false;
		System.out.println("\tUntapping " + card);
	}

	@Override
	public void undo(final Game game) {
		card.tapped = true;
		System.out.println("\tUndoing untap of " + card);
	}

}
