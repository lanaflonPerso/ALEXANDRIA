package com.alexandria.windows.client;

import com.alexandria.dao.ClientDao;
import com.alexandria.dao.DAOFactory;
import com.alexandria.entities.*;
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
import java.util.ArrayList;
import java.util.List;

/**
 * This view shows a list of clients in a JTable and provides
 * New, Edit and Delete buttons to edit the clients.
 * <p>
 * It uses Beans Binding to bind the list of {@link #clients} to the JTable.
 * Beans Binding is also used to enable/disable the Edit and Delete buttons based on selection.
 * <p>
 * The New and Edit buttons use {@link ClientView} to show/edit the selected client in a sub-dialog.
 * <p>
 * Use the {@link #main} method to test this view.
 */
public class ClientsView extends JPanel {

	private ClientDao clientDao = new DAOFactory().getClientDao();

	private static final Logger logger = LogManager.getLogger(ClientsView.class);

	private List<ClientEntity> clients = ObservableCollections.observableList(new ArrayList<ClientEntity>());

	public ClientsView() {

		doClientsList();

		initComponents();
	}

	// Init
	private void doClientsList() {

		List<ClientEntity> clientsList = clientDao.findAll();

		setClients(ObservableCollections.observableList(clientsList));
	}

	// Call by parent window
	public List<ClientEntity> getClients() {
		return clients;
	}

	public void setClients(List<ClientEntity> clients) {
		List<ClientEntity> oldClients = this.clients;
		this.clients = clients;
		firePropertyChange("clients", oldClients, clients);
	}

	/// ACTION LISTENER HANDLER Methods ///BEGIN

	private void newClient() {

		ClientEntity clientDummy = new ClientEntity(new TitleEntity(2, "Madame"), "firstName", "lastName","email", "phone", "password"
				, new AddressEntity("inv. addressLine1", "inv. addressLine2"
				, "inv. city", "inv. state", "inv. post", new CountryEntity(2, "Luxembourg"))
				, new AddressEntity("del. addressLine1", "del. addressLine2"
				, "del. city", "del. state", "del. post", new CountryEntity(3, "Hungary"))
				, new PaymentMethodEntity(2, "Visa"));

		ClientEntity client = showClientDialog("New Client", clientDummy);
		if (client == null)
			return;

		// add new client to clients list
		clients.add(client);

		// select new client in table and scroll row to visible area
		int row = clients.size() - 1;
		clientsTable.setRowSelectionInterval(row, row);
		clientsTable.scrollRectToVisible(clientsTable.getCellRect(row, 0, true));

		// Create in database
		clientDao.create(client);
	}

	private void editClient() {
		int selectedRow = clientsTable.getSelectedRow();
		if (selectedRow < 0)
			return;

		// Find in database
		ClientEntity client = clientDao.find(clients.get(selectedRow).getIdClient());

		// Call dialog box
		ClientEntity newClient = showClientDialog("Edit Client", new ClientEntity(client));
		if (newClient == null)
			return;

		// Set to model
		clients.set(selectedRow, newClient);

		// Update in database
		clientDao.update(newClient);
	}

	private void deleteClient() {
		int[] selectedRows = clientsTable.getSelectedRows();
		if (selectedRows.length == 0)
			return;

		// remove items from database
		for(int selectedRow : selectedRows)
//			clientDao.remove(clients.get(selectedRow).getIdClient());
            clientDao.remove(clients.get(selectedRow));


        // remove items from memory
		for (int i = selectedRows.length - 1; i >= 0; i--)
			clients.remove(selectedRows[i]);

		// select row
		if (clientsTable.getRowCount() > 0) {
			int newSel = Math.min(selectedRows[0], clientsTable.getRowCount() - 1);
			clientsTable.setRowSelectionInterval(newSel, newSel);
			clientsTable.scrollRectToVisible(clientsTable.getCellRect(newSel, 0, true));
		}
	}

	/// ACTION LISTENER HANDLER Methods ///END

	/// DIALOG BOXES ///BEGIN
	/**
	 * Show/edit a single client in a dialog.
	 */
	private ClientEntity showClientDialog(String title, ClientEntity client) {
		ClientView clientView = new ClientView();
		clientView.setClient(client);

		JOptionPane optionPane = new JOptionPane(clientView, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		JDialog dialog = optionPane.createDialog(this, title);
		dialog.setResizable(true);
		dialog.setVisible(true);

		if (!new Integer(JOptionPane.OK_OPTION).equals(optionPane.getValue()))
			return null;

		return clientView.getClient();
	}
	/// DIALOG BOXES ///END

	// Following code is generated //

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        clientsScrollPane = new JScrollPane();
        clientsTable = new JTable();
        buttonPanel = new JPanel();
        windowLabel = new JLabel();
        newClientButton = new JButton();
        editClientButton = new JButton();
        deleteClientButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 0.0, 1.0E-4};

        //======== clientsScrollPane ========
        {

            //---- clientsTable ----
            clientsTable.setPreferredScrollableViewportSize(new Dimension(1024, 768));
            clientsTable.setAutoCreateRowSorter(true);
            clientsScrollPane.setViewportView(clientsTable);
        }
        add(clientsScrollPane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
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
            windowLabel.setText("Clients");
            buttonPanel.add(windowLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- newClientButton ----
            newClientButton.setText("New...");
            newClientButton.setMnemonic('N');
            newClientButton.addActionListener(e -> newClient());
            buttonPanel.add(newClientButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- editClientButton ----
            editClientButton.setText("Edit...");
            editClientButton.setMnemonic('E');
            editClientButton.addActionListener(e -> editClient());
            buttonPanel.add(editClientButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- deleteClientButton ----
            deleteClientButton.setText("Delete");
            deleteClientButton.setMnemonic('D');
            deleteClientButton.addActionListener(e -> deleteClient());
            buttonPanel.add(deleteClientButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
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
                this, (BeanProperty) BeanProperty.create("clients"), clientsTable);
            binding.setEditable(false);
            binding.addColumnBinding(BeanProperty.create("idClient"))
                .setColumnName("Id Client")
                .setColumnClass(Integer.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("titleByTitleId.description"))
                .setColumnName("Title")
                .setColumnClass(String.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("firstName"))
                .setColumnName("First Name")
                .setColumnClass(String.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("lastName"))
                .setColumnName("Last Name")
                .setColumnClass(String.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("email"))
                .setColumnName("Email")
                .setColumnClass(String.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("phone"))
                .setColumnName("Phone")
                .setColumnClass(String.class)
                .setEditable(false);
            bindingGroup.addBinding(binding);
        }
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            clientsTable, ELProperty.create("${selectedElement != null}"),
            editClientButton, BeanProperty.create("enabled")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            clientsTable, ELProperty.create("${selectedElement != null}"),
            deleteClientButton, BeanProperty.create("enabled")));
        bindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JScrollPane clientsScrollPane;
    private JTable clientsTable;
    private JPanel buttonPanel;
    private JLabel windowLabel;
    private JButton newClientButton;
    private JButton editClientButton;
    private JButton deleteClientButton;
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

			// create clients view
			ClientsView clientsView = new ClientsView();
			clientsView.setBorder(new EmptyBorder(10, 10, 10, 10));

			// create frame
			JFrame frame = new JFrame("Clients");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(clientsView);
			frame.pack();
			frame.setVisible(true);
		});
	}
}
