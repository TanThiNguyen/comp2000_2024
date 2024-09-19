package userinterface;

import javax.swing.JPanel;

import student.ItemInterface;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Stack;

import java.util.Optional;

public class UIService {
    
    private CardLayout layout;
    private JPanel panel;
    private Stack<String> history;

    public UIService() {
        history = new Stack<>();
        layout = new CardLayout();
        panel = new JPanel(layout);
        panel.setPreferredSize(new Dimension(1280, 720));
    }

    public JPanel getPanel() {
        return panel;
    }

    public void addPage(Page page) {
        panel.add(page, page.routeName);
    }

    // Page getActivePage() {
    //     Page page = null;
    //     for (Component c : panel.getComponents()) {
    //         if (c.isVisible()) {
    //             page = (Page) c;
    //         }
    //     }
    //     return page;
    // }

    public Optional<Page> getActivePage() {
        for (Component c : panel.getComponents()) {
            if (c.isVisible()) {
                return Optional.of((Page) c);
            }
        }
        return Optional.empty();
    }

    // String lastPageRoute() {
    //     if (history.size() <= 1) {
    //         return "";
    //     }
    //     String current = history.pop();
    //     String last = history.peek();
    //     history.push(current);
    //     return last;
    // }
    public Optional<String> lastPageRoute() {
        if (history.size() <= 1) {
            return Optional.empty();
        }
        String current = history.pop();
        String last = history.peek();
        history.push(current);
        return Optional.of(last);
    }

    // void refresh() {
    //     String current = history.peek();
    //     if (history.size() > 1) {
    //         back();
    //     }
    //     navigateTo(current);
    // }

    public void refresh() {
        Optional<String> current = Optional.ofNullable(history.peek());
        if (history.size() > 1) {
            back();
        }
        current.ifPresent(this::navigateTo);
    }

    // public void navigateTo(String route) {
    //     if (history.size() > 0 && history.peek().equals(route)) {
    //         return;
    //     }
    //     history.push(route);
    //     layout.show(panel, route);
    //     getActivePage().buildPage();
    //     getActivePage().validate();
    // }
    public boolean navigateTo(String route) {
        if (history.size() > 0 && history.peek().equals(route)) {
            return false;  // Indicating no navigation occurred
        }
        history.push(route);
        layout.show(panel, route);
        Optional<Page> activePage = getActivePage();
        if (activePage.isPresent()) {
            activePage.get().buildPage();
            activePage.get().validate();
            return true;  // Navigation was successful
        } else {
            System.err.println("Failed to navigate: Active page not found.");
            return false;  // Navigation failed
        }
    }

    
    // public void navigateTo(String route, ItemInterface data) {
    //     if (history.size() > 0 && history.peek().equals(route)) {
    //         return;
    //     }
    //     history.push(route);
    //     layout.show(panel, route);
    //     getActivePage().accept(data);
    //     getActivePage().buildPage();
    //     getActivePage().validate();
    // }
 
    public boolean navigateTo(String route, ItemInterface data) {
        if (history.size() > 0 && history.peek().equals(route)) {
            return false;  // Indicating no navigation occurred
        }
        history.push(route);
        layout.show(panel, route);
        Optional<Page> activePage = getActivePage();
        if (activePage.isPresent()) {
            activePage.get().accept(data);
            activePage.get().buildPage();
            activePage.get().validate();
            return true;  // Navigation was successful
        } else {
            System.err.println("Failed to navigate: Active page not found.");
            return false;  // Navigation failed
        }
    }

    // void back() {
    //     String last = lastPageRoute();
    //     if (!last.isEmpty()) {
    //         navigateTo(last);
    //     }
    // }
    public void back() {
        lastPageRoute().ifPresent(this::navigateTo);
    }
}
