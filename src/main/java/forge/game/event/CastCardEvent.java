package forge.game.event;

import forge.game.Game;
import forge.game.action.*;
import forge.game.card.Card;
import forge.game.zone.ZoneType;

public class CastCardEvent extends GameEvent {
	
	private Card card;
	
	public CastCardEvent(final Card card) {
		this.card = card;
	}
	
	@Override
	public void execute(final Game game) {
		game.doAction(new RemoveManaFromPoolAction("G"));
		game.doAction(new AddToStackAction(new GameEvent() {
			@Override
			public void execute(final Game game) {
				game.doAction(new ResolveStackAction());
				game.doAction(new MoveCardAction(game.activePlayer, card, ZoneType.Hand, ZoneType.Battlefield));
			}
			@Override
			public String toString() {
				return "StackEvent";
			}
		}));
		game.doAction(new PassPriorityAction());
	}
	
}
