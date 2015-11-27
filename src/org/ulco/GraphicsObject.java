package org.ulco;

abstract public class GraphicsObject extends myJsonnable {
    public GraphicsObject() {
        m_ID = ID.newID();
    }

    abstract public GraphicsObject copy();

    public int getID() {
        return m_ID;
    }

    abstract boolean isClosed(Point pt, double distance);

    abstract void move(Point delta);

    abstract public String toJson();

    abstract public String toString();

    abstract public GraphicsObjects returnElement();

    abstract public int size();

    private int m_ID;
}
