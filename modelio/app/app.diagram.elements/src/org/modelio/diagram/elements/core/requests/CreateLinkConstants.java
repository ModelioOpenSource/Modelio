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
package org.modelio.diagram.elements.core.requests;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * constants for link with bend points management.
 * 
 * @author cmarin
 */
@objid ("80dab5be-1dec-11e2-8cad-001ec947c8cc")
public interface CreateLinkConstants {
    /**
     * Request property whose presence tells the Request is a user interaction request.
     * <p>
     * The edit policy may then deny interaction if a required diagram graphic is not editable by the user.
     */
    @objid ("a3626875-5552-4a6e-99c2-6861e3319797")
    public static final String PROP_USER_INTERACTION = "is user interaction";

    /**
     * REQ_RECONNECT_XXX Request property requesting an anchor on the same face than the given anchor.
     * <p>
     * The value is expected to be a compatible {@link org.eclipse.draw2d.ConnectionAnchor ConnectionAnchor}.
     */
    @objid ("60d0afd8-c1c3-46ba-a9f8-107f86d0dd94")
    public static final String PROP_RECONNECT_ON_SAME_FACE = "on same face than given anchor";

    /**
     * REQ_RECONNECT_XXX Request property requesting an anchor on the given figure instead of the
     * target edit part figure.
     * <p>
     * The value is expected to be a {@link org.eclipse.draw2d.IFigure IFigure}.
     * <p>
     * Used for feedback display when the feedback figure is not the main figure.
     */
    @objid ("11010844-3f4d-48aa-81fd-72e71fafa1c1")
    public static final String PROP_RECONNECT_ON_FIGURE = "reconnect on given figure";

    /**
     * Connection creation and reconnection Request property requesting a slidable anchor.
     * <p>
     * The value is expected to be a boolean.
     */
    @objid ("d9fb47b6-c450-43c9-af50-e34d9f426441")
    public static final String PROP_NEED_SLIDABLE_ANCHOR = "need slidable anchor";

    /**
     * Request type for {@link org.eclipse.gef.requests.CreateConnectionRequest CreateConnectionRequest} asking to add a
     * bend point.
     */
    @objid ("9367ac25-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_CONNECTION_ADD_BENDPOINT = "add bendpoint to future connection";

    @objid ("9a89c394-60a4-4370-a4e1-795331b3c0d7")
    public static final String REQ_CONNECTION_CREATE_LINK_CHOOSENODE = "propose node creation at the end of the link";

    /**
     * Request type for {@link org.eclipse.gef.requests.CreateConnectionRequest CreateConnectionRequest} asking to set
     * the next bend point.
     */
    @objid ("9367ac31-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_CONNECTION_SET_LAST_BENDPOINT = "set last bendpoint of future connection";

    /**
     * Request type asking the ConnectionEditPart to update the routing constraint in the model.
     * <p>
     * Emitted by DefaultCreateLinkPolicy.
     * Implemented by the AutoOrthoConnEndPointEditPolicy to update the routing constraint from the feedback figure.
     */
    @objid ("72989dda-85fe-4c64-864d-307be1edf52d")
    public static final String REQ_CONNECTION_UPDATE_ROUTING_CONSTRAINT = "Update connection routing constraint";

    /**
     * Request type asking to move a ConnectionEditPart segment.
     * <p>
     * Emitted by ConnectionSegmentTracker.
     * Implemented by connection bend point edit policies .
     */
    @objid ("c0676f9a-1fe4-4b9c-8518-427ac75f9e89")
    public static final String REQ_CONNECTION_MOVE_SEGMENT = "move connection segment";
}

