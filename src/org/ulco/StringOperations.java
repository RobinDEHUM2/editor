package org.ulco;

import java.util.Vector;

/**
 * Created by rdehu on 27/11/15.
 */
public class StringOperations<maClasse extends myJsonnable> {

    public static int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    public static void parseObjects(String objectsStr, Vector<GraphicsObject> m_objectList) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }


    public String toJson(Vector<maClasse> m_list) {
        String str = "";

        for (int i = 0; i < m_list.size(); ++i) {
            maClasse element = m_list.elementAt(i);

            str += element.toJson();
            if (i < m_list.size() - 1) {
                str += ", ";
            }
        }
        return str + " } }";
    }





}
