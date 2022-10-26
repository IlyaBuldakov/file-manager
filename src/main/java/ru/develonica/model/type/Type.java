package ru.develonica.model.type;

/**
 * Type of object (e.g., picture, video or dir).
 */
public enum Type {

    /**
     * Image type (like PNG, JPG, etc.).
     */
    IMAGE,

    /**
     * Video type (like MP4, AVI, etc.).
     */
    VIDEO,

    /**
     * Text type (like XML, TXT, etc.).
     */
    TEXT,

    /**
     * Other types of file.
     */
    OTHER,

    /**
     * Directory.
     */
    DIR
}