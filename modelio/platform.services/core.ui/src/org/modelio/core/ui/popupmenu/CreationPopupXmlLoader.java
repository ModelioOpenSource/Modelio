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

package org.modelio.core.ui.popupmenu;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.plugin.CoreUi;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Parser class for element creation popup.
 */
@objid ("57d2012e-8c00-4ed6-a753-b9e18cdd3dfe")
class CreationPopupXmlLoader {
    @objid ("f27b0708-f5b1-4c9e-b0e6-12a1eb0ef48d")
    private Map<String, List<IPopupEntryDescriptor>> popupEntries;

    @objid ("fb4ce079-bbe7-46e5-9e24-2556d101da22")
    public Map<String, List<IPopupEntryDescriptor>> parseCreationPopupEntries(final URL url) {
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
            CoreUi.LOG.debug(e);
        }
        return this.popupEntries;
    }

    @objid ("3d87bbbd-8dec-4e56-8ca9-c50baf1b850c")
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

    @objid ("e9649dfb-ccc0-402f-a311-5c741bf7bf4f")
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
                registerPopupEntry(metaclassName, new SeparatorPopupEntryDescriptor());
            }
        }
    }

    /**
     * Add a popup entry to display on a specific metaclass.
     * 
     * @param sourceMetaclass The metaclass to display the popup for.
     * @param item the entry to add.
     */
    @objid ("5540de8a-5dc5-4b11-bc89-e545b2c2d2f0")
    private void registerPopupEntry(final String sourceMetaclass, final IPopupEntryDescriptor item) {
        if (!this.popupEntries.containsKey(sourceMetaclass)) {
            this.popupEntries.put(sourceMetaclass, new ArrayList<IPopupEntryDescriptor>());
        }
        
        this.popupEntries.get(sourceMetaclass).add(item);
    }

    @objid ("ec98855c-bed4-4a41-ac4f-d618c0bc1178")
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

    @objid ("05db306d-3b26-4c7b-88ad-4d11b0075783")
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
