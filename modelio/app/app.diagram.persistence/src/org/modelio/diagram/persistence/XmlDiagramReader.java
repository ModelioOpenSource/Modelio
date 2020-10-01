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

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.modelio.platform.ui.CoreColorRegistry;
import org.modelio.platform.ui.CoreFontRegistry;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Reads a diagram from an XML string.
 * 
 * @author cmarin
 */
@objid ("cb750e6e-186f-11e2-92d2-001ec947c8cc")
public class XmlDiagramReader implements IDiagramReader {
    @objid ("cb750e70-186f-11e2-92d2-001ec947c8cc")
    private Element currentDomElement = null;

    @objid ("cb750e71-186f-11e2-92d2-001ec947c8cc")
    private Map<String, IPersistent> readObjects = new HashMap<>();

    @objid ("cb7770b5-186f-11e2-92d2-001ec947c8cc")
    private IPersistent rootObject = null;

    @objid ("cb7770b6-186f-11e2-92d2-001ec947c8cc")
    private IInstanceFactory instanceFactory;

    @objid ("cb7770b7-186f-11e2-92d2-001ec947c8cc")
    private IExtReferenceResolver extRefResolver;

    /**
     * Creates a diagram reader
     * 
     * @param instanceFactory An instance factory
     * @param extRefResolver An external reference resolver
     */
    @objid ("cb7770b8-186f-11e2-92d2-001ec947c8cc")
    public XmlDiagramReader(IInstanceFactory instanceFactory, IExtReferenceResolver extRefResolver) {
        this.instanceFactory = instanceFactory;
        this.extRefResolver = extRefResolver;
    }

    /**
     * Get the root object being read.
     * <p>
     * The root object is the persistent object passed to {@link #readDiagram(String, IPersistent)}.
     * 
     * @return the root object being read.
     */
    @objid ("cb7770bd-186f-11e2-92d2-001ec947c8cc")
    @Override
    public IPersistent getRoot() {
        return this.rootObject;
    }

    /**
     * Read all attributes at once.
     * @param attName The attribute name
     * 
     * @return a map with the attribute name as key and the attribute value as value.
     */
    @objid ("cb7770c2-186f-11e2-92d2-001ec947c8cc")
    @Override
    public Map<String, Object> readAllProperties() throws PersistenceException {
        final Map<String, Object> ret = new HashMap<>();
        final NodeList childrenNodes = this.currentDomElement.getChildNodes();
        
        for (int i = 0; i < childrenNodes.getLength(); i++) {
            final Node n = childrenNodes.item(i);
        
            if (n.getNodeName().equals(SchemaConstants.TAG_PROP)) {
                final Element attElement = (Element) n;
                final String attName = attElement.getAttribute(SchemaConstants.ATT_PROP_NAME);
                ret.put(attName, readPropertyElement(attElement));
            }
        }
        return ret;
    }

    /**
     * Read an attribute whose type is not constant.
     * 
     * @param attName The attribute name
     * @return The attribute value or <tt>null</tt> if the attribute has no value.
     */
    @objid ("cb7770e2-186f-11e2-92d2-001ec947c8cc")
    @Override
    public Object readProperty(String attName) throws PersistenceException {
        Element el = getPropertyElement(attName);
        if (el == null) {
            return null;
        } else {
            return readPropertyElement(el);
        }
    }

    @objid ("cb79d30f-186f-11e2-92d2-001ec947c8cc")
    private static AbsoluteBendpoint convertToAbsoluteBendpoint(String val) {
        final String[] vals = val.split(";");
        return new AbsoluteBendpoint(Integer.valueOf(vals[0]), Integer.valueOf(vals[1]));
    }

    @objid ("cb79d316-186f-11e2-92d2-001ec947c8cc")
    private static Color convertToColor(String val) {
        final String[] vals = val.split(";");
        
        final int red = Integer.parseInt(vals[0]);
        final int green = Integer.parseInt(vals[1]);
        final int blue = Integer.parseInt(vals[2]);
        return CoreColorRegistry.getColor(new RGB(red, green, blue));
    }

    @objid ("cb79d31b-186f-11e2-92d2-001ec947c8cc")
    private static Dimension convertToDimension(String val) {
        final String[] vals = val.split(";");
        return new Dimension(Integer.valueOf(vals[0]), Integer.valueOf(vals[1]));
    }

