package Utils;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Utility class used for dynamically populating a JTable model
 * using reflection to extract fields and values from a generic object list
 * This helps avoid writing repetitive code when displaying different model objects in a table
 */
public class TableGenerator {
    /**
     * Populates a given DefaultTableModel with data extracted via reflection
     * from a list of objects of type T
     * @param list a list T of objects to display in the table
     * @param tableModel the DefaultTableModel to populate with data
     * @param <T> the type of the objects in the list
     */
    public static <T> void populateTable(List<T> list, DefaultTableModel tableModel) {
        if(list == null || list.isEmpty()) return;

        Class<?> clazz = list.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        String[] columnNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName();
        }
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(columnNames);

        for(T obj : list) {
            Object[] row = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                try{
                    row[i] = fields[i].get(obj);
                }catch(IllegalAccessException e){
                    row[i] = "N/A";
                }
            }
            tableModel.addRow(row);
        }
    }
}
