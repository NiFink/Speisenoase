package mainpackage.itempackage;

public interface Item {
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    double getPrice();
    void setPrice(double price);
    String getCategory();
    void setCategory(String category);
    long getAvailable();
    void setAvailable(long available);
    boolean getFavourite();
    void setFavourite(boolean favourite);
    String getDescription();
    void setDescription(String description);
    String toString();
}
