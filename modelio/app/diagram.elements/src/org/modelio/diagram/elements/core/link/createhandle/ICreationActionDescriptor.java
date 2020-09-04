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

package org.modelio.diagram.elements.core.link.createhandle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.resource.ImageDescriptor;

@objid ("7393f12d-8380-4c58-a272-84cced0e302b")
public interface ICreationActionDescriptor {
    @objid ("9c53e4a7-aa0d-4d7b-8ba5-87dad07afce7")
    Command getCommand();

    @objid ("bbf9b49a-e129-46df-bf49-ea08f66f326f")
    String getLabel();

    @objid ("2df62ea8-06cf-442c-b260-981110bfa54e")
    ImageDescriptor getIcon();

    @objid ("873c7f32-7d8a-4d3f-bc06-64093a18a0be")
    CreateRequest getRequest();

    @objid ("d155ddc6-25a6-4500-930c-af758354d41c")
    void execute(EditPartViewer viewer);

}
