package ru.develonica.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TypeResolver {

    /**
     * Method defining the type of object.
     *
     * @param file Input file.
     * @return {@link Type Type}.
     */
    public Type getType(File file) throws IOException {
        String type = Files.probeContentType(file.toPath());
        return type == null ? Type.DIR : parseType(type);
    }

    /**
     * Auxiliary parse method.
     *
     * @param type String type (from {@link Files#probeContentType(Path)}.
     * @return {@link Type type}.
     */
    private Type parseType(String type) {
        if (type.contains("image")) {
            return Type.IMAGE;
        } else if (type.contains("video")) {
            return Type.VIDEO;
        } else if (type.contains("text")) {
            return Type.TEXT;
        } else {
            return Type.OTHER;
        }
    }
}
