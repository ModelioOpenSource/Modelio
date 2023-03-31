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
package org.modelio.app.project.conf.dialog.modules.parameters.standard;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColorCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.modelio.api.module.parameter.IParameterModel;
import org.modelio.api.module.parameter.impl.BoolParameterModel;
import org.modelio.api.module.parameter.impl.ColorParameterModel;
import org.modelio.api.module.parameter.impl.DirectoryParameterModel;
import org.modelio.api.module.parameter.impl.EnumParameterModel;
import org.modelio.api.module.parameter.impl.FileParameterModel;
import org.modelio.api.module.parameter.impl.IntParameterModel;
import org.modelio.api.module.parameter.impl.ParameterModel;
import org.modelio.api.module.parameter.impl.PasswordParameterModel;
import org.modelio.api.module.parameter.impl.StringParameterModel;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.platform.model.ui.treetable.combo.LabelsComboBoxCellEditor;
import org.modelio.platform.model.ui.treetable.directory.DirectoryCellEditor;
import org.modelio.platform.model.ui.treetable.file.FileCellEditor;
import org.modelio.platform.model.ui.treetable.number.IntegerCellEditor;

/**
 * StyleEditingSupport provides EditingSupport implementation for the StyleViewer.
 * <p>
 * It must be able to provide a Label and a CellEditor for all the supported StyleKey value types. It must also be able to get and
 * set values during edition, again dealing with all the possible StyleKey value types.
 */
@objid ("e95cc1a9-1ccc-4e98-951f-617b0ed09e45")
class ParametersEditingSupport extends EditingSupport {
    @objid ("381380d5-b813-496c-a64b-0a302f36e6c5")
    private TreeViewer viewer;

    /**
     * Initialize the StylePropertyEditingSupport.
     * @param viewer The style viewer.
     */
    @objid ("024fa361-5ecb-4c61-85cb-9d0fe6995a15")
    public  ParametersEditingSupport(TreeViewer viewer) {
        super(viewer);
        this.viewer = viewer;
        
    }

    @objid ("ba5db310-fc5c-4cff-a7c6-c689b78f9676")
    @Override
    protected boolean canEdit(Object element) {
        if (element instanceof IParameterModel) {
            return !((IParameterModel) element).isLocked();
        } else {
            return false;
        }
        
    }

    @objid ("77ab43c7-9a74-4f82-a194-7aa1ae08720a")
    @Override
    protected CellEditor getCellEditor(Object element) {
        final Tree tree = this.viewer.getTree();
        
        IParameterModel property = ((IParameterModel) element);
        
        // Boolean
        if (property instanceof BoolParameterModel) {
            return new CheckboxCellEditor(tree);
        } else if (property instanceof ColorParameterModel) {
            return new ColorCellEditor(tree);
        } else if (property instanceof DirectoryParameterModel) {
            return new DirectoryCellEditor(tree);
        } else if (property instanceof EnumParameterModel) {
            return new LabelsComboBoxCellEditor(tree, ((EnumParameterModel) element).getLabels().toArray(new String[0]), SWT.SINGLE);
        } else if (property instanceof FileParameterModel) {
            FileParameterModel fileProperty = (FileParameterModel) property;
            return new FileCellEditor(tree, fileProperty.getAllowedExtensionLabels(), fileProperty.getAllowedExtensions());
        } else if (property instanceof IntParameterModel) {
            return new IntegerCellEditor(tree, SWT.SINGLE);
        } else if (property instanceof PasswordParameterModel) {
            return new TextCellEditor(tree, SWT.PASSWORD);
        } else if (property instanceof StringParameterModel) {
            return new TextCellEditor(tree);
        } else {
            AppProjectConfExt.LOG.error("Invalid parameter type"); //$NON-NLS-1$
        }
        return null;
    }

    @objid ("0165394e-4ad7-4e6c-8180-afbd70d0cdc7")
    @Override
    protected Object getValue(Object element) {
        IParameterModel property = ((IParameterModel) element);
        
        // Boolean
        if (property instanceof BoolParameterModel) {
            return Boolean.valueOf(((ParameterModel) property).getStringValue());
        } else if (property instanceof ColorParameterModel) {
            return ((ColorParameterModel) property).getStringValue();
        } else if (property instanceof DirectoryParameterModel) {
            return ((ParameterModel) property).getStringValue();
        } else if (property instanceof EnumParameterModel) {
            return ((EnumParameterModel) element).getLabel(((ParameterModel) property).getStringValue());
        } else if (property instanceof FileParameterModel) {
            return ((ParameterModel) property).getStringValue();
        } else if (property instanceof IntParameterModel) {
            return Integer.valueOf(((ParameterModel) property).getStringValue());
        } else if (property instanceof PasswordParameterModel) {
            return ((PasswordParameterModel) property).getPasswordValue();
        } else if (property instanceof StringParameterModel) {
            return ((ParameterModel) property).getStringValue();
        } else {
            AppProjectConfExt.LOG.error("Invalid parameter type"); //$NON-NLS-1$
        }
        return null;
    }

    @objid ("1cec7488-d2ef-46d1-87f7-60bebc821954")
    @Override
    protected void setValue(Object element, Object value) {
        IParameterModel property = ((IParameterModel) element);
        
        if (property instanceof EnumParameterModel) {
            property.setValue(((EnumParameterModel) element).getValue((String) value));
        } else {
            property.setValue(value);
        }
        this.viewer.refresh(element, true);
        
    }

}
