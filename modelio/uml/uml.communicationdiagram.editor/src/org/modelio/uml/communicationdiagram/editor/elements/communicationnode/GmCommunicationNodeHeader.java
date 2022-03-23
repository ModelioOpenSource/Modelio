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
package org.modelio.uml.communicationdiagram.editor.elements.communicationnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * {@link ICommunicationNode} link header displayed on the node.
 * 
 * @author cmarin
 */
@objid ("7a576416-55b6-11e2-877f-002564c97630")
public class GmCommunicationNodeHeader extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7a57641a-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("7a58ea79-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * @param diagram the diagram
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("7a58ea7b-55b6-11e2-877f-002564c97630")
    public  GmCommunicationNodeHeader(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("7a58ea86-55b6-11e2-877f-002564c97630")
    public  GmCommunicationNodeHeader() {
        
    }

    @objid ("7a58ea89-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        final CommunicationNode inst = (CommunicationNode) getRelatedElement();
        return CommunicationNodeSymbolProvider.computeSimpleLabel(inst);
    }

    @objid ("7a58ea8e-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        CommunicationNode communicationNode = (CommunicationNode) getRelatedElement();
        if (communicationNode != null &&
                communicationNode.isValid() &&
                communicationNode.getRepresented() != null) {
            setShowMetaclassIcon(communicationNode.getRepresented().getBase() != null);
        } else {
            setShowMetaclassIcon(false);
        }
        super.refreshFromObModel();
        
    }

    @objid ("7a58ea91-55b6-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        CommunicationNode communicationNode = (CommunicationNode) getRelatedElement();
        if (communicationNode.getRepresented() != null) {
            NameSpace base = communicationNode.getRepresented().getBase();
            if (base != null) {
                return ElementImageService.getIcon(base);
            }
        }
        return null;
    }

    @objid ("7a58ea96-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCommunicationNodeHeader.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
        
    }

    @objid ("7a58ea9c-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCommunicationNodeHeader.", GmCommunicationNodeHeader.MINOR_VERSION);
        
    }

    @objid ("7a58eaa2-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("7a58eaa7-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("f8f38a0c-3ffd-49a3-a493-dda53fac1c13")
    @Override
    public IEditableText getEditableText() {
        return new IEditableText() {
                    @Override
                    public String getText() {
                        final CommunicationNode theInstanceNode = (CommunicationNode) getRelatedElement();
        
                        Instance instance = theInstanceNode.getRepresented();
        
                        if (instance != null) {
                            return instance.getName();
                        } else {
                            return theInstanceNode.getName();
                        }
                    }
        
                    @Override
                    public void setText(String text) {
                        final CommunicationNode theInstanceNode = (CommunicationNode) getRelatedElement();
                        Instance instance = theInstanceNode.getRepresented();
        
                        if (instance != null) {
                            instance.setName(text);
                            theInstanceNode.setName(text);
                        } else {
                            theInstanceNode.setName(text);
                        }
                    }
                };
        
    }

}
