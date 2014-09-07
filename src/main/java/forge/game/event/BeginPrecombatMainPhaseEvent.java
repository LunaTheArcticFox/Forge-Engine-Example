package forge.game.event;

import forge.game.Game;
import forge.game.Phase;
import forge.game.action.SetPhaseAction;

public class BeginPrecombatMainPhaseEvent extends GameEvent {

	@Override
	public void execute(final Game game) {
		game.doAction(new SetPhaseAction(Phase.MAIN1));
	}
	
}
