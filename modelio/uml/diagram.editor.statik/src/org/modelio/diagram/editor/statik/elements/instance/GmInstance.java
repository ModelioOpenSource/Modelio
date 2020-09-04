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

package org.modelio.diagram.editor.statik.elements.instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link Instance}.
 * 
 * @author fpoyer
 */
@objid ("3530f2bb-55b7-11e2-877f-002564c97630")
public class GmInstance extends GmPortContainer {
    @objid ("3530f2bf-55b7-11e2-877f-002564c97630")
    private Instance element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35327921-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35327924-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5db22e7c-5bd5-11e2-9e33-00137282c51b")
     static final GmInstanceStructuredStyleKeys STRUCTURED_KEYS = new GmInstanceStructuredStyleKeys();

    @objid ("5db6f32f-5bd5-11e2-9e33-00137282c51b")
    private static final GmInstanceSimpleStyleKeys SIMPLE_KEYS = new GmInstanceSimpleStyleKeys();

    @objid ("5db6f331-5bd5-11e2-9e33-00137282c51b")
    private static final GmInstanceImageStyleKeys IMAGE_KEYS = new GmInstanceImageStyleKeys();

    @objid ("b0148497-d8d3-4af5-940a-74b5d2d85364")
    private static final GmInstanceUserImageStyleKeys USERIMAGE_KEYS = new GmInstanceUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the class is unmasked.
     * @param el the unmasked class.
     * @param ref a reference to the unmasked class.
     */
    @objid ("35327926-55b7-11e2-877f-002564c97630")
    public GmInstance(IGmDiagram diagram, Instance el, MRef ref) {
        super(diagram, ref);
        GmInstancePrimaryNode mainNode = new GmInstancePrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        this.addChild(mainNode);
        this.element = el;
        
        final GmImageInstanceLabel instanceLabel = new GmImageInstanceLabel(diagram, this.element, ref);
        instanceLabel.setRoleInComposition(SATELLITE_ROLE);
        instanceLabel.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        addChild(instanceLabel);
    }

    @objid ("35327932-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (Port.class.isAssignableFrom(type));
    }

    @objid ("3532793a-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (Port.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner().equals(this.element));
    }

    @objid ("35327942-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = STRUCTURED_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = SIMPLE_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = IMAGE_KEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("3532794c-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getMainNodeRepresentationMode()) {
        case IMAGE:
            return IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    /**
     * Empty constructor needed for deserialization.
     */
    @objid ("35327954-55b7-11e2-877f-002564c97630")
    public GmInstance() {
        // Nothing specific to do.
    }

    @objid ("35327957-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInstance.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
    }

    @objid ("3532795d-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("3533ffbe-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("3533ffcb-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        switch (getMainNodeRepresentationMode()) {
        case SIMPLE:
        case STRUCTURED:
            // Exclude the image mode labels
            List<GmNodeModel> childrens = super.getVisibleChildren();
            List<GmNodeModel> labels = new ArrayList<>();
            for (GmNodeModel children : childrens) {
                if (children instanceof GmImageInstanceLabel) {
                    labels.add(children);
                }
            }
            childrens.removeAll(labels);
            return childrens;
        case IMAGE:
        default:
            return super.getVisibleChildren();
        }
    }

    @objid ("3533ffd3-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInstance.", GmInstance.MINOR_VERSION);
    }

    @objid ("3533ffd9-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (Instance) resolveRef(this.getRepresentedRef());
    }

    @objid ("3533ffde-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("3533ffeb-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("3533fff5-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("3535865d-55b7-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
    }

    @objid ("35358664-55b7-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
    }

    @objid ("66b9bf00-2d62-4c2f-9651-742b0ed10c6e")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
