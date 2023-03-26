import org.junit.platform.commons.util.StringUtils;

import java.lang.reflect.Array;
import java.util.*;

public class Graph<T> {
    double[][] graph;
    HashMap<T,Integer> indexes;
    String pathForDFS = "";

    public Graph(List<Edge<T>> edges) {
        int counter = 0;
        indexes = new HashMap<>();
        for(int i = 0; i < edges.size(); i++){
            Edge<T> edge = edges.get(i);
            boolean containsSource = indexes.containsKey(edge.getSource());
            boolean containsDestination = indexes.containsKey(edge.getDestination());
            if(!containsSource){
                indexes.put(edge.getSource(), counter);
                counter++;
            }
            if(!containsDestination){
                indexes.put(edge.getDestination(), counter);
                counter++;
            }
        }
        graph = new double[counter][counter];
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph.length; j++){
                if(graph[i][j] == 0){
                    graph[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        for(int i = 0; i < edges.size(); i++){
            Edge<T> edge = edges.get(i);
            int a = indexes.get(edge.getSource());
            int b = indexes.get(edge.getDestination());
            graph[a][b] = edge.getWeight();
        }
    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        if(!indexes.containsKey(startNode)){
            throw new NoSuchElementException();
        } else {
            boolean[] visited = new boolean[graph.length];
            int i = indexes.get(startNode);
            dfs(i, visited);
            StringBuffer sb = new StringBuffer(pathForDFS);
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            pathForDFS = String.valueOf(sb);
            return pathForDFS;
        }
    }

    public void dfs(int i, boolean[] visited){
        visited[i] = true;
        T key = null;
        for (Map.Entry<T, Integer> entry : indexes.entrySet()) {
            if (entry.getValue() == i) {
                key = entry.getKey();
                break;
            }
        }
        pathForDFS = pathForDFS + key.toString() + ", ";
        for(int j = 0; j < graph.length; j++){
            if(graph[i][j] != Double.POSITIVE_INFINITY){
                if(!visited[j])
                    dfs(j,visited);
            }
        }
    }

    public String breadthFirst(T startNode) throws NoSuchElementException {
        if(!indexes.containsKey(startNode)){
            throw new NoSuchElementException();
        } else {
            String[] color = new String[graph.length];
            double[] d = new double[graph.length];
            List<T> parent = new ArrayList<>();
            int i = indexes.get(startNode);
            String path = "";
            for (int j = 0; j < graph.length; j++) {
                if (j != i) {
                    color[j] = "white";
                    d[j] = Double.POSITIVE_INFINITY;
                }
                parent.add(null);
            }
            color[i] = "grey";
            d[i] = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            T key = null;
            path = startNode.toString() + ", ";
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int j = 0; j < graph.length; j++) {
                    if (graph[u][j] != Double.POSITIVE_INFINITY) {
                        if (color[j].equals("white")) {
                            for (Map.Entry<T, Integer> entry : indexes.entrySet()) {
                                if (entry.getValue() == j) {
                                    key = entry.getKey();
                                    break;
                                }
                            }
                            path = path + key.toString() + ", ";
                            color[j] = "grey";
                            d[j] = d[u] + 1;
                            parent.set(j, key);
                            queue.add(j);
                        }
                    }
                }
                color[u] = "black";
            }
            StringBuffer sb = new StringBuffer(path);
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            path = String.valueOf(sb);

            return path;
        }
    }

    public int connectedComponents() {
        String[] color = new String[graph.length];
        for (int j = 1; j < graph.length; j++) {
                color[j] = "white";
        }
        color[0] = "grey";
        int counter = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int j = 0; j < graph.length; j++) {
                if (graph[u][j] != Double.POSITIVE_INFINITY) {
                    if (color[j].equals("white")) {
                        color[j] = "grey";
                        queue.add(j);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LinkedList<Edge<String>> edges = new LinkedList<>();
        edges.add(new Edge<>("zero", "two", 2));
        edges.add(new Edge<>("zero", "four", 5));
        edges.add(new Edge<>("four", "two", 3));
        edges.add(new Edge<>("four", "one", 6));
        edges.add(new Edge<>("two", "one", 4));
        edges.add(new Edge<>("four", "three", 1));
        edges.add(new Edge<>("three", "one", 3));
        edges.add(new Edge<>("three", "five", 4));
        edges.add(new Edge<>("five", "zero", 3));

        edges.add(new Edge<>("six", "seven", 3));
        edges.add(new Edge<>("seven", "eight", 2));
        edges.add(new Edge<>("eight", "six", 4));

        edges.add(new Edge<>("nine", "ten", 2));

        Graph<String> graph = new Graph<>(edges);
        graph.depthFirst("five");
    }
}
