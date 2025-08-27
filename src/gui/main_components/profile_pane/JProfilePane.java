package gui.main_components.profile_pane;

import controller.Controller;
import entities.offerta.Offerta;
import javax.swing.*;
import java.util.ArrayList;

public class JProfilePane extends JPanel {

    private static final long serialVersionUID = 1L;
    
    public JProfilePane(Controller controller, ArrayList<Offerta> offerteInviate) {
        JLabel label = new JLabel("ciao");
        this.add(label);
    }
    
}