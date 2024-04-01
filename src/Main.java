public class Main {
    public static void main(String[] args) {
        WestminsterShoppingManager wManager = new WestminsterShoppingManager();
        LoginGUI loginGUI = new LoginGUI();
        System.out.println("\nWestminster Shopping Centre");
        wManager.load();
        loginGUI.loadFromFile();
        wManager.functions();
    }
}

