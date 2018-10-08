/**
 * Item.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite.items;

import java.util.logging.*;

public abstract class Item {

    // Fields

    private static int count;

    private ItemType itemType;

    private int guid;
    private String description;
    private int quantity;

    protected Logger log;

    // Constructors

    /**
     * Create a Item Object.
     * 
     */
    public Item() {
        this.guid = ++count;
        setLogginge();
    }

    /**
     * Create a Item Object specifying a GUID, description & quantity.
     * 
     * @param guid
     *            The GUID of the inventory item
     * @param description
     *            The description of the inventory item
     * @param quantity
     *            The quantity of the item in the inventory
     */
    public Item(int guid, String description, int quantity) {

        setLogginge();
        incrementCountTo(guid);

        this.guid = guid;
        this.description = description;
        this.quantity = quantity;
    }

    // Methods

    /**
     * Set the logging level for the Item Objects.
     * 
     */
    public void setLogginge() {
        this.log = Logger.getLogger(this.getClass().getSimpleName());
        this.log.setLevel(Level.SEVERE);
    }

    /**
     * Returns the GUID of the Item.
     * 
     * @return guid An Integer representation of the GUID of the Item
     */
    public int getGuid() {
        return guid;
    }

    /**
     * Set the description of the Item.
     * 
     * @param s
     *            A String representation of the description of the Item
     */
    public void setDescription(String s) {
        this.description = s;
    }

    /**
     * Returns the quantity of the Item.
     * 
     * @return description An String representation of the description of the Item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the quantity of the Item available.
     * 
     * @param i
     *            An Integer representation of the quantity of the Item available
     */
    public void setQuantity(Integer i) {
        this.quantity = i;
    }

    /**
     * Returns the quantity of the Item.
     * 
     * @return quantity An Integer representation of the number of the Item
     *         available
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param itemType
     *            the itemType to set
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    /**
     * Set the number of items that exist.
     * 
     * @param guid
     *            An Integer representation of highest GUID in use in by any Item
     *            Object
     */
    public void incrementCountTo(int guid) {
        if (guid > count) {
            count = guid;
        }
    }

}
