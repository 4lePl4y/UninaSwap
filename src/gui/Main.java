//my package
package gui;
import controller.Controller;
import entities.annuncio.*;
import gui.preset.JButtonWithBorder;
import gui.preset.JCustomScrollPane;
import gui.preset.presetJTextField.JCustomTextField;

//package java gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Main extends JFrame {
	//ATTRIBUTI
	private Controller controller;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel buttonPane;
	private JTextField srchBarTxtField;
	private ArrayList<Annuncio> annunciPubblicati = new ArrayList<>();
		

	//COSTRUTTORE
	public Main(Controller controller) {
		this.controller = controller;
		this.annunciPubblicati = controller.getAnnunci();
		
		setTitle("UninaSwap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 650);		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		// Content panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new CardLayout(0, 0));
		
		
		// Browse panel
		JPanel browsePane = new JPanel();
		browsePane.setLayout(new BorderLayout(0, 0));
		contentPane.add(browsePane, "BROWSE");
		
		
		// Filter and search panel for browse
		JPanel filterNsearchPane = new JPanel();
		filterNsearchPane.setBackground(new Color(255, 255, 255));
		filterNsearchPane.setPreferredSize(new Dimension(800, 75));
		GridBagLayout gbl_filterNsearchPane = new GridBagLayout();
		gbl_filterNsearchPane.columnWidths = new int[] {264, 264, 264, 264};
		filterNsearchPane.setLayout(gbl_filterNsearchPane);
		browsePane.add(filterNsearchPane, BorderLayout.NORTH);
		
		// Search bar text field
		srchBarTxtField = new JCustomTextField("Cerca il tuo prossimo articolo preferito");
		srchBarTxtField.setPreferredSize(new Dimension(1056, 25));
		GridBagConstraints gbc_srchBarTxtField = new GridBagConstraints();
		gbc_srchBarTxtField.gridwidth = 4;
		gbc_srchBarTxtField.fill = GridBagConstraints.BOTH;
		gbc_srchBarTxtField.insets = new Insets(0, 0, 5, 5);
		gbc_srchBarTxtField.gridx = 0;
		gbc_srchBarTxtField.gridy = 0;
		filterNsearchPane.add(srchBarTxtField, gbc_srchBarTxtField);
		srchBarTxtField.setColumns(10);
		
		// Radio buttons for filters
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton.gridx = 0;
		gbc_rdbtnNewRadioButton.gridy = 1;
		filterNsearchPane.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 1;
		gbc_rdbtnNewRadioButton_1.gridy = 1;
		filterNsearchPane.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 2;
		gbc_rdbtnNewRadioButton_2.gridy = 1;
		filterNsearchPane.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_3.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnNewRadioButton_3.gridx = 3;
		gbc_rdbtnNewRadioButton_3.gridy = 1;
		filterNsearchPane.add(rdbtnNewRadioButton_3, gbc_rdbtnNewRadioButton_3);
	
		
        // Browse scroll panel with responsive cards
		JScrollPane browseScrollPane = new JCustomScrollPane<Annuncio>(annunciPubblicati);
		browsePane.add(browseScrollPane, BorderLayout.CENTER);
		
        //FINE FINESTRA DI BROWSE
        
        //INIZIO FINISTRA DI LISTINGS
        // Listings panel
        JPanel listingsPanel = new JPanel();
		contentPane.add(listingsPanel, "LISTINGS");
		listingsPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane listingsScrollPane = new JScrollPane();
		listingsPanel.add(listingsScrollPane, BorderLayout.CENTER);
		
		JPanel listingsUpperPanel = new JPanel();
		listingsUpperPanel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		listingsUpperPanel.setPreferredSize(new Dimension(10, 70));
		listingsPanel.add(listingsUpperPanel, BorderLayout.NORTH);
		GridBagLayout gbl_listingsUpperPanel = new GridBagLayout();
		gbl_listingsUpperPanel.columnWidths = new int[]{0, 0, 0};
		gbl_listingsUpperPanel.rowHeights = new int[]{36, 0, 0};
		gbl_listingsUpperPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_listingsUpperPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		listingsUpperPanel.setLayout(gbl_listingsUpperPanel);
		
		JLabel welcomingLabel = new JLabel("Ciao User! Liberati di quello che non usi più e connettiti con altri studenti della Federico II");
		welcomingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_welcomingLabel = new GridBagConstraints();
		gbc_welcomingLabel.insets = new Insets(0, 0, 5, 5);
		gbc_welcomingLabel.gridx = 0;
		gbc_welcomingLabel.gridy = 0;
		listingsUpperPanel.add(welcomingLabel, gbc_welcomingLabel);
		
		JButton newListingButton = new JButtonWithBorder("Crea un nuovo annuncio");
		newListingButton.setText("Crea un nuovo annuncio +");
		GridBagConstraints gbc_newListingButton = new GridBagConstraints();
		gbc_newListingButton.insets = new Insets(0, 0, 5, 0);
		gbc_newListingButton.gridx = 1;
		gbc_newListingButton.gridy = 0;
		listingsUpperPanel.add(newListingButton, gbc_newListingButton);
		
		JLabel yourListingsLabel = new JLabel("Ecco qui i tuoi annunci:");
		yourListingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yourListingsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_yourListingsLabel = new GridBagConstraints();
		gbc_yourListingsLabel.anchor = GridBagConstraints.WEST;
		gbc_yourListingsLabel.insets = new Insets(0, 0, 0, 5);
		gbc_yourListingsLabel.gridx = 0;
		gbc_yourListingsLabel.gridy = 1;
		listingsUpperPanel.add(yourListingsLabel, gbc_yourListingsLabel);

		//FINE SECONDA FINESTRA

		
		//(Scelta delle finestre)
		// Button panel
        buttonPane = new JPanel();
        buttonPane.setBackground(new Color(255, 255, 255));
        FlowLayout fl_buttonPane = (FlowLayout) buttonPane.getLayout();
        fl_buttonPane.setHgap(50);
        
        JButton browseButton = new JButtonWithBorder("Browse");
        browseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "BROWSE");
			}
		});

        JButton listingsButton = new JButtonWithBorder("Listings");
        listingsButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		CardLayout cl = (CardLayout)(contentPane.getLayout());
        		cl.show(contentPane, "LISTINGS");
        	}
        });
        
        buttonPane.add(browseButton);
        buttonPane.add(listingsButton);
        buttonPane.add(new JButtonWithBorder("Offers"));
        buttonPane.add(new JButtonWithBorder("My objects"));
        buttonPane.add(new JButtonWithBorder("Profilo"));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

	//METODI
	
}

 