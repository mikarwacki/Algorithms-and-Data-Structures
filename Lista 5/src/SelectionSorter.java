public class SelectionSorter implements ISorter {
    private final IChecker checker;

    public SelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        int n = values.length;

        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (values[j] < values[min_idx])
                    min_idx = j;
            int temp = values[min_idx];
            values[min_idx] = values[i];
            values[i] = temp;
            checker.check(values);
        }
    }
}
