package models;

import util.FileCalculator;
import views.InformationView;

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

    Path path;

    public Information(File file, int number) {
        this.objName = file.getName();
        this.type = Type.getType(file);
        this.count = FileCalculator.calculateCount(file);
        this.size = FileCalculator.calculateSize(file);
        this.path = file.toPath();
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