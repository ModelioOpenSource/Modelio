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

package org.modelio.diagram.elements.core.figures.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;

/**
 * Same as {@link XYAnchor} but the reference is in coordinates <i>relative</i> to the owner.
 * 
 * @author cmarin
 */
@objid ("7f5adb49-1dec-11e2-8cad-001ec947c8cc")
public class PointAnchor extends XYAnchor {
    @objid ("40f51053-71d5-43b4-a6ed-eb9c4e24f467")
    private IFigure owner;

    /**
     * Constructs an anchor at the point p.
     * 
     * @param owner the owner figure
     * @param p The anchor location in coordinates relative to the owner.
     */
    @objid ("7f5adb50-1dec-11e2-8cad-001ec947c8cc")
    public PointAnchor(IFigure owner, Point p) {
        super(p);
        this.owner = owner;
    }

    @objid ("7f5adb59-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getReferencePoint() {
        Point ret = new Point(super.getReferencePoint());
        this.owner.translateToAbsolute(ret);
        return ret;
    }

    @objid ("7f5adb60-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLocation(Point reference) {
        return getReferencePoint();
    }

    /**
     * Get the reference point in coordinates <i>relative</i> to the owner.
     * 
     * @return the reference point in coordinates <i>relative</i> to the owner.
     */
    @objid ("1907e416-fd58-46a3-aa2b-0ed76200dfd1")
    public Point getRelativeReferencePoint() {
        return super.getReferencePoint();
    }

}
