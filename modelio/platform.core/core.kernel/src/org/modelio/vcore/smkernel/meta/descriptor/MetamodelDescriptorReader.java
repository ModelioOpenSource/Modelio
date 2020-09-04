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

package org.modelio.vcore.smkernel.meta.descriptor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.FileSystemException;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Read a {@link MetamodelDescriptor} from an XML source.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("94a2c9a3-9a00-446e-9be9-8236567b3786")
public class MetamodelDescriptorReader {
    @objid ("9ebf1106-a778-4957-b3c8-9762d326eb3f")
    private MetamodelDescriptor mmDesc;

    /**
     * Read a project descriptor from an XML input source.
     * 
     * @param in an input stream.
     * @param location The XML source {@link InputSource#getSystemId() system id}
     * @return the read descriptor.
     * @throws java.io.IOException in case of failure
     */
    @objid ("5454bc4f-2ace-4a51-ba57-10e810c3c383")
    public static MetamodelDescriptor readFrom(InputStream in, String location) throws IOException {
        InputSource src = new InputSource(in);
        src.setSystemId(location);
        return readFrom(src);
    }

    /**
     * Read a project descriptor from an XML input source.
     * 
     * @param src the XML input source.
     * @return the read descriptor.
     * @throws java.io.IOException in case of failure
     */
    @objid ("435ad7ec-4665-41b8-948f-227e6c31cb57")
    public static MetamodelDescriptor readFrom(InputSource src) throws IOException {
        return new MetamodelDescriptorReader().read(src);
    }

    /**
     * Read a project descriptor from an XML input source.
     * 
     * @param is the XML input source.
     * @return the read descriptor.
     * @throws java.io.IOException in case of failure
     */
    @objid ("b332bb6b-0cc6-4753-afe7-8ebb96e3e5fc")
    private MetamodelDescriptor read(final InputSource is) throws IOException {
        this.mmDesc = new MetamodelDescriptor();
        
        String confLocation = is.getSystemId();
        try  {
            
            Document dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        
            decodeMetamodel(dom.getDocumentElement());
        
        } catch (FileNotFoundException | FileSystemException e) {
            throw e;
        } catch (IOException e) {
            throw new IOException("Parsing of '" + confLocation + "' failed: " + FileUtils.getLocalizedMessage(e), e);
        } catch (RuntimeException e) {
            throw new IOException("Parsing of '" + confLocation + "' unexpectedly failed: " + e.toString(), e);
        } catch (SAXException e) {
            throw new IOException("Parsing of '" + confLocation  + "' failed: " + e.getLocalizedMessage(), e);
        } catch (ParserConfigurationException e) {
            throw new IOException("Parsing of '" + confLocation  + "' failed: " + e.getLocalizedMessage(), e);
        }
        return this.mmDesc;
    }

    @objid ("b920a35f-2bd0-4ab5-aae9-f1a53c77da65")
    private Iterable<Element> getChildren(Element el, String name) {
        return new NamedChildrenIterable(el, name);
    }

    @objid ("bf117c65-18e7-4e82-a98d-38f419e606d0")
    private MetamodelFragmentDescriptor decodeFragment(final Element domEl) throws SAXException {
        MetamodelFragmentDescriptor fd = new MetamodelFragmentDescriptor();
        
        fd.setName(domEl.getAttribute("name"));
        fd.setVersion(decodeVersion(domEl.getAttribute("version")));
        fd.setProvider(domEl.getAttribute("provider"));
        fd.setProviderVersion(domEl.getAttribute("providerVersion"));
        fd.setFake(parseBooleanAtt(domEl, "fake", false));
        
        for (Element section : getChildren(domEl, "dependencies")) {
            for (Element mel : getChildren(section, "metamodel_fragment")) {
                fd.getDependencies().add(decodeVersionedItem(mel));
            }
        }
        
        for (Element section : getChildren(domEl, "metaclasses")) {
            for (Element mel : getChildren(section, "metaclass")) {
                fd.getMetaclasses().add(decodeMetaclass(mel));
            }
            
            for (Element mel : getChildren(section, "link_metaclass")) {
                fd.getMetaclasses().add(decodeLinkMetaclass(mel));
            }
        }
        
        for (Element section : getChildren(domEl, "enumerations")) {
            for (Element mel : getChildren(section, "enumeration")) {
                fd.getEnumerations().add(decodeEnumeration(mel));
            }
        }
        
        // needed to make MMetamodelFragmentDescriptor.equals() work
        Collections.sort(fd.getMetaclasses(), (a,b) -> a.getName().compareTo(b.getName()));
        Collections.sort(fd.getEnumerations(), (a,b) -> a.getName().compareTo(b.getName()));
        return fd;
    }

