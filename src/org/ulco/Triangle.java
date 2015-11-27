package org.ulco;

/**
 * Created by rdehu on 27/11/15.
 */
public class Triangle extends SimpleGraphicsObject {
    private Point v_A;
    private Point v_B;
    private Point v_C;



    public Triangle(Point A, Point B, Point C){
       m_center = new Point((A.getX()+B.getX()+C.getX())/3, (A.getY()+B.getY()+C.getY())/3);
        v_A = new Point(A.getX()-m_center.getX(),A.getY()-m_center.getY());
        v_B = new Point(C.getX()-m_center.getX(),B.getY()-m_center.getY());
        v_C = new Point(A.getX()-m_center.getX(),C.getY()-m_center.getY());
    }

    public Triangle(Point vA, Point vB, Point vC, Point center){
        v_A = vA;
        v_B = vB;
        v_C = vC;

        m_center = center;
    }

    public Triangle(String json) {
        String str = json.replaceAll("\\s+","");
        int centerIndex = str.indexOf("center");
        int AIndex = str.indexOf("vectorA");
        int BIndex = str.indexOf("vectorB");
        int CIndex = str.indexOf("vectorC");
        int endIndex = str.lastIndexOf("}");

        m_center = new Point(str.substring(centerIndex + 7, AIndex - 1));
        v_A = new Point(str.substring(AIndex + 8, BIndex - 1));
        v_B = new Point(str.substring(BIndex + 8, CIndex - 1));
        v_C = new Point(str.substring(CIndex + 8, endIndex - 1));

    }

    public Triangle copy(){return new Triangle(v_A,v_B,v_C, m_center);}



    public String toJson(){
        return "{ type: square, center: " + m_center.toJson() + ", vectorA: " + v_A + ", vectorB: " + v_B + ", vectorC: " + v_C  + " }";
    }

    public String toString() {
        return "triangle[" + m_center.toString() + "," + v_A.toString() + "," + v_B.toString() + "," + v_C.toString() + "]";
    }

    public GraphicsObjects returnElement(){
        GraphicsObjects list = new GraphicsObjects();
        list.add(this);
        return list;
    }

    public int size(){return 1;}
}
