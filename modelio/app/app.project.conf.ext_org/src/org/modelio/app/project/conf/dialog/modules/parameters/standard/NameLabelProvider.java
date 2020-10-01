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
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.module.IModule;
import org.modelio.api.module.parameter.IParameterGroupModel;
import org.modelio.api.module.parameter.impl.ParameterModel;
import org.modelio.platform.ui.UIColor;

/**
 * Label provider for the parameter name column.
 */
@objid ("fbd136b3-e365-4d90-8a07-a4058681de7f")
class NameLabelProvider extends ColumnLabelProvider {
    @objid ("88b21814-74f1-4b53-ace2-dcaa78a4dcbd")
    @Override
    public String getText(Object element) {
        if (element instanceof IModule) {
            IModule module = (IModule) element;
            return module.getLabel();
        } else if (element instanceof IParameterGroupModel) {
            IParameterGroupModel groupModel = (IParameterGroupModel) element;
            return groupModel.getLabel();
        } else if (element instanceof ParameterModel) {
            ParameterModel property = (ParameterModel) element;
            return property.getLabel();
        } else {
            return super.getText(element);
        }
    }

    @objid ("fc176d88-c2cf-46f8-9da2-08f2292a9654")
    @Override
    public Image getImage(Object element) {
        if (element instanceof IModule) {
            IModule module = (IModule) element;
            return module.getModuleImage();
        } else {
            return super.getImage(element);
        }
    }

    @objid ("485afb4d-8c16-47af-b5cb-59bbb4b3e7aa")
    @Override
    public Color getForeground(Object element) {
        if (element instanceof ParameterModel) {
            ParameterModel property = (ParameterModel) element;
            if (property.isLocked()) {
                return UIColor.NONMODIFIABLE_ELEMENT_FG;
            } else {
                return UIColor.MODIFIABLE_ELEMENT_FG;
            }
        }
        return super.getForeground(element);
    }

}
