package gomergesort

import (
	"math/rand"
	"time"
)
// Have to use ascending as an int for now because bool is currently not supported.
func GoMergeSortEntry(size, ascending int) int {
	if size < 1 {
		return 0;
	}

	nums := make([]int, size, size)
	for idx := 0; idx < len(nums); idx++ {
		nums[idx] = rand.Int()
	}

	t0 := time.Now()
	goMergeSortFn(nums, (ascending !=0))
	t1 := time.Now()
	return int(t1.Sub(t0).Nanoseconds() / 1000000)
}

func goMergeSortFn(array []int, ascending bool) []int {
	if len(array) <= 1 {
		return array
	}
	middleIndex := len(array) / 2
	// Create the left and right slices.
	left, right := array[:middleIndex], array[middleIndex:]
	// Sort the individual left and right slices.
	left, right = goMergeSortFn(left, ascending), goMergeSortFn(right, ascending)
	// Merge the two slices.
	result := merge(left, right, ascending)

	return result
}

func merge(left, right []int, ascending bool) []int {
	leftMaxIndex, rightMaxIndex := len(left)-1, len(right)-1
	leftIndex, rightIndex := 0, 0
	totalLength := leftMaxIndex + 1 + rightMaxIndex + 1
	mergedArray := make([]int, totalLength, totalLength)
	for i := 0; i < totalLength; i++ {
		if leftIndex <= leftMaxIndex && rightIndex > rightMaxIndex {
			// Only elements in left array
			mergedArray[i] = left[leftIndex]
			leftIndex++
		} else if rightIndex <= rightMaxIndex && leftIndex > rightMaxIndex {
			// Only elements in right array
			mergedArray[i] = right[rightIndex]
			rightIndex++
		} else if left[leftIndex] < right[rightIndex] {
			if ascending {
				mergedArray[i] = left[leftIndex]
				leftIndex++
			} else {
				mergedArray[i] = right[rightIndex]
				rightIndex++
			}
		} else {
			if ascending {
				mergedArray[i] = right[rightIndex]
				rightIndex++
			} else {
				mergedArray[i] = left[leftIndex]
				leftIndex++
			}
		}
	}
	return mergedArray
}

/*
func main() {
	nums := []int{2, 3, 5, 1}
	fmt.Println(nums)
	fmt.Println(GoMergeSortFn(nums, false))
}
*/
