/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.audit.preferences.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.service.AuditSeverity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@objid ("ea314cb0-dedb-4a14-bd99-53f94c4b274c")
public class AuditCategoryBuilder {
    @objid ("6e8f1cf7-0131-4063-a7b1-914a6f04ab96")
    public static List<AuditCategory> parseCategories(final File xmlFile) {
        List<AuditCategory> rootCategories = new ArrayList<>();
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(xmlFile);
        
            // Get the configuration
            final Element configurationElement = document.getDocumentElement();
            if (configurationElement.getNodeType() != Node.ELEMENT_NODE || !configurationElement.getNodeName().equals("Configuration")) {
                return null;
            }
        
            final NodeList rootNodes = configurationElement.getChildNodes();
            for (int i = 0; i < rootNodes.getLength(); i++) {
                Node item = rootNodes.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE && item.getNodeName().equals("Category")) {
                    final Element categoryElement = (Element) item;
                    String categoryId = categoryElement.getAttribute("id");
        
                    AuditCategory subCategory = new AuditCategory(categoryId);
                    rootCategories.add(subCategory);
        
                    NodeList rules = categoryElement.getElementsByTagName("Rule");
                    for (int j = 0; j < rules.getLength(); j++) {
                        final Element ruleElement = (Element) rules.item(j);
        
                        String ruleId = ruleElement.getAttribute("id");
                        AuditSeverity severity = AuditSeverity.fromIdentifier(ruleElement.getAttribute("severity"));
                        boolean enabled = "enabled".equals(ruleElement.getAttribute("status"));
                        String implClass = ruleElement.getAttribute("class");
                        AuditRule rule = new AuditRule(ruleId, severity, enabled, implClass);
                        subCategory.add(rule);
                    }
                }
            }
        
        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return rootCategories;
    }

}
