package com.alexandria.windows.order;

import com.alexandria.entities.ClientEntity;
import com.alexandria.entities.OrderHeaderEntity;
import com.alexandria.entities.OrderLineEntity;
import com.alexandria.entities.ShippingMethodEntity;
import com.alexandria.persistence.PersistenceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.SwingBindings;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

/**
 * This view shows/edits a single order.
 * <p>
 * It uses Beans Binding to bind the properties of {@link #order} to input fields.
 * Beans Binding is also used to enable/disable the input fields.
 * <p>
 * Use the {@link #main} method to test this view.
 */
public class OrderView extends JPanel {

    private static final Logger logger = LogManager.getLogger(OrderView.class);

    private OrderHeaderEntity order;
    private List<ShippingMethodEntity> shippingMethods;
    private List<ClientEntity> searchClientsList;
    // Shortcut to orderLines (set in setOrder method)
    private List<OrderLineEntity> orderLines;

    public OrderView() {

        doShippingMethodsList();

        initComponents();
	}

    // Called (get & set) by parent window ClientsView in the showClientDialog method
    public OrderHeaderEntity getOrder() {
        return order;
    }

	public void setOrder(OrderHeaderEntity order) {
		OrderHeaderEntity oldOrder = this.order;
		this.order = order;
		
		// Set shortcut to orderLines
        if(this.order.getOrderLinesByIdOrderHeader() == null) // In case of a new Order (actualy already done in "init" OrderHeaderEntity constructor
            this.order.setOrderLinesByIdOrderHeader( new ArrayList<>() );
        this.orderLines = this.order.getOrderLinesByIdOrderHeader();
		
		firePropertyChange("order", oldOrder, order);
	}

    /// COMBOBOXES ///BEGIN

    // Combobox shippingMethods //
    private void doShippingMethodsList() {

        logger.info("DB_DO_LIST_SHIPPING_METHODS BEGIN");

        EntityManager session = beginTransaction();

        shippingMethods = session.createNamedQuery("ShippingMethodEntity.findAll").getResultList();

        commitTransaction();

        logger.info("DB_DO_LIST_SHIPPING_METHODS END");
    }
    /**
     * Returns a list of available shippingMethods.
     * Used to fill list of {@link #shippingMethods} combobox.
     * See binding: this.shippingMethods --> shippingMethodField.elements
     */
    public List<ShippingMethodEntity> getShippingMethods() {
        return shippingMethods;
    }
    /// COMBOBOXES ///END

    @Override
    public boolean requestFocusInWindow() {
        return firstNameField.requestFocusInWindow();
    }

    /// ACTION LISTENER HANDLER Methods ///BEGIN

    private void newOrderLine() {

        logger.info("NEW_ORDER_LINE BEGIN ");

        // Init new instance
        OrderLineEntity orderLineDummy = new OrderLineEntity(0, this.order);

        OrderLineEntity orderLine = showClientDialog("New Order Line", orderLineDummy);
        if (orderLine == null)
            return;

        // Update the product stock according to the order line quantity
        Integer newStock = orderLine.getProductByProductId().getStock() - orderLine.getQuantity();
        orderLine.getProductByProductId().setStock( newStock );

        // add new order line to order lines list
        orderLines.add(orderLine);

        // select new order line in table and scroll row to visible area
        int row = orderLines.size() - 1;
        orderLinesTable.setRowSelectionInterval(row, row);
        orderLinesTable.scrollRectToVisible(orderLinesTable.getCellRect(row, 0, true));

        // Create in database
        dbCreateOrderLine(orderLine);

        logger.info("NEW_ORDER_LINE END ");
    }

