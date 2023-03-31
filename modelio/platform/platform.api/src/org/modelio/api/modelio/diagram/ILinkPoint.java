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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;

/**
 * Represents a {@link ILinkRoute} point.
 * <p>
 * A ILinkPoint is one of the point that compose a link.
 * It may be an anchor point at the connection extremities or a bend point.
 * <p>
 * A bend point may be either automatic or fixed.<ul>
 * <li>{@link LinkPointKind#BENDPOINT automatic} points may be freely modified or deleted by the connection router,
 * <li>{@link LinkPointKind#FIXED_BENDPOINT fixed} bend points will not move unless the connection route is not consistent.
 * </ul>
 * An anchor point may be a sliding or a fixed anchor:<ul>
 * <li>{@link LinkPointKind#ANCHOR_SLIDING anchors} may be located anywhere on the node. They will often move to align with
 * the adjacent bend point or the opposite anchor if none.
 * <li>{@link LinkPointKind#ANCHOR_DISCRETE anchors} are predefined anchors whose location is determined by the node.
 * {@link #setLocation(Point)} will not move the anchor. Instead it will select the nearest anchor from the requested location.
 * </ul>
 * 
 * @since 5.1
 */
@objid ("704aee0b-737e-412e-aa84-ae04e69d15b3")
public interface ILinkPoint {
    /**
     * @return true if the point is manual, false if it is automatic.
     */
    @objid ("788ecbc8-606b-408d-80c1-94e8243cd967")
    LinkPointKind getKind();

    /**
     * Returns the location of the point <em>relative</em> to the
     * connection. The returned value is a copy that may be freely modified.
     * @return the location of the point relative to the Connection
     */
    @objid ("c0cc18b9-b538-454d-b987-35924a71d745")
    default Point getLocation() {
        return getLocation(new Point());
    }

    /**
     * Fills 'out' with the location of the point <em>relative</em> to the connection.
     * 'out' is returned for convenience.
     * <p>
     * This method may be used to save Point allocations.
     * @param out The point to fill with the location
     * @return out, containing the location of the point relative to the Connection
     */
    @objid ("a47f2d03-8af8-433f-8828-b89b72d391b3")
    <P extends Point> P getLocation(P out);

    /**
     * Changes the link point location.
     * <p>
     * Note : the router will make best effort to satisfy the requested location
     * but the final point location may be different than the requested one.
     * This is particularly true for anchor points.
     * @param val The point to fill with the location
     * @return out, containing the location of the point relative to the Connection
     */
    @objid ("8f3bc6e7-d1d6-4cd0-b2a2-d01d65d478d7")
    ILinkPoint setLocation(Point val);

    /**
     * Changes the link point kind.
     * <p>
     * The valid kinds depend on the modified link points: some kinds are valid only for anchors,
     * others only for bend point.
     * @param val The point to fill with the location
     * @return out, containing the location of the point relative to the Connection
     * @throws IllegalArgumentException when the given kind is not valid for this link point.
     */
    @objid ("96611825-1593-4983-8104-1a51feef5a00")
    ILinkPoint setKind(LinkPointKind val) throws IllegalArgumentException;

    /**
     * @return the location x coordinate.
     */
    @objid ("881e52c7-b293-4cce-876c-51a5926b895f")
    int x();

    /**
     * @return the location y coordinate.
     */
    @objid ("fe56825a-dce1-40ee-aa0b-1dfe9fbc86b2")
    int y();
}

