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

package org.modelio.diagram.editor.activity.elements.partition;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.elements.partition.bodyhybridcontainer.GmBodyHybridContainer;
import org.modelio.diagram.editor.activity.elements.partition.header.GmPartitionHeader;
import org.modelio.diagram.editor.activity.elements.partitioncontainer.GmPartitionContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a Partition.
 */
@objid ("2afd8449-55b6-11e2-877f-002564c97630")
public class GmPartition extends GmCompositeNode {
    /**
     * The represented partition.
     */
    @objid ("2afd844d-55b6-11e2-877f-002564c97630")
     ActivityPartition element;

    /**
     * The orientation of this container.
     */
    @objid ("2afdd26b-55b6-11e2-877f-002564c97630")
    private boolean vertical;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2afdd26d-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2afdf97b-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("313eb85d-58a2-11e2-9574-002564c97630")
    private static GmPartitionStructuredStyleKeys STRUCTKEYS = new GmPartitionStructuredStyleKeys();

    /**
     * Header
     */
    @objid ("313eb85e-58a2-11e2-9574-002564c97630")
    private GmPartitionHeader header;

    /**
     * Free zone
     */
    @objid ("313eb860-58a2-11e2-9574-002564c97630")
    private GmPartitionContainer body;

    /**
     * Default constructor.
     * @param diagram the diagram in which this partition will be unmasked.
     * @param thePartition the unmasked partition (can be null).
     * @param ref a reference to the unmasked partition (cannot be null).
     */
    @objid ("2afdf97d-55b6-11e2-877f-002564c97630")
    public GmPartition(IGmDiagram diagram, ActivityPartition thePartition, MRef ref) {
        super(diagram, ref);
        this.element = thePartition;
        this.header = new GmPartitionHeader(diagram, ref);
        this.body = new GmBodyHybridContainer(diagram, ref);
        super.addChild(this.header);
        super.addChild(this.body);
    }

    @objid ("2afe479c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return acceptMetaclass(type);
    }

    @objid ("2afe6ead-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        Class<? extends MObject> type = el.getClass();
        return Objects.equals(el.getCompositionOwner(), this.element) && acceptMetaclass(type);
    }

    @objid ("2afe95bd-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTKEYS.getStyleKey(metakey);
    }

    @objid ("2afee3da-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTKEYS.getStyleKeys();
    }

    @objid ("2aff0aec-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("2aff31fd-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (this.acceptMetaclass(metaclass))
            return this.body;
        // else
        return null;
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("2aff8019-55b6-11e2-877f-002564c97630")
    public GmPartition() {
        // Nothing to do.
    }

    @objid ("2affa729-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPartition.");
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

    /**
     * @return The partition content area.
     */
    @objid ("2affa72f-55b6-11e2-877f-002564c97630")
    public GmCompositeNode getContentsArea() {
        return this.body;
    }

    /**
     * Tells whether elements of the given metaclass can be unmasked inside this graphic node.
     * <p>
     * This method should return true only if it is consistent to display the given metaclass elements inside this graphic element.
     * <p>
     * <b>eg:</b> IAttributes can be displayed in a GmClass .
     * @param type The metaclass to unmask.
     * @return true only if it is consistent to display elements of the given metaclass inside this graphic element, false in the other cases.
     */
    @objid ("2b00436d-55b6-11e2-877f-002564c97630")
    private boolean acceptMetaclass(Class<? extends MObject> type) {
        boolean accept = false;
        // If there are no subpartitions yet, we can accept inner nodes and
        // edges.
        if (this.body.getChildren(GmPartitionContainer.SUB_PARTITION).isEmpty()) {
            accept = (ActivityNode.class.isAssignableFrom(type) && !Pin.class.isAssignableFrom(type)) ||
                    ActivityEdge.class.isAssignableFrom(type);
        }
        
        // We can always accept sub partitions: if things come to the worst, all
        // inner nodes and edges will be transfered to the first subpartition.
        accept = accept || ActivityPartition.class.isAssignableFrom(type);
        return accept;
    }

    @objid ("2b00b89d-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        out.writeProperty("isVertical", this.vertical ? Boolean.TRUE : Boolean.FALSE);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPartition.", GmPartition.MINOR_VERSION);
    }

    /**
     * Return whether this partition is vertical (header above body) or horizontal (header left of body).
     * @return whether this partition is vertical (header above body) or horizontal (header left of body).
     */
    @objid ("2b00dfae-55b6-11e2-877f-002564c97630")
    public boolean isVertical() {
        return this.vertical;
    }

    /**
     * Sets whether this partition is vertical (header above body) or horizontal (header left of body).
     * @param vertical true if this partition must be vertical (header above body), false if it must be horizontal (header left of body).
     */
    @objid ("2b0106bc-55b6-11e2-877f-002564c97630")
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
        this.header.setVertical(this.vertical);
        this.body.setVertical(!this.vertical);
        // This is to force the new layout orientation to be applied.
        super.removeChild(this.header);
        super.removeChild(this.body);
        super.addChild(this.header);
        super.addChild(this.body);
    }

    @objid ("2b012dca-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2b0154db-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2b017bec-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmPartitionHeader) this.getChildren().get(0);
        this.body = (GmPartitionContainer) this.getChildren().get(1);
        this.element = (ActivityPartition) resolveRef(this.getRepresentedRef());
        this.vertical = ((Boolean) in.readProperty("isVertical")).booleanValue();
    }

    @objid ("2b01a2fb-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
