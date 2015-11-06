package org.ulco;

import java.lang.reflect.Constructor;

public class JSON {
    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));
        char first =(char) (type.charAt(0) + 'A' - 'a');

        type ="org.ulco."+ first + type.substring(1);



        try {
            Class c = Class.forName(type);
            Class[] types = new Class[]{String.class};
            Constructor ct = c.getConstructor(types);
            o = (GraphicsObject) ct.newInstance(str);



        }catch(Exception e){
            e.printStackTrace();
        };

        return o;
    }



    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}
