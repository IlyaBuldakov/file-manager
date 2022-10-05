package main.java.models;

import main.java.services.SizeHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
     * Size of object.
     */
    float size;

    /**
     * Count of objects.
     */
    int count;

    /**
     * Total size of objects in dir.
     */
    float totalSize;

    public Information(File file) {
        this.objName = file.getName();
        this.type = Type.getType();
        this.size = calculateSize(file);
        this.count = calculateCount(file);
    }

    private float calculateSize(File file) {
        try {
            if (file.isDirectory()) {
                return SizeHandler.activate(file);
            }
            return Files.size(file.toPath());
        } catch (IOException exception) {
            System.err.println("Ошибка файла.");
        }
        return 0;
    }

    private int calculateCount(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                return files.length;
            } else {
                return 0;
            }
        }
        return 1;
    }

    public String getObjName() {
        return objName;
    }

    public Type getType() {
        return type;
    }

    public float getSize() {
        return size;
    }

    public int getCount() {
        return count;
    }

    public float getTotalSize() {
        return totalSize;
    }
}