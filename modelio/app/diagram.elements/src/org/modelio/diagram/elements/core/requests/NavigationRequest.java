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

package org.modelio.diagram.elements.core.requests;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.requests.SelectionRequest;

/**
 * Request to fire a Modelio navigation on the selection.
 */
@objid ("9be4f353-9c59-4394-97ca-7b28887c0051")
public class NavigationRequest extends SelectionRequest {
    /**
     * The navigation request type.
     */
    @objid ("b1f58b6f-c754-4e60-8b21-13e7cd9b7edb")
    public static final String TYPE = "FIRE_NAVIGATION";

    /**
     * Initialize the request
     */
    @objid ("c53e9ea5-1429-4d6e-9e91-069f546ff762")
    public NavigationRequest() {
        setType(TYPE);
    }

}
