import java.util.*;

public class Graph {
    public HashMap<Node, LinkedList<Node>> adjMap;
    private HashSet<String> distinctCities;

    public Graph() {
        adjMap = new HashMap<>();
        distinctCities = new HashSet<>();
    }

    public void insert(Node city1, Node city2) {
        if (adjMap.size() == 0) {
            adjMap.put(city1, new LinkedList<>());
            adjMap.put(city2, new LinkedList<>());
        } 

        boolean found1 = false;
        boolean found2 = false;

        for (Node n : adjMap.keySet()) {
            if (city1.getName().equals(n.getName())) {
                adjMap.get(n).add(city2);
                found1 = true;
            }
        }
        if (!found1) {
            LinkedList<Node> temp = new LinkedList<>();
            temp.add(city2);
            adjMap.put(city1, temp);
        }

        for (Node n : adjMap.keySet()) {
            if (city2.getName().equals(n.getName())) {
                adjMap.get(n).add(city1);
                found2 = true;
            }
        }
        if (!found2) {
            LinkedList<Node> temp = new LinkedList<>();
            temp.add(city1);
            adjMap.put(city2, temp);
        }
    }

    public int numberOfNodes() {
        for (Node n : adjMap.keySet()) {
            String city = n.getName().replaceAll("\\s", "");
            distinctCities.add(city);
        }
        return distinctCities.size();
    }

    public boolean hasEdge(Node city1, Node city2) {
        return adjMap.containsKey(city1) && adjMap.get(city1) != null && adjMap.get(city1).contains(city2);
    }

    public void printGraph() {
        for (Node root: adjMap.keySet()) {
            System.out.print("Traversing from node " + root.getName() + " - ");
            LinkedList<Node> destinations = adjMap.get(root);
            if (destinations != null) {
                for (Node adjacent : adjMap.get(root)) {
                    System.out.print(adjacent.getName() + " ");
                }
            }
            System.out.println();
        }
    }
    public static void main (String[] args) {
        Graph graph = new Graph();

        Node city1 = new Node("New York");
        Node city2 = new Node("Boston");
        Node city3 = new Node("Albany");
        Node city4 = new Node("Cornell");

        graph.insert(city1, city2);
        graph.insert(city2, city3);
        graph.insert(city4, city1);

        graph.printGraph();

        for (Node n : graph.adjMap.get(city1)) {
            System.out.println(n.getName());
        }

        System.out.println(graph.hasEdge(city2, city1));
        System.out.println(graph.hasEdge(city1, city2));
        System.out.println(graph.hasEdge(city3, city1));
        System.out.println(graph.hasEdge(city2, city4));
    }

}