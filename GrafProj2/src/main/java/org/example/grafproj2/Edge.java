package org.example.grafproj2;

public class Edge {
    private Node first;
    private Node second;
    private double length;

    public Edge(Node first, Node second) {
        this.first = first;
        this.second = second;
        this.length = calculateLength();
    }

    private double calculateLength() {
        int dx = (int) (second.getPos().getX() - first.getPos().getX());
        int dy = (int) (second.getPos().getY() - first.getPos().getY());
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Node getFirst() {
        return first;
    }

    public Node getSecond() {
        return second;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Edge{" + first + " <--> " + second + ", length=" + length + "}";
    }
}
