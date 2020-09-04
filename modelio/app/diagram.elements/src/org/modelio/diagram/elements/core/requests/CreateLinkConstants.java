/* 
 * Copyright 2013-2018 Modeliosoft
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
     * Request type for {@link org.eclipse.gef.requests.CreateConnectionRequest CreateConnectionRequest} asking to add a
     * bend point.
     */
    @objid ("9367ac25-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_CONNECTION_ADD_BENDPOINT = "add bendpoint to future connection";

    /**
     * Key to retrieve the link path in the request.
     */
    @objid ("9367ac2b-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROP_PATH = "link path";

    /**
     * Request type for {@link org.eclipse.gef.requests.CreateConnectionRequest CreateConnectionRequest} asking to set
     * the next bend point.
     */
    @objid ("9367ac31-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_CONNECTION_SET_LAST_BENDPOINT = "set last bendpoint of future connection";

    @objid ("9a89c394-60a4-4370-a4e1-795331b3c0d7")
    public static final String REQ_CONNECTION_CREATE_LINK_CHOOSENODE = "propose node creation at the end of the link";

    /**
     * Request property whose presence tells the Request is a user interaction request.
     * <p>
     * The edit policy may then deny interaction if a required diagram graphic is not editable by the user.
     */
    @objid ("a3626875-5552-4a6e-99c2-6861e3319797")
    public static final String PROP_USER_INTERACTION = "is user interaction";

}
