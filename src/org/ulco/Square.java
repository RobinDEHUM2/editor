package org.ulco;

public class Square extends SimpleGraphicsObject {
    public Square(Point center, double length) {
        m_center = center;
        this.m_length = length;

    }

    public Square(String json) {
        String str = json.replaceAll("\\s+","");
        int centerIndex = str.indexOf("center");
        int lengthIndex = str.indexOf("length");
        int endIndex = str.lastIndexOf("}");

        m_center = new Point(str.substring(centerIndex + 7, lengthIndex - 1));
        m_length = Double.parseDouble(str.substring(lengthIndex + 7, endIndex));
    }



    public Point getOrigin() { return m_center; }

    public GraphicsObject copy() {
        return new Square(m_center.copy(), m_length);
    }


    public String toJson() {
        return "{ type: square, center: " + m_center.toJson() + ", length: " + this.m_length + " }";
    }

    public String toString() {
        return "square[" + m_center.toString() + "," + m_length + "]";
    }


    private final double m_length;
}
