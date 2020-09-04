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

package org.modelio.diagram.editor.context;

import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.AboutToHide;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.modelio.mda.infra.service.IModuleService;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.module.browser.commands.ModulePopupManager;

/**
 * ModuleMenuCreator manage Modules commands in diagrams.
 */
@objid ("866308eb-cd0a-41c0-9f60-3b85c3521218")
public class ModuleMenuCreator {
    @objid ("c0a31703-2dae-4960-83c1-ba1146562131")
    @Inject
    protected MApplication application;

    @objid ("9e3daf2b-805c-41a6-901a-9a0dfd93f313")
    @Optional
    @Inject
    private IModuleService moduleService;

    @objid ("30faa94b-6790-4958-8ff1-8289849aa87c")
    @AboutToShow
    public void aboutToShow(List<MMenuElement> items) {
        MPart activePart = (MPart) this.application.getContext().get(IServiceConstants.ACTIVE_PART);
        if (activePart != null && this.moduleService != null) {
            for (IRTModule module : this.moduleService.getStartedModules()) {
                MMenuElement item = ModulePopupManager.createMenu(module, activePart);
                if (item != null) {
                    items.add(item);
                }
            }
        }
    }

    @objid ("5316cecb-313c-4f34-99ef-ab42d3ecf5c2")
    @AboutToHide
    public void aboutToHide(List<MMenuElement> items) {
        MPart activePart = (MPart) this.application.getContext().get(IServiceConstants.ACTIVE_PART);
        if (activePart != null && this.moduleService != null) {
            // Cleanup menu items
            if (items != null) {
                for (MMenuElement item : items) {
                    if (item.getParent() != null)
                        item.getParent().getChildren().remove(item);
                }
            }
        
            // Cleanup commands & handlers
            for (IRTModule module : this.moduleService.getStartedModules()) {
                ModulePopupManager.removeMenu(module, activePart);
            }
        }
    }

}
