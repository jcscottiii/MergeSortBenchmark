package io.jamescscott.mergesortbenchmark;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by jamescscott on 11/23/14.
 */
public class MergeSortBenchmarkController {
    // Array Logistics.
    // Default for whether we are sorting in ascending order or not.
    private static final boolean DEFAULT_ASCENDING_MODE = true;
    // Initial size for array.
    private static final int INITIAL_ARRAY_SIZE = 1000;
    // How much to increase the array size each iteration.
    private static final int ARRAY_SIZE_STEP = 1000;
    // The max array size to test.
    private static final int MAX_ARRAY_SIZE = 50000;
    // How many samples to take per size.
    private static final int SAMPLES = 1;

    // Should we validate the sorted array or not?
    private static boolean VALIDATE_SORTED_ARRAY = true;

    // List of types of Merge Sort Benchmarks to try.
    private static IMergeSortable[] MERGE_SORT_BENCHMARKS = {
            new JavaMergeSort(),
            // new CMergeSort(),
            // new GoMergeSort()
    };

    private static final String TAG = "MergeSortBenchmarkController";
    // The public method to run the benchmark.
    public static boolean conductMergeSortBenchmark(Context context) {
        HashMap<String, ArrayList<MergeSortBenchmarkData>> benchmarkDataMap
                = new HashMap<String, ArrayList<MergeSortBenchmarkData>>();

        Random random = new Random();
        for (int size = INITIAL_ARRAY_SIZE; size <= MAX_ARRAY_SIZE; size += ARRAY_SIZE_STEP) {
            // Create a random array.
            int[] randomArray = createRandomArray(size, random);

            // Iterate over the types of benchmarks.
            for (int j = 0; j < MERGE_SORT_BENCHMARKS.length; j++) {
                long totalDuration = 0;
                // Time the Merge Sort.
                for (int sample = 1; sample < SAMPLES; sample++) {
                    long beginTime = System.currentTimeMillis();
                    int[] result
                            = MERGE_SORT_BENCHMARKS[j].MergeSort(randomArray, DEFAULT_ASCENDING_MODE);
                    totalDuration += System.currentTimeMillis() - beginTime;

                    // In case you want to validate the array is sorted properly.
                    if (VALIDATE_SORTED_ARRAY) {
                        // Validate the sorted array.
                        boolean valid = validateSortedArray(result, DEFAULT_ASCENDING_MODE);
                        if (!valid) {
                            Log.w(TAG, "Invalid Sorted Array");
                            return valid;
                        }
                    }
                }
                long averageDuration = totalDuration / SAMPLES;
                String benchmarkLanguage = MERGE_SORT_BENCHMARKS[j].getSortLanguage();
                ArrayList<MergeSortBenchmarkData> benchmarkDataList
                        = benchmarkDataMap.get(benchmarkLanguage);
                if (benchmarkDataList == null) {
                    benchmarkDataList = new ArrayList<MergeSortBenchmarkData>();
                }
                benchmarkDataList.add(new MergeSortBenchmarkData(size, averageDuration));
                benchmarkDataMap.put(benchmarkLanguage, benchmarkDataList);
                Log.i(TAG, "Language: " + benchmarkLanguage
                        + "Array size: " + size + " Average Time: " + averageDuration);

            }
        }
        // Everything went smoothly, let's return home.
        return writeResultsToFiles(context, benchmarkDataMap);
    }

    private static boolean writeResultsToFiles(Context context,
                                            HashMap<String, ArrayList<MergeSortBenchmarkData>> data) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy-HH-mm-ss");
        String date_time = dateFormat.format(cal.getTime());
        for (String key: data.keySet()) {
            String filename = date_time + "-" + key + ".txt";
            FileOutputStream outputStream;
            try {
                outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                ArrayList<MergeSortBenchmarkData> benchmarkDataList = data.get(key);
                for (MergeSortBenchmarkData benchmarkData : benchmarkDataList) {
                    outputStream.write(benchmarkData.toString().getBytes());
                }
                outputStream.close();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                return false;
            }
        }
        return true;
    }

    // Helper function to create a random array of specified length.
    private static int[] createRandomArray(int elements, Random random) {
        int array[] = new int[elements];
        for (int i = 0; i < elements; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    // Helper function to validate the array is indeed sorted correctly.
    private static boolean validateSortedArray(int[] sortedArray, boolean ascending) {
        for (int i = 0; i < sortedArray.length-1; i++) {
            if (ascending) {
                if (sortedArray[i + 1] < sortedArray[i]) {
                    return false;
                }
            } else {
                if (sortedArray[i + 1] > sortedArray[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
