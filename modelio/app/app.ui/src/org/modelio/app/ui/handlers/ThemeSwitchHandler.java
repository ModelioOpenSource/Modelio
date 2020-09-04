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
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.ui.plugin.AppUi;

@objid ("002fd776-72a8-1ffd-ba14-001ec947cd2a")
public class ThemeSwitchHandler {
    @objid ("001fc53e-7425-1ffd-ba14-001ec947cd2a")
    private static final String THEMEID_PARAMETER = "org.modelio.app.ui.command.switchtheme.parameter.themeId";

    @objid ("002ff198-72a8-1ffd-ba14-001ec947cd2a")
    @Execute
    public void switchTheme(IThemeEngine engine, @Named(THEMEID_PARAMETER) String themeId, @Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
        // The last argument controls
        // whether the change should be persisted and
        // restored on restart
        engine.setTheme(themeId, true);
        
        MessageDialog.openWarning(shell, AppUi.I18N.getString("SwitchThemeWarning.title"),
                AppUi.I18N.getString("SwitchThemeWarning.message"));
        
        // TODO ideally propose a restart of the application
        // currently (july 2012) there is no means of restarting the application
        // programmatically.
    }

    @objid ("0030343c-72a8-1ffd-ba14-001ec947cd2a")
    @CanExecute
    boolean canExecute(IThemeEngine engine, @Named(THEMEID_PARAMETER) String themeId) {
        return !(engine.getActiveTheme().getId().equals(themeId));
    }

}
