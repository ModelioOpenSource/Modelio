/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.diagram.persistence;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Serialize a persistent element into an XML string.
 * 
 * @author cmarin
 */
@objid ("cb7c35af-186f-11e2-92d2-001ec947c8cc")
public class XmlDiagramWriter implements IDiagramWriter {
    @objid ("ebba428e-186f-11e2-92d2-001ec947c8cc")
    private Map<IPersistent, String> writtenObjects = new HashMap<>();

    @objid ("cb7c35b1-186f-11e2-92d2-001ec947c8cc")
    private StringWriter result = new StringWriter();

    @objid ("cb7c35b2-186f-11e2-92d2-001ec947c8cc")
    private XMLStreamWriter writer;

    @objid ("cb7c35b3-186f-11e2-92d2-001ec947c8cc")
    private IPersistent root;

    /**
     * Creates an XML serializer.
     * 
     * @throws org.modelio.diagram.persistence.PersistenceException in case of unexpected failure.
     */
    @objid ("cb7c35b8-186f-11e2-92d2-001ec947c8cc")
    public XmlDiagramWriter() throws PersistenceException {
        XMLOutputFactory f = XMLOutputFactory.newInstance();
        try {
            this.writer = f.createXMLStreamWriter(this.result);
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Get the serialized string.
     * 
     * @return the serialized string.
     */
    @objid ("cb7c35bb-186f-11e2-92d2-001ec947c8cc")
    @Override
    public String getOutput() {
        return org.modelio.vcore.utils.UUBase64Compressor.compress(this.result.toString());
    }

    /**
     * Get the root element of this writer.
     * <p>
     * The root element is the element that was passed to
     * {@link #save(IPersistent)}.
     * 
     * @return the root element.
     */
    @objid ("cb7e97c1-186f-11e2-92d2-001ec947c8cc")
    @Override
    public IPersistent getRoot() {
        return this.root;
    }

    /**
     * Save a root persistent element and all its relations.
     * 
     * @param diagram the element to save
     * @throws org.modelio.diagram.persistence.PersistenceException in case of unexpected error.
     */
    @objid ("cb7e97c6-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void save(IPersistent diagram) throws PersistenceException {
        this.root = diagram;
        try {
            // write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            this.writer.writeStartDocument();
            this.writePersistentObject(diagram);
            this.writer.writeEndDocument();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("cb7e97ca-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(Enum<?> e) {
        this.writeValueTag("Enum:" + e.getClass().getName(), e.toString());
    }

    @objid ("cb7e97cf-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(Float value) {
        this.writeValueTag("Float", value.toString());
    }

    @objid ("cb7e97d3-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(AbsoluteBendpoint value) throws PersistenceException {
        final String v = value.x + ";" + value.y;
        this.writeValueTag("AbsoluteBendpoint", v);
    }

    @objid ("cb7e97d8-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(String value) throws PersistenceException {
        this.writeValueTag("String", value);
    }

    @objid ("cb7e97db-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(Boolean value) {
        this.writeValueTag("Boolean", value.toString());
    }

    @objid ("cb7e97df-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(Double value) {
        this.writeValueTag("Double", value.toString());
    }

    @objid ("cb7e97e3-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(int value) throws PersistenceException {
        this.writeValueTag("Integer", String.valueOf(value));
    }

    @objid ("cb7e97e6-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(MRef value) throws PersistenceException {
        // write("<Att name='" + attName + "' type='MRef' value='" + +"'/>");
        this.writeValueTag("MRef", value.mc + " " + value.uuid);
    }

    @objid ("cb7e97e9-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(Rectangle value) throws PersistenceException {
        final String v = value.x + ";" + value.y + ";" + value.width + ";" + value.height;
        this.writeValueTag("Rectangle", v);
    }

    @objid ("cb7e97ee-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(Point value) throws PersistenceException {
        final String v = value.x + ";" + value.y;
        this.writeValueTag("Point", v);
    }

    /**
     * Write an external reference to a persistent element.
     * <p>
     * An external element is an element that is not stored in this stream but
     * in another one. The provided ids must help the diagram reader to retrieve
     * the data where is stored the external element.
     * 
     * @param extObj The external element to reference.
     * @param dbId A "database" id that can be used to retrieve the location
     * where the element is stored
     * @param refId An identifier for the external element
     * @throws org.modelio.diagram.persistence.PersistenceException in case of unexpected error.
     */
    @objid ("cb7e97f3-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeExtRef(IPersistent extObj, String dbId, String refId) throws PersistenceException {
        try {
            this.writer.writeEmptyElement(SchemaConstants.TAG_EXTREF);
            this.writer.writeAttribute(SchemaConstants.ATT_OBJECT_TYPE, extObj.getClass().getName());
            this.writer.writeAttribute(SchemaConstants.ATT_EXTREF_EXTDBID, dbId);
            this.writer.writeAttribute(SchemaConstants.ATT_EXTREF_EXTID, refId);
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("cb7e97f9-186f-11e2-92d2-001ec947c8cc")
    private <V> void writeList(List<V> list) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_LIST);
            for (V v : list) {
                this.writeObjectValue(v);
            }
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("cb7e97ff-186f-11e2-92d2-001ec947c8cc")
    private <K,V> void writeMap(Map<K, V> map) {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_MAP);
            for (Entry<K, V> e : map.entrySet()) {
                // Write key
                this.writeObjectValue(e.getKey());
        
                // Write value
                this.writeObjectValue(e.getValue());
            }
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Write an attribute whose type is not determined.
     * <p>
     * Only types for which a writeAtt() method is available are supported.
     * @param attName
     * @param value
     * 
     * @throws org.modelio.diagram.persistence.PersistenceException in case of unexpected error.
     */
    @objid ("cb7e9807-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, Object value) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeObjectValue(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("cb7e980c-186f-11e2-92d2-001ec947c8cc")
    private String getId(IPersistent i) {
        return this.writtenObjects.get(i);
    }

    @objid ("cb7e9811-186f-11e2-92d2-001ec947c8cc")
    private String putId(IPersistent i) {
        final String ret = String.valueOf(this.writtenObjects.size() + 1);
        this.writtenObjects.put(i, ret);
        return ret;
    }

    @objid ("cb7e9816-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(Color value) {
        final String s = String.valueOf(value.getRed()) + ";" + String.valueOf(value.getGreen()) + ";"
                + String.valueOf(value.getBlue());
        
        this.writeValueTag("Color", s);
    }

    @objid ("cb80fa1c-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(Font value) {
        final FontData fontData = value.getFontData()[0];
        final String s = fontData.getName() + ";" + String.valueOf(fontData.getHeight()) + ";"
                + String.valueOf(fontData.getStyle());
        
        this.writeValueTag("Font", s);
    }

    /**
     * Write an external reference to the given persistent element.
     * <p>
     * Calls {@link IPersistent#write(IDiagramWriter)} that must call
     * {@link #writeExtRef(IPersistent, String, String)}.
     * <p>
     * This (bad) design (work in progress) is made to minimize the methods to
     * add to IPersistent.
     * 
     * @param c the external element.
     */
    @objid ("cb80fa1f-186f-11e2-92d2-001ec947c8cc")
    private void writeExternalRef(IPersistent c) {
        c.write(this);
    }

    @objid ("cb80fa35-186f-11e2-92d2-001ec947c8cc")
    private void writePersistentObject(IPersistent i) throws XMLStreamException {
        final String objId = this.putId(i);
        
        this.writer.writeStartElement(SchemaConstants.TAG_PERSISTENT);
        this.writer.writeAttribute(SchemaConstants.ATT_OBJECT_ID, objId);
        this.writer.writeAttribute(SchemaConstants.ATT_OBJECT_TYPE, i.getClass().getName());
        this.writer.writeAttribute(SchemaConstants.ATT_OBJECT_MAJOR_VERSION, Integer.toString(i.getMajorVersion()));
        
        i.write(this);
        
        this.writer.writeEndElement();
    }

    /**
     * Write a reference to another XML node.
     * 
     * @param i the persistent object to reference.
     * @throws javax.xml.stream.XMLStreamException in case of error
     */
    @objid ("cb80fa38-186f-11e2-92d2-001ec947c8cc")
    private void writeRef(IPersistent i) throws XMLStreamException {
        this.writer.writeEmptyElement(SchemaConstants.TAG_REF);
        this.writer.writeAttribute(SchemaConstants.ATT_OBJECT_ID, this.getId(i));
    }

    @objid ("cb80fa3c-186f-11e2-92d2-001ec947c8cc")
    private void writeObjectValue(Object value) throws PersistenceException {
        if (value == null) {
            this.writeValueTag("null", null);
        } else {
            Class<?> c = value.getClass();
            if (c == Integer.class) {
                this.writeValue(((Integer) value).intValue());
            } else if (c == String.class) {
                this.writeValue((String) value);
            } else if (c == Rectangle.class) {
                this.writeValue((Rectangle) value);
            } else if (c == Point.class) {
                this.writeValue((Point) value);
            } else if (c == AbsoluteBendpoint.class) {
                this.writeValue((AbsoluteBendpoint) value);
            } else if (c == Color.class) {
                this.writeValue((Color) value);
            } else if (c == Boolean.class) {
                this.writeValue((Boolean) value);
            } else if (c == Double.class) {
                this.writeValue((Double) value);
            } else if (c == Float.class) {
                this.writeValue((Float) value);
            } else if (c == MRef.class) {
                this.writeValue((MRef) value);
            } else if (c == Font.class) {
                this.writeValue((Font) value);
            } else if (c == Dimension.class) {
                this.writeValue((Dimension) value);
            } else if (value instanceof Enum<?>) {
                this.writeValue((Enum<?>) value);
            } else if (value instanceof List<?>) {
                this.writeList((List<?>) value);
            } else if (value instanceof Map<?, ?>) {
                this.writeMap((Map<?, ?>) value);
            } else if (value instanceof IPersistent) {
                this.writePersistentValue((IPersistent) value);
            } else {
                throw new IllegalArgumentException(c.getSimpleName() + " is not handled.");
            }
        }
    }

    @objid ("cb80fa3f-186f-11e2-92d2-001ec947c8cc")
    private void writePersistentValue(IPersistent c) {
        try {
            if (c == null)
                this.writeValueTag("null", null);
            else if (c.isExternal(this))
                this.writeExternalRef(c);
            else if (this.writtenObjects.containsKey(c))
                this.writeRef(c);
            else
                this.writePersistentObject(c);
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Write a {@link Boolean} attribute
     * @param attName
     * @param value
     * @throws PersistenceException
     * in case of unexpected error.
     */
    @objid ("cb80fa49-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, Boolean value) {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeValue(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Write a {@link Double} attribute
     * @param attName
     * @param value
     * @throws PersistenceException
     * in case of unexpected error.
     */
    @objid ("cb80fa4f-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, Double value) {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeValue(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Write a string attribute
     * @param attName
     * @param value
     * @throws PersistenceException
     * in case of unexpected error.
     */
    @objid ("cb80fa5c-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, Float value) {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeValue(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Write an integer attribute.
     * @param attName
     * @param value
     * 
     * @throws org.modelio.diagram.persistence.PersistenceException in case of unexpected error.
     */
    @objid ("cb835c75-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, int value) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeValue(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Write a single object relation
     * 
     * @param relation a relation name to be fetched by readObject().
     * @param object the object to write.
     * @throws org.modelio.diagram.persistence.PersistenceException in case of unexpected error.
     */
    @objid ("cb835c7a-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String relation, IPersistent object) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, relation);
        
            this.writePersistentValue(object);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Write an {@link MRef} attribute.
     * @param attName
     * 
     * @param mRef a model element reference
     * @throws org.modelio.diagram.persistence.PersistenceException in case of unexpected error.
     */
    @objid ("cb835c7f-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, MRef mRef) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeValue(mRef);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Write a string attribute.
     * <p>
     * String attribute values are put in a text xml node to support line breaks.
     * 
     * @param attName attribute name
     * @param value attribute value
     * @throws org.modelio.diagram.persistence.PersistenceException in case of unexpected error.
     */
    @objid ("cb835c92-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, String value) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            //this.writeValue(value);
            this.writer.writeStartElement(SchemaConstants.TAG_VALUE);
            this.writer.writeAttribute(SchemaConstants.ATT_VALUE_TYPE, "String");
            if (value != null)
                this.writer.writeCharacters(value);
            this.writer.writeEndElement();
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("cb835c97-186f-11e2-92d2-001ec947c8cc")
    private void writeValueTag(String type, String value) throws PersistenceException {
        // write("<Value type='String' value='"+value+"'/>");
        try {
            this.writer.writeEmptyElement(SchemaConstants.TAG_VALUE);
            this.writer.writeAttribute(SchemaConstants.ATT_VALUE_TYPE, type);
            if (value != null)
                this.writer.writeAttribute(SchemaConstants.ATT_VALUE_VALUE, value);
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("cb835ca2-186f-11e2-92d2-001ec947c8cc")
    private void writeValue(Dimension value) {
        final String v = value.width + ";" + value.height;
        this.writeValueTag("Dimension", v);
    }

    @objid ("ed5de069-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, Enum<?> value) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeValue(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("ed6042c4-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, Rectangle value) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeValue(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("ed62a517-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, Point value) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeValue(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("ed62a51f-186f-11e2-92d2-001ec947c8cc")
    @Override
    public <K,V> void writeProperty(String attName, Map<K, V> value) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeMap(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("ed650779-186f-11e2-92d2-001ec947c8cc")
    @Override
    public <V> void writeProperty(String attName, List<V> value) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeList(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("ed650781-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, AbsoluteBendpoint value) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeValue(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

    @objid ("ed6769d1-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void writeProperty(String attName, Dimension value) throws PersistenceException {
        try {
            this.writer.writeStartElement(SchemaConstants.TAG_PROP);
            this.writer.writeAttribute(SchemaConstants.ATT_PROP_NAME, attName);
        
            this.writeValue(value);
        
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new PersistenceException(e);
        }
    }

}
