package forge.game.action;

import forge.game.Game;
import forge.game.zone.Zone;
import forge.game.zone.ZoneType;

import java.util.Collections;

public class ShuffleAction extends GameAction {

	private ZoneType zoneType;
	private Zone oldZone;
	
	public ShuffleAction(final ZoneType zoneType) {
		this.zoneType = zoneType;
	}

	@Override
	public void execute(final Game game) {
		System.out.println("\tShuffling " + zoneType.name() + " for player " + game.playerTurn + ".");
		oldZone = new Zone(game.playerTurn.getZone(zoneType));
		Collections.shuffle(game.playerTurn.getZone(zoneType), game.getRandom());
	}

	@Override
	public void undo(final Game game) {
		game.playerTurn.setZone(zoneType, oldZone);
		System.out.println("\tUndoing shuffle: " + zoneType.name() + " for player " + game.playerTurn + " has been reverted.");
	}
	
}
