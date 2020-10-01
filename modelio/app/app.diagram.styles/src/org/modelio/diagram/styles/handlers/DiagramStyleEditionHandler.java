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

/**
 *
 */
package org.modelio.diagram.styles.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;
import org.modelio.diagram.styles.manager.StyleEditorDialog;
import org.modelio.platform.core.picking.IModelioPickingService;
import org.modelio.platform.project.services.IProjectService;

@objid ("85ba0bb4-1926-11e2-92d2-001ec947c8cc")
public class DiagramStyleEditionHandler {
    @objid ("85ba0bb5-1926-11e2-92d2-001ec947c8cc")
    @SuppressWarnings("javadoc")
    @Execute
    public static void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell, IModelioPickingService pickingService) {
        StyleEditorDialog dlg = new StyleEditorDialog(activeShell, pickingService);
        dlg.open();
    }

    @objid ("85ba0bb9-1926-11e2-92d2-001ec947c8cc")
    @CanExecute
    public static boolean isEnabled(IProjectService projectService) {
        return projectService.getOpenedProject() != null;
    }

}