    private void editOrderLine() {

        logger.info("EDIT_ORDER_LINE BEGIN ");

        int selectedRow = orderLinesTable.getSelectedRow();
        if (selectedRow < 0)
            return;

        // Find in model
        OrderLineEntity orderLine = orderLines.get(selectedRow);

        // Refresh in case of desynchronization with database (eg product/stock)
        // dbRefreshOrderLine(orderLine); // FIXME : crash if used (Entity not managed)

        // Backup the old order line quantity in case it is updated
        Integer oldQuantity = orderLine.getQuantity();
        // Backup the old idProduct in case it is updated
        Integer oldIdProduct = orderLine.getProductByProductId().getIdProduct();

        // Call dialog box
        OrderLineEntity newOrderLine = showClientDialog("Edit Order Line", new OrderLineEntity(orderLine));
        if (newOrderLine == null)
            return;

        // Update the product stock according to the new order line quantity //BEGIN
        Integer newQuantity = newOrderLine.getQuantity();
        Integer newStock;

        if( newOrderLine.getProductByProductId().getIdProduct() == oldIdProduct ) {
            Integer actualQuantity = newQuantity - oldQuantity;
            newStock = newOrderLine.getProductByProductId().getStock() - actualQuantity;
        } else {
            newStock = newOrderLine.getProductByProductId().getStock() - newQuantity;
        }
        newOrderLine.getProductByProductId().setStock(newStock);
        // Update the product stock according to the new order line quantity //END

        // Set to model
        orderLines.set(selectedRow, newOrderLine);

        // Update in database
        dbUpdateOrderLine(newOrderLine);

        logger.info("EDIT_ORDER_LINE END ");
    }

    private void deleteOrderLine() {

        logger.info("DELETE_ORDER_LINE BEGIN ");

        // Delete one or multiple order lines according to the selection

        int[] selectedRows = orderLinesTable.getSelectedRows();
        if (selectedRows.length == 0)
            return;

        // Update the products stocks according to the order lines quantities
        for(int selectedRow : selectedRows) {
            // Update the product stock according to the order line quantity
            Integer newStock = orderLines.get(selectedRow).getProductByProductId().getStock() + orderLines.get(selectedRow).getQuantity();
            orderLines.get(selectedRow).getProductByProductId().setStock(newStock);
        }

        // remove items from database
        for(int selectedRow : selectedRows)
            dbRemoveOrderLine(orderLines.get(selectedRow));

        // remove items from memory
        for (int i = selectedRows.length - 1; i >= 0; i--)
            orderLines.remove(selectedRows[i]);

        // select row
        if (orderLinesTable.getRowCount() > 0) {
            int newSel = Math.min(selectedRows[0], orderLinesTable.getRowCount() - 1);
            orderLinesTable.setRowSelectionInterval(newSel, newSel);
            orderLinesTable.scrollRectToVisible(orderLinesTable.getCellRect(newSel, 0, true));
        }

        logger.info("DELETE_ORDER_LINE END ");
    }

    /// ACTION LISTENER HANDLER Methods ///END

    /// DATABASE MANAGEMENT Methods ///BEGIN

    private void dbCreateOrder(OrderHeaderEntity order) {

        logger.info("DB_CREATE_ORDER BEGIN ");

        EntityManager session = beginTransaction();

        session.persist(order);

        commitTransaction();

        logger.info("DB_CREATE_ORDER END ");
    }

    // FIXME : crash if used (Entity not managed)
    private void dbRefreshOrderLine(OrderLineEntity orderLine) {
        logger.info("DB_REFRESH_ORDER_LINE BEGIN " + "orderHeaderId: " + orderLine.getOrderHeaderId() + " productId: " + orderLine.getProductId());

        EntityManager session = beginTransaction();

        session.refresh(orderLine);

        commitTransaction();

        logger.info("DB_REFRESH_ORDER_LINE END " + "orderHeaderId: " + orderLine.getOrderHeaderId() + " productId: " + orderLine.getProductId());
    }

    private void dbCreateOrderLine(OrderLineEntity orderLine) {

        logger.info("DB_CREATE_ORDER_LINE BEGIN ");

        EntityManager session = beginTransaction();

        session.persist(orderLine);

        commitTransaction();

        logger.info("DB_CREATE_ORDER_LINE END ");
    }

