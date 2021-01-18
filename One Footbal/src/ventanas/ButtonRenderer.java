package ventanas;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            //setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(Color.WHITE);
            setBorderPainted(false);
          //  setBackground(UIManager.getColor("Button.background"));
        }
        setIcon(VentanaInicio.redimensionImgProd(new ImageIcon(ButtonRenderer.class.getResource((String)value)),30,30));
        return this;
    }
}

