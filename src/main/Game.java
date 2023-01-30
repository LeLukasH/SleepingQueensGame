import java.util.*;

public class Game {

    private final SleepingQueens sleepingQueens;
    private final DrawingAndThrashPile drawingAndThrashPile;
    private final GameState gameState;
    private final Map<Integer, Player> players;
    private final GameFinishedStrategy gameFinished;

    public Game(int numberOfPlayers) {
        sleepingQueens = new SleepingQueens();
        drawingAndThrashPile = new DrawingAndThrashPile(true);

        players = createPlayers(numberOfPlayers, sleepingQueens, drawingAndThrashPile);

        gameState = new GameState();
        gameState.numberOfPlayers = numberOfPlayers;
        gameState.onTurn = 0;
        updateGameState();

        this.gameFinished = new GameFinished(gameState);
    }

    public Optional<GameState> play(int playerIdx, List<Position> cards) {
        if (!players.containsKey(playerIdx) || playerIdx != gameState.onTurn) return Optional.empty();
        if (players.get(playerIdx).play(cards)) {
            gameState.onTurn = (gameState.onTurn + 1) % gameState.numberOfPlayers;
            updateGameState();
            drawingAndThrashPile.newTurn();
            Optional<Integer> winner = gameFinished.isFinished();
            if (winner.isPresent()) {
                System.out.println("Winner is: " + winner.get());
                System.out.println("Game Over");
                gameState.onTurn = -1;
            }
            return Optional.of(gameState);
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

    public DrawingAndThrashPile getDrawingAndThrashPile() {
        return drawingAndThrashPile;
    }

    private Map<Integer, Player> createPlayers(int numberOfPlayers, SleepingQueens sleepingQueens, DrawingAndThrashPile drawingAndThrashPile){
        Map<Integer, Player> returnPlayers = new HashMap<>();
        MoveQueen moveQueen = new MoveQueen(returnPlayers, sleepingQueens);
        EvaluateAttack evaluateAttack = new EvaluateAttack(returnPlayers, moveQueen);
        EvaluateNumberedCards evaluateNumberedCards = new EvaluateNumberedCards();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.put(i, new Player(moveQueen, evaluateAttack, evaluateNumberedCards, drawingAndThrashPile, i));
        }
        return returnPlayers;
    }
}
