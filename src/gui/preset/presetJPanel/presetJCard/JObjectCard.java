package gui.preset.presetJPanel.presetJCard;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class JObjectCard extends JCard {
	private static final long serialVersionUID = 1L;

	public JObjectCard() {
		super();
		this.setBounds(0, 0, 240, 450);
		this.setLayout(null);
		
		JLabel descriptionLabel = new JLabel("Descrizione");
		descriptionLabel.setVerticalAlignment(SwingConstants.TOP);
		descriptionLabel.setBounds(10, 125, 220, 95);
		add(descriptionLabel);
		
		JLabel brandLabel = new JLabel("Marchio: ");
		brandLabel.setBounds(10, 230, 220, 13);
		add(brandLabel);
		
		JLabel modelLabel = new JLabel("Modello: ");
		modelLabel.setBounds(10, 253, 220, 13);
		add(modelLabel);
		
		JLabel sizeLabel = new JLabel("Taglia:");
		sizeLabel.setBounds(10, 276, 220, 13);
		add(sizeLabel);
		
		JLabel releaseDateLabel = new JLabel("Anno di Uscita: ");
		releaseDateLabel.setBounds(10, 299, 220, 13);
		add(releaseDateLabel);
		
		JLabel titleLabel = new JLabel("Titolo: ");
		titleLabel.setBounds(10, 322, 220, 13);
		add(titleLabel);
		
		JLabel ISBNLabel = new JLabel("ISBN: ");
		ISBNLabel.setBounds(10, 345, 220, 13);
		add(ISBNLabel);
		
		JLabel authorLabel = new JLabel("Autore: ");
		authorLabel.setBounds(10, 368, 220, 13);
		add(authorLabel);
		
		JLabel genreLabel = new JLabel("Genere: ");
		genreLabel.setBounds(10, 391, 45, 13);
		add(genreLabel);
		
		// Additional components can be added here
		// For example, you can add labels, buttons, etc.
	}
	
	// Additional methods can be added here if needed

}
