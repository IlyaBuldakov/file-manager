package main.java.views;

import main.java.models.Information;
import main.java.models.Type;
import main.java.services.SizeConverter;

public class InformationDto {

    String objName;

    /**
     * Object type (e.g., dir or file).
     */
    Type type;

    /**
     * Formatted size representation (with measure).
     */
    String size;

    /**
     * Count of objects.
     */
    int count;

    /**
     * Total size of objects in dir.
     */
    String totalSize;

    public InformationDto(Information info) {
        this.objName = info.getObjName();
        this.type = info.getType();
        this.size = SizeConverter.convert(info.getSize());
        this.count = info.getCount();
        this.totalSize = SizeConverter.convert(info.getTotalSize());
    }

    @Override
    public String toString() {
        return "Name: " + objName + " | Type: " + type + " | Size: "
                + size + " | Count: " + count + " | Total size: " + totalSize + "\n";
    }
}