package mainpackage.itempackage;

public class ItemFactory {
    public static Item createItem(ItemType type, int id){
        if(type == ItemType.GROCERY){
            return new Grocery().createGrocery(id);
        }
        return null;
    }
}
