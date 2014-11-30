package io.jamescscott.mergesortbenchmark;

/**
 * Created by jamescscott on 11/23/14.
 */
public class GoMergeSort implements IMergeSortable {
    @Override
    public String getSortLanguage() {
        return "Go";
    }

    private static native int[] GoMergeSortFn(int[] array, boolean ascending);
    @Override
    public int[] MergeSort(int[] array, boolean ascending) {
        return GoMergeSortFn(array, ascending);
    }
}
