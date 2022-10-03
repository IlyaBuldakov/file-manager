package main.java.models;

import java.nio.file.attribute.BasicFileAttributes;

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
    long size;

    /** Read/write attributes.
     *
     */
    BasicFileAttributes attributes;

    /**
     * Count of objects.
     */
    int count;

    /**
     * Total size of objects in dir.
     */
    long totalSize;

    public Information(String objName, Type type, long size, BasicFileAttributes attributes, int count, long totalSize) {
        this.objName = objName;
        this.type = type;
        this.size = size;
        this.attributes = attributes;
        this.count = count;
        this.totalSize = totalSize;
    }
}
