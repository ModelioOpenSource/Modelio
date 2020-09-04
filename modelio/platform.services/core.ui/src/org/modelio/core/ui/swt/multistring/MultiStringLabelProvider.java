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

package org.modelio.core.ui.swt.multistring;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.plugin.CoreUi;

@objid ("8dd22d8e-c068-11e1-8c0a-002564c97630")
class MultiStringLabelProvider extends LabelProvider {
    @objid ("8dd22d8f-c068-11e1-8c0a-002564c97630")
    @Override
    public Image getImage(final Object element) {
        return null;
    }

    @objid ("8dd22d95-c068-11e1-8c0a-002564c97630")
    @Override
    public String getText(final Object element) {
        String value = (String)element;
        if (value == null || value.isEmpty()) {
            value = CoreUi.I18N.getString("MultiStringEditionDialog.EnterParameterHere");
        }
        return value;
    }

}