    /**
     * Convert the given string value to the given enumeration type.
     * 
     * @param enumType The enumeration type
     * @param val the string value to convert
     * @return the enumeration value
     * @throws java.lang.IllegalArgumentException if the specified enum type has no constant with the specified name, or the specified class object does not represent an enum type
     * @throws java.lang.ClassNotFoundException if the class cannot be located
     * @throws java.lang.NullPointerException if <tt>enumType</tt> is null
     */
    @objid ("cb79d322-186f-11e2-92d2-001ec947c8cc")
    private <T extends  Enum<T>> T convertToEnum(String enumType, String val) throws IllegalArgumentException, ClassNotFoundException, NullPointerException {
        return XmlDiagramReader.convertToEnum(this.instanceFactory.getEnumClass(enumType), val);
    }

    /**
     * Returns the enum constant of the specified enum type with the specified name. The name must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
     * 
     * @param enumType the <tt>Class</tt> object of the enum type from which to return a constant
     * @param val the name of the constant to return
     * @return the enum constant of the specified enum type with the specified name
     * @throws java.lang.IllegalArgumentException if the specified enum type has no constant with the specified name, or the specified class object does not represent an enum type
     * @throws java.lang.NullPointerException if <tt>enumType</tt> is null
     */
    @objid ("cb79d32c-186f-11e2-92d2-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    private static <T extends  Enum<T>> T convertToEnum(Class<?> enumType, String val) throws IllegalArgumentException, NullPointerException {
        if (val == null) {
            return null;
        }
        return Enum.valueOf((Class<T>) enumType, val);
    }

    @objid ("cb79d339-186f-11e2-92d2-001ec947c8cc")
    private static Font convertToFont(String val) {
        final String[] vals = val.split(";");
        
        final String name = vals[0];
        final int height = Integer.parseInt(vals[1]);
        final int style = Integer.parseInt(vals[2]);
        return CoreFontRegistry.getFont(new FontData(name, height, style));
    }

    @objid ("cb79d33e-186f-11e2-92d2-001ec947c8cc")
    private static MRef convertToMRef(String val) {
        int p = val.indexOf(' ');
        return new MRef(val.substring(0, p), val.substring(p + 1));
    }

    @objid ("cb79d343-186f-11e2-92d2-001ec947c8cc")
    private static Point convertToPoint(String val) {
        final String[] vals = val.split(";");
        return new Point(Integer.valueOf(vals[0]), Integer.valueOf(vals[1]));
    }

    @objid ("cb79d34a-186f-11e2-92d2-001ec947c8cc")
    private static Rectangle convertToRectangle(String val) {
        final String[] vals = val.split(";");
        return new Rectangle(Integer.valueOf(vals[0]),
                Integer.valueOf(vals[1]),
                Integer.valueOf(vals[2]),
                Integer.valueOf(vals[3]));
    }

    /**
     * Return an instance of the given class for the given DOM element.
     * <p>
     * This method can create a new instance or return an existing one.
     * 
     * @param nodeType The java class
     * @return The created instance.
     * 
     * @exception PersistenceException if the class can't be instantiated.
     */
    @objid ("cb79d351-186f-11e2-92d2-001ec947c8cc")
    private IPersistent createInstance(String nodeType) throws PersistenceException {
        return this.instanceFactory.createInstance(nodeType);
    }

    @objid ("cb79d357-186f-11e2-92d2-001ec947c8cc")
    private IPersistent getCachedObject(String objectId) {
        return this.readObjects.get(objectId);
    }

    @objid ("cb79d35c-186f-11e2-92d2-001ec947c8cc")
    private Element getPropertyElement(String attName) {
        NodeList childrenNodes = this.currentDomElement.getChildNodes();
        
        for (int i = 0; i < childrenNodes.getLength(); i++) {
            Node n = childrenNodes.item(i);
        
            if (n.getNodeName().equals(SchemaConstants.TAG_PROP)) {
                Element subEl = (Element) n;
                if (subEl.getAttribute(SchemaConstants.ATT_PROP_NAME).equals(attName)) {
                    return subEl;
        
                }
            }
        }
        return null;
    }

    /**
     * Read an external reference.
     * 
     * @param domElement a {@link SchemaConstants#TAG_EXTREF} DOM Element.
     * @return The read external reference
     */
    @objid ("cb79d361-186f-11e2-92d2-001ec947c8cc")
    private IPersistent readExtRef(Element domElement) throws PersistenceException {
        final String nodeType = domElement.getAttribute(SchemaConstants.ATT_OBJECT_TYPE);
        final String extDbId = domElement.getAttribute(SchemaConstants.ATT_EXTREF_EXTDBID);
        final String extRefId = domElement.getAttribute(SchemaConstants.ATT_EXTREF_EXTID);
        final String nodeId = domElement.getAttribute(SchemaConstants.ATT_OBJECT_ID);
        Element oldCurrentDomElement = this.currentDomElement;
        
        this.currentDomElement = domElement;
        
        try {
            final IPersistent ret = this.extRefResolver.resolveReference(nodeType, extDbId, extRefId);
        
            if (ret != null && nodeId != null && !nodeId.isEmpty()) {
                this.readObjects.put(nodeId, ret);
            }
        
            return ret;
        } finally {
            this.currentDomElement = oldCurrentDomElement;
        }
    }

