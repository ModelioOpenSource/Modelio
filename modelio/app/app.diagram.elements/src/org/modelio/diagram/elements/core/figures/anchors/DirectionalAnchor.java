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

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.figures.geometry.Direction;

@objid ("3cc8d1c6-58e8-42b8-b0fc-e36cda1572ed")
public class DirectionalAnchor extends XYAnchor {
    @objid ("ec1dbebd-b012-44dd-a9a8-a9753090f400")
    private final Direction direction;

    @objid ("5f88adbb-1ded-4743-b3c7-5f2e88d3d1ef")
    public  DirectionalAnchor(Point p, Direction d) {
        super(p);
        this.direction = d;
        
    }

    @objid ("d231532a-7401-4104-ab4a-a4eae72c50a3")
    public Direction getDirection() {
        return this.direction;
    }

    @objid ("4bfe5407-22e0-44b3-8049-35d0a52f56ba")
    @Override
    public int hashCode() {
        return Objects.hash(this.direction, getOwner(), getReferencePoint());
    }

    @objid ("3fb776b6-e516-42e1-b80e-e54190bc8d4a")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DirectionalAnchor other = (DirectionalAnchor) obj;
        return this.direction == other.direction
                && Objects.equals(getReferencePoint(), other.getReferencePoint())
                && Objects.equals(getOwner(), other.getOwner());
        
    }

}
