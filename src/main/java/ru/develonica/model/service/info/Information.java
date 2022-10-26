package ru.develonica.model.service.info;

import ru.develonica.model.type.Type;

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
    private Type type;

    /**
     * Size of object.
     */
    private long size;

    /**
     * Count of objects.
     */
    private int count;

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
     * @param file   File.
     * @param number Sequence number (in tree output).
     */
    Information(File file, int number) {
        this.objName = file.getName();
        this.type = Type.OTHER;
        this.path = file.toPath();
        this.number = number;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setSizeAndCount(long size, int count) {
        this.size = size;
        this.count = count;
    }

    public String getObjName() {
        return objName;
    }

    public Type getType() {
        return type;
    }

    public long getSize() {
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