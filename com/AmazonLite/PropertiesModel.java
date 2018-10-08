/**
 * PropertiesModel.java
 */

/**
 * @author Bryan Glogowski
 * @version 1.0
 */

package com.AmazonLite;

import java.io.*;
import java.util.*;
import com.AmazonLite.items.*;

public class PropertiesModel extends AbstractModel {

    private LinkedList<Book> books = new LinkedList<>();
    private LinkedList<CompactDisc> cds = new LinkedList<>();
    private LinkedList<DigitalVideoDisc> dvds = new LinkedList<>();
    
    
    
    public PropertiesModel() {
        readPersistentData();     
    }
    

    public void createBook(String description, Integer quantity) {
        log.info("com.AmazonLite.PropertiesModel.createBook(String, Integer)");

        Book book = new Book();
        book.setDescription(description);
        book.setQuantity(quantity);
        books.add(book);
        writePersistentData();
    }

    public void createBook(String description, Integer quantity, Integer pages) {
        log.info("com.AmazonLite.PropertiesModel.createBook(String, Integer, Integer)");

        Book book = new Book();
        book.setDescription(description);
        book.setQuantity(quantity);
        book.setNumberOfPages(pages);
        books.add(book);
        writePersistentData();
    }

    protected LinkedList<String> readBooks() {
        log.info("com.AmazonLite.PropertiesModel.readBookData()");

        LinkedList<String> text = new LinkedList<>();

        String s = new String();

        String guid;
        String desc;
        String qty;
        String pages;

        for (Book b : books) {
            guid = Integer.toString(b.getGuid());
            desc = b.getDescription();
            qty = Integer.toString(b.getQuantity());
            pages = Integer.toString(b.getNumberOfPages());

            s = "GUID=" + guid + ", Desc.=" + desc + ", Qty.=" + qty + ", Pages=" + pages;
            text.add(s);
        }
        return text;
    }

    public void updateBook(Integer guid, String description, Integer quantity) {
        log.info("com.AmazonLite.PropertiesModel.updateBook(Integer, String, Integer)");

        int i = 0;

        for (Book b : books) {
            if (b.getGuid() == guid) {
                books.set(i, new Book(guid, description, quantity));
            }
            i++;
        }
        writePersistentData();
    }

    public void updateBook(Integer guid, String description, Integer quantity, Integer pages) {
        log.info("com.AmazonLite.PropertiesModel.updateBook(Integer, String, Integer, Integer)");

        int i = 0;

        for (Book b : books) {
            if (b.getGuid() == guid) {
                books.set(i, new Book(guid, description, quantity, pages));
            }
            i++;
        }
        writePersistentData();
    }

    public void deleteBook(Integer guid) {
        log.info("com.AmazonLite.PropertiesModel.deleteBook(Integer)");

        int key;

        for (Book b : books) {
            key = b.getGuid();
            if (key == guid) {
                books.remove(b);
                writePropertiesFile();
            }
        }
    }

    /**
     * Add a CompactDisc Object to the data set.
     * 
     * @param description
     *            The description of the object
     * @param quantity
     *            The number of items in the inventory
     */
    public void createCompactDisc(String description, Integer quantity) {
        log.info("com.AmazonLite.PropertiesModel.createCompactDisc(String, Integer)");

        CompactDisc cd = new CompactDisc();
        cd.setDescription(description);
        cd.setQuantity(quantity);
        cds.add(cd);
        writePropertiesFile();
    }

    protected LinkedList<String> readCompactDiscs() {
        log.info("com.AmazonLite.PropertiesModel.readCompactDiscData()");

        LinkedList<String> text = new LinkedList<>();
        String s = new String();

        String guid;
        String desc;
        String qty;

        for (CompactDisc c : cds) {
            guid = Integer.toString(c.getGuid());
            desc = c.getDescription();
            qty = Integer.toString(c.getQuantity());

            s = "GUID=" + guid + ", Desc.=" + desc + ", Qty.=" + qty;
            text.add(s);
        }

        return text;
    }

    public void updateCompactDisc(Integer g, String d, Integer q) {
        log.info("com.AmazonLite.PropertiesModel.updateCompactDisc(Integer, String, Integer)");

        int guid;
        int i = 0;

        for (CompactDisc c : cds) {
            guid = c.getGuid();
            if (guid == g) {
                cds.set(i, new CompactDisc(g, d, q));
                writePropertiesFile();
            }
            i++;
        }
    }

