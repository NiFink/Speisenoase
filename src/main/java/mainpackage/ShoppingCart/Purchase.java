package mainpackage.ShoppingCart;

public class Purchase {
    private final String name;
    private final double price;
    private int amount;
    private float total;

    public Purchase(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.total = total;
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
    public void setAmount(int newAmount) {
        this.amount = newAmount;
    }

    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
}
