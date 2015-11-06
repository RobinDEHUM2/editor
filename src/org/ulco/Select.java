package org.ulco;

import java.util.Vector;

/**
 * Created by rdehu on 06/11/15.
 */
public class Select {

    private Point pt;
    private double distance;

    public Select(Point p, double d){
        pt = p;
        distance = d;
    }

    public GraphicsObjects get(Vector<GraphicsObject> m_list){
        GraphicsObjects list = new GraphicsObjects();

        for (GraphicsObject object : m_list) {
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }
}
