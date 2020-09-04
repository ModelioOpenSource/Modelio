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

package org.modelio.diagram.editor.bpmn.elements.bpmnintermediatethrowevent;

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
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@linkplain BpmnIntermediateThrowEvent initial node}.
 * 
 * @author fpoyer
 */
@objid ("610b4168-55b6-11e2-877f-002564c97630")
public class GmBpmnIntermediateThrowEvent extends GmPortContainer {
    @objid ("610b4172-55b6-11e2-877f-002564c97630")
    private BpmnIntermediateThrowEvent element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("610b4175-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("610b4178-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("c57e428f-59a6-11e2-ae45-002564c97630")
     static final GmBpmnIntermediateThrowEventStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnIntermediateThrowEventStructuredStyleKeys();

    @objid ("c57e4291-59a6-11e2-ae45-002564c97630")
    private static final GmBpmnIntermediateThrowEventSimpleStyleKeys SIMPLE_KEYS = new GmBpmnIntermediateThrowEventSimpleStyleKeys();

    @objid ("c57e4293-59a6-11e2-ae45-002564c97630")
    private static final GmBpmnIntermediateThrowEventImageStyleKeys IMAGE_KEYS = new GmBpmnIntermediateThrowEventImageStyleKeys();

    @objid ("e0893872-edcc-4a72-9095-d61ddaae2f4f")
    private static final GmBpmnIntermediateThrowEventUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnIntermediateThrowEventUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the timeEvent is unmasked.
     * @param el the unmasked timeEvent.
     * @param ref a reference to the unmasked timeEvent.
     */
    @objid ("610b417a-55b6-11e2-877f-002564c97630")
    public GmBpmnIntermediateThrowEvent(IGmDiagram diagram, BpmnIntermediateThrowEvent el, MRef ref) {
        super(diagram, ref);
        
        GmBpmnIntermediateThrowEventPrimaryNode mainNode = new GmBpmnIntermediateThrowEventPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        this.addChild(mainNode);
        
        this.element = el;
        GmNameLabel label = new GmNameLabel(diagram, ref);
        label.setRoleInComposition(GmPortContainer.SATELLITE_ROLE);
        label.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        this.addChild(label);
    }

    @objid ("610b4186-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("610cc7e0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("610cc7e8-55b6-11e2-877f-002564c97630")
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

    @objid ("610cc7f1-55b6-11e2-877f-002564c97630")
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
     * Empty constructor needed for deserialisation.
     */
    @objid ("610cc7f9-55b6-11e2-877f-002564c97630")
    public GmBpmnIntermediateThrowEvent() {
        // Nothing specific to do.
    }

    @objid ("610cc7fc-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnIntermediateThrowEvent.");
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

    @objid ("610cc802-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("610cc809-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("610e4e7a-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnIntermediateThrowEvent.", MINOR_VERSION);
    }

    @objid ("610e4e80-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnIntermediateThrowEvent) resolveRef(this.getRepresentedRef());
    }

    @objid ("610e4e85-55b6-11e2-877f-002564c97630")
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
    @objid ("610e4e92-55b6-11e2-877f-002564c97630")
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
    @objid ("610e4e9c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("53a5820b-d56f-4135-b3bc-00b1c88b1418")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
