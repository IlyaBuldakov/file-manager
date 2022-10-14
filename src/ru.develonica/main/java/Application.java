import controllers.InputController;
import models.Message;

public class Application {

    public static void main(String[] args) {
        InputController inputController = new InputController();
        inputController.start();
        System.err.println(Message.IO_ERROR.getText());
    }
}
