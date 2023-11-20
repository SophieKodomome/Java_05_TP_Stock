package visuel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PageAccueil extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton title = new JButton("Home");
    private JButton goToUpdateStockButton = new JButton("Update stocks");
    private JButton goToListStockButton = new JButton("See stocks");
    private JButton goToListCategory = new JButton("See category with Value");
    private JPanel boxContent = new JPanel(new GridLayout(3, 1));

    public PageAccueil() {
        this.setTitle("Home");
        this.setLayout(new BorderLayout());
        this.setSize(screenSize.width, screenSize.height);
        boxContent.add(goToUpdateStockButton);
        boxContent.add(goToListStockButton);
        boxContent.add(goToListCategory);

        goToUpdateStockButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                setVisible(false);
                PageStock pageStock = new PageStock();
            }
        });
        goToListStockButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                setVisible(false);
                PagePreview PagePreview = new PagePreview();
            }
        });

        goToListCategory.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent action) {
                setVisible(false);
                PageEtatCategory PageEtatCategory = new PageEtatCategory();
            }
        });
        this.add(title, BorderLayout.NORTH);
        this.add(boxContent, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
