package org.ulco;

/**
 * Created by rdehu on 27/11/15.
 */
public abstract class SimpleGraphicsObject extends GraphicsObject{

    protected Point m_center;

    abstract public GraphicsObject copy();

    abstract public String toJson();

    abstract public String toString();

    public boolean isClosed(Point pt, double distance){
        return Math.sqrt(dist(m_center.getX(), pt.getX()) +
                dist(m_center.getY(), pt.getY())) <= distance;
    }

    private double dist(double X, double Y){
        return (X - Y) * (X - Y);
    }


    public void move(Point delta) { m_center.move(delta); }

    public Point getCenter() { return m_center; }

    public GraphicsObjects returnElement(){
        GraphicsObjects list = new GraphicsObjects();
        list.add(this);
        return list;
    }

    public int size(){return 1;}
}
