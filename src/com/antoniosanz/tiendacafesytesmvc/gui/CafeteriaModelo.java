package com.antoniosanz.tiendacafesytesmvc.gui;

import com.antoniosanz.tiendacafesytesmvc.base.Bebida;
import com.antoniosanz.tiendacafesytesmvc.base.Cafe;
import com.antoniosanz.tiendacafesytesmvc.base.Te;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class CafeteriaModelo {
    private ArrayList<Bebida> listaBebidas;

    public CafeteriaModelo() {
        listaBebidas = new ArrayList<>();
    }

    public ArrayList<Bebida> obtenerBebidas() {
        return listaBebidas;
    }

    public void altaCafe(String nombre, String productor, int altitud, float notaCata, LocalDate fecha,
                         String origen, String proceso, String variedad, boolean preparacion, double peso, String molienda) {
        Cafe cafe = new Cafe(nombre, productor, altitud, notaCata, fecha, origen, proceso, variedad, preparacion, peso, molienda);
        listaBebidas.add(cafe);
    }
    public void altaTe(String nombre, String productor, int altitud, float notaCata, LocalDate fecha,
                       String tipo, int tiempoInfusion) {
        Te te = new Te(nombre, productor, altitud, notaCata, fecha, tipo, tiempoInfusion);
        listaBebidas.add(te);
    }

    public boolean existeNombre(String nombre) {
        for (Bebida b : listaBebidas) {
            if (b.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("tienda");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoBebida = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Bebida unaBebida: listaBebidas) {
            if (unaBebida instanceof Cafe) {
                nodoBebida = documento.createElement("Cafe");
            } else {
                nodoBebida = documento.createElement("Te");
            }
            raiz.appendChild(nodoBebida);

            // Campos comunes de Bebida
            nodoDatos = documento.createElement("nombre");
            nodoBebida.appendChild(nodoDatos);
            texto = documento.createTextNode(unaBebida.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("productor");
            nodoBebida.appendChild(nodoDatos);
            texto = documento.createTextNode(unaBebida.getProductor());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("altitud");
            nodoBebida.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unaBebida.getAltitud()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("notacata");
            nodoBebida.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unaBebida.getNotaCata()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fecha");
            nodoBebida.appendChild(nodoDatos);
            texto = documento.createTextNode(unaBebida.getFecha().toString());
            nodoDatos.appendChild(texto);

            // Campos específicos de cafe
            if (unaBebida instanceof Cafe) {
                Cafe cafe = (Cafe) unaBebida;

                nodoDatos = documento.createElement("origen");
                nodoBebida.appendChild(nodoDatos);
                texto = documento.createTextNode(cafe.getOrigen());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("proceso");
                nodoBebida.appendChild(nodoDatos);
                texto = documento.createTextNode(cafe.getProceso());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("variedad");
                nodoBebida.appendChild(nodoDatos);
                texto = documento.createTextNode(cafe.getVariedad());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("tipopreparacion");
                nodoBebida.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(cafe.isTipoPreparacion()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("peso");
                nodoBebida.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(cafe.getPeso()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("molienda");
                nodoBebida.appendChild(nodoDatos);
                texto = documento.createTextNode(cafe.getMolienda());
                nodoDatos.appendChild(texto);
            } else { // campos especificos de té
            Te te = (Te) unaBebida;

            nodoDatos = documento.createElement("tipo");
            nodoBebida.appendChild(nodoDatos);
            texto = documento.createTextNode(te.getTipo());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("tiempoinfusion");
            nodoBebida.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(te.getTiempoInfusion()));
            nodoDatos.appendChild(texto);
            }

        }
        // Guardo en fichero
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);


    }

    public void importarXML() {
        // lo hacemos mañana tmb
    }
}
