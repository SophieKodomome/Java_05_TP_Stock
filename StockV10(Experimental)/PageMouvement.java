package visuel;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.*;
import java.util.Locale.Category;

import connexion.*;
import transaction.*;
import marchandise.*;
import myUtil.*;

//azo antoka
public class PageMouvement extends JFrame {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JButton titleButton = new JButton("Stock Form");
    private final JPanel contentPanel = new JPanel(new FlowLayout());
    private final JPanel footerPanel = new JPanel(new FlowLayout());
    private final JLabel magasinLabel = new JLabel("magasin");
    private final JLabel produitLabel = new JLabel("product");
    private final JLabel quantityLabel = new JLabel("quantity");
    private final JLabel priceLabel = new JLabel("price");

    ArrayList<Magasin> listMagasin = DBCon.getListMagasin();
    ArrayList<Produit> listProduit = DBCon.getListProduit();
    ArrayList<marchandise.Category> listCategories = DBCon.getListCategory();
    ArrayList<EtatProduit> listEtatProduits = DBCon.getListEtatProduit();
    JTextField priceTextField = new JTextField(10);
    JTextField quantityTextField = new JTextField(10);

    private JComboBox<String> magasinComboBox = new JComboBox<>();
    private JComboBox<String> produitComboBox = new JComboBox<>();
    private JComboBox<String> mouvementComboBox = new JComboBox<>(new String[] { "Entree", "Sortie" });

    JButton submitButton = new JButton("Submit");

    // where it is at
    public PageMouvement() {
        this.setTitle("Page De Stock");
        this.setSize(screenSize.width, screenSize.height);
        this.setLayout(new BorderLayout());

        this.add(titleButton, BorderLayout.NORTH);

        // create the Combo Boxes
        for (Magasin existingmagasin : listMagasin) {
            magasinComboBox.addItem(existingmagasin.getName());

        }
        for (Produit existingProduit : listProduit) {
            produitComboBox.addItem(existingProduit.getName());
        }

        // set text field column size
        priceTextField.setColumns(10);
        quantityTextField.setColumns(10);

        contentPanel.add(magasinLabel);
        contentPanel.add(magasinComboBox);
        contentPanel.add(produitLabel);
        contentPanel.add(produitComboBox);
        contentPanel.add(quantityLabel);
        contentPanel.add(quantityTextField);
        contentPanel.add(priceLabel);
        contentPanel.add(priceTextField);
        contentPanel.add(mouvementComboBox);
        contentPanel.add(submitButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                try {
                    Mouvement mouvement = getFilteredMouvement();
                    if (mouvement.getSortie() <= 0) {
                        if (mouvement.getMoney() < getMinPercentCump(mouvement)) {
                            JOptionPane.showMessageDialog(null, "Money Inferior to Cump");
                        } else {
                            if (mouvement.getMoney() > getMaxPercentCump(mouvement)) {
                                JOptionPane.showMessageDialog(null, "Money Above Cump");
                            } else {
                                DBCon.UpdateMouvement(mouvement);
                                JOptionPane.showMessageDialog(null, "Insertion successful!");
                            }
                        }
                    } else {
                        DBCon.UpdateMouvement(mouvement);
                        JOptionPane.showMessageDialog(null, "Insertion successful!");
                    }
                } catch (NegativeValueException eN) {
                    if (Integer.parseInt(quantityTextField.getText()) < 0) {
                        JOptionPane.showMessageDialog(null, "Quantity can't be negative");
                    }
                    if (Integer.parseInt(priceTextField.getText()) < 0) {
                        JOptionPane.showMessageDialog(null, "Price can't be negative");
                    }
                } catch (NumberFormatException e) {
                    if (quantityTextField.getText().isEmpty() || priceTextField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Both field must be filled");
                    } else {
                        JOptionPane.showMessageDialog(null, "Fill the field with number");
                    }
                }

            }
        });

        JButton voirEtatStock = new JButton("Voir Etat de Stock");
        footerPanel.add(voirEtatStock);
        voirEtatStock.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                setVisible(false);
                PageEtatStock pageStock = new PageEtatStock();
            }
        });
        JButton voirEtatCategory = new JButton("Voir Etat par Category");
        footerPanel.add(voirEtatCategory);
        voirEtatCategory.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                setVisible(false);
                PageEtatCategory pageCategory = new PageEtatCategory();
            }
        });

        this.add(footerPanel, BorderLayout.SOUTH);

    }

    double getMaxPercentCump(Mouvement mouvement) {
        double maxCump = 0;
        for (EtatProduit instanceEtatProduit : listEtatProduits)
            if (instanceEtatProduit.getIDProduit() == mouvement.getIDProduit()) {
                maxCump = (instanceEtatProduit.getCump() * 20) / 100;
                maxCump = instanceEtatProduit.getCump() + maxCump;
            }
        return maxCump;
    }

    double getMinPercentCump(Mouvement mouvement) {
        double minCump = 0;
        for (EtatProduit instanceEtatProduit : listEtatProduits)
            if (instanceEtatProduit.getIDProduit() == mouvement.getIDProduit()) {
                minCump = (instanceEtatProduit.getCump() * 20) / 100;
                minCump = instanceEtatProduit.getCump() - minCump;
            }
        return minCump;
    }

    Mouvement getFilteredMouvement() throws NegativeValueException {
        Mouvement filteredMouvement = new Mouvement();
        String magasinString = (String) magasinComboBox.getSelectedItem();
        String produitString = (String) produitComboBox.getSelectedItem();
        String movementString = (String) mouvementComboBox.getSelectedItem();
        int quantityInt = Integer.parseInt(quantityTextField.getText());
        int prix = Integer.parseInt(priceTextField.getText());

        for (Magasin instanceMagasin : listMagasin) {
            if (instanceMagasin.getName().equals(magasinString)) {
                filteredMouvement.setIDMagasin(instanceMagasin.getID());
            }
        }

        for (Produit existingProduit : listProduit) {
            for (marchandise.Category existingCategory : listCategories) {
                if (existingProduit.getName().equals(produitString)
                        && existingProduit.getIdCategory() == existingCategory.getID()) {
                    filteredMouvement.setIDProduit(existingProduit.getID());
                    filteredMouvement.setIDCategory(existingCategory.getID());
                }
            }
        }
        if (movementString == "Entree") {
            filteredMouvement.setEntree(quantityInt);
        } else {
            filteredMouvement.setSortie(quantityInt);
        }
        filteredMouvement.setMoney(prix);

        return filteredMouvement;
    }
}
