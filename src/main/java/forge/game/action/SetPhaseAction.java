package forge.game.action;

import forge.game.Game;
import forge.game.Phase;

public class SetPhaseAction extends GameAction {

	private Phase lastPhase;
	private Phase nextPhase;
	
	public SetPhaseAction(final Phase phase) {
		nextPhase = phase;
	}

	@Override
	public void execute(final Game game) {
		lastPhase = game.currentPhase;
		game.currentPhase = nextPhase;
		System.out.println("\tSetting the current phase to " + nextPhase.name());
	}

	@Override
	public void undo(final Game game) {
		game.currentPhase = lastPhase;
		System.out.println("\tUndoing phase change: Setting the current phase to " + (lastPhase != null ? lastPhase.name() : "NO PHASE"));
	}
	
}