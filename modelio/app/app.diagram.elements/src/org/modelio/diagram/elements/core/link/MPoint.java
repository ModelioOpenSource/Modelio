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
package org.modelio.diagram.elements.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * {@link AbsoluteBendpoint} that is either {@link #isFixed() automatic or manually fixed}.
 * <p>
 * An automatic bend point is computed by the connection router (or an edit policy).
 * A manually fixed point is a point set by the user. This point may not be modified by the router.
 * <p>
 * <h3>Note:</h3>
 * All methods that declare return a {@link Point} return a effectively a {@link MPoint}.<br/>
 * eg: {@link #getTranslated(Dimension)}, {@link #translate(Point)}, ...
 * 
 * @author chm + cma
 * @since 5.0.2
 */
@objid ("bd496933-e61e-4136-a88b-6df7e8534bba")
public class MPoint extends AbsoluteBendpoint implements IPersistent, IMPoint<MPoint> {
    @objid ("243641f9-e9f0-4ec9-b448-5c53bbcd4451")
    private static final long serialVersionUID = 33399776935369594L;

    /**
     * <li>true = manual point
     * <li>false = automatic point
     */
    @objid ("9ad69db4-e9f5-4534-b536-8623fba21899")
    private boolean isFixed;

    /**
     * Creates an automatic MPoint at 0,0 .
     */
    @objid ("5596cd2e-4418-403e-a2b3-b014fa59f8aa")
    public  MPoint() {
        this(0, 0, false);
    }

    /**
     * Constructs an MPoint at the same location as the given Point.
     * @param p Point from which the initial values are taken.
     * @param isFixed true for manually fixed point, false for automatic point.
     */
    @objid ("36a0e0dc-e365-4789-a04f-73d7645b4614")
    public  MPoint(Point p, boolean isFixed) {
        this(p.x, p.y, isFixed);
    }

    /**
     * Constructs an MPoint at the specified x and y locations.
     * @param x x value
     * @param y y value
     * @param isFixed true for manually fixed point, false for automatic point.
     */
    @objid ("7e61cbe6-a48a-482d-abac-dc4ac467c4de")
    public  MPoint(int x, int y, boolean isFixed) {
        super(x, y);
        this.isFixed = isFixed;
        
    }

    /**
     * Tells whether the point is automatic or manual.
     * @return <li>true = manual point
     * <li>false = automatic point
     */
    @objid ("dfebdcd3-1e88-49ac-83a3-2cc51c1be947")
    @Override
    public boolean isFixed() {
        return this.isFixed;
    }

    /**
     * Set the point as automatic or manual.
     * @param isFixed true for manually fixed point, false for automatic point.
     * @return this instance to chain calls.
     */
    @objid ("8f16a216-943f-49fe-84dc-04098f2ca19b")
    @Override
    public MPoint setFixed(boolean isFixed) {
        this.isFixed = isFixed;
        return this;
    }

    @objid ("cf4d9009-449a-4a22-a5a3-c97177b02504")
    @Override
    public final Point asPoint() {
        return this;
    }

    @objid ("7c4d4202-02bd-4f45-a5b2-93422506807d")
    @Override
    public MPoint setValues(IMPoint<?> other) {
        setLocation(other.asPoint());
        setFixed(other.isFixed());
        return this;
    }

    @objid ("d836c08e-b9d6-4c40-a34d-2f52708a58ad")
    @Override
    public MPoint setLocation(Point p) {
        super.setLocation(p);
        return this;
    }

    @objid ("bde08054-d874-4fa3-88d0-4bb5750a9e95")
    @Override
    public MPoint setLocation(int x, int y) {
        super.setLocation(x, y);
        return this;
    }

    @objid ("45118225-47cd-417d-811a-2ea52a1e2e2e")
    @Override
    public MPoint setX(int x) {
        super.setX(x);
        return this;
    }

    @objid ("d1dd07a1-c609-423a-8c3f-0740934de4cd")
    @Override
    public MPoint setY(int y) {
        super.setY(y);
        return this;
    }

    @objid ("edbfaf06-2c76-4c5a-98c0-128acf1f145e")
    @Override
    public void read(IDiagramReader in) {
        this.x = (int) in.readProperty("x");
        this.y = (int) in.readProperty("y");
        this.isFixed = (boolean) in.readProperty("isFixed");
        
    }

    @objid ("1819462b-0920-48a5-a21b-13a571b06633")
    @Override
    public void write(IDiagramWriter out) {
        out.writeProperty("x", this.x);
        out.writeProperty("y", this.y);
        out.writeProperty("isFixed", this.isFixed);
        
    }

    @objid ("2d2d30f7-57c2-41a0-85bf-9bfacd76144c")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("ce661cb6-91c5-4b4c-bab0-027d452b741f")
    @Override
    public int getMajorVersion() {
        return 0;
    }

    @objid ("b045691d-86c0-4f72-8d46-d9475f51fb30")
    @Override
    public String toString() {
        
        return getClass().getSimpleName()+" [x=" + this.x + ", y=" + this.y + ", " + (this.isFixed ? "manual" : "automatic")+ "]";
    }

    @objid ("ae178cd0-7df1-4216-b32c-c0676193ea7c")
    @Override
    public MPoint getCopy() {
        return new MPoint(this, this.isFixed);
    }

}
