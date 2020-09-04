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

package org.modelio.core.ui.treetable.file;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.modelio.core.ui.treetable.EditableDialogCellEditor;
import org.modelio.ui.UIImages;

/**
 * CellEditor to choose a file
 */
@objid ("d847800b-1718-4f41-b42e-807bea85eaf2")
public class FileCellEditor extends EditableDialogCellEditor {
    @objid ("019dcfe1-94df-4419-947d-7e0d002fdbf9")
    private String[] filterNames;

    @objid ("e9259a7c-aea4-4480-af20-80f3c3291595")
    private String[] filterExtensions;

    @objid ("e061b9b3-8dd1-42f2-bdf1-661b95299d7b")
    public FileCellEditor(Composite parent, String[] filterNames, String[] filterExtensions) {
        super(parent);
        this.filterNames = filterNames;
        this.filterExtensions = filterExtensions;
    }

    @objid ("70d2adc3-04bb-43a6-ae38-1f25b42409aa")
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
        
        dialog.setFilterNames(this.filterNames);
        dialog.setFilterExtensions(this.filterExtensions);
        
        String file = dialog.open();
        return file;
    }

    @objid ("23abe008-b14d-4342-afe0-1115910fa856")
    @Override
    protected String getTextRepresentation(Object value) {
        return (value != null)? value.toString() : "";
    }

    @objid ("1878e3ac-1ef4-4ce4-9820-eeb3c5a75cae")
    @Override
    protected void configureButton(Composite parent, Button button) {
        button.setImage(UIImages.FILECHOOSE);
    }

    @objid ("aab333c9-6c66-4424-ae5c-3e707e1575d1")
    @Override
    protected void configureText(Composite parent, Text text) {
        super.configureText(parent, text);
        text.setEditable(true);
    }

}
