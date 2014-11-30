package io.jamescscott.mergesortbenchmark;

/**
 * Created by jamescscott on 11/23/14.
 */
public class MergeSortBenchmarkData {
    private int arraySize;
    private long duration;
    public MergeSortBenchmarkData(int size, long duration) {
        this.arraySize = size;
        this.duration = duration;
    }

    // Helper function. In case we want to change the delimiter.
    public static String getDataDelimiter() {
        return ",";
    }

    // Helper function. In case we want to print to a file.
    public static String getDataHeader() {
        return "array_size" + getDataDelimiter() + "duration";
    }

    @Override
    public String toString() {
        return arraySize + getDataDelimiter() + duration + "\n";
    }
}
