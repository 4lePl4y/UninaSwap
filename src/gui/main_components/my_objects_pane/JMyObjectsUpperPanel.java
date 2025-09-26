package gui.main_components.my_objects_pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controller;
import entities.studente.Studente;
import gui.preset.presetJButton.JButtonWithBorder;

public class JMyObjectsUpperPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Studente studenteLoggato;
	
	public JMyObjectsUpperPanel(Controller controller) {
		this.studenteLoggato = controller.getStudenteLoggato();
		this.setBackground(new Color(255, 255, 255));
		this.setPreferredSize(new Dimension(800, 85));
		GridBagLayout gbl_myObjectsUpperPanel = new GridBagLayout();
		gbl_myObjectsUpperPanel.columnWidths = new int[]{532, 0, 0};
		gbl_myObjectsUpperPanel.rowHeights = new int[]{36, 0, 0};
		gbl_myObjectsUpperPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_myObjectsUpperPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_myObjectsUpperPanel);
			
		JLabel welcomingLabel_2 = new JLabel("Ciao "+studenteLoggato.getUsername()+"! Aggiungi i tuoi oggetti");
		welcomingLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_welcomingLabel_2 = new GridBagConstraints();
		gbc_welcomingLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_welcomingLabel_2.gridx = 0;
		gbc_welcomingLabel_2.gridy = 0;
		this.add(welcomingLabel_2, gbc_welcomingLabel_2);
			
		JButton newObjectButton = new JButtonWithBorder("Crea un nuovo oggetto +", Controller.APP_BLUE);
		GridBagConstraints gbc_newObjectButton = new GridBagConstraints();
		gbc_newObjectButton.insets = new Insets(0, 0, 5, 0);
		gbc_newObjectButton.gridx = 1;
		gbc_newObjectButton.gridy = 0;
		this.add(newObjectButton, gbc_newObjectButton);
		newObjectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.onNuovoOggettoClicked();
			}
		});
			
		JLabel yourObjectsLabel = new JLabel("Ecco qui i tuoi oggetti:");
		yourObjectsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yourObjectsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_yourObjectsLabel = new GridBagConstraints();
		gbc_yourObjectsLabel.anchor = GridBagConstraints.WEST;
		gbc_yourObjectsLabel.insets = new Insets(0, 10, 0, 0);
		gbc_yourObjectsLabel.gridx = 0;
		gbc_yourObjectsLabel.gridy = 1;
		this.add(yourObjectsLabel, gbc_yourObjectsLabel);
		
	}

}
