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
@objid ("ea481bde-4696-446b-9b0c-0684ff0847f1")
public interface ILinkPoint {
    /**
     * @return true if the point is manual, false if it is automatic.
     */
    @objid ("8ec78c3f-5730-49c9-91a0-85b375146493")
    LinkPointKind getKind();

    /**
     * Returns the location of the point <em>relative</em> to the
     * connection. The returned value is a copy that may be freely modified.
     * @return the location of the point relative to the Connection
     */
    @objid ("38a09e50-e7a9-465b-b316-af0c1504f6c9")
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
    @objid ("775aa7d2-4e28-4787-ba09-6c8e22acbb25")
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
    @objid ("5219fad8-6de5-4c9f-86b0-397723d9221f")
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
    @objid ("c9bff135-6eb6-4ea7-aacd-20e980c4b13d")
    ILinkPoint setKind(LinkPointKind val) throws IllegalArgumentException;

    /**
     * @return the location x coordinate.
     */
    @objid ("149f6464-aa73-4e85-8518-dc43d16b5d25")
    int x();

    /**
     * @return the location y coordinate.
     */
    @objid ("f89a0624-f908-4788-a3fc-bd737d5a16f5")
    int y();

}
