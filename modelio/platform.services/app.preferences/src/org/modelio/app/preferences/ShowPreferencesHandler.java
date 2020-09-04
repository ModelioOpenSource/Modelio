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

/*
 * Handler to open up a configured preferences dialog.
 * Written by Brian de Alwis, Manumitting Technologies.
 * Placed in the public domain.
 */
package org.modelio.app.preferences;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;

@objid ("276e77f2-b24a-4ea1-a194-b0b9c28b1069")
public class ShowPreferencesHandler {
    @objid ("142cc116-57db-48d7-939f-613dfe014a9f")
    @CanExecute
    public boolean canExecute() {
        // Can always execute. The dialog will adapt its contents to the fact
        // that there is or not an opened project.
        return true;
    }

    @objid ("e3799e44-84b2-4139-8279-1f40ad188777")
    @Execute
    public void execute(final MApplication app, @Named(IServiceConstants.ACTIVE_SHELL) final Shell shell, final IEclipseContext context) {
        final ModelioPreferenceDialog dialog = new ModelioPreferenceDialog(shell, context);
        dialog.open();
    }

}
