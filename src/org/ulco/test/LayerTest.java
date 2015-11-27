package org.ulco.test;

import junit.framework.TestCase;
import org.ulco.*;

public class LayerTest extends TestCase {
    public void testType() throws Exception {
        Document document = new Document();
        int oldID = ID.oldID();
        Layer layer = document.createLayer();

        layer.add(new Square(new Point(2, 8), 10));

        assertEquals(layer.getID(), oldID + 1);
        assertEquals(layer.get(0).getID(), oldID + 2);
    }

    public void testJSON() throws Exception {
        Layer l = new Layer();
        Square s = new Square(new Point(0, 0), 5);
        Circle c = new Circle(new Point(5, 5), 4);

        l.add(s);
        l.add(c);
        assertEquals(l.toJson(), "{ type: layer, objects : { { type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 5.0 }, " +
                "{ type: circle, center: { type: point, x: 5.0, y: 5.0 }, radius: 4.0 } } }");
    }

    public void testGroup() throws Exception {
        Layer l = new Layer();
        Square s = new Square(new Point(0, 0), 5);
        Circle c = new Circle(new Point(5, 5), 4);
        Group g = new Group();
        g.add(s);
        g.add(c);

        l.add(s);
        l.add(c);
        l.add(g);
        assertEquals(l.getObjectNumber(), 4);
    }

    public void testSelectGroup() throws Exception {
        Layer l = new Layer();
        Square s = new Square(new Point(0, 0), 5);
        Circle c = new Circle(new Point(5, 5), 4);
        Group g = new Group();
        g.add(s);
        g.add(c);

        l.add(s);
        l.add(c);
        l.add(g);



        GraphicsObjects list1 = l.select(new Point(0,0), 5);
        GraphicsObjects list2 = l.select(new Point(0,0), 10);

        assertEquals(list1.size(), 1);
        assertEquals(list2.size(), 4);
    }
}