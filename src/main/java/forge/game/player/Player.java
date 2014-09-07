package forge.game.player;

import forge.game.card.Card;
import forge.game.zone.Zone;
import forge.game.zone.ZoneType;

import java.util.*;

public class Player {
	
	private PlayerController playerController;
	
	private String name;
	
	private final Map<ZoneType, Zone> zones = new EnumMap<>(ZoneType.class);
	
	public Player(final String name) {
		this.name = name;
		zones.put(ZoneType.Library, new Zone());
		zones.put(ZoneType.Hand, new Zone());
		zones.put(ZoneType.Battlefield, new Zone());
	}
	
	public String getName() {
		return name;
	}
	
	public List<Card> drawCard() {
		return drawCards(1);
	}
	
	public final List<Card> drawCards(final int n) {
		final List<Card> drawn = new ArrayList<>();
		final Zone library = this.getZone(ZoneType.Library);

		for (int i = 0; i < n; i++) {
			drawn.add(library.get(i));
		}
		return drawn;
	}
	
	public Zone getZone(final ZoneType zoneType) {
		return zones.get(zoneType);
	}
	
	public void setZone(final ZoneType zoneType, final Zone zone) {
		zones.put(zoneType, zone);
	}
	
	public PlayerController getController() {
		return playerController;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