    /**
     * Completely read a {@link List} from the given DOM element.
     * 
     * @param subEl DOM Element of type {@link SchemaConstants#TAG_LIST}
     * @return a list of persistent elements
     * @throws org.modelio.diagram.persistence.PersistenceException in case of error.
     */
    @objid ("cb79d366-186f-11e2-92d2-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    private <T> List<T> readListElement(Element subEl) throws PersistenceException {
        final NodeList compNodes2 = subEl.getChildNodes();
        final List<T> ret = new ArrayList<>(compNodes2.getLength());
        
        for (int i2 = 0; i2 < compNodes2.getLength(); i2++) {
            final Node compNode = compNodes2.item(i2);
            if (compNode instanceof Element) {
                final Element compElement = (Element) compNode;
                T persistentObject = (T) readObjectElement(compElement);
                if (persistentObject != null) {
                    ret.add(persistentObject);
                }
            }
        }
        return ret;
    }

    /**
     * Completely read a {@link Map} from the given DOM element.
     * 
     * @param domElement DOM Element of type {@link SchemaConstants#TAG_MAP}
     * @return the read map
     * @throws org.modelio.diagram.persistence.PersistenceException in case of unexpected error.
     */
    @objid ("cb7c3568-186f-11e2-92d2-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    private <K,V> Map<K, V> readMapElement(Element domElement) throws PersistenceException {
        final Map<K, V> ret = new HashMap<>();
        final NodeList childrenNodes = domElement.getChildNodes();
        
        K entryKey = null;
        
        for (int i = 0; i < childrenNodes.getLength(); i++) {
            final Node n = childrenNodes.item(i);
            if (n instanceof Element) {
                final Element mapElement = (Element) n;
                Object o = readObjectElement(mapElement);
        
                if (entryKey == null) {
                    entryKey = (K) o;
                } else {
                    ret.put(entryKey, (V) o);
                    entryKey = null;
                }
            }
        }
        return ret;
    }

    /**
     * Read the given element and return the read Object.
     * 
     * @param compElement a DOM Element.
     * @return the read Object
     * @throws org.modelio.diagram.persistence.PersistenceException in case of error
     */
    @objid ("cb7c3574-186f-11e2-92d2-001ec947c8cc")
    private Object readObjectElement(Element compElement) throws PersistenceException {
        final String tagName = compElement.getTagName();
        if (tagName.equals(SchemaConstants.TAG_PERSISTENT)) {
            return readPersistentElement(compElement);
        } else if (tagName.equals(SchemaConstants.TAG_REF)) {
            return readObjectRef(compElement);
        } else if (tagName.equals(SchemaConstants.TAG_EXTREF)) {
            return readExtRef(compElement);
        } else if (tagName.equals(SchemaConstants.TAG_VALUE)) {
            return readValueElement(compElement);
        } else if (tagName.equals(SchemaConstants.TAG_LIST)) {
            return this.readListElement(compElement);
        } else if (tagName.equals(SchemaConstants.TAG_MAP)) {
            return this.readMapElement(compElement);
        } else {
            throw new PersistenceException("<" + tagName + "> tag not handled.");
        }
    }

    /**
     * Read the 'Ref' tag value and return the matching persistent object.
     * 
     * @param compNode DOM Element of type {@link SchemaConstants#TAG_REF}
     * @return The read referenced persistent object.
     */
    @objid ("cb7c3579-186f-11e2-92d2-001ec947c8cc")
    private IPersistent readObjectRef(Element compNode) {
        // <ref id="xxx"/>
        String objectId = compNode.getAttribute(SchemaConstants.ATT_OBJECT_ID);
        return getCachedObject(objectId);
    }

