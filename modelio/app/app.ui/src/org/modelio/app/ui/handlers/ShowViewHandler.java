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

package org.modelio.app.ui.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuItem;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.ui.plugin.AppUi;

/**
 * Command handler to show a clicked view from the view menu To not show a view
 * by default, unselect the checkbox 'To Be Rendered' in e4xmi of the view part
 * 
 * @author xzhang
 */
@objid ("bb2d84b6-440c-400b-9ab0-839f0c6b3dbe")
public class ShowViewHandler {
    @objid ("b78ed128-37cf-4e9c-b16d-81041639a420")
    @Execute
    public void execute(EPartService partService, MMenuItem item, MWindow window, @Optional @Named("org.modelio.app.ui.command.showview.viewid") String viewid) {
        MPart part = null;
        
        // Get a shared part (if exists)
        for (MUIElement x : window.getSharedElements()) {
            if (x.getElementId().equals(viewid)) {
                part = (MPart) x;
                break;
            }
        }
        
        // Get an existing part
        if (part == null) {
            part = partService.findPart(viewid);
        }
        
        // Create one
        if (part == null)
            part = partService.createPart(viewid);
        
        if (part != null) {
            partService.showPart(part, PartState.ACTIVATE);
            AppUi.LOG.debug("Show view %s", viewid);
        } else {
            AppUi.LOG.debug("The view %s is null.", viewid);
        }
    }

    @objid ("aeebd232-0bce-4556-894c-c65de976dfc7")
    @CanExecute
    boolean canExecute(@Optional IProjectService projectService) {
        // Show view buttons are not enabled when there is no opened project.
        return projectService != null && projectService.getOpenedProject() != null;
    }

}
