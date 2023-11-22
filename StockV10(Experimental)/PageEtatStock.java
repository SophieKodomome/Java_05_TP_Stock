package visuel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import marchandise.*;
import myUtil.*;
import connexion.*;

public class PageEtatStock extends JFrame {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JButton title = new JButton("Page Etat Stock");

    private ArrayList<EtatStock> etatStockList = DBCon.getListEtatStock();
    private ArrayList<JButton> buttonStockList = new ArrayList<>();
    private JPanel contentPanel = new JPanel(new GridLayout(etatStockList.size(), 1));
    private final JPanel footerPanel = new JPanel(new FlowLayout());

    public PageEtatStock() {
        this.setTitle("Page Etat Stock");
        this.setLayout(new BorderLayout());
        this.setSize(screenSize.width, screenSize.height);
        int i = 0;
        for (EtatStock instanceEtatStock : etatStockList) {
            JButton button = new JButton("Nom: " + instanceEtatStock.getName() +
                    " | IdProduit: " + instanceEtatStock.getIDProduit() + " | IdMagasin: "
                    + instanceEtatStock.getIDMagasin() + " | Reste: "
                    + instanceEtatStock.getReste() + " | Cump: " + instanceEtatStock.getCump() + " | Montant: "
                    + instanceEtatStock.getMontant());
            buttonStockList.add(button);
            contentPanel.add(buttonStockList.get(i)); // Add each button individually
            i++;
        }

        // contentPanel.add(buttonStockList);
        this.add(contentPanel);
        this.add(title, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JButton ajout = new JButton("Ajout");
        footerPanel.add(ajout, BorderLayout.SOUTH);
        ajout.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                setVisible(false);
                PageMouvement pageMouvement = new PageMouvement();
            }
        });
        JButton voirEtatCategory = new JButton("Voir Etat par Category");
        footerPanel.add(voirEtatCategory, BorderLayout.SOUTH);
        voirEtatCategory.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                setVisible(false);
                PageEtatCategory pageCategory = new PageEtatCategory();
            }
        });
        this.add(footerPanel, BorderLayout.SOUTH);
    }

}
