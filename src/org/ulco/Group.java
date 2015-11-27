package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject{

    private Vector<GraphicsObject> m_objectList;
    private int m_ID;

    public Group() {
        m_objectList = new Vector<GraphicsObject>();
        m_ID = ID.newID();
    }

    public Group(String json) {
        m_objectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2));
        parseGroups(str.substring(groupsIndex + 8, endIndex - 1));
    }

    public boolean isClosed(Point pt, double distance){
        boolean closed = false;

        if(m_objectList.size()>0){

            closed = true;

            for(int i = 0; i < m_objectList.size() && closed ; i++){
                closed = m_objectList.get(i).isClosed(pt, distance);

            }
        }

        return closed;
    }

    public void add(Object object) {
        m_objectList.add((GraphicsObject)object);
    }

    public Group copy() {
        Group g = new Group();

        for (GraphicsObject o : m_objectList) {

            g.add(o.copy());
        }

        return g;
    }

    public int getID() {
        return m_ID;
    }

    public void move(Point delta) {

        for (GraphicsObject o : m_objectList) {

            o.move(delta);
        }

    }

    private int searchSeparator(String str) {
        return StringOperations.searchSeparator(str);
    }

    private void parseGroups(String groupsStr) {
        StringOperations.parseObjects(groupsStr, m_objectList);
    }

    private void parseObjects(String objectsStr) {
        StringOperations.parseObjects(objectsStr, m_objectList);
    }

    public int size() {
        int size = m_objectList.size();

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);

            if( element instanceof Group) {

                size += ((Group)element).size();
                size--;
            }
        }
        return size;
    }

    private void sort(){
        Vector<GraphicsObject> vectorGraphicObjects = new Vector<GraphicsObject>();
        Vector<GraphicsObject> vectorGroup = new Vector<GraphicsObject>();

        for(GraphicsObject o : m_objectList){
            if(o instanceof Group){
                vectorGroup.add(o);
            }
            else{
                vectorGraphicObjects.add(o);
            }
        }

        m_objectList.clear();
        m_objectList.addAll(vectorGraphicObjects);
        m_objectList.addAll(vectorGroup);

    }

    public String toJson() {
        String str = "{ type: group, objects : { ";


        for (int i = 0; i < m_objectList.size() ; ++i) {
            GraphicsObject element = m_objectList.elementAt(i);

            if(!(element instanceof Group) ) {

                str += element.toJson();
                if (i < m_objectList.size() - 1) {
                    str += ", ";
                }
            }
        }
        str += " }, groups : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);

            if( element instanceof Group) {

                str += element.toJson();
            }


        }
        return str + " } }";
    }

    public String toString() {
        String str = "group[[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);




            if(!(element instanceof Group) ) {

                if (!str.equals("group[[")) {
                    str += ", ";
                }

                str += element.toString();

            }
        }

        str += "],[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);

            if( element instanceof Group) {

                str += element.toString();
            }
        }

        return str + "]]";
    }



    public GraphicsObjects returnElement(){
        GraphicsObjects list = new GraphicsObjects();
        for( GraphicsObject object : m_objectList){
            list.add(object);
        }

        return list;
    }


}
