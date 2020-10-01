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

package org.modelio.vstore.exml.versioned.save;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.xml.CloseableXMLStreamWriter;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.exml.common.model.ExmlTags;
import org.modelio.vstore.exml.resource.IExmlResourceProvider.ExmlResource;

/**
 * Save references to non CMS managed objects in a separate stream.
 */
@objid ("7c3478c3-121b-11e2-816a-001ec947ccaf")
class LocalSaver implements AutoCloseable, ExmlTags {
    @objid ("4c07a266-1224-11e2-816a-001ec947ccaf")
    private XMLStreamWriter out;

    @objid ("4c07a267-1224-11e2-816a-001ec947ccaf")
    private SmObjectImpl currentObj;

    @objid ("1ee09b31-358e-11e2-a87b-001ec947ccaf")
    private CloseableXMLStreamWriter closeableWriter;

    @objid ("85899668-99a5-4d7f-a2fc-4e841cfd3e8e")
    private SmObjectImpl cmsNode;

    @objid ("d8f0d0c5-015c-42aa-8a84-537d6b617b66")
    private ExmlResource localResource;

    @objid ("7deda1eb-d0d2-4040-802a-c892b9a5680d")
    private OutputStream localOs;

    @objid ("4c07a269-1224-11e2-816a-001ec947ccaf")
    public LocalSaver(ExmlResource localResource) {
        this.localResource = localResource;
    }

    @objid ("4c07a26d-1224-11e2-816a-001ec947ccaf")
    @Override
    public void close() throws XMLStreamException, IOException {
        if (this.closeableWriter != null) {
            try (OutputStream lOs = this.localOs; CloseableXMLStreamWriter c = this.closeableWriter){
                if (this.currentObj != null) {
                    this.out.writeEndElement();
                }
        
                this.out.writeEndDocument();
            } 
        } else {
            // Delete any existing local file
            this.localResource.delete();
        }
    }

    @objid ("4c07a270-1224-11e2-816a-001ec947ccaf")
    public void dumpCompId(SmObjectImpl object, SmDependency dep, List<SmObjectImpl> targets) throws XMLStreamException {
        writeProlog();
        
        if (this.currentObj == null) {
            this.out.writeStartElement(TAG_OBJECT);
            dumpID(TAG_ID, object);
        
            this.currentObj = object;
        } else if (this.currentObj != object) {
            this.out.writeEndElement();
            this.out.writeStartElement(TAG_OBJECT);
            dumpID(TAG_ID, object);
        
            this.currentObj = object;
        }
        
        this.out.writeStartElement(TAG_COMP);
        this.out.writeAttribute(ATT_RELATION, dep.getName());
        
        for (SmObjectImpl t : targets) {
            dumpID(TAG_COMPID, t);
        }
        
        this.out.writeEndElement();
    }

    @objid ("4c07a277-1224-11e2-816a-001ec947ccaf")
    private void dumpID(final String xmlkey, final MObject object) throws XMLStreamException {
        this.out.writeEmptyElement(xmlkey);
        this.out.writeAttribute(ATT_ID_NAME, object.getName());
        this.out.writeAttribute(ATT_ID_MC, object.getMClass().getQualifiedName());
        this.out.writeAttribute(ATT_ID_UID, object.getUuid());
    }

    @objid ("4c07a27d-1224-11e2-816a-001ec947ccaf")
    public void begin(final SmObjectImpl theCmsNode) {
        this.cmsNode = theCmsNode;
        this.currentObj = null;
    }

    /**
     * Open the file and write the document begin if not already done.
     * 
     * @throws javax.xml.stream.XMLStreamException on I/O failure
     */
    @objid ("9385803c-310f-46ce-802c-c6f5a2457a5b")
    private void writeProlog() throws XMLStreamException {
        if (this.out == null) {
            try {
                this.localOs = this.localResource.bufferedWrite();
                this.closeableWriter = new CloseableXMLStreamWriter(this.localOs, INDENT_FILES);
                this.out = this.closeableWriter.getW();
            } catch (IOException e) {
                throw new XMLStreamException(FileUtils.getLocalizedMessage(e), e);
            }
            
            this.out.writeStartDocument();
            this.out.writeComment("GENERATED FILE, PLEASE DO NOT EDIT!!!");
            
            this.out.writeStartElement(TAG_EXT);
            this.out.writeAttribute(ATT_EXT_OBJECT, this.cmsNode.getName());
            this.out.writeAttribute(ATT_EXT_VERSION, Integer.toString(FORMAT_VERSION));
        }
    }

}
