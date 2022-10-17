package views;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class StopwatchView {

    private static final String MS_MEASURE = " ms.";

    public static void elapsed(Stopwatch stopwatch) {
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS) + MS_MEASURE);
    }
}
