import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class WestminsterShoppingManager implements ShoppingManager {
    ArrayList<Product> productList = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    int itemsCount = 50;
    void optionList() {
        System.out.print("""
                                    
                                    1. Add a New Product
                                    2. Delete a Product
                                    3. Update Stock
                                    4. Print The List Of Products
                                    5. Save In a File
                                    6. Open The GUI
                                    7. Exit The Program
                                    
                                    Select Option : \s""");
    }
    void functions() {
        while (true) try {
            optionList();
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1 -> addingProducts();
                case 2 -> deletingProducts();
                case 3 -> updateStock();
                case 4 -> printProducts();
                case 5 -> saveToFile();
                case 6 -> new LoginGUI(productList);
                case 7 -> {
                    System.out.println("Closing The Program..");
                    System.exit(0);
                }
                default -> System.out.println("Error! Enter Valid Option Number!");
            }
        }
        catch (Exception e) {
            System.out.println("Error! Enter Valid Option Number!");
            input.next();
        }
    }
    public void addingProducts() {
        if (itemsCount == 0) {
            System.out.println("Error! You Can Add only 50 Products!");
        }
        else {
            //Get inputs from the manager
            System.out.println("\u001B[38;5;208m"+"Note: when you adding a product ID, Remind To Start with \"A\" for Electronics and \"B\"for Clothing And Should Contain 5 Chars"+"\u001B[0m");
            String productId;
            while (true) {
                System.out.print("\nEnter Product ID : ");
                productId = input.nextLine();
                if (!Character.isUpperCase(productId.charAt(0))) {productId = Character.toUpperCase(productId.charAt(0)) + productId.substring(1);}
                if ((productId.startsWith("A") || productId.startsWith("B")) && productId.length() == 5) {
                    int exist = 0;
                    for (Product product : productList) {
                        if (product.getProductId().equals(productId)) {
                            System.out.println("This Product Is Already Exist!");
                            exist = 1;
                        }
                    }
                    if (exist == 0) {break;}
                }
                else {System.out.println("Error! Enter Valid Product ID!(Input should have 5 chars and should start with \"A\" Or \"B\")");}
            }
            if (productId.startsWith("A")) {
                String brandName, productName;
                int periodOfWarranty, stockQuantity;
                double price;
                while (true) {
                    System.out.print("Enter The Product Name : ");
                    productName = input.nextLine();
                    if (productName.matches("[a-zA-Z]+")) {break;}
                    else {System.out.println("Error! Enter Valid Name For Product!");}
                }
                while (true) try {
                    System.out.print("Enter The Price of The Product (£) : ");
                    price = input.nextDouble();
                    input.nextLine();
                    break;
                }
                catch (Exception e) {
                    System.out.println("Error! Enter Valid Price For Product!");
                    input.next();
                }
                while (true){
                    System.out.print("Enter The Brand Name Of The Product : ");
                    brandName = input.nextLine();
                    if (brandName.matches("[a-zA-Z]+")){break;}
                    else {System.out.println("Error! Enter Valid Brand Name For Product!");}
                }
                while (true) try {
                    System.out.print("Enter The Warranty Period Of The Product : ");
                    periodOfWarranty = input.nextInt();
                    input.nextLine();
                    break;
                }
                catch (Exception e) {
                    System.out.println("Error! Enter Valid Warranty Period For Product!");
                    input.next();
                }
                while (true) try {
                    System.out.print("Enter The Stock Quantity Of The Product : ");
                    stockQuantity = input.nextInt();
                    input.nextLine();
                    if (stockQuantity >= 1 && stockQuantity <= 50) {break;}
                    else {System.out.println("Error! Enter Quantity Between 0-51!");}
                }
                catch (Exception e) {
                    System.out.println("Error! Enter Valid Stock Quantity For Product!");
                    input.next();
                }
                //create electronic object

                Product electricProduct = new Electronic(productId, productName, price, brandName, periodOfWarranty, stockQuantity);
                productList.add(electricProduct);
            }
            else {
                String productName, size, colour;
                double price;
                int stockQuantity;
                while (true) {
                    System.out.print("Enter The Product Name : ");
                    productName = input.nextLine();
                    if (productName.matches("[a-zA-Z]+")) {break;}
                    else {System.out.println("Error! Enter Valid Name For Product!");}
                }
                while (true) try {
                    System.out.print("Enter The Price of The Product (£) : ");
                    price = input.nextDouble();
                    input.nextLine();
                    break;
                }
                catch (Exception e) {
                    System.out.println("Error! Enter Valid Price For Product!");
                    input.next();
                }
                while (true) {
                    System.out.print("Enter The Size : ");
                    size = input.nextLine();
                    if (size.matches("[a-zA-Z]+")) {break;}
                    else {System.out.println("Error! Enter Valid size For Product!");}
                }
                while (true) {
                    System.out.print("Enter The Colour : ");
                    colour = input.nextLine();
                    if (colour.matches("[a-zA-Z]+")) {break;}
                    else {System.out.println("Error! Enter Valid colour For Product!");}
                }
                while (true) try {
                    System.out.print("Enter The Stock Quantity Of The Product : ");
                    stockQuantity = input.nextInt();
                    input.nextLine();
                    if (stockQuantity >= 1 && stockQuantity <= 50) {break;}
                    else {System.out.println("Error! Enter Quantity Between 0-51!");}
                }
                catch (Exception e) {
                    System.out.println("Error! Enter Valid Stock Quantity For Product!");
                    input.next();
                }


                //create clothing object
                Product clothingProduct = new Clothing(productId, productName, price, size, colour, stockQuantity);
                productList.add(clothingProduct);
            }
            itemsCount--;
            System.out.println("Product Added Successfully!");
        }
    }
    public void deletingProducts() {
        String productId, confirm;
        Product selected = null;
        while (true) {
            System.out.print("Enter Product ID : ");
            productId = input.nextLine();
            if (!Character.isUpperCase(productId.charAt(0))) {productId = Character.toUpperCase(productId.charAt(0)) + productId.substring(1);}
            if ((productId.startsWith("A") || productId.startsWith("B")) && productId.length() <= 5) {
                int exist = 0;
                for (Product product : productList) {
                    if (product.getProductId().equals(productId)) {
                        selected = product;
                        exist = 1;
                        break;
                    }
                }
                if (exist == 0) {System.out.println("Product ID, \"" + productId + "\" Not Found! Try Again!");}
                else {
                    System.out.println("\n" + selected);
                    break;
                }
            }
            else {System.out.println("Error! Enter Valid Product ID!(Input Should have 5 chars and should start with \"A\" Or \"B\")");}
        }
        while (true) {
            System.out.print("Are You Confirm To Delete This Product (y/n)? ");
            confirm = input.nextLine();
            if (confirm.equals("y") || confirm.equals("n")) {break;}
            else {
                System.out.println("Enter \"y\"(yes) For Delete Or \"n\"(no) For Return To The Option Menu!");
            }
        }
        if (confirm.equals("y")) {
            itemsCount ++; //added vacant space to the no. of products
            productList.remove(selected); //remove selected product from arraylist
            System.out.println("Product Removed Successfully!");
        }
        else {System.out.println("Product Not Removed!");}
    }
    public void updateStock() {
        String productId, confirm;
        Product selected = null;
        int adding, deleting;
        while (true) {
            System.out.print("Enter Product ID : ");
            productId = input.nextLine();
            if (!Character.isUpperCase(productId.charAt(0))) {productId = Character.toUpperCase(productId.charAt(0)) + productId.substring(1);}
            if (productId.startsWith("A") || productId.startsWith("B")) {
                int exist = 0;
                for (Product product : productList) {
                    if (product.getProductId().equals(productId)) {
                        selected = product;
                        exist = 1;
                        break;
                    }
                }
                if (exist == 0) {System.out.println("Product ID, \"" + productId + "\" Seems Not Added yet! Try Again!");}
                else {
                    System.out.println("\n"+selected);
                    break;
                }
            }
            else {System.out.println("Error! Enter Valid Product ID!");}
        }
        while (true) {
            System.out.print("Do you need to add or delete stock (add/del)? ");
            confirm = input.nextLine();
            if (confirm.equals("add") || confirm.equals("del")) {break;}
            else {
                System.out.println("Enter \"add\" For Add Stock Or \"del\" For Delete Existing Stock!");
            }
        }
        if (confirm.equals("add")){
            while (true) try {
                System.out.print("Enter New Stock Quantity : ");
                adding = input.nextInt();
                input.nextLine();
                adding = selected.getNumOfAvailableItems() + adding;
                if (adding > 50) {System.out.println("Cant Add "+adding+" items to the Stock (Max 50)!");}
                else {
                    selected.setNumOfAvailableItems(adding);
                    System.out.println("Stock Added Successfully!");
                    break;
                }
            }
            catch (Exception e) {
                System.out.println("Error! Enter Valid Stock Quantity (Total Should less than 50) For Product! Try Again!");
                input.next();
            }
        }
        else {
            while (true) try {
                System.out.print("Enter Quantity Of Stocks To Delete : ");
                deleting = input.nextInt();
                input.nextLine();
                deleting = selected.getNumOfAvailableItems() - deleting;
                if (deleting < 0) {
                    System.out.println("Error! Stock Can't Be Less Than \"0\" Try Again!");
                }
                else {
                    selected.setNumOfAvailableItems(deleting);
                    System.out.println("Stock Updated Successfully!");
                    break;
                }
            }
            catch (Exception e) {
                System.out.println("Error! Enter Valid Stock Quantity (Minimum 0) For Product! Try Again!");
                input.next();
            }
        }
    }
    public void printProducts() {
        if (productList.isEmpty()) {System.out.println("No Products To Display!");}
        else {
            ArrayList <Product> sortList = new ArrayList<>(productList);
            for (int i=0; i<sortList.size(); i++) {
                for (int j = i+1; j<sortList.size(); j++) {
                    if (sortList.get(i).getProductId().compareTo(sortList.get(j).getProductId())>0){
                        Product temp = sortList.get(i);
                        sortList.set(i, sortList.get(j));
                        sortList.set(j, temp);
                    }
                }
            }
            for (Product products : sortList) {
                System.out.println(products);
            }
        }
    }
    public void saveToFile() {
        try {
            // delete the file data before saving
            RandomAccessFile raf = new RandomAccessFile("file.txt", "rw");
            raf.setLength(0);
            raf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("file.txt", true);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            try {
                for (Product array : productList) {
                    objectOut.writeObject(array);
                }
                System.out.println("Successfully Saved The Data!");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void load() {
        try {
            FileInputStream fin = new FileInputStream("file.txt");
            ObjectInputStream objIn = new ObjectInputStream(fin);
            while (true) {
                try {
                    Product p = (Product) objIn.readObject();
                    // Adding objects from file to the arraylist
                    productList.add(p);
                } catch (EOFException e) {
                    break;
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println(" ");
        }
    }
}


