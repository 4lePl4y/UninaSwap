package gui.preset.presetJPanel.presetJCard;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.Controller;
import entities.oggetto.*;
import gui.preset.JButtonWithBorder;

import javax.swing.JButton;


public class JObjectCard extends JCard {
	private static final long serialVersionUID = 1L;
	private Oggetto oggetto;
	private JLabel nameLabel;
	private JLabel brandLabel;
	private JLabel modelLabel;
	private JLabel sizeLabel;
	private JLabel releaseDateLabel;
	private JLabel titleLabel;
	private JLabel ISBNLabel;
	private JLabel authorLabel;
	private JLabel genreLabel;

	public JObjectCard(Oggetto oggetto, Controller controller) {
		super(controller);
		this.oggetto = oggetto;
		this.setBounds(0, 0, 280, 450);
		this.setLayout(null);
		
		JLabel nameLabel = new JLabel(oggetto.getNome());
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setVerticalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(0, 0, 280, 20);
		add(nameLabel);
		
		
		switch(oggetto) {
			case Abbigliamento a -> {brandLabel = new JLabel("Marchio: " + a.getMarchio());
									 brandLabel.setBounds(10, 230, 220, 13);
									 add(brandLabel);
									 
									 sizeLabel = new JLabel("Taglia: " + a.getTaglia());
									 sizeLabel.setBounds(10, 276, 220, 13);
									 add(sizeLabel);
									}
			case Elettronica e -> {brandLabel = new JLabel("Marchio: " + e.getMarchio());
									brandLabel.setBounds(10, 230, 220, 13);
									add(brandLabel);
									
									modelLabel = new JLabel("Modello: " + e.getModello());
									modelLabel.setBounds(10, 253, 220, 13);
									add(modelLabel);
									
									releaseDateLabel = new JLabel("Anno di Uscita: " + e.getAnnoUscita());
									releaseDateLabel.setBounds(10, 299, 220, 13);
									add(releaseDateLabel);
								   }
			case Libro l -> {titleLabel = new JLabel("Titolo: " + l.getTitolo());
							titleLabel.setBounds(10, 322, 220, 13);
							add(titleLabel);
							
							ISBNLabel = new JLabel("ISBN: " + l.getISBN());
							ISBNLabel.setBounds(10, 345, 220, 13);
							add(ISBNLabel);
							
							authorLabel = new JLabel("Autore: " + l.getAutore());
							authorLabel.setBounds(10, 368, 220, 13);
							add(authorLabel);
							
							genreLabel = new JLabel("Genere: " + l.getGenere());
							genreLabel.setBounds(10, 391, 220, 13);
							add(genreLabel);
							
							releaseDateLabel = new JLabel("Anno di Uscita: " + l.getAnnoUscita());
							releaseDateLabel.setBounds(10, 299, 220, 13);
							add(releaseDateLabel);
						  }
			case StrumentoMusicale sm -> {brandLabel = new JLabel("Marchio: " + sm.getMarchio());
									   brandLabel.setBounds(10, 230, 220, 13);
									   add(brandLabel);
									   }
			case Misc m -> {brandLabel = new JLabel("Marchio: " + m.getMarchio());
							brandLabel.setBounds(10, 230, 220, 13);
							add(brandLabel);
							}
			default -> {throw new IllegalArgumentException("Unsupported object type");}
		}		
		
		JButton updateButton = new JButtonWithBorder("Modifica");
		updateButton.setBounds(10, 419, 100, 30);
		add(updateButton);
		
		JButton deleteButton = new JButtonWithBorder("Elimina");
		deleteButton.setBounds(145, 419, 100, 30);
		add(deleteButton);
		
	}
}
