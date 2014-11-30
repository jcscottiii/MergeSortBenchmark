package io.jamescscott.mergesortbenchmark;

import java.util.Random;

/**
 * Created by jamescscott on 11/23/14.
 */
public interface IMergeSortable {
    public long MergeSortEntry(int size, int ascending, Random random);
    public String getSortLanguage();
}
