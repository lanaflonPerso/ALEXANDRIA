package com.alexandria.windows.mainwindow;

import com.alexandria.persistence.PersistenceUtils;
import com.alexandria.windows.client.ClientsView;
import com.alexandria.windows.order.OrdersView;
import com.alexandria.windows.product.ProductsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    ClientsView clientsView;
    Component productsView;
    OrdersView ordersView;
    Component eventsView;

    public MainWindow() {

        initComponents();
    }

    private void clientsMouseClicked(MouseEvent e) {

        if(clientsView == null)
            clientsView = new ClientsView();

        // refresh clientsList from database in case database is updated outside this thread
        clientsView.doClientsList();

        refreshAll(clientsView);
    }

    private void productsMouseClicked(MouseEvent e) {

        if(productsView == null)
            productsView = new ProductsView();

        // TODO : refresh productsList from database in case database is updated outside this thread

        refreshAll(productsView);
    }

    private void ordersButtonMouseClicked(MouseEvent e) {
        if(ordersView == null)
            ordersView = new OrdersView();

        // refresh ordersList from database in case database is updated outside this thread
        ordersView.doOrdersList();

        refreshAll(ordersView);
    }

    private void eventsButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        // TODO : refresh eventsList from database in case database is updated outside this thread
    }

    private void refreshAll(Component comp)
    {
        // TODO : improve performance by removing only the active panel
        //remove panels
        container.removeAll();
        container.setLayout(new GridLayout());
        //add panel
        container.add(comp);
        container.revalidate();
        container.repaint();
    }

    private void thisWindowClosing(WindowEvent e) {
        PersistenceUtils.shutdown();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        container = new JPanel();
        imgBack = new JLabel();
        panel2 = new JPanel();
        searchField = new JTextField();
        searchLabel = new JLabel();
        menu = new JPanel();
        clientsButton = new JButton();
        productsButton = new JButton();
        eventsButton = new JButton();
        ordersButton = new JButton();
        clientsLabel = new JLabel();
        productsLabel = new JLabel();
        ordersLabel = new JLabel();
        eventsLabel = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(1024, 768));
        setTitle("Alexandria's overview");
        setVisible(true);
        setBackground(Color.black);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        var contentPane = getContentPane();

        //======== container ========
        {
            container.setBackground(Color.black);
            container.setPreferredSize(null);
            container.setMinimumSize(new Dimension(1024, 768));

            //---- imgBack ----
            imgBack.setIcon(new ImageIcon(getClass().getResource("/images/thumb-book-1920-1213.jpg")));

            GroupLayout containerLayout = new GroupLayout(container);
            container.setLayout(containerLayout);
            containerLayout.setHorizontalGroup(
                containerLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, containerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imgBack))
            );
            containerLayout.setVerticalGroup(
                containerLayout.createParallelGroup()
                    .addComponent(imgBack, GroupLayout.Alignment.TRAILING)
            );
        }

        //======== panel2 ========
        {
            panel2.setBackground(Color.black);
            panel2.setPreferredSize(new Dimension(980, 62));

            //---- searchLabel ----
            searchLabel.setIcon(new ImageIcon(getClass().getResource("/icons/Search_24px.png")));

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(searchLabel)
                            .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(14, Short.MAX_VALUE))
            );
        }

        //======== menu ========
        {
            menu.setBackground(Color.black);
            menu.setPreferredSize(new Dimension(90, 329));

            //---- clientsButton ----
            clientsButton.setBorder(null);
            clientsButton.setIcon(new ImageIcon(getClass().getResource("/icons/User_24px.png")));
            clientsButton.setBackground(Color.black);
            clientsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            clientsButton.setFocusPainted(false);
            clientsButton.setRolloverIcon(new ImageIcon(getClass().getResource("/icons/User_over.png")));
            clientsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clientsMouseClicked(e);
                }
            });

            //---- productsButton ----
            productsButton.setFocusPainted(false);
            productsButton.setBorder(null);
            productsButton.setIcon(new ImageIcon(getClass().getResource("/icons/Library_24px.png")));
            productsButton.setBackground(Color.black);
            productsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            productsButton.setRolloverIcon(new ImageIcon(getClass().getResource("/icons/Library_over.png")));
            productsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    productsMouseClicked(e);
                }
            });

            //---- eventsButton ----
            eventsButton.setFocusPainted(false);
            eventsButton.setBorder(null);
            eventsButton.setIcon(new ImageIcon(getClass().getResource("/icons/Event_24px.png")));
            eventsButton.setBackground(Color.black);
            eventsButton.setRolloverIcon(new ImageIcon(getClass().getResource("/icons/Event_over.png")));
            eventsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            eventsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    eventsButtonMouseClicked(e);
                }
            });

            //---- ordersButton ----
            ordersButton.setFocusPainted(false);
            ordersButton.setBorder(null);
            ordersButton.setIcon(new ImageIcon(getClass().getResource("/icons/Sell_24px.png")));
            ordersButton.setBackground(Color.black);
            ordersButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            ordersButton.setRolloverIcon(new ImageIcon(getClass().getResource("/icons/Sell_over.png")));
            ordersButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ordersButtonMouseClicked(e);
                }
            });

            //---- clientsLabel ----
            clientsLabel.setText("Clients");
            clientsLabel.setForeground(new Color(207, 170, 46));
            clientsLabel.setHorizontalAlignment(SwingConstants.CENTER);

            //---- productsLabel ----
            productsLabel.setText("Products");
            productsLabel.setForeground(new Color(207, 170, 46));
            productsLabel.setHorizontalAlignment(SwingConstants.CENTER);

            //---- ordersLabel ----
            ordersLabel.setText("Orders");
            ordersLabel.setForeground(new Color(207, 170, 46));
            ordersLabel.setHorizontalAlignment(SwingConstants.CENTER);

            //---- eventsLabel ----
            eventsLabel.setText("Events");
            eventsLabel.setForeground(new Color(207, 170, 46));
            eventsLabel.setHorizontalAlignment(SwingConstants.CENTER);

            GroupLayout menuLayout = new GroupLayout(menu);
            menu.setLayout(menuLayout);
            menuLayout.setHorizontalGroup(
                menuLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(menuLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(ordersLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                            .addGroup(menuLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(clientsButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clientsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eventsButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(productsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ordersButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(productsButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eventsLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
            );
            menuLayout.setVerticalGroup(
                menuLayout.createParallelGroup()
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(clientsButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clientsLabel)
                        .addGap(18, 18, 18)
                        .addComponent(productsButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productsLabel)
                        .addGap(18, 18, 18)
                        .addComponent(ordersButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ordersLabel)
                        .addGap(18, 18, 18)
                        .addComponent(eventsButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventsLabel)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(menu, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(container, GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE))
                .addComponent(panel2, GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(menu, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                        .addComponent(container, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
                    .addGap(0, 0, 0))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JPanel container;
    private JLabel imgBack;
    private JPanel panel2;
    private JTextField searchField;
    private JLabel searchLabel;
    private JPanel menu;
    private JButton clientsButton;
    private JButton productsButton;
    private JButton eventsButton;
    private JButton ordersButton;
    private JLabel clientsLabel;
    private JLabel productsLabel;
    private JLabel ordersLabel;
    private JLabel eventsLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[]args){
        SwingUtilities.invokeLater(() -> {

            // Look & Feel
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                // If Nimbus is not available, you can set the GUI to another look and feel.
            }

            // Hibernate init
            PersistenceUtils.init();

            new MainWindow();
        });
    }
}
