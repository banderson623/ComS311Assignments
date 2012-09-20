public class Quicksort  {

    private IArrayLookup.Pair[] pairs;
    private int                 pairCount;

    public IArrayLookup.Pair[] sort(IArrayLookup.Pair[] values) {
        // Check for empty or null array
        if (values == null || values.length == 0){

        } else {
            pairs = values;
            pairCount = values.length;
            quicksort(0, pairCount - 1);
        }
        return pairs;
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        Object pivotKey = pairs[low + (high-low)/2].key;

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (pairs[i].key.compareTo(pivotKey) < 0) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (pairs[j].key.compareTo(pivotKey) > 0) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void exchange(int i, int j) {
        IArrayLookup.Pair temp = pairs[i];
        pairs[i] = pairs[j];
        pairs[j] = temp;
    }
} 