package org.ulco;

import java.util.Vector;

public class Layer extends myJsonnable{
    public Layer() {
        m_list = new Vector<GraphicsObject>();
        m_ID = ID.newID();
    }

    public Layer(String json) {
        m_list= new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, endIndex - 1));
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int size(){
        return m_list.size();
    }

    public int getObjectNumber() {
        int size = 0;
        for(GraphicsObject o : m_list){
            size+=o.size();

        }
        return size;
    }

    public int getID() {
        return m_ID;
    }

    private void parseObjects(String objectsStr) {
        StringOperations.parseObjects(objectsStr, m_list);

    }

    private int searchSeparator(String str) {
        return StringOperations.searchSeparator(str);
    }

    public GraphicsObjects select(Point pt, double distance) {

        Select select = new Select(pt, distance);

        return select.get(m_list);
    }

    public String toJson() {
        String str = "{ type: layer, objects : { ";

        StringOperations<GraphicsObject> SO = new StringOperations<GraphicsObject>();



        str +=SO.toJson(m_list);


        return str;
    }

    public Vector<GraphicsObject> getList(){
        return new Vector<GraphicsObject>(m_list);
    }

    private Vector<GraphicsObject> m_list;
    private int m_ID;
}
