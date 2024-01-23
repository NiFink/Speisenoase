package mainpackage.ShoppingCart;

import mainpackage.ControllerMainPage;
import mainpackage.itempackage.ItemManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ShoppingCart {

    private List<Purchase> purchaseList = new ArrayList<>();
    private static ShoppingCart instance;
    private static final Logger log = LogManager.getLogger(ItemManager.class);

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        log.debug("ShoppingCart instance is being returned");
        return instance;
    }

    public void addPurchase(Purchase purchase) {
        purchaseList.add(purchase);
        log.info("Added " + purchase.getAmount() + " " + purchase.getName() + " to the shopping cart");
    }

    public void updateAmount(String purchaseStr, double price,  int amountToAdd) {
        if(!purchaseList.isEmpty()){
            for (Purchase purchase : purchaseList) {
                if (purchase.getName().equals(purchaseStr)) {
                    purchase.setAmount(purchase.getAmount() + amountToAdd);
                }
            }
        } else {
            purchaseList.add(new Purchase(purchaseStr, price, amountToAdd));
        }
        log.info("Updated amount of " + purchaseStr + " in the shopping cart");
    }

    public int getTotalAmount(){
        int total = 0;
        for(Purchase purchase : purchaseList){
            total += purchase.getAmount();
        }
        return total;
    }

    public boolean hasPurchase(String name){
        for(Purchase purchase : purchaseList){
            if(purchase.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public List<Purchase> getPurchaseList() {
        return new ArrayList<>(purchaseList);
    }

    public double getTotal() {
        double total = 0;
        for (Purchase purchase : purchaseList) {
            total += purchase.getPrice() * purchase.getAmount();
        }
        log.debug("Total of purchases is: " + total);
        return total;
    }

    public void clearShoppingCart(){
        purchaseList = new ArrayList<>();
    }

    public  void deleteShoppingcartElemnent(Purchase purchase){
        purchaseList.remove(purchase);
    }
}
