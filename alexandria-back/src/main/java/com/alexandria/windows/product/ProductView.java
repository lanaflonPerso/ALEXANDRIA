package com.alexandria.windows.product;

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
 * This view shows/edits a single product.
 * <p>
 * It uses Beans Binding to bind the properties of {@link #product} to input fields.
 * Beans Binding is also used to enable/disable the input fields.
 * <p>
 * Use the {@link #main} method to test this view.
 */
public class ProductView extends JPanel {

    private static final Logger logger = LogManager.getLogger(ProductView.class);

    private ProductEntity product;

    /// COMBOBOXES
    private List<BookEntity> books = new DAOFactory().getBookDao().doBooksList();
    private List<AuthorEntity> authors = new DAOFactory().getAuthorDao().doAuthorsList();
    private List<GenreEntity> genres = new DAOFactory().getGenreDao().doGenresList();
    private List<CollectionEntity> collections = new DAOFactory().getCollectionDao().doCollectionsList();
    private List<PublisherEntity> publishers = new DAOFactory().getPublisherDao().doPublishersList();
    private List<CategoryEntity> categories = new DAOFactory().getCategoryDao().doCategoriesList();

    public ProductView() {

        initComponents();
	}

    // Called (get & set) by parent window ProductsView in the showProductDialog method
    public ProductEntity getProduct() {
        return product;
    }

	public void setProduct(ProductEntity product) {
		ProductEntity oldProduct = this.product;
		this.product = product;
		firePropertyChange("product", oldProduct, product);
	}

    /// COMBOBOXES ///BEGIN

    // Combobox books //
    /**
     * Returns a list of available books.
     * Used to fill list of {@link #titleField} combobox.
     * See binding: this.countries --> addressCountryField.elements
     */
    public List<BookEntity> getBooks() {
        return books;
    }


    // Combobox authors //
    /**
     * Returns a list of available countries.
     * Used to fill list of {@link #titleField} combobox.
     * See binding: this.countries --> addressCountryField.elements
     */
    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    // CheckBox categories //
    /**
     * Returns a list of available countries.
     * Used to fill list of {@link #addressInvoiceCountryField #addressDeliveryCountryField} combobox.
     * See binding: this.countries --> addressCountryField.elements
     */
    public List<CategoryEntity> getCategories() {
        return categories;
    }

    // Combobox Genres //
    /**
     * Returns a list of available paymentMethods.
     * Used to fill list of {@link #genres} combobox.
     * See binding: this.paymentMethods --> paymentMethodField.elements
     */
    public List<GenreEntity> getGenres() {
        return genres;
    }

    // Combobox authors //
    /**
     * Returns a list of available countries.
     * Used to fill list of {@link #titleField} combobox.
     * See binding: this.countries --> addressCountryField.elements
     */
    public List<CollectionEntity> getCollections() {
        return collections;
    }

    // Combobox authors //
    /**
     * Returns a list of available countries.
     * Used to fill list of {@link #titleField} combobox.
     * See binding: this.countries --> addressCountryField.elements
     */
    public List<PublisherEntity> getPublishers() {
        return publishers;
    }
    /// COMBOBOXES ///END

    @Override
    public boolean requestFocusInWindow() {
        return nameField.requestFocusInWindow();
    }

    // Following code is generated //

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        productInfoLabel = new JLabel();
        usernameLabel = new JLabel();
        productInfoPane = new JTabbedPane();
        productPanel = new JPanel();
        nameLabel = new JLabel();
        nameField = new JComboBox<>();
        Visuel = new JLabel();
        stockLabel = new JLabel();
        stockField = new JTextField();
        bioField = new JTextField();
        bioLabel = new JLabel();
        BookPanel = new JPanel();
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

        //---- productInfoLabel ----
        productInfoLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        productInfoLabel.setText("Product Info:");
        add(productInfoLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- usernameLabel ----
        usernameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        add(usernameLabel, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== productInfoPane ========
        {
            productInfoPane.setMinimumSize(new Dimension(400, 600));

            //======== productPanel ========
            {
                productPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)productPanel.getLayout()).columnWidths = new int[] {0, 210, 150, 0};
                ((GridBagLayout)productPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)productPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)productPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- nameLabel ----
                nameLabel.setText("name");
                nameLabel.setDisplayedMnemonic('T');
                nameLabel.setLabelFor(nameField);
                productPanel.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                productPanel.add(nameField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- Visuel ----
                Visuel.setText("text");
                productPanel.add(Visuel, new GridBagConstraints(2, 0, 1, 2, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- stockLabel ----
                stockLabel.setText("stock");
                stockLabel.setDisplayedMnemonic('F');
                stockLabel.setLabelFor(stockField);
                productPanel.add(stockLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                productPanel.add(stockField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                productPanel.add(bioField, new GridBagConstraints(1, 3, 2, 11, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- bioLabel ----
                bioLabel.setText("bio");
                bioLabel.setDisplayedMnemonic('L');
                bioLabel.setLabelFor(bioField);
                productPanel.add(bioLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }
            productInfoPane.addTab("Personal", productPanel);

            //======== BookPanel ========
            {
                BookPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)BookPanel.getLayout()).columnWidths = new int[] {0, 270, 0};
                ((GridBagLayout)BookPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)BookPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)BookPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- addressDeliveryLine1Label ----
                addressDeliveryLine1Label.setText("Line 1");
                addressDeliveryLine1Label.setDisplayedMnemonic('L');
                addressDeliveryLine1Label.setLabelFor(addressDeliveryLine1Field);
                BookPanel.add(addressDeliveryLine1Label, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                BookPanel.add(addressDeliveryLine1Field, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressDeliveryLine2Label ----
                addressDeliveryLine2Label.setText("Line 2");
                addressDeliveryLine2Label.setDisplayedMnemonic('L');
                addressDeliveryLine2Label.setLabelFor(addressDeliveryLine2Field);
                BookPanel.add(addressDeliveryLine2Label, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                BookPanel.add(addressDeliveryLine2Field, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressDeliveryCityLabel ----
                addressDeliveryCityLabel.setText("City");
                addressDeliveryCityLabel.setDisplayedMnemonic('C');
                addressDeliveryCityLabel.setLabelFor(addressDeliveryCityField);
                BookPanel.add(addressDeliveryCityLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                BookPanel.add(addressDeliveryCityField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressDeliveryStateLabel ----
                addressDeliveryStateLabel.setText("State");
                addressDeliveryStateLabel.setDisplayedMnemonic('S');
                addressDeliveryStateLabel.setLabelFor(addressDeliveryStateField);
                BookPanel.add(addressDeliveryStateLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                BookPanel.add(addressDeliveryStateField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressDeliveryPostalCodeLabel ----
                addressDeliveryPostalCodeLabel.setText("Postal code");
                addressDeliveryPostalCodeLabel.setDisplayedMnemonic('P');
                addressDeliveryPostalCodeLabel.setLabelFor(addressDeliveryPostalCodeField);
                BookPanel.add(addressDeliveryPostalCodeLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                BookPanel.add(addressDeliveryPostalCodeField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- addressDeliveryCountryLabel ----
                addressDeliveryCountryLabel.setText("Country");
                addressDeliveryCountryLabel.setDisplayedMnemonic('C');
                addressDeliveryCountryLabel.setLabelFor(addressDeliveryCountryField);
                BookPanel.add(addressDeliveryCountryLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
                BookPanel.add(addressDeliveryCountryField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            productInfoPane.addTab("Delivery address", BookPanel);

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
            productInfoPane.addTab("Invoice address", addressInvoicePanel);
        }
        add(productInfoPane, new GridBagConstraints(0, 8, 2, 10, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client.firstName} ${client.lastName}"),
            usernameLabel, BeanProperty.create("text"), "displayNameTitle"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.firstName"),
            stockField, BeanProperty.create("text"), "firstName"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.lastName"),
            bioField, BeanProperty.create("text"), "lastName"));
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
            this, (BeanProperty) BeanProperty.create("product.name"), nameField));
        bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("countries"), addressDeliveryCountryField));
        bindingGroup.addBinding(SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
            this, (BeanProperty) BeanProperty.create("countries"), addressInvoiceCountryField));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("product.idProduct"),
            nameField, BeanProperty.create("selectedItem"), "selectedTitle"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByDeliveryAddressId.countryByCountryId"),
            addressDeliveryCountryField, BeanProperty.create("selectedItem"), "selectedCountryAddressDelivery"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("client.addressByInvoiceAddressId.countryByCountryId"),
            addressInvoiceCountryField, BeanProperty.create("selectedItem"), "selectedCountryAddressInvoice"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("product.picture"),
            this, BeanProperty.create("product.picture")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("product.picture"),
            this, BeanProperty.create("product.idProduct")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("product.name.bytes"),
            this, BeanProperty.create("product.idProduct")));
        bindingGroup.bind();
        enablementBindingGroup = new BindingGroup();
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            nameField, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            stockField, BeanProperty.create("editable")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${client != null}"),
            bioField, BeanProperty.create("editable")));
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
            stockField, BeanProperty.create("editable"),
            stockLabel, BeanProperty.create("enabled")));
        enablementBindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            bioField, BeanProperty.create("editable"),
            bioLabel, BeanProperty.create("enabled")));
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
            nameField, BeanProperty.create("enabled"),
            nameLabel, BeanProperty.create("enabled")));
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
    private JLabel productInfoLabel;
    private JLabel usernameLabel;
    private JTabbedPane productInfoPane;
    private JPanel productPanel;
    private JLabel nameLabel;
    private JComboBox<TitleEntity> nameField;
    private JLabel Visuel;
    private JLabel stockLabel;
    private JTextField stockField;
    private JTextField bioField;
    private JLabel bioLabel;
    private JPanel BookPanel;
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

            // create product view
			ProductView productView = new ProductView();
			productView.setBorder(new EmptyBorder(10, 10, 10, 10));

            // init demo data
/*            ProductEntity product = new ProductEntity();
                    new ProductEntity("name", "stock", "priceExVat", "picture",
                        new BookEntity("isbn", "title", 44, "authorByAuthorId", )
                        new CategoryEntity("description", new CategoryEntity())
                    );

            productView.setProduct(product);*/

            // create frame
			JFrame frame = new JFrame("product");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(productView);
			frame.pack();
			frame.setVisible(true);
		});
	}
}
