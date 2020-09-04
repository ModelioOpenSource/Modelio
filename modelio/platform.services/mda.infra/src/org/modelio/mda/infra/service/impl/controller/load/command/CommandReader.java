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

package org.modelio.mda.infra.service.impl.controller.load.command;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.command.DefaultModuleAction;
import org.modelio.api.module.command.IModuleAction;
import org.modelio.api.module.command.IModuleCommandHandler;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Command;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.mda.infra.service.contributions.ContributionReader;

/**
 * Module command reader.
 */
@objid ("1086a98b-242a-48ba-9d79-ed2b587cdc6e")
public class CommandReader {
    @objid ("8bb0ee0d-0240-4df8-bc42-ee7cb6f88806")
    public IModuleAction createCommand(Jxbv2Command commandDef, IRTModule module) throws IOException {
        ContributionReader helper = new ContributionReader(module);
        
        // Create the handler
        IModuleCommandHandler handler = helper.createHandler(
                ContributionReader.CONTRIB_COMMAND, commandDef.getHandler()
                        .getClazz(),
                IModuleCommandHandler.class);
        
        // Collect applicable scopes
        List<ElementScope> scopes = helper.readScopes(commandDef.getScope());
        
        // Collect hParameters
        Map<String, String> hParameters = helper.readParameters(commandDef
                .getHandler().getHParameter());
        
        // Initialize the handler
        handler.initialize(scopes, hParameters);
        
        // Last, create the module action
        // Instantiate a IModuleAction wrapping this handler so it can be used
        // with the platform mechanisms.
        
        String name = module.getLabel(commandDef.getId());
        String label = module.getLabel(commandDef.getLabel());
        String bitmap = module.getLabel(commandDef.getImage());
        String tooltype = module.getLabel(commandDef.getTooltip());
        String group = module.getLabel(commandDef.getGroup());
        String imageGroup = module.getLabel(commandDef.getGroupImage());
        
        boolean editionMode = (commandDef.getModifyModel() != null)
                && commandDef.getModifyModel().equalsIgnoreCase("true");
        
        IModuleAction action = new DefaultModuleAction(module.getIModule(),
                name, label, tooltype, bitmap, group, imageGroup, editionMode,
                editionMode, handler);
        return action;
    }

}
