import java.io.Serializable;
public abstract class Product implements Serializable {
    private String productId;
    private String productName;
    private int numOfAvailableItems;
    private double price;
    private String category;
    Product(String productId, String productName, double price, int numOfAvailableItems) {
        setProductId(productId);
        setProductName(productName);
        setPrice(price);
        setNumOfAvailableItems(numOfAvailableItems);
    }
    private void setProductId(String productId) {
        this.productId = productId;
    }
    private void setProductName(String productName) {
        this.productName = productName;
    }
    private void setPrice(double price) {
        this.price = price;
    }
    public void setNumOfAvailableItems(int numOfAvailableItems) {this.numOfAvailableItems = numOfAvailableItems;}
    void setCategory(String category) {
        this.category = category;
    }
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public int getNumOfAvailableItems() {
        return numOfAvailableItems;
    }
    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }
}
