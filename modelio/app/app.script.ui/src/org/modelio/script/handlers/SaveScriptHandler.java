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
package org.modelio.script.handlers;

import java.io.FileWriter;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.script.plugin.Script;
import org.modelio.script.view.ScriptView;

@objid ("0044f2dc-6505-105c-84ef-001ec947cd2a")
public class SaveScriptHandler {
    @objid ("0045149c-6505-105c-84ef-001ec947cd2a")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, @Named(IServiceConstants.ACTIVE_PART) MPart part) {
        final ScriptView scriptView = (ScriptView) part.getObject();
        
        final String content = scriptView.getInputView().getText();
        if (content == null || content.isEmpty()) {
            return null;
        }
        
        final FileDialog dlg = new FileDialog(shell, SWT.SAVE);
        
        dlg.setFilterExtensions(new String[] { "*.py; *.jy" });
        dlg.setFilterNames(new String[] { "Python files" });
        // dlg.setFilterPath(new File(O.getDefault().getWorkspacePath(),
        // "macro").getPath());
        
        final String s = dlg.open();
        if (s != null) {
            try (FileWriter fileWriter = new FileWriter(s, false)) {
                fileWriter.append(content);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                MessageDialog.openError(shell, Script.I18N.getMessage("Error"),
                        Script.I18N.getMessage("CannotSaveFile", s, e.getLocalizedMessage()));
            }
        }
        return null;
    }

}
