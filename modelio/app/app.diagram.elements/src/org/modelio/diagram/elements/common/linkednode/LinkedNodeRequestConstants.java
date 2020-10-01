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

package org.modelio.diagram.elements.common.linkednode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.Request;

/**
 * The set of constants used to identify linked node <code>Requests</code> by their {@link Request#getType() type}.
 */
@objid ("7ebfe264-1dec-11e2-8cad-001ec947c8cc")
public interface LinkedNodeRequestConstants {
    /**
     * Indicates the creation of a new connection. Creating a connection involves both the source node and target node
     * EditParts. This is the constant that is used with the first node on which the user clicks.
     */
    @objid ("90b44234-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_LINKEDNODE_START = "Linked node start"; // $NON-NLS-1$

    /**
     * Indicates the end of creation of a new connection. Creating a connection involves both the source node and target
     * node EditParts. This is the constant that is used with the second node on which the user clicks.
     */
    @objid ("90b4423b-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_LINKEDNODE_END = "Linked node end"; // $NON-NLS-1$

}
