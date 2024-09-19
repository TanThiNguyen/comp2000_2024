package userinterface;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationBar extends JPanel {
    public NavigationBar(UIService service, boolean showBack) {
        Box horizontal1 = Box.createHorizontalBox();

        Box centreBox = Box.createHorizontalBox();
        if (showBack) {
            JButton back = new JButton("< Back");
            back.addActionListener((unused) -> {
                // Handle possible failures in the back navigation.
                try {
                    service.back();
                } catch (Exception e) {
                    System.err.println("Failed to navigate back: " + e.getMessage());
                }
            });
            centreBox.add(back);
        }
        JButton store = new JButton("Store");
        store.addActionListener((unused) -> {
            if (!service.navigateTo(StockPage.ROUTE_NAME)) {
                System.err.println("Failed to navigate to the store.");
            }
        });
    
        JButton cart = new JButton("Cart");
        cart.addActionListener((unused) -> {
            if (!service.navigateTo(CartPage.ROUTE_NAME)) {
                System.err.println("Failed to navigate to the cart.");
            }
        });

        centreBox.add(store);
        centreBox.add(cart);

        horizontal1.add(centreBox);
        super.add(horizontal1);
    }
}