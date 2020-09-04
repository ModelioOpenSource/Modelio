/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.api.module.parameter.impl.ParameterModel;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.ui.UIColor;

/**
 * Label provider for the parameter scope column.
 */
@objid ("fa0da1d1-3cc6-4f1b-8204-8089cc1286b0")
class ScopeLabelProvider extends ColumnLabelProvider {
    @objid ("304616c3-633d-4774-8382-6e0486ed1a28")
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

    @objid ("0f11716d-777b-4ca3-80f5-38b13742a2df")
    @Override
    public String getText(Object element) {
        if (element instanceof ParameterModel) {
            ParameterModel property = (ParameterModel) element;
            return property.isLocked() ? AppProjectConfExt.I18N.getString("ParameterSection.Server") : AppProjectConfExt.I18N.getString("ParameterSection.Local");
        } else {
            return "";
        }
    }

}
