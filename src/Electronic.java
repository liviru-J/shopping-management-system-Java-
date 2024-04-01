public class Electronic extends Product{
    private String brand;
    private int warrantyPeriod;
    Electronic(String productId, String productName, double price, String brand, int warrantyPeriod, int numOfAvailableItems) {
        super(productId, productName, price, numOfAvailableItems);
        setBrand(brand);
        setWarrantyPeriod(warrantyPeriod);
        String category = "Electronic";
        setCategory(category);
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    public String getBrand() {
        return brand;
    }
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    @Override
    public String toString() {
        return  " Product Id : " + getProductId() + "\n"+
                " category : "+ getCategory() + "\n"+
                " Product Name : " + getProductName() + "\n"+
                " Price : " + getPrice() + "\n"+
                " Brand : " + getBrand() + "\n"+
                " Warranty Period (Months) : " + getWarrantyPeriod() + "\n"+
                " Stock Available : " + getNumOfAvailableItems() + "\n\n";
    }
}