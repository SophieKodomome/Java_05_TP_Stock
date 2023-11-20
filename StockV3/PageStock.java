package visuel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import connexion.*;
import transaction.*;
import marchandise.*;

public class PageStock extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton titleButton = new JButton("Stock Form");
    private JPanel contentPanel = new JPanel(new FlowLayout());
    private JLabel depotLabel = new JLabel("depot");
    private JLabel produitLabel = new JLabel("product");
    private JLabel quantiteLabel = new JLabel("quantity");

    ArrayList<Depot> listDepot = DBCon.getListDepot();
    ArrayList<Produit> listProduit = DBCon.getListProduit();
    ArrayList<Stock> listStock = DBCon.getListStock();

    private JComboBox<String> depotComboBox = new JComboBox<>();
    private JComboBox<String> produitComboBox = new JComboBox<>();
    private JComboBox<String> quantiteComboBox = new JComboBox<>(new String[] { "Entree", "Sortie" });
    private JTextField quantityTextField = new JTextField(10);

    JButton submitButton = new JButton("Submit");

    public PageStock() {
        this.setTitle("Page De Stock");
        this.setSize(screenSize.width, screenSize.height);
        this.setLayout(new BorderLayout());

        this.add(titleButton, BorderLayout.NORTH);
        for (int i = 0; i < DBCon.getListDepotName().size(); i++) {
            depotComboBox.addItem(DBCon.getListDepotName().get(i));

        }
        for (int i = 0; i < listProduit.size(); i++) {
            produitComboBox.addItem(listProduit.get(i).getName());
        }
        contentPanel.add(depotLabel);
        contentPanel.add(depotComboBox);
        contentPanel.add(produitLabel);
        contentPanel.add(produitComboBox);
        contentPanel.add(quantiteLabel);
        contentPanel.add(quantiteComboBox);
        contentPanel.add(quantityTextField);
        contentPanel.add(submitButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                try {
                    Stock insertedStock = getInsertedStock();

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                break;

            }

        }

        // set ID Produit
        for (int i = 0; i < listProduit.size(); i++) {
            if (listProduit.get(i).getName().equals(nomProduit)) {
                stock.setIDProduit(listProduit.get(i).getID());
            }
        }

        // set QTE
        if (quantiteComboBox.getSelectedItem() == "Entree") {
            stock.setQEntry(Integer.parseInt(quantityTextField.getText()));
            stock.setQExit(0);
        } else {
            stock.setQExit(-(Integer.parseInt(quantityTextField.getText())));
            stock.setQEntry(0);
        }
        return stock;
    }

    private void calculCump(Stock insertedStock) {
        for (int i = 0; i < listProduit.size(); i++) {
            if(listProduit.get(i).getName().equals(nomProduit)){
                
            }
        }
        double cump = ((insertedStock.getQEntryt() - insertedStock.getQExit())*);
    }
}
