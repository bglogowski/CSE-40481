/**
 * Disc.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite.items;

public abstract class Disc extends Item implements PropertiesModelItem {

    // Fields

    private DiscType discType;

    // Constructors

    /**
     * Create a Disc Object.
     * 
     */
    public Disc() {
        super();
    }

    /**
     * Create a Disc Object specifying a GUID, description & quantity.
     * 
     * @param guid
     *            The GUID of the inventory item
     * @param description
     *            The description of the inventory item
     * @param quantity
     *            The quantity of the item in the inventory
     */
    public Disc(int guid, String description, int quantity) {
        super(guid, description, quantity);
    }

    // Methods

    public void setDiscType(DiscType discType) {
        this.discType = discType;
    }

    /**
     * Returns the type of disc.
     * 
     * @return discType An Enum representation of the type of disc
     */
    public DiscType getDiscType() {
        return discType;
    }

    /**
     * Returns a String array of all the data in the Object.
     * 
     * @return strArray A String representation of the data in the Object
     */
    public String[] toArray() {
        String[] strArray = new String[4];
        strArray[0] = getItemType().name();
        strArray[1] = getDescription();
        strArray[2] = Integer.toString(getQuantity());
        strArray[3] = getDiscType().name();
        return strArray;
    }

}
