package main.java.models;

import main.java.services.Calculator;
import main.java.views.InformationView;

import java.io.File;
import java.nio.file.Path;

/**
 * Base representation of object (file, dir).
 */
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

    Path path;

    public Information(File file, int number) {
        this.objName = file.getName();
        this.type = Type.getType(file);
        this.count = Calculator.calculateCount(file);
        this.path = file.toPath();
        this.totalSize = size;
        InformationView.print(this, file, number);
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

    public Path getPath() {
        return path;
    }
}