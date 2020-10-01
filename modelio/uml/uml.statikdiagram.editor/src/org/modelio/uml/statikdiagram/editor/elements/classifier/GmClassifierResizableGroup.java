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

package org.modelio.uml.statikdiagram.editor.elements.classifier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.resizablegroup.GmResizableGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Resizable group that contains the attribute group, the operation group, the internal structure group/zone and the
 * inner elements zone/group. Allows resize of children, but not move/reorder/orphan/add.
 * 
 * @author fpoyer
 */
@objid ("3433a6f8-55b7-11e2-877f-002564c97630")
public class GmClassifierResizableGroup extends GmResizableGroup {
    @objid ("3433a6fc-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3433a6fe-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this Gm is created.
     * @param relatedRef the reference of the represented element.
     */
    @objid ("3433a700-55b7-11e2-877f-002564c97630")
    public GmClassifierResizableGroup(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        setVertical(true);
    }

    /**
     * Deserialisation c'tor.
     */
    @objid ("34352d5c-55b7-11e2-877f-002564c97630")
    public GmClassifierResizableGroup() {
        super();
    }

    @objid ("34352d5f-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidChild(final GmNodeModel node) {
        return true;
    }

    @objid ("34352d68-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("34352d71-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("34352d7a-55b7-11e2-877f-002564c97630")
    @Override
    public void write(final IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmClassifierResizableGroup.", Integer.valueOf(GmClassifierResizableGroup.MINOR_VERSION));
    }

    @objid ("34352d81-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmClassifierResizableGroup.");
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

    @objid ("34352d88-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
    }

    @objid ("34352d8e-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("34352d93-55b7-11e2-877f-002564c97630")
    @Override
    public boolean allowsMove() {
        return false;
    }

    @objid ("34352d98-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        // Only visible if no parent or parent is in structured mode.
        return super.isVisible() &&
                                                                       (getParentNode() == null || getParentNode().getRepresentationMode() == RepresentationMode.STRUCTURED);
    }

}
