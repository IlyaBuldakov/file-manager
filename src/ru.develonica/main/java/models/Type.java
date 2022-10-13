package models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Type of object (e.g., picture, video or dir).
 */
public enum Type {

    IMAGE,

    VIDEO,

    TEXT,
    /**
     * Directory.
     */
    DIR;

    public static Type getType(File file) {
        try {
            String type = Files.probeContentType(file.toPath());
            return type == null ? DIR : Type.chooseType(type);
        } catch (IOException e) {
            System.err.println(Message.IO_ERROR.getText());
        }
        return DIR;
    }

    private static Type chooseType(String type) {
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
