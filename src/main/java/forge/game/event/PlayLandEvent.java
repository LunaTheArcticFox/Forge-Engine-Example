package forge.game.event;

import forge.game.Game;
import forge.game.action.MoveCardAction;
import forge.game.card.Card;
import forge.game.zone.ZoneType;

public class PlayLandEvent extends GameEvent {
	
	private Card landCard;
	
	public PlayLandEvent(final Card landCard) {
		this.landCard = landCard;
	}
	
	@Override
	public void execute(final Game game) {
		game.doAction(new MoveCardAction(game.activePlayer, landCard, ZoneType.Hand, ZoneType.Battlefield));
	}
	
}
