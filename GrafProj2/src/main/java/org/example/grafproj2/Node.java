package org.example.grafproj2;

import java.util.Objects;

public class Node implements Comparable<Node>{
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.id, o.id);

    }

    public static class Pos {
        private float x;
        private float y;

        public Pos(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public void setX(float x) {
            this.x = x;
        }

        public void setY(float y) {
            this.y = y;
        }
    }

    private int id;
    private Pos pos;

    public Node( int x, int y) {
        this.pos = new Pos(x, y);
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        this.pos = new Pos(x, y);
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + id + ", pos=(" + pos.getX() + "," + pos.getY() + ")}";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return id == node.id &&
                Float.compare(pos.getX(), node.pos.getX()) == 0 &&
                Float.compare(pos.getY(), node.pos.getY()) == 0;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, pos.getX(), pos.getY());
    }

}
