package io.jamescscott.mergesortbenchmark;

import java.util.Arrays;

/**
 * Created by jamescscott on 11/22/14.
 */
public class JavaMergeSort implements IMergeSortable{
    public JavaMergeSort() {}
    private static int[] JavaMerge(int[] left, int[] right, boolean ascending) {
        int leftMaxIndex = left.length - 1;
        int rightMaxIndex = right.length - 1;
        int leftIndex = 0, rightIndex = 0;
        int totalLength = leftMaxIndex + 1 + rightMaxIndex + 1;
        int[] mergedArray = new int[totalLength];
        for (int i = 0; i < totalLength; i++) {
            if (leftIndex <= leftMaxIndex && rightIndex > rightMaxIndex) {
                // Only elements in the left array.
                mergedArray[i] = left[leftIndex];
                leftIndex++;
            } else if (rightIndex <= rightMaxIndex && leftIndex > leftMaxIndex) {
                // Only elements in the right array.
                mergedArray[i] = right[rightIndex];
                rightIndex++;
            } else if (left[leftIndex] < right[rightIndex]) {
                if (ascending) {
                    mergedArray[i] = left[leftIndex];
                    leftIndex++;
                } else {
                    mergedArray[i] = right[rightIndex];
                    rightIndex++;
                }
            } else {
                if (ascending) {
                    mergedArray[i] = right[rightIndex];
                    rightIndex++;
                } else {
                    mergedArray[i] = left[leftIndex];
                    leftIndex++;
                }
            }
        }
        return left;
    }
    public static int[] JavaMergeSortFn(int[] array, boolean ascending) {
        if (array.length <= 1) {
            return array;
        }

        int middleIndex = array.length / 2;
        // Create the left and right arrays.
        int[] left = Arrays.copyOfRange(array, 0, middleIndex);
        int[] right = Arrays.copyOfRange(array, middleIndex, array.length);
        // Sort the individual left and right arrays.
        left = JavaMergeSortFn(left, ascending);
        right = JavaMergeSortFn(right, ascending);
        // Merge the two arrays.
        int[] result = JavaMerge(left, right, ascending);
        return result;
    }

    @Override
    public int[] MergeSort(int[] array, boolean ascending) {
        return JavaMergeSortFn(array, ascending);
    }

    @Override
    public String getSortLanguage() {
        return "Java";
    }
}
