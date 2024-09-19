package userinterface;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableButton extends DefaultCellEditor {
    private JButton button;
    private String text;

    public TableButton(String text) {
        super(new JCheckBox());
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> {
            try {
                fireEditingStopped(); // Can throw exceptions during editing
            } catch (Exception ex) {
                System.err.println("Error occurred while editing table cell: " + ex.getMessage());
            }
        });
        this.text = text;
    }

    @Override
    // Set text safely
    public Component getTableCellEditorComponent(
        JTable table, Object value, boolean isSelected, int row, int column
    ) {
        try {
            button.setText(text);  
        } catch (Exception ex) {
            System.err.println("Error occurred while setting button text: " + ex.getMessage());
        }
        return button;
    }
}
