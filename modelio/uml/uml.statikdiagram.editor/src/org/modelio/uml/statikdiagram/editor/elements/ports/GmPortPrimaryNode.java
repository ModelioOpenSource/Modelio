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

package org.modelio.uml.statikdiagram.editor.elements.ports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a {@link Port}.
 * 
 * @author phv
 */
@objid ("3646a85e-55b7-11e2-877f-002564c97630")
public class GmPortPrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 1.
     */
    @objid ("3646a864-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("3646a867-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("3646a869-55b7-11e2-877f-002564c97630")
    private int position = PositionConstants.NONE;

    /**
     * Creates a port model.
     * 
     * @param diagram The owning diagram
     * @param ref The represented port reference
     */
    @objid ("3646a86a-55b7-11e2-877f-002564c97630")
    public GmPortPrimaryNode(final IGmDiagram diagram, final MRef ref) {
        super(diagram, ref);
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("3646a875-55b7-11e2-877f-002564c97630")
    public GmPortPrimaryNode() {
        // Empty constructor needed for serialisation.
    }

    @objid ("3646a878-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("36482eda-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromObModel() {
        // forcing visual refresh in case Image changed 
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("36482edd-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("36482ee2-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPortPrimaryNode.");
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
                // reading as last handled version: 0
                read_1(in);
                break;
            }
        }
    }

    @objid ("36482ee8-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        out.writeProperty("orientation", Integer.valueOf(this.position));
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmPortPrimaryNode.", Integer.valueOf(GmPortPrimaryNode.MINOR_VERSION));
    }

    @objid ("36482eee-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        // In version 0, orientation was not handled, initialize it to PositionConstants.NONE
        this.position = PositionConstants.NONE;
    }

    @objid ("36482ef3-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * @return the current position of this Port. Returned value is one of {@link PositionConstants} values among:
     * NONE, NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST and NORTH_WEST.
     */
    @objid ("36482ef8-55b7-11e2-877f-002564c97630")
    public int getPosition() {
        return this.position;
    }

    /**
     * Sets the new position of this Port. Passed value must be one of {@link PositionConstants} values among:
     * NONE, NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST and NORTH_WEST.
     * 
     * @param position the new position of this Port.
     */
    @objid ("36482efd-55b7-11e2-877f-002564c97630")
    public void setPosition(final int position) {
        this.position = position;
    }

    @objid ("36482f02-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.position = ((Integer) in.readProperty("orientation")).intValue();
    }

}
