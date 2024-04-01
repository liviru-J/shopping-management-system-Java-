import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class LoginGUI implements ActionListener {
    JFrame frame = new JFrame();
    JButton registerButton = new JButton("Register");
    JButton loginButton = new JButton("Login");
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("userID:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();
    HashMap<String, User> userData = new HashMap<>();
    ArrayList<Product> list;
    LoginGUI(ArrayList<Product> products) {
        list = products;

        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        messageLabel.setForeground(Color.red);


        usernameField.setBounds(125, 100, 200, 25);
        passwordField.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        registerButton.setBounds(225, 200, 100, 25);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(registerButton);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setTitle("Registering and Login");
        frame.setLocation(550, 200);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    LoginGUI(){}
    @Override
    public void actionPerformed(ActionEvent e) {
        String userID = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        if (e.getSource() == loginButton) {
            if (!userID.isEmpty() && !password.isEmpty()) {
                if (userData.containsKey(userID) && userData.get(userID).getPassword().equals(password)) {
                    // Successful login
                    showMessage("Login successful");
                    frame.dispose();


                    // open GUIFrame after successful login
                    GUIFrame frame1 = new GUIFrame(list);
                    frame1.setTitle("Westminster Shopping Centre");
                    frame1.setSize(800, 740);
                    frame1.setResizable(false);
                    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame1.setVisible(true);
                }
                else {
                    showMessage("Invalid username or password");
                    clearField();
                }
            }
            else {
                showMessage("Invalid username or password");
                clearField();
            }
        }

        if (e.getSource() == registerButton) {
            if (!userID.isEmpty() && !password.isEmpty()) {
                if (!userData.containsKey(userID)) {
                    User user = new User(userID, password);
                    userData.put(userID, user);
                    showMessage("Registration successful");
                    saveToFile();
                    frame.dispose();

                    // open GUIFrame after successful registration
                    GUIFrame frame1 = new GUIFrame(list);
                    frame1.setTitle("Westminster Shopping Centre");
                    frame1.setSize(800, 740);
                    frame1.setResizable(false);
                    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame1.setVisible(true);
                }
                else {
                    showMessage("Username already exists");
                    clearField();
                }
            }
            else {
                showMessage("Invalid username or password");
                clearField();
            }
        }
    }
    private void showMessage(String message) {
        messageLabel.setText(message);
        Timer timer = new Timer(3000, evt -> messageLabel.setText(""));
        timer.setRepeats(false);
        timer.start();
    }
    private void clearField() {
        usernameField.setText("");
        passwordField.setText("");
    }
    private void saveToFile() {
        try {
            // delete the file data before saving
            RandomAccessFile raf = new RandomAccessFile("userData.txt", "rw");
            raf.setLength(0);
            raf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fileOut = new FileOutputStream("userData.txt");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            // save the userData HashMap to the file
            objectOut.writeObject(userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadFromFile() {
//        try (FileInputStream fileIn = new FileInputStream("file.txt");
//             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
//
//            // Load the userData HashMap from the file
//            Object obj = objectIn.readObject();
//
//            if (obj instanceof HashMap<?, ?> rawMap) {
//
//                // Check if the map contains User objects
//                if (rawMap.values().stream().allMatch(o -> o instanceof User)) {
//                    userData = castHashMap(rawMap);
//                    System.out.println("Successfully Loaded The Data!");
//                } else {
//                    System.out.println("Invalid data format in file.");
//                }
//            }
//
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    @SuppressWarnings("unchecked")
//    private static <K, V> HashMap<K, V> castHashMap(HashMap<?, ?> map) {
//        return (HashMap<K, V>) map;
   }
}
