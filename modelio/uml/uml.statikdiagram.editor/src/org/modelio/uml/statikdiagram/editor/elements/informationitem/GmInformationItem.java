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
package org.modelio.uml.statikdiagram.editor.elements.informationitem;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link InformationItem}.
 * 
 * @author cma
 */
@objid ("350dda1a-55b7-11e2-877f-002564c97630")
public class GmInformationItem extends GmPortContainer {
    @objid ("350dda1e-55b7-11e2-877f-002564c97630")
    private InformationItem element;

    /**
     * Current version of this Gm.
     */
    @objid ("350dda27-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("350dda2a-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("350dda2c-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("5d316f7e-5bd5-11e2-9e33-00137282c51b")
    static final InformationItemStructuredStyleKeys STRUCTURED_KEYS = new InformationItemStructuredStyleKeys();

    @objid ("5d316f80-5bd5-11e2-9e33-00137282c51b")
    static final InformationItemSimpleStyleKeys SIMPLE_KEYS = new InformationItemSimpleStyleKeys();

    @objid ("5d316f82-5bd5-11e2-9e33-00137282c51b")
    static final InformationItemImageStyleKeys IMAGE_KEYS = new InformationItemImageStyleKeys();

    @objid ("2dae03ad-56a0-49fc-bc7e-7f79b9b65884")
    static final InformationItemUserImageStyleKeys USERIMAGE_KEYS = new InformationItemUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the item is unmasked.
     * @param el the unmasked item.
     * @param ref a reference to the unmasked item.
     */
    @objid ("350dda2e-55b7-11e2-877f-002564c97630")
    public  GmInformationItem(IGmDiagram diagram, InformationItem el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmInformationItemPrimaryNode mainNode = new GmInformationItemPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmInformationItem.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    /**
     * Empty constructor needed for deserialization.
     */
    @objid ("350dda3a-55b7-11e2-877f-002564c97630")
    public  GmInformationItem() {
        // Nothing specific to do.
    }

    @objid ("350dda3d-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (Port.class.isAssignableFrom(type));
    }

    @objid ("350f60bb-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (Port.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner().equals(this.element));
    }

    @objid ("350f60c3-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("350f60ca-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("350f60d1-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = GmInformationItem.STRUCTURED_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = GmInformationItem.SIMPLE_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = GmInformationItem.IMAGE_KEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("350f60db-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmInformationItem.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmInformationItem.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmInformationItem.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmInformationItem.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
        
    }

    @objid ("350f60e3-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmInformationItem.");
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

    @objid ("350f60e9-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmInformationItem.", GmInformationItem.MINOR_VERSION);
        
    }

    @objid ("350f60ef-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (InformationItem) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmInformationItem.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
        
    }

    @objid ("350f60f4-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmInformationItem.MAJOR_VERSION;
    }

    @objid ("350f60f9-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (InformationItem) resolveRef(getRepresentedRef());
        
    }

    @objid ("3510e759-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmInformationItem.IMAGE_LABEL_ROLE);
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
    @objid ("3510e762-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                                        || GmInformationItem.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("3510e774-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("3510e77e-55b7-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
        
    }

    @objid ("3510e785-55b7-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
        
    }

    @objid ("3ef8f57b-0c1b-4f22-af39-d2938b1e496e")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmInformationItem.IMAGE_LABEL_ROLE);
    }

}
