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

package org.modelio.uml.activitydiagram.editor.elements.acceptsignal;

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
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.uml.activitydiagram.editor.elements.pincontainer.GmPinContainer;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * @author fpoyer
 */
@objid ("297361ef-55b6-11e2-877f-002564c97630")
public class GmAcceptSignal extends GmPinContainer {
    @objid ("297361f1-55b6-11e2-877f-002564c97630")
    private AcceptSignalAction element;

    /**
     * Current version of this Gm.
     */
    @objid ("297361fa-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("297361fd-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("297361ff-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("2f2be162-58a2-11e2-9574-002564c97630")
     static final GmAcceptSignalStructuredStyleKeys STRUCTURED_KEYS = new GmAcceptSignalStructuredStyleKeys();

    @objid ("2f2be164-58a2-11e2-9574-002564c97630")
     static final GmAcceptSignalSimpleStyleKeys SIMPLE_KEYS = new GmAcceptSignalSimpleStyleKeys();

    @objid ("2f2be166-58a2-11e2-9574-002564c97630")
     static final GmAcceptSignalImageStyleKeys IMAGE_KEYS = new GmAcceptSignalImageStyleKeys();

    @objid ("552d51e1-e819-456a-9504-dedae1ba17d1")
     static final GmAcceptSignalUserImageStyleKeys USERIMAGE_KEYS = new GmAcceptSignalUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the acceptSignal is unmasked.
     * @param el the unmasked acceptSignal.
     * @param ref a reference to the unmasked acceptSignal.
     */
    @objid ("29736201-55b6-11e2-877f-002564c97630")
    public GmAcceptSignal(IGmDiagram diagram, AcceptSignalAction el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmAcceptSignalPrimaryNode mainNode = new GmAcceptSignalPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmAcceptSignal.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    @objid ("2973620d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (InputPin.class.isAssignableFrom(type) || ValuePin.class.isAssignableFrom(type) || OutputPin.class.isAssignableFrom(type));
    }

    @objid ("29736215-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return ((InputPin.class.isAssignableFrom(el.getClass()) ||
                        ValuePin.class.isAssignableFrom(el.getClass()) || OutputPin.class.isAssignableFrom(el.getClass())) && el.getCompositionOwner()
                                .equals(this.element));
    }

    @objid ("2974e87f-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey styleKey = GmAcceptSignal.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmAcceptSignal.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmAcceptSignal.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmAcceptSignal.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmAcceptSignal.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("2974e889-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmAcceptSignal.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmAcceptSignal.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmAcceptSignal.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmAcceptSignal.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmAcceptSignal.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("2974e892-55b6-11e2-877f-002564c97630")
    public GmAcceptSignal() {
        // Nothing specific to do.
    }

    @objid ("2974e895-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmAcceptSignal.");
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

    @objid ("2974e89b-55b6-11e2-877f-002564c97630")
    @Override
    public ActivityAction getRepresentedElement() {
        return this.element;
    }

    @objid ("2974e8a2-55b6-11e2-877f-002564c97630")
    @Override
    public ActivityAction getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2974e8a9-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmAcceptSignal.", GmAcceptSignal.MINOR_VERSION);
    }

    @objid ("2974e8af-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (AcceptSignalAction) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmAcceptSignal.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("2974e8b4-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmAcceptSignal.MAJOR_VERSION;
    }

    @objid ("29766f1a-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (AcceptSignalAction) resolveRef(getRepresentedRef());
    }

    @objid ("29766f20-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmAcceptSignal.IMAGE_LABEL_ROLE);
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
    @objid ("29766f29-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmAcceptSignal.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("29766f3b-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("ec181f4d-97f3-49ea-8876-96bf15c6a95a")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmAcceptSignal.IMAGE_LABEL_ROLE);
    }

}
