package org.example.grafproj2;

import java.util.ArrayList;
import java.util.List;

public class Graf {
    private final List<Node> nodes;
    private final List<Edge> edges;
    private int m_id ;

    public Graf() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        m_id=1;
    }

    public int getId()
    {
        return m_id;
    }
    public void addNode(int x, int y) {
        int overlapThreshold = 30;
        for (Node node : nodes)
        {
            int dx = (int) (node.getPos().getX() - x);
            int dy = (int) (node.getPos().getY() - y);
            int distance = (int) Math.sqrt(dx * dx + dy * dy);

            if (distance < overlapThreshold)
            {
                return ;
            }
        }

        Node node = new Node( x, y);
        node.setId(m_id++);
        nodes.add(node);
    }

    public boolean addEdge(Node first, Node second) {
        if (!nodes.contains(first) || !nodes.contains(second)) {
            System.out.println("Error: One or both nodes are not in the graph.");
            return false;
        }
        if (first.equals(second)) {
            System.out.println("Error: Cannot create an edge between the same node.");
            return false;
        }
        for (Edge edge : edges) {
            if ((edge.getFirst().equals(first) && edge.getSecond().equals(second)) ||
                    (edge.getFirst().equals(second) && edge.getSecond().equals(first))) {
                System.out.println("Error: Edge already exists between these nodes.");
                return false;
            }
        }
        edges.add(new Edge(first, second));
        return true;
    }

    public List<Node> getNodes() { return nodes; }
    public List<Edge> getEdges() { return edges; }

    public void printGraph() {
        System.out.println("Nodes:");
        for (Node node : nodes) {
            System.out.println(node);
        }
        System.out.println("Edges:");
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }
}
