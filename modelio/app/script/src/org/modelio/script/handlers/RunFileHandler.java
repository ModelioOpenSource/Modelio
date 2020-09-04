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

package org.modelio.script.handlers;

import java.nio.file.Paths;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.script.engine.core.engine.IScriptRunner;
import org.modelio.script.view.ScriptView;
import org.modelio.script.view.ScriptViewSelectionGetter;

@objid ("004655dc-6505-105c-84ef-001ec947cd2a")
public class RunFileHandler {
    @objid ("0046762a-6505-105c-84ef-001ec947cd2a")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, @Named(IServiceConstants.ACTIVE_PART) MPart part) {
        ScriptView scriptView = (ScriptView) part.getObject();
        
        final IScriptRunner scriptRunner = scriptView.getScriptRunner();
        
        final FileDialog dlg = new FileDialog(shell, SWT.OPEN);
        dlg.setFilterExtensions(new String[] { "*.py; *.jy" });
        dlg.setFilterNames(new String[] { "Python files" });
        
        final String s = dlg.open();
        if (s != null) {
            scriptView.getOutputWriter();
            ScriptViewSelectionGetter selectionGetter = scriptView.getSelectionGetter();
            scriptRunner.runFile(Paths.get(s), selectionGetter.getSelection(), selectionGetter.getSelectedElements());
        }
    }

    @objid ("0046b266-6505-105c-84ef-001ec947cd2a")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
        if (part != null && part.getObject() instanceof ScriptView) {
            return ((ScriptView) part.getObject()).getScriptRunner() != null;
        } else {
            return false;
        }
    }

}
