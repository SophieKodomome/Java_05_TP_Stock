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

public class PageStock extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton titleButton = new JButton("Stock Form");
    private JPanel contentPanel = new JPanel(new FlowLayout());
    private JLabel depotLabel = new JLabel("depot");
    private JLabel produitLabel = new JLabel("product");
    private JLabel quantityLabel = new JLabel("quantity");
    private JLabel priceLabel = new JLabel("price");

    ArrayList<Depot> listDepot = DBCon.getListDepot();
    ArrayList<Produit> listProduit = DBCon.getListProduit();
    ArrayList<marchandise.Category> listCategories = DBCon.getListCategory();
    ArrayList<Stock> listStock = DBCon.getListStock();

    JTextField priceTextField = new JTextField(10);
    JTextField quantityTextField = new JTextField(10);

    private JComboBox<String> depotComboBox = new JComboBox<>();
    private JComboBox<String> produitComboBox = new JComboBox<>();
    private JComboBox<String> movementComboBox = new JComboBox<>(new String[] { "Entree", "Sortie" });

    JButton submitButton = new JButton("Submit");

    // where it is at
    public PageStock() {
        this.setTitle("Page De Stock");
        this.setSize(screenSize.width, screenSize.height);
        this.setLayout(new BorderLayout());

        this.add(titleButton, BorderLayout.NORTH);

        // create the Combo Boxes
        for (Depot existingDepot : listDepot) {
            depotComboBox.addItem(existingDepot.getName());

        }
        for (Produit existingProduit : listProduit) {
            produitComboBox.addItem(existingProduit.getName());
        }

        // set text field column size
        priceTextField.setColumns(10);
        quantityTextField.setColumns(10);

        contentPanel.add(depotLabel);
        contentPanel.add(depotComboBox);
        contentPanel.add(produitLabel);
        contentPanel.add(produitComboBox);
        contentPanel.add(quantityLabel);
        contentPanel.add(quantityTextField);
        contentPanel.add(priceLabel);
        contentPanel.add(priceTextField);
        // contentPanel.add(movementComboBox);
        contentPanel.add(submitButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                Stock stockInserted = getInsertedStock();
                Stock stockFilteredList = getFilteredStock();
                int insertedValues = Integer.parseInt(quantityTextField.getText());

                double prix = Double.parseDouble(priceTextField.getText());
                Stock finalStock = new Stock(
                        stockInserted.getIDProduit(),
                        stockInserted.getIDCategory(),
                        stockInserted.getIDDepot(),
                        0,
                        0);
                DBCon.updateStock(finalStock, insertedValues, prix);
                DBCon.updateMvmt(finalStock, insertedValues, prix);
            }
        });
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

    private Stock getInsertedStock() {
        Stock stock = new Stock();
        String nomDepot = (String) this.depotComboBox.getSelectedItem();
        String nomProduit = (String) this.produitComboBox.getSelectedItem();

        // set ID Depot
        for (int i = 0; i < listDepot.size(); i++) {
            if (listDepot.get(i).getName().equals(nomDepot)) {
                stock.setIDDepot(listDepot.get(i).getID());
            }

        }

        // set ID Produit
        for (int i = 0; i < listProduit.size(); i++) {
            for (int j = 0; j < listCategories.size(); j++) {
                if (listProduit.get(i).getName().equals(nomProduit)
                        && listProduit.get(i).getIdCategory() == listCategories.get(j).getID()) {
                    stock.setIDProduit(listProduit.get(i).getID());
                    stock.setIDCategory(listCategories.get(j).getID());
                }
            }
        }

        return stock;
    }

    private Stock getFilteredStock() {
        Stock filteredStock = new Stock();
        Stock stockForm = getInsertedStock(); // stock from Form

        for (Stock instanceStock : listStock) {
            if (stockForm.getIDDepot() == instanceStock.getIDDepot()
                    && stockForm.getIDProduit() == instanceStock.getIDProduit()) {
                filteredStock = instanceStock;
            }
        }

        return filteredStock;
    }
}