    /**
     * Add a DigitalVideoDisc Object to the data set.
     * 
     * @param description
     *            The description of the object
     * @param quantity
     *            The number of items in the inventory
     */
    public void createDigitalVideoDisc(String description, Integer quantity) {
        log.info("com.AmazonLite.PropertiesModel.createDigitalVideoDisc(String, Integer)");

        DigitalVideoDisc dvd = new DigitalVideoDisc();
        dvd.setDescription(description);
        dvd.setQuantity(quantity);
        dvds.add(dvd);
        writePropertiesFile();
    }

    protected LinkedList<String> readDigitalVideoDiscs() {
        log.info("com.AmazonLite.PropertiesModel.readDigitalVideoDiscData()");

        LinkedList<String> text = new LinkedList<>();
        String s = new String();

        String guid;
        String desc;
        String qty;

        for (DigitalVideoDisc d : dvds) {
            guid = Integer.toString(d.getGuid());
            desc = d.getDescription();
            qty = Integer.toString(d.getQuantity());

            s = "GUID=" + guid + ", Desc.=" + desc + ", Qty.=" + qty;
            text.add(s);
        }
        return text;
    }

    public void updateDigitalVideoDisc(Integer g, String d, Integer q) {
        log.info("com.AmazonLite.PropertiesModel.updateDigitalVideoDisc(Integer, String, Integer)");

        int guid;
        int i = 0;

        for (DigitalVideoDisc v : dvds) {
            guid = v.getGuid();
            if (guid == g) {
                dvds.set(i, new DigitalVideoDisc(g, d, q));
                writePropertiesFile();
            }
            i++;
        }
    }

    public LinkedList<String> updateItem(Integer g, String d, Integer q) {

        LinkedList<String> text = new LinkedList<>();

        updateBook(g, d, q);
        updateCompactDisc(g, d, q);
        updateDigitalVideoDisc(g, d, q);

        text.add("Item Updated.");

        return text;
    }

    /**
     * Remove an item from the inventory.
     * 
     * @param guid
     *            The GUID of the Object
     */
    public LinkedList<String> deleteItem(Integer guid) {
        log.info("com.AmazonLite.PropertiesModel.deleteItem(Integer)");

        LinkedList<String> text = new LinkedList<>();

        books.removeIf((Book b) -> b.getGuid() == guid);
        cds.removeIf((CompactDisc c) -> c.getGuid() == guid);
        dvds.removeIf((DigitalVideoDisc d) -> d.getGuid() == guid);

        text.add(Integer.toString(guid));
        writePropertiesFile();
        return text;

    }

    protected LinkedList<String> readAll() {
        log.info("com.AmazonLite.PropertiesModel.readAllData()");

        LinkedList<String> text = new LinkedList<>();

        for (String s : readBooks()) {
            text.add(s);
        }

        for (String s : readCompactDiscs()) {
            text.add(s);
        }

        for (String s : readDigitalVideoDiscs()) {
            text.add(s);
        }

        // Return a list that is sorted by GUID
        Collections.sort(text);
        return text;
    }

    protected LinkedList<String> readItem(Integer i) {
        log.info("com.AmazonLite.PropertiesModel.readItem(Integer)");

        LinkedList<String> text = new LinkedList<>();

        String s = new String();

        String guid;
        String desc;
        String qty;
        String pages;

        for (Book b : books) {
            guid = Integer.toString(b.getGuid());
            desc = b.getDescription();
            qty = Integer.toString(b.getQuantity());
            pages = Integer.toString(b.getNumberOfPages());

            if (b.getGuid() == i) {
                s = "GUID=" + guid + ", Desc.=" + desc + ", Qty.=" + qty + ", Pages=" + pages;
                text.add(s);
            }
        }

        for (CompactDisc c : cds) {
            guid = Integer.toString(c.getGuid());
            desc = c.getDescription();
            qty = Integer.toString(c.getQuantity());

            if (c.getGuid() == i) {
                s = "GUID=" + guid + ", Desc.=" + desc + ", Qty.=" + qty;
                text.add(s);
            }
        }

        for (DigitalVideoDisc d : dvds) {
            guid = Integer.toString(d.getGuid());
            desc = d.getDescription();
            qty = Integer.toString(d.getQuantity());

            if (d.getGuid() == i) {
                s = "GUID=" + guid + ", Desc.=" + desc + ", Qty.=" + qty;
                text.add(s);
            }
        }

        return text;
    }

