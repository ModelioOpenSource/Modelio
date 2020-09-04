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
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.createhandle.ICreationActionDescriptor;

@objid ("7fad871f-aa30-4ff5-9f2f-e9c625a84d4a")
class LinkToolActionDescriptor implements ICreationActionDescriptor {
    @objid ("63096513-9564-4878-ab17-bf02436c2974")
    private final PaletteEntry tool;

    @objid ("d9906cf6-1e32-41fb-8aba-48988ff174ba")
    private final CreateBendedConnectionRequest request;

    @objid ("f30c8706-e0d6-4c45-a321-c6e69c795c9b")
    private final Command finishCommand;

    @objid ("5c442f51-edbc-40a5-8d07-7ab3204fc032")
    public LinkToolActionDescriptor(PaletteEntry tool, CreateBendedConnectionRequest request, Command finishCommand) {
        super();
        this.tool = tool;
        this.request = request;
        this.finishCommand = finishCommand;
    }

    @objid ("77cc7de7-8c59-4ecc-94dd-415a4dc4dc66")
    @Override
    public String toString() {
        return "Action [tool=" + this.tool.getLabel()
                                        + ", request= from"
                                        // + String.valueOf(this.request.getSourceEditPart()).substring(0, 20)
                                        + " @ " + this.request.getLocation()
                                        + " - " + this.request.getData()
                                        + ", finishCommand="
                                        + (this.finishCommand == null ? "null" : this.finishCommand.getClass().getSimpleName())
                                        + "]";
    }

    @objid ("43f289ac-f199-4401-98b3-5ec82b63c567")
    @Override
    public Command getCommand() {
        return this.finishCommand;
    }

    @objid ("72399654-3588-4d05-b9d3-79e16abf17af")
    @Override
    public String getLabel() {
        return this.tool.getLabel();
    }

    @objid ("a4dd94c7-148c-4cb2-9d2e-0ff27d6dafc7")
    @Override
    public ImageDescriptor getIcon() {
        return this.tool.getSmallIcon();
    }

    @objid ("a6527fac-a4a5-40bb-a52d-85a6ae16bab2")
    @Override
    public CreateBendedConnectionRequest getRequest() {
        return this.request;
    }

    @objid ("49fd3dce-83be-49de-a952-8c9290261a83")
    @Override
    public void execute(EditPartViewer viewer) {
        if (this.finishCommand != null && this.finishCommand.canExecute()) {
            viewer.getEditDomain().getCommandStack().execute(this.finishCommand);
        }
    }

}
