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

package org.modelio.platform.model.ui.treetable.directory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.modelio.platform.model.ui.treetable.EditableDialogCellEditor;
import org.modelio.platform.ui.UIImages;

/**
 * CellEditor to choose a directory.
 */
@objid ("cd1551b4-db02-4d28-af82-8fecc86f1280")
public class DirectoryCellEditor extends EditableDialogCellEditor {
    @objid ("0baaf198-4148-462b-b4a3-e4647b33368b")
    public DirectoryCellEditor(Composite parent) {
        super(parent);
    }

    @objid ("bdaaeefb-0612-4cd1-b8bb-551ca31d1145")
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        DirectoryDialog dialog = new DirectoryDialog(Display.getDefault().getActiveShell());
        String directory = dialog.open();
        return directory;
    }

    @objid ("90f25ab4-49c0-446d-a6d0-51527fcc94db")
    @Override
    protected String getTextRepresentation(Object value) {
        return (value != null)? value.toString() : "";
    }

    @objid ("2470de2a-0a79-4428-9754-ad5707795726")
    @Override
    protected void configureButton(Composite parent, Button button) {
        button.setImage(UIImages.FILECHOOSE);
    }

    @objid ("ad70031e-4f11-4930-9017-f23a070e5fd7")
    @Override
    protected void configureText(Composite parent, Text text) {
        super.configureText(parent, text);
        text.setEditable(true);
    }

}
