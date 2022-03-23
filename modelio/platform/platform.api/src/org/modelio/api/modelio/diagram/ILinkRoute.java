/* 
 * Copyright 2013-2020 Modeliosoft
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
 * Interface for a connection route.
 * @since 5.1.0
 */
@objid ("f04ee740-7de7-4574-8fcf-cf19b84db964")
public interface ILinkRoute {
    /**
     * Create and add a new bend point link point as last bend point.
     * @param loc the point location
     * @return a new ILinkPoint.
     */
    @objid ("ed6610e9-b1b8-4119-b4cb-5049b76b9506")
    ILinkRoute addBendPoint(Point loc);

    @objid ("ad619e42-cc79-4028-8578-788e4dec2bbc")
    default ILinkRoute addBendPoints(Collection<Point> points) {
        for (Point p : points) {
            addBendPoint(p);
        }
        return this;
    }

    /**
     * Create a new fixed bend link point as last bend point.
     * @param loc the point location
     * @return a new ILinkPoint.
     */
    @objid ("b9bccd68-3033-4094-8bfc-a0613a80e79f")
    ILinkRoute addFixedPoint(Point loc);

    @objid ("457441eb-5d0c-493d-8196-1ad41afea11c")
    default ILinkRoute addFixedPoints(Collection<Point> points) {
        for (Point p : points) {
            addFixedPoint(p);
        }
        return this;
    }

    /**
     * Create a new link point.
     * <p>
     * It is added to the path, you have to add it to {@link #getLinkPoints()}.
     * @return a new ILinkPoint.
     */
    @objid ("a3491fa4-575c-4260-b799-dac2a48d8c53")
    ILinkRoute clearBendPoints();

    /**
     * Factory method to create an arbitrary {@link ILinkPoint}.
     * <p>
     * The returned link point is not attached to the route.
     * You may insert it into {@link #getAllPoints()} or {@link #getBendPoints()} .
     * @param kind the link point kind
     * @param location the link point location. This location is copied.
     * @return a new link point.
     */
    @objid ("73a2adaa-8f1e-499d-8fa8-fc25f95eace0")
    ILinkPoint createLinkPoint(LinkPointKind kind, Point location);

    /**
     * Get a direct access to all points of the connection.
     * <p>
     * The returned list is a direct reference, modifying it modifies the {@link ILinkRoute}.
     * It does not modify the connection from which this path was built.
     * The route must be applied to the {@link IDiagramLink#setRoute(ILinkRoute)}.
     * @return the points in coordinates relative to the diagram...
     */
    @objid ("0ebe52fc-72c3-4712-8383-a8ff1c781607")
    List<ILinkPoint> getAllPoints();

    /**
     * Get a direct access to bend points of the connection.
     * <p>
     * The returned list is a direct reference, modifying it modifies the {@link ILinkRoute}.
     * It does not modify the connection from which this path was built.
     * The route must be applied to the {@link IDiagramLink#setRoute(ILinkRoute)}.
     * @return the points in coordinates relative to the diagram...
     */
    @objid ("745bf13a-b492-4d78-b5cb-96e54fe5954b")
    List<ILinkPoint> getBendPoints();

    /**
     * Get the source anchor link point.
     * @return the source anchor link point
     */
    @objid ("5be18acc-b9a4-4f97-9199-108d32b5fdf8")
    ILinkPoint getSourceAnchor();

    /**
     * @return the target anchor link point
     */
    @objid ("5adae244-097b-47c6-ba1b-6d6e0861d59f")
    ILinkPoint getTargetAnchor();

    /**
     * Removes the point at the specified position in this point list.
     * <p>
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * @param index the index of the element to be removed.
     */
    @objid ("d75d0d2e-5b0b-450f-a084-f7adb3971653")
    void removePoint(final int index);

    /**
     * Request to anchor the connection source point at a given location.
     * <p>
     * The final anchor location may be different than the requested one.
     * It may also depends on the adjacent bend point location.
     * <p>
     * Call {@link #getSourceAnchor()} to get the final location of the anchor.
     * @param loc the point location
     * @param sliding true to request a sliding anchor, false for a fixed anchor
     * @return this instance.
     */
    @objid ("be120c1a-8607-4b5d-87f3-0f642e92521d")
    ILinkRoute setSourceAnchor(Point loc, boolean sliding);

    /**
     * Request to anchor the connection target point at a given location.
     * <p>
     * The final anchor location may be different than the requested one.
     * It may also depends on the adjacent bend point location.
     * <p>
     * Call {@link #getTargetAnchor()} to get the final location of the anchor.
     * @param loc the point location
     * @param sliding true to request a sliding anchor, false for a fixed anchor
     * @return this instance.
     */
    @objid ("d7ddd986-5717-4b4e-b3a1-47a658221a1a")
    ILinkRoute setTargetAnchor(Point loc, boolean sliding);

}
