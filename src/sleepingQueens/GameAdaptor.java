package sleepingQueens;

import java.util.*;

public class GameAdaptor implements GamePlayerInterface{

    public Game game;
    public GameObservable gameObservable;
    public Map<String, Integer> playerConverter; // External -> Internal (PlayerIdx)

    public GameAdaptor(Game game) {
        this.game = game;
        gameObservable = new GameObservable(this);
    }

    @Override
    public String play(String player, String cards) {
        int playerIndex = playerConverter.get(player);
        List<Position> cardsSend = new ArrayList<>();
        String[] split = cards.split(" ");
        for (String x : split) {
            int cardIndex = Integer.parseInt(String.valueOf(x.charAt(1)));
            switch (x.charAt(0)) {
                case 'h' -> cardsSend.add(new HandPosition(cardIndex, playerIndex));
                case 'a' -> cardsSend.add(new AwokenQueenPosition(cardIndex, Integer.parseInt(String.valueOf(x.charAt(2)))));
                case 's' -> cardsSend.add(new SleepingQueenPosition(Integer.parseInt(x.substring(1))));
            }
        }
        return game.play(playerIndex, cardsSend).toString();
    }
}
