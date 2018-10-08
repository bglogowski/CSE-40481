/**
 * Model.java
 */

/**
 * @author Bryan Glogowski
 * @version 3.0
 */

package com.AmazonLite;

import java.util.LinkedList;

public class Model extends SqliteModel {

    // Fields

    private static final long serialVersionUID = 3L;
    private LinkedList<View> views = new LinkedList<View>();
    private Controller controller;

    // Constructors

    public Model() {
        super();

    }

    public Model(View view) {
        super();

        this.setView(view);
    }

    public Model(Controller controller) {
        super();

        this.setController(controller);
    }

    public Model(View view, Controller controller) {
        super();

        this.setView(view);
        this.setController(controller);
    }

    public Model(Controller controller, View view) {
        super();

        this.setView(view);
        this.setController(controller);
    }

    // Methods

    /**
     * @return the View
     */
    public LinkedList<View> getViews() {
        return views;
    }

    /**
     * @param view
     *            the View to set
     */
    public void setView(View view) {
        views.add(view);
    }

    /**
     * @return the Controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * @param controller
     *            the Controller to set
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Create an item and put it in the inventory
     * 
     * @param controller
     *            the Controller to set
     * @param controller
     *            the Controller to set
     * @param controller
     *            the Controller to set
     */
    public void create(String type, String description, Integer quantity) {

        Object[][] obj = null;

        switch (type.toUpperCase()) {
        case "BOOK":
            obj = createBook(description, quantity);
            break;
        case "CD":
            obj = createCompactDisc(description, quantity);
            break;
        case "DVD":
            obj = createDigitalVideoDisc(description, quantity);
            break;
        }

        for (View v : views) {
            v.update(obj);
        }

    }

    /**
     * Create an item and put it in the inventory
     * 
     * @param controller
     *            the Controller to set
     * @param controller
     *            the Controller to set
     * @param controller
     *            the Controller to set
     */
    public void create(String description, Integer quantity, Integer pages) {

        Object[][] obj = null;

        obj = createBook(description, quantity, pages);

        for (View v : views) {
            v.update(obj);
        }

    }

    /**
     * Read all Items in the inventory
     * 
     */
    public void read() {

        Object[][] obj = readItems();

        for (View v : views) {
            v.update(obj);
        }
    }

    /**
     * Read an Item in the inventory
     * 
     * @param i
     *            An Integer representation of the GUID of the Object
     */
    public void read(Integer i) {

        Object[][] obj = readItems(i);

        for (View v : views) {
            v.update(obj);
        }

    }

    /**
     * Read items in the inventory
     * 
     * @param s
     *            A String representation of a category of items to read
     */
    public void read(String s) {

        Object[][] obj = readItems(s);

        for (View v : views) {
            v.update(obj);
        }

    }

    /**
     * Update an item and update the Views
     * 
     * @param g
     *            An Integer representation of the GUID of the Object
     * @param d
     *            A String representation of the description of the item
     * @param q
     *            An Integer representation of the quantity of items
     */
    public void update(Integer g, String d, Integer q) {

        Object[][] obj = updateItems(g, d, q);

        for (View v : views) {
            v.update(obj);
        }

    }

    /**
     * Delete an item from the inventory and update the Views
     * 
     * @param i
     *            An Integer representation of the GUID of the Object
     */
    public void delete(Integer i) {

        Object[][] obj = null;

        obj = deleteItems(i);

        for (View v : views) {
            v.update(obj);
        }

    }

}
