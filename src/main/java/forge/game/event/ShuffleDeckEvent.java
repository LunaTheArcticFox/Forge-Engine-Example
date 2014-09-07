package forge.game.event;

import forge.game.Game;
import forge.game.action.ShuffleAction;
import forge.game.random.ForgeRandom;
import forge.game.zone.ZoneType;

public class ShuffleDeckEvent extends GameEvent {
	
	private long seed;
	private ForgeRandom random;
	
	public ShuffleDeckEvent(final ForgeRandom random) {
		this.random = random;
		seed = random.getSeed();
	}
	
	@Override
	public void execute(final Game game) {
		random.setSeed(seed);
		game.doAction(new ShuffleAction(ZoneType.Library));
	}
	
}
