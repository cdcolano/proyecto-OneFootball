package ventanas;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import clases.ConImagenes;

public class NoticiaListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;
	Font font = new Font("helvitica", Font.BOLD, 24);

    @Override
    public Component getListCellRendererComponent(
            JList list, Object conImagen, int indice,
            boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, conImagen, indice, isSelected, cellHasFocus);
        ConImagenes img=(ConImagenes)conImagen;
        ImageIcon icono= new ImageIcon(VentanaInicio.class.getResource(img.getImagen()));
        label.setIcon(icono);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        return label;
    }
}
