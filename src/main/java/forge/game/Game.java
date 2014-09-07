package forge.game;

import forge.game.action.EventMarkerAction;
import forge.game.action.GameAction;
import forge.game.action.GameActionStack;
import forge.game.action.PassPriorityAction;
import forge.game.card.Card;
import forge.game.event.*;
import forge.game.player.Player;
import forge.game.random.ForgeRandom;
import forge.game.zone.ZoneType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

	private ForgeRandom random = new ForgeRandom(1);

	private GameActionStack gameActions = new GameActionStack();
	private GameEventQueue gameEvents = new GameEventQueue();
	
	public Phase currentPhase;
	public Player playerTurn;
	
	public Player activePlayer;
	
	private List<Player> players = new ArrayList<>();
	
	public GameEventQueue gameStack = new GameEventQueue();
	
	public Game() {
		
		Player player1 = new Player("Player One");
		Player player2 = new Player("Player Two");
		
		Card forest5 = new Card(9, "Forest [5]");
		Card elvishMystic1 = new Card(0, "Elvish Mystic [1]");
		Card llanowar1 = new Card(4, "Llanowar Elves [1]");
		
		player1.getZone(ZoneType.Library).add(elvishMystic1);
		player1.getZone(ZoneType.Library).add(new Card(1, "Elvish Mystic [2]"));
		player1.getZone(ZoneType.Library).add(new Card(2, "Elvish Mystic [3]"));
		player1.getZone(ZoneType.Library).add(new Card(3, "Elvish Mystic [4]"));
		player1.getZone(ZoneType.Library).add(llanowar1);
		player1.getZone(ZoneType.Library).add(new Card(5, "Forest [1]"));
		player1.getZone(ZoneType.Library).add(new Card(6, "Forest [2]"));
		player1.getZone(ZoneType.Library).add(new Card(7, "Forest [3]"));
		player1.getZone(ZoneType.Library).add(new Card(8, "Forest [4]"));
		player1.getZone(ZoneType.Library).add(forest5);

		player2.getZone(ZoneType.Library).add(new Card(10, "Dwarven Trader [1]"));
		player2.getZone(ZoneType.Library).add(new Card(11, "Dwarven Trader [2]"));
		player2.getZone(ZoneType.Library).add(new Card(12, "Dwarven Trader [3]"));
		player2.getZone(ZoneType.Library).add(new Card(13, "Dwarven Trader [4]"));
		player2.getZone(ZoneType.Library).add(new Card(14, "Dwarven Trader [5]"));
		player2.getZone(ZoneType.Library).add(new Card(15, "Mountain [1]"));
		player2.getZone(ZoneType.Library).add(new Card(16, "Mountain [2]"));
		player2.getZone(ZoneType.Library).add(new Card(17, "Mountain [3]"));
		player2.getZone(ZoneType.Library).add(new Card(18, "Mountain [4]"));
		player2.getZone(ZoneType.Library).add(new Card(19, "Mountain [5]"));
		
		players.add(player1);
		players.add(player2);
		
		shuffleDecks();
		determineFirstPlayer();

		System.out.println("======================================");
		System.out.println("Starting New Game -- " + playerTurn + " is going first.");
		System.out.println("======================================");
		System.out.println();
		
		player1.getZone(ZoneType.Hand).addAll(player1.drawCards(7));
		player1.getZone(ZoneType.Library).removeAll(player1.getZone(ZoneType.Hand));
		player2.getZone(ZoneType.Hand).addAll(player2.drawCards(7));
		player2.getZone(ZoneType.Library).removeAll(player2.getZone(ZoneType.Hand));
		
		//Start the game
		System.out.println("Input: None");
		executeEvent(new BeginUntapPhaseEvent());
		printState();
		System.out.println("Input: None");
		executeEvent(new BeginUpkeepPhaseEvent());
		printState();
		System.out.println("Input: Pass - Nothing to Do");
		doAction(new PassPriorityAction());
		printState();
		System.out.println("Input: Pass - Nothing to Do");
		doAction(new PassPriorityAction());
		printState();
		System.out.println("Input: Both players passed.");
		executeEvent(new BeginDrawPhaseEvent());
		printState();
		System.out.println("Input: Pass - Nothing to Do");
		doAction(new PassPriorityAction());
		printState();
		System.out.println("Input: Pass - Nothing to Do");
		doAction(new PassPriorityAction());
		printState();
		System.out.println("Input: Both players passed.");
		executeEvent(new BeginPrecombatMainPhaseEvent());
		printState();
		System.out.println("Input: Play Forest");
		executeEvent(new PlayLandEvent(forest5));
		printState();
		System.out.println("Input: Tap Forest");
		executeEvent(new ActivateAbilityEvent(forest5));
		printState();
		System.out.println("Input: Cast Elvish Mystic");
		executeEvent(new CastCardEvent(elvishMystic1));
		printState();
		System.out.println("Input: Pass - Nothing to Do (No Counter)");
		doAction(new PassPriorityAction());
		printState();
		System.out.println("Input: Both players passed.");
		System.out.println("All players passed priority... stack not empty! Resolving!");
		executeEvent(gameStack.getCurrentEvent());
		printState();
		
		undoLastEvent();
		printState();
		undoLastEvent();
		printState();
		
		System.out.println("Input: Cast Llanowar Elves");
		executeEvent(new CastCardEvent(llanowar1));
		printState();
		System.out.println("Input: Pass - Nothing to Do (No Counter)");
		doAction(new PassPriorityAction());
		printState();
		System.out.println("Input: Both players passed.");
		System.out.println("All players passed priority... stack not empty! Resolving!");
		executeEvent(gameStack.getCurrentEvent());
		printState();
		//
		/*undoLastEvent();
		printState();
		undoLastEvent();
		printState();
		undoLastEvent();
		printState();
		undoLastEvent();
		printState();
		undoLastEvent();
		printState();*/
		
	}
	
	public Player playerAfter(final Player player) {
		int playerIndex = players.indexOf(player) + 1;
		if (playerIndex > players.size() - 1) {
			return players.get(0);
		} else {
			return players.get(playerIndex);
		}
	}
	
	private void shuffleDecks() {
		for (Player player : players) {
			Collections.shuffle(player.getZone(ZoneType.Library), random);
		}
	}
	
	private void determineFirstPlayer() {
		playerTurn = activePlayer = players.get(0);
	}
	
	public ForgeRandom getRandom() {
		return random;
	}
	
	private void executeEvent(final GameEvent event) {
		System.out.println("Executing Event: " + event);
		gameActions.add(new EventMarkerAction());
		gameEvents.addEvent(event);
		event.execute(this);
	}

	public void doAction(final GameAction action) {
		System.out.println("Doing Action: " + action);
		gameActions.push(action);
		action.execute(this);
	}
	
	private void undoLastEvent() {

		GameEvent event = gameEvents.undoLastEvent();

		System.out.println("--Undoing Last Event: " + event + "--");
		
		GameAction previousAction;
		
		while (!gameActions.isEmpty() && !((previousAction = gameActions.pop()) instanceof EventMarkerAction)) {
			System.out.println("Undoing Action: " + previousAction);
			previousAction.undo(this);
		}

		System.out.println("--Event Undone--");
		
		System.out.flush();
		
	}
	
	private void redoNextEvent() {
		gameEvents.getNextEvent().execute(this);
	}
	
	private void printState() {

		System.out.println();
		System.out.println("===============================");
		System.out.println("GAME STATE");
		System.out.println("===============================");
		System.out.println();
		System.out.println("\tMagic Stack: [Old] " + gameStack + " [New]");
		System.out.println("\tGameEventQueue: [Old] " + gameEvents + " [New]");
		System.out.println("\tGameActionStack: [Old] " + gameActions + " [New]");
		System.out.println();
		System.out.println("\tPriority: " + activePlayer);
		System.out.println("\tTurn: " + playerTurn);
		System.out.println("\tPhase: " + currentPhase);
		System.out.println();
		System.out.println("\tPlayer Stats:");
		System.out.println();
		
		for (Player player : players) {
			System.out.println("\t\t" + player + ":");
			System.out.println("\t\t\tLibrary: ");
			for (Card card : player.getZone(ZoneType.Library)) {
				System.out.println("\t\t\t\t" + card);
			}
			System.out.println("\t\t\tHand: ");
			for (Card card : player.getZone(ZoneType.Hand)) {
				System.out.println("\t\t\t\t" + card);
			}
			System.out.println("\t\t\tBattlefield: ");
			for (Card card : player.getZone(ZoneType.Battlefield)) {
				System.out.println("\t\t\t\t" + card + " (" + (card.tapped ? "Tapped" : "Untapped") + ")");
			}
			System.out.println();
		}

		System.out.println("===============================");
		System.out.println("===============================");
		System.out.println();
		
	}
	
}
