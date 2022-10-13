package util;

import com.google.common.base.Stopwatch;
import models.FileTree;
import models.Information;
import views.v1.ProgressBarView;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Utility file tree builder class.
 */
public class FileTreeBuilder {

    public static FileTree build(String destination) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Path pathDest = Path.of(destination);
        File fileDest = pathDest.toFile();
        if (fileDest.exists()) {
            File[] files = fileDest.listFiles();
            if (files != null) {
                List<Information> infoList = createInfoList(files);
                stopwatch.stop();
                System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms.");
                return new FileTree(infoList, pathDest, fileDest.getName());
            }
            stopwatch.stop();
            System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms.");
            return new FileTree(fileDest.getName(), pathDest);
        }
        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms.");
        return build(System.getProperty("user.home"));
    }

    /**
     * Method for creating a list of {@link Information information}.
     *
     * @param files File array.
     * @return List of {@link Information information}.
     */
    public static LinkedList<Information> createInfoList(File[] files) {
        LinkedList<Information> infoList = new LinkedList<>();
        int progressCounter = 10;
        for (int i = 0; i < files.length; i++) {
            Information info = new Information(files[i], i + 1);
            infoList.add(info);
            ProgressBarView.displayProgress(progressCounter, files.length * 10);
            progressCounter += 10;
        }
        return infoList;
    }
}