package ru.develonica.views;

public class ProgressBarView {

    /**
     * Progress bar for building a file tree.
     *
     * @param done  Done part.
     * @param total Total (aim).
     */
    public static void displayProgress(int done, int total) {
        int size = 10;
        String iconDone = "█";
        String iconRemain = "░";

        if (done > total) {
            throw new IllegalArgumentException();
        }
        int donePercents = (100 * done) / total;
        int doneLength = size * donePercents / 100;

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i < doneLength) {
                bar.append(iconDone);
            } else {
                bar.append(iconRemain);
            }
        }
        System.out.print("\r" + bar + " " + donePercents + "%" + " [PROCESSING] ");
        if (done == total) {
            System.out.print("\r" + bar + " " + donePercents + "%");
            System.out.print("\n");
        }
    }
}