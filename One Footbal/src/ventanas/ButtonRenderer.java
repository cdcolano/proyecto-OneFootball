package ventanas;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

/**Renderer para los botones implementados en Jtble 
 * en las clases VentanaSiguiendo y Ventana Busqueda
 * @author cdcol
 *
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
        
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
    	String nombre=(String)table.getValueAt(row, 1);
        if (isSelected) {
        	setForeground(Color.LIGHT_GRAY);
            setBackground(Color.WHITE);
        } else {
            setForeground(Color.WHITE);
            setBackground(Color.WHITE);
            setBorderPainted(false);
          //  setBackground(UIManager.getColor("Button.background"));
        }try {
        	ImageIcon img=new ImageIcon(ButtonRenderer.class.getResource((String)value));
        	if (img!=null)
        		setIcon(VentanaInicio.redimensionImgProd(img,30,30));
        }catch(NullPointerException ex) {
        		ex.printStackTrace();
        	}
        return this;
    }
}

