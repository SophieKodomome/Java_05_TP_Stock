package visuel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

import connexion.*;
import transaction.*;
import marchandise.*;
import myUtil.*;

public class PagePreview extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton title = new JButton("List Of Stock");
    private ArrayList<JButton> listStockButton = new ArrayList<>();
    private ArrayList<Stock> listStocks = DBCon.getListStock();
    private JPanel contentPanel = new JPanel(new GridLayout(listStocks.size(), 0));

    public PagePreview() {
        this.setTitle("List of stock");
        this.setLayout(new BorderLayout());
        this.setSize(screenSize.width, screenSize.height);
        int i = 0;
        for (Stock instanceStock : listStocks) {
            JButton instanceButton = new JButton(instanceStock.getIDDepot() + " " + instanceStock.getIDProduit() + " "
                    + instanceStock.getIDCategory() + " " + instanceStock.getCurrentStock() + " "
                    + instanceStock.getCump());

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
