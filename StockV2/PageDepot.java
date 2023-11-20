package visuel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import connexion.*;
import marchandise.*;

public class PageDepot extends JFrame {
    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    ArrayList<JButton> buttonDepot = new ArrayList<>();
    JButton title = new JButton("choose your Depot");
    ArrayList<Depot> depotList = DBCon.getListDepot();
    JPanel boxContent = new JPanel(new GridLayout(1, 3));

    public PageDepot() {
        this.setTitle("Depot");
        this.setLayout(new BorderLayout());
        this.setSize(screensize.width, screensize.height);

        for (int i = 0; i < depotList.size(); i++) {
            JButton button = new JButton(depotList.get(i).getName());
            buttonDepot.add(button);
            boxContent.add(buttonDepot.get(i));
        }

        this.add(title, BorderLayout.NORTH);
        this.add(boxContent, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
