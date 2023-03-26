public class DisjointSetLinkedList implements IDisjointSetStructure {
    int[] head;
    int[] next;
    int[] last;
    int[] rank;

    public DisjointSetLinkedList(int size) {
        head = new int [size];
        next = new int [size];
        last = new int [size];
        rank = new int [size];
        for(int i = 0; i < size; i++){
            head[i] = i;
            last[i] = i;
            next[i] = -1;
            rank[i] = 1;
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if(item >= head.length || item < 0){
            throw new ItemOutOfRangeException();
        } else {
            return head[item];
        }
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if(item1 >= head.length || item1 < 0 || item2 >= head.length || item2 < 0){
            throw new ItemOutOfRangeException();
        } else {
            int rep1 = head[item1];
            int rep2 = head[item2];
            if (rank[rep1] > rank[rep2]) {
                int nextToAdd = rep2;
                next[last[rep1]] = rep2;
                while (nextToAdd >= 0) {
                    head[nextToAdd] = rep1;
                    nextToAdd = next[nextToAdd];
                }
                last[rep1] = last[rep2];
                rank[rep1] += rank[rep2];
            } else {
                int nextToAdd = rep1;
                next[last[rep2]] = rep1;
                while (nextToAdd >= 0) {
                    head[nextToAdd] = rep2;
                    nextToAdd = next[nextToAdd];
                }
                last[rep2] = last[rep1];
                rank[rep2] += rank[rep1];
            }
        }
    }
}
