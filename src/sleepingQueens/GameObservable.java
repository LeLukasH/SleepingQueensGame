package sleepingQueens;

public class GameObservable {
    public ShuffleStrategy shuffleStrategy;

    public void add(ObserverInterface observer) {

    }

    public void addPlayer(int playerIdx, ObserverInterface observer) {

    }

    public void remove(ObserverInterface observer) {

    }

    public void notifyAll(GameState message) {

    }

    public void setShuffleStrategy(ShuffleStrategy shuffleStrategy){
        this.shuffleStrategy = shuffleStrategy;
    }
}
