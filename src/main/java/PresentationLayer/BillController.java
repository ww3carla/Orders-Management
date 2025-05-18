package PresentationLayer;

import DataAccess.BillDAO;
import Model.Bill;
import Utils.TableGenerator;

import java.util.List;

/**
 * Controller class for displaying bills in the BillView table
 * Fetches all bills using BillDAO and shows them using reflection
 */
public class BillController {
    private BillView view;
    private BillDAO billDAO;

    /**
     * Constructs the controller and loads the bills
     * @param view the GUI view for displaying bills
     */
    public BillController(BillView view) {
        this.view = view;
        this.billDAO = new BillDAO();

        initListeners();
    }

    /**
     * Sets up the refresh button to reload bills when clicked
     */
    private void initListeners(){
        view.refreshButton.addActionListener(e -> loadBills());
    }

    /**
     * Uses reflection to populate the JTable with all bill entries from database
     */
    private void loadBills(){
        List<Bill> bills = billDAO.findAll();
        TableGenerator.populateTable(bills, view.tableModel);
    }
}
