package View;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class QueryDisplay implements DisplayResults {
    @Override
    public void display(ResultSet rs) {
        // Set up JFrame
        JFrame frame = new JFrame("Query Results");
        frame.setSize(800, 600);

        try {
            // Get the column names
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }

            // Get the data
            Object[][] data = new Object[1000][columnCount];
            int row = 0;
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    data[row][i - 1] = rs.getObject(i);
                }
                row++;
            }

            // Create a table model
            DefaultTableModel model = new DefaultTableModel(data, columnNames);

            // Create a table
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }

}
