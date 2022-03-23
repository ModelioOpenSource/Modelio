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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;

/**
 * {@link IMPoint} implementation with {@link PrecisionPoint}.
 * @author cma
 * @since 5.0.2
 */
@objid ("109c9d10-8e5d-423c-bb7b-5aaab5724212")
public class MPrecisionPoint extends PrecisionPoint implements IMPoint<MPrecisionPoint> {
    @objid ("829f1a42-ada7-4499-91a9-bdfdba470d4d")
    private static final long serialVersionUID = 1L;

    @objid ("ff8b4f35-0e10-4ee2-9d7b-a1f91f3e2d80")
    private boolean fixed;

    @objid ("7c03a7f9-6d32-44ef-ad8e-84832e86a7f7")
    @Override
    public boolean isFixed() {
        return this.fixed;
    }

    @objid ("cb393683-b8e3-4684-8daa-b6e854cd14b0")
    @Override
    public MPrecisionPoint setFixed(boolean fixed) {
        this.fixed = fixed;
        return this;
    }

    @objid ("5f7170f2-69be-403e-a23b-75416f71b30e")
    @Override
    public MPrecisionPoint setLocation(Point pt) {
        super.setLocation(pt);
        return this;
    }

    @objid ("875ac537-891e-49f9-b178-2bb5e9253fa4")
    @Override
    public Point getLocation() {
        return this;
    }

    @objid ("853c308a-e46b-41bf-b7e1-bdea545cff97")
    @Override
    public final Point asPoint() {
        return this;
    }

    @objid ("68e8ab32-9f68-4a68-af52-b4ed6300f7d8")
    @Override
    public MPrecisionPoint setValues(IMPoint<?> other) {
        setLocation(other.asPoint());
        setFixed(other.isFixed());
        return this;
    }

}
