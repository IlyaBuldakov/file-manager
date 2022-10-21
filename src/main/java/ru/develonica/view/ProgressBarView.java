package ru.develonica.view;

public final class ProgressBarView {

    /**
     * Progress bar for building a file tree.
     *
     * @param done  Done part.
     * @param total Total (aim).
     */
    public void proceed(int done, int total) {
        int size = 10;
        String iconDone = "█";
        String iconRemain = "░";

        if (done > total) {
            throw new IllegalArgumentException();
        }
        int finish = 100;
        int donePercents = (finish * done) / total;
        int doneLength = size * donePercents / finish;

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i < doneLength) {
                bar.append(iconDone);
            } else {
                bar.append(iconRemain);
            }
        }
        System.out.printf("\r %s %d%% [PROCESSING]", bar, donePercents);
        if (done == total) {
            System.out.printf("\r %s %d%%", bar, donePercents);
            System.out.print("\n");
        }
    }
}