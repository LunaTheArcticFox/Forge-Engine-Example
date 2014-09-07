package forge.game.event;


import forge.game.Game;
import forge.game.Phase;
import forge.game.action.SetPhaseAction;
import forge.game.action.UntapCardAction;
import forge.game.card.Card;
import forge.game.zone.ZoneType;

public class BeginUpkeepPhaseEvent extends GameEvent {

	@Override
	public void execute(final Game game) {
		game.doAction(new SetPhaseAction(Phase.UPKEEP));
		for (Card card : game.playerTurn.getZone(ZoneType.Battlefield)) {
			game.doAction(new UntapCardAction(card));
		}
	}
	
}
