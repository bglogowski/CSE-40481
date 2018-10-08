/**
 * View.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite;

public class View extends SwingView {

    private Model model;
    // private Controller controller;

    public View(Controller controller) {
        log.info("com.AmazonLite.View(Controller)");
        this.setController(controller);
    }

    /**
     * @return the Model
     */
    final public Model getModel() {
        log.info("com.AmazonLite.View.getModel()");
        return model;
    }

    /**
     * @param model
     *            the Model to set
     */
    final public void setModel(Model model) {
        log.info("com.AmazonLite.View.setModel(Model)");
        this.model = model;
    }

    /**
     * @return the Controller
     */
    public Controller getController() {
        log.info("com.AmazonLite.View.getController()");
        return super.getController();
    }

    /**
     * @param controller
     *            the Controller to set
     */
    public void setController(Controller controller) {
        log.info("com.AmazonLite.View.setController(Controller)");
        super.setController(controller);
    }

}
