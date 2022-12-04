package sleepingQueens;

public class ObserverInterface implements GameObserver{
    private String message;
    @Override
    public void notify(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
