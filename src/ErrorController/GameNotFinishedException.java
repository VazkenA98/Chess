package ErrorController;

public class GameNotFinishedException extends Exception {
    public GameNotFinishedException(String error) {
        super(error);
    }
}