    private void dbUpdateOrderLine(OrderLineEntity orderLine) {
        logger.info("DB_UPDATE_ORDER_LINE BEGIN " + "orderHeaderId: " + orderLine.getOrderHeaderId() + " productId: " + orderLine.getProductId());

        EntityManager session = beginTransaction();

        session.merge(orderLine);

        commitTransaction();

        logger.info("DB_UPDATE_ORDER_LINE END " + "orderHeaderId: " + orderLine.getOrderHeaderId() + " productId: " + orderLine.getProductId());
    }

    private void dbRemoveOrderLine(OrderLineEntity orderLine)
    {
        logger.info("DB_REMOVE_ORDER_LINE BEGIN " + "iOrderLine: ");

        EntityManager session = beginTransaction();

        OrderHeaderEntity order = session.getReference(OrderHeaderEntity.class, orderLine.getOrderHeaderByOrderHeaderId().getIdOrderHeader());
        order.getOrderLinesByIdOrderHeader().remove(0);

        commitTransaction();

        logger.info("DB_REMOVE_ORDER_LINE END " + "iOrderLine: ");
    }

    // From JTable
    private ClientEntity dbFindClient(Integer idClient) {

        logger.info("DB_FIND_CLIENT BEGIN " + "idClient: " + idClient);

        EntityManager session = beginTransaction();

        ClientEntity client = session.find(ClientEntity.class, idClient);

        commitTransaction();

        logger.info("DB_FIND_CLIENT END " + "idClient: " + idClient);

        return client;
    }

    /// DATABASE MANAGEMENT Methods ///END

