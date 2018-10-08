/**
 * CompactDisc.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite.items;

public class CompactDisc extends Disc {

    // Fields
    private static final DiscType compactDiscType = DiscType.AUDIO;

    // Constructors

    /**
     * Create a CompactDisc Object specifying a GUID, description & quantity.
     *
     */
    public CompactDisc() {
        super();
        this.setDiscType(compactDiscType);
        setItemType(ItemType.CD);
    }

    /**
     * Create a CompactDisc Object specifying a GUID, description & quantity.
     * 
     * @param guid
     *            The GUID of the inventory item
     * @param description
     *            The description of the inventory item
     * @param quantity
     *            The quantity of the item in the inventory
     * @param pages
     *            The number of pages in the book
     */
    public CompactDisc(int guid, String description, int quantity) {
        super(guid, description, quantity);
        this.setDiscType(compactDiscType);
        setItemType(ItemType.CD);
    }

}
