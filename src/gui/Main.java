//my package
package gui;
import controller.Controller;
import entities.annuncio.*;
import entities.oggetto.*;
import entities.studente.*;
import gui.main_components.browse_pane.*;
import gui.preset.JButtonWithBorder;

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
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
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
	private Studente studenteLoggato;
	private ArrayList<Annuncio> annunciPubblicati;
	private ArrayList<Annuncio> mieiAnnunci;
	private ArrayList<Oggetto> mieiOggetti;
		

	//COSTRUTTORE
	public Main(Controller controller, Studente studenteLoggato) {
		this.controller = controller;
		this.studenteLoggato = studenteLoggato;
		this.annunciPubblicati = controller.getAnnunci(40);
		this.mieiAnnunci = controller.getMieiAnnunci(studenteLoggato.getUsername());
		this.mieiOggetti = controller.getMieiOggetti(studenteLoggato.getUsername());
		
		setTitle("UninaSwap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 650);		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		// Content panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.setFocusable(true);
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		
		// Browse panel
		JBrowsePane browsePane = new JBrowsePane(annunciPubblicati, controller);
		contentPane.add(browsePane, "BROWSE");
		
		
        //INIZIO FINISTRA DI LISTINGS
        // Listings panel
        JPanel listingsPanel = new JPanel();
		contentPane.add(listingsPanel, "LISTINGS");
		listingsPanel.setLayout(new BorderLayout(0, 0));
		
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
		
		JButton newListingButton = new JButtonWithBorder("Crea un nuovo annuncio +");
		GridBagConstraints gbc_newListingButton = new GridBagConstraints();
		gbc_newListingButton.insets = new Insets(0, 0, 5, 0);
		gbc_newListingButton.gridx = 1;
		gbc_newListingButton.gridy = 0;
		listingsUpperPanel.add(newListingButton, gbc_newListingButton);
		newListingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.onNuovoAnnuncioClicked();
			}
		});
		
		JLabel yourListingsLabel = new JLabel("Ecco qui i tuoi annunci:");
		yourListingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yourListingsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_yourListingsLabel = new GridBagConstraints();
		gbc_yourListingsLabel.anchor = GridBagConstraints.WEST;
		gbc_yourListingsLabel.insets = new Insets(0, 0, 0, 5);
		gbc_yourListingsLabel.gridx = 0;
		gbc_yourListingsLabel.gridy = 1;
		listingsUpperPanel.add(yourListingsLabel, gbc_yourListingsLabel);
		
		JScrollPane listingsScrollPane = new JScrollPane();
		listingsPanel.add(listingsScrollPane, BorderLayout.CENTER);

		//FINE SECONDA FINESTRA
		
		//INIZIO FINESTRA MY OBJECTS
		JPanel myObjectsPane = new JPanel();
		contentPane.add(myObjectsPane, "MYOBJECTS");
		myObjectsPane.setLayout(new BorderLayout(0, 0));
			
		JScrollPane myObjectScrollPane = new JScrollPane();
		myObjectsPane.add(myObjectScrollPane, BorderLayout.CENTER);
			
		JPanel myObjectsUpperPanel = new JPanel();
		myObjectsUpperPanel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		myObjectsUpperPanel.setPreferredSize(new Dimension(10, 70));
		myObjectsPane.add(myObjectsUpperPanel, BorderLayout.NORTH);
		GridBagLayout gbl_myObjectsUpperPanel = new GridBagLayout();
		gbl_myObjectsUpperPanel.columnWidths = new int[]{0, 0, 0};
		gbl_myObjectsUpperPanel.rowHeights = new int[]{36, 0, 0};
		gbl_myObjectsUpperPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_myObjectsUpperPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		myObjectsUpperPanel.setLayout(gbl_myObjectsUpperPanel);
			
		JLabel welcomingLabel_2 = new JLabel("Ciao User! Aggiungi i tuoi oggetti");
		welcomingLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_welcomingLabel_2 = new GridBagConstraints();
		gbc_welcomingLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_welcomingLabel_2.gridx = 0;
		gbc_welcomingLabel.gridy = 0;
		myObjectsUpperPanel.add(welcomingLabel_2, gbc_welcomingLabel_2);
			
		JButton newObjectButton = new JButtonWithBorder("Crea un nuovo oggetto +");
		GridBagConstraints gbc_newObjectButton = new GridBagConstraints();
		gbc_newObjectButton.insets = new Insets(0, 0, 5, 0);
		gbc_newObjectButton.gridx = 1;
		gbc_newObjectButton.gridy = 0;
		myObjectsUpperPanel.add(newObjectButton, gbc_newObjectButton);
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
		gbc_yourObjectsLabel.insets = new Insets(0, 0, 0, 5);
		gbc_yourObjectsLabel.gridx = 0;
		gbc_yourObjectsLabel.gridy = 1;
		myObjectsUpperPanel.add(yourObjectsLabel, gbc_yourObjectsLabel);
		
		
		//(Scelta delle finestre)
		// Button panel
        buttonPane = new JPanel();
        buttonPane.setBackground(new Color(255, 255, 255));
        FlowLayout fl_buttonPane = (FlowLayout) buttonPane.getLayout();
        fl_buttonPane.setVgap(10);
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
        
        
        JButton myObjectsButton = new JButtonWithBorder("My Objects");
        myObjectsButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		CardLayout cl = (CardLayout)(contentPane.getLayout());
        		cl.show(contentPane, "MYOBJECTS");
        	}
        });
        
        buttonPane.add(browseButton);
        buttonPane.add(listingsButton);
        buttonPane.add(new JButtonWithBorder("Offers"));
        buttonPane.add(myObjectsButton);
        buttonPane.add(new JButtonWithBorder("Profilo"));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

	//METODI
	
}

 