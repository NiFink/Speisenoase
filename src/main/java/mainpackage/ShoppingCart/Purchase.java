package mainpackage.ShoppingCart;

public class Purchase {
    private final String name;
    private final double price;
    private final int amount;

    public Purchase(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
