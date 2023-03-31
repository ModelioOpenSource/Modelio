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
package org.modelio.diagram.elements.core.commands;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmNodeModel} creation command that:
 * <ul>
 * <li>creates and initialize the {@link MObject} if asked.
 * <li>creates the {@link GmNodeModel} and unmask it.
 * </ul>
 * according to the provided {@link ModelioCreationContext}.
 */
@objid ("f195d61f-e2ec-47e6-85a7-126ef5dcaf41")
public class DefaultEditCreatedElementCommand extends Command implements ICreationCommand, ILinkAndNodeCreationSupport {
    @objid ("4d82fbf5-1e7f-43cd-ac36-9aee5ac4e488")
    private Map editPartRegistry;

    @objid ("1adba959-5ef5-42ed-8b49-18ce2a286f15")
    private Command creationCommand;

    @objid ("1be59c23-c358-403e-9459-b7777790de12")
    protected GmNodeModel mainLinkable;

    @objid ("926ad000-3a19-46d7-9691-4b9c00ef1fc7")
    public  DefaultEditCreatedElementCommand(Command creationCommand, Map editPartRegistry) {
        super();
        this.creationCommand = creationCommand;
        this.editPartRegistry = editPartRegistry;
        
    }

    @objid ("f4702326-15c6-4032-98ba-3528274de00e")
    @Override
    public void execute() {
        if(this.creationCommand.canExecute()) {
            this.creationCommand.execute();
        }
        
        if(this.creationCommand instanceof ICreationCommand) {
        
            EditPart editPart = (EditPart) this.editPartRegistry.get(((ICreationCommand)this.creationCommand).getCreatedGraphicModel());
            if(editPart != null) {
                GroupRequest editReq = new GroupRequest(RequestConstants.REQ_DIRECT_EDIT);
                editReq.setEditParts(editPart);
                editPart.performRequest(editReq);
            }
        
        }
        
    }

    @objid ("50c97b5c-6227-4737-a678-a3db898bc159")
    @Override
    public boolean canExecute() {
        return this.creationCommand.canExecute();
    }

    @objid ("586b2f51-e5ce-45aa-b260-7126adb4106f")
    @Override
    public IGmLinkable getCreatedGraphicModel() {
        if(this.creationCommand instanceof ICreationCommand) {
            return ((ICreationCommand)this.creationCommand).getCreatedGraphicModel();
        }
        return null;
    }

    @objid ("6af8da42-44b3-439c-8507-0715eaf5fb15")
    @Override
    public GmNodeModel getMainLinkable() {
        if(this.creationCommand instanceof DefaultCreateElementCommand) {
            return ((DefaultCreateElementCommand)this.creationCommand).getMainLinkable();
        }
        return null;
    }

}
