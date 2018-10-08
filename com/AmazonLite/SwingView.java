/**
 * SwingView.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingView extends Component implements ViewInterface {

    // Fields

    private Controller controller;

    private JFrame frame;
    private JPanel cards;
    private GridBagConstraints c;

    private JPanel mainPanel;
    private JPanel createPanel;
    private JPanel displayPanel;
    private JPanel updatePanel;
    private JPanel deletePanel;
    private JPanel listPanel;
    private JPanel searchGuidInputPanel;
    private JPanel deleteGuidInputPanel;
    private JPanel bookInputPanel;
    private JPanel compactDiscInputPanel;
    private JPanel digitalVideoDiscInputPanel;

    // Constructors

    /**
     * Create a SwingTableView Object.
     * 
     */
    public SwingView() {
        log.info("com.AmazonLite.SwingView()");

        this.frame = new JFrame(Constants.APPNAME);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.frame.setTitle(Constants.APPNAME);

        this.frame.setSize(640, 480);
        this.frame.setMinimumSize(new Dimension(640, 480));

        // This will center the JFrame in the middle of the screen
        this.frame.setLocationRelativeTo(null);

        // Create a card layout to organize the panels
        this.cards = new JPanel(new CardLayout());

        // Use these coordinates to place the GUI elements
        this.c = new GridBagConstraints();

        c.weightx = 1;

        // Call methods which configure the panels
        createMainPanel();
        createCreatePanel();
        createDisplayPanel();
        createUpdatePanel();
        createDeletePanel();
        createListPanel();
        createSearchGuidInputPanel();
        createDeleteGuidInputPanel();
        createBookInputPanel();
        createCompactDiscInputPanel();
        createDigitalVideoDiscInputPanel();

        // Add the panels to the cards Object
        this.cards.add(mainPanel);
        this.cards.add(createPanel);
        this.cards.add(displayPanel);
        this.cards.add(updatePanel);
        this.cards.add(deletePanel);
        this.cards.add(listPanel);
        this.cards.add(bookInputPanel);
        this.cards.add(compactDiscInputPanel);
        this.cards.add(digitalVideoDiscInputPanel);
        this.cards.add(searchGuidInputPanel);
        this.cards.add(deleteGuidInputPanel);

        // Create a scroll pane to navigate the panels
        JScrollPane scrollPane = new JScrollPane(cards);
        this.frame.add(scrollPane, BorderLayout.CENTER);

    }

    /**
     * Create a SwingView Object.
     * 
     * @param controller
     *            The Controller Object
     */
    public SwingView(Controller controller) {
        this();
        log.info("com.AmazonLite.SwingView(Controller)");
        this.setController(controller);
    }

    // Methods

    /**
     * Creates the Main Menu Panel.
     * 
     */
    public void createMainPanel() {
        log.info("com.AmazonLite.SwingView.createMainPanel()");

        this.mainPanel = new JPanel(new GridBagLayout());
        setPanelProperties(mainPanel, "Main Menu");

        JButton createButton = new JButton("Create a new item");
        createButton.setPreferredSize(new Dimension(300, 50));
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                mainPanel.setVisible(false);
                createPanel.setVisible(true);
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        this.mainPanel.add(createButton, c);

        JButton displayButton = new JButton("Display an inventory item");
        displayButton.setPreferredSize(new Dimension(300, 50));
        displayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                mainPanel.setVisible(false);
                displayPanel.setVisible(true);
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        this.mainPanel.add(displayButton, c);

        JButton updateButton = new JButton("Modify or Update an inventory item");
        updateButton.setPreferredSize(new Dimension(300, 50));
        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                mainPanel.setVisible(false);
                updatePanel.setVisible(true);
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        this.mainPanel.add(updateButton, c);

        JButton deleteButton = new JButton("Delete an item from the inventory");
        deleteButton.setPreferredSize(new Dimension(300, 50));
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                mainPanel.setVisible(false);
                deletePanel.setVisible(true);
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        mainPanel.add(deleteButton, c);

        JButton listButton = new JButton("List all items in the inventory");
        listButton.setPreferredSize(new Dimension(300, 50));
        listButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                mainPanel.setVisible(false);
                listPanel.setVisible(true);
                controller.readInventoryItems();
            }
        });
        c.gridx = 0;
        c.gridy = 5;
        mainPanel.add(listButton, c);

        JButton exitButton = new JButton("Exit the program");
        exitButton.setPreferredSize(new Dimension(300, 50));
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        c.gridx = 0;
        c.gridy = 6;
        mainPanel.add(exitButton, c);

    }

    /**
     * Creates the Create Item Panel.
     * 
     */
    private void createCreatePanel() {
        log.info("com.AmazonLite.SwingView.createCreatePanel()");

        this.createPanel = new JPanel(new GridBagLayout());
        setPanelProperties(createPanel, "Create Item Menu");

        JButton createBookButton = new JButton("Create a new book item");
        createBookButton.setPreferredSize(new Dimension(300, 50));
        createBookButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                bookInputPanel.setVisible(true);
                createPanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        createPanel.add(createBookButton, c);

        JButton createCompactDiscButton = new JButton("Create a new CD item");
        createCompactDiscButton.setPreferredSize(new Dimension(300, 50));
        createCompactDiscButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                compactDiscInputPanel.setVisible(true);
                createPanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        createPanel.add(createCompactDiscButton, c);

        JButton createDigitalVideoDiscButton = new JButton("Create a new DVD item");
        createDigitalVideoDiscButton.setPreferredSize(new Dimension(300, 50));
        createDigitalVideoDiscButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                digitalVideoDiscInputPanel.setVisible(true);
                createPanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        createPanel.add(createDigitalVideoDiscButton, c);

        JButton mainButton = new JButton("Return to Main Menu");
        mainButton.setPreferredSize(new Dimension(300, 50));
        mainButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                mainPanel.setVisible(true);
                createPanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        createPanel.add(mainButton, c);

    }

    /**
     * Creates the Display Item Panel.
     * 
     */
    private void createDisplayPanel() {
        log.info("com.AmazonLite.SwingView.createDisplayPanel()");

        this.displayPanel = new JPanel(new GridBagLayout());
        setPanelProperties(this.displayPanel, "Display Items Menu");

        JButton displayItemButton = new JButton("Search the inventory by GUID");
        displayItemButton.setPreferredSize(new Dimension(300, 50));
        displayItemButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                displayPanel.setVisible(false);
                searchGuidInputPanel.setVisible(true);
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        displayPanel.add(displayItemButton, c);

        JButton displayBooksButton = new JButton("List all Books in the inventory");
        displayBooksButton.setPreferredSize(new Dimension(300, 50));
        displayBooksButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                displayPanel.setVisible(false);
                controller.readInventoryItems("books");
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        displayPanel.add(displayBooksButton, c);

        JButton displayCompactDiscsButton = new JButton("List all CDs in the inventory");
        displayCompactDiscsButton.setPreferredSize(new Dimension(300, 50));
        displayCompactDiscsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                displayPanel.setVisible(false);
                controller.readInventoryItems("cds");
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        displayPanel.add(displayCompactDiscsButton, c);

        JButton displayDigitalVideoDiscsButton = new JButton("List all DVDs in the inventory");
        displayDigitalVideoDiscsButton.setPreferredSize(new Dimension(300, 50));
        displayDigitalVideoDiscsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                displayPanel.setVisible(false);
                controller.readInventoryItems("dvds");
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        displayPanel.add(displayDigitalVideoDiscsButton, c);

        JButton displayMainButton = new JButton("Return to Main Menu");
        displayMainButton.setPreferredSize(new Dimension(300, 50));
        displayMainButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                mainPanel.setVisible(true);
                displayPanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 5;
        displayPanel.add(displayMainButton, c);

    }

    /**
     * Creates the Update Item Panel.
     * 
     */
    private void createUpdatePanel() {
        log.info("com.AmazonLite.SwingView.createUpdatePanel()");

        this.updatePanel = new JPanel(new GridBagLayout());
        setPanelProperties(updatePanel, "Modify or Update Item Menu");

        JLabel guidLabel = new JLabel("GUID of item to update:", SwingConstants.RIGHT);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 1;
        updatePanel.add(guidLabel, c);

        JTextField guidField = new JTextField(20);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 1;
        updatePanel.add(guidField, c);

        JLabel descriptionLabel = new JLabel("Description:", SwingConstants.RIGHT);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 2;
        updatePanel.add(descriptionLabel, c);

        JTextField descriptionField = new JTextField(20);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 2;
        updatePanel.add(descriptionField, c);

        JLabel quantityLabel = new JLabel("Quantity:", SwingConstants.RIGHT);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 3;
        updatePanel.add(quantityLabel, c);

        JTextField quantityField = new JTextField(20);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 3;
        updatePanel.add(quantityField, c);

        JButton acceptButton = new JButton("Update Item");
        acceptButton.setPreferredSize(new Dimension(300, 50));
        acceptButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                controller.updateItem(Integer.valueOf(guidField.getText()), descriptionField.getText(),
                        Integer.valueOf(quantityField.getText()));
                updatePanel.setVisible(false);
            }
        });
        c.gridy = 4;
        updatePanel.add(acceptButton, c);

        c.anchor = GridBagConstraints.CENTER;

    }

    /**
     * Creates the Delete Item Panel.
     * 
     */
    private void createDeletePanel() {
        log.info("com.AmazonLite.SwingView.createDeletePanel()");

        this.deletePanel = new JPanel(new GridBagLayout());
        setPanelProperties(deletePanel, "Delete Items Menu");

        JButton deleteItemButton = new JButton("Delete item from the inventory by GUID");
        deleteItemButton.setPreferredSize(new Dimension(300, 50));
        deleteItemButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                deletePanel.setVisible(false);
                deleteGuidInputPanel.setVisible(true);
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        deletePanel.add(deleteItemButton, c);

        JButton mainButton = new JButton("Return to Main Menu");
        mainButton.setPreferredSize(new Dimension(300, 50));
        mainButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                mainPanel.setVisible(true);
                deletePanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        deletePanel.add(mainButton, c);

    }

    /**
     * Creates the Item List Output Panel.
     * 
     */
    private void createListPanel() {
        log.info("com.AmazonLite.SwingView.createListPanel()");

        listPanel = new JPanel(new GridBagLayout());
        setPanelProperties(listPanel, "List of Items");

    }

    /**
     * Creates the Item Search Input Panel.
     * 
     */
    private void createSearchGuidInputPanel() {
        log.info("com.AmazonLite.SwingView.createSearchGuidInputPanel()");

        this.searchGuidInputPanel = new JPanel(new GridBagLayout());
        setPanelProperties(searchGuidInputPanel, "Enter the GUID to find");

        JTextField textField = new JTextField(20);
        textField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                controller.readInventoryItems(Integer.valueOf(textField.getText()));
                searchGuidInputPanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        searchGuidInputPanel.add(textField, c);

        JButton acceptButton = new JButton("Search for Item");
        acceptButton.setPreferredSize(new Dimension(300, 50));
        acceptButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                controller.readInventoryItems(Integer.valueOf(textField.getText()));
                searchGuidInputPanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        searchGuidInputPanel.add(acceptButton, c);

    }

    /**
     * Creates the Book Input Panel.
     * 
     */
    private void createBookInputPanel() {
        log.info("com.AmazonLite.SwingView.createBookInputPanel()");

        this.bookInputPanel = new JPanel(new GridBagLayout());
        setPanelProperties(bookInputPanel, "Add a Book to the inventory");

        JLabel descriptionLabel = new JLabel("Description:", SwingConstants.RIGHT);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 1;
        bookInputPanel.add(descriptionLabel, c);

        JTextField descriptionField = new JTextField(20);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 1;
        bookInputPanel.add(descriptionField, c);

        JLabel quantityLabel = new JLabel("Quantity:", SwingConstants.RIGHT);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 2;
        bookInputPanel.add(quantityLabel, c);

        JTextField quantityField = new JTextField(20);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 2;
        bookInputPanel.add(quantityField, c);

        JLabel pageNumberLabel = new JLabel("Number of Pages:", SwingConstants.RIGHT);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 3;
        bookInputPanel.add(pageNumberLabel, c);

        JTextField pageNumberField = new JTextField(20);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 3;
        bookInputPanel.add(pageNumberField, c);

        JButton acceptButton = new JButton("Add Book");
        acceptButton.setPreferredSize(new Dimension(300, 50));
        acceptButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                controller.createItem(descriptionField.getText(), Integer.valueOf(quantityField.getText()),
                        Integer.valueOf(pageNumberField.getText()));
                bookInputPanel.setVisible(false);
            }
        });
        c.gridy = 4;
        bookInputPanel.add(acceptButton, c);

        c.anchor = GridBagConstraints.CENTER;

    }

    /**
     * Creates the CD Input Panel.
     * 
     */
    private void createCompactDiscInputPanel() {
        log.info("com.AmazonLite.SwingView.createCompactDiscInputPanel()");

        this.compactDiscInputPanel = new JPanel(new GridBagLayout());
        setPanelProperties(compactDiscInputPanel, "Add a CD to the inventory");

        JLabel descriptionLabel = new JLabel("Description:", SwingConstants.RIGHT);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 1;
        compactDiscInputPanel.add(descriptionLabel, c);

        JTextField descriptionField = new JTextField(20);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 1;
        compactDiscInputPanel.add(descriptionField, c);

        JLabel quantityLabel = new JLabel("Quantity:", SwingConstants.RIGHT);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 2;
        compactDiscInputPanel.add(quantityLabel, c);

        JTextField quantityField = new JTextField(20);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 2;
        compactDiscInputPanel.add(quantityField, c);

        JButton acceptButton = new JButton("Add CD");
        acceptButton.setPreferredSize(new Dimension(300, 50));
        acceptButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                controller.createItem("CD", descriptionField.getText(), Integer.valueOf(quantityField.getText()));
                compactDiscInputPanel.setVisible(false);
            }
        });
        c.gridy = 4;
        compactDiscInputPanel.add(acceptButton, c);

        c.anchor = GridBagConstraints.CENTER;

    }

    /**
     * Creates the DVD Input Panel.
     * 
     */
    private void createDigitalVideoDiscInputPanel() {
        log.info("com.AmazonLite.SwingView.createDigitalVideoDiscInputPanel()");

        this.digitalVideoDiscInputPanel = new JPanel(new GridBagLayout());
        setPanelProperties(digitalVideoDiscInputPanel, "Add a DVD to the inventory");

        JLabel descriptionLabel = new JLabel("Description:", SwingConstants.RIGHT);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 1;
        digitalVideoDiscInputPanel.add(descriptionLabel, c);

        JTextField descriptionField = new JTextField(20);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 1;
        digitalVideoDiscInputPanel.add(descriptionField, c);

        JLabel quantityLabel = new JLabel("Quantity:", SwingConstants.RIGHT);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 2;
        digitalVideoDiscInputPanel.add(quantityLabel, c);

        JTextField quantityField = new JTextField(20);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 2;
        digitalVideoDiscInputPanel.add(quantityField, c);

        JButton acceptButton = new JButton("Add DVD");
        acceptButton.setPreferredSize(new Dimension(300, 50));
        acceptButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                controller.createItem("DVD", descriptionField.getText(), Integer.valueOf(quantityField.getText()));
                digitalVideoDiscInputPanel.setVisible(false);
            }
        });
        c.gridy = 4;
        digitalVideoDiscInputPanel.add(acceptButton, c);

        c.anchor = GridBagConstraints.CENTER;

    }

    /**
     * Creates the Delete Item Input Panel.
     * 
     */
    private void createDeleteGuidInputPanel() {
        log.info("com.AmazonLite.SwingView.createInputDeleteGuidPanel()");

        this.deleteGuidInputPanel = new JPanel(new GridBagLayout());
        setPanelProperties(deleteGuidInputPanel, "Enter the GUID to Delete");

        JTextField textField = new JTextField(20);
        textField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                controller.deleteItem(Integer.valueOf(textField.getText()));
                deleteGuidInputPanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        deleteGuidInputPanel.add(textField, c);

        JButton acceptButton = new JButton("Delete Item");
        acceptButton.setPreferredSize(new Dimension(300, 50));
        acceptButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                controller.deleteItem(Integer.valueOf(textField.getText()));
                deleteGuidInputPanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        deleteGuidInputPanel.add(acceptButton, c);

    }

    /**
     * Updates the View based on Events in the Model.
     * 
     * @return controller Returns the Controller Object
     */
    public Controller getController() {
        log.info("com.AmazonLite.SwingView.getController()");
        return controller;
    }

    /**
     * Sets the Controller Object
     * 
     * @param controller
     *            the Controller to set
     */
    public void setController(Controller controller) {
        log.info("com.AmazonLite.SwingView.setController(Controller)");
        this.controller = controller;
    }

    /**
     * Sets default Panel properties for panels
     * 
     * @param panel
     *            The panel to configure
     * @param label
     *            The label to use for the panel
     */
    private void setPanelProperties(JPanel panel, String label) {
        panel.setOpaque(true);
        panel.add(new JLabel(label));
        panel.setVisible(false);
    }

    /**
     * Sets the Controller Object
     * 
     * @param obj
     *            An Object data set returned from the Model
     */
    public void update(Object[][] obj) {
        log.info("com.AmazonLite.SwingView.update(Object[][])");

        listPanel.removeAll();

        // Create Labels for each panel view based on the calling method
        switch (Thread.currentThread().getStackTrace()[2].getMethodName()) {
        case "create":
            listPanel.add(new JLabel("Items Created"));
            break;
        case "read":
            listPanel.add(new JLabel("List of Items"));
            break;
        case "update":
            listPanel.add(new JLabel("Items Modified"));
            break;
        case "delete":
            listPanel.add(new JLabel("Items Deleted"));
            break;
        default:
            // If nothing else, it's just a list of items...
            listPanel.add(new JLabel("List of Items"));
            break;
        }

        String[] columns = new String[] { "GUID", "Description", "Quantity" };

        // Create a table with the Object data
        JTable table = new JTable(obj, columns);

        // Put the table in a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 350));

        // Add the scroll pane to the panel
        c.gridx = 0;
        c.gridy = 1;
        this.listPanel.add(scrollPane, c);

        // Create a button to return the user to the Main Menu
        JButton mainButton = new JButton("Return to Main Menu");
        mainButton.setPreferredSize(new Dimension(300, 50));
        mainButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                mainPanel.setVisible(true);
                listPanel.setVisible(false);
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        this.listPanel.add(mainButton, c);

        // Make sure this panel is visible to the user
        this.listPanel.setVisible(true);

    }

    /**
     * Starts the Graphical Interface.
     * 
     */
    @Override
    public void start() {
        log.info("com.AmazonLite.SwingView.start()");
        
        // Make the frame and Main Menu panel visible
        frame.setVisible(true);
        mainPanel.setVisible(true);
    }

    /**
     * Resets the view to the Main Menu view.
     * 
     */
    @Override
    public void reset() {
        log.info("com.AmazonLite.SwingView.reset()");
        
        // Reset all the views to their default states
        frame.setVisible(true);
        mainPanel.setVisible(true);
        createPanel.setVisible(false);
        displayPanel.setVisible(false);
        updatePanel.setVisible(false);
        deletePanel.setVisible(false);
        listPanel.setVisible(false);
        searchGuidInputPanel.setVisible(false);
        deleteGuidInputPanel.setVisible(false);
        bookInputPanel.setVisible(false);
        compactDiscInputPanel.setVisible(false);
        digitalVideoDiscInputPanel.setVisible(false);
    }

}
