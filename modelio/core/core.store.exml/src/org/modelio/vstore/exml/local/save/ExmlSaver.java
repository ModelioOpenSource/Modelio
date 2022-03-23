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
package org.modelio.vstore.exml.local.save;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.modelio.vbasic.xml.CloseableXMLStreamWriter;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.exml.common.ExmlStorageHandler;
import org.modelio.vstore.exml.common.model.ExmlTags;
import org.modelio.vstore.exml.common.utils.ExmlUtils;

/**
 * Save a CMS node in an EXML file.
 */
@objid ("fd26ba06-5986-11e1-991a-001ec947ccaf")
public class ExmlSaver implements ExmlTags {
    @objid ("fd21f5c9-5986-11e1-991a-001ec947ccaf")
    private XMLStreamWriter out;

    /**
     * Get the parent CMS node of the given object.
     * <p>
     * If the object is itself a CMS node returns the composition owner CMS node.
     * @param object a model object
     * @return its parent CMS node.
     */
    @objid ("fd245745-5986-11e1-991a-001ec947ccaf")
    private static SmObjectImpl getParentExt(final SmObjectImpl object) {
        // Get the repository handler of the object if not a CMS node,
        // of its composition owner if it is a CMS node.
        IRepositoryObject handler ;
        if (object.getMClass().isCmsNode()) {
            SmObjectImpl parent = object.getCompositionOwner();
            if (parent == null) {
                return null;
            }
            handler = parent.getRepositoryObject();
        } else {
            handler = object.getRepositoryObject();
        }
        
        // Return the CMS node represented by the handler
        ExmlStorageHandler exmlHandler = (ExmlStorageHandler) handler;
        SmObjectImpl cmsNode = exmlHandler.getCmsNode();
        return cmsNode;
    }

    /**
     * Save the given CMS node in an output stream.
     * @param object the CMS node to save
     * @param os an output stream.
     * @throws IOException in case of failure.
     */
    @objid ("fd245740-5986-11e1-991a-001ec947ccaf")
    public void externalize(final SmObjectImpl object, final OutputStream os) throws IOException {
        // Note : http://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        // In a try-with-resources statement, any catch or finally block
        // is run after the resources declared have been closed.
        try (CloseableXMLStreamWriter closeableWriter = new CloseableXMLStreamWriter(os, INDENT_FILES)){
            this.out = closeableWriter.getW();
        
            this.out.writeStartDocument();
            this.out.writeComment("GENERATED FILE, PLEASE DO NOT EDIT!!!");
        
            dumpEXT(object);
        } catch (FactoryConfigurationError e) {
            throw new IOException(e);
        } catch (XMLStreamException e) {
            throw new IOException(e);
        } finally {
            this.out = null;
        }
        
    }

    @objid ("fd245741-5986-11e1-991a-001ec947ccaf")
    private final void dumpOBJECT(final SmObjectImpl object, Collection<SmObjectImpl> recursionContext, boolean withPid) throws XMLStreamException {
        if (recursionContext.contains(object)) {
            // Object already being externalized, skip it
            return;
        }
        
        // Process it, add it to context
        recursionContext.add(object);
        
        SmObjectImpl parent = getParentExt(object);
        
        this.out.writeStartElement(TAG_OBJECT);
        
        dumpID(TAG_ID, object);
        if (withPid && parent != null) {
            dumpID(TAG_CMSNODE_PID, parent);
        }
        
        dumpATTRIBUTES(object);
        dumpDEPENDENCIES(object, recursionContext);
        
        this.out.writeEndElement();
        
        // // Processed, remove from context
        // recursionContext.remove(object);
        
    }

    @objid ("fd245738-5986-11e1-991a-001ec947ccaf")
    private void dumpATT(final SmObjectImpl object, final SmAttribute att) throws XMLStreamException {
        this.out.writeStartElement(TAG_ATT);
        this.out.writeAttribute(ATT_NAME, att.getName());
        
        Object attVal = object.getAttVal(att);
        if (attVal == null) {
            //TODO que faire ??
        } else {
            String stringVal = attVal.toString();
            if (att.getType() == String.class) {
                if (!stringVal.isEmpty()) {
                    this.out.writeCData(getCDataForm(stringVal));
                }
            } else {
                this.out.writeCharacters(stringVal);
            }
        }
        this.out.writeEndElement();
        
    }

