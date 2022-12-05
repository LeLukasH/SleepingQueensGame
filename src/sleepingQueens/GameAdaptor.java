package sleepingQueens;

import java.util.*;

public class GameAdaptor implements GamePlayerInterface{

    public Game game;
    public GameObservable gameObservable;
    public Map<String, Integer> playerConverterString; // External -> Internal (PlayerIdx)
    public Map<Integer, Integer> playerConverterObservable; // Internal -> External

    public GameAdaptor(GameObservable gameObservable) {
        if (gameObservable.getNumberOfPlayers() < 2){
            System.out.println("Too little players!");
        }
        else {
            this.gameObservable = gameObservable;
            game = new Game(gameObservable.getNumberOfPlayers());
        }
    }

    @Override
    public String play(String player, String cards) {
        int playerIndex = playerConverterString.get(player);
        List<Position> cardsSend = new ArrayList<>();
        for (String x : cards.split(" ")) {
            int cardIndex = Integer.parseInt(String.valueOf(x.charAt(1)));
            switch (x.charAt(0)) {
                case 'h' -> cardsSend.add(new HandPosition(cardIndex, playerIndex));
                case 'a' -> cardsSend.add(new AwokenQueenPosition(cardIndex, Integer.parseInt(String.valueOf(x.charAt(2)))));
                case 's' -> cardsSend.add(new SleepingQueenPosition(Integer.parseInt(x.substring(1))));
            }
        }
        Optional<GameState> gs = game.play(playerIndex, cardsSend);
        if (gs.isEmpty()) return "Error";
        gameObservable.notifyAll(gs.get());
        return "You played your cards.";
    }
}
