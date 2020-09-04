/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.diagram;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;

/**
 * Interface for a connection path.
 */
@objid ("4bae6102-644c-11e0-b650-001ec947cd2a")
public interface ILinkPath {
    /**
     * Get all bend points of the connection.
     * <p>
     * The returned list is a copy and can be freely be modified.<br>
     * To apply changes to the returned list, call {@link #setPoints(Collection)} with the returned
     * list as parameter.
     * @return the bend points in coordinates relative to the diagram...
     */
    @objid ("6d18692e-69b5-11e0-adf3-002564c97630")
    List<Point> getPoints();

    /**
     * Removes the point at the specified position in this point list.
     * <p>
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * @param index the index of the element to be removed.
     */
    @objid ("6d18de62-69b5-11e0-adf3-002564c97630")
    void removePoint(final int index);

    /**
     * Discards all points from the point list, then add all specified points in it.
     * @param points the new point list for this connection.
     */
    @objid ("6d190573-69b5-11e0-adf3-002564c97630")
    void setPoints(final Collection<Point> points);

    /**
     * Change the coordinates of a specific bend point of the connection.
     * @param index the index of the point to move in the point list.
     * @param point the new coordinates for this point.
     */
    @objid ("a3de5386-0ecc-11e2-96c4-002564c97630")
    void movePoint(final int index, final Point point);

    /**
     * Change the coordinates of a specific bend point of the connection.
     * @param index the index of the point to move in the point list.
     * @param x the new x coordinate
     * @param y the new y coordinate
     * @since 3.1
     */
    @objid ("eacc0650-411e-4c5e-9346-a921c1139005")
    void movePoint(final int index, int x, int y);

}
