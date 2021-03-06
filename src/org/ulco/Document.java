package org.ulco;

import java.util.Iterator;
import java.util.Vector;

public class Document extends myJsonnable{
    public Document() {
        m_layers = new Vector<Layer>();
    }

    public Document(String json) {
        m_layers = new Vector<Layer>();
        String str = json.replaceAll("\\s+", "");
        int layersIndex = str.indexOf("layers");
        int endIndex = str.lastIndexOf("}");

        parseLayers(str.substring(layersIndex + 8, endIndex));
    }


    public Layer createLayer() {
        Layer layer = new Layer();

        m_layers.add(layer);
        return layer;
    }

    public int getLayerNumber() {
        return m_layers.size();
    }

    public int getObjectNumber() {
        int size = 0;

        for (int i = 0; i < m_layers.size(); ++i) {
            size += m_layers.elementAt(i).getObjectNumber();
        }
        return size;
    }

    private void parseLayers(String layersStr) {
        while (!layersStr.isEmpty()) {
            int separatorIndex = searchSeparator(layersStr);
            String layerStr;

            if (separatorIndex == -1) {
                layerStr = layersStr;
            } else {
                layerStr = layersStr.substring(0, separatorIndex);
            }
            m_layers.add(JSON.parseLayer(layerStr));
            if (separatorIndex == -1) {
                layersStr = "";
            } else {
                layersStr = layersStr.substring(separatorIndex + 1);
            }
        }
    }

    private int searchSeparator(String str) {
        return StringOperations.searchSeparator(str);
    }

    public GraphicsObjects select(Point pt, double distance) {
        GraphicsObjects list = new GraphicsObjects();

        Select s = new Select(pt, distance);

        list = s.get(this);

        return list;
    }

    public String toJson() {
        String str = "{ type: document, layers: { ";


        StringOperations<Layer> SO = new StringOperations<Layer>();

        str +=SO.toJson(m_layers);



        return str;
    }

    public Vector<Layer> getLayers(){

        return new Vector<Layer>(m_layers);
    }

    private Vector<Layer> m_layers;
}
