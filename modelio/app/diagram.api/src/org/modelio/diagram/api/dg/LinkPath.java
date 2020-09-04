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

package org.modelio.diagram.api.dg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.api.modelio.diagram.ILinkPath;

/**
 * Implementation of {@link ILinkPath} using an {@link ArrayList} of Points.
 */
@objid ("83bb65fd-0645-4308-864d-aaea2d62a9ef")
public class LinkPath implements ILinkPath {
    @objid ("b8003ebe-c309-4d42-94b8-8ee3000cd71e")
    private List<Point> points = new ArrayList<>();

    /**
     * Creates a new empty link path.
     */
    @objid ("4b7a6bd0-19db-4ae1-9911-ebac5ce5b736")
    public LinkPath() {
    }

    /**
     * Creates a new link path.
     * @param points points of the path
     */
    @objid ("fbdc9a1b-0e16-45dc-b424-65cebf087897")
    public LinkPath(Collection<Point> points) {
        this.points.addAll(points);
    }

    @objid ("a549225f-96c6-49bf-82d5-55d645178b0f")
    @Override
    public List<Point> getPoints() {
        List<Point> oldPoints = new ArrayList<> ();
        for (Point p : this.points) {
            oldPoints.add(new AbsoluteBendpoint(p.x, p.y));
        }
        return oldPoints;
    }

    @objid ("19b3611f-7668-41a3-8877-43b1f52a1ad4")
    @Override
    public void movePoint(int index, Point point) {
        movePoint(index, new AbsoluteBendpoint(point.x, point.y));
    }

    @objid ("21af0563-1824-44f3-9c5f-9ff89e539c8c")
    @Override
    public void movePoint(int index, int x, int y) {
        movePoint(index, new AbsoluteBendpoint(x, y));
    }

    @objid ("298efc4f-4092-4226-a474-576f946fba72")
    @Override
    public void removePoint(int index) {
        this.points.remove(index);
    }

    @objid ("25727945-8584-4992-8627-4fd3020b17b9")
    @Override
    public void setPoints(Collection<Point> points) {
        this.points.clear();
        for (Point p : points) {
            this.points.add(new AbsoluteBendpoint(p.x, p.y));
        }
    }

    @objid ("c0eab552-67f8-4f4d-946c-48dcd9e365e7")
    @Override
    public String toString() {
        return this.points.toString();
    }

    @objid ("bfa9d26f-f7e2-4581-86a3-a7544a622500")
    private void movePoint(final int index, final AbsoluteBendpoint point) {
        this.points.set(index, point);
    }

}
