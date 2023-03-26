import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IterativeMergeSorter implements ISorter {
    private final IChecker checker;

    public IterativeMergeSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        int n = values.length;
        int curr_size;
        int left_start;

        for (curr_size = 1; curr_size <= n - 1;
             curr_size = 2 * curr_size) {

            for (left_start = 0; left_start < n - 1;
                 left_start += 2 * curr_size) {

                int mid = Math.min(left_start + curr_size - 1, n - 1);

                int right_end = Math.min(left_start + 2 * curr_size - 1, n - 1);

                merge(values, left_start, mid, right_end);
            }
            checker.check(values);
        }
    }

    public static void merge(int arr[], int left, int mid, int right) {
        int i, j, k;
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int L[] = new int[leftSize];
        int R[] = new int[rightSize];

        for (i = 0; i < leftSize; i++)
            L[i] = arr[left + i];

        for (j = 0; j < rightSize; j++)
            R[j] = arr[mid + 1 + j];

        i = 0;
        j = 0;
        k = left;
        while (i < leftSize && j < rightSize) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
