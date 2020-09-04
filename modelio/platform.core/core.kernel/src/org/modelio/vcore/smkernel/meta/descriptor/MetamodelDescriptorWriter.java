/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.vcore.smkernel.meta.descriptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vbasic.xml.CloseableXMLStreamWriter;

/**
 * Serialize a {@link MetamodelDescriptor} as XML.
 * @author cma
 * @since 3.6
 */
@objid ("ee0b2793-6748-496e-8485-8bd81e8338b1")
public class MetamodelDescriptorWriter {
    /**
     * XML file format version.
     */
    @objid ("14402b91-d2ac-4ebd-b24d-c95d2b4e2dad")
     static final int FORMAT = 1;

    @objid ("10994b1f-a42d-4363-96cf-6290441d5112")
    private XMLStreamWriter out;

    /**
     * @param d the descriptor to write
     * @param os the output stream
     * @throws java.io.IOException on failure.
     */
    @objid ("b9678eb0-b022-4b5f-982d-40ba06d483e2")
    public void write(final MetamodelDescriptor d, final OutputStream os) throws IOException {
        try (CloseableXMLStreamWriter cout = new CloseableXMLStreamWriter(os, true)) {
            this.out = cout.getW();
            this.out.writeStartDocument(StandardCharsets.UTF_8.name(), "1.0");
            //this.out.writeComment("GENERATED FILE, PLEASE DO NOT EDIT!!!");
        
            write(d);
        
            this.out.writeEndDocument();
        
        } catch (FactoryConfigurationError e) {
            throw new IOException(e);
        } catch (XMLStreamException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        } finally {
            this.out = null;
        }
    }

    @objid ("3a1ada36-56a2-4ac9-9141-50e894e09502")
    private void write(MetamodelDescriptor d) throws XMLStreamException {
        this.out.writeStartElement("metamodel");
        this.out.writeAttribute("format", String.valueOf(FORMAT));
        this.out.writeAttribute("MetamodelDescriptor.format", String.valueOf(MetamodelDescriptor.serialVersionUID));
        
        for (MetamodelFragmentDescriptor f : d.getFragments().values()) {
            writeFragment (f);
        }
        
        this.out.writeEndElement();
    }

    @objid ("b372ccaa-c8f1-4b1d-b43e-d39fbe9a9e78")
    private void writeFragment(MetamodelFragmentDescriptor f) throws XMLStreamException {
        this.out.writeStartElement("fragment");
        this.out.writeAttribute("name", f.getName());
        this.out.writeAttribute("version", f.getVersion().toString());
        this.out.writeAttribute("provider", f.getProvider());
        this.out.writeAttribute("providerVersion", f.getProviderVersion());
        writeOptionalBoolAtt("fake", f.isFake(), false);
        
        if (! f.getDependencies().isEmpty()) {
            this.out.writeStartElement("dependencies");
            for (VersionedItem<?> fragDep : f.getDependencies()) {
                this.out.writeEmptyElement("metamodel_fragment");
                this.out.writeAttribute("name", fragDep.getName());
                this.out.writeAttribute("version", fragDep.getVersion().toString());
            }
            this.out.writeEndElement();
        }
        
        this.out.writeStartElement("metaclasses");
        for (MClassDescriptor mc : f.getMetaclasses()) {
            if (mc instanceof MLinkMetaclassDescriptor) {
                writeLinkMetaclass((MLinkMetaclassDescriptor) mc);
            } else {
                writeNodeMetaclass(mc);
            }
        }
        this.out.writeEndElement();
        
        this.out.writeStartElement("enumerations");
        for (MEnumDescriptor enumDescriptor : f.getEnumerations()) {
            writeEnum(enumDescriptor);
        }
        this.out.writeEndElement();
        
        this.out.writeEndElement();
    }

    @objid ("3e3e6e12-acdb-41cf-8fa7-c51c656644a6")
    private void writeLinkMetaclass(MLinkMetaclassDescriptor mc) throws XMLStreamException {
        writeMetaclassHeader(mc, "link_metaclass");
        
        this.out.writeStartElement("sources");
        for (String depName : mc.getSourceDepencencies()) {
            this.out.writeEmptyElement("dep");
            this.out.writeAttribute("name", depName);
        }
        this.out.writeEndElement();
        
        this.out.writeStartElement("targets");
        for (String depName : mc.getTargetDepencencies()) {
            this.out.writeEmptyElement("dep");
            this.out.writeAttribute("name", depName);
        }
        this.out.writeEndElement();
        
        this.out.writeEndElement();
    }

    @objid ("f41f0e7c-5c19-4531-a259-84292ae09524")
    private void writeNodeMetaclass(MClassDescriptor mc) throws XMLStreamException {
        writeMetaclassHeader(mc, "metaclass");
        this.out.writeEndElement();
    }

