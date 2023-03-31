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
package org.modelio.bpmn.diagram.editor.elements.participant;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.bpmn.diagram.editor.elements.bpmnnodefooter.GmBpmnNodeFooter;
import org.modelio.bpmn.diagram.editor.elements.diagrams.processdesign.GmBpmnProcessDesignDiagram;
import org.modelio.bpmn.diagram.editor.elements.participant.content.GmBpmnParticipantContent;
import org.modelio.bpmn.diagram.editor.elements.participant.header.GmBpmnParticipantHeader;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a Participant.
 */
@objid ("03f8d572-5512-475e-a6cd-2379a1f4c087")
public class GmBpmnParticipantPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    @objid ("2305ba1a-2bb8-4223-8ab4-0632eef53510")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("cddaab57-43bb-4a94-8a67-fe69988c29b4")
    private static final int MINOR_VERSION = 0;

    @objid ("62aedb4d-6288-439f-a7f5-6eeba47061c1")
    private static final String MINOR_PREFIX = "GmBpmnParticipantPrimaryExpanded.";

    @objid ("31cdf934-a21d-4b1e-a1bf-dd204b576d60")
    static final String ROLE_HEAD = "participant.head";

    @objid ("6bf2d8a6-9830-409b-9fae-a11b8ed06b98")
    static final String ROLE_BODY = "participant.body";

    @objid ("b980eb79-f0a1-4366-8950-87c581a61fd0")
    static final String ROLE_FOOTER = "participant.foot";

    @objid ("36e5807e-102e-4def-a26d-a9155b2871ed")
    private static final Collection<String> ROLES_IMAGE = Collections.emptyList();

    @objid ("31c6da81-332f-4bb0-b814-d7b7adeaced7")
    private static final Collection<String> ROLES_SIMPLE = new HashSet<>(Arrays.asList(GmBpmnParticipantPrimaryNode.ROLE_HEAD, GmBpmnParticipantPrimaryNode.ROLE_FOOTER));

    @objid ("daf5a381-9bec-4e1b-b2c6-b037c09effd2")
    private static final Collection<String> ROLES_STRUCTURED = new HashSet<>(Arrays.asList(GmBpmnParticipantPrimaryNode.ROLE_HEAD, GmBpmnParticipantPrimaryNode.ROLE_BODY, GmBpmnParticipantPrimaryNode.ROLE_FOOTER));

    @objid ("61c0bdf3-e13a-47c2-b700-4add88cb280d")
    static final String CHECK_ORIENTATION = "CHECK_ORIENTATION";

    /**
     * Free zone
     */
    @objid ("2ab278b5-808f-44dd-964d-2350a3a3acca")
    private GmBpmnParticipantContent body;

    /**
     * Header
     */
    @objid ("2fbb4e5d-0e7e-4530-91c5-159ec56119a6")
    private GmBpmnParticipantHeader header;

    @objid ("5ebc6ed5-fa27-4277-8cc4-f81fa0712665")
    private GmBpmnNodeFooter footer;

    /**
     * The represented participant.
     */
    @objid ("be9a8b4b-01a8-49f0-8c00-0694ab16f54e")
    private BpmnParticipant element; // BpmnLane or BpmnParticipant
    

    /**
     * Default constructor.
     * @param diagram the diagram in which this partition will be unmasked.
     * @param theParticipant the unmasked partition (can be null).
     * @param ref a reference to the unmasked partition (cannot be null).
     */
    @objid ("16febe96-6160-4914-a72c-b5578ff47092")
    public  GmBpmnParticipantPrimaryNode(IGmDiagram diagram, BpmnParticipant theParticipant, MRef ref) {
        super(diagram, ref);
        this.element = theParticipant;
        
        this.header = new GmBpmnParticipantHeader(diagram, ref);
        this.header.setRoleInComposition(GmBpmnParticipantPrimaryNode.ROLE_HEAD);
        this.header.setShowMetaclassIcon(true);
        super.addChild(this.header);
        
        this.body = createBody();
        
        this.footer = new GmBpmnNodeFooter(diagram, ref);
        this.footer.setHorizontal(true);
        this.footer.setEmptySubProcess(true);
        this.footer.setRoleInComposition(GmBpmnParticipantPrimaryNode.ROLE_FOOTER);
        super.addChild(this.footer);
        
        refreshOrientation();
        
    }

    /**
     * Empty constructor needed for serialization.
     */
    @objid ("d0c47542-3a13-4a14-8915-8f933aaa00c3")
    public  GmBpmnParticipantPrimaryNode() {
        // Nothing to do.
    }

    @objid ("7edc7adb-4a7d-49b3-882a-5d2ddcbfdd3b")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("90298873-3100-4831-ab2f-d518b73c8c8e")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("6ea60b90-6b44-4526-b340-0222084520a1")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (this.body == null) {
            return null;
        }
        return this.body.getCompositeFor(metaclass);
    }

    @objid ("9fa0ef16-4258-4d19-9e9b-426c1b19de02")
    @Override
    public int getMajorVersion() {
        return GmBpmnParticipantPrimaryNode.MAJOR_VERSION;
    }

    @objid ("ac0a51ae-2c70-4264-95f1-551645a8a62c")
    @Override
    public BpmnParticipant getRelatedElement() {
        return this.element;
    }

    @objid ("6fd35651-4297-4a8f-9f34-25cd49ea66a1")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(ParticipantStyleKeys.REPMODE);
    }

    @objid ("ff91a790-fb93-4a64-b76f-3e6816ea2917")
    @Override
    public BpmnParticipant getRepresentedElement() {
        return this.element;
    }

    @objid ("d5059ae0-e85a-4bc3-8904-22bc0c5cd7be")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, GmBpmnParticipantPrimaryNode.MINOR_PREFIX);
        switch (readVersion) {
        case 0:
            read_0(in);
            break;
        
        default:
            assert (false) : readVersion + " version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        
        }
        
    }

    @objid ("f34ced4b-4428-4f78-985e-3bf2625d24b5")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, GmBpmnParticipantPrimaryNode.MINOR_PREFIX, GmBpmnParticipantPrimaryNode.MINOR_VERSION);
        
    }

    @objid ("38d6653b-2d91-4792-8053-3398c0f3068e")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnParticipantHeader) getFirstChild(GmBpmnParticipantPrimaryNode.ROLE_HEAD);
        this.body = (GmBpmnParticipantContent) getFirstChild(GmBpmnParticipantPrimaryNode.ROLE_BODY);
        this.footer = (GmBpmnNodeFooter) getFirstChild(GmBpmnParticipantPrimaryNode.ROLE_FOOTER);
        this.element = (BpmnParticipant) resolveRef(getRepresentedRef());
        refreshOrientation();
        
    }

    @objid ("b6b69e8e-0d3e-48a5-b441-80c95e7ec8dd")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        if (this.body == null) {
            this.body = createBody();
        }
        
        if (this.body != null) {
            GmBpmnProcessDesignDiagram embeddedDiagram = this.body.getViewedDiagramModel(true);
            if (embeddedDiagram != null) {
                embeddedDiagram.addPropertyChangeListener(new PropertyChangeListener() {
        
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals(GmAbstractDiagram.PROP_DIAGRAM_LOAD_END)) {
                            firePropertyChange(GmBpmnParticipantPrimaryNode.CHECK_ORIENTATION, null, null);
                            embeddedDiagram.removePropertyChangeListener(this);
                        }
                    }
                });
            }
        }
        
        if (getRelatedElement() != null) {
            final int multiplicityMax = getRelatedElement().getMultiplicityMax();
            this.footer.setParallel(multiplicityMax != 0 && multiplicityMax != 1);
        }
        
        this.footer.setEmptySubProcess(getRepresentationMode() == RepresentationMode.SIMPLE);
        
        // forcing visual refresh in case Image changed
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
        
    }

    @objid ("1922ef41-593f-4ad9-b72d-851ce92715fb")
    public GmBpmnParticipantContent getBody() {
        return this.body;
    }

    @objid ("73b9826e-b60a-4cd3-97b9-ba57853ba728")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        Collection<String> visibleRoles;
        List<GmNodeModel> ret = super.getVisibleChildren();
        switch (getRepresentationMode()) {
        case STRUCTURED:
            visibleRoles = GmBpmnParticipantPrimaryNode.ROLES_STRUCTURED;
            break;
        case IMAGE:
        case USER_IMAGE:
            visibleRoles = GmBpmnParticipantPrimaryNode.ROLES_IMAGE;
            break;
        default:
        case SIMPLE:
            visibleRoles = GmBpmnParticipantPrimaryNode.ROLES_SIMPLE;
            break;
        }
        
        ret.removeIf(gm -> !visibleRoles.contains(gm.getRoleInComposition()));
        return ret;
    }

    @objid ("2ea036c7-1d54-46a2-a931-4bdfcc49a164")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("f01ae14b-b490-4157-820d-41cda7faf3c8")
    private GmBpmnParticipantContent createBody() {
        final BpmnProcess process = this.element != null && this.element.isValid() ? this.element.getProcess() : null;
        if (process != null) {
            List<BpmnProcessDesignDiagram> processdiags = process.getProduct(BpmnProcessDesignDiagram.class);
            if (!processdiags.isEmpty()) {
                BpmnProcessDesignDiagram diag = processdiags.get(0);
                GmBpmnParticipantContent gmBody = new GmBpmnParticipantContent(getDiagram(), diag, new MRef(diag));
                gmBody.setRoleInComposition(GmBpmnParticipantPrimaryNode.ROLE_BODY);
                addChild(gmBody);
                return gmBody;
            }
        }
        return null;
    }

    /**
     * @return whether the participant should be displayed horizontally or vertically.
     */
    @objid ("9de30138-eda2-4ac5-8a45-7364b85aca07")
    boolean isHorizontalParticipantOrientation() {
        if (this.body != null) {
            GmBpmnProcessDesignDiagram diagram = this.body.getViewedDiagramModel(true);
            if (diagram != null) {
                return diagram.isHorizontalLaneOrientation();
            }
        }
        return true;
    }

    @objid ("25733623-aafe-4919-8a2c-0968950a0ddb")
    protected final void refreshOrientation() {
        this.header.setHorizontal(!isHorizontalParticipantOrientation());
        this.footer.setHorizontal(isHorizontalParticipantOrientation());
        
    }

    @objid ("ad7122f2-6bc6-4449-8a54-4ee26b1c7466")
    @Override
    public void removeChild(GmNodeModel child) {
        if (child == this.body) {
            this.body = null;
        }
        super.removeChild(child);
        
    }

    @objid ("15568458-54c4-46c0-9ee9-e50f33e64f03")
    @Override
    protected boolean isValidChild(GmNodeModel child) {
        if (child == this.body) {
            AbstractDiagram embeddedDiagram = this.body.getRelatedElement();
            ModelElement process = embeddedDiagram != null ? embeddedDiagram.getOrigin() : null;
            return Objects.equals(this.element.getProcess(), process);
        } else {
            return super.isValidChild(child);
        }
        
    }

}
