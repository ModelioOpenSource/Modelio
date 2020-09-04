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

package org.modelio.diagram.elements.core.figures.geometry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Class implementing a list of <code>PrecisionPoint</code> similarly to <code>PointList</code> class.
 * 
 * @author aboyko
 */
@objid ("7f9b3ade-1dec-11e2-8cad-001ec947c8cc")
public class PrecisionPointList extends PointList {
    @objid ("7f9d9cfd-1dec-11e2-8cad-001ec947c8cc")
    private double[] points = new double[8];

    @objid ("7f9d9d03-1dec-11e2-8cad-001ec947c8cc")
    private int size = 0;

    @objid ("7f9d9d04-1dec-11e2-8cad-001ec947c8cc")
     static final long serialVersionUID = 1;

    @objid ("e8b74cf9-ceb6-4c9f-afde-69ce9c9fa079")
    private PrecisionRectangle bounds;

    /**
     * Constructs an empty PrecisionPointList.
     */
    @objid ("7f9d9d06-1dec-11e2-8cad-001ec947c8cc")
    public PrecisionPointList() {
    }

    /**
     * Constructs a PointList with the given points.
     * @param points double array where two consecutive double form the coordinates of a point
     */
    @objid ("7f9d9d09-1dec-11e2-8cad-001ec947c8cc")
    public PrecisionPointList(double[] points) {
        this.points = points;
        this.size = points.length / 2;
    }

    /**
     * Constructs a PrecisionPointList with initial capacity <i>size</i>, but no points.
     * @param size Number of points to hold.
     */
    @objid ("7f9d9d0f-1dec-11e2-8cad-001ec947c8cc")
    public PrecisionPointList(int size) {
        this.points = new double[size * 2];
    }

    /**
     * Creates a precision point list.
     * @param pointList a point list.
     */
    @objid ("7f9d9d13-1dec-11e2-8cad-001ec947c8cc")
    public PrecisionPointList(PointList pointList) {
        this();
        addAll(pointList);
    }

    /**
     * Appends all of the given points to this PrecisionPointList.
     * @param source the source PrecisionPointlist
     */
    @objid ("7f9d9d19-1dec-11e2-8cad-001ec947c8cc")
    public void addAll(PrecisionPointList source) {
        ensureCapacity(this.size + source.size);
        System.arraycopy(source.points, 0, this.points, this.size * 2, source.size * 2);
        this.size += source.size;
    }

    @objid ("7f9d9d1d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void addAll(PointList source) {
        if (source instanceof PrecisionPointList) {
            addAll((PrecisionPointList) source);
        }
        ensureCapacity(this.size + source.size());
        for (int i = 0; i < source.size(); i++) {
            addPoint(source.getPoint(i));
        }
    }

    @objid ("7f9d9d23-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void addPoint(Point p) {
        addPrecisionPoint(p.preciseX(), p.preciseY());
    }

    /**
     * Adds the input point values to this PointList.
     * @param x X value of a point to add
     * @param y Y value of a point to add
     */
    @objid ("7f9d9d29-1dec-11e2-8cad-001ec947c8cc")
    public void addPrecisionPoint(double x, double y) {
        this.bounds = null;
        int index = this.size * 2;
        ensureCapacity(this.size + 1);
        this.points[index] = x;
        this.points[index + 1] = y;
        ++this.size;
    }

    @objid ("7f9d9d2e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Rectangle getBounds() {
        if (this.bounds != null) {
            return this.bounds;
        }
        this.bounds = new PrecisionRectangle();
        if (this.size > 0) {
            this.bounds.setLocation(getPoint(0));
            for (int i = 0; i < this.size; i++) {
                PrecisionPoint p = (PrecisionPoint) getPoint(i);
                this.bounds.union(p);
            }
        }
        return this.bounds;
    }

    @objid ("7f9d9d35-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public PrecisionPointList getCopy() {
        PrecisionPointList result = new PrecisionPointList(this.size);
        System.arraycopy(this.points, 0, result.points, 0, this.size * 2);
        result.size = this.size;
        result.bounds = null;
        return result;
    }

    @objid ("7f9d9d3a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLastPoint() {
        return getPoint(this.size - 1);
    }

    @objid ("7f9d9d41-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getMidpoint() {
        if (size() % 2 == 0) {
            return getPoint(size() / 2 - 1).getTranslated(getPoint(size() / 2)).scale(0.5f);
        }
        return getPoint(size() / 2);
    }

