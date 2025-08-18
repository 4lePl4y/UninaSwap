//my package
package gui;
import controller.Controller;
import entities.annuncio.*;
import gui.preset.presetJPanel.JCardsPanel;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;

public class Main extends JFrame {
	//ATTRIBUTI
	private Controller controller;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel buttonPane;
	private JTextField srchBarTxtField;
	private ArrayList<Annuncio> annunciPubblicati = new ArrayList<>();
	
	//Cards related attributes
	private JCardsPanel cardsPane;
	private int browseCardsCount = 40;
	private int hGap = 30;
	private int vGap = 15;
	

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
		Panel browsePane = new Panel();
		contentPane.add(browsePane, "BROWSE");
		browsePane.setLayout(new BorderLayout(0, 0));
		
		
		// Filter and search panel for browse
		JPanel filterNsearchPane = new JPanel();
		filterNsearchPane.setBackground(new Color(255, 255, 255));
		browsePane.add(filterNsearchPane, BorderLayout.NORTH);
		filterNsearchPane.setPreferredSize(new Dimension(800, 75));
		GridBagLayout gbl_filterNsearchPane = new GridBagLayout();
		gbl_filterNsearchPane.columnWidths = new int[] {264, 264, 264, 264};
		gbl_filterNsearchPane.rowHeights = new int[] {19, 19};
		gbl_filterNsearchPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_filterNsearchPane.rowWeights = new double[]{0.0, 0.0};
		filterNsearchPane.setLayout(gbl_filterNsearchPane);
		
		// Search bar text field
		srchBarTxtField = new JTextField();
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
		JScrollPane browseScrollPane = new JScrollPane();
		browsePane.add(browseScrollPane, BorderLayout.CENTER);
        browseScrollPane.getVerticalScrollBar().setUnitIncrement(15);
        browseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Cards panel 
		cardsPane = new JCardsPanel(browseCardsCount, hGap, vGap);
        cardsPane.setLayout(new GridBagLayout());
        browseScrollPane.setViewportView(cardsPane);
        cardsPane.updateCardsLayout(cardsPane.getCardsPerRow(browseScrollPane.getViewport().getWidth()), annunciPubblicati);
        browseScrollPane.getViewport().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int cardsPerRow = cardsPane.getCardsPerRow(browseScrollPane.getViewport().getWidth());
                cardsPane.updateCardsLayout(cardsPerRow, annunciPubblicati);
            }
        });
        
        //FINE FINESTRA DI BROWSE
        
        //INIZIO FINISTRA DI LISTINGS
        // Listings panel
        JPanel listingsPanel = new JPanel();
		contentPane.add(listingsPanel, "LISTINGS");
		listingsPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane listingsScrollPane = new JScrollPane();
		listingsPanel.add(listingsScrollPane, BorderLayout.CENTER);

		//FINE SECONDA FINESTRA

		
		//(Scelta delle finestre)
		// Button panel
        buttonPane = new JPanel();
        buttonPane.setBackground(new Color(255, 255, 255));
        FlowLayout fl_buttonPane = (FlowLayout) buttonPane.getLayout();
        fl_buttonPane.setHgap(50);
        
        JButton browseButton = new JButton("Browse");
        browseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "BROWSE");
			}
		});

        JButton listingsButton = new JButton("Listings");
        listingsButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		CardLayout cl = (CardLayout)(contentPane.getLayout());
        		cl.show(contentPane, "LISTINGS");
        	}
        });
        
        buttonPane.add(browseButton);
        buttonPane.add(listingsButton);
        buttonPane.add(new JButton("Offers"));
        buttonPane.add(new JButton("My objects"));
        buttonPane.add(new JButton("Profilo"));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

	//METODI
	
}

 
                




	