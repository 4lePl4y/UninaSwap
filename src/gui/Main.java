package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Panel;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Main extends JFrame {
	private Controller controller;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel buttonPane;
	private JTextField srchBarTxtField;

	public Main(Controller controller) {
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 650);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Panel browsePanel = new Panel();
		contentPane.add(browsePanel, "BROWSE");
		browsePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel filterNsearchPanel = new JPanel();
		browsePanel.add(filterNsearchPanel, BorderLayout.NORTH);
		filterNsearchPanel.setPreferredSize(new Dimension(800, 75));
		GridBagLayout gbl_filterNsearchPanel = new GridBagLayout();
		gbl_filterNsearchPanel.columnWidths = new int[] {1054, 264, 264, 264};
		gbl_filterNsearchPanel.rowHeights = new int[]{37, 0, 0};
		gbl_filterNsearchPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_filterNsearchPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		filterNsearchPanel.setLayout(gbl_filterNsearchPanel);
		
		srchBarTxtField = new JTextField();
		srchBarTxtField.setPreferredSize(new Dimension(1056, 19));
		GridBagConstraints gbc_srchBarTxtField = new GridBagConstraints();
		gbc_srchBarTxtField.gridwidth = 4;
		gbc_srchBarTxtField.insets = new Insets(0, 0, 5, 0);
		gbc_srchBarTxtField.fill = GridBagConstraints.BOTH;
		gbc_srchBarTxtField.gridx = 0;
		gbc_srchBarTxtField.gridy = 0;
		filterNsearchPanel.add(srchBarTxtField, gbc_srchBarTxtField);
		srchBarTxtField.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton.gridx = 0;
		gbc_rdbtnNewRadioButton.gridy = 1;
		filterNsearchPanel.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 1;
		gbc_rdbtnNewRadioButton_1.gridy = 1;
		filterNsearchPanel.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 2;
		gbc_rdbtnNewRadioButton_2.gridy = 1;
		filterNsearchPanel.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
		GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_3.gridx = 3;
		gbc_rdbtnNewRadioButton_3.gridy = 1;
		filterNsearchPanel.add(rdbtnNewRadioButton_3, gbc_rdbtnNewRadioButton_3);
		
		JScrollPane browseScrollPane = new JScrollPane();
		browsePanel.add(browseScrollPane, BorderLayout.CENTER);
		
		JPanel listingsPanel = new JPanel();
		contentPane.add(listingsPanel, "LISTINGS");
		listingsPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane listingsScrollPane = new JScrollPane();
		listingsPanel.add(listingsScrollPane, BorderLayout.CENTER);

        buttonPane = new JPanel();
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
        buttonPane.add(new JButton("Button 3"));
        buttonPane.add(new JButton("Button 4"));
        buttonPane.add(new JButton("Profilo"));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}

}
