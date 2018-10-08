/**
 * AbstractModel.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite;

public abstract class AbstractModel extends Component implements ModelInterface {

    public void create() {
        log.info("com.AmazonLite.AbstractModel.create()");
    };

    public void read() {
        log.info("com.AmazonLite.AbstractModel.read()");
    };

    public void read(Integer i) {
        log.info("com.AmazonLite.AbstractModel.read(Integer)");
    };

    public void read(String s) {
        log.info("com.AmazonLite.AbstractModel.read(String)");
    };

    public void update() {
        log.info("com.AmazonLite.AbstractModel.update()");
    };

    public void update(Integer i) {
        log.info("com.AmazonLite.AbstractModel.update(Integer)");
    };

    public void update(String s) {
        log.info("com.AmazonLite.AbstractModel.update(String)");
    };

    public void update(Integer i, String s) {
        log.info("com.AmazonLite.AbstractModel.update(Integer, String)");
    };

    public void delete() {
        log.info("com.AmazonLite.AbstractModel.delete()");
    };

    public void delete(Integer i) {
        log.info("com.AmazonLite.AbstractModel.delete(Integer)");
    };

    public void delete(String s) {
        log.info("com.AmazonLite.AbstractModel.delete(String)");
    };

}
