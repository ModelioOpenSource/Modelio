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

package org.modelio.diagram.editor.statik.elements.bpmnprocess;

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
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link BpmnProcess}.
 */
@objid ("342464a8-55b7-11e2-877f-002564c97630")
public class GmBpmnProcess extends GmPortContainer {
    @objid ("342464ac-55b7-11e2-877f-002564c97630")
    private BpmnProcess element;

    /**
     * Current version of this Gm.
     */
    @objid ("342464b5-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("3425eb1b-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("3425eb1d-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("5b202573-5bd5-11e2-9e33-00137282c51b")
     static final GmBpmnProcessStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnProcessStructuredStyleKeys();

    @objid ("5b202575-5bd5-11e2-9e33-00137282c51b")
     static final GmBpmnProcessSimpleStyleKeys SIMPLE_KEYS = new GmBpmnProcessSimpleStyleKeys();

    @objid ("5b202577-5bd5-11e2-9e33-00137282c51b")
     static final GmBpmnProcessImageStyleKeys IMAGE_KEYS = new GmBpmnProcessImageStyleKeys();

    @objid ("7da90ed7-5f4e-49bf-ab0e-cc599123a165")
     static final GmBpmnProcessUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnProcessUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the callBehavior is unmasked.
     * @param el the unmasked callBehavior.
     * @param ref a reference to the unmasked callBehavior.
     */
    @objid ("3425eb1f-55b7-11e2-877f-002564c97630")
    public GmBpmnProcess(final IGmDiagram diagram, final BpmnProcess el, final MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmBpmnProcessPrimaryNode mainNode = new GmBpmnProcessPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnProcess.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("3425eb2e-55b7-11e2-877f-002564c97630")
    public GmBpmnProcess() {
        // Nothing specific to do.
    }

    @objid ("3425eb31-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("3425eb3a-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("3425eb43-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("3425eb4a-55b7-11e2-877f-002564c97630")
    @Override
    public BpmnProcess getRepresentedElement() {
        return this.element;
    }

    @objid ("3425eb51-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        StyleKey styleKey = GmBpmnProcess.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmBpmnProcess.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmBpmnProcess.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmBpmnProcess.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmBpmnProcess.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("342771b9-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmBpmnProcess.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmBpmnProcess.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmBpmnProcess.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmBpmnProcess.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmBpmnProcess.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    @objid ("342771c2-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnProcess.");
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

    @objid ("342771c9-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnProcess.", GmBpmnProcess.MINOR_VERSION);
    }

    @objid ("3428f85c-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnProcess) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmBpmnProcess.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("3428f862-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnProcess.MAJOR_VERSION;
    }

    @objid ("3428f867-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnProcess) resolveRef(getRepresentedRef());
    }

    @objid ("3428f86d-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmBpmnProcess.IMAGE_LABEL_ROLE);
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
    @objid ("3428f876-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnProcess.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("3428f888-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("81d08c65-640d-4be3-b12c-f7262ce1bcc7")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnProcess.IMAGE_LABEL_ROLE);
    }

}
