/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.common.linktovoid;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.Request;

/**
 * The set of constants used to identify linked node <code>Requests</code> by their {@link Request#getType() type}.
 */
@objid ("7ecbce46-1dec-11e2-8cad-001ec947c8cc")
public interface LinkToVoidConstants {
    /**
     * Indicates the creation of a new connection. Creating a connection involves both the source node and target node
     * EditParts. This is the constant that is used with the first node on which the user clicks.
     */
    @objid ("9191fce2-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_LINKTOVOID_START = "Link to void start"; // $NON-NLS-1$

    /**
     * Indicates the end of creation of a new connection. Creating a connection involves both the source node and target
     * node EditParts. This is the constant that is used with the second node on which the user clicks.
     */
    @objid ("9191fce9-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_LINKTOVOID_END = "Link to void end"; // $NON-NLS-1$

    /**
     * Constant used to indicate that the <i>source</i> end of an existing connection is being reconnected to a new
     * source node EditPart. The new source node is the receiver of such Requests.
     */
    @objid ("9191fcf0-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_LINKTOVOID_RECONNECT_SOURCE = "Link to void reconnection source"; // $NON-NLS-1$

    /**
     * Constant used to indicate that the <i>target</i> end of an existing connection is being reconnected to a new
     * target node EditPart. The new target node is the receiver of such Requests.
     */
    @objid ("9191fcf7-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_LINKTOVOID_RECONNECT_TARGET = "Link to void reconnection target"; // $NON-NLS-1$

}
