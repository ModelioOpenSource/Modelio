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

package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;

/**
 * Repositions a GmNode attached to a {@link GmLink} when the GmLink is moved.
 * <p>
 * Provides for alignment at the start
 * (source), middle, or end (target) of the Connection.
 * 
 * @see org.eclipse.draw2d.ConnectionLocator
 * @deprecated does not handle fire size constraint.
 * Seems to not be used since long before Modelio 3.3.
 * Might be deleted.
 */
@objid ("8004221c-1dec-11e2-8cad-001ec947c8cc")
@Deprecated
public class GmConnectionLocator extends GmAbstractLocator {
    /**
     * The alignment. Possible values are {@link org.eclipse.draw2d.ConnectionLocator#SOURCE SOURCE},
     * {@link org.eclipse.draw2d.ConnectionLocator#MIDDLE MIDDLE}, and
     * {@link org.eclipse.draw2d.ConnectionLocator#TARGET TARGET}.
     */
    @objid ("8004221e-1dec-11e2-8cad-001ec947c8cc")
    private int alignment;

    /**
     * The number of pixels to leave between the figure being located and the reference point.
     */
    @objid ("80042220-1dec-11e2-8cad-001ec947c8cc")
    private int gap;

    /**
     * The position of the figure with respect to the center point. Possible values can be found in
     * {@link PositionConstants} and include CENTER, NORTH, SOUTH, EAST, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, or
     * SOUTH_WEST.
     */
    @objid ("80042222-1dec-11e2-8cad-001ec947c8cc")
    private int relativePosition = PositionConstants.CENTER;

    @objid ("80042224-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Returns the alignment of ConnectionLocator.
     * 
     * @return The alignment
     */
    @objid ("80042226-1dec-11e2-8cad-001ec947c8cc")
    public int getAlignment() {
        return this.alignment;
    }

    /**
     * Returns the number of pixels to leave between the figure being located and the reference point.
     * 
     * @return The gap
     */
    @objid ("8004222b-1dec-11e2-8cad-001ec947c8cc")
    public int getGap() {
        return this.gap;
    }

    /**
     * Returns the position of the figure with respect to the center point. Possible values can be found in
     * {@link PositionConstants} and include CENTER, NORTH, SOUTH, EAST, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, or
     * SOUTH_WEST.
     * 
     * @return An int constant representing the relative position
     */
    @objid ("80042230-1dec-11e2-8cad-001ec947c8cc")
    public int getRelativePosition() {
        return this.relativePosition;
    }

    @objid ("8004223b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        super.read(in);
        
        this.alignment = (Integer) in.readProperty("align");
        this.gap = (Integer) in.readProperty("gap");
        this.relativePosition = (Integer) in.readProperty("pos");
    }

    /**
     * Sets the alignment. Possible values are {@link org.eclipse.draw2d.ConnectionLocator#SOURCE SOURCE},
     * {@link org.eclipse.draw2d.ConnectionLocator#MIDDLE MIDDLE}, and
     * {@link org.eclipse.draw2d.ConnectionLocator#TARGET TARGET}.
     * 
     * @param align The alignment
     */
    @objid ("8004223f-1dec-11e2-8cad-001ec947c8cc")
    public void setAlignment(int align) {
        this.alignment = align;
    }

    /**
     * Sets the gap between the reference point and the figure being placed.
     * <p>
     * Only used if {@link #getRelativePosition()} returns
     * something other than {@link PositionConstants#CENTER}.
     * 
     * @param i The gap
     */
    @objid ("80042243-1dec-11e2-8cad-001ec947c8cc")
    public void setGap(int i) {
        this.gap = i;
    }

    /**
     * Sets the position of the figure with respect to the center point. Possible values can be found in
     * {@link PositionConstants} and include CENTER, NORTH, SOUTH, EAST, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, or
     * SOUTH_WEST.
     * 
     * @param pos The relative position
     */
    @objid ("80042247-1dec-11e2-8cad-001ec947c8cc")
    public void setRelativePosition(int pos) {
        this.relativePosition = pos;
    }

    @objid ("8004224b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        out.writeProperty("align", this.alignment);
        out.writeProperty("gap", this.gap);
        out.writeProperty("pos", this.relativePosition);
    }

    @objid ("8004224f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Default constructor.
     */
    @objid ("43dfc86c-5d05-4806-8616-1a48e61d426f")
    public GmConnectionLocator() {
        super();
    }

    /**
     * Copy constructor.
     * 
     * @param src the object to copy.
     */
    @objid ("fe2ae304-5ee7-43a2-8b1b-db18eed6ab77")
    public GmConnectionLocator(GmConnectionLocator src) {
        super(src);
        
        this.alignment = src.alignment;
        this.gap = src.gap;
        this.relativePosition = src.relativePosition;
    }

    @objid ("3edc6e93-4ed8-459a-990b-5006402bfcd7")
    @Override
    public IGmLocator copy() {
        return new GmConnectionLocator(this);
    }

}
