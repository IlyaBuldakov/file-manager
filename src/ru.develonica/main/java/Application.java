import controllers.InputController;

/**
 * Main application class.
 */
public class Application {

    public static void main(String[] args) {
        /*
        Program loop start.
         */
        InputController inputController = new InputController();
        inputController.start();
    }
}