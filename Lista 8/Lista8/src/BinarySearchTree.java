import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    Node root;

    private class Node{
        T value;
        Node left;
        Node right;

        public Node(T value){
            this.value = value;
            this.left = this.right = null;
        }
    }

    public void add(T value) throws DuplicateElementException {
        root = recursiveAdd(root, value);
    }

    public boolean contains(T value) {
        root = recursiveSearch(root, value);
        if(root != null){
            return true;
        } else return false;
    }

    public void delete(T value) {
        recursiveDelete(root, value);
    }

    public String toStringPreOrder() {
        List<T> temp = new ArrayList<>();
        recursivePreOrder(root, temp);
        String s = "";
        for(int i = 0; i < temp.size(); i++){
            if(i == temp.size() - 1){
                s += temp.get(i);
            }else {
                s += temp.get(i) + ", ";
            }
        }
        return s;
    }

    public String toStringInOrder() {
        List<T> temp = new ArrayList<>();
        recursiveInOrder(root, temp);
        String s = "";
        for(int i = 0; i < temp.size(); i++){
            if(i == temp.size() - 1){
                s += temp.get(i);
            }else {
                s += temp.get(i) + ", ";
            }
        }
        return s;
    }

    public String toStringPostOrder() {
        List<T> temp = new ArrayList<>();
        recursivePostOrder(root, temp);
        String s = "";
        for(int i = 0; i < temp.size(); i++){
            if(i == temp.size() - 1){
                s += temp.get(i);
            }else {
                s += temp.get(i) + ", ";
            }
        }
        return s;
    }

    private Node recursiveAdd(Node root, T value) throws DuplicateElementException {
        if(root == null)
            root = new Node(value);
        else {
            if (value.compareTo(root.value) < 0)
                root.left = recursiveAdd(root.left, value);
            else if (value.compareTo(root.value) > 0)
                root.right = recursiveAdd(root.right, value);
            else
                throw new DuplicateElementException();
        }
        return root;
    }

    private Node recursiveSearch(Node root, T value){
        if (root == null || root.value == value)
            return root;
        if (value.compareTo(root.value) < 0)
            return recursiveSearch(root.left, value);
        return recursiveSearch(root.right, value);
    }

    private Node recursiveDelete(Node root, T value){
        if (root == null)
            return root;
        if (value.compareTo(root.value) < 0)
            root.left = recursiveDelete(root.left, value);
        else if (value.compareTo(root.value) > 0)
            root.right = recursiveDelete(root.right, value);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.value = minValue(root.right);

            root.right = recursiveDelete(root.right, root.value);
        }
        return root;
    }

    private T minValue(Node root)  {
        T min = root.value;
        while (root.left != null)  {
            min = root.left.value;
            root = root.left;
        }
        return min;
    }

    private void recursivePreOrder(Node root, List<T> arr){
        if(root == null)
            return;
        arr.add(root.value);
        recursivePreOrder(root.left, arr);
        recursivePreOrder(root.right, arr);
    }

    private void recursiveInOrder(Node root, List<T> list){
        if(root == null)
            return;
        recursiveInOrder(root.left, list);
        list.add(root.value);
        recursiveInOrder(root.right, list);
    }

    private void recursivePostOrder(Node root, List<T> list){
        if(root == null)
            return;
        recursivePostOrder(root.left, list);
        recursivePostOrder(root.right, list);
        list.add(root.value);
    }

    public void sort(List<T> list){
        recursiveSort(root, list);
    }

    private List<T> recursiveSort(Node root, List<T> list){
        if(root == null)
            return list;
        recursiveSort(root.left, list);
        list.add(root.value);
        recursiveSort(root.right, list);
        return list;
    }
}