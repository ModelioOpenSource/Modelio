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

package org.modelio.diagram.elements.core.helpers.palapi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.diagram.elements.core.link.createhandle.ICreationActionDescriptor;

@objid ("4a66f043-3b28-4e49-9925-67663dbe25ce")
class NodeToolActionDescriptor implements ICreationActionDescriptor {
    @objid ("14ed7452-26d9-45b0-a145-e969595e3a6a")
    private final PaletteEntry tool;

    @objid ("3953a10d-b978-4d2d-8024-e69c34c759be")
    private final CreateRequest request;

    @objid ("b9f5771a-be35-422e-9236-d600d4b57b17")
    private final Command command;

    @objid ("9551d04a-bd9b-4cba-b906-94a02164f46e")
    public NodeToolActionDescriptor(PaletteEntry tool, CreateRequest request, Command command) {
        super();
        this.tool = tool;
        this.request = request;
        this.command = command;
    }

    @objid ("2af5375e-1422-45b5-a6f4-a569605b0ca8")
    @Override
    public void execute(EditPartViewer viewer) {
        if (this.command != null && this.command.canExecute()) {
            viewer.getEditDomain().getCommandStack().execute(this.command);
        }
    }

    @objid ("7ed5b2f7-ed88-4b15-bdc5-005d564a2614")
    @Override
    public String toString() {
        return "Action [tool=" + this.tool.getLabel()
                                                                        + ", request= from"
                                                                        // + String.valueOf(this.request.getSourceEditPart()).substring(0, 20)
                                                                        + " @ " + this.request.getLocation()
                                                                        //+ " - " + this.request.getData()
                                                                        + ", finishCommand="
                                                                        + (this.command == null ? "null" : this.command.getClass().getSimpleName())
                                                                        + "]";
    }

    @objid ("43f4c2fd-8ff0-48c2-b439-c48c288e6916")
    @Override
    public Command getCommand() {
        return this.command;
    }

    @objid ("b8b5289e-a973-48f8-a1f0-39259273781c")
    @Override
    public String getLabel() {
        return this.tool.getLabel();
    }

    @objid ("19cfbc39-58a3-4f28-a7bd-4a628afe628a")
    @Override
    public ImageDescriptor getIcon() {
        return this.tool.getSmallIcon();
    }

    @objid ("db515310-e488-49cb-91dc-f4c68ee84d9e")
    @Override
    public CreateRequest getRequest() {
        return this.request;
    }

}
