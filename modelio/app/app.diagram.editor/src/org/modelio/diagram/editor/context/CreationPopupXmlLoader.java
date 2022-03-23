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
package org.modelio.diagram.editor.context;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Parser class for element creation popup.
 */
@objid ("ab528060-efdf-4ae3-bc2b-6a80e14bdb7c")
class CreationPopupXmlLoader {
    @objid ("2b0feb3b-a430-4df3-a831-b5721c086970")
    private Map<String, List<CreationPopupEntryDescriptor>> popupEntries;

    @objid ("9d71eb98-4fed-4f98-b7e4-3ecb77225396")
    public Map<String, List<CreationPopupEntryDescriptor>> parseCreationPopupEntries(final URL url) {
        this.popupEntries = new HashMap<>();
        
        try (InputStream xmlStream = url.openStream()) {
            // Create a DocumentBuilderFactory
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setXIncludeAware(false);
            // dbf.setSchema(schema);
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
        
            // Create a DocumentBuilder
            final DocumentBuilder db = dbf.newDocumentBuilder();
        
            // Parse
            // db.setErrorHandler(parserAPIUsage);
            final Document xmlDoc = db.parse(xmlStream);
            final Element rootElement = xmlDoc.getDocumentElement();
            parseMenus(rootElement);
            xmlStream.close();
        } catch (final ParserConfigurationException | SAXException | IOException e) {
            DiagramEditor.LOG.debug(e);
        }
        return this.popupEntries;
    }

    @objid ("ea3c58cb-72d7-4e0b-9559-1104662ded05")
    private void parseMenus(final Element rootElement) {
        // Explore nodes under the root node:
        final NodeList childNodes = rootElement.getChildNodes();
        
        final int nNodes = childNodes.getLength();
        for (int i = 0; i < nNodes; i++) {
            final Node node = childNodes.item(i);
            if (node.getNodeName().equals("popup")) {
                parsePopupNode(node);
            }
        }
        
    }

    @objid ("ed7e838c-6313-4194-a697-caded32ad654")
    private void parsePopupNode(final Node popupNode) {
        // Get the metaclass name
        final NamedNodeMap attributes = popupNode.getAttributes();
        String metaclassName = "";
        String stereotypeName = "";
        
        // Get source metaclass and stereotype
        if (attributes != null) {
            final Node metaclassNode = attributes.getNamedItem("metaclass");
            if (metaclassNode != null) {
                metaclassName = metaclassNode.getTextContent();
            }
            final Node stereotypeNode = attributes.getNamedItem("stereotype");
            if (stereotypeNode != null) {
                stereotypeName = stereotypeNode.getTextContent();
            }
        }
        
        // Explore items and menus
        final NodeList menuNodes = popupNode.getChildNodes();
        final int nNodes = menuNodes.getLength();
        for (int i = 0; i < nNodes; i++) {
            final Node node = menuNodes.item(i);
        
            if (node.getNodeName().equals("command")) {
                final CreationPopupEntryDescriptor entryDescriptor = parseCommandNode(node);
                entryDescriptor.sourceMetaclass = metaclassName;
                entryDescriptor.sourceStereotype = stereotypeName;
        
                registerPopupEntry(metaclassName, entryDescriptor);
            } else if (node.getNodeName().equals("separator")) {
                // Use an empty popup entry for separators
                registerPopupEntry(metaclassName, new CreationPopupEntryDescriptor());
            }
        
        }
        
    }

    /**
     * Add a popup entry to display on a specific metaclass.
     * @param sourceMetaclass The metaclass to display the popup for.
     * @param item the entry to add.
     */
    @objid ("e42ce501-75f8-44ae-bd3a-500b71c69602")
    private void registerPopupEntry(final String sourceMetaclass, final CreationPopupEntryDescriptor item) {
        if (!this.popupEntries.containsKey(sourceMetaclass)) {
            this.popupEntries.put(sourceMetaclass, new ArrayList<CreationPopupEntryDescriptor>());
        }
        
        this.popupEntries.get(sourceMetaclass).add(item);
        
    }

    @objid ("2bb01469-c094-470a-a3b3-0f575e69e44f")
    private CreationPopupEntryDescriptor parseCommandNode(final Node itemNode) {
        final CreationPopupEntryDescriptor entryDescriptor = new CreationPopupEntryDescriptor();
        
        final NamedNodeMap attributes = itemNode.getAttributes();
        String id = "";
        
        // get command id
        if (attributes != null) {
            final Node nameNode = attributes.getNamedItem("id");
            if (nameNode != null) {
        
                id = nameNode.getTextContent();
            }
        }
        entryDescriptor.commandId = id;
        
        // parse command parameters
        final NodeList childNodes = itemNode.getChildNodes();
        final int nNodes = childNodes.getLength();
        for (int i = 0; i < nNodes; i++) {
            final Node node = childNodes.item(i);
            if (node.getNodeName().equals("parameter")) {
                parseParameterNode(node, entryDescriptor);
            }
        }
        return entryDescriptor;
    }

    @objid ("f532f5d6-6cf5-4523-951d-10187adfd8d7")
    private void parseParameterNode(final Node parameterNode, final CreationPopupEntryDescriptor entryDescriptor) {
        final NamedNodeMap attributes = parameterNode.getAttributes();
        String name = "";
        String value = "";
        
        if (attributes != null) {
            final Node nameNode = attributes.getNamedItem("name");
            if (nameNode != null) {
                name = nameNode.getTextContent();
            }
            final Node valueNode = attributes.getNamedItem("value");
            if (valueNode != null) {
                value = valueNode.getTextContent();
            }
            entryDescriptor.parameters.put(name, value);
        }
        
    }

}
