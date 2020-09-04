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

package org.modelio.diagram.editor.statik.elements.bpmncollaboration;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link BpmnCollaboration}.
 */
@objid ("55de26d8-7fa1-48b4-9a6c-05eaabd497ba")
public class GmBpmnCollaboration extends GmPortContainer {
    @objid ("8ba1a01f-8909-4c65-a7a1-83fd470e4c65")
    private BpmnCollaboration element;

    /**
     * Current version of this Gm.
     */
    @objid ("1f47bb54-d80b-41db-8189-5f17cb8e3681")
    private static final int MINOR_VERSION = 1;

    @objid ("99096067-6a3d-4e79-b02c-ca2534972b01")
    private static final int MAJOR_VERSION = 0;

    @objid ("5518ab58-f982-48ca-a279-31cf55dfa560")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("4e3ceb03-676a-4aec-bef5-e2b1dcf01a86")
     static final GmBpmnCollaborationStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnCollaborationStructuredStyleKeys();

    @objid ("419439ce-b70d-48ed-8293-a0f5ec9844fa")
     static final GmBpmnCollaborationSimpleStyleKeys SIMPLE_KEYS = new GmBpmnCollaborationSimpleStyleKeys();

    @objid ("d2e3f146-d8d2-40a8-b342-4ca70a41c13f")
     static final GmBpmnCollaborationImageStyleKeys IMAGE_KEYS = new GmBpmnCollaborationImageStyleKeys();

    @objid ("40c36e8a-e28c-4f3b-a7fb-b22db92086b0")
     static final GmBpmnCollaborationUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnCollaborationUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the callBehavior is unmasked.
     * @param el the unmasked callBehavior.
     * @param ref a reference to the unmasked callBehavior.
     */
    @objid ("6acb1609-b7df-46cc-abfc-723ca77768ca")
    public GmBpmnCollaboration(final IGmDiagram diagram, final BpmnCollaboration el, final MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmBpmnCollaborationPrimaryNode mainNode = new GmBpmnCollaborationPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnCollaboration.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("7383a840-8489-45b9-8a74-032e87e2dce8")
    public GmBpmnCollaboration() {
        // Nothing specific to do.
    }

    @objid ("c163eef7-7086-4682-9f7a-89a284a83b60")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("bd22c5dd-1de8-4474-b9a5-060c272dcda9")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("8779bac9-5748-44e3-a270-ff151d3f6a8d")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("82e79c52-f055-410f-a23d-7b5b9db58642")
    @Override
    public BpmnCollaboration getRepresentedElement() {
        return this.element;
    }

    @objid ("aea4a196-b7a4-4752-a43f-67d7ca81f49a")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        StyleKey styleKey = GmBpmnCollaboration.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmBpmnCollaboration.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmBpmnCollaboration.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmBpmnCollaboration.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmBpmnCollaboration.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("e650477f-9906-4ae6-8406-6dce9cd463e6")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmBpmnCollaboration.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmBpmnCollaboration.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmBpmnCollaboration.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmBpmnCollaboration.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmBpmnCollaboration.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    @objid ("c6e1c7f8-7b42-4112-a926-65599a610641")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnCollaboration.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 1
            read_1(in);
            break;
        }
        }
    }

    @objid ("03472b29-7aff-4e81-9395-a405364bbae2")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnCollaboration.", GmBpmnCollaboration.MINOR_VERSION);
    }

    @objid ("6178219b-c202-49cc-ba3a-8569f5c40c26")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnCollaboration) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmBpmnCollaboration.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("3a920db2-a70d-4f8f-9407-7b8baa2ef9da")
    @Override
    public int getMajorVersion() {
        return GmBpmnCollaboration.MAJOR_VERSION;
    }

    @objid ("7495d101-7db1-4d89-a8a1-25164ac698b6")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnCollaboration) resolveRef(getRepresentedRef());
    }

    @objid ("88330b20-4ac7-44e5-8ef1-fbc90e62293e")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmBpmnCollaboration.IMAGE_LABEL_ROLE);
                ret.remove(imageModeHeader);
                break;
            }
            case IMAGE:
            default: {
                break;
            }
        
            }
        }
        return ret;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("75add86a-0bb6-4323-98f5-e4aa9a2e6af7")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnCollaboration.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("3cdacee4-aeef-48dd-8955-18eba2de0385")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("0c01e294-68a3-4d24-b83f-c50c98048c2f")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnCollaboration.IMAGE_LABEL_ROLE);
    }

}