    @objid ("ea91ea3e-3918-4430-a30d-c541510f28d0")
    private void writeMetaclassHeader(MClassDescriptor mc, String type) throws XMLStreamException {
        this.out.writeStartElement(type);
        this.out.writeAttribute("name", mc.getName());
        this.out.writeAttribute("version", mc.getVersion().toString());
        writeOptionalBoolAtt("abstract", mc.isAbstrakt(), false);
        writeOptionalBoolAtt("cmsNode", mc.isCmsNode(), false);
        writeOptionalBoolAtt("fake", mc.isFake(), false);
        
        writeMClassRef("parent", mc.getParent());
        
        for (MAttributeDescriptor att : mc.getAttributes()) {
            writeAttribute(att);
        }
        
        for (MDependencyDescriptor dep : mc.getDependencies()) {
            writeDependency(dep);
        }
    }

    @objid ("197eb5af-acae-43e9-8847-c06692646dcb")
    private void writeDependency(MDependencyDescriptor dep) throws XMLStreamException {
        this.out.writeStartElement("dependency");
        this.out.writeAttribute("name", dep.getName());
        this.out.writeAttribute("min", String.valueOf(dep.getMin()));
        this.out.writeAttribute("max", String.valueOf(dep.getMax()));
        
        writeAggregation(dep);
        
        this.out.writeAttribute("navigate", String.valueOf(dep.isNavigate()));
        writeOptionalBoolAtt("cascadeDelete", dep.isCascadeDelete(), false);
        writeOptionalBoolAtt("weakReference", dep.isWeakReference(), false);
        
        writeMClassRef("target", dep.getTarget());
        
        if (! isEmpty(dep.getOppositeName())) {
            this.out.writeEmptyElement("opposite");
            this.out.writeAttribute("name", dep.getOppositeName());
        }
        
        this.out.writeEndElement();
    }

    @objid ("83c564b4-8996-401f-abc5-933c3b278bea")
    private boolean isEmpty(String s) {
        return s==null || s.isEmpty();
    }

    @objid ("e4e534e5-6fd0-4712-ad57-9bbb49146c65")
    private void writeAttribute(MAttributeDescriptor att) throws XMLStreamException {
        this.out.writeStartElement("attribute");
        this.out.writeAttribute("name", att.getName());
        //this.out.writeAttribute("min", String.valueOf(att.getMin()));
        //this.out.writeAttribute("max", String.valueOf(att.getMax()));
        
        this.out.writeAttribute("type", att.getType().getName());
        writeOptionalStringAtt("enumType", att.getEnumType(), "");
        
        this.out.writeEndElement();
    }

    /**
     * Dump the metamodel descriptor to a string.
     * @param d the descriptor to write
     * @return the XML string
     */
    @objid ("dc180cd4-d354-430d-828d-1d90acd6976a")
    public static String dumpToString(final MetamodelDescriptor d) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            new MetamodelDescriptorWriter().write(d, os);
            return os.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @objid ("672c59f1-a30a-46c4-94b4-309f714107eb")
    private void writeAggregation(MDependencyDescriptor dep) throws XMLStreamException {
        if (dep.getAggregation() != MAggregation.None) {
            this.out.writeAttribute("aggregation", dep.getAggregation().name());
        }
    }

    @objid ("957ae36d-e4c8-43e0-8362-511f034d39ca")
    private void writeOptionalBoolAtt(String attName, boolean value, boolean defaultVal) throws XMLStreamException {
        if (value != defaultVal) {
            this.out.writeAttribute(attName, String.valueOf(value));
        }
    }

    @objid ("0b01ba4a-69fa-45d7-837c-b2d78b856bb2")
    private void writeMClassRef(String tagName, MClassRef ref) throws XMLStreamException {
        if (ref != null) {
            this.out.writeEmptyElement(tagName);
            this.out.writeAttribute("fragment", ref.getFragmentName());
            this.out.writeAttribute("name", ref.getClassName());
        }
    }

    @objid ("2679464a-7cd7-4275-a987-802c7927479c")
    private void writeEnum(MEnumDescriptor n) throws XMLStreamException {
        this.out.writeStartElement("enumeration");
        this.out.writeAttribute("name", n.getName());
        
        for (String v : n.getValues()) {
            this.out.writeEmptyElement("value");
            this.out.writeAttribute("name", v);
        }
        
        this.out.writeEndElement();
    }

    @objid ("a0af2bea-5b19-4d37-a23d-3e5fa633692a")
    private void writeOptionalStringAtt(String attName, String value, String defaultVal) throws XMLStreamException {
        if (isEmpty(value) != isEmpty(defaultVal) && !value.equals(defaultVal)) {
            this.out.writeAttribute(attName, String.valueOf(value));
        }
    }

}
