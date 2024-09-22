package student;

import java.util.ArrayList;
import java.util.List;

public class ItemStock
{
    private List<ItemBrief> stock;
    private List<Integer> quantities;

    public ItemStock(List<ItemBrief> startingStock, List<Integer> qtys)
    {
        stock = startingStock;
        quantities = qtys;
    }

    public List<ItemBrief> getStockList() { return stock; }

    public List<Integer> getQuantities() { return quantities; }

    public int getQuantityOf(String name)
    {
        int index = indexOf(name);
        if (index == -1) 
        {
            return 0;
            
        }
        return quantities.get(index);
    }

    public void setQuantity(String name, int quantity)
    {
        int index = indexOf(name);
        if (index == -1) 
        {
            return;
            
        }
        quantities.set(index, quantity);
    }

    private int indexOf(String name)
    {
        for (int i = 0; i < stock.size(); i++) {
            if (stock.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    List<TableRowModel> toTableRows()
    {
        List<TableRowModel> result = new ArrayList<>();

        for (int i = 0; i < stock.size(); i++) {
            ItemBrief item = stock.get(i);
            String name = item.getName();
            String quantity = quantities.get(i) + "";
            String path = item.getDataPath();
            result.add(new TableRowModel(name, quantity, path));
        }

        return result;
    }
}
