package com.alexandria.windows.order;

import com.alexandria.dao.DAOFactory;
import com.alexandria.dao.OrderHeaderDao;
import com.alexandria.entities.OrderHeaderEntity;
import com.alexandria.entities.ShippingMethodEntity;
import com.alexandria.persistence.PersistenceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.SwingBindings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This view shows a list of orders in a JTable and provides
 * New, Edit and Delete buttons to edit the orders.
 * <p>
 * It uses Beans Binding to bind the list of {@link #orders} to the JTable.
 * Beans Binding is also used to enable/disable the Edit and Delete buttons based on selection.
 * <p>
 * The New and Edit buttons use {@link OrderView} to show/edit the selected order in a sub-dialog.
 * <p>
 * Use the {@link #main} method to test this view.
 */
public class OrdersView extends JPanel {

	private static final Logger logger = LogManager.getLogger(OrdersView.class);

	private List<OrderHeaderEntity> orders = ObservableCollections.observableList(new ArrayList<OrderHeaderEntity>());

	private OrderHeaderDao orderHeaderDao = new DAOFactory().getOrderHeaderDao();

	public OrdersView() {

		doOrdersList();

		initComponents();
	}

	// Init
	public void doOrdersList() {

		List<OrderHeaderEntity> ordersList = orderHeaderDao.findAll();

		setOrders(ObservableCollections.observableList(ordersList));
	}

	// Call by parent window
	public List<OrderHeaderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderHeaderEntity> orders) {
		List<OrderHeaderEntity> oldOrders = this.orders;
		this.orders = orders;
		firePropertyChange("orders", oldOrders, orders);
	}

	/// ACTION LISTENER HANDLER Methods ///BEGIN

	private void newOrder() {

		// clientByClientId is set after the search of the client (OrderView -> searchClients)
		OrderHeaderEntity orderDummy = new OrderHeaderEntity(new Date(System.currentTimeMillis())
				, new Date(System.currentTimeMillis())
				, new Date(System.currentTimeMillis())
				, "comment"
				, new ShippingMethodEntity(1, "DHL", new BigDecimal(18.4065)));

		// The new order header entity is created before adding new order lines in database (OrderView -> newOrderLine)
        // It is created after the client is set into the model (OrderView -> searchClients) because of the FK client in OrderHeaderEntity.
        // Moreover :
        // the FK orderHeaderId in OrderLineEntity is set in the constructor OrderLineEntity called in OrderView -> newOrderLine
        // the FK productId in OrderLineEntity is set after the search of the product (OrderLineView -> searchProducts)
        // the referenced entity orderHeaderByOrderHeaderId in OrderLineEntity is set in the constructor OrderLineEntity called in OrderView -> newOrderLine
		// the referenced entity productByProductId in OrderLineEntity is set after the search of the product (OrderLineView -> searchProducts)

		OrderHeaderEntity order = showClientDialog("New Order", orderDummy);
		if (order == null)
			return;

		// add new order to orders list
		orders.add(order);

		// select new order in table and scroll row to visible area
		int row = orders.size() - 1;
		ordersTable.setRowSelectionInterval(row, row);
		ordersTable.scrollRectToVisible(ordersTable.getCellRect(row, 0, true));

		// Update in database
		orderHeaderDao.update(order);
	}

	private void editOrder() {
		int selectedRow = ordersTable.getSelectedRow();
		if (selectedRow < 0)
			return;

		// Find in database
		OrderHeaderEntity order = orderHeaderDao.find(orders.get(selectedRow).getIdOrderHeader());

		// Call dialog box
		OrderHeaderEntity newOrder = showClientDialog("Edit Order", new OrderHeaderEntity(order));
		if (newOrder == null)
			return;

		// Set to model
		orders.set(selectedRow, newOrder);

		// Update in database
		orderHeaderDao.update(newOrder);
	}

	private void deleteOrder() {
		int[] selectedRows = ordersTable.getSelectedRows();
		if (selectedRows.length == 0)
			return;

		// remove items from database
		for(int selectedRow : selectedRows)
			orderHeaderDao.remove(orders.get(selectedRow));

        // remove items from memory
		for (int i = selectedRows.length - 1; i >= 0; i--)
			orders.remove(selectedRows[i]);

		// select row
		if (ordersTable.getRowCount() > 0) {
			int newSel = Math.min(selectedRows[0], ordersTable.getRowCount() - 1);
			ordersTable.setRowSelectionInterval(newSel, newSel);
			ordersTable.scrollRectToVisible(ordersTable.getCellRect(newSel, 0, true));
		}
	}

	/// ACTION LISTENER HANDLER Methods ///END

	/// DIALOG BOXES ///BEGIN
	/**
	 * Show/edit a single order in a dialog.
	 */
	private OrderHeaderEntity showClientDialog(String title, OrderHeaderEntity order) {
		OrderView orderView = new OrderView();
		orderView.setOrder(order);

		JOptionPane optionPane = new JOptionPane(orderView, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		JDialog dialog = optionPane.createDialog(this, title);
		dialog.setResizable(true);
		dialog.setVisible(true);

		if (!new Integer(JOptionPane.OK_OPTION).equals(optionPane.getValue()))
			return null;

		return orderView.getOrder();
	}

	/// DIALOG BOXES ///END

	// Following code is generated //

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        ordersScrollPane = new JScrollPane();
        ordersTable = new JTable();
        buttonPanel = new JPanel();
        windowLabel = new JLabel();
        newOrderButton = new JButton();
        editOrderButton = new JButton();
        deleteOrderButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 0.0, 1.0E-4};

        //======== ordersScrollPane ========
        {

            //---- ordersTable ----
            ordersTable.setPreferredScrollableViewportSize(new Dimension(1024, 768));
            ordersTable.setAutoCreateRowSorter(true);
            ordersScrollPane.setViewportView(ordersTable);
        }
        add(ordersScrollPane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
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
            windowLabel.setText("Orders");
            buttonPanel.add(windowLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- newOrderButton ----
            newOrderButton.setText("New...");
            newOrderButton.setMnemonic('N');
            newOrderButton.addActionListener(e -> newOrder());
            buttonPanel.add(newOrderButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- editOrderButton ----
            editOrderButton.setText("Edit...");
            editOrderButton.setMnemonic('E');
            editOrderButton.addActionListener(e -> editOrder());
            buttonPanel.add(editOrderButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- deleteOrderButton ----
            deleteOrderButton.setText("Delete");
            deleteOrderButton.setMnemonic('D');
            deleteOrderButton.addActionListener(e -> deleteOrder());
            buttonPanel.add(deleteOrderButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(buttonPanel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- bindings ----
        bindingGroup = new BindingGroup();
        {
            var binding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE,
                this, (BeanProperty) BeanProperty.create("orders"), ordersTable);
            binding.setEditable(false);
            binding.addColumnBinding(BeanProperty.create("idOrderHeader"))
                .setColumnName("Id Order")
                .setColumnClass(Integer.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("clientByClientId.titleByTitleId.description"))
                .setColumnName("Client Title")
                .setColumnClass(String.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("clientByClientId.firstName"))
                .setColumnName("Client First Name")
                .setColumnClass(String.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("clientByClientId.lastName"))
                .setColumnName("Client Last Name")
                .setColumnClass(String.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("datePlaced"))
                .setColumnName("Date Placed")
                .setColumnClass(Date.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("dateShipped"))
                .setColumnName("Date Shipped")
                .setColumnClass(Date.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("dateDelivered"))
                .setColumnName("Date Delivered")
                .setColumnClass(Date.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("comment"))
                .setColumnName("Comment")
                .setColumnClass(String.class)
                .setEditable(false);
            bindingGroup.addBinding(binding);
        }
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            ordersTable, ELProperty.create("${selectedElement != null}"),
            editOrderButton, BeanProperty.create("enabled")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            ordersTable, ELProperty.create("${selectedElement != null}"),
            deleteOrderButton, BeanProperty.create("enabled")));
        bindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JScrollPane ordersScrollPane;
    private JTable ordersTable;
    private JPanel buttonPanel;
    private JLabel windowLabel;
    private JButton newOrderButton;
    private JButton editOrderButton;
    private JButton deleteOrderButton;
    private BindingGroup bindingGroup;
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

			// create orders view
			OrdersView clientsView = new OrdersView();
			clientsView.setBorder(new EmptyBorder(10, 10, 10, 10));

			// create frame
			JFrame frame = new JFrame("Orders");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(clientsView);
			frame.pack();
			frame.setVisible(true);
		});
	}
}
