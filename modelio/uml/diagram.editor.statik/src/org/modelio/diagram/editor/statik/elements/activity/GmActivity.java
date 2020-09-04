/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.activity;

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
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link Activity}.
 */
@objid ("33d81942-55b7-11e2-877f-002564c97630")
public class GmActivity extends GmPortContainer {
    @objid ("33d81946-55b7-11e2-877f-002564c97630")
    private Activity element;

    /**
     * Current version of this Gm.
     */
    @objid ("33d8194f-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("33d81952-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("33d81954-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("33d81949-55b7-11e2-877f-002564c97630")
     static final GmActivityStructuredStyleKeys STRUCTURED_KEYS = new GmActivityStructuredStyleKeys();

    @objid ("33d8194d-55b7-11e2-877f-002564c97630")
     static final GmActivityImageStyleKeys IMAGE_KEYS = new GmActivityImageStyleKeys();

    @objid ("5a25ce88-5bd5-11e2-9e33-00137282c51b")
     static final GmActivitySimpleStyleKeys SIMPLE_KEYS = new GmActivitySimpleStyleKeys();

    @objid ("dd5ab8d1-29f3-435b-b6e3-f45b891bb6c6")
     static final GmActivityUserImageStyleKeys USERIMAGE_KEYS = new GmActivityUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the activity is unmasked.
     * @param el the unmasked activity.
     * @param ref a reference to the unmasked activity.
     */
    @objid ("33d81956-55b7-11e2-877f-002564c97630")
    public GmActivity(final IGmDiagram diagram, final Activity el, final MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmActivityPrimaryNode mainNode = new GmActivityPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmActivity.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("33d81965-55b7-11e2-877f-002564c97630")
    public GmActivity() {
        // Nothing specific to do.
    }

    @objid ("33d81968-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("33d81971-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("33d8197a-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("33d99fd9-55b7-11e2-877f-002564c97630")
    @Override
    public Activity getRepresentedElement() {
        return this.element;
    }

    @objid ("33d99fe0-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        StyleKey styleKey = GmActivity.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmActivity.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmActivity.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmActivity.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmActivity.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("33d99feb-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmActivity.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmActivity.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmActivity.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmActivity.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmActivity.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    @objid ("33d99ff4-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmActivity.");
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

    @objid ("33d99ffb-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmActivity.", GmActivity.MINOR_VERSION);
    }

    @objid ("33d9a001-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.element = (Activity) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmActivity.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("33d9a007-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmActivity.MAJOR_VERSION;
    }

    @objid ("33d9a00c-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (Activity) resolveRef(getRepresentedRef());
    }

    @objid ("33d9a012-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        List<GmNodeModel> children = super.getVisibleChildren();
        
        RepresentationMode mode = getMainNodeRepresentationMode();
        if (mode == RepresentationMode.SIMPLE || mode == RepresentationMode.STRUCTURED) {
            // Hide "image" labels
            children.removeIf(child -> GmActivity.IMAGE_LABEL_ROLE.equals(child.getRoleInComposition()));
        }
        return children;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("33db2679-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role) || GmActivity.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("33db268b-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("b4abe117-4c20-423f-a068-450ea509e4a5")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmActivity.IMAGE_LABEL_ROLE);
    }

}
