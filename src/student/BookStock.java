package student;

import java.util.ArrayList;
import java.util.List;

public class BookStock {
    private List<BookBrief> stock;
    private List<Integer> quantities;
    
    public BookStock(List<BookBrief> startingStock, List<Integer> qtys) {
        stock = startingStock;
        quantities = qtys;
    }

    public List<BookBrief> getStockList() {
        return stock;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public int getQuantityOf(String name) {
        int index = indexOf(name);
        return index >= 0 ? quantities.get(index) : 0;
    }

    public void setQuantity(String name, int quantity) {
        int index = indexOf(name);
        if (index >= 0) {
            quantities.set(index, quantity);
        }
    }
// add get price
    public double getPrice(String name) {
        int index = indexOf(name);
        if (index >= 0) {
            return stock.get(index).getPrice(); 
        }
        return 0; 
    }

    private int indexOf(String name) {
        Integer index = null;
        for (int i = 0; i < stock.size() && index == null; i++) {
            if (stock.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }

    List<TableRowModel> toTableRows() {
        List<TableRowModel> result = new ArrayList<>();

        for (int i = 0; i < stock.size(); i++) {
            BookBrief item = stock.get(i);
            String name = item.getName();
            String quantity = quantities.get(i) + "";
            String path = item.getDataPath();
            result.add(new TableRowModel(name, quantity, path));
        }

        return result;
    }

}
