package PresentationLayer;
import PresentationLayer.*;

public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        new MainController(view);
        view.setVisible(true);
    }
}
