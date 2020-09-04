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

package org.modelio.app.project.conf.dialog.modules.parameters.standard;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.api.module.parameter.impl.BoolParameterModel;
import org.modelio.api.module.parameter.impl.EnumParameterModel;
import org.modelio.api.module.parameter.impl.ParameterModel;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.ui.UIColor;

/**
 * Label provider for the parameter value column.
 */
@objid ("596010c2-a363-44f8-ad73-dab2ba0a1818")
class ValueLabelProvider extends ColumnLabelProvider {
    @objid ("be1deb7e-8c66-4223-b709-271dc9860b6e")
    private static final Image CHECKED = AbstractUIPlugin.imageDescriptorFromPlugin(AppProjectConfExt.PLUGIN_ID, "icons/checked.png").createImage();

    @objid ("34b41a18-f2ee-40c1-b0a9-57d6700abc6d")
    private static final Image UNCHECKED = AbstractUIPlugin.imageDescriptorFromPlugin(AppProjectConfExt.PLUGIN_ID, "icons/unchecked.png").createImage();

    @objid ("3587abcf-b27f-4eec-b2c1-60c8ad4aad11")
    @Override
    public String getToolTipText(Object element) {
        return super.getToolTipText(element);
    }

    @objid ("edf5c9ee-ef3b-429d-ba2c-13ca891cf17d")
    @Override
    public String getText(Object element) {
        if (element instanceof BoolParameterModel) {
            return ""; //$NON-NLS-1$
        } else if (element instanceof EnumParameterModel) {
            return ((EnumParameterModel) element).getLabel(((ParameterModel) element).getStringValue());
        } else if (element instanceof ParameterModel) {
            ParameterModel property = (ParameterModel) element;
            return property.getStringValue();
        } else {
            return ""; //$NON-NLS-1$
        }
    }

    @objid ("81d80e12-32ea-4c05-bcfc-514486127a45")
    @Override
    public Image getImage(Object element) {
        if (element instanceof BoolParameterModel) {
            BoolParameterModel property = (BoolParameterModel) element;
            if (Boolean.parseBoolean(property.getStringValue()))
                return CHECKED;
            else
                return UNCHECKED;
        } else {
            // Default case
            return null;
        }
    }

    @objid ("5ff22b6c-929d-416d-9361-fae3c6214df6")
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
