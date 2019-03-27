package com.alexandria.windows.client;

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
import org.jdesktop.swingbinding.SwingBindings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * This view shows/edits a single client.
 * <p>
 * It uses Beans Binding to bind the properties of {@link #client} to input fields.
 * Beans Binding is also used to enable/disable the input fields.
 * <p>
 * Use the {@link #main} method to test this view.
 */
public class ClientView extends JPanel {

    private static final Logger logger = LogManager.getLogger(ClientView.class);

    private ClientEntity client;

    /// COMBOBOXES
    private List<CountryEntity> countries = new DAOFactory().getCountryDao().doCountriesList();
    private List<PaymentMethodEntity> paymentMethods = new DAOFactory().getPaymentMethodDao().doPaymentMethodsList();
    private List<TitleEntity> titles = new DAOFactory().getTitleDao().doTitlesList();

    public ClientView() {

        initComponents();
	}

    // Called (get & set) by parent window ClientsView in the showClientDialog method
    public ClientEntity getClient() {
        return client;
    }

	public void setClient(ClientEntity client) {
		ClientEntity oldClient = this.client;
		this.client = client;
		firePropertyChange("client", oldClient, client);
	}

    /// COMBOBOXES ///BEGIN
    // Combobox titles //
    /**
     * Returns a list of available countries.
     * Used to fill list of {@link #titleField} combobox.
     * See binding: this.countries --> addressCountryField.elements
     */
    public List<TitleEntity> getTitles() {
        return titles;
    }

    // Combobox countries //
    /**
     * Returns a list of available countries.
     * Used to fill list of {@link #addressInvoiceCountryField #addressDeliveryCountryField} combobox.
     * See binding: this.countries --> addressCountryField.elements
     */
    public List<CountryEntity> getCountries() {
        return countries;
    }

    // Combobox paymentMethods //
    /**
     * Returns a list of available paymentMethods.
     * Used to fill list of {@link #paymentMethods} combobox.
     * See binding: this.paymentMethods --> paymentMethodField.elements
     */
    public List<PaymentMethodEntity> getPaymentMethods() {
        return paymentMethods;
    }
    /// COMBOBOXES ///END

    @Override
    public boolean requestFocusInWindow() {
        return firstNameField.requestFocusInWindow();
    }

    // Following code is generated //

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        clientInfoLabel = new JLabel();
        usernameLabel = new JLabel();
        clientInfoPane = new JTabbedPane();
        personalPanel = new JPanel();
        titleLabel = new JLabel();
        titleField = new JComboBox<>();
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
        paymentMethodField = new JComboBox<>();
        addressDeliveryPanel = new JPanel();
        addressDeliveryLine1Label = new JLabel();
        addressDeliveryLine1Field = new JTextField();
        addressDeliveryLine2Label = new JLabel();
        addressDeliveryLine2Field = new JTextField();
        addressDeliveryCityLabel = new JLabel();
        addressDeliveryCityField = new JTextField();
        addressDeliveryStateLabel = new JLabel();
        addressDeliveryStateField = new JTextField();
        addressDeliveryPostalCodeLabel = new JLabel();
        addressDeliveryPostalCodeField = new JTextField();
        addressDeliveryCountryLabel = new JLabel();
        addressDeliveryCountryField = new JComboBox<>();
        addressInvoicePanel = new JPanel();
        addressInvoiceLine1Label = new JLabel();
        addressInvoiceLine1Field = new JTextField();
        addressInvoiceLine2Label = new JLabel();
        addressInvoiceLine2Field = new JTextField();
        addressInvoiceCityLabel = new JLabel();
        addressInvoiceCityField = new JTextField();
        addressInvoiceStateLabel = new JLabel();
        addressInvoiceStateField = new JTextField();
        addressInvoicePostalCodeLabel = new JLabel();
        addressInvoicePostalCodeField = new JTextField();
        addressInvoiceCountryLabel = new JLabel();
        addressInvoiceCountryField = new JComboBox<>();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {112, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- clientInfoLabel ----
        clientInfoLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        clientInfoLabel.setText("Client Info:");
        add(clientInfoLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- usernameLabel ----
        usernameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        add(usernameLabel, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== clientInfoPane ========
        {
            clientInfoPane.setMinimumSize(new Dimension(400, 600));

            //======== personalPanel ========
            {
                personalPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)personalPanel.getLayout()).columnWidths = new int[] {0, 210, 150, 0};
                ((GridBagLayout)personalPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)personalPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)personalPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- titleLabel ----
                titleLabel.setText("Title");
                titleLabel.setDisplayedMnemonic('T');
                titleLabel.setLabelFor(titleField);
                personalPanel.add(titleLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                personalPanel.add(titleField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- firstNameLabel ----
                firstNameLabel.setText("FirstName");
                firstNameLabel.setDisplayedMnemonic('F');
                firstNameLabel.setLabelFor(firstNameField);
                personalPanel.add(firstNameLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                personalPanel.add(firstNameField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lastNameLabel ----
                lastNameLabel.setText("LastName");
                lastNameLabel.setDisplayedMnemonic('L');
                lastNameLabel.setLabelFor(lastNameField);
                personalPanel.add(lastNameLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                personalPanel.add(lastNameField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- emailLabel ----
                emailLabel.setText("Email");
                emailLabel.setDisplayedMnemonic('E');
                emailLabel.setLabelFor(emailField);
                personalPanel.add(emailLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                personalPanel.add(emailField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- phoneLabel ----
                phoneLabel.setText("Phone");
                phoneLabel.setDisplayedMnemonic('P');
                phoneLabel.setLabelFor(phoneField);
                personalPanel.add(phoneLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                personalPanel.add(phoneField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- passwordLabel ----
                passwordLabel.setText("Password");
                passwordLabel.setDisplayedMnemonic('P');
                passwordLabel.setLabelFor(passwordField);
                personalPanel.add(passwordLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- passwordField ----
                passwordField.setToolTipText("Your password must be at least 8 characters in length.\nYour password must contain at least 3 out of the 4 following items:\nUppercase letters (A-Z)\nLowercase letters (a-z)\nNumbers (0-9)\nSymbols or special characters");
                personalPanel.add(passwordField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- paymentMethodLabel ----
                paymentMethodLabel.setText("Payment method");
                paymentMethodLabel.setDisplayedMnemonic('P');
                paymentMethodLabel.setLabelFor(paymentMethodField);
                personalPanel.add(paymentMethodLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                personalPanel.add(paymentMethodField, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }
            clientInfoPane.addTab("Personal", personalPanel);

            //======== addressDeliveryPanel ========
            {
                addressDeliveryPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)addressDeliveryPanel.getLayout()).columnWidths = new int[] {0, 270, 0};
                ((GridBagLayout)addressDeliveryPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)addressDeliveryPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)addressDeliveryPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- addressDeliveryLine1Label ----
                addressDeliveryLine1Label.setText("Line 1");
                addressDeliveryLine1Label.setDisplayedMnemonic('L');
                addressDeliveryLine1Label.setLabelFor(addressDeliveryLine1Field);
                addressDeliveryPanel.add(addressDeliveryLine1Label, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                addressDeliveryPanel.add(addressDeliveryLine1Field, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressDeliveryLine2Label ----
                addressDeliveryLine2Label.setText("Line 2");
                addressDeliveryLine2Label.setDisplayedMnemonic('L');
                addressDeliveryLine2Label.setLabelFor(addressDeliveryLine2Field);
                addressDeliveryPanel.add(addressDeliveryLine2Label, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                addressDeliveryPanel.add(addressDeliveryLine2Field, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressDeliveryCityLabel ----
                addressDeliveryCityLabel.setText("City");
                addressDeliveryCityLabel.setDisplayedMnemonic('C');
                addressDeliveryCityLabel.setLabelFor(addressDeliveryCityField);
                addressDeliveryPanel.add(addressDeliveryCityLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                addressDeliveryPanel.add(addressDeliveryCityField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressDeliveryStateLabel ----
                addressDeliveryStateLabel.setText("State");
                addressDeliveryStateLabel.setDisplayedMnemonic('S');
                addressDeliveryStateLabel.setLabelFor(addressDeliveryStateField);
                addressDeliveryPanel.add(addressDeliveryStateLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                addressDeliveryPanel.add(addressDeliveryStateField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressDeliveryPostalCodeLabel ----
                addressDeliveryPostalCodeLabel.setText("Postal code");
                addressDeliveryPostalCodeLabel.setDisplayedMnemonic('P');
                addressDeliveryPostalCodeLabel.setLabelFor(addressDeliveryPostalCodeField);
                addressDeliveryPanel.add(addressDeliveryPostalCodeLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                addressDeliveryPanel.add(addressDeliveryPostalCodeField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressDeliveryCountryLabel ----
                addressDeliveryCountryLabel.setText("Country");
                addressDeliveryCountryLabel.setDisplayedMnemonic('C');
                addressDeliveryCountryLabel.setLabelFor(addressDeliveryCountryField);
                addressDeliveryPanel.add(addressDeliveryCountryLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
                addressDeliveryPanel.add(addressDeliveryCountryField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            clientInfoPane.addTab("Delivery address", addressDeliveryPanel);

            //======== addressInvoicePanel ========
            {
                addressInvoicePanel.setLayout(new GridBagLayout());
                ((GridBagLayout)addressInvoicePanel.getLayout()).columnWidths = new int[] {0, 29, 269, 0};
                ((GridBagLayout)addressInvoicePanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)addressInvoicePanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)addressInvoicePanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- addressInvoiceLine1Label ----
                addressInvoiceLine1Label.setText("Line 1");
                addressInvoiceLine1Label.setDisplayedMnemonic('L');
                addressInvoiceLine1Label.setLabelFor(addressInvoiceLine1Field);
                addressInvoicePanel.add(addressInvoiceLine1Label, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                addressInvoicePanel.add(addressInvoiceLine1Field, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressInvoiceLine2Label ----
                addressInvoiceLine2Label.setText("Line 2");
                addressInvoiceLine2Label.setDisplayedMnemonic('L');
                addressInvoiceLine2Label.setLabelFor(addressInvoiceLine2Field);
                addressInvoicePanel.add(addressInvoiceLine2Label, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                addressInvoicePanel.add(addressInvoiceLine2Field, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressInvoiceCityLabel ----
                addressInvoiceCityLabel.setText("City");
                addressInvoiceCityLabel.setDisplayedMnemonic('C');
                addressInvoiceCityLabel.setLabelFor(addressInvoiceCityField);
                addressInvoicePanel.add(addressInvoiceCityLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                addressInvoicePanel.add(addressInvoiceCityField, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressInvoiceStateLabel ----
                addressInvoiceStateLabel.setText("State");
                addressInvoiceStateLabel.setDisplayedMnemonic('S');
                addressInvoiceStateLabel.setLabelFor(addressInvoiceStateField);
                addressInvoicePanel.add(addressInvoiceStateLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                addressInvoicePanel.add(addressInvoiceStateField, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressInvoicePostalCodeLabel ----
                addressInvoicePostalCodeLabel.setText("Postal code");
                addressInvoicePostalCodeLabel.setDisplayedMnemonic('P');
                addressInvoicePostalCodeLabel.setLabelFor(addressInvoicePostalCodeField);
                addressInvoicePanel.add(addressInvoicePostalCodeLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                addressInvoicePanel.add(addressInvoicePostalCodeField, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressInvoiceCountryLabel ----
                addressInvoiceCountryLabel.setText("Country");
                addressInvoiceCountryLabel.setDisplayedMnemonic('C');
                addressInvoiceCountryLabel.setLabelFor(addressInvoiceCountryField);
                addressInvoicePanel.add(addressInvoiceCountryLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
                addressInvoicePanel.add(addressInvoiceCountryField, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            clientInfoPane.addTab("Invoice address", addressInvoicePanel);
        }
        add(clientInfoPane, new GridBagConstraints(0, 8, 2, 10, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client.firstName} ${client.lastName}"),
            usernameLabel, BeanProperty.create("text"), "displayNameTitle"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.firstName"),
            firstNameField, BeanProperty.create("text"), "firstName"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.lastName"),
            lastNameField, BeanProperty.create("text"), "lastName"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.email"),
            emailField, BeanProperty.create("text"), "email"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.phone"),
            phoneField, BeanProperty.create("text"), "phone"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.password"),
            passwordField, BeanProperty.create("text"), "password"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByDeliveryAddressId.addressLine1"),
            addressDeliveryLine1Field, BeanProperty.create("text"), "addressByDeliveryAddressId.addressLine1"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByDeliveryAddressId.addressLine2"),
            addressDeliveryLine2Field, BeanProperty.create("text"), "addressByDeliveryAddressId.addressLine2"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByDeliveryAddressId.city"),
            addressDeliveryCityField, BeanProperty.create("text"), "addressByDeliveryAddressId.city"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByDeliveryAddressId.state"),
            addressDeliveryStateField, BeanProperty.create("text"), "addressByDeliveryAddressId.state"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByDeliveryAddressId.postalCode"),
            addressDeliveryPostalCodeField, BeanProperty.create("text"), "addressByDeliveryAddressId.postalCode"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByInvoiceAddressId.addressLine1"),
            addressInvoiceLine1Field, BeanProperty.create("text"), "addressByInvoiceAddressId.addressLine1"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByInvoiceAddressId.addressLine2"),
            addressInvoiceLine2Field, BeanProperty.create("text"), "addressByInvoiceAddressId.addressLine2"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByInvoiceAddressId.city"),
            addressInvoiceCityField, BeanProperty.create("text"), "addressByInvoiceAddressId.city"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByInvoiceAddressId.state"),
            addressInvoiceStateField, BeanProperty.create("text"), "addressByInvoiceAddressId.state"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByInvoiceAddressId.postalCode"),
            addressInvoicePostalCodeField, BeanProperty.create("text"), "addressByInvoiceAddressId.postalCode"));
        bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("titles"), titleField));
        bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("countries"), addressDeliveryCountryField));
        bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("countries"), addressInvoiceCountryField));
        bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("paymentMethods"), paymentMethodField));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.titleByTitleId"),
            titleField, BeanProperty.create("selectedItem"), "selectedTitle"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByDeliveryAddressId.countryByCountryId"),
            addressDeliveryCountryField, BeanProperty.create("selectedItem"), "selectedCountryAddressDelivery"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByInvoiceAddressId.countryByCountryId"),
            addressInvoiceCountryField, BeanProperty.create("selectedItem"), "selectedCountryAddressInvoice"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.paymentMethodByPaymentMethodId"),
            paymentMethodField, BeanProperty.create("selectedItem"), "selectedPaymentMethod"));
        bindingGroup.bind();
        enablementBindingGroup = new BindingGroup();
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            titleField, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            firstNameField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            lastNameField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            emailField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            phoneField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            passwordField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressDeliveryLine1Field, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressDeliveryLine2Field, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressDeliveryCityField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressDeliveryStateField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressDeliveryPostalCodeField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressInvoiceLine1Field, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressInvoiceLine2Field, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressInvoiceCityField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressInvoiceStateField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressInvoicePostalCodeField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressDeliveryCountryField, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            addressInvoiceCountryField, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            paymentMethodField, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            firstNameField, BeanProperty.create("editable"),
            firstNameLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            lastNameField, BeanProperty.create("editable"),
            lastNameLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            emailField, BeanProperty.create("editable"),
            emailLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            phoneField, BeanProperty.create("editable"),
            phoneLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            passwordField, BeanProperty.create("editable"),
            passwordLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressDeliveryLine1Field, BeanProperty.create("editable"),
            addressDeliveryLine1Label, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressDeliveryLine2Field, BeanProperty.create("editable"),
            addressDeliveryLine2Label, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressDeliveryCityField, BeanProperty.create("editable"),
            addressDeliveryCityLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressDeliveryStateField, BeanProperty.create("editable"),
            addressDeliveryStateLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressDeliveryPostalCodeField, BeanProperty.create("editable"),
            addressDeliveryPostalCodeLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressInvoiceLine1Field, BeanProperty.create("editable"),
            addressInvoiceLine1Label, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressInvoiceLine2Field, BeanProperty.create("editable"),
            addressInvoiceLine2Label, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressInvoiceCityField, BeanProperty.create("editable"),
            addressInvoiceCityLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressInvoiceStateField, BeanProperty.create("editable"),
            addressInvoiceStateLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressInvoicePostalCodeLabel, BeanProperty.create("editable"),
            addressInvoicePostalCodeLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            titleField, BeanProperty.create("enabled"),
            titleLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            paymentMethodField, BeanProperty.create("enabled"),
            paymentMethodLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressDeliveryCountryField, BeanProperty.create("enabled"),
            addressDeliveryCountryLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            addressInvoiceCountryField, BeanProperty.create("enabled"),
            addressInvoiceCountryLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel clientInfoLabel;
    private JLabel usernameLabel;
    private JTabbedPane clientInfoPane;
    private JPanel personalPanel;
    private JLabel titleLabel;
    private JComboBox<TitleEntity> titleField;
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
    private JComboBox<PaymentMethodEntity> paymentMethodField;
    private JPanel addressDeliveryPanel;
    private JLabel addressDeliveryLine1Label;
    private JTextField addressDeliveryLine1Field;
    private JLabel addressDeliveryLine2Label;
    private JTextField addressDeliveryLine2Field;
    private JLabel addressDeliveryCityLabel;
    private JTextField addressDeliveryCityField;
    private JLabel addressDeliveryStateLabel;
    private JTextField addressDeliveryStateField;
    private JLabel addressDeliveryPostalCodeLabel;
    private JTextField addressDeliveryPostalCodeField;
    private JLabel addressDeliveryCountryLabel;
    private JComboBox<CountryEntity> addressDeliveryCountryField;
    private JPanel addressInvoicePanel;
    private JLabel addressInvoiceLine1Label;
    private JTextField addressInvoiceLine1Field;
    private JLabel addressInvoiceLine2Label;
    private JTextField addressInvoiceLine2Field;
    private JLabel addressInvoiceCityLabel;
    private JTextField addressInvoiceCityField;
    private JLabel addressInvoiceStateLabel;
    private JTextField addressInvoiceStateField;
    private JLabel addressInvoicePostalCodeLabel;
    private JTextField addressInvoicePostalCodeField;
    private JLabel addressInvoiceCountryLabel;
    private JComboBox<CountryEntity> addressInvoiceCountryField;
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

            // create client view
			ClientView clientView = new ClientView();
			clientView.setBorder(new EmptyBorder(10, 10, 10, 10));

            // init demo data
            ClientEntity client = new ClientEntity(new TitleEntity(2, "Madame"), "firstName", "lastName","email", "phone", "password"
                    , new AddressEntity("inv. addressLine1", "inv. addressLine2"
                    , "inv. city", "inv. state", "inv. postalCode", new CountryEntity(2, "Luxembourg"))
                    , new AddressEntity("del. addressLine1", "del. addressLine2"
                    , "del. city", "del. state", "del. postalCode", new CountryEntity(3, "Hungary"))
                    , new PaymentMethodEntity(2, "Visa"));
            clientView.setClient(client);

            // create frame
			JFrame frame = new JFrame("Client");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(clientView);
			frame.pack();
			frame.setVisible(true);
		});
	}
}
