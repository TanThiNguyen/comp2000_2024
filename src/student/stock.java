package student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stock {
    public List<Brief> stock;
    private List<Integer> quantities;
    
    public Stock(List<Brief> startingStock, List<Integer> qtys) {
        stock = startingStock;
        quantities = qtys;
    }

    public List<Brief> getStockList() {
        return stock;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public int getQuantityOf(String name) {
        Optional<Integer> index = indexOf(name);
        if(index.isPresent()){
            return quantities.get(index.get());
        }
        return 0;
    }

    public void setQuantity(String name, int quantity) {
        Optional<Integer> index = indexOf(name);
        if (index.isPresent()) {
            quantities.set(index.get(), quantity);
        }
    }

    protected Optional<Integer> indexOf(String name) {
        for (int i = 0; i < stock.size(); i++) {
            if (stock.get(i).getName().get().equals(name)) {
                return Optional.of(i);
            }
        }
        return Optional.of(0);
    }
    

    List<TableRowModel> toTableRows() {
        List<TableRowModel> result = new ArrayList<>();

        for (int i = 0; i < stock.size(); i++) {
            Brief item = stock.get(i);
            String name = item.getName().get();
            String quantity = quantities.get(i) + "";
            String path = item.getDataPath().get();
            result.add(new TableRowModel(name, quantity, path));
        }

        return result;
    }
}
