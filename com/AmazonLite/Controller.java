/**
 * Controller.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite;

public class Controller extends Component {

    private Model model;
    private View view;

    public Controller() {
        log.info("com.AmazonLite.Controller()");
    }

    public Controller(Model model) {
        log.info("com.AmazonLite.Controller(Model)");
        this.setModel(model);
    }

    public Controller(View view) {
        log.info("com.AmazonLite.Controller(View)");
        this.setView(view);
    }

    /**
     * @return the Model
     */
    final public Model getModel() {
        log.info("com.AmazonLite.Controller.getModel()");
        return model;
    }

    /**
     * @param model
     *            the Model to set
     */
    final public void setModel(Model model) {
        log.info("com.AmazonLite.Controller.setModel(Model)");
        this.model = model;
    }

    /**
     * @return the View
     */
    final public View getView() {
        log.info("com.AmazonLite.Controller.getView()");
        return view;
    }

    /**
     * @param view
     *            the View to set
     */
    final public void setView(View view) {
        log.info("com.AmazonLite.Controller.setView(View)");
        this.view = view;
    }

    // Methods to create inventory items

    public void createItem(String type, String description, Integer quantity) {
        log.info("com.AmazonLite.Controller.createItem(String, String, Integer)");
        model.create(type, description, quantity);
    }

    public void createItem(String description, Integer quantity, Integer pages) {
        log.info("com.AmazonLite.Controller.createItem(String, String, Integer, Integer)");
        model.create(description, quantity, pages);
    }

    // Methods to read inventory items

    public void readInventoryItems() {
        log.info("com.AmazonLite.Controller.listInventoryItems()");
        model.read();
    }

    public void readInventoryItems(Integer i) {
        log.info("com.AmazonLite.Controller.listInventoryItems()");
        model.read(i);
    }

    public void readInventoryItems(String s) {
        log.info("com.AmazonLite.Controller.listInventoryItems()");
        model.read(s);
    }

    // Methods to update inventory items

    public void updateItem(Integer guid, String description, Integer quantity) {
        log.info("com.AmazonLite.Controller.deleteItem(Integer)");
        model.update(guid, description, quantity);
    }

    // Methods to delete inventory items

    public void deleteItem(Integer guid) {
        log.info("com.AmazonLite.Controller.deleteItem(Integer)");
        model.delete(guid);
    }

}
