package forge.game.card;

public class Card {
	
	public final int uniqueNumber;
	public final String name;
	
	public boolean tapped = false;
	
	public Card(final int id, final String name) {
		this.uniqueNumber = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return uniqueNumber + " - " + name;
	}
	
}
