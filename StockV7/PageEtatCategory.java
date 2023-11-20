package visuel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

import connexion.*;
import transaction.*;
import visuel.PageAccueil;
import marchandise.*;
import myUtil.*;

public class PageEtatCategory extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton title = new JButton("List Of Stock by Category");
    private ArrayList<EtatCategory> listEtatCategories = DBCon.getListEtatCategory();
    private ArrayList<JButton> listStockButton = new ArrayList<>();
    private JPanel contentPanel = new JPanel(new GridLayout(listEtatCategories.size(), 0));

    public PageEtatCategory() {
        this.setTitle("List Of stock by Category");
        this.setLayout(new BorderLayout());
        this.setSize(screenSize.width, screenSize.height);

        int i = 0;
        for (EtatCategory instanceEtatCategory : listEtatCategories) {
            JButton instanceButton = new JButton(
                    instanceEtatCategory.getID() + " " + instanceEtatCategory.getValeur());

            listStockButton.add(instanceButton);
            contentPanel.add(listStockButton.get(i));
            i++;
        }
        this.add(title, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JButton retour = new JButton("retour");
        this.add(retour, BorderLayout.SOUTH);
        retour.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                setVisible(false);
                PageAccueil pageStock = new PageAccueil();
            }
        });

    }

}