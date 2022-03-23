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
package org.modelio.uml.statikdiagram.editor.elements.interaction;

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
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link Interaction}.
 */
@objid ("35665a5a-55b7-11e2-877f-002564c97630")
public class GmInteraction extends GmPortContainer {
    @objid ("35665a5e-55b7-11e2-877f-002564c97630")
    private Interaction element;

    /**
     * Current version of this Gm.
     */
    @objid ("35665a67-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("35665a6a-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("35665a6c-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("5e603a24-5bd5-11e2-9e33-00137282c51b")
    static final GmInteractionStructuredStyleKeys STRUCTURED_KEYS = new GmInteractionStructuredStyleKeys();

    @objid ("5e64fed9-5bd5-11e2-9e33-00137282c51b")
    static final GmInteractionSimpleStyleKeys SIMPLE_KEYS = new GmInteractionSimpleStyleKeys();

    @objid ("5e64fedb-5bd5-11e2-9e33-00137282c51b")
    static final GmInteractionImageStyleKeys IMAGE_KEYS = new GmInteractionImageStyleKeys();

    @objid ("cc59209b-2785-42b7-b3d3-2fc55fd15ce7")
    static final GmInteractionUserImageStyleKeys USERIMAGE_KEYS = new GmInteractionUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the callBehavior is unmasked.
     * @param el the unmasked callBehavior.
     * @param ref a reference to the unmasked callBehavior.
     */
    @objid ("35665a6e-55b7-11e2-877f-002564c97630")
    public  GmInteraction(final IGmDiagram diagram, final Interaction el, final MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmInteractionPrimaryNode mainNode = new GmInteractionPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmInteraction.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("3567e101-55b7-11e2-877f-002564c97630")
    public  GmInteraction() {
        // Nothing specific to do.
    }

    @objid ("3567e104-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("3567e10d-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("3567e116-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("3567e11d-55b7-11e2-877f-002564c97630")
    @Override
    public Interaction getRepresentedElement() {
        return this.element;
    }

    @objid ("3567e124-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        StyleKey styleKey = GmInteraction.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmInteraction.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmInteraction.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmInteraction.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmInteraction.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("3567e12f-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmInteraction.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmInteraction.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmInteraction.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmInteraction.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmInteraction.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    @objid ("3567e138-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmInteraction.");
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

    @objid ("3569679c-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmInteraction.", GmInteraction.MINOR_VERSION);
        
    }

    @objid ("356967a2-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.element = (Interaction) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmInteraction.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
        
    }

    @objid ("356967a8-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmInteraction.MAJOR_VERSION;
    }

    @objid ("356967ad-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (Interaction) resolveRef(getRepresentedRef());
        
    }

    @objid ("356967b3-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmInteraction.IMAGE_LABEL_ROLE);
                ret.remove(imageModeHeader);
                break;
            }
            case USER_IMAGE:
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
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("356967bc-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                                        || GmInteraction.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("356967ce-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("5e7af9f4-0372-415c-88be-d4c112b4e723")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmInteraction.IMAGE_LABEL_ROLE);
    }

}
