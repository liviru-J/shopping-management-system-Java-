import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUIFrame extends JFrame implements ActionListener {
    JTableHeader tableHeader;
    DefaultTableModel tableModel;
    JScrollPane jScrollPane;
    JTable jTable;
    JPanel panel1, panel2, panel3;
    JComboBox comboBox;
    JLabel label, label1;
    JButton cart, addCart;
    ArrayList<Product> all, electronic, clothing, array;
    Map<Product, Integer> shopMap = new HashMap<>();
    int add = 0;
    int sorted = 1;
    int state1;
    GUIFrame(ArrayList<Product> arrayList) {
        electronic = new ArrayList<>();
        clothing = new ArrayList<>();
        all = arrayList;
        for (Product product: arrayList) {
            if (product.getProductId().startsWith("A")) {electronic.add(product);}
            else {clothing.add(product);}
        }
        // create table
        tableCreate(arrayList);

        // when user selects row, display it below the table
        array = new ArrayList<>(all);
        showSelectedDetails();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            tableModel.setRowCount(0);
            if (comboBox.getSelectedItem()=="Electronic") {
                array = new ArrayList<>(electronic);
                // when user selects row, display it below the table
                showSelectedDetails();
                for (Product product: all) {
                    if (product.getCategory().equals("Electronic")) {
                        String productInfo = ((Electronic) product).getWarrantyPeriod() + " Months Warranty, "+ "From "+((Electronic) product).getBrand() + " Brand";
                        Object[] rowData = {product.getProductId(), product.getProductName(), product.getCategory(), product.getPrice(), productInfo};
                        tableModel.addRow(rowData);
                    }
                }
            }
            else if (comboBox.getSelectedItem()=="Clothing") {
                array = new ArrayList<>(clothing);
                // when user selects row, display it below the table
                showSelectedDetails();
                for (Product product: all) {
                    if (product.getCategory().equals("Clothing")) {
                        String productInfo = ((Clothing) product).getSize() + " Size, " + "Color is " + ((Clothing) product).getColour();
                        Object[] rowData = {product.getProductId(), product.getProductName(), product.getCategory(), product.getPrice(), productInfo};
                        tableModel.addRow(rowData);
                    }
                }
            }
            else if (comboBox.getSelectedItem()=="All") {
                array = new ArrayList<>(all);
                // when user selects row, display it below the table
                showSelectedDetails();
                for (Product product: all) {
                    String productInfo = null;
                    if(product instanceof Electronic) {
                        productInfo = ((Electronic) product).getWarrantyPeriod() + " Months Warranty, "+ "From "+((Electronic) product).getBrand() + " Brand";
                    } else if (product instanceof  Clothing) {
                        productInfo = ((Clothing) product).getSize() + " Size, " + "Color is " + ((Clothing) product).getColour();
                    }
                    Object[] rowData = {product.getProductId(), product.getProductName(), product.getCategory(), product.getPrice(), productInfo};
                    tableModel.addRow(rowData);
                }
            }
        }
        ShoppingCart shoppingCart = new ShoppingCart(shopMap);
        if (e.getSource() == cart) {
            cart.setEnabled(false);
            shoppingCart.setVisible(true);
            shoppingCart.setTitle("Shopping Cart");
            shoppingCart.setSize(740, 740);
            shoppingCart.setResizable(false);
            shoppingCart.setLocation(790, 0);
            shoppingCart.showTotal();
            shoppingCart.showCount();
            shoppingCart.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {cart.setEnabled(true);}
            });
        }
        if (e.getSource() == addCart) {
            if (add == 1) {
                if (sorted == 1) {
                    // If the table is sorted, get the selected row from the view
                    int selectedRow = jTable.getSelectedRow();
                    state1 = jTable.convertRowIndexToModel(selectedRow);
                }

                shoppingCart.selected(array.get(state1).getProductId());
                Product product = array.get(state1);
                if (shopMap.containsKey(product)) {
                    shopMap.put(product, shopMap.get(product) + 1);
                } else {
                    shopMap.put(product, 1);
                }
            }
        }
    }
    public void showSelectedDetails() {
        jTable.getSelectionModel().addListSelectionListener(e -> {
            addCart.setEnabled(true);
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow != -1) {
                    int modelRow = jTable.convertRowIndexToModel(selectedRow);
                    selected(modelRow);
                    for (Product product : array) {
                        if (array.indexOf(product) == modelRow) {
                            if (product instanceof Electronic) {
                                String text = "<html><body style='width: 538px;font-size: 14px;font-family: \"Times New Roman\"'>" +
                                        "<br>" +"Selected Product - Details"+ "<br>" + "<br>" +
                                        "Product ID : " +product.getProductId() + "<br>" +
                                        "Category : " + product.getCategory() + "<br>" +
                                        "Product Name : " + product.getProductName() + "<br>" +
                                        "Price(£) : " + product.getPrice() + "<br>" +
                                        "Brand Name : " + ((Electronic) product).getBrand() + "<br>" +
                                        "Warranty Period(Months) : " + ((Electronic) product).getWarrantyPeriod() + "<br>" +
                                        "Items Available : " + product.getNumOfAvailableItems() +
                                        "</body></html>";
                                label1.setText(text);
                            }
                            else if(product instanceof Clothing) {
                                String text = "<html><body style='width: 538px;font-size: 14px;font-family: \"Times New Roman\"'>" +
                                        "<br>" +"Selected Product - Details"+ "<br>" + "<br>" +
                                        "Product ID : " +product.getProductId() + "<br>" +
                                        "Category : " + product.getCategory() + "<br>" +
                                        "Product Name : " + product.getProductName() + "<br>" +
                                        "Price(£) : " + product.getPrice() + "<br>" +
                                        "Size : " + ((Clothing) product).getSize() + "<br>" +
                                        "Colour : " + ((Clothing) product).getColour() + "<br>" +
                                        "Items Available : " + product.getNumOfAvailableItems() +
                                        "</body></html>";
                                label1.setText(text);
                            }
                        }
                    }
                }
            }
        });
    }
    private void selected(int state) {
        add = 1;
        state1 = state;
    }
    private void tableCreate(ArrayList<Product> arrayList1) {
        String [] productCategory= {"All", "Electronic", "Clothing"};
        comboBox = new JComboBox<>(productCategory);
        comboBox.setFont(new Font("Times New Roman", Font.BOLD,15));
        comboBox.setBorder(new MatteBorder(45,10,8,10, new Color(238, 238,238)));

        cart = new JButton("Shopping Cart");
        cart.setFont(new Font("Times New Roman", Font.BOLD,18));

        addCart = new JButton("Add to Shopping Cart");
        addCart.setFont(new Font("Times New Roman", Font.BOLD,18));
        addCart.setEnabled(false);

        label = new JLabel();
        label.setText("Select Product Category :");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setBorder(new MatteBorder(45,10,8,10, new Color(238, 238,238)));

        label1 = new JLabel();

        tableModel = new DefaultTableModel(new Object[] {"Product ID", "Name", "Category", "Price(£)", "Info"}, 0);

        for (Product product: arrayList1) {
            String productInfo = null;
            if(product instanceof Electronic) {
                productInfo = ((Electronic) product).getWarrantyPeriod() + " Months Warranty, "+ "From "+((Electronic) product).getBrand() + " Brand";
            } else if (product instanceof  Clothing) {
                productInfo = ((Clothing) product).getSize() + " Size, " + "Color is " + ((Clothing) product).getColour();
            }
            Object[] rowData = {product.getProductId(), product.getProductName(), product.getCategory(), product.getPrice(), productInfo};
            tableModel.addRow(rowData);
        }

        // make the cells un editable
        jTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable.getModel());
        jTable.setRowSorter(sorter);
        // table header styling
        tableHeader = jTable.getTableHeader();
        tableHeader.setBackground(Color.DARK_GRAY);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Times New Roman",Font.BOLD,17));

        //info column adjusting
        jTable.getColumnModel().getColumn(4).setPreferredWidth(200);
        jTable.setFont(new Font("Times New Roman",Font.BOLD,17));

        jScrollPane = new JScrollPane(jTable);
        // adjust table width and height
        jScrollPane.setPreferredSize(new Dimension(710, 280));
        jScrollPane.setBorder(new MatteBorder(50,30,30,30, new Color(120, 126,131)));

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel1.add(cart);
        panel1.setBackground(new Color(120, 126, 131));

        panel2 = new JPanel();
        panel2.add(label);
        panel2.add(comboBox);
        panel2.add(jScrollPane);
        panel2.add(label1);


        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.add(addCart);
        panel3.setBackground(new Color(120, 126, 131));

        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);

        cart.addActionListener(this);
        addCart.addActionListener(this);
        comboBox.addActionListener(this);
    }
}