    // Methods that do I/O

    protected void readPersistentData() {
        log.info("com.AmazonLite.PropertiesModel.readPersistentData()");
        readPropertiesFile();

    }

    private void readPropertiesFile() {
        log.info("com.AmazonLite.PropertiesModel.readPropertiesFile()");

        Properties p = new Properties();
        String[] properties;
        File props = new File(Constants.FILENAME);

        log.info("com.AmazonLite.PropertiesModel.readPropertiesFile() find: " + Constants.FILENAME);
        if (props.exists()) {

            try {
                log.info("com.AmazonLite.PropertiesModel.readPropertiesFile() read: " + Constants.FILENAME);
                FileInputStream in = new FileInputStream(Constants.FILENAME);
                p.load(in);
                in.close();

                Set<String> keys = p.stringPropertyNames();
                for (String key : keys) {

                    properties = p.getProperty(key).split(Constants.DELIMITER);

                    if (properties[0].equals("BOOK")) {
                        log.info("com.AmazonLite.PropertiesModel.readPropertiesFile() books: found key:" + key
                                + ", value:" + p.getProperty(key));
                        books.add(new Book(Integer.parseInt(key), properties[1], Integer.parseInt(properties[2]),
                                Integer.parseInt(properties[3])));

                    }

                    if (properties[0].equals("CD")) {
                        log.info("com.AmazonLite.PropertiesModel.readPropertiesFile() cds: found key:" + key
                                + ", value:" + p.getProperty(key));
                        cds.add(new CompactDisc(Integer.parseInt(key), properties[1], Integer.parseInt(properties[2])));
                    }

                    if (properties[0].equals("DVD")) {
                        log.info("com.AmazonLite.PropertiesModel.readPropertiesFile() dvds: found key:" + key
                                + ", value:" + p.getProperty(key));
                        dvds.add(new DigitalVideoDisc(Integer.parseInt(key), properties[1],
                                Integer.parseInt(properties[2])));
                    }

                }

            } catch (Exception e) {
                log.severe(e.getMessage());
                log.severe(
                        "com.AmazonLite.PropertiesModel.readPropertiesFile() read: " + Constants.FILENAME + " failed.");
            }

        } else {
            log.severe(
                    "com.AmazonLite.PropertiesModel.readPropertiesFile() find: " + Constants.FILENAME + " not found.");
        }

    }

    protected void writePersistentData() {
        log.info("com.AmazonLite.PropertiesModel.writePersistentData()");
        writePropertiesFile();
    }

    private void writePropertiesFile() {
        log.info("com.AmazonLite.PropertiesModel.writePropertiesFile()");

        Properties p = new Properties();

        String key;
        String value;

        // Convert Book data into the properties format
        for (Book b : books) {
            key = Integer.toString(b.getGuid());
            value = String.join(Constants.DELIMITER, b.toArray());
            log.info("com.AmazonLite.PropertiesModel.writePropertiesFile() books: found key:" + key + ", value:"
                    + value);
            p.setProperty(key, value);
        }

        // Convert CD data into the properties format
        for (CompactDisc c : cds) {
            key = Integer.toString(c.getGuid());
            value = String.join(Constants.DELIMITER, c.toArray());
            log.info("com.AmazonLite.PropertiesModel.writePropertiesFile() cds: found key:" + key + ", value:" + value);
            p.setProperty(key, value);
        }

        // Convert DVD data into the properties format
        for (DigitalVideoDisc d : dvds) {
            key = Integer.toString(d.getGuid());
            value = String.join(Constants.DELIMITER, d.toArray());
            log.info(
                    "com.AmazonLite.PropertiesModel.writePropertiesFile() dvds: found key:" + key + ", value:" + value);
            p.setProperty(key, value);
        }

        try {
            log.info("com.AmazonLite.PropertiesModel.writePropertiesFile() write: " + Constants.FILENAME);
            FileOutputStream out = new FileOutputStream(Constants.FILENAME);
            p.store(out, Constants.APPNAME + " Inventory");
            out.close();
        } catch (Exception e) {
            log.severe(e.getMessage());
            log.severe(
                    "com.AmazonLite.PropertiesModel.writePropertiesFile() write: " + Constants.FILENAME + " failed.");
        }

    }

}
