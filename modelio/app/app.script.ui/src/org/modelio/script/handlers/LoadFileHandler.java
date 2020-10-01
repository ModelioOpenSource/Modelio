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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.script.plugin.Script;
import org.modelio.script.view.ScriptView;

/**
 * Load file in the script view input field.
 */
@objid ("0049ce10-6505-105c-84ef-001ec947cd2a")
public class LoadFileHandler {
    @objid ("0049f5de-6505-105c-84ef-001ec947cd2a")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, @Named(IServiceConstants.ACTIVE_PART) MPart part) {
        ScriptView scriptView = (ScriptView) part.getObject();
        
        final FileDialog dlg = new FileDialog(shell, SWT.OPEN);
        dlg.setFilterExtensions(new String[] { "*.py; *.jy" });
        dlg.setFilterNames(new String[] { Script.I18N.getString("LoadFileHandler.Filter")});
        
        final String s = dlg.open();
        if (s != null) {
            File f = new File(s);
            try {
                byte[] contents = Files.readAllBytes(f.toPath());
                scriptView.getInputView().append(new String(contents));
            } catch (FileNotFoundException e) {
                Script.LOG.error(e);
                MessageDialog.openError(shell, Script.I18N.getMessage("LoadFileHandler.Error"), Script.I18N.getMessage("LoadFileHandler.FileNotFound", f.getPath()));
            } catch (IOException e) {
                Script.LOG.error(e);
                MessageDialog.openError(shell, Script.I18N.getMessage("LoadFileHandler.Error"), Script.I18N.getMessage("LoadFileHandler.CannotLoadFile", f.getPath(), e.getLocalizedMessage()));
            }
        }
    }

}
