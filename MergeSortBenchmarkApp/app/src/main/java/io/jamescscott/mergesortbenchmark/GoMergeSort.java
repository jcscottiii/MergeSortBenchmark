package io.jamescscott.mergesortbenchmark;

import go.gomergesort.Gomergesort;
import java.util.Random;

/**
 * Created by jamescscott on 11/23/14.
 */
public class GoMergeSort implements IMergeSortable {
    @Override
    public String getSortLanguage() {
        return "Go";
    }

    private static native long GoMergeSortFn(int size, boolean ascending);
    @Override
    public long MergeSortEntry(int size, int ascending, Random random) {
        return Gomergesort.GoMergeSortEntry(size, ascending);
    }
}
