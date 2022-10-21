package ru.develonica.model;

import ru.develonica.util.FileOperationsUtil;

import java.io.File;
import java.nio.file.Path;

/**
 * Base representation of object (file, dir).
 */
public class Information {

    /**
     * Object name.
     */
    private final String objName;

    /**
     * Object type (e.g., dir or file).
     */
    private final Type type;

    /**
     * Size of object.
     */
    private final double size;

    /**
     * Count of objects.
     */
    private final int count;

    /**
     * Object path.
     */
    private final Path path;

    /**
     * Sequence number in list.
     */
    private final int number;

    /**
     * Creates {@link Information information instance} from file.
     *
     * @param file File.
     * @param number Sequence number (in tree output).
     */
    public Information(File file, int number) {
        this.objName = file.getName();
        this.type = Type.getType(file);
        this.count = FileOperationsUtil.calculateCount(file);
        this.size = FileOperationsUtil.calculateSize(file);
        this.path = file.toPath();
        this.number = number;
    }

    public String getObjName() {
        return objName;
    }

    public Type getType() {
        return type;
    }

    public double getSize() {
        return size;
    }

    public int getCount() {
        return count;
    }

    public Path getPath() {
        return path;
    }

    public int getNumber() {
        return number;
    }
}