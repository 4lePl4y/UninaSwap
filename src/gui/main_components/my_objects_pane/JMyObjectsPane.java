package gui.main_components.my_objects_pane;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;
import entities.oggetto.Oggetto;
import gui.preset.JCustomScrollPane;

public class JMyObjectsPane extends JPanel {
	private static final long serialVersionUID = 1L;
	private JMyObjectsUpperPanel myObjectsUpperPanel;
	private JScrollPane myObjectsScrollPane;
	
	public JMyObjectsPane(ArrayList<Oggetto> mieiOggetti, Controller controller){
		this.setLayout(new BorderLayout(0, 0)); 
		myObjectsUpperPanel = new JMyObjectsUpperPanel(controller);
		myObjectsScrollPane = new JCustomScrollPane<Oggetto>(mieiOggetti, controller);
		this.add(myObjectsUpperPanel, BorderLayout.NORTH);
		this.add(myObjectsScrollPane, BorderLayout.CENTER);
	}

}
