package ventanas;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**Renderer que hace que la columna 0 se sistitya por el icono correspondiente
 * @author cdcol
 *
 */
public class RendererPlantilla extends DefaultTableCellRenderer {
	   
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
	      JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      if (column==0) {
	    	  ImageIcon valor=(ImageIcon)value;
	    	  cell.setIcon(VentanaInicio.redimensionImgProd(valor, 50, 50));
	    	  cell.setText("");
	      }
	      return cell;
	   }
}
