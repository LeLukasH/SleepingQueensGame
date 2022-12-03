package sleepingQueens;

import java.util.*;

public class GameFinished implements GameFinishedStrategy{

    private final Game game;
    private final int scoreLine;
    private final int queenLine;

    public GameFinished(Game game) {
        this.game = game;
        if (game.gameState.numberOfPlayers == 2 || game.gameState.numberOfPlayers == 3) {
            scoreLine = 50;
            queenLine = 5;
        }
        else if (game.gameState.numberOfPlayers == 4 || game.gameState.numberOfPlayers == 5) {
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
        for (Map.Entry<AwokenQueenPosition, Queen> entry : game.gameState.awokenQueens.entrySet()) {
            int playerIdx = entry.getKey().getPlayerIndex();
            playerPoints.put(playerIdx, playerPoints.getOrDefault(playerIdx, 0) + entry.getValue().getPoints());
            playerQueens.put(playerIdx, playerQueens.getOrDefault(playerIdx, 0) + 1);
            if (playerPoints.get(playerIdx) >= scoreLine || playerQueens.get(playerIdx) >= queenLine) {
                return Optional.of(playerIdx);
            }
        }
        return Optional.empty();
    }
}
