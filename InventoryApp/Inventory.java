/**
 * Inventory.java
 */

/**
 * @author Bryan Glogowski
 * @version 1.0
 */

package InventoryApp;

import com.AmazonLite.*;

public class Inventory {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller);

        model.setView(view);
        view.setModel(model);

        view.start();

    }

}
