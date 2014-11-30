package io.jamescscott.mergesortbenchmark;

import java.util.Random;

/**
 * Created by jamescscott on 11/23/14.
 */
public interface IMergeSortable {
    // TODO: use this prototype when they enable passing boolean and arrays between go and java.
    // public int[] MergeSortEntry(int[] randomArray, boolean ascending);
    public long MergeSortEntry(int size, int ascending, int[] randomArray);
    public String getSortLanguage();
}
