package forge.game.action;

import forge.game.Game;
import forge.game.card.Card;
import forge.game.player.Player;
import forge.game.zone.ZoneType;

public class MoveCardAction extends GameAction {

	private Player player;
	private Card card;
	private int index;
	private ZoneType from, to;
	
	public MoveCardAction(final Player player, final Card card, final ZoneType from, final ZoneType to) {
		this.player = player;
		this.card = card;
		this.from = from;
		this.to = to;
	}
	
	@Override
	public void execute(final Game game) {
		index = player.getZone(from).indexOf(card);
		player.getZone(from).remove(card);
		player.getZone(to).add(card);
		System.out.println("\t" + player + " moves " + card.name + " from " + from.name() + " to " + to.name());
	}

	@Override
	public void undo(final Game game) {
		player.getZone(from).add(index, card);
		player.getZone(to).remove(card);
		System.out.println("\tUndoing card move: " + player + " moves " + card.name + " from " + to.name() + " to " + from.name());
	}
	
}