    /**
     * Completely read an object from the given DOM element.
     * 
     * @param domElement DOM Element of type {@link SchemaConstants#TAG_PERSISTENT}
     * @return the read object. Might be null in some migration cases.
     * @throws org.modelio.diagram.persistence.PersistenceException in case of unexpected error.
     */
    @objid ("cb7c357f-186f-11e2-92d2-001ec947c8cc")
    private IPersistent readPersistentElement(Element domElement) throws PersistenceException {
        final String nodeType = domElement.getAttribute(SchemaConstants.ATT_OBJECT_TYPE);
        final String majorVersionAtt = domElement.getAttribute(SchemaConstants.ATT_OBJECT_MAJOR_VERSION);
        final int majorVersionRead = majorVersionAtt.equals("") ? 0 : Integer.parseInt(majorVersionAtt);
        final String nodeId = domElement.getAttribute(SchemaConstants.ATT_OBJECT_ID);
        
        final Element oldCurrentDomElement = this.currentDomElement;
        this.currentDomElement = domElement;
        
        try {
            // Create a new instance
            IPersistent persistentObject = createInstance(nodeType);
            // Check that a migration is not needed
            if (persistentObject.getMajorVersion() == majorVersionRead) {
                // If major version read is the same as current major version, no migration is needed, just read the object.
                // Deserialize the object
                persistentObject.read(this);
                // Register the read object
                this.readObjects.put(nodeId, persistentObject);
            } else {
                // Major version read is NOT current major version: request help of the dedicated migrator.
                final String migratorType = nodeType + "Migrator";
                final IPersistentMigrator migrator = createMigratorInstance(migratorType);
                if (migrator == null) {
                    throw new PersistenceException("Unable to find '" + migratorType + "' class");
                }
                // Ask for a version of the object that fits the read version
                persistentObject = migrator.createInstanceOfMajorVersion(majorVersionRead);
                if (persistentObject == null) {
                    throw new PersistenceException("Unable to instanciate '" + nodeType + "' class with a major version of " + majorVersionRead);
                }
        
                // this object can read the serialized data
                persistentObject.read(this);
                // Ask for a migration of that object to the most recent version
                persistentObject = migrator.migrate(persistentObject);
                // Register the read object if any
                if (persistentObject != null) {
                    this.readObjects.put(nodeId, persistentObject);
                }
            }
            return persistentObject;
        } finally {
            this.currentDomElement = oldCurrentDomElement;
        }
    }

    /**
     * Read the 'Property' tag value and convert it to the right type.
     * 
     * @param el DOM Element of type {@link SchemaConstants#TAG_PROP}
     * @return The read property value.
     * @throws org.modelio.diagram.persistence.PersistenceException in case of error
     */
    @objid ("cb7c3584-186f-11e2-92d2-001ec947c8cc")
    private Object readPropertyElement(Element el) throws PersistenceException {
        NodeList childrenNodes = el.getChildNodes();
        
        for (int i = 0; i < childrenNodes.getLength(); i++) {
            Node n = childrenNodes.item(i);
            if (n instanceof Element) {
                Element subEl = (Element) n;
                return readObjectElement(subEl);
            }
        }
        return null;
    }

    /**
     * Read the 'Value' tag value and convert it to the right type.
     * 
     * @param el DOM Element of type {@link SchemaConstants#TAG_VALUE}
     * @return The read value
     * @throws java.lang.NumberFormatException in case of number conversion error
     * @throws org.modelio.diagram.persistence.PersistenceException in case of error
     */
    @objid ("cb7c358a-186f-11e2-92d2-001ec947c8cc")
    private Object readValueElement(Element el) throws NumberFormatException, PersistenceException {
        final String type = el.getAttribute(SchemaConstants.ATT_VALUE_TYPE);
        final String val = el.getAttribute(SchemaConstants.ATT_VALUE_VALUE);
        
        if (type.equals("null")) {
            return null;
        } else if (type.equals("Rectangle")) {
            return XmlDiagramReader.convertToRectangle(val);
        } else if (type.equals("Point")) {
            return XmlDiagramReader.convertToPoint(val);
        } else if (type.equals("Dimension")) {
            return XmlDiagramReader.convertToDimension(val);
        } else if (type.equals("AbsoluteBendpoint")) {
            return XmlDiagramReader.convertToAbsoluteBendpoint(val);
        } else if (type.equals("String")) {
            return val + getTextNodes(el);
        } else if (type.equals("Integer")) {
            return Integer.valueOf(val);
        } else if (type.equals("Color")) {
            return XmlDiagramReader.convertToColor(val);
        } else if (type.equals("Font")) {
            return XmlDiagramReader.convertToFont(val);
        } else if (type.equals("Boolean")) {
            return Boolean.parseBoolean(val);
        } else if (type.equals("Double")) {
            return Double.parseDouble(val);
        } else if (type.equals("Float")) {
            return Float.parseFloat(val);
        } else if (type.equals("MRef")) {
            return XmlDiagramReader.convertToMRef(val);
        } else if (type.startsWith("Enum:")) {
            final String enumType = type.substring(type.indexOf(':') + 1);
            try {
                return this.convertToEnum(enumType, val);
            } catch (Exception e) {
                throw new PersistenceException("Couldn't read '" +
                        val +
                        "' value of " +
                        type +
                        " type: " +
                        e.getLocalizedMessage(), e);
            }
        } else if (type.equals("ObRef")) {
            // Migration case, ObRef might be encountered instead of MRef in old dirty exml files...
            return XmlDiagramReader.convertToMRef(val);
        } else {
            throw new PersistenceException("'" + val + "' of " + type + " type is not handled.");
        }
    }

