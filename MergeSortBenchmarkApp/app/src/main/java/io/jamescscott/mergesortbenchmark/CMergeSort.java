package io.jamescscott.mergesortbenchmark;

/**
 * Created by jamescscott on 11/23/14.
 */
import java.util.Random;
public class CMergeSort implements IMergeSortable {
    private static native int[] CMergeSortFn(int[] array, int size, boolean ascending);
    @Override
    public long MergeSortEntry(int size, int ascending, int[] randomArray) {
        //return CMergeSortFn(array, array.length, ascending);
        return 0;
    }

    @Override
    public String getSortLanguage() {
        return "C";
    }
}