    @objid ("73d6f334-5fed-4db2-967a-324722172e91")
    private MClassDescriptor decodeLinkMetaclass(Element domEl) throws SAXException {
        MLinkMetaclassDescriptor mc = new MLinkMetaclassDescriptor();
        
        decodeMetaclassContent(domEl, mc);
        
        for (Element section : getChildren(domEl, "sources")) {
            for (Element refEl : getChildren(section, "dep")) {
                mc.getSourceDepencencies().add(refEl.getAttribute("name"));
            }
        }
        
        for (Element section : getChildren(domEl, "targets")) {
            for (Element refEl : getChildren(section, "dep")) {
                mc.getTargetDepencencies().add(refEl.getAttribute("name"));
            }
        }
        return mc;
    }

    @objid ("d83ade1d-e370-41ac-a269-bdc1b63de810")
    private void decodeMetaclassContent(Element domEl, MClassDescriptor mc) throws SAXException {
        mc.setName(domEl.getAttribute("name"));
        mc.setVersion(decodeVersion(domEl.getAttribute("version")));
        mc.setAbstrakt(parseBooleanAtt(domEl,"abstract", false));
        mc.setCmsNode(parseBooleanAtt(domEl,"cmsNode", false));
        mc.setFake(parseBooleanAtt(domEl, "fake", false));
        
        for (Element el : getChildren(domEl, "parent")) {
            mc.setParent(decodeMetaclassRef(el));
        }
        
        for (Element attEl : getChildren(domEl, "attribute")) {
            mc.getAttributes().add(decodeAttribute(attEl));
        }
        
        for (Element depEl : getChildren(domEl, "dependency")) {
            mc.getDependencies().add(decodeDepencency(depEl));
        }
    }

    @objid ("7eaf933b-aba6-44d2-8ee5-1f0afacb0591")
    private MAttributeDescriptor decodeAttribute(Element domEl) throws SAXException {
        MAttributeDescriptor d = new MAttributeDescriptor();
        d.setName(domEl.getAttribute("name"));
        String typeDef = domEl.getAttribute("type");
        try {
            d.setType(Class.forName(typeDef));
        } catch (ClassNotFoundException e) {
            throw new SAXException(String.format("'%s' attribute has unknown '%s' type.", d.getName(), typeDef), e);
        }
        
        if (d.getType() == Enum.class) {
            d.setEnumType(domEl.getAttribute("enumType"));
        }
        return d;
    }

    @objid ("8e759235-e509-4049-b5b0-421a4d9b2525")
    private MDependencyDescriptor decodeDepencency(Element domEl) {
        MDependencyDescriptor d = new MDependencyDescriptor();
        
        d.setName(domEl.getAttribute("name"));
        d.setMin(Integer.parseInt(domEl.getAttribute("min")));
        d.setMax(Integer.parseInt(domEl.getAttribute("max")));
        d.setAggregation(parseAggregation(domEl.getAttribute("aggregation")));
        d.setCascadeDelete(parseBooleanAtt(domEl,"cascadeDelete", false));
        d.setNavigate(parseBooleanAtt(domEl, "navigate", false));
        d.setWeakReference(parseBooleanAtt(domEl, "weakReference", false));
        
        for (Element oppEl : getChildren(domEl, "opposite")) {
            d.setOppositeName(oppEl.getAttribute("name"));
        }
        
        for (Element tel : getChildren(domEl, "target")) {
            d.setTarget(decodeMetaclassRef(tel));
        }
        return d;
    }

    @objid ("600e7974-190c-48c5-93a3-fdb8e6a175bc")
    private static boolean parseBooleanAtt(Element domEl, String attName, boolean defaultVal) {
        String attVal = domEl.getAttribute(attName);
        if (isEmpty(attVal)) {
            return defaultVal;
        } else {
            return Boolean.parseBoolean(attVal);
        }
    }

    @objid ("648e0c28-a7c9-423a-9e57-8a75ee1e0453")
    private static boolean isEmpty(String s) {
        return s==null || s.isEmpty();
    }

    @objid ("e98631ff-0d38-43c6-9344-8df132352916")
    private static MAggregation parseAggregation(String val) {
        if (val.isEmpty()) {
            return MAggregation.None;
        } else {
            return MAggregation.valueOf(val);
        }
    }

    @objid ("6516747d-5987-4931-a5fb-27427421738b")
    private MClassRef decodeMetaclassRef(Element domEl) {
        MClassRef r = new MClassRef();
        r.setClassName(domEl.getAttribute("name"));
        r.setFragmentName(domEl.getAttribute("fragment"));
        return r;
    }

    @objid ("2210770d-d5a3-4da5-adf2-3730bc3a9810")
    private MClassDescriptor decodeMetaclass(Element domEl) throws SAXException {
        MClassDescriptor mc = new MClassDescriptor();
        
        decodeMetaclassContent(domEl, mc);
        return mc;
    }

    @objid ("9213d49b-3393-44d2-ad5f-9812e813ee8d")
    private Version decodeVersion(String val) {
        return new Version(val);
    }