    @objid ("ed4acd97-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void readDiagram(String rawData, IPersistent root) throws PersistenceException {
        this.rootObject = root;
        
        String data;
        if (rawData.startsWith("<?xml")) {
            // old uncompressed format
            data = rawData;
        } else {
            data = org.modelio.vcore.utils.UUBase64Compressor.decompress(rawData);
        }
        
        try {
            // Create a DocumentBuilderFactory
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setXIncludeAware(false);
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
        
            // Create a DocumentBuilder
            final DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new LocalErrorHandler(System.err));
        
            // Parse
            final Document xmlDoc = db.parse(new InputSource(new StringReader(data)));
        
            // DocumentElement is the root <Object> tag
            this.currentDomElement = xmlDoc.getDocumentElement();
        
            // Register the root object
            final String nodeId = this.currentDomElement.getAttribute(SchemaConstants.ATT_OBJECT_ID);
            this.readObjects.put(nodeId, this.rootObject);
        
            // Deserialize the root object
            this.rootObject.read(this);
        
        } catch (ParserConfigurationException | SAXException | RuntimeException e) {
            throw new PersistenceException(e);
        } catch (IOException e) {
            throw new PersistenceException(FileUtils.getLocalizedMessage(e), e);
        }
    }

    @objid ("ed4d2fed-186f-11e2-92d2-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    @Override
    public <K,V> Map<K, V> readMapProperty(String mapName) throws PersistenceException {
        return (Map<K, V>) readProperty(mapName);
    }

    @objid ("ed4d2ff9-186f-11e2-92d2-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    @Override
    public <T> List<T> readListProperty(String propertyName) throws PersistenceException {
        final List<T> ret = (List<T>) readProperty(propertyName);
        if (ret != null) {
            return ret;
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("83462b50-099f-43ca-b903-0cb155e015d9")
    private String getTextNodes(Element el) {
        NodeList childrenNodes = el.getChildNodes();
        StringBuilder s = new StringBuilder();
        
        for (int i = 0; i < childrenNodes.getLength(); i++) {
            Node n = childrenNodes.item(i);
            if (n.getNodeType() == Node.TEXT_NODE || n.getNodeType() == Node.CDATA_SECTION_NODE) {
                s.append(n.getNodeValue());
            }
        }
        return s.toString();
    }

    @objid ("b446de4b-f409-4b25-bf1c-9116978330a5")
    private IPersistentMigrator createMigratorInstance(String nodeType) throws PersistenceException {
        return this.instanceFactory.createMigratorInstance(nodeType);
    }

    /**
     * XML error handler that logs errors to the given writer.
     * 
     * @author cmarin
     */
    @objid ("cb7c358f-186f-11e2-92d2-001ec947c8cc")
    private static class LocalErrorHandler implements ErrorHandler {
        @objid ("cb7c3592-186f-11e2-92d2-001ec947c8cc")
        private PrintStream report;

        @objid ("cb7c3593-186f-11e2-92d2-001ec947c8cc")
        public LocalErrorHandler(final PrintStream err) {
            this.report = err;
        }

        @objid ("cb7c3597-186f-11e2-92d2-001ec947c8cc")
        @Override
        public void error(final SAXParseException exception) throws SAXException {
            print(exception);
        }

        @objid ("cb7c359c-186f-11e2-92d2-001ec947c8cc")
        @Override
        public void fatalError(final SAXParseException exception) throws SAXException {
            print(exception);
            throw exception;
        }

        @objid ("cb7c35a1-186f-11e2-92d2-001ec947c8cc")
        @Override
        public void warning(final SAXParseException exception) throws SAXException {
            print(exception);
        }

        @objid ("cb7c35a6-186f-11e2-92d2-001ec947c8cc")
        private void print(final SAXParseException exception) {
            this.report.print(exception.getLineNumber());
            this.report.print(exception.getColumnNumber());
            this.report.print(":");
            this.report.print(exception.getLocalizedMessage());
            
            if (exception.getException() != null) {
                exception.getException().printStackTrace(this.report);
            }
        }

    }

}
