package userinterface;

import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import student.Account;
import student.ItemInterface;

public class ProductPage extends Page {

    public static final String ROUTE_NAME = "product-page";

    private ItemInterface display;
    private Consumer<ItemInterface> addFn;

    public ProductPage(UIService uiservice, Account account) {
        super(uiservice, account, ROUTE_NAME);
    }

    public void setAddToCartAction(Consumer<ItemInterface> addToCartFn) {
        addFn = addToCartFn;
    }

    @Override
    // Clear the display if the data is invalid.
    public void accept(ItemInterface data) {
        if (data == null) {
            System.err.println("Received null ItemInterface data.");
            display = null;  
        } else {
            display = data;
        }
    }
    

    @Override
    protected void init() {}

    @Override
    protected JPanel getCentre() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        panel.add(new JLabel("Name:"));
        panel.add(new JLabel(display == null ? "" : display.getName()));
        panel.add(new JPanel());
        panel.add(new JLabel("Description:"));
        panel.add(new JLabel(display == null ? "" : display.getDescription()));
        panel.add(new JPanel());
        panel.add(new JLabel("Price:"));
        panel.add(new JLabel(display == null ? "" : display.getPrice() + ""));

        return panel;
    }

    @Override
    
    protected JPanel getBottom() {
        JPanel panel = new JPanel();

        JButton buy = new JButton("Add to cart");
            // Disable button if display is null
        try {
            if (display != null) {
                buy.addActionListener(e -> addFn.accept(display));
            } else {
                buy.setEnabled(false);  
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        panel.add(buy);
        return panel;
    }
}

