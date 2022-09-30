package main.java.models;

public class Information {

    /**
     * Object name.
     */
    String objName;

    /**
     * Object type (e.g., dir or file).
     */
    Type type;

    /**
     * Object size.
     */
    int size;

    /** Read/write attributes.
     *
     */
    String[] attributes;

    /**
     * Count of objects.
     */
    int count;

    /**
     * Total size of objects in dir.
     */
    long totalSize;
}
