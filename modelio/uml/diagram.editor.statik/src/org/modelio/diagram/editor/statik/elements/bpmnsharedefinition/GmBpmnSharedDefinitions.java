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

package org.modelio.diagram.editor.statik.elements.bpmnsharedefinition;

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
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link BpmnSharedDefinitions}.
 */
@objid ("21f7e0ab-69cd-4bdd-afe6-70af64c89702")
public class GmBpmnSharedDefinitions extends GmPortContainer {
    @objid ("8c73f435-7c34-45db-8e91-942fc3838e80")
    private BpmnSharedDefinitions element;

    /**
     * Current version of this Gm.
     */
    @objid ("07bde93f-137f-426e-9596-77956e2a8056")
    private static final int MINOR_VERSION = 1;

    @objid ("d7e04709-b650-4f3e-b63b-277b5866e603")
    private static final int MAJOR_VERSION = 0;

    @objid ("123b8f60-c01d-4ed7-92a6-ae948d7eb013")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("bf872394-8ce4-409c-8ed9-9f734b4dc360")
     static final GmBpmnSharedDefinitionsStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnSharedDefinitionsStructuredStyleKeys();

    @objid ("5c643784-e68d-4485-b85b-e2292132132e")
     static final GmBpmnSharedDefinitionsSimpleStyleKeys SIMPLE_KEYS = new GmBpmnSharedDefinitionsSimpleStyleKeys();

    @objid ("a010aa56-be60-4cce-b4ef-8ae9597c8143")
     static final GmBpmnSharedDefinitionsImageStyleKeys IMAGE_KEYS = new GmBpmnSharedDefinitionsImageStyleKeys();

    @objid ("ebee0743-1dd8-4638-b6d7-b740610b4886")
     static final GmBpmnSharedDefinitionsUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnSharedDefinitionsUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the callBehavior is unmasked.
     * @param el the unmasked callBehavior.
     * @param ref a reference to the unmasked callBehavior.
     */
    @objid ("23825b6d-745b-4877-8677-09f8b5e8d720")
    public GmBpmnSharedDefinitions(final IGmDiagram diagram, final BpmnSharedDefinitions el, final MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmBpmnSharedDefinitionsPrimaryNode mainNode = new GmBpmnSharedDefinitionsPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnSharedDefinitions.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("35daedae-d210-49e9-a4a1-1d9efa6d28d0")
    public GmBpmnSharedDefinitions() {
        // Nothing specific to do.
    }

    @objid ("bce3368f-0820-4f7f-bdd8-b787a313931d")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("706f4398-cc4d-4886-bf36-1ec3e47575cd")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("7faf643b-0fea-4541-b575-60bedf8bc2ab")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("829b36df-1e72-408e-9e77-650941fbfecd")
    @Override
    public BpmnSharedDefinitions getRepresentedElement() {
        return this.element;
    }

    @objid ("f8498971-eec6-4276-97f3-2e74ad99d6c9")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        StyleKey styleKey = GmBpmnSharedDefinitions.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmBpmnSharedDefinitions.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmBpmnSharedDefinitions.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmBpmnSharedDefinitions.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmBpmnSharedDefinitions.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("15b6b0d3-276c-4b0e-8c03-dd07d76dda04")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmBpmnSharedDefinitions.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmBpmnSharedDefinitions.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmBpmnSharedDefinitions.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmBpmnSharedDefinitions.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmBpmnSharedDefinitions.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    @objid ("6e5ec74a-2002-40f2-82d1-e6107639fc35")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnSharedDefinitions.");
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

    @objid ("66b30bd8-f9a6-4837-b295-8d50cc47e107")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnSharedDefinitions.", GmBpmnSharedDefinitions.MINOR_VERSION);
    }

    @objid ("61dd4a56-dc01-4a5d-83bf-65cd4606c870")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnSharedDefinitions) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmBpmnSharedDefinitions.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("79ab670b-5e80-438d-ba39-6d515f00b74d")
    @Override
    public int getMajorVersion() {
        return GmBpmnSharedDefinitions.MAJOR_VERSION;
    }

    @objid ("122062b5-1640-486e-8882-1f33e487ce66")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnSharedDefinitions) resolveRef(getRepresentedRef());
    }

    @objid ("aa892ffa-471c-4860-b339-a0876424582b")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmBpmnSharedDefinitions.IMAGE_LABEL_ROLE);
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
    @objid ("360adc29-66eb-4ed4-92dc-3b2339847f61")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnSharedDefinitions.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("170096f3-8b77-45a5-a80f-e7cdd433ab18")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("ae2c9ddc-0ece-4224-a3c4-fbe594150f20")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnSharedDefinitions.IMAGE_LABEL_ROLE);
    }

}
