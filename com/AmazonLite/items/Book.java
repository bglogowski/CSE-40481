/**
 * Book.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite.items;

public class Book extends Item implements PropertiesModelItem {

    // Fields
    private int numberOfPages;

    // Constructors

    /**
     * Create a Book Object.
     * 
     */
    public Book() {
        super();
        setItemType(ItemType.BOOK);
    }

    /**
     * Create a Book Object specifying a GUID, description & quantity.
     * 
     * @param guid
     *            The GUID of the inventory item
     * @param description
     *            The description of the inventory item
     * @param quantity
     *            The quantity of the item in the inventory
     */
    public Book(int guid, String description, int quantity) {
        super(guid, description, quantity);
        setItemType(ItemType.BOOK);
    }

    /**
     * Create a Book Object specifying a GUID, description, quantity & number of
     * pages.
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
    public Book(int guid, String description, int quantity, int pages) {
        super(guid, description, quantity);
        this.numberOfPages = pages;
        setItemType(ItemType.BOOK);
    }

    // Methods

    /**
     * Set the number of pages for a book.
     * 
     * @param numberOfPages
     *            An Integer representation of the number of pages in a book
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * Returns the number of pages in a book.
     * 
     * @return numberOfPages An Integer representation of the number of pages in a
     *         book
     */
    public int getNumberOfPages() {
        return numberOfPages;
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
        strArray[3] = Integer.toString(getNumberOfPages());
        return strArray;
    }

}
