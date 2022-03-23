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
package org.modelio.diagram.api.dg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.ILinkPoint;

/**
 * Implementation of {@link ILinkPath} using an {@link ArrayList} of Points.
 */
@objid ("83bb65fd-0645-4308-864d-aaea2d62a9ef")
public class LinkPath implements ILinkPath {
    @objid ("b8003ebe-c309-4d42-94b8-8ee3000cd71e")
    private final List<Point> points;

    /**
     * Creates a new empty link path.
     */
    @objid ("4b7a6bd0-19db-4ae1-9911-ebac5ce5b736")
    public  LinkPath() {
        this.points = new ArrayList<>();
    }

    /**
     * Convert a Connection points to a list of {@link ILinkPoint}.
     * @param connection a connection figure
     */
    @objid ("fa40a652-07b5-404e-b7f3-bf2db3752fc0")
    public  LinkPath(Connection connection) {
        this.points = computeLinkPoints(connection);
    }

    /**
     * Creates a new link path.
     * @param points points of the path
     */
    @objid ("fbdc9a1b-0e16-45dc-b424-65cebf087897")
    public  LinkPath(Collection<Point> points) {
        this.points = new ArrayList<>(points.size());
        for (Point p : points) {
            this.points.add(p.getCopy());
        }
        
    }

    /**
     * Convert a Connection to a list of {@link ILinkPoint}.
     * @param connection a connection figure
     * @return its points as a list of ILinkPoint.
     */
    @objid ("0dec89fb-abc7-41e7-b7ed-604fe5b1a456")
    protected static List<Point> computeLinkPoints(Connection connection) {
        final PointList connPoints = connection.getPoints();
        ArrayList<Point> ret;
        
        ret = new ArrayList<>(connPoints.size());
        Point p = new PrecisionPoint();
        for (int i = 0, last=connPoints.size()-1; i <= last; i++) {
            connPoints.getPoint(p, i);
            connection.translateToAbsolute(p);
            ret.add(p.getCopy());
        }
        return ret;
    }

    @objid ("a549225f-96c6-49bf-82d5-55d645178b0f")
    @Override
    public List<Point> getPoints() {
        List<Point> oldPoints = new ArrayList<> (this.points.size());
        for (Point p : this.points) {
            oldPoints.add(p.getCopy());
        }
        return oldPoints;
    }

    @objid ("19b3611f-7668-41a3-8877-43b1f52a1ad4")
    @Override
    public void movePoint(int index, Point point) {
        this.points.get(index).setLocation(point);
    }

    @objid ("21af0563-1824-44f3-9c5f-9ff89e539c8c")
    @Override
    public void movePoint(int index, int x, int y) {
        this.points.get(index).setLocation(x, y);
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
            this.points.add(new Point(p));
        }
        
    }

    @objid ("c0eab552-67f8-4f4d-946c-48dcd9e365e7")
    @Override
    public String toString() {
        return this.points.toString();
    }

}
