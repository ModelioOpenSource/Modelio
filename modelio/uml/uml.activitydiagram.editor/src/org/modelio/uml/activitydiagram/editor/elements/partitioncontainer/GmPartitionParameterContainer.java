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
package org.modelio.uml.activitydiagram.editor.elements.partitioncontainer;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the GmPortContainer class for Partition (will hold the activity parameters).
 * 
 * @author fpoyer
 */
@objid ("2b269019-55b6-11e2-877f-002564c97630")
public class GmPartitionParameterContainer extends GmPortContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2b26b72b-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2b26b72e-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * @param diagram the diagram in which this ActivityParameter container is unmasked.
     * @param relatedRef represented element reference, must not be null.
     */
    @objid ("2b26de3a-55b6-11e2-877f-002564c97630")
    public  GmPartitionParameterContainer(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        GmDiagramPartitionContainer mainNode = new GmDiagramPartitionContainer(diagram, relatedRef);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        this.addChild(mainNode);
        
    }

    @objid ("2b27054c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (ActivityParameterNode.class.isAssignableFrom(type));
    }

    @objid ("2b272c5d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (ActivityParameterNode.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner()
                        .equals(this.getRelatedElement()
                                .getOrigin()));
        
    }

    @objid ("2b2ad5da-55b6-11e2-877f-002564c97630")
    @Override
    public ActivityDiagram getRelatedElement() {
        return (ActivityDiagram) super.getRelatedElement();
    }

    @objid ("2b2afcea-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return null;
    }

    @objid ("2b2b23fd-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("2b2b9929-55b6-11e2-877f-002564c97630")
    public  GmPartitionParameterContainer() {
        // Nothing specific to do.
    }

    @objid ("2b2bc03b-55b6-11e2-877f-002564c97630")
    @Override
    public void removeChild(GmNodeModel child) {
        super.removeChild(child);
        // If the removed child is the main partition container, delete self.
        if (GmPortContainer.MAIN_NODE_ROLE.equals(child.getRoleInComposition())) {
            delete();
        }
        
    }

    @objid ("2b2be74c-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPartitionParameterContainer.");
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

    @objid ("2b2c0e5c-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPartitionParameterContainer.", GmPartitionParameterContainer.MINOR_VERSION);
        
    }

    @objid ("2b2c356a-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2b2c356f-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("2b2caa9a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("2b2cf8b9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("b8cfb89e-5ecf-4c57-b142-f76447173cb0")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
