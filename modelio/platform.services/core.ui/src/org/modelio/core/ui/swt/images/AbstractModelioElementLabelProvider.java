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

package org.modelio.core.ui.swt.images;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * Abstract {@link IModelioElementLabelProvider} implementation.
 */
@objid ("0086281a-d911-100f-85b1-001ec947cd2a")
public abstract class AbstractModelioElementLabelProvider extends LabelProvider implements IModelioElementLabelProvider {
    @objid ("0095fa88-f04f-100f-85b1-001ec947cd2a")
    @Override
    public boolean showAsReference(Object object) {
        return false;
    }

}
