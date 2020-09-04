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

package org.modelio.diagram.editor.bpmn.elements.bpmninclusivegateway;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnGatewayImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnGatewaySimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnGatewayStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnGatewayUserImageStyleKeys;
import org.modelio.diagram.elements.common.label.name.GmNameLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@linkplain BpmnInclusiveGateway initial node}.
 * 
 * @author fpoyer
 */
@objid ("60f76b4a-55b6-11e2-877f-002564c97630")
public class GmBpmnInclusiveGateway extends GmPortContainer {
    @objid ("60f8f1bc-55b6-11e2-877f-002564c97630")
    private BpmnInclusiveGateway element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("60f8f1bf-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("60f8f1c2-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("c4975d2d-59a6-11e2-ae45-002564c97630")
    public static final GmBpmnGatewayStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnGatewayStructuredStyleKeys();

    @objid ("c4975d2f-59a6-11e2-ae45-002564c97630")
    private static final GmBpmnGatewaySimpleStyleKeys SIMPLE_KEYS = new GmBpmnGatewaySimpleStyleKeys();

    @objid ("c4975d31-59a6-11e2-ae45-002564c97630")
    private static final GmBpmnGatewayImageStyleKeys IMAGE_KEYS = new GmBpmnGatewayImageStyleKeys();

    @objid ("a50a2408-ee9d-4852-ab9a-621df9f63074")
    private static final GmBpmnGatewayUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnGatewayUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the timeEvent is unmasked.
     * @param el the unmasked timeEvent.
     * @param ref a reference to the unmasked timeEvent.
     */
    @objid ("60f8f1c4-55b6-11e2-877f-002564c97630")
    public GmBpmnInclusiveGateway(IGmDiagram diagram, BpmnInclusiveGateway el, MRef ref) {
        super(diagram, ref);
        
        GmBpmnInclusiveGatewayPrimaryNode mainNode = new GmBpmnInclusiveGatewayPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        this.addChild(mainNode);
        
        this.element = el;
        GmNameLabel label = new GmNameLabel(diagram, ref);
        label.setRoleInComposition(GmPortContainer.SATELLITE_ROLE);
        label.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        this.addChild(label);
    }

    @objid ("60f8f1d0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("60f8f1d8-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("60f8f1e0-55b6-11e2-877f-002564c97630")
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

    @objid ("60f8f1e9-55b6-11e2-877f-002564c97630")
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
    @objid ("60f8f1f1-55b6-11e2-877f-002564c97630")
    public GmBpmnInclusiveGateway() {
        // Nothing specific to do.
    }

    @objid ("60f8f1f4-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnInclusiveGateway.");
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

    @objid ("60f8f1fa-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("60fa785e-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("60fa7873-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnInclusiveGateway.", MINOR_VERSION);
    }

    @objid ("60fa7879-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnInclusiveGateway) resolveRef(this.getRepresentedRef());
    }

    @objid ("60fa787e-55b6-11e2-877f-002564c97630")
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
    @objid ("60fa788b-55b6-11e2-877f-002564c97630")
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
    @objid ("60fa7895-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("de434aa1-0875-472d-9e7d-32cbbfdfb0b4")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
