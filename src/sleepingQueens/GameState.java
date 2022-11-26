package sleepingQueens;

import java.util.*;

public class GameState {
    private int numberOfPlayers;
    private int onTurn;
    private Set<SleepingQueenPosition> sleepingQueens;
    private Map<HandPosition, Optional<Card>> cards;
    private Map<AwokenQueenPosition, Queen> awokenQueens;
    private List<Card> cardsDiscardedLastTurn;
}
