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

package org.modelio.platform.model.ui.nattable.parts.data.string.single;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;

/**
 * Display converter replacing 'null' values with an empty String.
 */
@objid ("0db74a31-bff2-4cb2-a3c6-35a276348d20")
public class DefaultStringDisplayConverter extends DisplayConverter {
    @objid ("9a26476e-965d-420d-bdc5-455b27826203")
    @Override
    public Object canonicalToDisplayValue(Object sourceValue) {
        return sourceValue != null ? sourceValue.toString() : ""; //$NON-NLS-1$
    }

    @objid ("815ce1b6-551a-4c35-9b32-3f0a1b187ef7")
    @Override
    public Object displayToCanonicalValue(Object destinationValue) {
        if (destinationValue == null || destinationValue.toString().length() == 0) {
            return "";
        } else {
            return destinationValue.toString();
        }
    }

}