    /// DIALOG BOXES ///BEGIN
    /**
     * Show/edit a single order in a dialog.
     */
    private OrderLineEntity showClientDialog(String title, OrderLineEntity orderLine) {
        OrderLineView orderLineView = new OrderLineView();
        orderLineView.setOrderLine(orderLine);

        JOptionPane optionPane = new JOptionPane(orderLineView, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(this, title);
        dialog.setResizable(true);
        dialog.setVisible(true);

        if (!new Integer(JOptionPane.OK_OPTION).equals(optionPane.getValue()))
            return null;

        return orderLineView.getOrderLine();
    }

    /// ACTION LISTENER HANDLER Methods ///BEGIN

    private void searchClients(ActionEvent e) {

        logger.info("DB_SEARCH_CLIENTS BEGIN");

        EntityManager session = beginTransaction();

        TypedQuery<ClientEntity> query = session.createNamedQuery("ClientEntity.findFromFirstNameLastName", ClientEntity.class);
        query.setParameter("name", e.getActionCommand());
        searchClientsList = query.getResultList();

        commitTransaction();

        // FIXME : Workaround as searchClientsList cannot be mapped in the JTable in JFormDesigner (bug?)
        //  - the select from JTable (selectClient) is bypased and we set the model with the first result of the searchClientsList
        if(searchClientsList != null && searchClientsList.size() > 0) {
            order.setClientByClientId(searchClientsList.get(0));

            // Create new order in database Cf. comments in OrdersView -> newOrder
            dbCreateOrder(order);
        }

        logger.info("DB_SEARCH_CLIENTS END");
    }

    // From JTable, not used as the databinding with searchClientsList doesn't work (JFormDesigner bug ?)
    private void selectClient() {

        int selectedRow = searchClientsTable.getSelectedRow();
        if (selectedRow < 0)
            return;

        // Find in database
        ClientEntity client = dbFindClient(searchClientsList.get(selectedRow).getIdClient());

        // Replace in model
        order.setClientByClientId(client);
    }

    /// ACTION LISTENER HANDLER Methods ///END

    /// DIALOG BOXES ///END

    // Following code is generated //

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        orderInfoLabel = new JLabel();
        usernameLabel = new JLabel();
        orderInfoPane = new JTabbedPane();
        clientPanel = new JPanel();
        titleLabel = new JLabel();
        titleField = new JTextField();
        firstNameLabel = new JLabel();
        firstNameField = new JTextField();
        lastNameLabel = new JLabel();
        lastNameField = new JTextField();
        emailLabel = new JLabel();
        emailField = new JTextField();
        phoneLabel = new JLabel();
        phoneField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        paymentMethodLabel = new JLabel();
        paymentMethodField = new JTextField();
        searchClientsLabel = new JLabel();
        searchClientsField = new JTextField();
        searchClientsResLabel = new JLabel();
        searchClientsScrollPane = new JScrollPane();
        searchClientsTable = new JTable();
        selectClientButton = new JButton();
        orderPanel = new JPanel();
        datePlacedLabel = new JLabel();
        datePlacedField = new JFormattedTextField();
        dateShippedLabel = new JLabel();
        dateShippedField = new JFormattedTextField();
        dateDeliveredLabel = new JLabel();
        dateDeliveredField = new JFormattedTextField();
        commentLabel = new JLabel();
        commentField = new JTextField();
        shippingMethodLabel = new JLabel();
        shippingMethodField = new JComboBox<>();
        shippingMethodChargesLabel = new JLabel();
        shippingMethodChargesField = new JTextField();
        orderLinesPanel = new JPanel();
        orderLinesScrollPane = new JScrollPane();
        orderLinesTable = new JTable();
        buttonPanel = new JPanel();
        windowLabel = new JLabel();
        newOrderLineButton = new JButton();
        editOrderLineButton = new JButton();
        deleteOrderLineButton = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {112, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- orderInfoLabel ----
        orderInfoLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        orderInfoLabel.setText("Order Info:");
        add(orderInfoLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- usernameLabel ----
        usernameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        add(usernameLabel, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== orderInfoPane ========
        {

            //======== clientPanel ========
            {
                clientPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)clientPanel.getLayout()).columnWidths = new int[] {0, 210, 150, 0};
                ((GridBagLayout)clientPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)clientPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)clientPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- titleLabel ----
                titleLabel.setText("Title");
                titleLabel.setDisplayedMnemonic('T');
                titleLabel.setLabelFor(titleField);
                clientPanel.add(titleLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- titleField ----
                titleField.setEditable(false);
                clientPanel.add(titleField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- firstNameLabel ----
                firstNameLabel.setText("FirstName");
                firstNameLabel.setDisplayedMnemonic('F');
                firstNameLabel.setLabelFor(firstNameField);
                clientPanel.add(firstNameLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- firstNameField ----
                firstNameField.setEditable(false);
                clientPanel.add(firstNameField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lastNameLabel ----
                lastNameLabel.setText("LastName");
                lastNameLabel.setDisplayedMnemonic('L');
                lastNameLabel.setLabelFor(lastNameField);
                clientPanel.add(lastNameLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lastNameField ----
                lastNameField.setEditable(false);
                clientPanel.add(lastNameField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- emailLabel ----
                emailLabel.setText("Email");
                emailLabel.setDisplayedMnemonic('E');
                emailLabel.setLabelFor(emailField);
                clientPanel.add(emailLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- emailField ----
                emailField.setEditable(false);
                clientPanel.add(emailField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- phoneLabel ----
                phoneLabel.setText("Phone");
                phoneLabel.setDisplayedMnemonic('P');
                phoneLabel.setLabelFor(phoneField);
                clientPanel.add(phoneLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- phoneField ----
                phoneField.setEditable(false);
                clientPanel.add(phoneField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- passwordLabel ----
                passwordLabel.setText("Password");
                passwordLabel.setDisplayedMnemonic('P');
                passwordLabel.setLabelFor(passwordField);
                clientPanel.add(passwordLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- passwordField ----
                passwordField.setToolTipText("Your password must be at least 8 characters in length.\nYour password must contain at least 3 out of the 4 following items:\nUppercase letters (A-Z)\nLowercase letters (a-z)\nNumbers (0-9)\nSymbols or special characters");
                passwordField.setEditable(false);
                clientPanel.add(passwordField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- paymentMethodLabel ----
                paymentMethodLabel.setText("Payment method");
                paymentMethodLabel.setDisplayedMnemonic('P');
                paymentMethodLabel.setLabelFor(paymentMethodField);
                clientPanel.add(paymentMethodLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- paymentMethodField ----
                paymentMethodField.setEditable(false);
                clientPanel.add(paymentMethodField, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- searchClientsLabel ----
                searchClientsLabel.setDisplayedMnemonic('C');
                searchClientsLabel.setLabelFor(searchClientsField);
                searchClientsLabel.setIcon(new ImageIcon(getClass().getResource("/icons/Search_24px.png")));
                searchClientsLabel.setText("Clients");
                clientPanel.add(searchClientsLabel, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- searchClientsField ----
                searchClientsField.addActionListener(e -> searchClients(e));
                clientPanel.add(searchClientsField, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- searchClientsResLabel ----
                searchClientsResLabel.setText("Search result");
                searchClientsResLabel.setDisplayedMnemonic('S');
                searchClientsResLabel.setLabelFor(searchClientsTable);
                clientPanel.add(searchClientsResLabel, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== searchClientsScrollPane ========
                {

                    //---- searchClientsTable ----
                    searchClientsTable.setPreferredScrollableViewportSize(new Dimension(1024, 768));
                    searchClientsScrollPane.setViewportView(searchClientsTable);
                }
                clientPanel.add(searchClientsScrollPane, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- selectClientButton ----
                selectClientButton.setText("Select...");
                selectClientButton.setMnemonic('S');
                selectClientButton.addActionListener(e -> selectClient());
                clientPanel.add(selectClientButton, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }
            orderInfoPane.addTab("Client", clientPanel);

            //======== orderPanel ========
            {
                orderPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)orderPanel.getLayout()).columnWidths = new int[] {0, 270, 0};
                ((GridBagLayout)orderPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)orderPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)orderPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- datePlacedLabel ----
                datePlacedLabel.setText("Date placed");
                datePlacedLabel.setDisplayedMnemonic('L');
                datePlacedLabel.setLabelFor(datePlacedField);
                orderPanel.add(datePlacedLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                orderPanel.add(datePlacedField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- dateShippedLabel ----
                dateShippedLabel.setText("Date shipped");
                dateShippedLabel.setDisplayedMnemonic('L');
                dateShippedLabel.setLabelFor(dateShippedField);
                orderPanel.add(dateShippedLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                orderPanel.add(dateShippedField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- dateDeliveredLabel ----
                dateDeliveredLabel.setText("Date delivered");
                dateDeliveredLabel.setDisplayedMnemonic('C');
                dateDeliveredLabel.setLabelFor(dateDeliveredField);
                orderPanel.add(dateDeliveredLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                orderPanel.add(dateDeliveredField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- commentLabel ----
                commentLabel.setText("Comment");
                commentLabel.setDisplayedMnemonic('S');
                commentLabel.setLabelFor(commentField);
                orderPanel.add(commentLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                orderPanel.add(commentField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- shippingMethodLabel ----
                shippingMethodLabel.setText("Shipping method");
                shippingMethodLabel.setDisplayedMnemonic('P');
                shippingMethodLabel.setLabelFor(shippingMethodField);
                orderPanel.add(shippingMethodLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                orderPanel.add(shippingMethodField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- shippingMethodChargesLabel ----
                shippingMethodChargesLabel.setText("Shipping method charges");
                shippingMethodChargesLabel.setDisplayedMnemonic('S');
                shippingMethodChargesLabel.setLabelFor(shippingMethodChargesField);
                orderPanel.add(shippingMethodChargesLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- shippingMethodChargesField ----
                shippingMethodChargesField.setEditable(false);
                orderPanel.add(shippingMethodChargesField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            orderInfoPane.addTab("Order", orderPanel);

            //======== orderLinesPanel ========
            {
                orderLinesPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)orderLinesPanel.getLayout()).columnWidths = new int[] {0, 29, 713, 0};
                ((GridBagLayout)orderLinesPanel.getLayout()).rowHeights = new int[] {431, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)orderLinesPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)orderLinesPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //======== orderLinesScrollPane ========
                {

                    //---- orderLinesTable ----
                    orderLinesTable.setRowHeight(250);
                    orderLinesTable.setAutoCreateRowSorter(true);
                    orderLinesTable.setPreferredScrollableViewportSize(new Dimension(750, 550));
                    orderLinesScrollPane.setViewportView(orderLinesTable);
                }
                orderLinesPanel.add(orderLinesScrollPane, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== buttonPanel ========
                {
                    buttonPanel.setLayout(new GridBagLayout());
                    ((GridBagLayout)buttonPanel.getLayout()).columnWidths = new int[] {0, 85, 85, 80, 0};
                    ((GridBagLayout)buttonPanel.getLayout()).rowHeights = new int[] {0, 0};
                    ((GridBagLayout)buttonPanel.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)buttonPanel.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

                    //---- windowLabel ----
                    windowLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
                    windowLabel.setText("Total Cost Ex Vat");
                    windowLabel.setForeground(new Color(255, 102, 0));
                    buttonPanel.add(windowLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- newOrderLineButton ----
                    newOrderLineButton.setText("New...");
                    newOrderLineButton.setMnemonic('N');
                    newOrderLineButton.addActionListener(e -> newOrderLine());
                    buttonPanel.add(newOrderLineButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- editOrderLineButton ----
                    editOrderLineButton.setText("Edit...");
                    editOrderLineButton.setMnemonic('E');
                    editOrderLineButton.addActionListener(e -> editOrderLine());
                    buttonPanel.add(editOrderLineButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- deleteOrderLineButton ----
                    deleteOrderLineButton.setText("Delete");
                    deleteOrderLineButton.setMnemonic('D');
                    deleteOrderLineButton.addActionListener(e -> deleteOrderLine());
                    buttonPanel.add(deleteOrderLineButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                orderLinesPanel.add(buttonPanel, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            orderInfoPane.addTab("Order lines", orderLinesPanel);
        }
        add(orderInfoPane, new GridBagConstraints(0, 8, 2, 10, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${order.clientByClientId.firstName} ${order.clientByClientId.lastName}"),
            usernameLabel, BeanProperty.create("text"), "displayNameTitle"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.clientByClientId.firstName"),
            firstNameField, BeanProperty.create("text"), "firstName"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.clientByClientId.lastName"),
            lastNameField, BeanProperty.create("text"), "lastName"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.clientByClientId.email"),
            emailField, BeanProperty.create("text"), "email"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.clientByClientId.phone"),
            phoneField, BeanProperty.create("text"), "phone"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.clientByClientId.password"),
            passwordField, BeanProperty.create("text"), "password"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.comment"),
            commentField, BeanProperty.create("text"), "addressByDeliveryAddressId.state"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.clientByClientId.titleByTitleId.description"),
            titleField, BeanProperty.create("text"), "selectedTitle"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.clientByClientId.paymentMethodByPaymentMethodId.description"),
            paymentMethodField, BeanProperty.create("text"), "selectedShippingMethod"));
        bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("shippingMethods"), shippingMethodField));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.shippingMethodByShippingMethodId"),
            shippingMethodField, BeanProperty.create("selectedItem")));
        {
            var binding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE,
                this, (BeanProperty) BeanProperty.create("order.orderLinesByIdOrderHeader"), orderLinesTable);
            binding.setEditable(false);
            binding.addColumnBinding(BeanProperty.create("orderHeaderId"))
                .setColumnName("Order Header Id")
                .setColumnClass(Integer.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("productId"))
                .setColumnName("Product Id")
                .setColumnClass(Integer.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("productByProductId.name"))
                .setColumnName("Name")
                .setColumnClass(String.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("productByProductId.priceExVat"))
                .setColumnName("Price Ex Vat")
                .setColumnClass(BigDecimal.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("quantity"))
                .setColumnName("Quantity")
                .setColumnClass(Integer.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("productByProductId.image"))
                .setColumnName("Image")
                .setColumnClass(ImageIcon.class)
                .setEditable(false);
            bindingGroup.addBinding(binding);
        }
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.datePlaced"),
            datePlacedField, BeanProperty.create("value")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.dateShipped"),
            dateShippedField, BeanProperty.create("value")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("order.dateDelivered"),
            dateDeliveredField, BeanProperty.create("value")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            orderLinesTable, ELProperty.create("${selectedElement != null}"),
            editOrderLineButton, BeanProperty.create("enabled")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            orderLinesTable, ELProperty.create("${selectedElement != null}"),
            deleteOrderLineButton, BeanProperty.create("enabled")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("Total Cost Ex Vat : ${order.totalCostExVat} $"),
            windowLabel, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            searchClientsTable, ELProperty.create("${selectedElement != null}"),
            selectClientButton, BeanProperty.create("enabled")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            shippingMethodField, BeanProperty.create("selectedItem.charges"),
            shippingMethodChargesField, BeanProperty.create("text")));
        bindingGroup.bind();
        enablementBindingGroup = new BindingGroup();
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${order != null}"),
            datePlacedField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${order != null}"),
            dateShippedField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${order != null}"),
            dateDeliveredField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${order != null}"),
            commentField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            datePlacedField, BeanProperty.create("editable"),
            datePlacedLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            dateShippedField, BeanProperty.create("editable"),
            dateShippedLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            dateDeliveredField, BeanProperty.create("editable"),
            dateDeliveredLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            commentField, BeanProperty.create("editable"),
            commentLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            titleField, BeanProperty.create("enabled"),
            titleLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            paymentMethodField, BeanProperty.create("enabled"),
            paymentMethodLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel orderInfoLabel;
    private JLabel usernameLabel;
    private JTabbedPane orderInfoPane;
    private JPanel clientPanel;
    private JLabel titleLabel;
    private JTextField titleField;
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel paymentMethodLabel;
    private JTextField paymentMethodField;
    private JLabel searchClientsLabel;
    private JTextField searchClientsField;
    private JLabel searchClientsResLabel;
    private JScrollPane searchClientsScrollPane;
    private JTable searchClientsTable;
    private JButton selectClientButton;
    private JPanel orderPanel;
    private JLabel datePlacedLabel;
    private JFormattedTextField datePlacedField;
    private JLabel dateShippedLabel;
    private JFormattedTextField dateShippedField;
    private JLabel dateDeliveredLabel;
    private JFormattedTextField dateDeliveredField;
    private JLabel commentLabel;
    private JTextField commentField;
    private JLabel shippingMethodLabel;
    private JComboBox<ShippingMethodEntity> shippingMethodField;
    private JLabel shippingMethodChargesLabel;
    private JTextField shippingMethodChargesField;
    private JPanel orderLinesPanel;
    private JScrollPane orderLinesScrollPane;
    private JTable orderLinesTable;
    private JPanel buttonPanel;
    private JLabel windowLabel;
    private JButton newOrderLineButton;
    private JButton editOrderLineButton;
    private JButton deleteOrderLineButton;
    private BindingGroup bindingGroup;
    private BindingGroup enablementBindingGroup;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	//---- for testing --------------------------------------------------------

	public static void main(String[] args) {

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

            // create order view
			OrderView orderView = new OrderView();
			orderView.setBorder(new EmptyBorder(10, 10, 10, 10));

            // init demo data
            // TODO : complete initialization
            OrderHeaderEntity order = new OrderHeaderEntity(new java.sql.Date(System.currentTimeMillis())
                    , new java.sql.Date(System.currentTimeMillis())
                    , new java.sql.Date(System.currentTimeMillis())
                    , "comment"
                    , new ShippingMethodEntity(1, "DHL", new BigDecimal(18.4065)));
            orderView.setOrder(order);

            // create frame
			JFrame frame = new JFrame("Order");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(orderView);
			frame.pack();
			frame.setVisible(true);
		});
	}
}
