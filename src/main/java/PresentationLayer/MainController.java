package PresentationLayer;

/**
 * The Maincontroller handles user interaction from the MainView
 * This class acts as the main navigator controller in the app
 * @author Carla Bozintan
 * @version 1
 */
public class MainController {
    private MainView view;

    /**
     * Constructs the controller and attaches event listeners to the main menu buttons
     * @param view the main menu view
     */
    public MainController(MainView view) {
        this.view = view;

        view.clientsButton.addActionListener(e -> {
            ClientView clientView = new ClientView();
            new ClientController(clientView);
            clientView.setVisible(true);
        });

        view.productsButton.addActionListener(e -> {
            ProductView productView = new ProductView();
            new ProductController(productView);
            productView.setVisible(true);
        });

        view.ordersButton.addActionListener(e -> {
            OrderView orderView = new OrderView();
            new OrderController(orderView);
            orderView.setVisible(true);
        });

        view.billsButton.addActionListener(e -> {
            BillView billView = new BillView();
            new BillController(billView);
            billView.setVisible(true);
        });
    }
}
