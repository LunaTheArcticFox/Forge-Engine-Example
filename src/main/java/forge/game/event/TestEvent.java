package forge.game.event;

import forge.game.Game;
import forge.game.action.TestAction;

public class TestEvent extends GameEvent {

	private String name;
	
	public TestEvent(final String name) {
		this.name = "Action " + name;
	}
	
	@Override
	public void execute(final Game game) {
		game.doAction(new TestAction(name));
		game.doAction(new TestAction(name + ".5"));
	}
	
}