    @objid ("fd245735-5986-11e1-991a-001ec947ccaf")
    private void dumpATTRIBUTES(final SmObjectImpl object) throws XMLStreamException {
        this.out.writeStartElement(TAG_ATTRIBUTES);
        
        for (SmAttribute att : object.getClassOf().getAllAttDef()) {
            dumpATT(object, att);
        }
        this.out.writeEndElement();
        
    }

    @objid ("fd245734-5986-11e1-991a-001ec947ccaf")
    private void dumpCOMPS(final SmObjectImpl object, final SmDependency dep, final List<SmObjectImpl> targets, Collection<SmObjectImpl> recursionContext) throws XMLStreamException {
        this.out.writeStartElement(TAG_COMP);
        this.out.writeAttribute(ATT_RELATION, dep.getName());
        
        for (SmObjectImpl t : targets) {
            if (! ExmlUtils.sameRepository(object, t)) {
                dumpID(TAG_FOREIGNID, t);
            } else if (t.getClassOf().isCmsNode()) {
                dumpID(TAG_COMPID, t);
            } else if (recursionContext.contains(t) ) {
                // composed by value but already externalized, ref it
                dumpID(TAG_COMPID, t);
            } else if (t.getRepositoryObject() != object.getRepositoryObject()) {
                // Different handle ==> different file
                dumpREFOBJ(t);
            } else {
                // composed by value, recursive call
                dumpOBJECT( t, recursionContext, false);
            }
        }
        
        this.out.writeEndElement();
        
    }

    /**
     * Write the object SmDependencies.
     * @param object the CMS node
     * @param recursionContext to avoid cycles
     * @throws XMLStreamException in case of XML error
     */
    @objid ("fd245730-5986-11e1-991a-001ec947ccaf")
    private void dumpDEPENDENCIES(final SmObjectImpl object, Collection<SmObjectImpl> recursionContext) throws XMLStreamException {
        this.out.writeStartElement(TAG_DEPENDENCIES);
        
        List<SmDependency > dependencies = ExmlUtils.getExternalisableDeps(object);
        
        for (SmDependency dep : dependencies) {
        
            List<SmObjectImpl> content = object.getDepValList(dep);
        
            if (! content.isEmpty()) {
                if (ExmlUtils.isDepComponent(dep)) {
                    dumpCOMPS(object, dep, content, recursionContext);
                } else if (! content.isEmpty()) {
                    dumpLINKS(object, dep, content);
                }
            }
        }
        
        this.out.writeEndElement();
        
    }

    /**
     * Write the CMS node dependencies.
     * @param object
     * @throws XMLStreamException
     */
    @objid ("fd24572d-5986-11e1-991a-001ec947ccaf")
    @Deprecated
    private void dumpFileDEPS(final SmObjectImpl object) throws XMLStreamException {
        this.out.writeStartElement(TAG_DEPS);
        dumpID("ID", object);
        
        ElementDependencies deps = new DependencyAnalyzer().getDependentObjects(object);
        
        // #if 0
        //         cout << std::endl << "BEGIN list of dependency objects for: " << const_cast<SmObjectImpl&>(object).name() << std::endl;
        //
        //         for (std::set<SmObjectImpl*>::iterator it=deps.compNodes.begin(); it != deps.compNodes.end(); it++) {
        //                 SmObjectImpl o = const_cast<SmObjectImpl *>(*it);
        //                 cout << "   - composition: " << o.name() << " ramc= " << o.isRamcObject() << ", isExt=" << o.isCmsNode() << ", isCmsManaged=" << o.isCmsManaged() << std::endl;
        //         }
        //         for (std::set<SmObjectImpl*>::iterator it=deps.refNodes.begin(); it != deps.refNodes.end(); it++) {
        //                 SmObjectImpl o = const_cast<SmObjectImpl *>(*it);
        //                 cout << "   - ext ref: " << o.name() << " ramc= " << o.isRamcObject() << ", isExt=" << o.isCmsNode() << ", isCmsManaged=" << o.isCmsManaged() << std::endl;
        //         }
        //         for (std::set<SmObjectImpl*>::iterator it=deps.refDeps.begin(); it != deps.refDeps.end(); it++) {
        //                 SmObjectImpl o = const_cast<SmObjectImpl *>(*it);
        //                 cout << "   - link ref: " << o.name() << " ramc= " << o.isRamcObject() << ", isExt=" << o.isCmsNode() << ", isCmsManaged=" << o.isCmsManaged() << std::endl;
        //         }
        //         cout << std::endl << "END list of dependency objects for: " << const_cast<SmObjectImpl&>(object).name() << std::endl;
        // #endif
        
        // dump composed
        for ( MObject it:deps.compNodes ) {
            dumpID(TAG_COMPID, it);
        }
        
        // dump references nodes
        for ( MObject it:deps.refNodes) {
            dumpID(TAG_DEPS_EXTID, it);
        }
        
        // dump ext ref
        for ( MObject it:deps.extDeps) {
            dumpID(TAG_FOREIGNID, it);
        }
        
        // dump links
        for ( MObject it: deps.refDeps) {
            dumpREFOBJ(it);
        }
        
        this.out.writeEndElement();
        
    }

