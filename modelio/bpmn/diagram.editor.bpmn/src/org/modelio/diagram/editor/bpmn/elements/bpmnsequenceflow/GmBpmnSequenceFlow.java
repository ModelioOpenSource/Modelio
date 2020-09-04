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

package org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataobject.GmBpmnDataObject;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflowdataassociation.GmBpmnSequenceFlowDataAssociation;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link BpmnSequenceFlow}.
 */
@objid ("619f43fe-55b6-11e2-877f-002564c97630")
public class GmBpmnSequenceFlow extends GmLink {
    @objid ("619f4404-55b6-11e2-877f-002564c97630")
    private BpmnSequenceFlow element;

    /**
     * Current version of this Gm class. Defaults to 0.
     */
    @objid ("619f4407-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("619f440a-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("e9c03722-3cbc-443b-8134-62441bf65712")
    public static final String ROLE_GUARD = "guard";

    @objid ("9295cb26-d4cc-4b98-b3e0-9e1fbe26c9d8")
    public static final String ROLE_DATAOBJECT = "dataobject";

    @objid ("c5ca6e8d-59a6-11e2-ae45-002564c97630")
    private static final GmBpmnSequenceFlowStyleKeys styleKeyProvider = new GmBpmnSequenceFlowStyleKeys();

    /**
     * Initialize a control flow graphic model.
     * 
     * @param diagram The owning diagram
     * @param element The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("619f440c-55b6-11e2-877f-002564c97630")
    public GmBpmnSequenceFlow(IGmDiagram diagram, BpmnSequenceFlow element, MRef ref) {
        super(diagram, ref);
        
        this.element = element;
        
        GmDefaultModelElementLabel extension = new GmDefaultModelElementLabel(diagram, ref);
        extension.setShowLabel(false);
        addExtension(ExtensionLocation.MiddleNW, IGmLink.ROLE_MAIN_LABEL, extension);
        addExtension(ExtensionLocation.SourceNW, GmBpmnSequenceFlow.ROLE_GUARD, new GmBpmnEdgeGuard(diagram, ref));
    }

    /**
     * For deserialization only.
     */
    @objid ("619f4418-55b6-11e2-877f-002564c97630")
    public GmBpmnSequenceFlow() {
        // Nothing to do.
    }

    @objid ("619f441b-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmBpmnSequenceFlow.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("61a0ca7d-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmBpmnSequenceFlow.styleKeyProvider.getStyleKeys();
    }

    @objid ("61a0ca86-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.element.getSourceRef();
    }

    @objid ("61a0ca8d-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getTargetRef();
    }

    @objid ("61a0ca94-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnSequenceFlow getRepresentedElement() {
        return this.element;
    }

    /**
     * Manages {@link BpmnDataObject} to be masked/unmasked.
     */
    @objid ("61a0ca9b-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        if (this.element != null) {
            List<BpmnDataObject> existingDataObjects = new ArrayList<>();
            List<GmBpmnDataObject> existingGmDataObjects = new ArrayList<>();
            Map<BpmnDataObject, BpmnSequenceFlowDataAssociation> extensionDataObjects = new HashMap<>();
        
            // Get GmBpmnDataObjects already unmasked as extensions
            for (GmModel model : getExtensions()) {
                if (model instanceof GmBpmnDataObject) {
                    GmBpmnDataObject gmDataObject = (GmBpmnDataObject) model;
                    existingDataObjects.add((BpmnDataObject) gmDataObject.getRelatedElement());
                    existingGmDataObjects.add(gmDataObject);
                }
            }
        
            // Get referenced BpmnDataObjects
            for (BpmnSequenceFlowDataAssociation sfda : this.element.getConnector()) {
                for (BpmnDataAssociation dataAssociation : sfda.getDataAssociation()) {
                    if (dataAssociation.getSourceRef().size() > 0) {
                        BpmnDataObject dataObject = (BpmnDataObject) dataAssociation.getSourceRef().get(0);
                        extensionDataObjects.put(dataObject, sfda);
                    }
                }
            }
        
            // Remove no longer referenced data objects
            for (GmBpmnDataObject gmDataObject : existingGmDataObjects) {
                if (!extensionDataObjects.containsKey(gmDataObject.getRelatedElement())) {
                    removeExtension(gmDataObject);
                } else {
                    boolean isMissingLink = true;
                    for (IGmLink startingLink : getStartingLinks()) {
                        if (startingLink instanceof GmBpmnSequenceFlowDataAssociation && gmDataObject.getRepresentedRef().equals(startingLink.getTo().getRepresentedRef())) {
                            isMissingLink = false;
                            break;
                        }
                    }
                    if (isMissingLink) {
                        createMissingLinkForElement(extensionDataObjects.get(gmDataObject.getRepresentedElement()), gmDataObject);
                    }
                }
            }
        
            // Unmask data objects that are not already extensions
            for (Entry<BpmnDataObject, BpmnSequenceFlowDataAssociation> entry : extensionDataObjects.entrySet()) {
                BpmnDataObject dataObject = entry.getKey();
                BpmnSequenceFlowDataAssociation sfda = entry.getValue();
        
                if (!existingDataObjects.contains(dataObject)) {
                    GmBpmnDataObject gmDataObject = null;
        
                    // Get existing gm data object from the diagram
                    for (GmModel existingGm : this.getDiagram().getAllGMRepresenting(new MRef(dataObject))) {
                        if (existingGm instanceof GmBpmnDataObject && !(existingGm.getParent() instanceof GmBpmnSequenceFlow)) {
                            gmDataObject = (GmBpmnDataObject) existingGm;
                            gmDataObject.getParentNode().removeChild(gmDataObject);
                            gmDataObject.setLayoutData(null);
                            break;
                        }
                    }
        
                    if (gmDataObject == null) {
                        // Create gm data object
                        gmDataObject = new GmBpmnDataObject(getDiagram(), dataObject, new MRef(dataObject));
                    }
                    addExtension(ExtensionLocation.MiddleNW, GmBpmnSequenceFlow.ROLE_DATAOBJECT, gmDataObject);
        
                    // Move extension a little
                    GmFractionalConnectionLocator layoutContraint = (GmFractionalConnectionLocator) getLayoutContraint(gmDataObject);
                    layoutContraint.setVDistance(60);
        
                    // Unmask link
                    createMissingLinkForElement(sfda, gmDataObject);
        
                    firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, getRelatedElement().getName());
                }
            }
        
            // Fire changes
            firePropertyChange(IGmObject.PROPERTY_LABEL, null, getRelatedElement().getName());
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getRepresentedElement().getName());
        }
        super.refreshFromObModel();
    }

    @objid ("61a0ca9e-55b6-11e2-877f-002564c97630")
    @Override
    public void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (BpmnSequenceFlow) resolveRef(this.getRepresentedRef());
    }

    @objid ("61a0caa4-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("61a0caab-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnSequenceFlow.", GmBpmnSequenceFlow.MINOR_VERSION);
    }

    @objid ("61a0cab1-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnSequenceFlow.MAJOR_VERSION;
    }

    @objid ("dae95017-a1d5-4497-99f2-8118ec998080")
    @Override
    protected void read_GmLinkV0_roles() {
        for (GmNodeModel n : getExtensions()) {
            if (n instanceof GmBpmnDataObject) {
                n.setRoleInComposition(GmBpmnSequenceFlow.ROLE_DATAOBJECT);
            } else if (n instanceof GmBpmnEdgeGuard) {
                n.setRoleInComposition(GmBpmnSequenceFlow.ROLE_GUARD);
            } else if (n instanceof GmDefaultModelElementLabel) {
                n.setRoleInComposition(IGmLink.ROLE_MAIN_LABEL);
            }
        }
    }

    @objid ("de1d2cff-baff-466c-96e7-9948abaa301a")
    private void createMissingLinkForElement(BpmnSequenceFlowDataAssociation link, GmBpmnDataObject target) {
        GmBpmnSequenceFlowDataAssociation gmLink = new GmBpmnSequenceFlowDataAssociation(getDiagram(), link, target.getRepresentedElement(), new MRef(link));
        
        addStartingLink(gmLink);
        target.getMainNode().addEndingLink(gmLink);
    }

}
