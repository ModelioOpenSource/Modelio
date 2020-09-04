/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.common.portcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Specific constraint for the PortLayout. <br>
 * Basically, it is just a pair of a reference Border and requested bounds (described as a {@link Rectangle}
 * 
 * @author fpoyer
 */
@objid ("7eeacce6-1dec-11e2-8cad-001ec947c8cc")
public class PortConstraint implements IPersistent {
    @objid ("7eed2f00-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("8f5cef91-1e83-11e2-8cad-001ec947c8cc")
    private Border referenceBorder = Border.Undefined;

    @objid ("f1aa2828-c122-47fe-96fe-9e75528f5a11")
    private Rectangle requestedBounds;

    /**
     * This point, if defined, must be considered over the center of the requestedBounds (in which case only the size of
     * the requested bounds will be used).
     */
    @objid ("2634c1aa-2129-429e-ab27-f8fde7872599")
    private Point requestedCenter = null;

    /**
     * @return the request bounds.
     */
    @objid ("7eed2f02-1dec-11e2-8cad-001ec947c8cc")
    public Rectangle getRequestedBounds() {
        return this.requestedBounds;
    }

    /**
     * Sets the requested bounds.
     * 
     * @param requestedBounds the requested bounds.
     */
    @objid ("7eed2f09-1dec-11e2-8cad-001ec947c8cc")
    public void setRequestedBounds(Rectangle requestedBounds) {
        this.requestedBounds = requestedBounds;
    }

    /**
     * @return the reference border.
     */
    @objid ("7eed2f0f-1dec-11e2-8cad-001ec947c8cc")
    public Border getReferenceBorder() {
        return this.referenceBorder;
    }

    /**
     * Sets the reference border.
     * 
     * @param referenceBorder the reference border.
     */
    @objid ("7eed2f14-1dec-11e2-8cad-001ec947c8cc")
    public void setReferenceBorder(Border referenceBorder) {
        this.referenceBorder = referenceBorder;
    }

    @objid ("7eed2f18-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("7eed2f1e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        this.requestedBounds = (Rectangle) in.readProperty("requestedBounds");
        this.referenceBorder = (Border) in.readProperty("referenceBorder");
        this.requestedCenter = (Point) in.readProperty("requestedCenter");
    }

    @objid ("7eed2f22-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        // Small hack to workaround the serialisation not handling "null" as a
        // valid Rectangle...
        if (this.requestedBounds == null) {
            out.writeProperty("requestedBounds", (Object) null);
        } else {
            out.writeProperty("requestedBounds", this.requestedBounds);
        }
        
        out.writeProperty("referenceBorder", this.referenceBorder);
        
        // Small hack to workaround the serialisation not handling "null" as a
        // valid Point...
        if (this.requestedCenter == null) {
            out.writeProperty("requestedCenter", (Object) null);
        } else {
            out.writeProperty("requestedCenter", this.requestedCenter);
        }
    }

    /**
     * @return the requested centre, or null if undefined.
     */
    @objid ("7eed2f26-1dec-11e2-8cad-001ec947c8cc")
    public Point getRequestedCenter() {
        return this.requestedCenter;
    }

    /**
     * Sets the requested centre.
     * 
     * @param requestedCenter the requested centre.
     */
    @objid ("7eef9154-1dec-11e2-8cad-001ec947c8cc")
    public void setRequestedCenter(Point requestedCenter) {
        this.requestedCenter = requestedCenter;
    }

    @objid ("7eef915a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("c48d976a-26b1-487f-91e1-1c9a71104f9e")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PortConstraint [");
        builder.append("border=");
        builder.append(this.referenceBorder);
        builder.append(", ");
        
        builder.append("bounds=");
        builder.append(this.requestedBounds);
        builder.append(", ");
        
        if (this.requestedCenter != null) {
            builder.append("center=");
            builder.append(this.requestedCenter);
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * Enumeration of the borders a child can be referencing for its layout.
     * 
     * @author fpoyer
     */
    @objid ("7eef915f-1dec-11e2-8cad-001ec947c8cc")
    public enum Border {
        /**
         * Do not stick to any specific border.
         */
        Undefined,
        /**
         * The top border.
         */
        North,
        /**
         * Top right corner.
         */
        NorthEast,
        /**
         * the right border.
         */
        East,
        /**
         * Bottom right corner.
         */
        SouthEast,
        /**
         * The bottom border.
         */
        South,
        /**
         * Bottom left corner.
         */
        SouthWest,
        /**
         * The left border.
         */
        West,
        /**
         * Top left corner.
         */
        NorthWest;
    }

}
