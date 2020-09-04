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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.program.Program;
import org.modelio.app.ui.plugin.AppUi;

@objid ("c80533ae-6a43-44de-b6a5-909ed22e6a27")
public class SubmitIssueHandler {
    @objid ("ec5d7ff6-836d-45a4-9dfe-3768433f4b58")
    @Execute
    public void execute() {
        String href = AppUi.I18N.getString("SubmitIssue.site");
        try {
            Program.launch(href);
        } catch (IllegalArgumentException e) {
            AppUi.LOG.error(e);
        }
    }

    @objid ("176f4621-1200-49ad-b178-2d6854f010bc")
    @CanExecute
    boolean canExecute() {
        return true;
    }

}
