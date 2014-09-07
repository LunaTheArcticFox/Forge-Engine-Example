package forge.game.event;


import forge.game.Game;
import forge.game.action.AddManaToPoolAction;
import forge.game.action.TapCardAction;
import forge.game.card.Card;

public class ActivateAbilityEvent extends GameEvent {

	private Card card;
	
	public ActivateAbilityEvent(final Card card) {
		this.card = card;
	}
	
	@Override
	public void execute(final Game game) {
		game.doAction(new TapCardAction(card));
		game.doAction(new AddManaToPoolAction("G"));
	}
	
}