    @objid ("ef90a773-dc47-4351-bda5-5ce7d83d1cd3")
    private void decodeMetamodel(final Element p) throws SAXException {
        checkNodeName(p, "metamodel");
        
        // Check file format version
        String version = p.getAttribute("format");
        long lversion = Long.parseLong(version);
        if (lversion != MetamodelDescriptorWriter.FORMAT) {
            throw new SAXException(String.format("File format %d not supported, last supported format is %d.", lversion, MetamodelDescriptorWriter.FORMAT));
        }
        
        // Check descriptor format version
        version = p.getAttribute("MetamodelDescriptor.format");
        lversion = Long.parseLong(version);
        if (lversion != MetamodelDescriptor.serialVersionUID) {
            throw new SAXException(String.format("Descriptor format %d not supported, last supported format is %d.", lversion, MetamodelDescriptor.serialVersionUID));
        }
        
        // Read fragments
        for (Element f : getChildren(p, "fragment")) {
            this.mmDesc.addFragment(decodeFragment(f));
        }
    }

    /**
     * Read a project descriptor from an XML string.
     * 
     * @param str XML in a string.
     * @return the read descriptor.
     * @throws java.io.IOException in case of failure
     */
    @objid ("d3e1dd91-4f4b-45bf-8e9d-7465ad29f2d5")
    public static MetamodelDescriptor readFrom(String str) throws IOException {
        InputSource src = new InputSource(new StringReader(str));
        src.setSystemId("local string");
        return readFrom(src);
    }

    @objid ("4e997bed-fb22-49c2-96ee-90bd33d8cab4")
    private VersionedItem<?> decodeVersionedItem(Element el) {
        return new VersionedItem<>(
                                                                el.getAttribute("name"), 
                                                                new Version(el.getAttribute("version")));
    }

    @objid ("68599bf3-3e42-4ceb-967d-2defcc303867")
    private MEnumDescriptor decodeEnumeration(Element domEl) {
        MEnumDescriptor fd = new MEnumDescriptor();
        fd.setName(domEl.getAttribute("name"));
        
        for (Element el : getChildren(domEl, "value")) {
            fd.getValues().add(el.getAttribute("name"));
        }
        return fd;
    }

    @objid ("fb7a77f2-3350-47b0-a507-607893f3b29e")
    private void checkNodeName(final Element p, String expectedName) throws SAXException {
        if (!p.getNodeName().equals(expectedName)) {
            throw new SAXException(String.format("XML node is '%s' instead of '%s'", p.getNodeName(), expectedName));
        }
    }

    @objid ("d19c7e1c-008f-4a47-8702-ce527cd9c975")
    private static class NamedChildrenIterable implements Iterable<Element> {
        @objid ("865252ae-67d1-4dc8-8e93-75b77b4f638a")
        private final String name;

        @objid ("9f3704d4-d8c4-4e80-8352-f4972a1a46dd")
        private Element el;

        @objid ("2e3c9908-97e6-4b67-a348-d41cf1009af1")
        public NamedChildrenIterable(Element el, String name) {
            this.el = el;
            this.name = name;
        }

        @objid ("450ba4ec-d48b-4f4f-94bf-e58eb830e533")
        @Override
        public Iterator<Element> iterator() {
            return new NamedChildrenIterator(this.el, this.name);
        }

    }

    @objid ("2b065965-0cf8-438d-9538-f4b1e0468f92")
    private static class NamedChildrenIterator implements Iterable<Element>, Iterator<Element> {
        @objid ("a368fe92-179c-490f-81b6-9692208491b2")
        private final String name;

        @objid ("37e4652a-cc66-4c43-9c08-cba78ffa4483")
        private Node current;

        @objid ("9bd76899-ea3a-4318-a7ce-9243409b613b")
        public NamedChildrenIterator(Element el, String name) {
            this.name = name;
            this.current = el.getFirstChild();
            
            lookForValid();
        }

        @objid ("54dd68d2-e7db-4486-b0c7-3d348565358c")
        @Override
        public boolean hasNext() {
            return isValid();
        }

        @objid ("c0968a78-38ea-493c-934d-d473e9fa7e18")
        @Override
        public Element next() {
            if (! isValid()) {
                throw new NoSuchElementException();
            }
            Element next = (Element) this.current;
            
            this.current = this.current.getNextSibling();
            lookForValid();
            return next;
        }

        @objid ("2a1fa00d-c333-481f-aff9-4b383fd50f52")
        private void lookForValid() {
            while (this.current != null && ! isValid()) {
                this.current = this.current.getNextSibling();
            }
        }

        @objid ("9fdd8b5a-279c-4bde-9d0e-da37fb6b6ccc")
        private boolean isValid() {
            return this.current != null && this.current.getNodeType() == Node.ELEMENT_NODE
                                                                                                                    && this.current.getNodeName().equals(this.name);
        }

        @objid ("4c6fd4b4-d5c1-4fd8-a9b8-3fb29c0574d5")
        @Override
        public Iterator<Element> iterator() {
            return this;
        }

    }

}
