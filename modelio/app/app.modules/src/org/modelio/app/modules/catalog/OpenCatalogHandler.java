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

package org.modelio.app.modules.catalog;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.ui.progress.IModelioProgressService;

/**
 * E4 handler to open the modules catalog.
 */
@objid ("ffa63e83-a8c7-41d1-84e3-3feb029bfd80")
@SuppressWarnings("javadoc")
public class OpenCatalogHandler {
    @objid ("37e43d6f-19db-4c68-9d65-89ce0be1bb60")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, IModuleStore catalog, IModelioProgressService progressService) {
        ModuleCatalogDialog dlg = new ModuleCatalogDialog(shell, catalog, progressService);
        dlg.open();
    }

    @objid ("264d0f22-f84b-4e71-9c20-f86b0e45fa42")
    @CanExecute
    public boolean canExecute() {
        return true;
    }

}
