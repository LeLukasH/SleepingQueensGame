package sleepingQueens;

public class GameAdaptor implements GamePlayerInterface{

    public Game game;
    public GameObservable gameObservable;

    public GameAdaptor(Game game) {
        this.game = game;
        gameObservable = new GameObservable(this);
    }

    @Override
    public String play(String player, String cards) {
        return null;
    }

    public void setGameObservable(GameObservable gameObservable){
        this.gameObservable = gameObservable;
    }
}
