package ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class Pruebas extends JFrame {
	
	public static void main (String[]args) {
		Pruebas p=new Pruebas();
		Object[]b= {"saludo", ""};
		Object[][] o= {{"hola", "adios \n julio"},{"hola pepe", "adios"}};
		JTable t= new JTable(o, b);
		t.getColumnModel().getColumn(0).setCellRenderer(new ColumnColorRenderer());
		t.getColumnModel().getColumn(1).setCellRenderer(new ColumnColorRenderer());
		
		p.add(new JScrollPane(t), BorderLayout.CENTER);
		p.setVisible(true);
		p.setBounds(300, 200, 600, 400);
	}
}
