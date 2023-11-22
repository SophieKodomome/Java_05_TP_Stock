package visuel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import marchandise.*;
import myUtil.*;
import visuel.PageEtatStock;
import visuel.PageMouvement;
import connexion.*;

public class PageEtatCategory extends JFrame {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JButton title = new JButton("Page Etat Category");

    private ArrayList<EtatCategory> etatCategoryList = DBCon.getListEtatCategory();
    private ArrayList<JButton> buttonCategoryList = new ArrayList<>();
    private JPanel contentPanel = new JPanel(new GridLayout(etatCategoryList.size(), 1));
    private final JPanel footerPanel = new JPanel(new FlowLayout());

    public PageEtatCategory() {
        this.setTitle("Page Etat Category");
        this.setLayout(new BorderLayout());
        this.setSize(screenSize.width, screenSize.height);
        int i = 0;
        for (EtatCategory instanceEtatCategory : etatCategoryList) {
            JButton button = new JButton("Nom: " + instanceEtatCategory.getName() +
                    " | IdCategory: " + instanceEtatCategory.getIDCategory() + " | IdMagasin: "
                    + instanceEtatCategory.getIDMagasin() + " | Montant: " + instanceEtatCategory.getMontant());
            buttonCategoryList.add(button);
            contentPanel.add(buttonCategoryList.get(i)); // Add each button individually
            i++;
        }

        // contentPanel.add(buttonCategoryList);
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
        JButton voirEtatStock = new JButton("Voir Etat de Stock");
        footerPanel.add(voirEtatStock, BorderLayout.SOUTH);
        voirEtatStock.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                setVisible(false);
                PageEtatStock pageStock = new PageEtatStock();
            }
        });
        this.add(footerPanel, BorderLayout.SOUTH);
    }

}