    /**
     * Write the file header.
     * @param object the main CMS node
     * @throws XMLStreamException in case of write error.
     */
    @objid ("fd24572a-5986-11e1-991a-001ec947ccaf")
    private void dumpEXT(final SmObjectImpl object) throws XMLStreamException {
        this.out.writeStartElement(TAG_EXT);
        this.out.writeAttribute(ATT_EXT_OBJECT, object.getName());
        this.out.writeAttribute(ATT_EXT_VERSION, Integer.toString(FORMAT_VERSION));
        //dumpFileDEPS(object);
        dumpOBJECT(object, new HashSet<SmObjectImpl>(), true);
        this.out.writeEndElement();
        
    }

    @objid ("fd21f74d-5986-11e1-991a-001ec947ccaf")
    private void dumpID(final String xmlkey, final MObject object) throws XMLStreamException {
        this.out.writeEmptyElement(xmlkey);
        
        this.out.writeAttribute(ATT_ID_NAME, Objects.toString(object.getName(), ""));
        this.out.writeAttribute(ATT_ID_MC, object.getMClass().getQualifiedName());
        this.out.writeAttribute(ATT_ID_UID, object.getUuid().toString());
        
    }

    @objid ("fd21f747-5986-11e1-991a-001ec947ccaf")
    private void dumpLINKS(final SmObjectImpl object, final SmDependency dep, final List<SmObjectImpl> targets) throws XMLStreamException {
        this.out.writeStartElement(TAG_LINK);
        this.out.writeAttribute(ATT_RELATION, dep.getName());
        for (SmObjectImpl t : targets) {
            if (!ExmlUtils.sameRepository(t, object)) {
                dumpID(TAG_FOREIGNID, t);
            } else if (t.getClassOf().isCmsNode()) {
                dumpID(TAG_ID, t);
            } else {
                dumpREFOBJ(t);
            }
        }
        this.out.writeEndElement();
        
    }

    @objid ("fd21f73c-5986-11e1-991a-001ec947ccaf")
    private void dumpREFOBJ(final MObject object) throws XMLStreamException {
        this.out.writeStartElement(TAG_REFOBJ);
        dumpID(TAG_ID, object);
        
        /*ExmlStorageHandler h = (ExmlStorageHandler) object.getRepositoryObject();
        MObject parent = h.getCmsNode();
        if (parent != null) {
            // TODO This information is not accurate over time, to be removed and no tool should rely on it.
            dumpID(TAG_PID, parent);
        } else {
            throw new XMLStreamException(object+" is not in a CMS node.");
        }*/
        this.out.writeEndElement();
        
    }

    /**
     * Split all CDATA end tags in 2 CDATA sections to avoid XML parse error.
     * This code must be obfuscated to avoid being itself broken on Modelio EXML save
     * in the case it wouldn't work.
     * <p><code>
     * "&#x5d; &#x5d;&gt;" <b>---></b> "]"(1) <b>+</b> "&#x5d; &#x5d;&gt;&lt;![CDATA[" <b>+</b> "]&gt;"(2)
     * </code>
     * @param aString a future CDATA string
     * @return a CDATA ready string
     */
    @objid ("fd21f739-5986-11e1-991a-001ec947ccaf")
    private static String getCDataForm(String aString) {
        // split all CDATA end tags in 2 CDATA sections to avoid XML parse error
        // This code must be obfuscated to avoid being itself broken on Modelio EXML save.
        // "&#x5d; &#x5d;&gt;" --> "]"(1) + "&#x5d; &#x5d;&gt;<![CDATA[" + "]>"(2)
        return aString.replace("]"+"]"+">", "]"+"]]"+"><![CDATA["+"]>");
    }

}
