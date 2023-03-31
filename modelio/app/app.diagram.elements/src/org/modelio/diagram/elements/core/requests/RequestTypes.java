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

@objid ("be9672aa-be3a-4aed-a930-d2ed35af2f2a")
public class RequestTypes {
    /**
     * Request type for {@link org.eclipse.gef.requests.CreateConnectionRequest CreateConnectionRequest} asking to add a
     * bend point.
     */
    @objid ("4e0dbca7-f3b8-4ae6-a4c2-6b0a73d7ab28")
    public static final String REQ_DELETING_CHILDREN = "Deleting children";

    /**
     * Request type for {@link org.modelio.diagram.elements.core.commands.DefaultSelectElementCommand} asking to
     * select and unmask an existing element.
     */
    @objid ("8b4b977c-b452-44aa-8553-401c23a9c318")
    public static final String UNMASK_OR_CREATE_CHILDREN = "Unmask or Create children";

}
