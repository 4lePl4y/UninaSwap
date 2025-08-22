//my package
package gui;
import controller.Controller;
import entities.annuncio.*;
import entities.oggetto.*;
import entities.studente.*;
import gui.main_components.browse_pane.*;
import gui.main_components.listings_pane.JListingsPane;
import gui.main_components.my_objects_pane.JMyObjectsPane;
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
	public Main(Controller controller) {
		this.controller = controller;
		this.studenteLoggato = controller.getStudenteLoggato();
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
		
        // Listings panel
        JPanel listingsPane = new JListingsPane(mieiAnnunci, controller);
		contentPane.add(listingsPane, "LISTINGS");
		
		//FINE SECONDA FINESTRA
		
		// My Objects panel
		JPanel myObjectsPane = new JMyObjectsPane(mieiOggetti, controller);
		contentPane.add(myObjectsPane, "MYOBJECTS");

		
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
	public ArrayList<Oggetto>getMieiOggetti() {
		return mieiOggetti;
	}
	
	public Studente getStudenteLoggato() {
		return this.studenteLoggato;
	}
}

 