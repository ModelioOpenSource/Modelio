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

package org.modelio.script.macro.catalogdialog;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

@objid ("00696306-c497-106a-bf4f-001ec947cd2a")
class FileChooserButtonFactory {
    @objid ("00699c68-c497-106a-bf4f-001ec947cd2a")
    public static Button create(final Composite parent, final Text textField, final String aDefaultPath, final String[] filterNames, final String[] filterExtensions) {
        final Button ret = new Button(parent, SWT.PUSH);
        
        final String initPath = textField.getText();
        final String defaultPath;
        if (initPath != null) {
            defaultPath = initPath;
        } else {
            defaultPath = aDefaultPath;
        }
        
        ret.addSelectionListener(new SelectionListener() {
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                //
            }
        
            @Override
            public void widgetSelected(SelectionEvent e) {
                int dlgStyle = SWT.SAVE;
                FileDialog dlg = new FileDialog(ret.getShell(), dlgStyle);
                dlg.setFileName(defaultPath);
                dlg.setFilterExtensions(filterExtensions);
                dlg.setFilterNames(filterNames);
        
                String res = dlg.open();
        
                if (res != null) {
                    textField.setText(res);
                }
            }
        
        });
        return ret;
    }

}
