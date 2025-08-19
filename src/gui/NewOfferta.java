package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.annuncio.Annuncio;
import entities.studente.Studente;
import gui.preset.JCustomTextArea;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewOfferta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller;
	private Annuncio annuncio;
	private Studente studente;
	private JTextField textField;
	
	public NewOfferta(Controller controller) {
		this.controller = controller;
		setTitle("UninaSwap");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(new BorderLayout(0, 0));
				

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{227, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 154, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel welcomingLabel = new JLabel("Fai un'offerta per: "+annuncio.getTitolo()+" di "+studente.getUsername());
		welcomingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_welcomingLabel = new GridBagConstraints();
		gbc_welcomingLabel.insets = new Insets(0, 0, 5, 0);
		gbc_welcomingLabel.gridwidth = 2;
		gbc_welcomingLabel.gridx = 0;
		gbc_welcomingLabel.gridy = 0;
		contentPane.add(welcomingLabel, gbc_welcomingLabel);
		
		JTextArea textArea = new JCustomTextArea("Aggiungi una descrizione alla tua offerta...");
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 2;
		contentPane.add(textArea, gbc_textArea);
		
		JLabel addMoneyLabel = new JLabel("Aggiungi quanto vuoi offrire: ");
		addMoneyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_addMoneyLabel = new GridBagConstraints();
		gbc_addMoneyLabel.anchor = GridBagConstraints.WEST;
		gbc_addMoneyLabel.insets = new Insets(0, 0, 5, 5);
		gbc_addMoneyLabel.gridx = 0;
		gbc_addMoneyLabel.gridy = 4;
		contentPane.add(addMoneyLabel, gbc_addMoneyLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 5;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
	}
	
	public void setAnnuncio(Annuncio annuncio) {
		this.annuncio = annuncio;
	}
	
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	
}
