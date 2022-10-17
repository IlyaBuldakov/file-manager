package models;

import views.ErrorView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Type of object (e.g., picture, video or dir).
 */
public enum Type {

    IMAGE,

    VIDEO,

    TEXT,

    DIR;

    /**
     * Method defining the type of object.
     *
     * @param file Input file.
     * @return {@link Type Type}.
     */
    public static Type getType(File file) {
        try {
            String type = Files.probeContentType(file.toPath());
            return type == null ? DIR : Type.parseType(type);
        } catch (IOException e) {
            ErrorView.displayError(Message.IO_ERROR);
        }
        return DIR;
    }

    /**
     * Auxiliary parse method.
     *
     * @param type String type (from {@link Files#probeContentType(Path)}.
     * @return {@link Type type}.
     */
    private static Type parseType(String type) {
        if (type.contains("image")) {
            return Type.IMAGE;
        } else if (type.contains("video")) {
            return Type.VIDEO;
        } else if (type.contains("text")) {
            return Type.TEXT;
        } else {
            return Type.DIR;
        }
    }
}
