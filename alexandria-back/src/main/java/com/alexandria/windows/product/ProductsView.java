package com.alexandria.windows.product;

import com.alexandria.dao.DAOFactory;
import com.alexandria.dao.ProductDao;
import com.alexandria.entities.ProductEntity;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This view shows a list of products in a JTable and provides
 * New, Edit and Delete buttons to edit the products.
 * <p>
 * It uses Beans Binding to bind the list of {@link #products} to the JTable.
 * Beans Binding is also used to enable/disable the Edit and Delete buttons based on selection.
 * <p>
 * The New and Edit buttons use {@link ProductsView} to show/edit the selected product in a sub-dialog.
 * <p>
 * Use the {@link #main} method to test this view.
 */
public class ProductsView extends JPanel {

	private static final Logger logger = LogManager.getLogger(ProductsView.class);

	private List<ProductEntity> products = ObservableCollections.observableList(new ArrayList<ProductEntity>());

	private ProductDao productDao = new DAOFactory().getProductDao();

	public ProductsView() {

		doProductsList();

		initComponents();
	}

	// Init
	private void doProductsList() {

		List<ProductEntity> productsList = productDao.findAll();

		setProducts(ObservableCollections.observableList(productsList));
	}

	// Call by parent window
	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		List<ProductEntity> oldProducts = this.products;
		this.products = products;
		firePropertyChange("products", oldProducts, products);
	}

	/// ACTION LISTENER HANDLER Methods ///BEGIN

	private void newProduct() {

		ProductEntity productDummy = new ProductEntity();

		ProductEntity product = showProductDialog("New Product", productDummy);
		if (product == null)
			return;

		// add new product to products list
		products.add(product);

		// select new product in table and scroll row to visible area
		int row = products.size() - 1;
		productsTable.setRowSelectionInterval(row, row);
		productsTable.scrollRectToVisible(productsTable.getCellRect(row, 0, true));

		// Create in database
		productDao.create(product);
	}

	private void editProduct() {
		int selectedRow = getSelectedRows(productsTable)[0]; // instead of getSelectedRow()
		if (selectedRow < 0)
			return;

		// Find in database
		ProductEntity product = productDao.find(products.get(selectedRow).getIdProduct());

		// Call dialog box
		ProductEntity newProduct = showProductDialog("Edit Product", new ProductEntity(product));
		if (newProduct == null)
			return;

		// Set to model
		products.set(selectedRow, newProduct);

		// Update in database
		productDao.update(newProduct);
	}

	private void deleteProduct() {
		int[] selectedRows = getSelectedRows(productsTable);
		if (selectedRows.length == 0)
			return;

		// remove items from database
		for(int selectedRow : selectedRows)
//			productDao.remove(products.get(selectedRow).getIdProduct());
			productDao.remove(products.get(selectedRow));


		// remove items from memory
		for (int i = selectedRows.length - 1; i >= 0; i--)
			products.remove(selectedRows[i]);

		// select row
		if (productsTable.getRowCount() > 0) {
			int newSel = Math.min(selectedRows[0], productsTable.getRowCount() - 1);
			productsTable.setRowSelectionInterval(newSel, newSel);
			productsTable.scrollRectToVisible(productsTable.getCellRect(newSel, 0, true));
		}
	}

	private int[] getSelectedRows(JTable table) {
		/* les index retournés par les méthodes pour récupérer la sélection (getSelectedRows() par exemple) retournent l'index visuel.
		Dans le cas d'une JTable non triable, cet index correspond également à l'index du modèle, mais ce n'est plus le cas avec un tableau trié.
		On peut néanmoins très facilement résoudre ce problème en utilisant la méthode convertRowIndexToModel de la classe RowSorter. */
		int[] selection = table.getSelectedRows();
		int[] modelIndexes = new int[selection.length];

		for (int i = 0; i < selection.length; i++) {
			modelIndexes[i] = table.getRowSorter().convertRowIndexToModel(selection[i]);
		}

		Arrays.sort(modelIndexes);

		return modelIndexes;
	}

	/// ACTION LISTENER HANDLER Methods ///END

	/// DIALOG BOXES ///BEGIN
	/**
	 * Show/edit a single product in a dialog.
	 */
	private ProductEntity showProductDialog(String title, ProductEntity product) {
		ProductView productView = new ProductView();
		productView.setProduct(product);

		JOptionPane optionPane = new JOptionPane(productView, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		JDialog dialog = optionPane.createDialog(this, title);
		dialog.setResizable(true);
		dialog.setVisible(true);

		if (!new Integer(JOptionPane.OK_OPTION).equals(optionPane.getValue()))
			return null;

		return productView.getProduct();
	}

	/// DIALOG BOXES ///END

	// Following code is generated //

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        productsScrollPane = new JScrollPane();
        productsTable = new JTable();
        buttonPanel = new JPanel();
        windowLabel = new JLabel();
        newProductButton = new JButton();
        editProductButton = new JButton();
        deleteProductButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 0.0, 1.0E-4};

        //======== productsScrollPane ========
        {

            //---- productsTable ----
            productsTable.setPreferredScrollableViewportSize(new Dimension(1024, 768));
            productsTable.setRowHeight(250);
            productsScrollPane.setViewportView(productsTable);
        }
        add(productsScrollPane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
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
            windowLabel.setText("Products");
            buttonPanel.add(windowLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- newProductButton ----
            newProductButton.setText("New...");
            newProductButton.setMnemonic('N');
            newProductButton.addActionListener(e -> newProduct());
            buttonPanel.add(newProductButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- editProductButton ----
            editProductButton.setText("Edit...");
            editProductButton.setMnemonic('E');
            editProductButton.addActionListener(e -> editProduct());
            buttonPanel.add(editProductButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- deleteProductButton ----
            deleteProductButton.setText("Delete");
            deleteProductButton.setMnemonic('D');
            deleteProductButton.addActionListener(e -> deleteProduct());
            buttonPanel.add(deleteProductButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
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
                this, (BeanProperty) BeanProperty.create("products"), productsTable);
            binding.addColumnBinding(BeanProperty.create("idProduct"))
                .setColumnName("Id Product")
                .setColumnClass(Integer.class)
                .setEditable(false);
            binding.addColumnBinding(BeanProperty.create("name"))
                .setColumnName("name")
                .setColumnClass(String.class);
            binding.addColumnBinding(BeanProperty.create("stock"))
                .setColumnName("Stock")
                .setColumnClass(Integer.class);
            binding.addColumnBinding(BeanProperty.create("priceExVat"))
                .setColumnName("Price Ex Vat $")
                .setColumnClass(BigDecimal.class);
            binding.addColumnBinding(BeanProperty.create("image"))
                .setColumnName("Image")
                .setColumnClass(ImageIcon.class)
                .setEditable(false);
            bindingGroup.addBinding(binding);
        }
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            productsTable, ELProperty.create("${selectedElement != null}"),
            editProductButton, BeanProperty.create("enabled")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            productsTable, ELProperty.create("${selectedElement != null}"),
            deleteProductButton, BeanProperty.create("enabled")));
        bindingGroup.bind();
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JScrollPane productsScrollPane;
    private JTable productsTable;
    private JPanel buttonPanel;
    private JLabel windowLabel;
    private JButton newProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;
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

			// create products view
			ProductsView productsView = new ProductsView();
			productsView.setBorder(new EmptyBorder(10, 10, 10, 10));

			// create frame
			JFrame frame = new JFrame("Products");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(productsView);
			frame.pack();
			frame.setVisible(true);
		});
	}
}
