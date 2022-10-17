package util;

import com.google.common.base.Stopwatch;
import models.FileTree;
import models.Information;
import views.ProgressBarView;
import views.StopwatchView;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility file tree builder class.
 */
public class FileTreeBuilder {

    private static final String HOME_PATH = System.getProperty("user.home");

    public static FileTree build(String destination) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Path pathDest = Path.of(destination);
        File fileDest = pathDest.toFile();
        if (fileDest.exists()) {
            File[] files = fileDest.listFiles();
            if (files != null) {
                List<Information> infoList = createInfoList(files);
                stopwatch.stop();
                StopwatchView.elapsed(stopwatch);
                return new FileTree(infoList, pathDest, fileDest.getName());
            }
            stopwatch.stop();
            StopwatchView.elapsed(stopwatch);
            return new FileTree(fileDest.getName(), pathDest);
        }
        stopwatch.stop();
        StopwatchView.elapsed(stopwatch);
        return build(HOME_PATH);
    }

    /**
     * Method for creating a list of {@link Information information}.
     *
     * @param files File array.
     * @return List of {@link Information information}.
     */
    public static LinkedList<Information> createInfoList(File[] files) {
        int total = files.length * 10;
        LinkedList<Information> infoList = new LinkedList<>();
        int progressCounter = 10;
        for (int i = 0; i < files.length; i++) {
            Information info = new Information(files[i], i + 1);
            infoList.add(info);
            ProgressBarView.displayProgress(progressCounter, total);
            progressCounter += 10;
        }
        return infoList;
    }
}