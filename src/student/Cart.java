package student;

import java.util.List;
import java.util.ArrayList;

public class Cart {
    private List<ItemInterface> items;       // Single list for both books and products
    private List<Integer> itemQuantities;    // Corresponding list for quantities

    public Cart() {
        items = new ArrayList<>();
        itemQuantities = new ArrayList<>();
    }

    public void clear() {
        items.clear();
        itemQuantities.clear();
    }

    public void add(ItemInterface item) {
        int itemIndex = items.indexOf(item);
        if (itemIndex < 0) {
            items.add(item);
            itemQuantities.add(1);
        } else {
            itemQuantities.set(itemIndex, itemQuantities.get(itemIndex) + 1);
        }
    }

    public boolean buy(Account user, BookStock from) {
        double totalCost = 0;

        // Calculate total cost and check stock availability
        for (int i = 0; i < items.size(); i++) {
            ItemInterface item = items.get(i);
            Integer orderQuantity = itemQuantities.get(i);
            totalCost += item.getPrice() * orderQuantity;

            int stockQuantity = from.getQuantityOf(item.getName());
            if (stockQuantity == 0 || stockQuantity < orderQuantity) {
                System.err.println("There is not enough " + item.getName() + " in stock for purchase");
                return false;
            }
        }

        if (totalCost > user.credit) {
            System.err.println("User does not have enough credit for purchase");
            return false;
        }

        // Deduct total cost from user's credit
        user.credit -= totalCost;

        // Update stock quantities after purchase
        for (int i = 0; i < items.size(); i++) {
            ItemInterface item = items.get(i);
            Integer orderQuantity = itemQuantities.get(i);
            int newQuantity = from.getQuantityOf(item.getName()) - orderQuantity;
            from.setQuantity(item.getName(), newQuantity);
        }

        return true;
    }

    public List<TableRowModel> toTableRows() {
        List<TableRowModel> result = new ArrayList<>(items.size());

        for (int i = 0; i < items.size(); i++) {
            ItemInterface item = items.get(i);
            Integer quantity = itemQuantities.get(i);
            result.add(new TableRowModel(item.getName(), item.getPrice() + "", quantity + ""));
        }

        return result;
    }
}
