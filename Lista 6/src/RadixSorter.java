public class RadixSorter implements ISorter {
    private final IChecker checker;

    public RadixSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        int max = maxValue(values);

        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(values, place);
            checker.check(values);
        }
    }

    public void countingSort(int[] arr, int place){
        int size = arr.length;
        int[] output = new int[size + 1];
        int max = arr[0];
        for (int i = 1; i < size; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        int[] count = new int[max + 1];

        for (int i = 0; i < max; ++i)
            count[i] = 0;

        for (int i = 0; i < size; i++)
            count[(arr[i] / place) % 10]++;

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = size - 1; i >= 0; i--) {
            output[count[(arr[i] / place) % 10] - 1] = arr[i];
            count[(arr[i] / place) % 10]--;
        }

        for (int i = 0; i < size; i++)
            arr[i] = output[i];
    }

    public int maxValue(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }
}
