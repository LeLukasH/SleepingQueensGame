package sleepingQueens;

import java.util.*;

public class Game {

    public GameAdaptor gameAdaptor;
    public SleepingQueens sleepingQueens;
    public Map<Integer, Player> players;
    public DrawingAndThrashPile drawingAndThrashPile;
    public GameState gameState;
    private List<Integer> playersOrder;

    public Game(GameAdaptor gameAdaptor) {
        this.gameAdaptor = gameAdaptor;
        drawingAndThrashPile = new DrawingAndThrashPile(this);
        sleepingQueens = new SleepingQueens(this);
        gameState = new GameState();
        gameState.numberOfPlayers = players.size();
        gameState.onTurn = 0;
    }
    public Optional<GameState> play(int playerIdx, List<Position> cards) {
        if (!players.containsKey(playerIdx)) return Optional.empty();
        players.get(playerIdx).play(cards);

        return null;
    }

    private void updateGameState() {
        gameState.onTurn = (gameState.onTurn + 1) % players.size();
        Set<SleepingQueenPosition> sleepingQueenPositions = new HashSet<>();
        for (Map.Entry<Position, Queen> entry : sleepingQueens.getQueens().entrySet()) {
            sleepingQueenPositions.add((SleepingQueenPosition) entry.getKey());
        }
        gameState.sleepingQueens = sleepingQueenPositions;
        gameState.cardsDiscardedLastTurn = drawingAndThrashPile.getCardsDiscardedThisTurn();

    }
}
