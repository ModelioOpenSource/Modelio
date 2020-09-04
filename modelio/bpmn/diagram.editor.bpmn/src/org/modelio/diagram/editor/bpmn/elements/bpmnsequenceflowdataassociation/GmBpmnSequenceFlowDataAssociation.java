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

package org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflowdataassociation;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataobject.GmBpmnDataObject;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link BpmnSequenceFlowDataAssociation}.
 */
@objid ("b760a20f-7bb3-48f0-a082-48353709d72f")
public class GmBpmnSequenceFlowDataAssociation extends GmLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("4202a316-7a9e-4433-857a-7a1fe2af0109")
    private static final int MINOR_VERSION = 0;

    @objid ("8e8b51a4-cbab-4548-a281-f9e329be1386")
    private static final int MAJOR_VERSION = 0;

    @objid ("2744cd1a-620a-4ebf-a8cf-2ba3f8530c5e")
    private BpmnSequenceFlowDataAssociation element;

    @objid ("6575245e-e29f-4994-a12a-e1a8a1489ac8")
    private BpmnDataObject dataObject;

    @objid ("b3646d26-9d9c-4a4a-a965-1a6bc5f7fcdd")
    private static final GmBpmnSequenceFlowDataAssociationStyleKeys styleKeyProvider = new GmBpmnSequenceFlowDataAssociationStyleKeys();

    /**
     * Initialize a control flow graphic model.
     * 
     * @param diagram The owning diagram
     * @param element The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("c96de0ed-0257-4293-9450-233d1c932a30")
    public GmBpmnSequenceFlowDataAssociation(IGmDiagram diagram, BpmnSequenceFlowDataAssociation element, BpmnDataObject dataObject, MRef ref) {
        super(diagram, ref);
        this.element = element;
        this.dataObject = dataObject;
        
        GmDefaultModelElementLabel extension = new GmDefaultModelElementLabel(diagram, ref);
        extension.setShowLabel(false);
        addExtension(ExtensionLocation.MiddleNW, IGmLink.ROLE_MAIN_LABEL, extension);
    }

    @objid ("3d845dda-650e-4701-b4f4-b57aacc25369")
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
    @objid ("8b6d251c-17cd-4d7d-9001-f09ab1bba31e")
    public GmBpmnSequenceFlowDataAssociation() {
        // Nothing to do.
    }

    @objid ("098a3fff-2734-4f67-83f5-a7fd2f4bbb21")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmBpmnSequenceFlowDataAssociation.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("12a5d119-6202-4977-aeff-391a120e9a8f")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmBpmnSequenceFlowDataAssociation.styleKeyProvider.getStyleKeys();
    }

    @objid ("b624e0c3-3f2d-4f7c-93d7-f026b2675dc7")
    @Override
    public MObject getToElement() {
        return this.dataObject;
    }

    @objid ("73cc9c94-e0aa-4b4f-8819-8cb7395e17e4")
    @Override
    public BpmnSequenceFlowDataAssociation getRepresentedElement() {
        return this.element;
    }

    @objid ("b6a30465-d327-4875-8213-3fffb67e7160")
    @Override
    public MObject getFromElement() {
        return this.element.getConnected();
    }

    @objid ("bc872051-107a-4837-9439-047de7ce7aba")
    @Override
    public void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (BpmnSequenceFlowDataAssociation) resolveRef(getRepresentedRef());
        
        IGmLinkable to = getTo();
        if (to instanceof GmBpmnDataObject) {
            setTo(((GmBpmnDataObject) to).getMainNode());
        }
    }

    @objid ("5ed3dd0b-cc5e-49ca-b2fc-02a14ab605ea")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    /**
     * @return <code>true</code> if the link's source or target is a {@link BpmnFlowNode}.
     */
    @objid ("9e3bf69a-fc2c-4568-b680-f8563f447fa4")
    public boolean isLinkToSequenceFlow() {
        return !(getToElement() instanceof BpmnFlowNode || getFromElement() instanceof BpmnFlowNode);
    }

    @objid ("9318ee58-af2c-41e9-98f1-d0bd5ce1aa77")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnSequenceFlowDataAssociation.", GmBpmnSequenceFlowDataAssociation.MINOR_VERSION);
    }

    @objid ("580dba9a-e244-48dc-80f4-efdf28194db7")
    @Override
    public int getMajorVersion() {
        return GmBpmnSequenceFlowDataAssociation.MAJOR_VERSION;
    }

    @objid ("4c492649-b63e-45b6-b131-badd925e2235")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
