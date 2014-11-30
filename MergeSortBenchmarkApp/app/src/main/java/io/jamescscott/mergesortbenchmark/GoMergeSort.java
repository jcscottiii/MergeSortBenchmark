package io.jamescscott.mergesortbenchmark;

import go.mergesort.Mergesort;

/**
 * Created by jamescscott on 11/23/14.
 */
public class GoMergeSort implements IMergeSortable {
    @Override
    public String getSortLanguage() {
        return "Go";
    }
    @Override
    public long MergeSortEntry(int size, int ascending, int[] randomArray) {
        return Mergesort.GoMergeSortEntry(size, ascending);
    }
}
