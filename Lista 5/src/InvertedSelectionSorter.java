import java.util.ArrayList;
import java.util.List;

public class InvertedSelectionSorter implements ISorter {
    private final IChecker checker;

    public InvertedSelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        int n = values.length;

        for (int i = n - 1; i >= 0; i--) {
            int min_idx = i;
            for (int j = i - 1; j >= 0; j--)
                if (values[j] > values[min_idx])
                    min_idx = j;
            int temp = values[min_idx];
            values[min_idx] = values[i];
            values[i] = temp;
            checker.check(values);
            print(values);
        }
    }

    public void print(int[] values){
        for(int i = 0; i < values.length; i++){
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }
}
