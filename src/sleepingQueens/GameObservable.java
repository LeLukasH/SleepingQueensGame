package sleepingQueens;

import java.util.*;

public class GameObservable {
    private final List<ObserverInterface> allObservers;
    public Map<Integer, ObserverInterface> playersObservers;
    private final GameAdaptor gameAdaptor;

    public GameObservable(GameAdaptor gameAdaptor) {
        this.gameAdaptor = gameAdaptor;
        allObservers = new ArrayList<>();
        playersObservers = new HashMap<>();
    }

    public void add(ObserverInterface observer) {
        allObservers.add(observer);
    }

    public void addPlayer(int playerIdx, ObserverInterface observer) {
        gameAdaptor.game.players.put(playerIdx, new Player(gameAdaptor.game, playerIdx));
        gameAdaptor.game.gameState.numberOfPlayers += 1;
        playersObservers.put(playerIdx, observer);
        allObservers.add(observer);
    }

    public void remove(ObserverInterface observer) {
        allObservers.remove(observer);
    }

    public void notifyAll(GameState message) {
        String s0 = "- GAMESTATE -";
        String s1 = "Number of players: " + message.numberOfPlayers;
        String s2 = "On turn: " + message.onTurn;
        String s3 = "Sleeping Queens Positions: " + message.sleepingQueens;
        String s4 = "Cards: " + message.cards;
        String s5 = "Awoken Queens: " + message.awokenQueens;
        String s6 = "Cards discarded this turn: " + message.cardsDiscardedLastTurn;
        String notifyingMessage = s0 + "\n" + s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5 + "\n" + s6;

        for (GameObserver o : allObservers) {
            o.notify(notifyingMessage);
        }
    }
}
