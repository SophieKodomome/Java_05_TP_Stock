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
    private JComboBox<String> produitCombobox = new JComboBox<>();
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
            produitCombobox.addItem(listProduit.get(i).getName());
        }
        contentPanel.add(depotLabel);
        contentPanel.add(depotComboBox);
        contentPanel.add(produitLabel);
        contentPanel.add(produitCombobox);
        contentPanel.add(quantiteLabel);
        contentPanel.add(quantityTextField);
        contentPanel.add(submitButton);

        this.add(contentPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {

            }
        });
    }
}
