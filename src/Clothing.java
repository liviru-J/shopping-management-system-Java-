public class Clothing extends Product{
    private String size;
    private String colour;
    Clothing(String productId, String productName, double price, String size, String colour, int numOfAvailableItems) {
        super(productId, productName,price, numOfAvailableItems);
        setSize(size);
        setColour(colour);
        String category = "Clothing";
        setCategory(category);
    }
    public void setSize(String size) {
        this.size = size;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public String getSize() {
        return size;
    }
    public String getColour() {
        return colour;
    }
    @Override
    public String toString() {
        return  " Product Id : " + getProductId()+ "\n" +
                " category : "+ getCategory() +"\n" +
                " Product Name : " + getProductName() + "\n"+
                " Price : " + getPrice() + "\n"+
                " Size : " + getSize() + "\n"+
                " Colour : " + getColour() + "\n"+
                " Stock Available : " + getNumOfAvailableItems()+ "\n\n";
    }
}
