import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeSorter {
    public static <T extends Comparable<T>> void sort(List<T> list) throws DuplicateElementException {
        BinarySearchTree<T> tree = new BinarySearchTree<>();
        for(T element : list){
            tree.add(element);
        }
        list.clear();
        tree.sort(list);
    }
}
