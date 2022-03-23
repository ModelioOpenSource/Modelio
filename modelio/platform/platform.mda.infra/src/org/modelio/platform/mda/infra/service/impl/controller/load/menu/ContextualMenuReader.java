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
package org.modelio.platform.mda.infra.service.impl.controller.load.menu;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.command.ActionLocation;
import org.modelio.api.module.command.IModuleAction;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2ContextualMenu.Jxbv2CommandRef;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;

@objid ("56170d96-7b89-4ff7-b201-8ee5be960a21")
public class ContextualMenuReader {
    @objid ("e93bee7b-8894-4214-a5a3-8377563af159")
    public void registerContextualMenu(IRTModuleAccess module, Jxbv2ContextualMenu menu, Map<String, IModuleAction> commandCache) {
        for (Jxbv2CommandRef ref : menu.getCommandRef()) {
            IModuleAction action = commandCache.get(ref.getRefid());
            if (action != null) {
                if(ref.getGroup() != null && !"".equals(ref.getGroup())){
                    action.parseSlotPattern(module.getLabel(ref.getGroup()));
                    action.parseSlotImagePaths(module.getLabel(ref.getGroupImage()));
                }
                // Menu category is ignored for now, use it to create several menus instead of one
                module.registerAction(ActionLocation.contextualpopup, action);
            } else {
                MdaInfra.LOG.error("Unresolved contextual command ref for '%s' module : '%s'", module.getName(), ref.getRefid());
            }
        }
        
    }

}