    @objid ("7f9d9d48-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getPoint(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + //$NON-NLS-1$
                    ", Size: " +
                    this.size);
        }
        index *= 2;
        return new PrecisionPoint(this.points[index], this.points[index + 1]);
    }

    @objid ("7f9fff57-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getPoint(Point p, int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + //$NON-NLS-1$
                    ", Size: " +
                    this.size);
        }
        index *= 2;
        if (p instanceof PrecisionPoint) {
            PrecisionPoint preciseP = (PrecisionPoint) p;
            preciseP.setPreciseX(this.points[index]);
            preciseP.setPreciseY(this.points[index + 1]);
        } else {
            p.x = (int) Math.floor(this.points[index] + 0.000000001);
            p.y = (int) Math.floor(this.points[index + 1] + 0.000000001);
        }
        return p;
    }

    @objid ("7f9fff62-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void insertPoint(Point p, int index) {
        if (this.bounds != null && !this.bounds.contains(p)) {
            this.bounds = null;
        }
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + //$NON-NLS-1$
                    ", Size: " +
                    this.size);
        }
        index *= 2;
        
        int length = this.points.length;
        double old[] = this.points;
        this.points = new double[length + 2];
        System.arraycopy(old, 0, this.points, 0, index);
        System.arraycopy(old, index, this.points, index + 2, length - index);
        
        if (p instanceof PrecisionPoint) {
            PrecisionPoint precisionPt = (PrecisionPoint) p;
            this.points[index] = precisionPt.preciseX();
            this.points[index + 1] = precisionPt.preciseY();
        } else {
            this.points[index] = p.x;
            this.points[index + 1] = p.y;
        }
        this.size++;
    }

    @objid ("7f9fff69-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performScale(double factor) {
        for (int i = 0; i < this.points.length; i++) {
            this.points[i] = this.points[i] * factor;
        }
        this.bounds = null;
    }

    @objid ("7f9fff6d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performTranslate(int dx, int dy) {
        for (int i = 0; i < this.size * 2; i += 2) {
            this.points[i] += dx;
            this.points[i + 1] += dy;
        }
        if (this.bounds != null) {
            this.bounds.translate(dx, dy);
        }
    }

    @objid ("7f9fff72-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void removeAllPoints() {
        this.bounds = null;
        this.size = 0;
    }

    @objid ("7f9fff75-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point removePoint(int index) {
        this.bounds = null;
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + //$NON-NLS-1$
                    ", Size: " +
                    this.size);
        }
        
        index *= 2;
        PrecisionPoint pt = new PrecisionPoint(this.points[index], this.points[index + 1]);
        if (index != this.size * 2 - 2) {
            System.arraycopy(this.points, index + 2, this.points, index, this.size * 2 - index - 2);
        }
        this.size--;
        return pt;
    }

    @objid ("7f9fff7d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void reverse() {
        double temp;
        for (int i = 0, j = this.size * 2 - 2; i < this.size; i += 2, j -= 2) {
            temp = this.points[i];
            this.points[i] = this.points[j];
            this.points[j] = temp;
            temp = this.points[i + 1];
            this.points[i + 1] = this.points[j + 1];
            this.points[j + 1] = temp;
        }
    }

    @objid ("7f9fff80-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setPoint(Point pt, int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + //$NON-NLS-1$
                    ", Size: " +
                    this.size);
        }
        if (this.bounds != null && !this.bounds.contains(pt)) {
            this.bounds = null;
        }
        if (pt instanceof PrecisionPoint) {
            PrecisionPoint precisionPt = (PrecisionPoint) pt;
            this.points[index * 2] = precisionPt.preciseX();
            this.points[index * 2 + 1] = precisionPt.preciseY();
        } else {
            this.points[index * 2] = pt.x;
            this.points[index * 2 + 1] = pt.y;
        }
    }

    @objid ("7f9fff87-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setSize(int newSize) {
        if (this.points.length > newSize * 2) {
            this.size = newSize;
            return;
        }
        double[] newArray = new double[newSize * 2];
        System.arraycopy(this.points, 0, newArray, 0, this.points.length);
        this.points = newArray;
        this.size = newSize;
    }

    @objid ("7f9fff8b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns the contents of this PrecisionPointList as a double array. The returned array is by reference. Any changes made to the array will also be changing the original PrecisionPointList.
     * @return the integer array of points by reference
     */
    @objid ("7f9fff90-1dec-11e2-8cad-001ec947c8cc")
    public double[] toDoubleArray() {
        if (this.points.length != this.size * 2) {
            double[] old = this.points;
            this.points = new double[this.size * 2];
            System.arraycopy(old, 0, this.points, 0, this.size * 2);
        }
        return this.points;
    }

    @objid ("7f9fff97-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int[] toIntArray() {
        int[] intArray = new int[this.size * 2];
        for (int i = 0; i < size(); i++) {
            Point p = getPoint(i);
            int idx = 2 * i;
            intArray[idx] = p.x;
            intArray[idx + 1] = p.y;
        }
        return intArray;
    }

    @objid ("7f9fff9e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void translate(int x, int y) {
        if (x == 0 && y == 0) {
            return;
        }
        if (this.bounds != null) {
            this.bounds.translate(x, y);
        }
        for (int i = 0; i < this.size * 2; i += 2) {
            this.points[i] += x;
            this.points[i + 1] += y;
        }
    }

    @objid ("7f9fffa3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void transpose() {
        double temp;
        if (this.bounds != null) {
            this.bounds.transpose();
        }
        for (int i = 0; i < this.size * 2; i += 2) {
            temp = this.points[i];
            this.points[i] = this.points[i + 1];
            this.points[i + 1] = temp;
        }
    }

    @objid ("7f9fffa6-1dec-11e2-8cad-001ec947c8cc")
    private void ensureCapacity(int newSize) {
        newSize *= 2;
        if (this.points.length < newSize) {
            double old[] = this.points;
            this.points = new double[Math.max(newSize, this.size * 4)];
            System.arraycopy(old, 0, this.points, 0, this.size * 2);
        }
    }

}
