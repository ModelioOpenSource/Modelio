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
package org.modelio.uml.activitydiagram.editor.elements.action;

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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.uml.activitydiagram.editor.elements.pincontainer.GmPinContainer;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the graphic model of an {@link OpaqueAction action} .
 * <p>
 * Specialization of the GmPortContainer class for OpaqueAction.
 * 
 * @author fpoyer
 */
@objid ("29811d91-55b6-11e2-877f-002564c97630")
public class GmAction extends GmPinContainer {
    @objid ("29811d93-55b6-11e2-877f-002564c97630")
    private OpaqueAction element;

    /**
     * Current version of this Gm.
     */
    @objid ("29811d9c-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("29811d9f-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("29811da1-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("29811d98-55b6-11e2-877f-002564c97630")
    static final GmActionSimpleStyleKeys SIMPLE_KEYS = new GmActionSimpleStyleKeys();

    @objid ("2f43af53-58a2-11e2-9574-002564c97630")
    static final GmActionStructuredStyleKeys STRUCTURED_KEYS = new GmActionStructuredStyleKeys();

    @objid ("2f43af55-58a2-11e2-9574-002564c97630")
    static final GmActionImageStyleKeys IMAGE_KEYS = new GmActionImageStyleKeys();

    @objid ("9cc97483-88b7-4667-b9da-714db407613e")
    static final GmActionUserImageStyleKeys USERIMAGE_KEYS = new GmActionUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the action is unmasked.
     * @param el the unmasked action.
     * @param ref a reference to the unmasked action.
     */
    @objid ("29811da3-55b6-11e2-877f-002564c97630")
    public  GmAction(IGmDiagram diagram, OpaqueAction el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmActionPrimaryNode mainNode = new GmActionPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmAction.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    @objid ("29811daf-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (InputPin.class.isAssignableFrom(type) || OutputPin.class.isAssignableFrom(type) || ValuePin.class.isAssignableFrom(type));
    }

    @objid ("2982a419-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return ((InputPin.class.isAssignableFrom(el.getClass()) ||
                        OutputPin.class.isAssignableFrom(el.getClass()) || ValuePin.class.isAssignableFrom(el.getClass())) &&
                        el.isValid() && el.getCompositionOwner().equals(this.element));
        
    }

    @objid ("2982a421-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey styleKey = GmAction.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmAction.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmAction.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmAction.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmAction.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("2982a42b-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmAction.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmAction.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmAction.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmAction.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmAction.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    /**
     * Empty constructor needed for deserialization.
     */
    @objid ("2982a434-55b6-11e2-877f-002564c97630")
    public  GmAction() {
        // Nothing specific to do.
    }

    @objid ("2982a437-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmAction.");
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

    @objid ("2982a43d-55b6-11e2-877f-002564c97630")
    @Override
    public ActivityAction getRepresentedElement() {
        return this.element;
    }

    @objid ("2982a444-55b6-11e2-877f-002564c97630")
    @Override
    public ActivityAction getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2982a44b-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmAction.", GmAction.MINOR_VERSION);
        
    }

    @objid ("2982a451-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (OpaqueAction) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmAction.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
        
    }

    @objid ("2982a456-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmAction.MAJOR_VERSION;
    }

    @objid ("29842abc-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (OpaqueAction) resolveRef(getRepresentedRef());
        
    }

    @objid ("29842ac2-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmAction.IMAGE_LABEL_ROLE);
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
    @objid ("29842acb-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmAction.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("29842add-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("a06b6493-216d-4926-a45e-5ac3c0deda31")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmAction.IMAGE_LABEL_ROLE);
    }

}
