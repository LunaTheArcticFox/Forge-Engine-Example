package forge.game.action;

import forge.game.Game;
import forge.game.card.Card;

public class TapCardAction extends GameAction {
	
	private Card card;
	
	public TapCardAction(final Card card) {
		this.card = card;
	}
	
	@Override
	public void execute(final Game game) {
		card.tapped = true;
		System.out.println("\tTapping " + card);
	}

	@Override
	public void undo(final Game game) {
		card.tapped = false;
		System.out.println("\tUndoing tap of " + card);
	}

}
