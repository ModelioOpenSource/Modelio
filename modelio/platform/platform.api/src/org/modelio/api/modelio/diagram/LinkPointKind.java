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
 * Kind of a {@link ILinkPoint} is one of the point that compose a link.
 * Defines  an anchor point at the connection extremities or a bend point.
 * <p>
 * A bend point may be either automatic or fixed.<ul>
 * <li>{@link LinkPointKind#BENDPOINT automatic} points may be freely modified or deleted by the connection router,
 * <li>{@link LinkPointKind#BENDPOINT_FIXED fixed} bend points will not move unless the connection route is not consistent.
 * </ul>
 * An anchor point may be a sliding or a fixed anchor:<ul>
 * <li>{@link LinkPointKind#ANCHOR_SLIDING anchors} may be located anywhere on the node. They will often move to align with
 * the adjacent bend point or the opposite anchor if none.
 * <li>{@link LinkPointKind#ANCHOR_DISCRETE anchors} are predefined anchors whose location is determined by the node.
 * {@link ILinkPoint#setLocation(Point)} will not move the anchor. Instead it will select the nearest anchor from the requested location.
 * </ul>
 */
@objid ("03ea3183-7587-4d24-8bb8-8e034875dc84")
public enum LinkPointKind {
    /**
     * Bend point that will not move unless the connection route is not consistent.
     */
    @objid ("008d2043-3d2e-49e6-9cfc-7f17ed5e5aff")
    BENDPOINT_FIXED,
    /**
     * Bend point that may be freely modified or deleted by the connection router.
     */
    @objid ("5e62fb7c-6f5a-4dfe-b792-d1c628064cb8")
    BENDPOINT,
    /**
     * Predefined anchors whose location is determined by the node.
     * {@link ILinkPoint#setLocation(Point)} will not move the anchor.
     * Instead it will select the nearest anchor from the requested location.
     */
    @objid ("ba89733e-df3d-479f-85dd-9202a7c05d60")
    ANCHOR_DISCRETE,
    /**
     * Anchor point that may be located anywhere on the node. They may move to align with
     * the adjacent bend point or the opposite anchor if no bend point.
     */
    @objid ("5640182f-1784-4f9e-b995-1b8aed69b197")
    ANCHOR_SLIDING;

}
