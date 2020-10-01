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

package org.modelio.diagram.elements.core.figures.routers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AnchorListener;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Layout constraint for links in rake mode.
 * <p>
 * This constraint is shared by all links that compose the same rake. The rake merge point may be either on the source
 * side or the target side of the link.
 * 
 * @author cmarin
 */
@objid ("7fb7d708-1dec-11e2-8cad-001ec947c8cc")
public class RakeConstraint implements IPersistent {
    @objid ("7fb7d712-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Position where all links merge on the source side.
     */
    @objid ("635ba86b-1e83-11e2-8cad-001ec947c8cc")
    private XYAnchor sourceRakeAnchor;

    /**
     * Position where all links merge on the target side.
     */
    @objid ("635ba86d-1e83-11e2-8cad-001ec947c8cc")
    private XYAnchor targetRakeAnchor;

    /**
     * Copy constructor.
     * <p>
     * The copy references the same anchors as the original.
     * 
     * @param other The original.
     */
    @objid ("7fb7d714-1dec-11e2-8cad-001ec947c8cc")
    public RakeConstraint(RakeConstraint other) {
        this.sourceRakeAnchor = other.sourceRakeAnchor;
        this.targetRakeAnchor = other.targetRakeAnchor;
    }

    /**
     * Get a copy of this constraint whose anchors are a copy of own anchors.
     * 
     * @return a copy of this constraint.
     */
    @objid ("7fb7d718-1dec-11e2-8cad-001ec947c8cc")
    public RakeConstraint getCopy() {
        RakeConstraint ret = new RakeConstraint();
        ret.sourceRakeAnchor = this.sourceRakeAnchor == null ? null
                : new XYAnchor(this.sourceRakeAnchor.getReferencePoint());
        ret.targetRakeAnchor = this.targetRakeAnchor == null ? null
                : new XYAnchor(this.targetRakeAnchor.getReferencePoint());
        return ret;
    }

    /**
     * Default constructor.
     */
    @objid ("7fb7d71d-1dec-11e2-8cad-001ec947c8cc")
    public RakeConstraint() {
    }

    /**
     * Set the rake anchor.
     * 
     * @param rakeAnchor the rake anchor.
     */
    @objid ("7fb7d720-1dec-11e2-8cad-001ec947c8cc")
    public void setSourceRakeAnchor(XYAnchor rakeAnchor) {
        this.sourceRakeAnchor = rakeAnchor;
    }

    /**
     * @return the rake anchor on the source side.
     */
    @objid ("7fb7d726-1dec-11e2-8cad-001ec947c8cc")
    public XYAnchor getSourceRakeAnchor() {
        return this.sourceRakeAnchor;
    }

    /**
     * Set the rake anchor.
     * 
     * @param rakeAnchor the rake anchor.
     */
    @objid ("7fb7d72d-1dec-11e2-8cad-001ec947c8cc")
    public void setTargetRakeAnchor(XYAnchor rakeAnchor) {
        this.targetRakeAnchor = rakeAnchor;
    }

    /**
     * @return the rake anchor on the source side.
     */
    @objid ("7fb7d733-1dec-11e2-8cad-001ec947c8cc")
    public XYAnchor getTargetRakeAnchor() {
        return this.targetRakeAnchor;
    }

    @objid ("7fba3934-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("7fba393a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        Point p = (Point) in.readProperty("src");
        if (p != null)
            this.sourceRakeAnchor = new XYAnchor(p);
        
        p = (Point) in.readProperty("target");
        if (p != null)
            this.targetRakeAnchor = new XYAnchor(p);
    }

    @objid ("7fba393e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        if (this.sourceRakeAnchor != null)
            out.writeProperty("src", this.sourceRakeAnchor.getReferencePoint());
        if (this.targetRakeAnchor != null)
            out.writeProperty("target", this.targetRakeAnchor.getReferencePoint());
    }

    /**
     * Register a connection as listener of this constraint.
     * <p>
     * Called by {@link RakeRouter} to invalidate all connections on the same rake.
     * 
     * @param connection The connection that is attached to this rake.
     */
    @objid ("7fba3942-1dec-11e2-8cad-001ec947c8cc")
    void addListener(AnchorListener connection) {
        if (this.sourceRakeAnchor != null)
            this.sourceRakeAnchor.addAnchorListener(connection);
        if (this.targetRakeAnchor != null)
            this.targetRakeAnchor.addAnchorListener(connection);
    }

    /**
     * Remove a connection as listener of this constraint.
     * 
     * @param connection The connection that is detached from this rake.
     */
    @objid ("7fbc9b90-1dec-11e2-8cad-001ec947c8cc")
    void removeListener(AnchorListener connection) {
        if (this.sourceRakeAnchor != null)
            this.sourceRakeAnchor.removeAnchorListener(connection);
        if (this.targetRakeAnchor != null)
            this.targetRakeAnchor.removeAnchorListener(connection);
    }

    /**
     * Get the source side rake position or null if there is no rake on the source side.
     * 
     * @return the source side rake position in <i>relative</i> coordinates.
     */
    @objid ("7fbc9b96-1dec-11e2-8cad-001ec947c8cc")
    public Point getSourceRakeLocation() {
        if (this.sourceRakeAnchor != null)
            return this.sourceRakeAnchor.getReferencePoint();
        return null;
    }

    /**
     * Get the target side rake position or null if there is no rake on the source side.
     * 
     * @return the target side rake position in <i>relative</i> coordinates.
     */
    @objid ("7fbc9b9d-1dec-11e2-8cad-001ec947c8cc")
    public Point getTargetRakeLocation() {
        if (this.targetRakeAnchor != null)
            return this.targetRakeAnchor.getReferencePoint();
        return null;
    }

    @objid ("7fbc9ba4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
