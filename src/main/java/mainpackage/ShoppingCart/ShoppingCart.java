package mainpackage.ShoppingCart;

import mainpackage.itempackage.ItemManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ShoppingCart {

    private ArrayList<Purchase> purchaseList;
    private static ShoppingCart instance;
    private static final Logger log = LogManager.getLogger(ItemManager.class);
    public static ShoppingCart getInstance(){
        if(instance == null){
            instance = new ShoppingCart();
        }
        log.debug("ShoppingCart instance is being returned");
        return instance;
    }

    public void addPurchase(Purchase purchase){
        purchaseList.add(purchase);
    }

    public List<Purchase> getPurchaseList(){
        return new ArrayList<>(purchaseList);
    }

    public double getTotal(){
        double total = 0;
        for(Purchase purchase : purchaseList){
            total += purchase.getPrice();
        }
        return total;
    }
}
