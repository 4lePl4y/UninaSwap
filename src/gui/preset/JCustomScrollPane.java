package gui.preset;

import gui.preset.presetJPanel.JCardsPane;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;

import controller.Controller;

public class JCustomScrollPane<T> extends JScrollPane {
	private static final long serialVersionUID = 1L;
	private JCardsPane<T> cardsPane;
	private Controller controller;
	
	public JCustomScrollPane(ArrayList<T> contents, Controller controller) {
		this.controller = controller;
		this.getVerticalScrollBar().setUnitIncrement(15);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		cardsPane = new JCardsPane<T>(controller);
		this.setViewportView(cardsPane);
	
		this.getViewport().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int viewportWidth = getViewport().getWidth();
				int cardsPerRow = cardsPane.getCardsPerRow(viewportWidth);
				cardsPane.updateCardsLayout(cardsPerRow, contents);
				cardsPane.revalidate();
				}
		});
	}
	
}
