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
package org.modelio.vcore.model.spi.mm;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;
import org.modelio.vcore.utils.UUBase64Compressor;

/**
 * Migrates references written in the diagram data string.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("6bde6636-3d16-4c61-9175-e04fee59e60d")
class MofDiagramMigrator {
    @objid ("998700e6-0be5-4b03-9786-8ad4f547a9d0")
    private final Map<MRef, String> mapping = new HashMap<>();

    @objid ("9407fea7-1e02-4e23-b9a0-a610f2bfb438")
    private final IMofSession mofSession;

    @objid ("b57cc7bc-80aa-4068-a4d7-9a1b1192f6e8")
    private final XMLInputFactory xInputFactory;

    @objid ("9456d4de-641b-4aea-aff2-07a450823f65")
    private final XMLOutputFactory xOutputFactory;

    @objid ("58e9d430-f780-448e-a6d6-b7f1f7ad2a39")
    private final XMLEventFactory xEventFactory;

    @objid ("159c3452-b493-4680-a0bc-1ff3e31d19f5")
    private final PrintWriter logger;

    /**
     * @param mofSession a MOF session.
     */
    @objid ("9d2a8a62-ab40-4bb1-ba45-ba3881216b70")
    public  MofDiagramMigrator(IMofSession mofSession) {
        this.mofSession = mofSession;
        this.xInputFactory = XMLInputFactory.newInstance();
        this.xOutputFactory = XMLOutputFactory.newInstance();
        this.xEventFactory = XMLEventFactory.newFactory();
        this.logger = this.mofSession.getReport().getLogger();
        
    }

    @objid ("42ae550b-3bd6-44f5-8ef6-df3f7b48a0d0")
    public void run(SubProgress monitor) throws MofMigrationException {
        if (this.mapping.isEmpty()) {
            return;
        }
        
        this.logger.printf(" Migrating diagram content references...\n");
        
        // find all diagram metaclasses
        Collection<MClass> diagramMetaclasses = new HashSet<>();
        for (SmClass mc : this.mofSession.getMetamodel().getRegisteredMClasses()) {
            if ("AbstractDiagram".equals(mc.getName())) {
                diagramMetaclasses.addAll(mc.getSub(true));
            }
        }
        
        this.logger.printf("    Diagram metaclasses: %s\n", diagramMetaclasses);
        
        // Iterate all diagram instances
        for (MClass diagMc : diagramMetaclasses) {
            if (!diagMc.isAbstract()) {
                for (MofSmObjectImpl diag : this.mofSession.findByClass(diagMc, false)) {
                    migrateDiagram(diag);
                    monitor.worked(1);
                    monitor.setWorkRemaining(5);
                }
            }
        }
        
    }

    @objid ("382aeee5-484d-4565-a1a7-ff8361d99151")
    private void migrateDiagram(MofSmObjectImpl diag) throws MofMigrationException {
        this.logger.printf("     Processing %s ...\n", diag);
        
        String rawData = (String) diag.getAtt("UiData");
        
        if (rawData == null || rawData.isEmpty()) {
            this.logger.printf("       %s has no data.\n", diag);
            return;
        }
        
        if (!rawData.startsWith("<?xml")) {
            // compressed format
            rawData = UUBase64Compressor.decompress(rawData);
            if (rawData == null) {
                // Let it fail later
                rawData = "";
            }
        }
        StringWriter outw = new StringWriter(rawData.length());
        
        boolean changeDone = false;
        try {
            changeDone = convertDiagramContent(diag, rawData, outw);
        } catch (XMLStreamException e) {
            this.logger.printf("      Failed migrating '%s' at %s: %s\n", diag, toString(e.getLocation()), e.getMessage());
            this.logger.printf("      '%s' data is :\n%s\n--------\n", diag, rawData.replace("\n", "\n      "));
            throw new MofMigrationException(toString(e.getLocation()) + ": " + e.getLocalizedMessage(), e);
        }
        
        if (changeDone) {
            String newXml = outw.toString();
            // this.logger.printf(" %s : ui data rewriten to :\n%s\n--------\n", diag, newXml.replace("\n", "\n "));
            String newDiagData = UUBase64Compressor.compress(newXml);
            diag.setAttVal("UiData", newDiagData);
            this.logger.printf("      %s diagram references migrated.\n", diag);
        }
        
    }

    @objid ("7b31090f-c6d4-4f36-8099-978346b0b245")
    private String getMigratedRefVal(String strMref) {
        return this.mapping.getOrDefault(convertToMRef(strMref), strMref);
    }

    /**
     * Copied from {@link org.modelio.diagram.persistence.XmlDiagramReader#convertToMRef(String)}
     * @param val
     * @return the MRef
     */
    @objid ("d0164a92-3659-407b-b040-aff73983ba52")
    private static String convertToString(MRef value) {
        return value.mc + " " + value.uuid;
    }

    /**
     * Add an identifier to change to another
     * @param orig the original reference.
     * @param newRef the replacement reference .
     */
    @objid ("1423a067-ca79-4c56-9d99-775d25b67dc4")
    public void addMapping(MRef orig, MRef newRef) {
        this.mapping.put(orig, convertToString(newRef));
    }

    @objid ("62393645-be03-40d6-8eb2-ec289454f54b")
    private boolean convertDiagramContent(MofSmObjectImpl diag, String content, StringWriter outw) throws XMLStreamException {
        XMLEventReader reader = this.xInputFactory.createXMLEventReader(new StringReader(content));
        XMLEventWriter writer = this.xOutputFactory.createXMLEventWriter(outw);
        
        boolean isRewrittenMRefState = false;
        boolean changeDone = false;
        
        try (XmlCloser c1 = reader::close; XmlCloser c2 = writer::close;) {
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (isRewrittenMRefState) {
                    if (event.getEventType() == XMLStreamConstants.ATTRIBUTE) {
                        // skip all attributes, already handled by START_ELEMENT
                    } else if (event.getEventType() == XMLStreamConstants.END_ELEMENT) {
                        // exit <Value ...> tag
                        isRewrittenMRefState = false;
                    } else {
                        writer.add(event);
                    }
                } else if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    StartElement startElement = event.asStartElement();
                    if ("Value".equals(startElement.getName().getLocalPart())) {
                        Attribute typeAtt = startElement.getAttributeByName(new QName("type"));
                        Attribute valAtt = startElement.getAttributeByName(new QName("value"));
                        if (typeAtt != null && valAtt != null && "MRef".equals(typeAtt.getValue())) {
                            // <Value type='MRef' value='Metaclass uuid'/>
                            String oldValue = valAtt.getValue();
                            String migratedRefVal = getMigratedRefVal(oldValue);
                            if (!Objects.equals(oldValue, migratedRefVal)) {
                                changeDone = true;
        
                                Attribute migratedAtt = this.xEventFactory.createAttribute("value", migratedRefVal);
        
                                StartElement ev2 = this.xEventFactory.createStartElement(
                                        startElement.getName(),
                                        Arrays.asList(typeAtt, migratedAtt).iterator(),
                                        startElement.getNamespaces());
                                writer.add(ev2);
                                isRewrittenMRefState = true;
                            }
                        } else {
                            // log problem
                            if (typeAtt == null) {
                                this.logger.printf("    %s @ %s: 'type' attribute missing in %s.\n", diag, toString(startElement.getLocation()), startElement);
                            } else if ("MRef".equals(typeAtt.getValue())) {
                                this.logger.printf("    %s @ %s: 'value' attribute missing in %s.\n", diag, toString(startElement.getLocation()), startElement);
                            }
        
                        }
                    }
                }
                if (!isRewrittenMRefState) {
                    writer.add(event);
                }
            }
        }
        return changeDone;
    }

    @objid ("3b91c3cc-cacc-4d56-81cd-cc0bf40b4617")
    private static String toString(Location loc) {
        if (loc == null) {
            return "";
        } else {
            return "l" + loc.getLineNumber() + " c" + loc.getColumnNumber();
        }
        
    }

    @objid ("1ea2e85b-5739-41be-9fc7-a6e579eb5872")
    private static MRef convertToMRef(String val) {
        int p = val.indexOf(' ');
        return new MRef(val.substring(0, p), val.substring(p + 1));
    }

    /**
     * {@link AutoCloseable} function to close an {@link XMLEventReader} or {@link XMLEventWriter}.
     * 
     * @author cma
     * @since 3.7
     */
    @objid ("10a7136f-5c08-49c9-8f5e-54d6b23e74c5")
    @FunctionalInterface
    private interface XmlCloser extends AutoCloseable {
        @objid ("814bd943-79e9-4d26-9d72-31e950e85fae")
        @Override
        void close() throws XMLStreamException;
}
    

}
