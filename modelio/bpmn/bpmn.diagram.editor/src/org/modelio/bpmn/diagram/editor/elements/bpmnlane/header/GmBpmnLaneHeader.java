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
package org.modelio.bpmn.diagram.editor.elements.bpmnlane.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * <p>
 * Specialisation of the default model element header for {@link BpmnLane}.
 * </p>
 */
@objid ("6129c5d4-55b6-11e2-877f-002564c97630")
public class GmBpmnLaneHeader extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("6129c5d8-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("6129c5db-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor without args for deserialization.
     */
    @objid ("6129c5e8-55b6-11e2-877f-002564c97630")
    public  GmBpmnLaneHeader() {
        // Nothing to do.
    }

    /**
     * C'tor.
     * @param diagram the owning diagram.
     */
    @objid ("6129c5eb-55b6-11e2-877f-002564c97630")
    public  GmBpmnLaneHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("612b4c5f-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnLaneHeader.");
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

    @objid ("612b4c65-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnLaneHeader.", GmBpmnLaneHeader.MINOR_VERSION);
        
    }

    @objid ("612b4c6b-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        if (getRelatedElement() instanceof BpmnLane) {
            BpmnLane lane = (BpmnLane) getRelatedElement();
            if (lane != null && lane.isValid()) {
                setShowMetaclassIcon(PartitionElement.getTarget(lane) != null);
            } else {
                setShowMetaclassIcon(false);
            }
        } else if (getRelatedElement() instanceof BpmnParticipant) {
            BpmnParticipant participant = (BpmnParticipant) getRelatedElement();
            if (participant != null && participant.isValid()) {
                ModelElement ref = Reference.getTarget(participant);
                setShowMetaclassIcon(ref != null);
            } else {
                setShowMetaclassIcon(false);
            }
        }
        
        super.refreshFromObModel();
        
    }

    @objid ("612b4c6e-55b6-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        if (getRelatedElement() instanceof BpmnLane) {
            BpmnLane lane = (BpmnLane) getRelatedElement();
        
            ModelElement type = PartitionElement.getTarget(lane);
            if (type != null) {
                return ElementImageService.getIcon(type);
            } else {
                return ElementImageService.getIcon(lane);
            }
        } else {
            return ElementImageService.getIcon(getRelatedElement());
        }
        
    }

    @objid ("612b4c72-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        return LaneSymbolProvider.computeSimpleLabel(getDiagram().getModelManager().getModelServices().getElementNamer(), getRelatedElement());
    }

    @objid ("612b4c76-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("612b4c7b-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnLaneHeader.MAJOR_VERSION;
    }

    /**
     * Specialization of {@link ProxyStyle} that returns a darker shade for the background color.
     * </p>
     * <p>
     * This class <strong>MUST</strong> be static or deserialization will fail.
     * </p>
     */
    @objid ("a0159d29-9c5e-4b0c-bf93-5cb83ba6bde5")
    public static class GmPartitionHeaderStyle extends ProxyStyle {
        /**
         * Returns a darker shade for the background color.
         */
        @objid ("454cf642-5295-4880-a129-d43f319a472d")
        @Override
        public Color getColor(StyleKey propertyKey) {
            return super.getColor(propertyKey);
        }

        /**
         * Empty c'tor needed for deserialization.
         */
        @objid ("dda7d924-f57c-407a-a5fd-81ea639cd969")
        public  GmPartitionHeaderStyle() {
            super();
        }

        /**
         * C'tor.
         * @param cascadedStyle the style this style should cascade on.
         */
        @objid ("70dc37a2-6215-4109-97ee-edc0be18a6da")
        public  GmPartitionHeaderStyle(IStyle cascadedStyle) {
            super(cascadedStyle);
        }

    }

}
