public class DisjointSetForest implements IDisjointSetStructure {
    int[] parent;
    int[] rank;

    public DisjointSetForest(int size) {
        parent = new int[size];
        rank = new int[size];
        for(int i = 0; i < size;i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if(item >= parent.length || item < 0){
            throw new ItemOutOfRangeException();
        } else {
            if (parent[item] != item) {
                parent[item] = findSet(parent[item]);
            }
            return parent[item];
        }
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if(item1 >= parent.length || item1 < 0 || item2 < 0 || item2 >= parent.length) {
            throw new ItemOutOfRangeException();
        } else {
            int item1Root = findSet(item1);
            int item2Root = findSet(item2);
            if (item1Root == item2Root)
                return;
            if (rank[item1Root] < rank[item2Root])
                parent[item1Root] = item2Root;
            else if (rank[item2Root] < rank[item1Root])
                parent[item2Root] = item1Root;
            else {
                parent[item2Root] = item1Root;
                rank[item1Root] = rank[item1Root] + 1;
            }
        }
    }
}
