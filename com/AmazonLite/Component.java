/**
 * Component.java
 */

/**
 * @author Bryan Glogowski
 * @version 2.0
 */

package com.AmazonLite;

import java.util.logging.*;

public abstract class Component {

    protected static Logger log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", Constants.LOG_FORMAT);
        log = Logger.getLogger(Component.class.getName());
        log.setLevel(Level.SEVERE);
    }

    public Component() {
        log.info("com.AmazonLite.Component()");
    }

}
