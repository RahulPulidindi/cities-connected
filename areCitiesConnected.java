import java.util.*;
import java.io.*;

public class areCitiesConnected {

    public static Graph createGraph(Scanner scanner) {
        Graph graph = new Graph();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cities = line.split(", ");
            Node city1 = new Node(cities[0]);
            Node city2 = new Node(cities[1]);
            graph.insert(city1, city2);
        }
        return graph;
    }
    
    public static boolean checkConnection(Graph graph, Node city1, Node city2) {
        if (city1.getName().equals(city2.getName())) return true;
        
        Node firstNode = new Node(city1.getName());
        Node secondNode = new Node(city2.getName());
        for (Node n : graph.adjMap.keySet()) {
            if (city1.getName().equals(n.getName())) {
                firstNode = n;
            }
            if (city2.getName().equals(n.getName())) {
                secondNode = n;
            }
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(firstNode);
        HashMap<Node, Boolean> visited = new HashMap<>();
        for (Node n : graph.adjMap.keySet()) {
            visited.put(n, false);
        }
        visited.put(firstNode, true);

        while (!queue.isEmpty()) {
            if (visited.get(secondNode)) {
                return true;
            }
            Node curr = queue.poll();
            Iterator<Node> iter = graph.adjMap.get(curr).iterator();
            while (iter.hasNext()) {
                Node next = iter.next();
                Node nextNode = new Node(next.getName());
                for (Node n : graph.adjMap.keySet()) {
                    if (next.getName().equals(n.getName())) {
                        nextNode = n;
                    }
                }
                if (!visited.get(nextNode)) {
                    queue.add(nextNode);
                    visited.put(nextNode, true);
                }
            }
        }
        return false;
    }

    public static void main (String[] args) throws FileNotFoundException {
        File text = new File("/Users/rahulpulidindi/Desktop/citiesConnected/city.txt");
        Scanner scanner = new Scanner(text);
        Scanner userInput = new Scanner(System.in);

        Graph graph = createGraph(scanner);
        
        System.out.println("Enter first city: ");
        Node city1 = new Node(userInput.nextLine());
        System.out.println("Enter second city: ");
        Node city2 = new Node(userInput.nextLine());

        if (checkConnection(graph, city1, city2)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

        userInput.close();
        scanner.close();
    }
}