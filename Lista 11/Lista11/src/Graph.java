import java.lang.reflect.Array;
import java.util.*;

public class Graph<T> {
    ArrayList<LinkedList<Node>> graph;
    HashMap<T,Integer> indexes;


    private class Node<T>{
        public Node(T vertex, int distance){
            this.vertex = vertex;
            this.distance = distance;
        }
        T vertex;
        int distance;
    }

    public Graph(List<Edge<T>> edges) {
        graph = new ArrayList<>();
        int counter = 0;
        indexes = new HashMap<>();
        for(int i = 0; i < edges.size(); i++){
            Edge<T> edge = edges.get(i);
            T vertex1 = edge.getNode1();
            T vertex2 = edge.getNode2();
            int distance = edge.getDistance();
            boolean containsVertex1 = indexes.containsKey(vertex1);
            boolean containsVertex2 = indexes.containsKey(vertex2);
            if(!containsVertex1){
                graph.add(new LinkedList<>());
                indexes.put(vertex1,counter);
                graph.get(indexes.get(vertex1)).add(new Node(vertex1,0));
                graph.get(indexes.get(vertex1)).add(new Node(vertex2,distance));
                counter++;
            }
            if(!containsVertex2){
                graph.add(new LinkedList<>());
                indexes.put(vertex2,counter);
                graph.get(indexes.get(vertex2)).add(new Node(vertex2,0));
                graph.get(indexes.get(vertex1)).add(new Node(vertex1,distance));
                counter++;
            }
        }
    }

    public Map<T, Integer> calculateShortestPaths(T startNode) throws NoSuchElementException {
        if(!indexes.containsKey(startNode)){
            throw new NoSuchElementException();
        } else {
            int[] distanceFromStart = new int[indexes.size()];
            boolean[] included = new boolean[indexes.size()];
            Map<T, Integer> path = new HashMap<>();
            for (int i = 0; i < distanceFromStart.length; i++) {
                distanceFromStart[i] = Integer.MAX_VALUE;
                included[i] = false;
            }
            int startIndex = indexes.get(startNode);
            distanceFromStart[startIndex] = 0;
            for (int i = 0; i < distanceFromStart.length; i++) {
                int u = minValue(distanceFromStart, included);
                included[u] = true;
                for (int j = 0; j < indexes.size(); j++) {
                    T key = null;
                    if (condition(included, distanceFromStart, u, j)) {
                        distanceFromStart[j] = distanceFromStart[u] + getWeight(u, j);
                        for (Map.Entry<T, Integer> entry : indexes.entrySet()) {
                            if (entry.getValue() == j) {
                                key = entry.getKey();
                                break;
                            }
                        }
                        path.put(key, distanceFromStart[j]);
                    }
                }
            }
            return path;
        }
    }

    private boolean condition(boolean[] included, int[] dist,int u, int v){
        int weight = getWeight(u,v);
        return !included[v] && weight != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v];
    }

    private int getWeight(int u, int v){
        int weight = 0;
        for(int i = 0; i < graph.get(u).size();i++){
            int temp = graph.get(u).get(i).distance;
            Node node  = graph.get(u).get(i);
            T vertex = (T) node.vertex;
            int tempIndex = indexes.get(vertex);
            if(v == tempIndex){
                weight = temp;
                break;
            }
        }
        return weight;
    }

    private int minValue(int[] dist, boolean[] included) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (included[v] == false && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        LinkedList<Edge<String>> edges = new LinkedList<>();
        edges.add(new Edge<>("a", "b", 2));
        edges.add(new Edge<>("a", "c", 5));
        edges.add(new Edge<>("b", "d", 3));
        edges.add(new Edge<>("b", "e", 4));
        edges.add(new Edge<>("c", "d", 5));

        Graph<String> graph = new Graph<>(edges);
        System.out.println(graph.indexes.size());
    }
}

//        for(int i = 0; i < graph.get(startIndex).size(); i++){
//            int temp = graph.get(startIndex).get(i).distance;
//            Node node  = graph.get(startIndex).get(i);
//            T vertex = (T) node.vertex;
//            int tempIndex = indexes.get(vertex);
//            if(temp < min){
//                min = temp;
//                minIndex = tempIndex;
//            }
//        }
//        return minIndex;
