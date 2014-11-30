package io.jamescscott.mergesortbenchmark;

/**
 * Created by jamescscott on 11/23/14.
 */
public class CMergeSort implements IMergeSortable {
    private static native int[] CMergeSortFn(int[] array, int size, boolean ascending);
    @Override
    public int[] MergeSort(int[] array, boolean ascending) {
        return CMergeSortFn(array, array.length, ascending);
    }

    @Override
    public String getSortLanguage() {
        return "C";
    }
}
