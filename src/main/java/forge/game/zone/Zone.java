package forge.game.zone;

import forge.game.card.Card;

import java.util.ArrayList;

public class Zone extends ArrayList<Card> {
	
	public Zone() {
		super();
	}
	
	public Zone(final Zone zone) {
		super();
		for (Card card : zone) {
			add(card);
		}
	}
	
}
