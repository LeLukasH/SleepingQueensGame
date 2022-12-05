package sleepingQueens;

import java.util.*;

public class Game {

    public GameAdaptor gameAdaptor;
    public SleepingQueens sleepingQueens;
    public DrawingAndThrashPile drawingAndThrashPile;
    public GameState gameState;
    public Map<Integer, Player> players;

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
        updateGameState();
        drawingAndThrashPile.newTurn();
        return Optional.ofNullable(gameState);
    }

    private void updateGameState() {
        Set<SleepingQueenPosition> sleepingQueenPositions = new HashSet<>();
        for (Map.Entry<Position, Queen> entry : sleepingQueens.getQueens().entrySet()) {
            sleepingQueenPositions.add((SleepingQueenPosition) entry.getKey());
        }
        gameState.sleepingQueens = sleepingQueenPositions;
        gameState.cardsDiscardedLastTurn = drawingAndThrashPile.getCardsDiscardedThisTurn();
        Map<HandPosition, Optional<Card>> playerCards = new HashMap<>();
        for (Map.Entry<Integer, Optional<Card>> entry : players.get(gameState.onTurn).getPlayerState().cards.entrySet()) {
            playerCards.put(new HandPosition(entry.getKey(), gameState.onTurn), entry.getValue());
        }
        gameState.cards = playerCards;
        Map<AwokenQueenPosition, Queen> playersQueens = new HashMap<>();
        for (Map.Entry<Integer, Player> entry : players.entrySet()) {
            for (Map.Entry<Integer, Queen> entryAwokenQueens : entry.getValue().getPlayerState().awokenQueens.entrySet()) {
                playersQueens.put(new AwokenQueenPosition(entryAwokenQueens.getKey(), entry.getKey()), entryAwokenQueens.getValue());
            }
        }
        gameState.awokenQueens = playersQueens;
        gameState.onTurn = (gameState.onTurn + 1) % players.size();
    }
}
