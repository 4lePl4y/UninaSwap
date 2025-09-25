package gui.preset;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class JCustomList<T> extends JPanel {
    private static final long serialVersionUID = 1L;

    private JList<T> jList;

    public enum Mode {
        DISPLAY_ONLY,      // solo mostrare
        SINGLE_SELECTION,  // selezione singola
        MULTIPLE_SELECTION // selezione multipla
    }

    public JCustomList(List<T> elements, Mode mode, int width, int height) {
    	UIManager.put("ScrollBar.width", 8);                  

    	setLayout(new BorderLayout());
    	this.setPreferredSize(new Dimension(width, height));
    	this.setMaximumSize(new Dimension(width, height));
    	
        // Usa Vector per evitare cast e problemi di tipo
        jList = new JList<>(new Vector<>(elements));
        jList.setVisibleRowCount(5); // default: 5 righe visibili
        jList.setFont(new Font("Arial", Font.PLAIN, 14));

        // Configurazione modalità
        switch (mode) {
            case DISPLAY_ONLY:
                jList.setSelectionModel(new DefaultListSelectionModel() {
					private static final long serialVersionUID = 1L;

					@Override
                    public void setSelectionInterval(int index0, int index1) {
                        // disabilita selezione
                    }
                });
                break;
            case SINGLE_SELECTION:
                jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                break;
            case MULTIPLE_SELECTION:
            	jList.setSelectionModel(new DefaultListSelectionModel() {
					private static final long serialVersionUID = 1L;
					
					public void setSelectionInterval(int index0, int index1) {
						if (isSelectedIndex(index0))
							super.removeSelectionInterval(index0, index1);
						else
							super.addSelectionInterval(index0, index1);
                    }
                });
                break;
        }

        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setPreferredSize(new Dimension(width, height));
        add(scrollPane, BorderLayout.CENTER);
       
        
    }


    // Getter per la JList interna
    public JList<T> getList() {
        return jList;
    }

    // Recupera elementi selezionati
    public ArrayList<T> getSelectedValues() {
        return new ArrayList<T>(jList.getSelectedValuesList());
    }
}

