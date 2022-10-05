package main.java.views;

import main.java.models.FileTree;
import main.java.models.Information;

import java.util.List;

/**
 * View for {@link FileTree FileTree}
 */
public class FileTreeView {

    FileTree tree;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Information> list = tree.getTree();
        for (Information info : list) {
            sb.append(new InformationDto(info));
        }
        return "=== File tree === \n" +
                sb + "\n" +
                "=== File tree ===";
    }

    /**
     * Constructor that creates view
     * and display in console.
     * @param tree FileTree.
     */
    public FileTreeView(FileTree tree) {
        this.tree = tree;
        System.out.println(this);
    }
}
