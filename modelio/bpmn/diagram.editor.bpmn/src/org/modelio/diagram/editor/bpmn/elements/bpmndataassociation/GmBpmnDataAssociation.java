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

package org.modelio.diagram.editor.bpmn.elements.bpmndataassociation;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link BpmnDataAssociation}.
 */
@objid ("60a812af-55b6-11e2-877f-002564c97630")
public class GmBpmnDataAssociation extends GmLink {
    @objid ("60a812b5-55b6-11e2-877f-002564c97630")
    private BpmnDataAssociation element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("60a812b8-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("60a99939-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("c555cb2d-59a6-11e2-ae45-002564c97630")
    private static final GmBpmnDataAssociationStyleKeys styleKeyProvider = new GmBpmnDataAssociationStyleKeys();

    /**
     * Initialize a control flow graphic model.
     * 
     * @param diagram The owning diagram
     * @param element The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("60a9993b-55b6-11e2-877f-002564c97630")
    public GmBpmnDataAssociation(IGmDiagram diagram, BpmnDataAssociation element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmDefaultModelElementLabel extension = new GmDefaultModelElementLabel(diagram, ref);
        extension.setShowLabel(false);
        addExtension(ExtensionLocation.MiddleNW, IGmLink.ROLE_MAIN_LABEL, extension);
    }

    @objid ("60a99947-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        if (this.element != null) {
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getRepresentedElement().getName());
        }
    }

    /**
     * For deserialization only.
     */
    @objid ("60a9994a-55b6-11e2-877f-002564c97630")
    public GmBpmnDataAssociation() {
        // Nothing to do.
    }

    @objid ("60a9994d-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmBpmnDataAssociation.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("60a99957-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmBpmnDataAssociation.styleKeyProvider.getStyleKeys();
    }

    @objid ("60a99960-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        if (this.element.getStartingEvent() != null) {
            return this.element.getStartingEvent();
        } else if (this.element.getEndingActivity() != null) {
            return this.element.getEndingActivity();
        } else if (this.element.getTargetRef() != null) {
            return this.element.getTargetRef();
        } else if (this.element.getSourceRef().size() > 0) {
            return this.element.getSourceRef().get(0);
        }
        return null;
    }

    @objid ("60a99967-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnDataAssociation getRepresentedElement() {
        return this.element;
    }

    @objid ("60a9996e-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        if (this.element.getEndingEvent() != null) {
            return this.element.getEndingEvent();
        } else if (this.element.getStartingActivity() != null) {
            return this.element.getStartingActivity();
        } else if (this.element.getSourceRef().size() > 0) {
            return this.element.getSourceRef().get(0);
        } else if (this.element.getTargetRef() != null) {
            return this.element.getTargetRef();
        }
        return null;
    }

    @objid ("60a99975-55b6-11e2-877f-002564c97630")
    @Override
    public void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (BpmnDataAssociation) resolveRef(getRepresentedRef());
    }

    @objid ("60a9997b-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    /**
     * @return <code>true</code> if the link's source or target is a {@link BpmnFlowNode}.
     */
    @objid ("60ab1fdb-55b6-11e2-877f-002564c97630")
    public boolean isLinkToSequenceFlow() {
        return !(getToElement() instanceof BpmnFlowNode || getFromElement() instanceof BpmnFlowNode);
    }

    @objid ("60ab1fdf-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnDataAssociation.", GmBpmnDataAssociation.MINOR_VERSION);
    }

    @objid ("60ab1fe5-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnDataAssociation.MAJOR_VERSION;
    }

    @objid ("4a883b92-6f34-4781-b242-5f223da72e43")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
