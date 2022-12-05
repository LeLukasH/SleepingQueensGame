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
        for (String x : cards.split(" ")) {
            int cardIndex = Integer.parseInt(String.valueOf(x.charAt(1)));
            switch (x.charAt(0)) {
                case 'h' -> cardsSend.add(new HandPosition(cardIndex, playerIndex));
                case 'a' -> cardsSend.add(new AwokenQueenPosition(cardIndex, Integer.parseInt(String.valueOf(x.charAt(2)))));
                case 's' -> cardsSend.add(new SleepingQueenPosition(Integer.parseInt(x.substring(1))));
            }
        }
        Optional<GameState> gs = game.play(playerIndex, cardsSend);
        if (gs.isEmpty()) return "Error...";
        return gs.get().toString();
    }
}
