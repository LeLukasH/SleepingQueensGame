import java.util.*;

public class GameFinished implements GameFinishedStrategy{

    private final GameState gameState;
    private final int scoreLine;
    private final int queenLine;

    public GameFinished(GameState gameState) {
        this.gameState = gameState;
        if (gameState.numberOfPlayers == 2 || gameState.numberOfPlayers == 3) {
            scoreLine = 50;
            queenLine = 5;
        }
        else if (gameState.numberOfPlayers == 4 || gameState.numberOfPlayers == 5) {
            scoreLine = 40;
            queenLine = 4;
        }
        else {
            scoreLine = 200;
            queenLine = 13;
        }
    }
    @Override
    public Optional<Integer> isFinished() {
        Map<Integer, Integer> playerPoints = new HashMap<>(); // playerIdx, points
        Map<Integer, Integer> playerQueens = new HashMap<>(); // playerIdx, queenCount
        for (Map.Entry<AwokenQueenPosition, Queen> entry : gameState.awokenQueens.entrySet()) {
            int playerIdx = entry.getKey().getPlayerIndex();
            playerPoints.put(playerIdx, playerPoints.getOrDefault(playerIdx, 0) + entry.getValue().getPoints());
            playerQueens.put(playerIdx, playerQueens.getOrDefault(playerIdx, 0) + 1);
            if (playerPoints.get(playerIdx) >= scoreLine || playerQueens.get(playerIdx) >= queenLine) {
                return Optional.of(playerIdx);
            }
        }
        if (gameState.sleepingQueens.isEmpty()) {
            int maxPoints = -1;
            int winningPlayerIdx = -1;
            for (Map.Entry<Integer, Integer> entry : playerPoints.entrySet()) {
                if (entry.getValue() > maxPoints) {
                    maxPoints = entry.getValue();
                    winningPlayerIdx = entry.getKey();
                }
            }
            return Optional.of(winningPlayerIdx);
        }
        return Optional.empty();
    }
}
