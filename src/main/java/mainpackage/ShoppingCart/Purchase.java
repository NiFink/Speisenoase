package mainpackage.ShoppingCart;

public class Purchase {
    private final String name;
    private final String price;
    private final int amount;

    public Purchase(String name, String price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
