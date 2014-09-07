package forge.game.event;

import forge.game.Game;
import forge.game.Phase;
import forge.game.action.MoveCardAction;
import forge.game.action.SetPhaseAction;
import forge.game.card.Card;
import forge.game.zone.ZoneType;

public class BeginDrawPhaseEvent extends GameEvent {

	@Override
	public void execute(final Game game) {
		game.doAction(new SetPhaseAction(Phase.DRAW));
		for (Card card : game.playerTurn.drawCard()) {
			game.doAction(new MoveCardAction(game.playerTurn, card, ZoneType.Library, ZoneType.Hand));
		}
	}
	
}
