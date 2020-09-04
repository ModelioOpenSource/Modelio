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

package org.modelio.app.ui.about;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.core.ModelioEnv;

@objid ("0044a624-cc35-1ff2-a7f4-001ec947cd2a")
public class AboutHandler {
    @objid ("004705b8-cc35-1ff2-a7f4-001ec947cd2a")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell, ModelioEnv modelioEnv) {
        AboutDialog dlg = new AboutDialog(shell, modelioEnv);
        dlg.open();
    }

}
