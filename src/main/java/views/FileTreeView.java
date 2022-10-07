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
        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1);
            sb.append(" | ");
            sb.append(new InformationDto(list.get(i)));
        }
        return "=== File tree " + tree.getHeadName() + " === \n" +
                sb + "\n" +
                "=== File tree " + tree.getHeadName() + " ===";
    }

    /**
     * Constructor that creates view
     * and display in console.
     *
     * @param tree FileTree.
     */
    public FileTreeView(FileTree tree) {
        this.tree = tree;
        System.out.println(this);
    }
}