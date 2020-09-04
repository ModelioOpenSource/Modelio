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

package org.modelio.diagram.editor.activity.elements.partitioncontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.elements.partition.GmPartition;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * The node (doesn't have anything corresponding in the ObModel) that contains partitions. Used on the diagram background to hold top-level partitions AND in partitions to hold sub-partitions.
 * 
 * @author fpoyer
 */
@objid ("2b22bf89-55b6-11e2-877f-002564c97630")
public class GmPartitionContainer extends GmCompositeNode {
    /**
     * The orientation of this container.
     */
    @objid ("2b22e69a-55b6-11e2-877f-002564c97630")
    private boolean vertical;

    /**
     * Constant used to describe role of subpartitions.
     */
    @objid ("2b22e69c-55b6-11e2-877f-002564c97630")
    public static final String SUB_PARTITION = "SubPartition";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2b230da9-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2b230dac-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("2b2334b9-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (ActivityPartition.class.isAssignableFrom(metaclass))
            return this;
        // else
        return null;
    }

    @objid ("2b235bcb-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return ActivityPartition.class.isAssignableFrom(type);
    }

    @objid ("2b2382dd-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (ActivityPartition.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner()
                        .equals(this.getRelatedElement()));
    }

    @objid ("2b23a9ed-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    /**
     * @param diagram the diagram in which this partition container is used.
     * @param relatedRef represented element reference, must not be null.
     */
    @objid ("2b23d0fd-55b6-11e2-877f-002564c97630")
    public GmPartitionContainer(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty constructor needed for serialization.
     */
    @objid ("2b241f19-55b6-11e2-877f-002564c97630")
    public GmPartitionContainer() {
        // Nothing to do.
    }

    @objid ("2b246d39-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPartitionContainer.");
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

    @objid ("2b246d3f-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        out.writeProperty("isVertical", this.vertical ? Boolean.TRUE : Boolean.FALSE);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPartitionContainer.", GmPartitionContainer.MINOR_VERSION);
    }

    /**
     * Returns a list of the contained GmPartition nodes.
     * @return a list of the contained GmPartition nodes.
     */
    @objid ("2b24944e-55b6-11e2-877f-002564c97630")
    public List<GmPartition> getPartitions() {
        List<GmPartition> partitions = new ArrayList<>();
        for (GmNodeModel p : getChildren()) {
            partitions.add((GmPartition) p);
        }
        return partitions;
    }

    /**
     * Returns whether this container is horizontal.
     * @return true if this container is horizontal, false otherwise.
     */
    @objid ("2b24e269-55b6-11e2-877f-002564c97630")
    public boolean isVertical() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.vertical;
    }

    /**
     * Sets the orientation of this container.
     * @param value true if this container must be vertical, false otherwise.
     */
    @objid ("2b250979-55b6-11e2-877f-002564c97630")
    public void setVertical(boolean value) {
        if (this.vertical != value) {
            this.vertical = value;
            for (GmNodeModel child : getChildren()) {
                if (child instanceof GmPartition) {
                    ((GmPartition) child).setVertical(!this.vertical);
                }
            }
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, this.getLayoutData());
        }
    }

    @objid ("2b25097d-55b6-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel child) {
        // Assign correct role to child: either sub partition, or anything else!
        if (child instanceof GmPartition) {
            child.setRoleInComposition(SUB_PARTITION);
            ((GmPartition) child).setVertical(!this.vertical);
        }
        super.addChild(child);
    }

    @objid ("2b25308d-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return null;
    }

    @objid ("2b257eaa-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    @objid ("2b25a5bd-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.vertical = ((Boolean) in.readProperty("isVertical")).booleanValue();
    }

    @objid ("2b25cccc-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
