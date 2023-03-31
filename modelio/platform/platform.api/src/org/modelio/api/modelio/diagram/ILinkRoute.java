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
@objid ("b09b857b-914c-43f7-abdf-9fe5009063f9")
public interface ILinkRoute {
    /**
     * Create and add a new bend point link point as last bend point.
     * @param loc the point location
     * @return a new ILinkPoint.
     */
    @objid ("764a87b9-6fb8-459c-aa65-fd1c1f6fec14")
    ILinkRoute addBendPoint(Point loc);

    @objid ("b76beb65-ca21-468b-aaac-c6b9b6005041")
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
    @objid ("fb1d1f73-19d4-4b28-a7d7-2a7e78c58227")
    ILinkRoute addFixedPoint(Point loc);

    @objid ("34f28cdd-a310-474c-9999-d6c530ad499a")
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
    @objid ("ecf96b0b-9a1b-421f-97a5-bdbd41344257")
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
    @objid ("5539e70b-de34-40ef-8c64-8043585ec2aa")
    ILinkPoint createLinkPoint(LinkPointKind kind, Point location);

    /**
     * Get a direct access to all points of the connection.
     * <p>
     * The returned list is a direct reference, modifying it modifies the {@link ILinkRoute}.
     * It does not modify the connection from which this path was built.
     * The route must be applied to the {@link IDiagramLink#setRoute(ILinkRoute)}.
     * @return the points in coordinates relative to the diagram...
     */
    @objid ("3ba8b5ba-25b2-4fa1-a34b-f9a0f7c0c456")
    List<ILinkPoint> getAllPoints();

    /**
     * Get a direct access to bend points of the connection.
     * <p>
     * The returned list is a direct reference, modifying it modifies the {@link ILinkRoute}.
     * It does not modify the connection from which this path was built.
     * The route must be applied to the {@link IDiagramLink#setRoute(ILinkRoute)}.
     * @return the points in coordinates relative to the diagram...
     */
    @objid ("78e3d989-4ab8-4bca-84b6-9d4adfb55a09")
    List<ILinkPoint> getBendPoints();

    /**
     * Get the source anchor link point.
     * @return the source anchor link point
     */
    @objid ("5887ee72-2361-45c0-9c23-628c7f16242e")
    ILinkPoint getSourceAnchor();

    /**
     * @return the target anchor link point
     */
    @objid ("57012ae9-2cca-429e-ad15-98ef3eb23bb9")
    ILinkPoint getTargetAnchor();

    /**
     * Removes the point at the specified position in this point list.
     * <p>
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * @param index the index of the element to be removed.
     */
    @objid ("5edd9efa-532e-4767-8478-1de9930ddba0")
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
    @objid ("5290dd49-8ba6-4e25-95a6-e0863afb2bfb")
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
    @objid ("8d0f8920-c395-4329-918d-9d2d740067f8")
    ILinkRoute setTargetAnchor(Point loc, boolean sliding);
}

