/**
 * SqliteModel.java
 */

/**
 * @author Bryan Glogowski
 * @version 1.0
 */

package com.AmazonLite;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import com.AmazonLite.items.*;

public class SqliteModel extends AbstractTableModel {

    // Fields

    private static final long serialVersionUID = 1L;
    private static final String URL = "jdbc:sqlite:test.db";
    private LinkedList<Item> items = new LinkedList<>();

    // Constructors

    /**
     * Create an SqliteModel Object.
     * 
     */
    public SqliteModel() {

        String itemSchema = "guid INTEGER PRIMARY KEY, description TEXT NOT NULL, quantity INTEGER NOT NULL";
        String bookSchema = "guid INTEGER PRIMARY KEY, pages INTEGER";
        String discSchema = "guid INTEGER PRIMARY KEY, disctype TEXT";

        createNewDatabase();
        createNewTable("items", itemSchema);
        createNewTable("books", bookSchema);
        createNewTable("discs", discSchema);
        readPersistedData();

    }

    // Methods

    /**
     * Create a database connection
     * 
     * @return The database connection Object
     */
    private static Connection connect() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Create a new database in the filesystem
     * 
     */
    private static void createNewDatabase() {

        Connection conn = connect();
        if (conn != null) {
            try {

                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Loaded Database driver: " + meta.getDriverName());

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Create a new table in the database
     * 
     * @param tableName
     *            The name of the table to create
     * @param schema
     *            The columns and data types of those columns
     */
    private static void createNewTable(String tableName, String schema) {

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + schema + ");";

        Connection conn = connect();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Insert an item in the items table
     *
     * @param item
     *            The Item Object whose data representation will be inserted
     */
    private <I extends Item> void insertItem(I item) {

        String sql = "INSERT INTO items(guid, description, quantity) VALUES(?, ?, ?)";
        PreparedStatement ps = null;

        Connection conn = connect();
        if (conn != null) {
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, item.getGuid());
                ps.setString(2, item.getDescription());
                ps.setInt(3, item.getQuantity());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Insert a Book item in the books table
     *
     * @param book
     *            The Book Item Object whose data representation will be inserted
     */
    private void insertBook(Book book) {

        String sql = "INSERT INTO books(guid, pages) VALUES(?, ?)";
        PreparedStatement ps = null;

        Connection conn = connect();
        if (conn != null) {
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, book.getGuid());
                ps.setInt(2, book.getNumberOfPages());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Insert a Disc item in the discs table
     *
     * @param disc
     *            The Disc Item Object whose data representation will be inserted
     */
    private <D extends Disc> void insertDisc(D disc) {

        String sql = "INSERT INTO discs(guid, disctype) VALUES(?, ?)";
        PreparedStatement ps = null;

        Connection conn = connect();
        if (conn != null) {
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, disc.getGuid());
                ps.setString(2, disc.getDiscType().name());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Create a Book Object and add it to the inventory
     *
     * @param description
     *            A description of the item
     * @param quantity
     *            The quantity of items in the inventory
     * @return an array Object with result data
     */
    protected Object[][] createBook(String description, Integer quantity) {

        // Create the item
        Book book = new Book();
        book.setDescription(description);
        book.setQuantity(quantity);

        // Add the item to the inventories
        items.add(book);
        insertItem(book);
        insertBook(book);

        return new Object[][] { { book.getGuid(), book.getDescription(), book.getQuantity() } };
    }

    /**
     * Create a Book Object and add it to the inventory
     *
     * @param description
     *            A description of the item
     * @param quantity
     *            The quantity of items in the inventory
     * @param pages
     *            The number of pages in the book
     * @return an array Object with result data
     */
    protected Object[][] createBook(String description, Integer quantity, Integer pages) {

        // Create the item
        Book book = new Book();
        book.setDescription(description);
        book.setQuantity(quantity);
        book.setNumberOfPages(pages);

        // Add the item to the inventories
        items.add(book);
        insertItem(book);
        insertBook(book);

        return new Object[][] { { book.getGuid(), book.getDescription(), book.getQuantity() } };

    }

    /**
     * Create a CompactDisc Object and add it to the inventory
     *
     * @param description
     *            A description of the item
     * @param quantity
     *            The quantity of items in the inventory
     * @return an array Object with result data
     */
    protected Object[][] createCompactDisc(String description, Integer quantity) {

        // Create the item
        CompactDisc cd = new CompactDisc();
        cd.setDescription(description);
        cd.setQuantity(quantity);

        // Add the item to the inventories
        insertItem(cd);
        insertDisc(cd);
        items.add(cd);

        return new Object[][] { { cd.getGuid(), cd.getDescription(), cd.getQuantity() } };
    }

    /**
     * Create a DigitalVideoDisc Object and add it to the inventory
     *
     * @param description
     *            A description of the item
     * @param quantity
     *            The quantity of items in the inventory
     * @return an array Object with result data
     */
    protected Object[][] createDigitalVideoDisc(String description, Integer quantity) {

        // Create the item
        DigitalVideoDisc dvd = new DigitalVideoDisc();
        dvd.setDescription(description);
        dvd.setQuantity(quantity);

        // Add the item to the inventories
        insertItem(dvd);
        insertDisc(dvd);
        items.add(dvd);

        return new Object[][] { { dvd.getGuid(), dvd.getDescription(), dvd.getQuantity() } };

    }

    /**
     * Returns only Items which are Books
     *
     * @return an array Object with result data
     */
    private Object[][] readBooks() {

        String sql = "SELECT items.guid AS guid, description, quantity FROM items,books WHERE items.guid = books.guid";
        Integer counter = 0;

        int columnCount = getColumnCount();
        int rowCount = getRowCount();
        Object[][] obj = new Object[rowCount][columnCount];

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    obj[counter] = new Object[] { rs.getInt("guid"), rs.getString("description"),
                            rs.getInt("quantity") };
                    counter++;
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;

    }

    /**
     * Returns only Items which are CDs
     *
     * @return an array Object with result data
     */
    private Object[][] readCompactDiscs() {

        String sql = "SELECT items.guid AS guid, description, quantity FROM items,discs WHERE items.guid = discs.guid AND disctype = 'AUDIO'";
        Integer counter = 0;

        int columnCount = getColumnCount();
        int rowCount = getRowCount();
        Object[][] obj = new Object[rowCount][columnCount];

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    obj[counter] = new Object[] { rs.getInt("guid"), rs.getString("description"),
                            rs.getInt("quantity") };
                    counter++;
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;

    }

    /**
     * Returns only Items which are DVDs
     *
     * @return an array Object with result data
     */
    private Object[][] readDigitalVideoDiscs() {

        String sql = "SELECT items.guid AS guid, description, quantity FROM items,discs WHERE items.guid = discs.guid AND disctype = 'VIDEO'";
        Integer counter = 0;

        int columnCount = getColumnCount();
        int rowCount = getRowCount();
        Object[][] obj = new Object[rowCount][columnCount];

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    obj[counter] = new Object[] { rs.getInt("guid"), rs.getString("description"),
                            rs.getInt("quantity") };
                    counter++;
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;

    }

    /**
     * Returns only Items which have the same GUID (This should always only return
     * one row of data)
     * 
     * @param i
     *            The guid of the Object
     * 
     * @return an array Object with result data
     */
    protected Object[][] readItems(Integer i) {

        String sql = "SELECT * FROM items WHERE guid = " + i;
        Integer counter = 0;

        int columnCount = getColumnCount();
        int rowCount = getRowCount();
        Object[][] obj = new Object[rowCount][columnCount];

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    obj[counter] = new Object[] { rs.getInt("guid"), rs.getString("description"),
                            rs.getInt("quantity") };
                    counter++;
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    /**
     * Returns only Items which match a defined category
     * 
     * @param s
     *            A String representation of an Item category
     * @return an array Object with result data
     */
    protected Object[][] readItems(String s) {

        Object[][] obj = null;

        switch (s) {
        case "books":
            obj = readBooks();
            break;
        case "cds":
            obj = readCompactDiscs();
            break;
        case "dvds":
            obj = readDigitalVideoDiscs();
            break;
        default:
            break;
        }
        return obj;
    }

    /**
     * Returns all Items in the inventory
     * 
     * @return an array Object with result data
     */
    protected Object[][] readItems() {

        String sql = "SELECT * FROM items";
        Integer counter = 0;

        int columnCount = getColumnCount();
        int rowCount = getRowCount();
        Object[][] obj = new Object[rowCount][columnCount];

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    obj[counter] = new Object[] { rs.getInt("guid"), rs.getString("description"),
                            rs.getInt("quantity") };
                    counter++;
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return obj;
    }

    /**
     * Updates Items in the inventory
     * 
     * @param g
     *            An Integer representation of the GUID of the Item
     * @param d
     *            A String representation of the description of an Item
     * @param q
     *            An Integer representation of the quantity of Items
     * 
     * @return an array Object with result data
     */
    protected Object[][] updateItems(Integer g, String d, Integer q) {

        String sql = "UPDATE items SET description = '" + d + "', quantity = " + q + " WHERE guid = " + g;

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return readItems(g);
    }

    /**
     * Deletes Items from the inventory
     * 
     * @param i
     *            An Integer representation of the GUID of the Item
     * 
     * @return an array Object with result data
     */
    protected Object[][] deleteItems(Integer i) {

        String sql;
        Connection conn;

        Object[][] obj = readItems(i);

        // Delete the row from the items table
        sql = "DELETE FROM items WHERE guid = " + i;

        conn = connect();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Delete the row from the books table
        sql = "DELETE FROM books WHERE guid = " + i;

        conn = connect();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Delete the row from the discs table
        sql = "DELETE FROM discs WHERE guid = " + i;

        conn = connect();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return obj;
    }

    /**
     * Read the data stored in the database and add the items to the inventory
     * 
     */
    private void readPersistedBooks() {

        String sql = "SELECT * FROM items,books WHERE items.guid = books.guid";

        Book book;

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    book = new Book(rs.getInt("guid"), rs.getString("description"), rs.getInt("quantity"));
                    book.setNumberOfPages(rs.getInt("pages"));
                    items.add(book);
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Read the data stored in the database and add the items to the inventory
     * 
     */
    private void readPersistedCompactDiscs() {

        String sql = "SELECT * FROM items,discs WHERE items.guid = discs.guid AND disctype = 'AUDIO'";

        CompactDisc cd;

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    cd = new CompactDisc(rs.getInt("guid"), rs.getString("description"), rs.getInt("quantity"));
                    items.add(cd);
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Read the data stored in the database and add the items to the inventory
     * 
     */
    private void readPersistedDigitalVideoDiscs() {

        String sql = "SELECT * FROM items,discs WHERE items.guid = discs.guid AND disctype = 'VIDEO'";

        DigitalVideoDisc dvd;

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    dvd = new DigitalVideoDisc(rs.getInt("guid"), rs.getString("description"), rs.getInt("quantity"));
                    items.add(dvd);
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Read the data stored in the database and add the items to the inventory
     * 
     */
    private void readPersistedData() {

        readPersistedBooks();
        readPersistedCompactDiscs();
        readPersistedDigitalVideoDiscs();
    }

    @Override
    public int getRowCount() {

        Integer rowCount = 0;
        String sql = "SELECT COUNT(*) AS rowcount FROM items";

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                rowCount = rs.getInt("rowcount");

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rowCount;
    }

    @Override
    public int getColumnCount() {

        String sql = "SELECT * FROM items";
        Integer numberOfColumns = 0;

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();

                numberOfColumns = rsmd.getColumnCount();

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return numberOfColumns;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        String sql = "SELECT * FROM items";

        Connection conn = connect();
        if (conn != null) {
            try {

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs.absolute(rowIndex + 1);
                return rs.getObject(columnIndex + 1);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
