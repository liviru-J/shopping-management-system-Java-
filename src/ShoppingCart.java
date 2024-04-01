import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Map;

public class ShoppingCart extends JFrame {
    JTableHeader tableHeader;
    DefaultTableModel tableModel;
    JScrollPane jScrollPane;
    JTable jTable;
    JPanel panel1;
    JLabel label;
    int count = 1;
    String selectedID = "";
    double firstDiscount, threeItemDiscount, lastTotal;
    private final Map<Product, Integer> shopMap;
    ShoppingCart(Map<Product, Integer> shopMap) {
        this.shopMap = shopMap;
        createTable();
    }
    private void createTable() {
        tableModel = new DefaultTableModel(new Object[]{"Product", "Quantity", "Price"}, 0);
        jTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Map.Entry<Product, Integer> entry : shopMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            String column1;

            if (product instanceof Electronic) {
                column1 = product.getProductId() + ", " +
                        product.getProductName() + ", " +
                        ((Electronic) product).getBrand() + " , " + ((Electronic) product).getWarrantyPeriod();
            } else if (product instanceof Clothing) {
                column1 = product.getProductId() + ", " +
                        product.getProductName() + ", " +
                        ((Clothing) product).getSize() + " , " + ((Clothing) product).getColour();
            } else {
                // Handle other product types if needed
                column1 = product.getProductId() + ", " + product.getProductName();
            }

            double column3 = product.getPrice();

            Object[] rowData = {column1, quantity, column3};
            tableModel.addRow(rowData);
        }

        // table header styling
        tableHeader = jTable.getTableHeader();
        tableHeader.setBackground(Color.DARK_GRAY);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Times New Roman",Font.BOLD,17));

        jTable.setFont(new Font("Times New Roman",Font.BOLD,17));

        jScrollPane = new JScrollPane(jTable);
        // adjust table width and height
        jScrollPane.setPreferredSize(new Dimension(710, 280));
        jScrollPane.setBorder(new MatteBorder(50,30,30,30, new Color(120, 126,131)));

        // jLabel
        label = new JLabel();
        String label1 = "<html><body style='width: 538px;font-size: 14px;text-align: right;margin-right:50px;margin-top:40px;font-family: \"Times New Roman\"'>" +
                "Total : " + "&nbsp;" + showTotal() + "<br>" + "<br>" +
                "First Purchase Discount (10%) : " + "&nbsp;" + discount1() + "<br>" + "<br>" +
                "Three Items in Same Category Discount (20%) : " + "&nbsp;" + threeDiscount() + "<br>" + "<br>" +
                "Final Total : " + "&nbsp;" + finalTotal() +
                "</body></html>";

        label.setText(label1);

        panel1 = new JPanel();
        panel1.add(jScrollPane);
        panel1.add(label);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

        //add panel to the frame
        this.add(panel1, BorderLayout.CENTER);
    }
    public double showTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : shopMap.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
    private double discount1() {
        firstDiscount = showTotal() / -10;
        return firstDiscount;
    }
    private double threeDiscount() {
        threeItemDiscount = showTotal() / -5;
        return threeItemDiscount;
    }
    private double finalTotal() {
        lastTotal = showTotal() + discount1() + threeDiscount();
        return lastTotal;
    }
    public void showCount() {
        for (Map.Entry<Product, Integer> entry1 : shopMap.entrySet()) {
            for (Map.Entry<Product, Integer> entry2 : shopMap.entrySet()) {
                if (entry1.getKey().getProductId().equals(entry2.getKey().getProductId()) && !entry1.equals(entry2)) {
                    count++;
                }
            }
        }
    }
    public void selected(String select1){
        selectedID = select1;
    }
}
