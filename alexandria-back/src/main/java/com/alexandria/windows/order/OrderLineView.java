package com.alexandria.windows.order;

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

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.List;

import static com.alexandria.persistence.PersistenceUtils.beginTransaction;
import static com.alexandria.persistence.PersistenceUtils.commitTransaction;

/**
 * This view shows/edits a single orderLine.
 * <p>
 * It uses Beans Binding to bind the properties of {@link #orderLine} to input fields.
 * Beans Binding is also used to enable/disable the input fields.
 * <p>
 * Use the {@link #main} method to test this view.
 */
public class OrderLineView extends JPanel {

    private static final Logger logger = LogManager.getLogger(OrderLineView.class);

    private OrderLineEntity orderLine;
    private List<ProductEntity> searchProductsList;

    public OrderLineView() {

        initComponents();
	}

    // Called (get & set) by parent window ClientsView in the showClientDialog method
    public OrderLineEntity getOrderLine() {
        return orderLine;
    }

	public void setOrderLine(OrderLineEntity orderLine) {
		OrderLineEntity oldOrderLine = this.orderLine;
		this.orderLine = orderLine;
		firePropertyChange("orderLine", oldOrderLine, orderLine);
	}

    /// COMBOBOXES ///BEGIN


    /// COMBOBOXES ///END

    @Override
    public boolean requestFocusInWindow() {
        return productNameField.requestFocusInWindow();
    }

    /// ACTION LISTENER HANDLER Methods ///BEGIN

    private void searchProducts(ActionEvent e) {

        logger.info("DB_SEARCH_PRODUCTS BEGIN");

        EntityManager session = beginTransaction();

        TypedQuery<ProductEntity> query = session.createNamedQuery("ProductEntity.findFromName", ProductEntity.class);
        query.setParameter("name", e.getActionCommand());
        searchProductsList = query.getResultList();

        commitTransaction();

        // Log searchProductsList
        if(searchProductsList.size() > 1)
            logger.info("searchProductsList > 1 -> " + "nbFound: " + searchProductsList.size());
        for(ProductEntity product : searchProductsList)
            logger.info("Found product: " + product.getIdProduct() + " " + product.getName());

        // FIXME BEGIN : Workaround as searchProductsList cannot be mapped in the JTable in JFormDesigner (bug?)
        //  - the select from JTable (selectProduct) is bypased and we set the model with the first result of the searchProductsList
        if(searchProductsList != null && searchProductsList.size() > 0) {

            // Force selected value to the first item of the searchProductsList
            ProductEntity forcedSelectedProduct = searchProductsList.get(0);

            // Check that the forcedSelectedProduct is not already present in a order line of the order
            if( !isProductAlreadyPresentInOrder(orderLine.getOrderHeaderByOrderHeaderId(), forcedSelectedProduct) ) {

                // Set forcedSelectedProduct as the order line product
                orderLine.setProductByProductId( forcedSelectedProduct );
                orderLine.setProductId(forcedSelectedProduct.getIdProduct());

                // Reset order line quantity
                orderLine.setQuantity(1);

            } else {
                // Warning to user
                productNameField.setText(forcedSelectedProduct.getName() + " already in order");
            }

        }
        /// FIXME END

        logger.info("DB_SEARCH_PRODUCTS END");
    }

    private boolean isProductAlreadyPresentInOrder(OrderHeaderEntity order, ProductEntity product)
    {
        boolean isPresent = false;

        List<OrderLineEntity> orderlines = order.getOrderLinesByIdOrderHeader();

        for(OrderLineEntity orderLine : orderlines)
        {
            if( isPresent = (orderLine.getProductId() == product.getIdProduct()) ? true : false ) break;
        }
        return isPresent;
    }

    // From JTable, not used as the databinding with searchProductsList doesn't work (JFormDesigner bug ?)
    private void selectProduct() {
        int selectedRow = searchProductsTable.getSelectedRow();
        if (selectedRow < 0)
            return;

        // Find in database
        ProductEntity product = dbFindProduct(searchProductsList.get(selectedRow).getIdProduct());

        // Replace in model
        orderLine.setProductByProductId(product);

        // TODO : TBC : do we need to update orderLine with orderLine.setProductId( product.getIdProduct() );
        orderLine.setProductId( product.getIdProduct() );

        // Reset order line quantity
        orderLine.setQuantity(0);
    }

