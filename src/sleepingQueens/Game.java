package sleepingQueens;

import java.util.*;

public class Game {

    public SleepingQueens sleepingQueens;
    public DrawingAndThrashPile drawingAndThrashPile;
    public GameState gameState;
    public Map<Integer, Player> players;

    public Game(int numberOfPlayers) {
        drawingAndThrashPile = new DrawingAndThrashPile(this);
        sleepingQueens = new SleepingQueens();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.put(i, new Player(this, i));
        }
        gameState = new GameState();
        gameState.numberOfPlayers = numberOfPlayers;
        gameState.onTurn = 0;
    }
    public Optional<GameState> play(int playerIdx, List<Position> cards) {
        if (!players.containsKey(playerIdx)) return Optional.empty();
        if (players.get(playerIdx).play(cards)) {
            gameState.onTurn = (gameState.onTurn + 1) % gameState.numberOfPlayers;
            updateGameState();
            drawingAndThrashPile.newTurn();
            return Optional.ofNullable(gameState);
        }
        System.out.println("Play Again...");
        return Optional.empty();
    }

    private void updateGameState() {
        Set<SleepingQueenPosition> sleepingQueenPositions = new HashSet<>();
        for (Map.Entry<Position, Queen> entry : sleepingQueens.getQueens().entrySet()) {
            sleepingQueenPositions.add((SleepingQueenPosition) entry.getKey());
        }
        gameState.sleepingQueens = sleepingQueenPositions;

        gameState.cardsDiscardedLastTurn = drawingAndThrashPile.getCardsDiscardedThisTurn();

        Map<HandPosition, Optional<Card>> playersCards = new HashMap<>();
        for (Map.Entry<Integer, Player> entryPlayer : players.entrySet()) {
            for (Map.Entry<Integer, Optional<Card>> entryCards : entryPlayer.getValue().getPlayerState().cards.entrySet()) {
                playersCards.put(new HandPosition(entryCards.getKey(), entryPlayer.getKey()), entryCards.getValue());
            }
        }
        gameState.cards = playersCards;

        Map<AwokenQueenPosition, Queen> playersQueens = new HashMap<>();
        for (Map.Entry<Integer, Player> entryPlayer : players.entrySet()) {
            for (Map.Entry<Integer, Queen> entryAwokenQueens : entryPlayer.getValue().getPlayerState().awokenQueens.entrySet()) {
                playersQueens.put(new AwokenQueenPosition(entryAwokenQueens.getKey(), entryPlayer.getKey()), entryAwokenQueens.getValue());
            }
        }
        gameState.awokenQueens = playersQueens;
    }
}
