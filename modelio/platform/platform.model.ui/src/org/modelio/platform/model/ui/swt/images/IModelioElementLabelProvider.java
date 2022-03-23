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
package org.modelio.platform.model.ui.swt.images;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IToolTipProvider;

@objid ("0008f390-d8ec-100f-85b1-001ec947cd2a")
public interface IModelioElementLabelProvider extends IStyledLabelProvider, IToolTipProvider {
    @objid ("00092a4a-d8ec-100f-85b1-001ec947cd2a")
    boolean showAsReference(Object object);

    @objid ("be2c700e-42e2-4504-a15b-01ff36fc2a2a")
    @Override
    default String getToolTipText(Object object) {
        return null;
    }

}
