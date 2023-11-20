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

    LocalDate currentDate = LocalDate.now();
    Date currentDateConverted = convertToDate(currentDate);
    ArrayList<Depot> listDepot = DBCon.getListDepot();
    ArrayList<Produit> listProduit = DBCon.getListProduit();
    ArrayList<Stock> listStock = DBCon.getListStock();
    ArrayList<marchandise.Category> listCategories = DBCon.getListCategory();

    JFormattedTextField priceTextField = new JFormattedTextField(new NumberFormateur().getFormatter());
    JFormattedTextField quantityTextField = new JFormattedTextField(new NumberFormateur().getFormatter());

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
        contentPanel.add(movementComboBox);
        contentPanel.add(submitButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {

                Stock stockInserted = getInsertedStock();
                Stock stockFilteredList = getFilteredStock();
                int newStockInt = (stockInserted.getMovementQuantite() + stockFilteredList.getCurrentStock());
                // DateTimeFormatter dateTempsFormatter =
                // DateTimeFormatter.ofPattern("dd/MMM/yy");

                double prix = Double.parseDouble(priceTextField.getText());
                double cump = (stockInserted.getMovementQuantite() * prix)
                        + (stockFilteredList.getCurrentStock() * stockFilteredList.getCump())
                                / newStockInt;

                Stock finalStock = new Stock(
                        stockFilteredList.getIDProduit(),
                        stockFilteredList.getIDCategory(),
                        stockInserted.getMovementQuantite(),
                        stockFilteredList.getIDDepot(),
                        newStockInt,
                        currentDateConverted,
                        cump);

                DBCon.updateStock(finalStock);
            }
        });

    }

    private Stock getInsertedStock() {
        Stock stock = new Stock();
        String nomDepot = (String) this.depotComboBox.getSelectedItem();
        String nomProduit = (String) this.produitComboBox.getSelectedItem();
        int quantity = Integer.parseInt(quantityTextField.getText());

        // set ID Depot
        for (int i = 0; i < listDepot.size(); i++) {
            if (listDepot.get(i).getName().equals(nomDepot)) {
                stock.setIDDepot(listDepot.get(i).getID());
            }

        }

        // set ID Produit
        for (int i = 0; i < listProduit.size(); i++) {
            if (listProduit.get(i).getName().equals(nomProduit)) {
                stock.setIDProduit(listProduit.get(i).getID());
            }
        }

        // set Quantity
        if ((String) this.movementComboBox.getSelectedItem() == "Entree") {
            stock.setMovementQuantite(quantity);
        } else {
            stock.setMovementQuantite(quantity * -1);
        }

        return stock;
    }

    private Stock getFilteredStock() {
        Stock filteredStock = null;
        Stock stockForm = getInsertedStock(); // stock from Form
        Date mostRecentDate = null;

        for (Stock instanceStock : this.listStock) {
            if (stockForm.getIDDepot() == instanceStock.getIDDepot()
                    && stockForm.getIDProduit() == instanceStock.getIDProduit()
                    && this.currentDateConverted.after(instanceStock.getDateStock())) {

                if (mostRecentDate == null || instanceStock.getDateStock().after(mostRecentDate)) {

                    mostRecentDate = instanceStock.getDateStock();
                    filteredStock = instanceStock;
                }
            }
        }

        return filteredStock;
    }

    private static Date convertToDate(LocalDate localDate) {
        LocalDateTime localDateTime = localDate.atTime(LocalTime.now());

        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
