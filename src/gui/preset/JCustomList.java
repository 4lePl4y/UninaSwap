package gui.preset;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.*;

public class JCustomList<T> extends JPanel {
	// ATTRIBUTI
	private static final long serialVersionUID = 1L;
    private JList<T> jList;
    public enum Mode {
        DISPLAY_ONLY,      // solo mostrare
        SINGLE_SELECTION,  // selezione singola
        MULTIPLE_SELECTION // selezione multipla
    }

    // COSTRUTTORE
    public JCustomList(List<T> elements, Mode mode, int width, int height) {
        UIManager.put("ScrollBar.width", 10);	// larghezza barra di scorrimento

        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));

        jList = new JList<>(new Vector<>(elements));
        jList.setVisibleRowCount(5);
        jList.setFont(new Font("Arial", Font.PLAIN, 14));

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
        scrollPane.setMaximumSize(new Dimension(width, height));
        add(scrollPane, BorderLayout.CENTER);

        // Propaga scroll al parent quando siamo a inizio/fine o la barra non c’è
        jList.addMouseWheelListener(e -> {
            JScrollBar vBar = scrollPane.getVerticalScrollBar();

            boolean atTop = vBar.getValue() == 0;
            boolean atBottom = vBar.getValue() >= vBar.getMaximum() - vBar.getVisibleAmount();

            if (!vBar.isVisible() || (e.getWheelRotation() < 0 && atTop) || (e.getWheelRotation() > 0 && atBottom)) {
                scrollPane.getParent().dispatchEvent(
                        SwingUtilities.convertMouseEvent(jList, e, scrollPane.getParent())
                );
            } else
				scrollPane.dispatchEvent(SwingUtilities.convertMouseEvent(jList, e, scrollPane));
        });
    }

    
    // METODI
    public JList<T> getList() {
        return jList;
    }

    public ArrayList<T> getSelectedValues() {
    	if(jList.getSelectedValuesList().isEmpty())
    		return null;
    	else
    		return new ArrayList<>(jList.getSelectedValuesList());
    }
}
