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

package org.modelio.model.browser.view.context;

import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.AboutToHide;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.modelio.platform.mda.infra.service.IModuleService;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.module.browser.commands.ModulePopupManager;

/**
 * ModuleCommandsForModelBrowser manage Modules commands
 */
@objid ("a25f391f-8de0-4bf9-a51c-a00065365b03")
class ModuleCommandsForModelBrowser {
    @objid ("6a7df8c6-40b0-4cd7-ba9e-d535274c704f")
    @Optional
    @Inject
    private IModuleService moduleService;

    @objid ("fba298cb-0239-4900-83c5-66b6baa841a6")
    @Inject
    protected MApplication application;

    @objid ("931730a5-d01c-4aeb-b1d4-a706c4e71a43")
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

    @objid ("263dc59b-6d6c-411e-bcd0-8c0b81e2fbb4")
    @AboutToHide
    public void aboutToHide(List<MMenuElement> items) {
        MPart activePart = (MPart) this.application.getContext().get(IServiceConstants.ACTIVE_PART);
        if (activePart != null && this.moduleService != null && items != null) {
            // Cleanup menu items
            for (MMenuElement item : items) {
                final MElementContainer<MUIElement> parentItem = item.getParent();
                if (parentItem != null) {
                    parentItem.getChildren().remove(item);
                }
            }
        
            // Cleanip commands & handlers
            for (IRTModule module : this.moduleService.getStartedModules()) {
                ModulePopupManager.removeMenu(module, activePart);
            }
        }
    }

}