    /// ACTION LISTENER HANDLER Methods ///END

    /// DATABASE MANAGEMENT Methods ///BEGIN

    private ProductEntity dbFindProduct(Integer idProduct) {

        logger.info("DB_FIND_PRODUCT BEGIN " + "idProduct: " + idProduct);

        EntityManager session = beginTransaction();

        ProductEntity product = session.find(ProductEntity.class, idProduct);

        commitTransaction();

        logger.info("DB_FIND_PRODUCT END " + "idProduct: " + idProduct);

        return product;
    }

    /// DATABASE MANAGEMENT Methods ///END

    // Following code is generated //

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        orderLineInfoLabel = new JLabel();
        productNameMainLabel = new JLabel();
        orderLineInfoPane = new JTabbedPane();
        productPanel = new JPanel();
        productNameLabel = new JLabel();
        productNameField = new JTextField();
        productStockLabel = new JLabel();
        productStockField = new JTextField();
        productPriceExVatLabel = new JLabel();
        productPriceExVatField = new JTextField();
        orderLineQuantityLabel = new JLabel();
        orderLineQuantityField = new JSpinner();
        productPictureLabel = new JLabel();
        productPictureIconLabel = new JLabel();
        productCategoriesLabel = new JLabel();
        categoriesScrollPane = new JScrollPane();
        categoriesTable = new JTable();
        searchProductsLabel = new JLabel();
        searchProductsField = new JTextField();
        searchProductsResLabel = new JLabel();
        searchProductsScrollPane = new JScrollPane();
        searchProductsTable = new JTable();
        selectProductButton = new JButton();
        bookPanel = new JPanel();
        bookTitleLabel = new JLabel();
        bookTitleField = new JTextField();
        bookIsbnLabel = new JLabel();
        bookIsbnField = new JTextField();
        bookNbPagesLabel = new JLabel();
        bookNbPagesField = new JTextField();
        bookCollectionLabel = new JLabel();
        bookCollectionField = new JTextField();
        bookAuthorNamesLabel = new JLabel();
        bookAuthorNamesField = new JTextField();
        bookAuthorBioLabel = new JLabel();
        bookAuthorBioScrollPane = new JScrollPane();
        bookAuthorBioField = new JTextArea();
        bookPublisherLabel = new JLabel();
        bookPublisherField = new JTextField();
        bookGenresLabel = new JLabel();
        genresScrollPane = new JScrollPane();
        genresTable = new JTable();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {112, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- orderLineInfoLabel ----
        orderLineInfoLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        orderLineInfoLabel.setText("OrderLine Info:");
        add(orderLineInfoLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- productNameMainLabel ----
        productNameMainLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        productNameMainLabel.setForeground(new Color(255, 102, 51));
        productNameMainLabel.setText("Total Ex Vat");
        add(productNameMainLabel, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== orderLineInfoPane ========
        {
            orderLineInfoPane.setMinimumSize(new Dimension(400, 600));

            //======== productPanel ========
            {
                productPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)productPanel.getLayout()).columnWidths = new int[] {0, 210, 150, 0};
                ((GridBagLayout)productPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 84, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)productPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)productPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- productNameLabel ----
                productNameLabel.setText("Name");
                productNameLabel.setDisplayedMnemonic('L');
                productNameLabel.setLabelFor(productNameField);
                productPanel.add(productNameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- productNameField ----
                productNameField.setEditable(false);
                productPanel.add(productNameField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- productStockLabel ----
                productStockLabel.setText("Stock");
                productStockLabel.setDisplayedMnemonic('L');
                productStockLabel.setLabelFor(productStockField);
                productPanel.add(productStockLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- productStockField ----
                productStockField.setEditable(false);
                productPanel.add(productStockField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- productPriceExVatLabel ----
                productPriceExVatLabel.setText("Price ex vat $");
                productPriceExVatLabel.setDisplayedMnemonic('C');
                productPriceExVatLabel.setLabelFor(productPriceExVatField);
                productPanel.add(productPriceExVatLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- productPriceExVatField ----
                productPriceExVatField.setEditable(false);
                productPanel.add(productPriceExVatField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- orderLineQuantityLabel ----
                orderLineQuantityLabel.setText("Quantity");
                orderLineQuantityLabel.setDisplayedMnemonic('L');
                orderLineQuantityLabel.setLabelFor(orderLineQuantityField);
                productPanel.add(orderLineQuantityLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                productPanel.add(orderLineQuantityField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- productPictureLabel ----
                productPictureLabel.setText("Picture");
                productPictureLabel.setDisplayedMnemonic('S');
                productPanel.add(productPictureLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- productPictureIconLabel ----
                productPictureIconLabel.setText("Kiss my ass");
                productPictureIconLabel.setDisplayedMnemonic('S');
                productPanel.add(productPictureIconLabel, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- productCategoriesLabel ----
                productCategoriesLabel.setText("Product categories");
                productCategoriesLabel.setDisplayedMnemonic('L');
                productCategoriesLabel.setLabelFor(categoriesTable);
                productPanel.add(productCategoriesLabel, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== categoriesScrollPane ========
                {

                    //---- categoriesTable ----
                    categoriesTable.setAutoCreateRowSorter(true);
                    categoriesScrollPane.setViewportView(categoriesTable);
                }
                productPanel.add(categoriesScrollPane, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- searchProductsLabel ----
                searchProductsLabel.setDisplayedMnemonic('L');
                searchProductsLabel.setLabelFor(searchProductsField);
                searchProductsLabel.setIcon(new ImageIcon(getClass().getResource("/icons/Search_24px.png")));
                searchProductsLabel.setText("Products");
                productPanel.add(searchProductsLabel, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- searchProductsField ----
                searchProductsField.addActionListener(e -> searchProducts(e));
                productPanel.add(searchProductsField, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- searchProductsResLabel ----
                searchProductsResLabel.setText("Search result");
                searchProductsResLabel.setDisplayedMnemonic('L');
                searchProductsResLabel.setLabelFor(searchProductsTable);
                productPanel.add(searchProductsResLabel, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== searchProductsScrollPane ========
                {
                    searchProductsScrollPane.setViewportView(searchProductsTable);
                }
                productPanel.add(searchProductsScrollPane, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- selectProductButton ----
                selectProductButton.setText("Select...");
                selectProductButton.setMnemonic('S');
                selectProductButton.addActionListener(e -> selectProduct());
                productPanel.add(selectProductButton, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }
            orderLineInfoPane.addTab("Product", productPanel);

            //======== bookPanel ========
            {
                bookPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)bookPanel.getLayout()).columnWidths = new int[] {0, 270, 0};
                ((GridBagLayout)bookPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 76, 0, 103, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)bookPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)bookPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- bookTitleLabel ----
                bookTitleLabel.setText("Title");
                bookTitleLabel.setDisplayedMnemonic('T');
                bookPanel.add(bookTitleLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- bookTitleField ----
                bookTitleField.setEditable(false);
                bookPanel.add(bookTitleField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- bookIsbnLabel ----
                bookIsbnLabel.setText("Isbn");
                bookIsbnLabel.setDisplayedMnemonic('F');
                bookIsbnLabel.setLabelFor(bookIsbnField);
                bookPanel.add(bookIsbnLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- bookIsbnField ----
                bookIsbnField.setEditable(false);
                bookPanel.add(bookIsbnField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- bookNbPagesLabel ----
                bookNbPagesLabel.setText("Nb pages");
                bookNbPagesLabel.setDisplayedMnemonic('L');
                bookNbPagesLabel.setLabelFor(bookNbPagesField);
                bookPanel.add(bookNbPagesLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- bookNbPagesField ----
                bookNbPagesField.setEditable(false);
                bookPanel.add(bookNbPagesField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- bookCollectionLabel ----
                bookCollectionLabel.setText("Collection");
                bookCollectionLabel.setDisplayedMnemonic('E');
                bookCollectionLabel.setLabelFor(bookCollectionField);
                bookPanel.add(bookCollectionLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- bookCollectionField ----
                bookCollectionField.setEditable(false);
                bookPanel.add(bookCollectionField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- bookAuthorNamesLabel ----
                bookAuthorNamesLabel.setText("Author");
                bookAuthorNamesLabel.setDisplayedMnemonic('P');
                bookAuthorNamesLabel.setLabelFor(bookAuthorNamesField);
                bookPanel.add(bookAuthorNamesLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- bookAuthorNamesField ----
                bookAuthorNamesField.setEditable(false);
                bookPanel.add(bookAuthorNamesField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- bookAuthorBioLabel ----
                bookAuthorBioLabel.setText("Author bio");
                bookAuthorBioLabel.setDisplayedMnemonic('P');
                bookAuthorBioLabel.setLabelFor(bookAuthorBioField);
                bookPanel.add(bookAuthorBioLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== bookAuthorBioScrollPane ========
                {

                    //---- bookAuthorBioField ----
                    bookAuthorBioField.setEditable(false);
                    bookAuthorBioScrollPane.setViewportView(bookAuthorBioField);
                }
                bookPanel.add(bookAuthorBioScrollPane, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- bookPublisherLabel ----
                bookPublisherLabel.setText("Publisher");
                bookPublisherLabel.setDisplayedMnemonic('P');
                bookPublisherLabel.setLabelFor(bookPublisherField);
                bookPanel.add(bookPublisherLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- bookPublisherField ----
                bookPublisherField.setToolTipText("Your password must be at least 8 characters in length.\nYour password must contain at least 3 out of the 4 following items:\nUppercase letters (A-Z)\nLowercase letters (a-z)\nNumbers (0-9)\nSymbols or special characters");
                bookPublisherField.setEditable(false);
                bookPanel.add(bookPublisherField, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- bookGenresLabel ----
                bookGenresLabel.setText("Genres");
                bookGenresLabel.setDisplayedMnemonic('G');
                bookGenresLabel.setLabelFor(genresTable);
                bookPanel.add(bookGenresLabel, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== genresScrollPane ========
                {

                    //---- genresTable ----
                    genresTable.setAutoCreateRowSorter(true);
                    genresScrollPane.setViewportView(genresTable);
                }
                bookPanel.add(genresScrollPane, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            orderLineInfoPane.addTab("Book", bookPanel);
        }
        add(orderLineInfoPane, new GridBagConstraints(0, 8, 2, 10, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("Total ExVat : ${orderLine.productByProductId.priceExVat * orderLine.quantity} $"),
            productNameMainLabel, BeanProperty.create("text"), "displayNameTitle"));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("orderLine.productByProductId.name"),
            productNameField, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${ orderLine.productByProductId.stock - orderLine.quantity }"),
            productStockField, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("orderLine.productByProductId.priceExVat"),
            productPriceExVatField, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("orderLine.quantity"),
            orderLineQuantityField, BeanProperty.create("value")));
        {
            var binding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE,
                this, (BeanProperty) BeanProperty.create("orderLine.productByProductId.productCategoriesByIdProduct"), categoriesTable);
            binding.setEditable(false);
            binding.addColumnBinding(BeanProperty.create("categoryByCategoryId.idCategory"))
                .setColumnName("Id Category")
                .setColumnClass(Integer.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("categoryByCategoryId.description"))
                .setColumnName("Description")
                .setColumnClass(String.class)
                .setEditable(false);
            bindingGroup.addBinding(binding);
        }
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("orderLine.productByProductId.image"),
            productPictureIconLabel, BeanProperty.create("icon")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            searchProductsTable, ELProperty.create("${selectedElement != null}"),
            selectProductButton, BeanProperty.create("enabled")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("orderLine.productByProductId.bookByIdProduct.title"),
            bookTitleField, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("orderLine.productByProductId.bookByIdProduct.isbn"),
            bookIsbnField, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("orderLine.productByProductId.bookByIdProduct.nbPages"),
            bookNbPagesField, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("orderLine.productByProductId.bookByIdProduct.collectionByCollectionId.description"),
            bookCollectionField, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, ELProperty.create("${orderLine.productByProductId.bookByIdProduct.authorByAuthorId.firstName} ${orderLine.productByProductId.bookByIdProduct.authorByAuthorId.lastName}"),
            bookAuthorNamesField, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("orderLine.productByProductId.bookByIdProduct.publisherByPublisherId.name"),
            bookPublisherField, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            this, BeanProperty.create("orderLine.productByProductId.bookByIdProduct.authorByAuthorId.bio"),
            bookAuthorBioField, BeanProperty.create("text")));
        {
            var binding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE,
                this, (BeanProperty) BeanProperty.create("orderLine.productByProductId.bookByIdProduct.bookGenresByIdBook"), genresTable);
            binding.setEditable(false);
            binding.addColumnBinding(BeanProperty.create("genreByGenreId.idGenre"))
                .setColumnName("Id Genre")
                .setColumnClass(Integer.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("genreByGenreId.description"))
                .setColumnName("Description")
                .setColumnClass(String.class)
                .setEditable(false);
            bindingGroup.addBinding(binding);
        }
        bindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel orderLineInfoLabel;
    private JLabel productNameMainLabel;
    private JTabbedPane orderLineInfoPane;
    private JPanel productPanel;
    private JLabel productNameLabel;
    private JTextField productNameField;
    private JLabel productStockLabel;
    private JTextField productStockField;
    private JLabel productPriceExVatLabel;
    private JTextField productPriceExVatField;
    private JLabel orderLineQuantityLabel;
    private JSpinner orderLineQuantityField;
    private JLabel productPictureLabel;
    private JLabel productPictureIconLabel;
    private JLabel productCategoriesLabel;
    private JScrollPane categoriesScrollPane;
    private JTable categoriesTable;
    private JLabel searchProductsLabel;
    private JTextField searchProductsField;
    private JLabel searchProductsResLabel;
    private JScrollPane searchProductsScrollPane;
    private JTable searchProductsTable;
    private JButton selectProductButton;
    private JPanel bookPanel;
    private JLabel bookTitleLabel;
    private JTextField bookTitleField;
    private JLabel bookIsbnLabel;
    private JTextField bookIsbnField;
    private JLabel bookNbPagesLabel;
    private JTextField bookNbPagesField;
    private JLabel bookCollectionLabel;
    private JTextField bookCollectionField;
    private JLabel bookAuthorNamesLabel;
    private JTextField bookAuthorNamesField;
    private JLabel bookAuthorBioLabel;
    private JScrollPane bookAuthorBioScrollPane;
    private JTextArea bookAuthorBioField;
    private JLabel bookPublisherLabel;
    private JTextField bookPublisherField;
    private JLabel bookGenresLabel;
    private JScrollPane genresScrollPane;
    private JTable genresTable;
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

            // create orderLine view
			OrderLineView orderLineView = new OrderLineView();
			orderLineView.setBorder(new EmptyBorder(10, 10, 10, 10));

            // init demo data
            OrderHeaderEntity order = new OrderHeaderEntity(new java.sql.Date(System.currentTimeMillis())
                    , new java.sql.Date(System.currentTimeMillis())
                    , new java.sql.Date(System.currentTimeMillis())
                    , "comment"
                    , new ShippingMethodEntity(1, "DHL", new BigDecimal(18.4065)));

            ClientEntity client = new ClientEntity(new TitleEntity(2, "Madame"), "firstName", "lastName","email", "phone", "password"
                    , new AddressEntity("inv. addressLine1", "inv. addressLine2"
                    , "inv. city", "inv. state", "inv. postalCode", new CountryEntity(2, "Luxembourg"))
                    , new AddressEntity("del. addressLine1", "del. addressLine2"
                    , "del. city", "del. state", "del. postalCode", new CountryEntity(3, "Hungary"))
                    , new PaymentMethodEntity(2, "Visa"));

            order.setClientByClientId(client);

            OrderLineEntity orderLine = new OrderLineEntity(0, order);
            orderLineView.setOrderLine(orderLine);

            // create frame
			JFrame frame = new JFrame("Order Line");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(orderLineView);
			frame.pack();
			frame.setVisible(true);
		});
	}
}
