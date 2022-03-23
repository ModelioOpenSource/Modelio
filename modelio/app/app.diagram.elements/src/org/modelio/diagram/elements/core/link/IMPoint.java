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
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Translatable;

/**
 * A IMPoint is a {@link org.eclipse.draw2d.geometry.Point} with a {@link #isFixed()} value.
 * <p>
 * Implementations are {@link MPoint} and {@link MPrecisionPoint}.
 * 
 * @author cma
 * @param <T> The IMPoint type subclass
 * @since 5.0.2
 */
@objid ("5dbb131d-2e11-48fc-a080-f83cc05e6bd9")
public interface IMPoint<T extends IMPoint<T>> extends Cloneable, Translatable, Bendpoint {
    /**
     * Tells whether the point is automatic or manual.
     * @return <li>true = manual point
     * <li>false = automatic point
     */
    @objid ("c73234e3-5fe1-449c-a31b-6b86c9dbc869")
    boolean isFixed();

    /**
     * Set the point as manual or automatic
     * @param val false for automatic, true for manual
     * @return this instance to chain calls.
     */
    @objid ("677158b9-7071-45fe-b547-9bf30ee5bcac")
    T setFixed(boolean val);

    /**
     * @see org.eclipse.draw2d.geometry.Point#preciseX()
     * @return <code>double</code> x coordinate
     */
    @objid ("9b2f01ed-975a-4fbe-8256-d7ff03d09da0")
    double preciseX();

    /**
     * @see org.eclipse.draw2d.geometry.Point#preciseY()
     * @return <code>double</code> y coordinate
     */
    @objid ("f4730253-4813-4767-8dc3-41d079800eb5")
    double preciseY();

    /**
     * @see org.eclipse.draw2d.geometry.Point#setLocation(Point)
     * @param pt the other point to copy
     * @return this instance to chain calls.
     */
    @objid ("29f14ff1-f916-481b-ae31-e9c1e4de4041")
    T setLocation(Point pt);

    /**
     * Copy all passed point values to this instance.
     * @param other the other point to copy
     * @return this instance to chain calls.
     */
    @objid ("8c0364df-e827-4595-8272-732555007539")
    T setValues(IMPoint<?> other);

    /**
     * Convert this IMPoint to a {@link Point}.
     * <p>
     * Usually return this instance .
     * @return this IMPoint as a Point.
     */
    @objid ("02441bb0-a0a7-4e0a-bacb-26d6106bcab2")
    Point asPoint();

}
