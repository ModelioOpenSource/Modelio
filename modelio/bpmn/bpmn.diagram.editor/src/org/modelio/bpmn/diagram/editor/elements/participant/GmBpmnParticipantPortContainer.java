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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.bpmn.diagram.editor.elements.diagrams.processdesign.GmBpmnProcessDesignDiagram;
import org.modelio.bpmn.diagram.editor.elements.workflow.GmWorkflow;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.core.view.LegacyStyleKeyProviderSymbolViewModel;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("e3fbdabb-487a-4838-9d57-4949f4b7a15a")
public class GmBpmnParticipantPortContainer extends GmPortContainer {
    @objid ("ee5cbdd8-bb5c-42e0-b446-2a1075c474fc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current minor version of this Gm.
     */
    @objid ("fcd35623-abf7-46d6-a9dd-d96d22d1cb78")
    private static final int MINOR_VERSION = 0;

    @objid ("3eb743d0-b635-4702-8ea3-33339cce9b0d")
    private static final String ROLE_IMAGE_LABEL = "ImageLabel";

    @objid ("8210d0b0-8b94-4ccf-ac59-a25bdd73808f")
    private static final Collection<String> ROLES_IMAGE = new HashSet<>(Arrays.asList(GmPortContainer.MAIN_NODE_ROLE, GmBpmnParticipantPortContainer.ROLE_IMAGE_LABEL));

    @objid ("250e1768-a2ad-4c1a-9f09-d4caf5c2c48f")
    private static final Collection<String> ROLES_SIMPLE = new HashSet<>(Arrays.asList(GmPortContainer.MAIN_NODE_ROLE));

    @objid ("3a39147e-4ee1-49ba-8274-5bef35472ff5")
    private static final Collection<String> ROLES_STRUCTURED = new HashSet<>(Arrays.asList(GmPortContainer.MAIN_NODE_ROLE));

    @objid ("a63dc527-51e5-4762-adfb-183f29109e68")
    private BpmnParticipant element;

    @objid ("6074f3f2-4784-477f-b937-293989529941")
    private static final String MINOR_PREFIX = "GmBpmnParticipantPortContainer.";

    /**
     * Constructor.
     * @param diagram the diagram in which the BpmnReceiveTask is unmasked.
     * @param element the unmasked BpmnReceiveTask.
     * @param ref a reference to the unmasked callOperation.
     */
    @objid ("6ff21340-44ae-4992-808a-f6a0f3027e08")
    public  GmBpmnParticipantPortContainer(IGmDiagram diagram, BpmnParticipant element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmBpmnParticipantPrimaryNode mainNode = new GmBpmnParticipantPrimaryNode(diagram, element, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnParticipantPortContainer.ROLE_IMAGE_LABEL);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("186edf2c-c55e-4f4b-86d1-ab99a3781c01")
    public  GmBpmnParticipantPortContainer() {
        super();
    }

    @objid ("38a5d32f-f322-4561-acff-f40fbcad22a3")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("9c6c3da2-83d8-49a0-8e76-62ab5a3987c9")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("452249b3-05fd-4cb5-aa40-544d4c73b890")
    @Override
    public RepresentationMode getMainNodeRepresentationMode() {
        return getDisplayedStyle().getProperty(ParticipantStyleKeys.REPMODE);
    }

    @objid ("962c471a-04d3-4b62-8669-4aedd4e91b96")
    @Override
    public int getMajorVersion() {
        return GmBpmnParticipantPortContainer.MAJOR_VERSION;
    }

    @objid ("48382859-6743-4429-b153-c7695cf082d8")
    @Override
    public BpmnParticipant getRelatedElement() {
        return this.element;
    }

    @objid ("1b6daa41-1e00-48e1-bb72-2000a2e2aa4c")
    @Override
    public BpmnParticipant getRepresentedElement() {
        return this.element;
    }

    @objid ("2a71fa9e-6f16-4da5-a5b7-f5ef19ab33fa")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return ParticipantStyleKeys.INSTANCE.getStyleKey(metakey);
    }

    @objid ("cd7cb39a-0bad-48a9-8032-d6f7c86876fe")
    @Override
    public List<StyleKey> getStyleKeys() {
        return ParticipantStyleKeys.INSTANCE.getStyleKeys();
    }

    @objid ("236269b2-8dd4-4d90-94a9-f8bc994b057c")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        Collection<String> visibleRoles;
        List<GmNodeModel> ret = super.getVisibleChildren();
        switch (getMainNodeRepresentationMode()) {
        case STRUCTURED:
            visibleRoles = GmBpmnParticipantPortContainer.ROLES_STRUCTURED;
            break;
        case IMAGE:
        case USER_IMAGE:
            visibleRoles = GmBpmnParticipantPortContainer.ROLES_IMAGE;
            break;
        default:
        case SIMPLE:
            visibleRoles = GmBpmnParticipantPortContainer.ROLES_SIMPLE;
            break;
        }
        
        ret.removeIf(gm -> !visibleRoles.contains(gm.getRoleInComposition()));
        return ret;
    }

    @objid ("f8d80e2a-fef4-4357-91cb-7c556288aa29")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnParticipantPortContainer.ROLE_IMAGE_LABEL);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("fa878c00-d763-4b62-a268-96313cf4a187")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("74ddddc0-bf6e-42d4-b1e4-a1ce521d77bc")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnParticipantPortContainer.ROLE_IMAGE_LABEL.equals(role);
        
    }

    @objid ("21f8e055-7a7c-4809-8ce0-f2347213ec7a")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, GmBpmnParticipantPortContainer.MINOR_PREFIX);
        switch (readVersion) {
        case 0:
            read_0(in);
            break;
        default:
            assert (false) : "version number not covered!";
            // reading as last handled version: 1
            read_0(in);
            break;
        
        }
        
    }

    @objid ("05f2da0f-fc17-4328-8993-d1f8358c4a95")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, GmBpmnParticipantPortContainer.MINOR_PREFIX, GmBpmnParticipantPortContainer.MINOR_VERSION);
        
    }

    @objid ("9eeda7cd-0398-498d-8aeb-e415412b531b")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnParticipant) resolveRef(getRepresentedRef());
        
    }

    @objid ("2a9271db-fc31-4408-8fee-6125804ef722")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return new LegacyStyleKeyProviderSymbolViewModel(ParticipantStyleKeys.INSTANCE, getDiagram().getPersistedStyle());
    }

    /**
     * Get the diagram model embedded in this participant.
     * @return the embedded diagram for an expanded participant, else <i>null</i>.
     */
    @objid ("f351db08-e2ff-4c1a-adc1-4d4e9f7cc63c")
    private GmBpmnProcessDesignDiagram getEmbeddedDiagram() {
        if (getMainNodeRepresentationMode() == RepresentationMode.STRUCTURED) {
            GmBpmnParticipantPrimaryNode mainNode = getMainNode();
            if (mainNode != null && mainNode.getBody() != null) {
                return mainNode.getBody().getViewedDiagramModel(true);
            }
        }
        return null;
    }

    /**
     * Get the {@link GmWorkflow} embedded in this participant.
     * @return the embedded {@link GmWorkflow} for an expanded participant, else <i>null</i>.
     */
    @objid ("bf07b7c7-b460-4b23-99bb-b22b67105c4d")
    public final GmWorkflow getWorkflow() {
        final GmBpmnProcessDesignDiagram embeddedDiagram = getEmbeddedDiagram();
        if (embeddedDiagram != null) {
            return embeddedDiagram.getWorkflow();
        } else {
            return null;
        }
        
    }

}
