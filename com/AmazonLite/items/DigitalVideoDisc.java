/**
 * DigitalVideoDisc.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite.items;

public class DigitalVideoDisc extends Disc {

    // Fields
    private static final DiscType digitalVideoDiscType = DiscType.VIDEO;

    // Constructors

    /**
     * Create a DigitalVideoDisc Object.
     * 
     */
    public DigitalVideoDisc() {
        super();
        this.setDiscType(digitalVideoDiscType);
        setItemType(ItemType.DVD);
    }

    /**
     * Create a DigitalVideoDisc Object specifying a GUID, description & quantity.
     * 
     * @param guid
     *            The GUID of the inventory item
     * @param description
     *            The description of the inventory item
     * @param quantity
     *            The quantity of the item in the inventory
     */
    public DigitalVideoDisc(int guid, String description, int quantity) {
        super(guid, description, quantity);
        this.setDiscType(digitalVideoDiscType);
        setItemType(ItemType.DVD);
    }

}
