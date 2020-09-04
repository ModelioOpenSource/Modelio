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

package org.modelio.diagram.editor.bpmn.elements.bpmnstartevent;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.name.GmNameLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@linkplain BpmnStartEvent initial node}.
 * 
 * @author fpoyer
 */
@objid ("61b7ae19-55b6-11e2-877f-002564c97630")
public class GmBpmnStartEvent extends GmPortContainer {
    @objid ("61b9347e-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("61b9347b-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("c50c0091-59a6-11e2-ae45-002564c97630")
    private static final GmBpmnStartEventImageStyleKeys IMAGE_KEYS = new GmBpmnStartEventImageStyleKeys();

    @objid ("c50c008f-59a6-11e2-ae45-002564c97630")
    private static final GmBpmnStartEventSimpleStyleKeys SIMPLE_KEYS = new GmBpmnStartEventSimpleStyleKeys();

    @objid ("c50c008d-59a6-11e2-ae45-002564c97630")
     static final GmBpmnStartEventStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnStartEventStructuredStyleKeys();

    @objid ("d6cd040e-2436-4b8e-bde9-56310a18fb97")
    private static final GmBpmnStartEventUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnStartEventUserImageStyleKeys();

    @objid ("56f800c9-1529-4937-a077-6fd95b6c77b9")
    private BpmnStartEvent element;

    /**
     * Constructor.
     * @param diagram the diagram in which the timeEvent is unmasked.
     * @param el the unmasked timeEvent.
     * @param ref a reference to the unmasked timeEvent.
     */
    @objid ("61b93480-55b6-11e2-877f-002564c97630")
    public GmBpmnStartEvent(IGmDiagram diagram, BpmnStartEvent el, MRef ref) {
        super(diagram, ref);
        
        GmBpmnStartEventPrimaryNode mainNode = new GmBpmnStartEventPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        this.addChild(mainNode);
        
        this.element = el;
        GmNameLabel label = new GmNameLabel(diagram, ref);
        label.setRoleInComposition(GmPortContainer.SATELLITE_ROLE);
        label.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        this.addChild(label);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("61b934ad-55b6-11e2-877f-002564c97630")
    public GmBpmnStartEvent() {
        // Nothing specific to do.
    }

    @objid ("61b9348c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("61b93494-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("61babb38-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("61b934bd-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("61b934b6-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("61b9349c-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getMainNodeRepresentationMode()) {
        case IMAGE:
            return IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
    }

    @objid ("61b934a5-55b6-11e2-877f-002564c97630")
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

    @objid ("7e69bfdc-c4ca-4077-8d98-6d23b2310e12")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("61babb45-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("61babb4f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("61b934b0-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnStartEvent.");
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

    @objid ("61babb2d-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmBpmnStartEvent.", MINOR_VERSION);
    }

    @objid ("61babb33-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnStartEvent) resolveRef(this.getRepresentedRef());
    }

}
